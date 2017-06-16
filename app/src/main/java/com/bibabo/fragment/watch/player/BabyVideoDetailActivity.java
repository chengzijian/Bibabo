package com.bibabo.fragment.watch.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bibabo.R;
import com.bibabo.base.MVPBaseActivity;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.MainListDto;
import com.bibabo.framework.fragmentation.anim.DefaultHorizontalAnimator;
import com.bibabo.framework.fragmentation.anim.FragmentAnimator;
import com.bibabo.framework.utils.StringUtils;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;

/**
 * Created by zijian.cheng on 2017/6/16.
 */
public class BabyVideoDetailActivity extends MVPBaseActivity<BabyVideoDetailContract.View, BabyVideoDetailPresenter>
        implements ListBaseView, BabyVideoDetailContract.View {

    public static final String INTENT_URL = "html_url";

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.detail_player)
    ListGSYVideoPlayer detailPlayer;

    private VideoListAdapter mAdapter;
    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;

    public static void launch(Context context, String url) {
        Intent intent = new Intent();
        intent.setClass(context, BabyVideoDetailActivity.class);
        intent.putExtra(INTENT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_video_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecyclerView();
        initPlayerVideo();
        String mHtmlUrl = getIntent().getStringExtra(INTENT_URL);
        if (!StringUtils.isEmpty(mHtmlUrl)) {
            presenter.fetchVideoUrl(mHtmlUrl);
        }
    }

    private void initRecyclerView() {
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setFadingEdgeLength(0);
        mRecyclerView.setVerticalScrollBarEnabled(false);
        mRecyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        mRecyclerView.setAdapter(mAdapter = new VideoListAdapter());
    }

    private void initPlayerVideo() {

        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
//        detailPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        detailPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(BabyVideoDetailActivity.this, true, true);
            }
        });

        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!detailPlayer.isIfCurrentIsFullscreen()) {
                    detailPlayer.startWindowFullscreen(BabyVideoDetailActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (detailPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getTitleTextView().setText("测试视频");
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void playVideo(String url) {
//        List<GSYVideoModel> urls = new ArrayList<>();
//        urls.add(new GSYVideoModel("http://baobab.wdjcdn.com/14564977406580.mp4", "标题1"));
//        urls.add(new GSYVideoModel("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", "标题2"));
//        urls.add(new GSYVideoModel("http://baobab.wdjcdn.com/14564977406580.mp4", "标题3"));
//        urls.add(new GSYVideoModel("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", "标题4"));
//        detailPlayer.setUp(urls, true, 0, "");
        detailPlayer.setUp(url, false, null, "加载成功");
    }

    @Override
    public void setEnd(boolean end) {

    }

    @Override
    public void setCurrPage(int page) {

    }

    @Override
    public void setRefresh(boolean refresh) {

    }

    @Override
    public void notifyLoadingStarted() {

    }

    @Override
    public void notifyLoadingFinished() {

    }

    @Override
    public void error() {

    }

    private class VideoListAdapter extends BaseRecyclerListAdapter<MainListDto, ViewHolder> {

        @Override
        public boolean setSelectableItemBackground() {
            return false;
        }

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_video_list;
        }

        @Override
        protected void convert(final ViewHolder holder, MainListDto userInfo, int position) {

        }
    }


}

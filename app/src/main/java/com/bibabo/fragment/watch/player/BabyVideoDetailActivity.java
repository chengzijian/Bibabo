package com.bibabo.fragment.watch.player;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;

import com.bibabo.R;
import com.bibabo.base.MVPBaseActivity;
import com.bibabo.base.list.ListBaseView;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.event.ExitVideoPlayerEvent;
import com.bibabo.event.GetVideoInfoEvent;
import com.bibabo.event.PlayVideoEvent;
import com.bibabo.event.PlayVideoListEvent;
import com.bibabo.fragment.watch.player.detail.VideoDetailFragment;
import com.bibabo.framework.fragmentation.SupportFragment;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;
import com.bibabo.widget.VideoPlayer.QQVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 *
 * Created by zijian.cheng on 2017/6/16.
 */
public class BabyVideoDetailActivity extends MVPBaseActivity<BabyVideoDetailContract.View, BabyVideoDetailPresenter>
        implements ListBaseView, BabyVideoDetailContract.View {

    public static final String INTENT_URL = "movie_vid";

    @BindView(R.id.detail_player)
    QQVideoPlayer detailPlayer;

    private ImageView previewImageView;

    private OrientationUtils orientationUtils;

    private boolean isPlay;
    private boolean isPause;

    public static void launch(Context context, String vid) {
        Intent intent = new Intent();
        intent.setClass(context, BabyVideoDetailActivity.class);
        intent.putExtra(INTENT_URL, vid);
        context.startActivity(intent);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected boolean needWebViewService() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        String vid = getIntent().getStringExtra(INTENT_URL);
        SupportFragment fragment = findFragment(VideoDetailFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, VideoDetailFragment.newInstance(vid));
        }
        initPlayerVideo();
    }

    @Subscribe
    public void playVideo(PlayVideoEvent event) {
        detailPlayer.setUp(null, event.position);
    }

    /**
     * 设置播放列表
     * @param event
     */
    @Subscribe
    public void setVideoPlayerList(PlayVideoListEvent event){
        List<QQListInfoResult.DataBean> videoList = VideoCommonData.getVideoList();
        if(videoList != null && videoList.size() > 0){
            detailPlayer.setUp(videoList, 0);
        }
    }

    /**
     * 获取当前播放的视频的信息
     * @param event
     */
    @Subscribe
    public void fetchVideoInfo(GetVideoInfoEvent event){
        loadLocalUrl(event.getInfo, new InJavaScriptLocalObj(){
            @Override
            @JavascriptInterface
            public void showSource(String html) {
                String getInfoUrl = Jsoup.parse(html).getElementById("get_info_div").text();
                if (!StringUtils.isEmpty(getInfoUrl)) {
                    //https://vv.video.qq.com/getinfo?
                    LogUtils.e("getInfoUrl：", getInfoUrl);
                    presenter.fetchVideoPlayInfo(getInfoUrl);
                } else {
                    String getKeyUrl = Jsoup.parse(html).getElementById("get_key_div").text();
                    if (!StringUtils.isEmpty(getKeyUrl)) {
                        //https://vv.video.qq.com/getkey?
                        LogUtils.e("nextVideoUrlKey:", getKeyUrl);
                        presenter.fetchNextInfo(getKeyUrl);
                    }
                }
            }
        });
    }

    /**
     * 得到下一段视频播放地址成功
     * @param videoUrl
     */
    @Override
    public void playNextPackVideo(String videoUrl) {
        LogUtils.e("NextVideoUrl:", videoUrl);
        detailPlayer.setUp(videoUrl);
    }

    @Override
    public void playVideo(List<CustomVideoModel> list) {
        //默认播放第1段视频
        detailPlayer.setCurrentVideo(list, 0);
    }

    private void initPlayerVideo() {
//        //增加封面
//        previewImageView = new ImageView(this);
//        previewImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        previewImageView.setImageResource(R.mipmap.ic_launcher);
//        detailPlayer.setThumbImageView(previewImageView);
//
//        //设置返回键
//        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
//        //设置返回按键功能
//        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressedSupport();
//            }
//        });
//
//        //画面比例 全屏
//        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
//
//        //外部辅助的旋转，帮助全屏
//        orientationUtils = new OrientationUtils(this, detailPlayer);
//        //初始化不打开外部的旋转
//        orientationUtils.setEnable(false);
//        //是否可以滑动调整
//        detailPlayer.setIsTouchWiget(true);
//        //关闭自动旋转
//        detailPlayer.setRotateViewAuto(false);
//        detailPlayer.setLockLand(false);
//        detailPlayer.setShowFullAnimation(false);
//        detailPlayer.setNeedLockFull(true);
//
//        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //直接横屏
//                orientationUtils.resolveByClick();
//
//                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
//                detailPlayer.startWindowFullscreen(BabyVideoDetailActivity.this, true, true);
//            }
//        });
//
//        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
//            @Override
//            public void onPrepared(String url, Object... objects) {
//                super.onPrepared(url, objects);
//                //开始播放了才能旋转和全屏
//                orientationUtils.setEnable(true);
//                isPlay = true;
//            }
//
//            @Override
//            public void onAutoComplete(String url, Object... objects) {
//                super.onAutoComplete(url, objects);
//            }
//
//            @Override
//            public void onClickStartError(String url, Object... objects) {
//                super.onClickStartError(url, objects);
//            }
//
//            @Override
//            public void onQuitFullscreen(String url, Object... objects) {
//                super.onQuitFullscreen(url, objects);
//                if (orientationUtils != null) {
//                    orientationUtils.backToProtVideo();
//                }
//            }
//        });
//
//        detailPlayer.setLockClickListener(new LockClickListener() {
//            @Override
//            public void onClick(View view, boolean lock) {
//                if (orientationUtils != null) {
//                    //配合下方的onConfigurationChanged
//                    orientationUtils.setEnable(!lock);
//                }
//            }
//        });
//
//        detailPlayer.setFetchNextPackVkey(new DefaultVideoPlayer.FetchNextPackVkey() {
//            @Override
//            public void fetchVkey(CustomVideoModel model) {
//                String playPrefix = model.getPlayPrefix();
//                String keyid = model.getKeyid();
//                String guid = ShowConfig.GUID;
//
//                String vkey = model.getFvkey();
//                if (StringUtils.isEmpty(vkey)) {
//                    String keyId = model.getKeyid();
//                    String format = keyid.substring(keyId.indexOf(".p") + 2);
//                    format = "10" + format.substring(0, format.indexOf("."));
//                    String url = String.format("file:///android_asset/qv_url.html?vid=%1$s&guid=%2$s&filename=%3$s&timestamp=%4$s&format=%5$s",
//                            model.getVid(), ShowConfig.GUID, keyId, String.valueOf(System.currentTimeMillis() / 1000), format);
//                    loadLocalUrl(url, new InJavaScriptLocalObj(){
//                        @Override
//                        @JavascriptInterface
//                        public void showSource(String html) {
//                            String getKeyUrl = Jsoup.parse(html).getElementById("get_key_div").text();
//                            if (!StringUtils.isEmpty(getKeyUrl)) {
//                                //https://vv.video.qq.com/getkey?
//                                LogUtils.e("nextVideoUrlKey:", getKeyUrl);
//                                presenter.fetchNextInfo(getKeyUrl);
//                            }
//                        }
//                    });
//                } else {
//                    String videoUrl = String.format(PLAY_VIDEO_URL, playPrefix, keyid, guid, vkey);
//                    LogUtils.e("videoUrl:", videoUrl);
//                    detailPlayer.playNextPackVideo(new GSYVideoModel(videoUrl, model.getTitle()));
//                }
//            }
//        });
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

    @Subscribe
    public void exitPlayerVideo(ExitVideoPlayerEvent event){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        VideoCommonData.clear();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
//        if (isPlay && !isPause) {
//            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
//                if (!detailPlayer.isIfCurrentIsFullscreen()) {
//                    detailPlayer.startWindowFullscreen(BabyVideoDetailActivity.this, true, true);
//                }
//            } else {
//                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
//                if (detailPlayer.isIfCurrentIsFullscreen()) {
//                    StandardGSYVideoPlayer.backFromWindowFull(this);
//                }
//                if (orientationUtils != null) {
//                    orientationUtils.setEnable(true);
//                }
//            }
//        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void setEnd(boolean end) {

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
}

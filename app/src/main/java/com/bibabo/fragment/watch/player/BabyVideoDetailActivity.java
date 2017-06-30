package com.bibabo.fragment.watch.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bibabo.R;
import com.bibabo.base.MVPBaseActivity;
import com.bibabo.base.list.BaseRecyclerListAdapter;
import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.list.ViewHolder;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.PlayVideoData;
import com.bibabo.entity.VideoData;
import com.bibabo.framework.fragmentation.anim.DefaultHorizontalAnimator;
import com.bibabo.framework.fragmentation.anim.FragmentAnimator;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;
import com.bibabo.widget.DefaultVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 * Created by zijian.cheng on 2017/6/16.
 */
public class BabyVideoDetailActivity extends MVPBaseActivity<BabyVideoDetailContract.View, BabyVideoDetailPresenter>
        implements ListBaseView, BabyVideoDetailContract.View {

    public static final String INTENT_URL = "movie_vid";

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.detail_player)
    DefaultVideoPlayer detailPlayer;

    private VideoListAdapter mAdapter;
    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;

    public static void launch(Context context, String vid) {
        Intent intent = new Intent();
        intent.setClass(context, BabyVideoDetailActivity.class);
        intent.putExtra(INTENT_URL, vid);
        context.startActivity(intent);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_video_detail;
    }

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecyclerView();
        initPlayerVideo();
        initWebView();

        String vid = getIntent().getStringExtra(INTENT_URL);
        if (!StringUtils.isEmpty(vid)) {
            presenter.fetchQQVideoUrl(vid);
        }
    }

    private void initWebView() {
        mWebView = new WebView(getContext());
        //下面三行设置主要是为了待webView成功加载html网页之后，我们能够通过webView获取到具体的html字符串
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<body>'+"
                        + "document.getElementsByTagName('body')[0].innerHTML+'</body>');");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        //mWebView.loadUrl("file:///android_asset/qv_url.html?filename=123");
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            //refreshHtmlContent(html);
            LogUtils.e("", html);
            String getInfoUrl = Jsoup.parse(html).getElementById("get_info_div").text();
            if (!StringUtils.isEmpty(getInfoUrl)) {
                //https://vv.video.qq.com/getinfo?
                presenter.fetchVideoInfo(getInfoUrl);
            } else {
                String getKeyUrl = Jsoup.parse(html).getElementById("get_key_div").text();
                if (!StringUtils.isEmpty(getKeyUrl)) {
                    //https://vv.video.qq.com/getkey?
                    presenter.fetchNextInfo(getKeyUrl);
                }
            }
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
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        detailPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //设置返回键
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置返回按键功能
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        //画面比例 全屏
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        //是否可以滑动调整
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
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
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
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getTitleTextView().setText("测试视频");
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void playVideo(PlayVideoData result) {
        List<GSYVideoModel> urls = new ArrayList<>();
        urls.add(new GSYVideoModel(result.getUrl(), result.getTitle()));
        detailPlayer.setUp(urls, 0);
    }

    @Override
    public void fetchVideoUrlSuccess(VideoData result) {
        String url = String.format("file:///android_asset/qv_url.html?vid=%1$s&guid=%2$s&platform=10901&sdtfrom=v1010&defn=shd&ehost=%3$s&timestamp=%4$s",
                result.getVid(), result.getGuid(), result.getEhost(), String.valueOf(System.currentTimeMillis() / 1000));
        LogUtils.e("getInfoUrl:", url);
        mWebView.loadUrl(url);
    }

    @Override
    public void fetchVideoUrlSuccess(List<PlayVideoData> result) {
//        String url = String.format("file:///android_asset/qv_url.html?vid=%1$s&guid=%2$s&platform=10901&sdtfrom=v1010&defn=shd&ehost=%3$s&timestamp=%4$s",
//                result.getVid(), result.getGuid(), result.getEhost(), String.valueOf(System.currentTimeMillis() / 1000));
//        LogUtils.e("getInfoUrl:", url);
//        mWebView.loadUrl(url);
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

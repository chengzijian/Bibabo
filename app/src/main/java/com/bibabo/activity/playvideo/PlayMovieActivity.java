package com.bibabo.activity.playvideo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.EditText;

import com.bibabo.R;
import com.bibabo.base.MVPBaseActivity;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.MovieCoverInfo;
import com.bibabo.event.ExitVideoPlayerEvent;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.PromptUtils;
import com.bibabo.framework.utils.StringUtils;
import com.bibabo.videoplayer.JCVideoPlayer;
import com.bibabo.widget.VideoPlayer.QQVideoPlayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by zijian.cheng on 2017/7/5.
 */

public class PlayMovieActivity extends MVPBaseActivity<PlayMovieContract.View, PlayMoviePresenter>
        implements PlayMovieContract.View {

    public static void launch(Context context) {
        Intent intent = new Intent(context, PlayMovieActivity.class);
        //intent.putExtra(ShowConfig.INTENT_CLICK_URL, url);
        context.startActivity(intent);
    }

    //    @BindView(R.id.jc_video)
//    JCVideoPlayerStandard mJcVideoPlayerStandard;
    @BindView(R.id.id_video_html)
    EditText mPlayVideoUrl;
    private String ehost;
    @BindView(R.id.detail_player)
    QQVideoPlayer detailPlayer;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_play_movie;
    }

    @Override
    protected boolean needWebViewService() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (savedInstanceState == null) {
            //loadRootFragment(R.id.fl_container, PlayMovieFragment.newInstance());
        }

//        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "tt");


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

    @OnClick(R.id.id_analytic_url)
    public void onClickPlayVideo() {
        ehost = mPlayVideoUrl.getText().toString();
        if (StringUtils.isEmpty(ehost)) {
            PromptUtils.getInstance().showToast("播放地址不能为空");
            return;
        }
        //分析html网页，获取vid等必要的参数，getinfo的前提。
        presenter.analyUrl(ehost);
    }

    /**
     * 获取影片信息成功
     *
     * @param result
     */
    @Override
    public void fetchCoverInfoSuccess(MovieCoverInfo result) {
        LogUtils.e("获取影片信息成功", result.getTitle());

        List<String> vids = result.getVideo_ids();
        String vid = null;
        if (vids.size() > 0) {
            for (String v : vids) {
                if (!StringUtils.isEmpty(v)) {
                    vid = v;
                    break;
                }
            }
        }
        //获取getinfo 播放信息
        String url = String.format(ShowConfig.GET_INFO, vid, ShowConfig.GUID, ehost, String.valueOf(System.currentTimeMillis() / 1000));
        loadLocalUrl(url, new InJavaScriptLocalObj() {
            @Override
            @JavascriptInterface
            public void showSource(String html) {
                String getInfoUrl = Jsoup.parse(html).getElementById("get_info_div").text();
                if (!StringUtils.isEmpty(getInfoUrl)) {
                    //https://vv.video.qq.com/getinfo?
                    LogUtils.e("getInfoUrl", getInfoUrl);
                    //获取mp4播放地址
                    presenter.fetchMP4PlayUrl(getInfoUrl);
                }
            }
        });
    }

    @Override
    public void playVideo(List<CustomVideoModel> list) {
        //默认播放第1段视频
        detailPlayer.setCurrentVideo(list, 0);
    }

    /**
     * 得到下一段视频播放地址成功
     *
     * @param videoUrl
     */
    @Override
    public void playNextPackVideo(String videoUrl) {
        LogUtils.e("NextVideoUrl:", videoUrl);
        detailPlayer.setUp(videoUrl);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Subscribe
    public void exitPlayerVideo(ExitVideoPlayerEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}

package com.bibabo.widget.VideoPlayer;

import android.content.Context;
import android.util.AttributeSet;

import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.QQListInfoResult.DataBean;
import com.bibabo.event.GetVideoInfoEvent;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;
import com.bibabo.videoplayer.JCVideoPlayerStandard;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zijian.cheng on 2017/7/11.
 */

public class QQVideoPlayer extends JCVideoPlayerStandard {

    private String playPrefix;

    protected List<DataBean> mUriList = new ArrayList();
    protected int mPlayPosition;
    private DataBean.VideoItemBean mVideoItemBean;
    private List<CustomVideoModel> currentVideo;
    private int mCurrPlayPosition;

    public QQVideoPlayer(Context context) {
        super(context);
    }

    public QQVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp(List<DataBean> url, int position) {
        if(url != null){
            this.mUriList = url;
        }
        if(position < mUriList.size()){
            mCurrPlayPosition = 0;
            this.mPlayPosition = position;
            mVideoItemBean = mUriList.get(position).getVideoItem();

            String getInfo = String.format("file:///android_asset/qv_url.html?vid=%1$s&guid=%2$s&platform=10901&sdtfrom=v1010&defn=hd&ehost=%3$s&timestamp=%4$s",
                    mVideoItemBean.getVid(), ShowConfig.GUID, "", String.valueOf(System.currentTimeMillis() / 1000));
            EventBus.getDefault().post(new GetVideoInfoEvent(getInfo));
        }
    }

    public void setCurrentVideo(int position) {
        if(this.currentVideo != null){
            setCurrentVideo(null, position);
        }
    }

    /**
     * 设置当前播放的视频源
     * @param currentVideo
     */
    public void setCurrentVideo(List<CustomVideoModel> currentVideo, int position) {
        this.mCurrPlayPosition = position;
        if(currentVideo != null){
            this.currentVideo = currentVideo;
        }
        if(this.currentVideo != null && mCurrPlayPosition < this.currentVideo.size()){
            getRealVideoPlayUrl(this.currentVideo.get(mCurrPlayPosition));
        }
    }

    /**
     * 获取播放地址
     * @param model
     * @return
     */
    private void getRealVideoPlayUrl(CustomVideoModel model) {
        playPrefix = model.getPlayPrefix();
        String keyid = model.getKeyid();
        String guid = ShowConfig.GUID;

        String vkey = model.getFvkey();
        //这里存在vkey为空的情况，需要异步去获取
        if (StringUtils.isEmpty(vkey)) {
            LogUtils.e("", "vkey is null");
            String format = keyid.substring(keyid.indexOf(".p") + 2);
            format = "10" + format.substring(0, format.indexOf("."));
            String getkey = String.format("file:///android_asset/qv_url.html?vid=%1$s&guid=%2$s&filename=%3$s&timestamp=%4$s&format=%5$s",
                    model.getVid(), ShowConfig.GUID, keyid, String.valueOf(System.currentTimeMillis() / 1000), format);
            EventBus.getDefault().post(new GetVideoInfoEvent(getkey));
        } else {
            String videoUrl = String.format(ShowConfig.PLAY_VIDEO_URL, keyid, guid, vkey);
            LogUtils.e("videoUrl:", videoUrl);
            setUp(videoUrl);
        }
    }

    public void setUp(String videoUrl){
        if(!StringUtils.isEmpty(playPrefix))
            videoUrl = playPrefix + videoUrl;
        this.setUp(videoUrl, SCREEN_LAYOUT_NORMAL, this.currentVideo.get(mCurrPlayPosition).getTitle());
    }

//    public boolean setUp(List<DataBean> url, boolean cacheWithPlay, int position, Object... objects) {
//        this.mUriList = url;
//        this.mPlayPosition = position;
//        GSYVideoModel gsyVideoModel = (GSYVideoModel)url.get(position);
//        boolean set = this.setUp(gsyVideoModel.getUrl(), cacheWithPlay, objects);
//        if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
//            this.mTitleTextView.setText(gsyVideoModel.getTitle());
//        }
//
//        return set;
//    }
//
//    public boolean setUp(List<DataBean> url, boolean cacheWithPlay, int position, File cachePath, Object... objects) {
//        this.mUriList = url;
//        this.mPlayPosition = position;
//        GSYVideoModel gsyVideoModel = (GSYVideoModel)url.get(position);
//        boolean set = this.setUp(gsyVideoModel.getUrl(), cacheWithPlay, cachePath, objects);
//        if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
//            this.mTitleTextView.setText(gsyVideoModel.getTitle());
//        }
//
//        return set;
//    }
//
//    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
//        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
//        if(gsyBaseVideoPlayer != null) {
//            QQVideoPlayer QQVideoPlayer = (QQVideoPlayer)gsyBaseVideoPlayer;
//            QQVideoPlayer.mPlayPosition = this.mPlayPosition;
//            QQVideoPlayer.mUriList = this.mUriList;
//            GSYVideoModel gsyVideoModel = (GSYVideoModel)this.mUriList.get(this.mPlayPosition);
//            if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
//                QQVideoPlayer.mTitleTextView.setText(gsyVideoModel.getTitle());
//            }
//        }
//
//        return gsyBaseVideoPlayer;
//    }
//
//    protected void resolveNormalVideoShow(View oldF, ViewGroup vp, DataBean gsyVideoPlayer) {
//        if(gsyVideoPlayer != null) {
//            QQVideoPlayer QQVideoPlayer = (QQVideoPlayer)gsyVideoPlayer;
//            this.mPlayPosition = QQVideoPlayer.mPlayPosition;
//            this.mUriList = QQVideoPlayer.mUriList;
//            GSYVideoModel gsyVideoModel = (GSYVideoModel)this.mUriList.get(this.mPlayPosition);
//            if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
//                this.mTitleTextView.setText(gsyVideoModel.getTitle());
//            }
//        }
//
//        super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);
//    }
//
//    public void onCompletion() {
//        if(this.mPlayPosition >= this.mUriList.size() - 1) {
//            super.onCompletion();
//        }
//    }
//
//    public void onAutoCompletion() {
//        if(this.mPlayPosition < this.mUriList.size() - 1) {
//            ++this.mPlayPosition;
//            DataBean gsyVideoModel = (DataBean)this.mUriList.get(this.mPlayPosition);
//            this.setUp(gsyVideoModel.getUrl(), this.mCache, this.mCachePath, this.mObjects);
//            if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
//                this.mTitleTextView.setText(gsyVideoModel.getTitle());
//            }
//
//            this.startPlayLogic();
//        } else {
//            super.onAutoCompletion();
//        }
//    }


    @Override
    public void onPrepared() {
        super.onPrepared();
    }

    @Override
    public void onCompletion() {
        //if(this.mPlayPosition >= this.mUriList.size() - 1) {
            super.onCompletion();
        //}
    }

    @Override
    public void onAutoCompletion() {
        ++this.mCurrPlayPosition;
        if(mCurrPlayPosition < this.currentVideo.size()) {
            //播放当前视频的下一段
            setCurrentVideo(mCurrPlayPosition);
        } else {
            //播放下一个视频
            ++this.mPlayPosition;
            if(mUriList != null && mPlayPosition < this.mUriList.size()){
                setUp(null, mPlayPosition);
            } else {
                super.onAutoCompletion();
            }
        }
    }

    @Override
    public void setBufferProgress(int bufferProgress) {
        super.setBufferProgress(bufferProgress);
    }

    @Override
    public void onSeekComplete() {
        super.onSeekComplete();
    }

    @Override
    public void onVideoSizeChanged() {
        super.onVideoSizeChanged();
    }
}

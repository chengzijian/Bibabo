package com.bibabo.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibabo.R;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuyu on 2016/12/7.
 * 注意
 * 这个播放器的demo配置切换到全屏播放器
 * 这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
 */

public class DefaultVideoPlayer extends ListGSYVideoPlayer {

    private TextView mMoreScale;

    private TextView mSwitchSize;

//    private TextView mChangeRotate;

    private TextView mChangeTransform;

    private List<CustomVideoModel> mCustomVideoList = new ArrayList();

    //记住切换数据源类型
    private int mType = 0;

    private int mTransformSize = 0;

    //数据源
    private int mSourcePosition = 0;

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public DefaultVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public DefaultVideoPlayer(Context context) {
        super(context);
    }

    public DefaultVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //这个必须配置最上面的构造才能生效
    @Override
    public int getLayoutId() {
        if (mIfCurrentIsFullscreen) {
            return R.layout.sample_video_land;
        }
        return R.layout.sample_video_normal;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {
//        mChangeRotate = (TextView) findViewById(R.id.change_rotate);
        mChangeTransform = (TextView) findViewById(R.id.change_transform);
        if (mIfCurrentIsFullscreen) {
            mMoreScale = (TextView) findViewById(R.id.moreScale);
            mSwitchSize = (TextView) findViewById(R.id.switchSize);

            //切换清晰度
            mMoreScale.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mType == 0) {
                        mType = 1;
                        mMoreScale.setText("16:9");
                        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
                        if (mTextureView != null)
                            mTextureView.requestLayout();
                    } else if (mType == 1) {
                        mType = 2;
                        mMoreScale.setText("4:3");
                        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_4_3);
                        if (mTextureView != null)
                            mTextureView.requestLayout();
                    } else if (mType == 2) {
                        mType = 3;
                        mMoreScale.setText("全屏");
                        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
                        if (mTextureView != null)
                            mTextureView.requestLayout();
                    } else if (mType == 3) {
                        mType = 0;
                        mMoreScale.setText("默认比例");
                        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
                        if (mTextureView != null)
                            mTextureView.requestLayout();
                    }
                }
            });

            //切换视频清晰度
            mSwitchSize.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSwitchDialog();
                }
            });

//        //旋转播放角度
//        mChangeRotate.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((mTextureView.getRotation() - mRotate) == 270) {
//                    mTextureView.setRotation(mRotate);
//                    mTextureView.requestLayout();
//                    mCoverImageView.setRotation(mRotate);
//                    mCoverImageView.requestLayout();
//                } else {
//                    mTextureView.setRotation(mTextureView.getRotation() + 90);
//                    mTextureView.requestLayout();
//                    mCoverImageView.setRotation(mCoverImageView.getRotation() + 90);
//                    mCoverImageView.requestLayout();
//                }
//            }
//        });
//
            //镜像旋转
            mChangeTransform.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTransformSize == 0) {
                        mTransformSize = 1;
                    } else if (mTransformSize == 1) {
                        mTransformSize = 2;
                    } else if (mTransformSize == 2) {
                        mTransformSize = 0;
                    }
                    resolveTransform();
                }
            });
        } else {
            findViewById(R.id.previous).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    previous();
                }
            });
            findViewById(R.id.next).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    next();
                }
            });
        }

    }

    @Override
    protected void updateStartImage() {
        if (mIfCurrentIsFullscreen) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setImageResource(R.drawable.video_click_pause_selector);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            } else {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            }
        } else {
            super.updateStartImage();
        }
    }

    /**
     * 需要在尺寸发生变化的时候重新处理
     */
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        super.onSurfaceTextureSizeChanged(surface, width, height);
        resolveTransform();
    }

    /**
     * 处理镜像旋转
     */
    protected void resolveTransform() {

        switch (mTransformSize) {
            case 1: {
                Matrix transform = new Matrix();
                transform.setScale(-1, 1, mTextureView.getWidth() / 2, 0);
                mTextureView.setTransform(transform);
                mCoverImageView.setScaleType(ImageView.ScaleType.MATRIX);
                mCoverImageView.setImageMatrix(transform);
                mTransformCover = transform;
                mChangeTransform.setText("左右镜像");
            }
            break;
            case 2: {
                Matrix transform = new Matrix();
                transform.setScale(1, -1, 0, mTextureView.getHeight() / 2);
                mTextureView.setTransform(transform);
                mCoverImageView.setScaleType(ImageView.ScaleType.MATRIX);
                mCoverImageView.setImageMatrix(transform);
                mTransformCover = transform;
                mChangeTransform.setText("上下镜像");
            }
            break;
            case 0: {
                Matrix transform = new Matrix();
                transform.setScale(1, 1, mTextureView.getWidth() / 2, 0);
                mTextureView.setTransform(transform);
                mCoverImageView.setScaleType(ImageView.ScaleType.MATRIX);
                mCoverImageView.setImageMatrix(transform);
                mTransformCover = null;
                mChangeTransform.setText("旋转镜像");
            }
            break;
        }
    }

    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        DefaultVideoPlayer sampleVideo = (DefaultVideoPlayer) super.startWindowFullscreen(context, actionBar, statusBar);
        //这个播放器的demo配置切换到全屏播放器
        //这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
        //比如已旋转角度之类的等等
        //可参考super中的实现
        return sampleVideo;
    }

    /**
     * 弹出切换清晰度
     */
    private void showSwitchDialog() {
//        SwitchVideoTypeDialog switchVideoTypeDialog = new SwitchVideoTypeDialog(getContext());
//        switchVideoTypeDialog.initList(mUrlList, new SwitchVideoTypeDialog.OnListItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                final String name = mUrlList.get(position).getName();
//                if (mSourcePosition != position) {
//                    if ((mCurrentState == GSYVideoPlayer.CURRENT_STATE_PLAYING
//                            || mCurrentState == GSYVideoPlayer.CURRENT_STATE_PAUSE)
//                            && GSYVideoManager.instance().getMediaPlayer() != null) {
//                        final String url = mUrlList.get(position).getUrl();
//                        onVideoPause();
//                        final long currentPosition = mCurrentPosition;
//                        GSYVideoManager.instance().releaseMediaPlayer();
//                        cancelProgressTimer();
//                        hideAllWidget();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                setUp(url, mCache, mCachePath, mObjects);
//                                setSeekOnStart(currentPosition);
//                                startPlayLogic();
//                                cancelProgressTimer();
//                                hideAllWidget();
//                            }
//                        }, 500);
//                        mSwitchSize.setText(name);
//                        mSourcePosition = position;
//                    }
//                } else {
//                    Toast.makeText(getContext(), "已经是 " + name, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        switchVideoTypeDialog.show();
    }

    /**
     * 上一首
     */
    public void previous() {
        if (this.mPlayPosition < this.mUriList.size() && mPlayPosition > 0) {
            GSYVideoModel gsyVideoModel = this.mUriList.get(--mPlayPosition);
            this.setUp(gsyVideoModel.getUrl(), this.mCache, this.mCachePath, this.mObjects);
            if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                this.mTitleTextView.setText(gsyVideoModel.getTitle());
            }
            this.startPlayLogic();
        }
    }

    /**
     * 下一首
     */
    public void next() {
        if (this.mPlayPosition < this.mUriList.size() - 1 && mPlayPosition >= 0) {
            GSYVideoModel gsyVideoModel = this.mUriList.get(++mPlayPosition);
            this.setUp(gsyVideoModel.getUrl(), this.mCache, this.mCachePath, this.mObjects);
            if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                this.mTitleTextView.setText(gsyVideoModel.getTitle());
            }
            this.startPlayLogic();
        }
    }

    public static final String PLAY_VIDEO_URL = "%1$s%2$s?sdtfrom=v1010&guid=%3$s&vkey=%4$s#t=66";
    private String playPrefix;

    private GSYVideoModel getGSYVideoModelForCustomVideoModel(CustomVideoModel model) {
        playPrefix = model.getPlayPrefix();
        String keyid = model.getKeyid();
        String guid = ShowConfig.GUID;

        String vkey = model.getFvkey();
        if (StringUtils.isEmpty(vkey)) {
            LogUtils.e("", "vkey is null");
        }
        String videoUrl = String.format(PLAY_VIDEO_URL, playPrefix, keyid, guid, vkey);
        LogUtils.e("videoUrl:", videoUrl);
        return new GSYVideoModel(videoUrl, model.getTitle());
    }

    public boolean setUp(List<CustomVideoModel> url, int position) {
        mCustomVideoList = url;
        this.mPlayPosition = position;
        for (CustomVideoModel model : url) {
            mUriList.add(getGSYVideoModelForCustomVideoModel(model));
        }
        GSYVideoModel gsyVideoModel = getGSYVideoModelForCustomVideoModel(url.get(position));
        boolean set = this.setUp(gsyVideoModel.getUrl(), true, "");
        if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
            this.mTitleTextView.setText(gsyVideoModel.getTitle());
        }
        return set;
    }

    public void onAutoCompletion() {
        if (this.mPlayPosition < this.mCustomVideoList.size() - 1) {
            ++this.mPlayPosition;
            if (mFetchNextPackVkey != null) {
                mFetchNextPackVkey.fetchVkey(this.mCustomVideoList.get(this.mPlayPosition));
            }
        } else {
            super.onAutoCompletion();
        }
    }

    private FetchNextPackVkey mFetchNextPackVkey;

    public void setFetchNextPackVkey(FetchNextPackVkey mFetchNextPackVkey) {
        this.mFetchNextPackVkey = mFetchNextPackVkey;
    }

    public interface FetchNextPackVkey {
        void fetchVkey(CustomVideoModel model);
    }

    public void playNextPackVideo(GSYVideoModel gsyVideoModel) {
        String url = gsyVideoModel.getUrl();
        if (!StringUtils.isEmpty(playPrefix)) {
            url = playPrefix + url;
        }
        this.setUp(url, this.mCache, this.mCachePath, this.mObjects);
        if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
            this.mTitleTextView.setText(gsyVideoModel.getTitle());
        }
        this.startPlayLogic();
    }
}

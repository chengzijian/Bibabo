package com.bibabo.fragment.watch.player;

import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.mvp.BasePresenter;
import com.bibabo.entity.PlayVideoData;
import com.bibabo.entity.VideoData;

import java.util.List;
import java.util.Map;

/**
 * Created by zijian.cheng on 2017/6/6.
 */

public class BabyVideoDetailContract {

    interface View extends ListBaseView {

        void playVideo(PlayVideoData result);

        void fetchVideoInfoSuccess(Map<Integer, Object> result);

        void fetchVideoUrlSuccess(VideoData result);
    }

    interface Presenter extends BasePresenter<View> {

        void fetchVideoList(String mHtmlUrl);

        void fetchVideoPlayInfo(String getInfoUrl);

        void fetchNextInfo(String getKeyUrl);
    }
}

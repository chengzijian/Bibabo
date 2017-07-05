package com.bibabo.activity.playvideo;

import com.bibabo.base.mvp.BasePresenter;
import com.bibabo.base.mvp.BaseView;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.MovieCoverInfo;

import java.util.List;

/**
 * Created by zijian.cheng on 2017/7/5.
 */

public class PlayMovieContract {

    interface View extends BaseView {

        void fetchCoverInfoSuccess(MovieCoverInfo result);

        void playVideo(List<CustomVideoModel> result);

        void playNextPackVideo(String result);
    }

    interface Presenter extends BasePresenter<PlayMovieContract.View> {
        void analyUrl(String string);

        void fetchMP4PlayUrl(String getInfoUrl);

        void fetchNextInfo(String url);
    }
}

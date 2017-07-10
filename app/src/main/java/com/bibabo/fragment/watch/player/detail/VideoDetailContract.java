package com.bibabo.fragment.watch.player.detail;

import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.mvp.BasePresenter;
import com.bibabo.entity.QQListInfoResult.DataBean;
import com.bibabo.entity.VideoDetailsInfo;

import java.util.List;

/**
 * Created by zijian.cheng on 2017/7/10.
 */

public class VideoDetailContract {

    interface View extends ListBaseView {
        void fetchVideoInfoSuccess(VideoDetailsInfo result);
    }

    interface Presenter extends BasePresenter<VideoDetailContract.View> {

        void fetchVideoList(String vid);

    }
}

package com.bibabo.fragment.watch.player;

import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.mvp.BasePresenter;

/**
 * Created by zijian.cheng on 2017/6/6.
 */

public class BabyVideoDetailContract {

    interface View extends ListBaseView {

        void playVideo(String url);
    }

    interface Presenter extends BasePresenter<View> {

        void fetchVideoUrl(String mHtmlUrl);
    }
}

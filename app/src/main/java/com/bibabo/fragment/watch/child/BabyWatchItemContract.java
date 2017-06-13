package com.bibabo.fragment.watch.child;

import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.mvp.BasePresenter;

/**
 * Created by zijian.cheng on 2017/6/13.
 */

public class BabyWatchItemContract {
    public interface View extends ListBaseView {
    }

    public interface Presenter extends BasePresenter<View> {
    }
}

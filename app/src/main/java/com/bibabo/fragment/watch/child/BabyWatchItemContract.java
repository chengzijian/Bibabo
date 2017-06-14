package com.bibabo.fragment.watch.child;

import com.bibabo.base.list.ListBaseView;
import com.bibabo.base.mvp.BasePresenter;
import com.bibabo.entity.MainListDto;

import java.util.List;

/**
 * Created by zijian.cheng on 2017/6/13.
 */

public class BabyWatchItemContract {
    public interface View extends ListBaseView {

        void updateCurrentList(List<MainListDto> list);
    }

    public interface Presenter extends BasePresenter<View> {
        //获取网页信息
        void fetchList(String httpUrl);
    }
}

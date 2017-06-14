package com.bibabo.fragment.watch.child;

import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.DataListResult;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.MainListResult;
import com.bibabo.httpdata.HttpData;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 *
 * Created by zijian.cheng on 2017/6/13.
 */
public class BabyWatchItemPresenter extends ListBasePresenterImpl<BabyWatchItemContract.View, MainListDto>
        implements BabyWatchItemContract.Presenter {

    @Override
    public void fetchList(String httpUrl) {
        HttpData.getDefault().getMainList(httpUrl)
                .compose(view.<MainListResult>bindToLife())
                .compose(applyCommonOperators())
                .subscribe(new Consumer<DataListResult<MainListDto>>() {
                    @Override
                    public void accept(@NonNull DataListResult<MainListDto> result) throws Exception {
                        view.updateCurrentList(result.getDataList());
                    }
                });
    }
}

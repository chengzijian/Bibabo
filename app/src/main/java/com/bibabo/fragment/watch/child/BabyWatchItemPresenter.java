package com.bibabo.fragment.watch.child;

import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.MainListDto;
import com.bibabo.httpdata.HttpData;

import java.util.List;

import io.reactivex.Flowable;
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
        Flowable<List<MainListDto>> flowable = HttpData.getInstance().getMainList(httpUrl);
        flowable.compose(view.<List<MainListDto>>bindToLife())
                .doOnNext(new Consumer<List<MainListDto>>() {
                    @Override
                    public void accept(@NonNull List<MainListDto> dataResult) throws Exception {
                        //需要分页操作时参考 ApplyFamilyPresenter
                        //dataResult.setPage(page);
                    }
                })
                .compose(applyCommonOperators())
                .subscribe(new Consumer<List<MainListDto>>() {
                    @Override
                    public void accept(@NonNull List<MainListDto> result) throws Exception {
                        view.updateCurrentList(result);
                    }
                });
    }
}

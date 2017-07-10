package com.bibabo.fragment.watch.child;

import com.bibabo.api.DefaultRetrofit;
import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.QVMovieInfo;
import com.bibabo.resolver.QVMovieListConvert;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 *
 * Created by zijian.cheng on 2017/6/13.
 */
public class BabyWatchItemPresenter extends ListBasePresenterImpl<BabyWatchItemContract.View, QVMovieInfo>
        implements BabyWatchItemContract.Presenter {

    /**
     * 获取tx某个类别下的视频列表，支持分页
     * @param itype
     * @param offset
     */
    @Override
    public void fetchQVChildrenVideoList(String itype, String offset) {
        DefaultRetrofit.api().fetchQVChildrenVideoList(itype, offset)
                .flatMap(new QVMovieListConvert<String, List<QVMovieInfo>>())
                .compose(view.<List<QVMovieInfo>>bindToLife())
                .doOnNext(new Consumer<List<QVMovieInfo>>() {
                    @Override
                    public void accept(@NonNull List<QVMovieInfo> models) throws Exception {
                        if(models.size() > 0){
                            view.setEnd(!models.get(0).hasNextPage());
                            models.remove(0);
                        }
                    }
                })
                .compose(applyCommonOperators())
                .subscribe(new Consumer<List<QVMovieInfo>>() {
                    @Override
                    public void accept(@NonNull List<QVMovieInfo> result) throws Exception {
                        view.updateCurrentList(result);
                    }
                });
    }
}

package com.bibabo.fragment.watch.child;

import com.bibabo.api.DefaultRetrofit;
import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.DataListResult;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.MainListResult;
import com.bibabo.entity.QVMovieInfo;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.httpdata.HttpData;
import com.bibabo.resolver.QVMovieListConvert;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
                /*.filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object city) throws Exception {
                        String id = city.getId();
                        if(Integer.parseInt(id)<5){
                            return true;
                        }
                        return false;
                    }
                })*/
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

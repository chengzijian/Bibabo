package com.bibabo.base.list;

import com.bibabo.base.mvp.BasePresenterImpl;
import com.bibabo.entity.DataListResult;
import com.bibabo.framework.utils.LogUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 */
public class ListBasePresenterImpl<V extends ListBaseView, MODEL> extends BasePresenterImpl<V> {

    protected FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>> applyCommonOperators() {
        return commonOperatorsTransformer;
    }

    private final FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>> commonOperatorsTransformer = new FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>>() {
        @Override
        public Publisher<DataListResult<MODEL>> apply(Flowable<DataListResult<MODEL>> upstream) {
            return upstream
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(@NonNull Subscription subscription) throws Exception {
                            view.notifyLoadingStarted();
                        }
                    })
                    .doOnNext(new Consumer<DataListResult<MODEL>>() {
                        @Override
                        public void accept(@NonNull DataListResult<MODEL> models) throws Exception {
                            view.setEnd(models.isAllDataLoaded());
                            if(!models.isAllDataLoaded()){
                                view.setCurrPage(models.getCurrPage());
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())//子线程访问网络
                    .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            view.error();
                        }
                    })
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
                            view.setRefresh(false);
                            view.notifyLoadingFinished();
                        }
                    });
        }
    };
}

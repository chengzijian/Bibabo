package com.bibabo.base.list;

import com.bibabo.base.mvp.BasePresenterImpl;
import com.bibabo.framework.utils.LogUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

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

    protected FlowableTransformer<List<MODEL>, List<MODEL>> applyCommonOperators() {
        return commonOperatorsTransformer;
    }

    private final FlowableTransformer<List<MODEL>, List<MODEL>> commonOperatorsTransformer = new FlowableTransformer<List<MODEL>, List<MODEL>>() {
        @Override
        public Publisher<List<MODEL>> apply(Flowable<List<MODEL>> upstream) {
            return upstream
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(@NonNull Subscription subscription) throws Exception {
                            view.notifyLoadingStarted();
                        }
                    })
                    .doOnNext(new Consumer<List<MODEL>>() {
                        @Override
                        public void accept(@NonNull List<MODEL> models) throws Exception {
//                            view.setEnd(models.getDataList().size() == 0
//                                    || models.getPage() >= models.getAllPage());
                            LogUtils.e("","");
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

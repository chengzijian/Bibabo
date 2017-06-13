/*
 * Copyright (c) 2017 SamuelGjk <samuel.alva@outlook.com>
 *
 * This file is part of DiyCode
 *
 * DiyCode is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DiyCode is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DiyCode. If not, see <http://www.gnu.org/licenses/>.
 */

package com.bibabo.base.list;


import com.bibabo.base.mvp.BasePresenterImpl;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by SamuelGjk on 2017/4/23.
 */

public class ListBasePresenterImpl<V extends ListBaseView, MODEL> extends BasePresenterImpl<V> {

//    int LIMIT = 20;
//    protected FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>> applyCommonOperators() {
//        return commonOperatorsTransformer;
//    }
//
//    private final FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>> commonOperatorsTransformer = new FlowableTransformer<DataListResult<MODEL>, DataListResult<MODEL>>() {
//        @Override
//        public Publisher<DataListResult<MODEL>> apply(Flowable<DataListResult<MODEL>> upstream) {
//            return upstream
//                    .doOnSubscribe(new Consumer<Subscription>() {
//                        @Override
//                        public void accept(@NonNull Subscription subscription) throws Exception {
//                            view.notifyLoadingStarted();
//                        }
//                    })
//                    .doOnNext(new Consumer<DataListResult<MODEL>>() {
//                        @Override
//                        public void accept(@NonNull DataListResult<MODEL> models) throws Exception {
//                            view.setEnd(models.getDataList().size() == 0
//                                    || models.getPage() >= models.getAllPage());
//                        }
//                    })
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError(new Consumer<Throwable>() {
//                        @Override
//                        public void accept(@NonNull Throwable throwable) throws Exception {
//                            view.error();
//                        }
//                    })
//                    .doFinally(new Action() {
//                        @Override
//                        public void run() throws Exception {
//                            view.setRefresh(false);
//                            view.notifyLoadingFinished();
//                        }
//                    });
//        }
//    };
}

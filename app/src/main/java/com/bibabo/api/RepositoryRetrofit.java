package com.bibabo.api;

import com.bibabo.entity.HttpResult;
import com.bibabo.framework.BaseApplication;
import com.bibabo.resolver.QVVideoDetailsResolver;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * 带缓存的请求
 * Created by zijian.cheng on 2017/7/6.
 */

public class RepositoryRetrofit {

    private final CacheProviders providers;
    static volatile RepositoryRetrofit defaultInstance;

    private RepositoryRetrofit() {
        providers = new RxCache.Builder()
                .persistence(BaseApplication.getApplication().getCacheDir(), new GsonSpeaker())
                .using(CacheProviders.class);
    }

    public static RepositoryRetrofit api() {
        if (defaultInstance == null) {
            synchronized (RepositoryRetrofit.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RepositoryRetrofit();
                }
            }
        }
        return defaultInstance;
    }

    public <T> Flowable<T> fetchVideoPlayList(String vid) {
        return providers.getCacheData(DefaultRetrofit.api().fetchVideoPlayList(vid)
                        .flatMap(new QVVideoDetailsResolver<String, T>())
                , new DynamicKey(vid), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<T>());
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

        @Override
        public T apply(@NonNull HttpResult<T> httpResult) throws Exception {
            if (httpResult.getCode() != 1) {
                throw new ApiException(httpResult);
            }
            return httpResult.getData();
        }
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpResultFuncCcche<T> implements Function<Reply<T>, T> {
        @Override
        public T apply(@NonNull Reply<T> httpResult) throws Exception {
            return httpResult.getData();
        }
    }
}

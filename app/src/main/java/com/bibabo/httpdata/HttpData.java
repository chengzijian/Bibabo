package com.bibabo.httpdata;

import com.bibabo.api.ApiException;
import com.bibabo.api.CacheProviders;
import com.bibabo.entity.HttpResult;
import com.bibabo.framework.BaseApplication;
import com.bibabo.resolver.MainInfoHtmlOnSubscribe;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 *
 * Created by zijian.cheng on 2017/6/14.
 */
public class HttpData {
    //    protected static final APIService service = RetrofitUtils.api();
    private final CacheProviders providers;
    static volatile HttpData defaultInstance;

    public static HttpData getDefault() {
        if (defaultInstance == null) {
            synchronized (HttpData.class) {
                if (defaultInstance == null) {
                    defaultInstance = new HttpData();
                }
            }
        }
        return defaultInstance;
    }

    private HttpData() {
        providers = new RxCache.Builder()
                .persistence(BaseApplication.getApplication().getCacheDir(), new GsonSpeaker())
                .using(CacheProviders.class);
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
//
//    /**
//     * 插入观察者
//     *
//     * @param observable
//     * @param observer
//     * @param <T>
//     */
//    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
//        observable.subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
//                .subscribe(observer);
//    }
//
    public Flowable getMainList(String path) {
        Flowable observable = Flowable.create(new MainInfoHtmlOnSubscribe(path), BackpressureStrategy.BUFFER);
        return providers.getCacheData(observable, new DynamicKey(path), new EvictDynamicKey(false)).map(new HttpResultFuncCcche());
    }

//    public void getMainList(String path, Observer<List<MainListDto>> observer) {
//        Observable observable = service.fetchList(path).map(new HttpResultFunc<List<MainListDto>>());
//        Observable observableCahce = providers.fetchList(observable, new DynamicKey("书本类别"), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<MainListDto>>());
//        setSubscribe(observableCahce, observer);
//    }
//
//    public void getMainList2(String path, Observer<List<MainListDto>> observer) {
//        Observable observable = Observable.create(new MainInfoHtmlOnSubscribe(path));
//        Observable observableCahce = providers.fetchList(observable, new DynamicKey("书本类别"), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<MainListDto>>());
//        setSubscribe(observableCahce, observer);
//    }

//    //获取app首页配置信息  banner  最新 最热
//    public void getHomeInfo(boolean isload,Observer<HomeDto> observer){
//        Observable observable=service.getHomeInfo().map(new HttpResultFunc<HomeDto>());;
//        Observable observableCache=providers.getHomeInfo(observable,new DynamicKey("首页配置"),new EvictDynamicKey(isload)).map(new HttpResultFuncCcche<HomeDto>());
//        setSubscribe(observableCache,observer);
//    }
//    //获得搜索热门标签
//    public void getSearchLable(Observer<List<String>> observer){
//        Observable observable=service.getHotLable().map(new HttpResultFunc<List<String>>());;
//        Observable observableCache=providers.getHotLable(observable,new DynamicKey("搜索热门标签"), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<String>>());
//        setSubscribe(observableCache,observer);
//    }
//    //根据类型获取书籍集合
//    public void getBookList(int bookType, int pageIndex, Observer<List<BookInfoListDto>> observer) {
//        Observable observable = service.getBookList(bookType,pageIndex).map(new HttpResultFunc<List<BookInfoListDto>>());
//        Observable observableCache=providers.getBookList(observable,new DynamicKey("getStackTypeHtml"+bookType+pageIndex), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<BookInfoListDto>>());
//        setSubscribe(observableCache, observer);
//    }
//    //根据关键字搜索书籍
//    public void getSearchList(String key,Observer<List<BookInfoListDto>> observer){
//        try {
//            //中文记得转码  不然会乱码  搜索不出想要的效果
//            key = URLEncoder.encode(key, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        Observable observable=service.getSearchList(key).map(new HttpResultFunc<List<BookInfoListDto>>());
//        Observable observableCache=providers.getSearchList(observable,new DynamicKey("getSearchList&"+key), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<BookInfoListDto>>());
//        setSubscribe(observableCache, observer);
//    }
//    //获取书籍详情
//    public void getBookInfo(int id, Observer<BookInfoDto> observer){
//        Observable observable=service.getBookInfo(id).map(new HttpResultFunc<BookInfoDto>());
//        Observable observableCache=providers.getBookInfo(observable,new DynamicKey("getBookInfo&"+id), new EvictDynamicKey(false)).map(new HttpResultFuncCcche<BookInfoDto>());
//        setSubscribe(observableCache, observer);
//    }

}

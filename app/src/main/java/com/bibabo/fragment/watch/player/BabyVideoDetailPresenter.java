package com.bibabo.fragment.watch.player;

import com.bibabo.api.DefaultRetrofit;
import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.PlayVideoData;
import com.bibabo.entity.QQVideoInfo;
import com.bibabo.httpdata.HttpData;
import com.bibabo.resolver.QVMovieDetailsConvert;
import com.bibabo.resolver.QVMovieNextPlayUrlConvert;
import com.bibabo.resolver.QVMoviePlayUrlConvert;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by zijian.cheng on 2017/6/6.
 */

public class BabyVideoDetailPresenter extends ListBasePresenterImpl<BabyVideoDetailContract.View, QQListInfoResult>
        implements BabyVideoDetailContract.Presenter {

    /**
     * 获取视频播放连接
     */
//    public void fetchVideoUrl(String mHtmlUrl) {
//        HttpData.getDefault().fetchVideoUrl(mHtmlUrl)
//                .compose(view.<PlayVideoData>bindToLife())
//                .subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
//                .doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        view.error();
//                    }
//                })
//                .subscribe(new Consumer<PlayVideoData>() {
//                    @Override
//                    public void accept(@NonNull PlayVideoData result) throws Exception {
//                        view.playVideo(result);
//                    }
//                });
//    }

    /**
     * 获取视频播放列表
     * @param vid
     */
    @Override
    public void fetchVideoList(String vid) {
        DefaultRetrofit.api().fetchVideoPlayList(vid)
                .flatMap(new QVMovieDetailsConvert<String, Map<Integer, Object>>())
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
                .compose(view.<Map<Integer, Object>>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
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
                })
                .subscribe(new Consumer<Map<Integer, Object>>() {
                    @Override
                    public void accept(@NonNull Map<Integer, Object> result) throws Exception {
                        view.fetchVideoInfoSuccess(result);
                    }
                });
    }

    /**
     * 获取播放视频的信息
     * @param mHtmlUrl
     */
    @Override
    public void fetchVideoPlayInfo(String mHtmlUrl) {
        DefaultRetrofit.api().fetchVideoInfo(mHtmlUrl)
                .flatMap(new QVMoviePlayUrlConvert<String, List<CustomVideoModel>>())
                .compose(view.<List<CustomVideoModel>>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
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
                })
                .subscribe(new Consumer<List<CustomVideoModel>>() {
                    @Override
                    public void accept(@NonNull List<CustomVideoModel> result) throws Exception {
                        view.playVideo(result);
                    }
                });
    }

    @Override
    public void fetchNextInfo(String mHtmlUrl) {
        DefaultRetrofit.api().fetchNextInfo(mHtmlUrl)
                .flatMap(new QVMovieNextPlayUrlConvert<String, String>())
                .compose(view.<String>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
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
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String result) throws Exception {
                        view.playNextPackVideo(result);
                    }
                });
    }

}

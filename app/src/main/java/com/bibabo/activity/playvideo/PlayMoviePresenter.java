package com.bibabo.activity.playvideo;

import com.bibabo.api.DefaultRetrofit;
import com.bibabo.base.mvp.BasePresenterImpl;
import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.MovieCoverInfo;
import com.bibabo.resolver.QVMovieDetailsConvert;
import com.bibabo.resolver.QVMovieNextPlayUrlConvert;
import com.bibabo.resolver.QVMoviePlayUrlConvert;
import com.bibabo.resolver.VipVideoHtmlResolver;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 *
 * Created by zijian.cheng on 2017/7/5.
 */

public class PlayMoviePresenter extends BasePresenterImpl<PlayMovieContract.View>
        implements PlayMovieContract.Presenter {

    /**
     * 获取网页的视频播放信息
     * @param url
     */
    @Override
    public void analyUrl(String url) {
        DefaultRetrofit.api().fetchFullUrlInfo(url)
                .flatMap(new VipVideoHtmlResolver<String, MovieCoverInfo>())
                .compose(view.<MovieCoverInfo>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        //view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        //view.error();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //view.setRefresh(false);
                        //view.notifyLoadingFinished();
                    }
                })
                .subscribe(new Consumer<MovieCoverInfo>() {
                    @Override
                    public void accept(@NonNull MovieCoverInfo result) throws Exception {
                        view.fetchCoverInfoSuccess(result);
                    }
                });
    }

    /**
     * 获取播放地址
     * @param infoUrl
     */
    @Override
    public void fetchMP4PlayUrl(String infoUrl) {
        DefaultRetrofit.api().fetchFullUrlInfo(infoUrl)
                .flatMap(new QVMoviePlayUrlConvert<String, List<CustomVideoModel>>())
                .compose(view.<List<CustomVideoModel>>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        //view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        //view.error();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //view.setRefresh(false);
                        //view.notifyLoadingFinished();
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
        DefaultRetrofit.api().fetchFullUrlInfo(mHtmlUrl)
                .flatMap(new QVMovieNextPlayUrlConvert<String, String>())
                .compose(view.<String>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        //view.notifyLoadingStarted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        //view.error();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //view.setRefresh(false);
                        //view.notifyLoadingFinished();
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

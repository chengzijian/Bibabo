package com.bibabo.fragment.watch.player;

import com.bibabo.api.DefaultRetrofit;
import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.QVMovieInfo;
import com.bibabo.entity.VideoData;
import com.bibabo.entity.PlayVideoData;
import com.bibabo.httpdata.HttpData;
import com.bibabo.resolver.QVMovieDetailsConvert;
import com.bibabo.resolver.QVMovieListConvert;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by zijian.cheng on 2017/6/6.
 */

public class BabyVideoDetailPresenter extends ListBasePresenterImpl<BabyVideoDetailContract.View, PlayVideoData>
        implements BabyVideoDetailContract.Presenter {

    /**
     * 获取视频播放连接
     * @param mHtmlUrl
     */
    @Override
    public void fetchVideoUrl(String mHtmlUrl) {
        HttpData.getDefault().fetchVideoUrl(mHtmlUrl)
                .compose(view.<PlayVideoData>bindToLife())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.error();
                    }
                })
                .subscribe(new Consumer<PlayVideoData>() {
                    @Override
                    public void accept(@NonNull PlayVideoData result) throws Exception {
                        view.playVideo(result);
                    }
                });
    }

    @Override
    public void fetchQQVideoUrl(String vid) {
        DefaultRetrofit.api().fetchQQVideoUrl(vid)
                .flatMap(new QVMovieDetailsConvert<String, List<PlayVideoData>>())
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
                .compose(view.<List<PlayVideoData>>bindToLife())
                .compose(applyCommonOperators())
                .subscribe(new Consumer<List<PlayVideoData>>() {
                    @Override
                    public void accept(@NonNull List<PlayVideoData> result) throws Exception {
                        view.fetchVideoUrlSuccess(result);
                    }
                });

//        HttpData.getDefault().fetchQQVideoUrl(vid)
//                .compose(view.<VideoData>bindToLife())
//                .subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
//                .doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        view.error();
//                    }
//                })
//                .subscribe(new Consumer<VideoData>() {
//                    @Override
//                    public void accept(@NonNull VideoData result) throws Exception {
//                        view.fetchVideoUrlSuccess(result);
//                    }
//                });
    }

    @Override
    public void fetchVideoInfo(String mHtmlUrl) {
        HttpData.getDefault().fetchVideoInfo(mHtmlUrl)
                .compose(view.<PlayVideoData>bindToLife())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.error();
                    }
                })
                .subscribe(new Consumer<PlayVideoData>() {
                    @Override
                    public void accept(@NonNull PlayVideoData result) throws Exception {
                        view.playVideo(result);
                    }
                });
    }

    @Override
    public void fetchNextInfo(String getKeyUrl) {

    }

}

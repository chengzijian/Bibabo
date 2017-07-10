package com.bibabo.fragment.watch.player.detail;

import com.bibabo.api.RepositoryRetrofit;
import com.bibabo.base.list.ListBasePresenterImpl;
import com.bibabo.entity.QQListInfoResult.DataBean;
import com.bibabo.entity.VideoDetailsInfo;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by zijian.cheng on 2017/7/10.
 */

public class VideoDetailPresenter extends ListBasePresenterImpl<VideoDetailContract.View, DataBean> implements
        VideoDetailContract.Presenter {

    /**
     * 获取视频播放列表
     * @param vid
     */
    @Override
    public void fetchVideoList(String vid) {
        RepositoryRetrofit.api().<VideoDetailsInfo>fetchVideoPlayList(vid)
                .compose(view.<VideoDetailsInfo>bindToLife())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        view.notifyLoadingStarted();
                    }
                })
                .doOnNext(new Consumer<VideoDetailsInfo>() {
                    @Override
                    public void accept(@NonNull VideoDetailsInfo models) throws Exception {
                        view.setEnd(true);
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
                .subscribe(new Consumer<VideoDetailsInfo>() {
                    @Override
                    public void accept(@NonNull VideoDetailsInfo result) throws Exception {
                        view.fetchVideoInfoSuccess(result);
                    }
                });
    }

}

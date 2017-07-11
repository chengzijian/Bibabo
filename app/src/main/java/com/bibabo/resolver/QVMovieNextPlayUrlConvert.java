package com.bibabo.resolver;

import com.bibabo.entity.QQNextPackVideoInfo;
import com.bibabo.framework.config.ShowConfig;
import com.bibabo.framework.utils.JSONUtils;
import com.bibabo.framework.utils.LogUtils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拿到下一段视频播放地址
 *
 * @param <T>
 */
public class QVMovieNextPlayUrlConvert<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        String htmlString = String.valueOf(source);
        htmlString = htmlString.replace("QZOutputJson=", "").replace(";", "");
        QQNextPackVideoInfo treasure = JSONUtils.fromJsonString(htmlString, QQNextPackVideoInfo.class);
        String nextFileName = treasure.getKeyid().replaceAll("\\..*?10", ".p") + ".mp4";
        String videoUrl = String.format(ShowConfig.PLAY_VIDEO_URL, nextFileName, ShowConfig.GUID, treasure.getKey());
        LogUtils.e("nextVideoUrl:", videoUrl);
        return Flowable.just((F) videoUrl);
    }
}
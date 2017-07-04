package com.bibabo.resolver;

import com.bibabo.entity.CustomVideoModel;
import com.bibabo.entity.QQVideoInfo;
import com.bibabo.framework.utils.JSONUtils;
import com.bibabo.framework.utils.LogUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拿到视频播放地址
 *
 * @param <T>
 */
public class QVMoviePlayUrlConvert<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        String htmlString = String.valueOf(source);
        htmlString = htmlString.replace("QZOutputJson=", "").replace(";", "");
        LogUtils.e("playUrls:", htmlString);
        QQVideoInfo treasure = JSONUtils.fromJsonString(htmlString, QQVideoInfo.class);

        List<CustomVideoModel> result = new ArrayList<>();
        QQVideoInfo.VlBean.ViBean viBean = treasure.getVl().getVi().get(0);
        List<QQVideoInfo.VlBean.ViBean.ClBean.CiBean> ciBeen = viBean.getCl().getCi();
        String playPrefix = viBean.getUl().getUi().get(0).getUrl();
        String td = viBean.getTd();
        String title = viBean.getTi();
        String vid = viBean.getLnk();

        if (ciBeen != null && ciBeen.size() > 0) {
            for (int i = 0; i < ciBeen.size(); i++) {
                CustomVideoModel model;
                if(i == 0){
                    model = new CustomVideoModel(vid, playPrefix, title, viBean.getFvkey(), ciBeen.get(i).getKeyid(), ciBeen.get(i).getCd(), td);
                } else {
                    model = new CustomVideoModel(vid, playPrefix, title, null, ciBeen.get(i).getKeyid(), ciBeen.get(i).getCd(), td);
                }
                result.add(model);
            }
        }

        return Flowable.just((F) result);
    }
}
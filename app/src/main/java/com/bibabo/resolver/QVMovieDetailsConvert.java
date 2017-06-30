package com.bibabo.resolver;

import com.bibabo.entity.PlayVideoData;
import com.bibabo.entity.QVMovieInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拿到视频列表
 * https://v.qq.com/x/list/children?itype=2&offset=0
 * @param <T>
 */
public class QVMovieDetailsConvert<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        List<PlayVideoData> items = new ArrayList<>();
//        Document doc = Jsoup.parse(String.valueOf(source));
//        Elements list = doc.select("ul.figures_list").first().getElementsByTag("li");
//        int size = list.size();
//        if (size > 0) {
//            for (int i = 0; i < size; i++) {
//                Element element = list.get(i);
//                Element aTag = element.select("a").first();
//                Element iTag = aTag.select("img").first();
//                QVMovieInfo info = new QVMovieInfo();
//                info.setVid(aTag.attr("data-float"));
//                info.setVideoHtmlUrl(aTag.attr("href"));
//                info.setVpic(iTag.attr("r-lazyload").replace("//", "http://"));
//                info.setVtitle(iTag.attr("alt"));
//                info.setFigureInfo(aTag.select("span.figure_info").text());
//                info.setFigureDesc(element.select("div.figure_desc").text());
//                info.setPlayNum(element.select("span.num").text());
//                items.add(info);
//            }
//        }

        return Flowable.just((F)items);
    }
}
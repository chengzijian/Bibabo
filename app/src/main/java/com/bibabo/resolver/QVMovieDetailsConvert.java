package com.bibabo.resolver;

import com.bibabo.entity.PlayVideoData;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.SummaryInfo;
import com.bibabo.framework.utils.JSONUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拿到视频列表
 * https://v.qq.com/x/list/children?itype=2&offset=0
 *
 * @param <T>
 */
public class QVMovieDetailsConvert<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        Map<Integer, Object> result = new HashMap<>();
        String htmlString = String.valueOf(source);
        Document doc = Jsoup.parse(htmlString);

        Elements pages = doc.select("div.mod_episode_filter").first().getElementsByTag("a");
        int pageSize = pages.size();
        if (pageSize > 0) {
            //这里获取列表的分页信息 如 1-120；121-140
            List<String> pagesSelect = new ArrayList<>(pageSize);
            for (int i = 0; i < pageSize; i++) {
                pagesSelect.add(pages.get(i).text());
            }
            result.put(0, pagesSelect);
        } else {
            result.put(0, null);
        }

        /**
         * 获取简介
         */
        String title = doc.select("h1.album_title").first().getElementsByTag("a").text();
        String intro = doc.select("div.album_intro").first().text();
        String img = doc.select("a.album_pic").first().getElementsByTag("img").attr("r-lazyload").replace("//", "http://");

        SummaryInfo summaryInfo = new SummaryInfo();
        summaryInfo.setTitle(title);
        summaryInfo.setIntro(intro);
        summaryInfo.setImage(img);
        result.put(1, summaryInfo);

        /**
         * 获取播放列表
         */
        String begin = "LIST_INFO = {";
        String end = "]}}}";
        String listInfo = htmlString.substring(htmlString.indexOf(begin) + begin.length() - 1, htmlString.indexOf(end) + end.length());
        String str = replaceAllShow(listInfo, "[^\\]\\{]*(\":\\{\"v)", "\\}\\}, \\{\"videoItem\":\\{\"v");// 将数字替换成　#
        str = str.replace("{}},", "[").replace("}}}", "}}]}");

        QQListInfoResult qqListInfos = JSONUtils.fromJsonString(str, QQListInfoResult.class);
        result.put(2, qqListInfos);

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

        return Flowable.just((F) result);
    }

    /**
     * 正则表达式的替换
     */
    public String replaceAllShow(String str, String regex, String newstr) {
        str = str.replaceAll(regex, newstr);
        return str;
    }
}
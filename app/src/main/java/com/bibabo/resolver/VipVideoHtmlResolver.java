package com.bibabo.resolver;

import com.bibabo.entity.MovieCoverInfo;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.SummaryInfo;
import com.bibabo.framework.utils.JSONUtils;
import com.bibabo.framework.utils.LogUtils;

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
 * vip电影内容解析
 * https://v.qq.com/x/cover/2xxul4n2j8y0rxi/a0024c3w6lf.html?new=1
 *
 * @param <T>
 */
public class VipVideoHtmlResolver<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        String htmlString = String.valueOf(source);
        String beginStr = "var COVER_INFO = ";
        String endStr = "COLUMN_INFO";
        htmlString = htmlString.substring(htmlString.indexOf(beginStr) + beginStr.length(), htmlString.indexOf(endStr) - 4);
        MovieCoverInfo result = JSONUtils.fromJsonString(htmlString, MovieCoverInfo.class);
        return Flowable.just((F) result);
    }

}
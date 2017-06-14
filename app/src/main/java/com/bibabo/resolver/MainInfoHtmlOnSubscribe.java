package com.bibabo.resolver;

import com.bibabo.api.ApiException;
import com.bibabo.api.Constant;
import com.bibabo.entity.MainListDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class MainInfoHtmlOnSubscribe<T> implements FlowableOnSubscribe<T> {
    private String url;

    public MainInfoHtmlOnSubscribe(String url) {
        //获取到需要解析html地址
        this.url = Constant.BASE_URL + url;
    }

    @Override
    public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
        try {
            //开始疯狂的数据抓取啦 这个我就不解释了  大家去看看文档  http://www.open-open.com/jsoup/
            Document doc = Jsoup.connect(url).timeout(15000).get();
            Elements list = doc.select("div.HotMainbox").first().getElementsByTag("li");
            List<MainListDto> dataList = null;
            int size = list.size();
            if (size > 0) {
                dataList = new ArrayList<>(size);
                Element element;
                for (int i = 0; i < size; i++) {
                    element = list.get(i).select("a").first();
                    dataList.add(new MainListDto(
                            element.select("img").attr("title"),
                            element.select("img").attr("src"),
                            element.attr("href"),
                            list.get(i).select("div.clicknumber").text().replace("播放：","")));
                }
            }
            T bookInfoDto = (T) dataList;
            subscriber.onNext(bookInfoDto);
            subscriber.onComplete();
        } catch (IOException e) {
            throw new ApiException("ERROR:数据解析错误");
        }
    }
}
package com.bibabo.resolver;

import com.bibabo.api.ApiException;
import com.bibabo.api.Constant;
import com.bibabo.entity.MainListDto;
import com.bibabo.entity.MainListResult;
import com.bibabo.framework.utils.StringUtils;

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
            //开始疯狂的数据抓取啦 不解释了 看文档  http://www.open-open.com/jsoup/
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
            MainListResult result = new MainListResult();
            result.setDataList(dataList);
            result.setSize(dataList.size());

            //设置page
            Element page = doc.select("div.YBNavTabPage").first();
            if(page != null){
                String spans = page.select("span").get(1).text();
                if(!StringUtils.isEmpty(spans) && spans.contains("/")){
                    String[] strs = spans.split("/");
                    if(strs.length == 2){
                        try{
                            result.setCurrPage(Integer.valueOf(strs[0]));
                            result.setAllPage(Integer.valueOf(strs[1]));
                        } catch (Exception e){}
                    }
                }
            }

            T bookInfoDto = (T) result;
            subscriber.onNext(bookInfoDto);
            subscriber.onComplete();
        } catch (IOException e) {
            throw new ApiException("ERROR:数据解析错误");
        }
    }
}
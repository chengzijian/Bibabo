package com.bibabo.resolver;

import com.bibabo.api.ApiException;
import com.bibabo.api.OkHttp3Utils;
import com.bibabo.entity.VideoUrl;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * 通过URl获取有伴网播放地址
 *
 * @param <T>
 */
public class FetchVideoUrlOnSubscribe<T> implements FlowableOnSubscribe<T> {
    private String url;

    public FetchVideoUrlOnSubscribe(String url) {
        //获取到需要解析html地址
        this.url = url;
    }

    @Override
    public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
        try {
            //开始疯狂的数据抓取啦 不解释了 看文档  http://www.open-open.com/jsoup/
            LogUtils.e("FetchVideoUrlOnSubscribe>", url);
            String source = OkHttp3Utils.getDefault()._getSyncString(url);
            subscriber.onNext(parseSource(source));
            subscriber.onComplete();
        } catch (Exception e) {
            throw new ApiException("ERROR:数据解析错误");
        }
    }

    private synchronized T parseSource(String source) {
        VideoUrl videoUrl = null;
        Document doc = Jsoup.parse(source);
        Elements list = doc.select("script");
        String scriptString = "";
        for (Element e : list) {
            if (verifyScriptString(e.data())) {
                scriptString = e.data();
                break;
            }
        }
        if (!StringUtils.isEmpty(scriptString)) {
            String string = scriptString.substring(scriptString.indexOf("swflink"));
            String swflink = string.substring(string.indexOf("'") + 1, string.indexOf(";") - 1);
            String[] strarr = swflink.replace("http://", "").split("/");
            String videoName = strarr[strarr.length - 1].replace(".swf", "");
            String pdtype = scriptString.substring(scriptString.indexOf("pdtype"));
            pdtype = pdtype.substring(pdtype.indexOf("'") + 1, pdtype.indexOf(";") - 1);

            if (pdtype.equals("10")) {
                videoUrl = new VideoUrl(String.format("http://swf.youban.com/swf/lmp4dir/%s.mp4", videoName));
            } else if (pdtype.equals("1")) {
                String storyjihao = scriptString.substring(scriptString.indexOf("storyjihao"));
                storyjihao = storyjihao.substring(storyjihao.indexOf("'") + 1, storyjihao.indexOf(";") - 1);
                /*if (storyjihao.equals("0")) {
                    videoUrl = new VideoUrl(swflink);
                } else */if (storyjihao.equals("1")) {
                    videoUrl = new VideoUrl(String.format("http://swf.youban.com/swf/lgsmp4d/%s.mp4", videoName));
                }
            }
        }

        return (T) videoUrl;
    }


    /**
     * 验证该script是否正确
     *
     * @param str
     * @return
     */
    public boolean verifyScriptString(String str) {
        final String reg = "swflink.*?;";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

}
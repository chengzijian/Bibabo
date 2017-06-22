package com.bibabo.resolver;

import com.bibabo.api.ApiException;
import com.bibabo.api.OkHttp3Utils;
import com.bibabo.entity.QQVideoInfo;
import com.bibabo.entity.VideoUrl;
import com.bibabo.framework.utils.JSONUtils;
import com.bibabo.framework.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * 通过QQ视频播放页获取视频的播放地址
 * https://v.qq.com/x/cover/nnjrt62wfa51161.html
 *
 * @param <T>
 */
public class QQVideoOnSubscribe<T> implements FlowableOnSubscribe<T> {
    private String url;
    //&_qv_rmt=NcVO9eD3A190371Uy%3D&_qv_rmt2=ZP68ewgI148355kcg%3D
    private static String getinfo = "https://vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_%1$s&&charge=0&vid=%2$s&defaultfmt=auto&otype=json&guid=%3$s&platform=10901&defnpayver=1&appVer=3.2.18&sdtfrom=v1010&host=v.qq.com&ehost=%4$s&sphttps=1&_rnd=%5$s&spwm=2&defn=%6$s&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=2&_%7$s=";
    private static String playVideoUrl = "%1$s%2$s?sdtfrom=v1010&guid=%3$s&vkey=%4$s#t=66";

    public QQVideoOnSubscribe(String url) {
        //获取到需要解析html地址
        this.url = url;
    }

    @Override
    public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
        try {
            //开始疯狂的数据抓取啦 不解释了 看文档  http://www.open-open.com/jsoup/
            LogUtils.e("MainInfoHtmlOnSubscribe>", url);
            String source = OkHttp3Utils.getDefault()._getSyncString(url);
            subscriber.onNext(parseSource(source));
            subscriber.onComplete();
        } catch (Exception e) {
            throw new ApiException("ERROR:数据解析错误");
        }
    }

    private String createGuid() {
        String e = "";
        for (int r = 1; r <= 32; r++) {
            double d = Math.floor(16 * Math.random());
            //e += Math.floor(16 * Math.random()).toString(16);
            e += Integer.toString((int) d, 16);
        }
        LogUtils.e("createGuid:>>", "" + e);
        return e;//"9292fbe6a29f78d1dad9b3ad2c26c714";
    }

    private String getSubString(String href) {
        String suffixes = "html";
        Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");//正则判断
        Matcher mc = pat.matcher(href);//条件匹配
        while (mc.find()) {
            String substring = mc.group();//截取文件名后缀名
            LogUtils.e("substring:", substring);
            return substring;
        }
        return "";
    }

    private synchronized T parseSource(String source) throws Exception {
        VideoUrl result = null;
        Document doc = Jsoup.parse(source);
        Elements list = doc.select("link");
        String guid = createGuid();
        String ehost = "";
        String vid = "";
        for (Element link : list) {
            if (link.attr("rel").equals("canonical")) {
                ehost = link.attr("href");
                vid = getSubString(ehost).replace(".html", "");
                LogUtils.e("vid:", vid);
                break;
            }
        }

        long currentTime = System.currentTimeMillis();
        String infoUrl = String.format(getinfo, (int) ((Math.random() * 9 + 1) * 100000), vid, guid, ehost, String.valueOf(currentTime / 1000), "sd", String.valueOf(currentTime));
        LogUtils.e("infoUrl:", infoUrl);
        String source2 = OkHttp3Utils.getDefault()._getSyncString(infoUrl);
        source2 = source2.substring(source2.indexOf("(") + 1, source2.lastIndexOf(")"));
        QQVideoInfo treasure = JSONUtils.fromJsonString(source2, QQVideoInfo.class);

        QQVideoInfo.VlBean.ViBean viBean = treasure.getVl().getVi().get(0);
        String playPrefix = viBean.getUl().getUi().get(0).getUrl();
        String videoName = viBean.getFn().replace(".mp4", ".1.mp4");
        String vkey = viBean.getFvkey();
        String videoUrl = String.format(playVideoUrl, playPrefix, videoName, guid, vkey);
        LogUtils.e("videoUrl:", videoUrl);
        result = new VideoUrl(videoUrl);

        return (T) result;
    }
}
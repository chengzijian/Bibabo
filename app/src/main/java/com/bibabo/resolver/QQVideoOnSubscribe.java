package com.bibabo.resolver;

import android.renderscript.RenderScript;
import android.util.Log;

import com.bibabo.api.ApiException;
import com.bibabo.api.OkHttp3Utils;
import com.bibabo.entity.QQVideoInfo;
import com.bibabo.entity.VideoUrl;
import com.bibabo.framework.utils.JSONUtils;

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
    private static String getinfo = "https://vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_%1$s&&charge=0&vid=%2$s&defaultfmt=auto&otype=json&guid=%3$s&platform=10901&defnpayver=1&appVer=3.2.18&sdtfrom=v6010&host=v.qq.com&ehost=%4$s&sphttps=1&_rnd=%5$s&spwm=2&defn=%6$s&fhdswitch=0&show1080p=1&isHLS=0&newplatform=10901&defsrc=2&_%7$s=";
    private static String playVideoUrl = "%1$s%2$s?sdtfrom=v1010&guid=%3$s&vkey=%4$s#t=66";

    public QQVideoOnSubscribe(String url) {
        //获取到需要解析html地址
        this.url = url;
    }

    @Override
    public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
        try {
            //开始疯狂的数据抓取啦 不解释了 看文档  http://www.open-open.com/jsoup/
            Log.e("MainInfoHtmlOnSubscribe>", url);
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
        Log.e("createGuid:>>", "" + e);
        return e;//"9292fbe6a29f78d1dad9b3ad2c26c714";
    }

    private String getSubString(String href) {
        String suffixes = "html";
        Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");//正则判断
        Matcher mc = pat.matcher(href);//条件匹配
        while (mc.find()) {
            String substring = mc.group();//截取文件名后缀名
            Log.e("substring:", substring);
            return substring;
        }
        return "";
    }

    private synchronized T parseSource(String source) throws Exception {
        qqRm();
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
                Log.e("vid:", vid);
                break;
            }
        }

        long currentTime = System.currentTimeMillis();
        String infoUrl = String.format(getinfo, (int) ((Math.random() * 9 + 1) * 100000), vid, guid, ehost, String.valueOf(currentTime / 1000), "sd", String.valueOf(currentTime));
        Log.e("infoUrl:", infoUrl);
        String source2 = OkHttp3Utils.getDefault()._getSyncString(infoUrl);
        source2 = source2.substring(source2.indexOf("(") + 1, source2.lastIndexOf(")"));
        QQVideoInfo treasure = JSONUtils.fromJsonString(source2, QQVideoInfo.class);

        QQVideoInfo.VlBean.ViBean viBean = treasure.getVl().getVi().get(0);
        String playPrefix = viBean.getUl().getUi().get(0).getUrl();
        String videoName = viBean.getFn().replace(".mp4", ".1.mp4");
        String vkey = viBean.getFvkey();
        String videoUrl = String.format(playVideoUrl, playPrefix, videoName, guid, vkey);
        Log.e("videoUrl:", videoUrl);
        result = new VideoUrl(videoUrl);

        return (T) result;
    }

//    https://vv.video.qq.com/getinfo?callback=txplayerJsonpCallBack_getinfo_897766&&charge=0&vid=l0024si3r7q&defaultfmt=auto&otype=json&guid=70172a7b5122e521f8ddb7836b6cf5a6&platform=10901&defnpayver=1&appVer=3.2.21&sdtfrom=v1010&host=v.qq.com&ehost=https%3A%2F%2Fv.qq.com%2Ftv%2Fp%2Ftopic%2Fcqzzt%2Findex.html&sphttps=1&_rnd=1498708582&spwm=2&defn=hd&fhdswitch=1&show1080p=1&isHLS=0&newplatform=10901&defsrc=2&_qv_rmt=VKAjyq%2FGA197882zW%3D&_qv_rmt2=em9vTNb91480523jw%3D&_1498708582732=
    private String a = "10901";//platform       a
    private String b = "l0024si3r7q";//vids     b
    private String c = "V5013";           //    c
    private String d = "1";               //    d
    private String g = "";
    private String h = "";
    private long f = System.currentTimeMillis() / 1000;      //    parseInt(tvp.$.now() / 1e3, 10);

    String key = a + b + f + eSeed + g + h + d + c;

    private String qqRm() {
        String hex = ehexToString("1a0fe620487b3fcc03f1f205f981d65e");
        Log.e("QQVideoOnSubscribe>>hex", hex);
        String tempcalc = eTempcalc(hex, eSeed);
        Log.e("QQVideoOnSubscribe>>tempcalc", tempcalc);
        String urlenc = eUrlenc(tempcalc, 1, 1498714227);
        Log.e("QQVideoOnSubscribe>>urlenc", urlenc);
        String u1 = eU1(urlenc, 0);
        Log.e("QQVideoOnSubscribe>>u1", u1);
        return "";
    }

    private String eU1(String a, int b){
        String c="";
        for(int d = b;d<a.length();d+=2){
            c+=a.charAt(d);
        }
        return c;
    }

    static String eSeed = "#$#@#*ad";
    String _urlStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    private String ehexToString(String a) {
        StringBuffer sb = new StringBuffer();
        int i = "0x".equals(a.substring(0, 2)) ? 2 : 0;
        for (int c = i; c < a.length(); c += 2){
            sb.append(String.valueOf(Integer.valueOf(a.substring(c, 2+c), 16)));
        }
        return sb.toString();
    }

    private String eTempcalc(String a, String b) {
        String c = "";
        for (int d = 0; d < a.length(); d++){
            c += String.valueOf(charCodeAt(a, d) ^ charCodeAt(b, d % 4));
        }
        return c;
    }


    private String eUrlenc(String a, int b, int c) {
        int g, f, d, h, i, j, k;
        String l = "";
        for (int m = 0; m < a.length(); ) {
            d = charCodeAt(a, m++);
            f = charCodeAt(a, m++);
            g = charCodeAt(a, m++);
            if (15 == m) {
                l += "A";
                l += b;
                l += c;
            }

            h = d >> 2;
            i = (3 & d) << 4 | f >> 4;
            j = (15 & f) << 2 | g >> 6;
            k = 63 & g;
            //isNaN(f) ? j = k = 64 : isNaN(g) && (k = 64);
            l = l + _urlStr.charAt(h) + _urlStr.charAt(i) + _urlStr.charAt(j) + _urlStr.charAt(k);
        }
        return l;
    }

    public static int charCodeAt(String str, int i) {
        return (int) str.charAt(i);
    }

    public boolean notIsNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return false;
        }
        return true;
    }

}
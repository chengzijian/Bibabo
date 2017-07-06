package com.bibabo.resolver;

import com.bibabo.entity.QQCoverInfoResult;
import com.bibabo.entity.QQListInfoResult;
import com.bibabo.entity.QQVideoInfoResult;
import com.bibabo.entity.VideoDetailsInfo;
import com.bibabo.framework.utils.JSONUtils;

import org.reactivestreams.Publisher;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拿到视频播放列表
 * https://v.qq.com/x/list/children?itype=2&offset=0
 *
 * @param <T>
 */
public class QVVideoDetailsResolver<T, F> implements Function<T, Publisher<F>> {
    @Override
    public Publisher<F> apply(@NonNull T source) throws Exception {
        VideoDetailsInfo result = new VideoDetailsInfo();
        String htmlString = String.valueOf(source);
        String beginStr = "COVER_INFO = ";
        String endStr = "]}}}";
        htmlString = htmlString.substring(htmlString.indexOf(beginStr), htmlString.indexOf(endStr) + endStr.length());
        String[] infoList = htmlString.split("var ");
        for (String info : infoList) {
            String title = info.substring(0, info.indexOf("=")).trim();
            info = info.substring(info.indexOf("{"), info.lastIndexOf("}") + 1);
            if(title.equals("COVER_INFO")){
                QQCoverInfoResult coverInfoResult = JSONUtils.fromJsonString(info, QQCoverInfoResult.class);

                result.setTitle(coverInfoResult.getTitle());
                result.setSecTitle(coverInfoResult.getSecTitle());
                result.setId(coverInfoResult.getId());
                result.setPic(coverInfoResult.getPic());
                result.setTypeName(coverInfoResult.getType_name());
                result.setScore(coverInfoResult.getScore());
                result.setEpisodeCurrent(coverInfoResult.getEpisodeCurrent());
            } else if(title.equals("COLUMN_INFO")){
                continue;
            } else if(title.equals("VIDEO_INFO")){
                //去掉无法解析的参数
                info = info.replace(info.substring(info.indexOf("recordHistory"), info.indexOf("vid")), "");
                QQVideoInfoResult coverInfoResult = JSONUtils.fromJsonString(info, QQVideoInfoResult.class);

                result.setCurrVideoTitle(coverInfoResult.getTitle());
                result.setCurrVideoTryTime(coverInfoResult.getTryTime());
                result.setCurrVideoVid(coverInfoResult.getVid());
                result.setCurrVideoType(coverInfoResult.getType());
                result.setCurrVideoPiantou(coverInfoResult.getPiantou());
                result.setCurrVideoPianwei(coverInfoResult.getPianwei());
            } else if(title.equals("VPP_INFO")){
                continue;
            } else if(title.equals("LIST_INFO")){
                String str = replaceAllShow(info, "[^\\]\\{]*(\":\\{\"v)", "\\}\\}, \\{\"videoItem\":\\{\"v");
                str = str.replace("{}},", "[").replace("}}}", "}}]}");
                QQListInfoResult videoListInfo = JSONUtils.fromJsonString(str, QQListInfoResult.class);
                result.setListInfo(videoListInfo);
            }
        }

//        Map<Integer, Object> result = new HashMap<>();
//        Document doc = Jsoup.parse(htmlString);
//
//        Element filter = doc.select("div.mod_episode_filter").first();
//        if(filter != null){
//            Elements pages = filter.getElementsByTag("a");
//            int pageSize = pages.size();
//            if (pageSize > 0) {
//                //这里获取列表的分页信息 如 1-120；121-140
//                List<String> pagesSelect = new ArrayList<>(pageSize);
//                for (int i = 0; i < pageSize; i++) {
//                    pagesSelect.add(pages.get(i).text());
//                }
//                result.put(0, pagesSelect);
//            } else {
//                result.put(0, null);
//            }
//        } else {
//            result.put(0, null);
//        }
//
//        /**
//         * 获取简介
//         */
//        String title = doc.select("h1.album_title").first().getElementsByTag("a").text();
//        String intro = doc.select("div.album_intro").first().text();
//        String img = doc.select("a.album_pic").first().getElementsByTag("img").attr("r-lazyload").replace("//", "http://");
//
//        SummaryInfo summaryInfo = new SummaryInfo();
//        summaryInfo.setTitle(title);
//        summaryInfo.setIntro(intro);
//        summaryInfo.setImage(img);
//        result.put(1, summaryInfo);
//
//        /**
//         * 获取播放列表
//         */
//        String begin = "LIST_INFO = {";
//        String end = "]}}}";
//        String listInfo = htmlString.substring(htmlString.indexOf(begin) + begin.length() - 1, htmlString.indexOf(end) + end.length());
//        String str = replaceAllShow(listInfo, "[^\\]\\{]*(\":\\{\"v)", "\\}\\}, \\{\"videoItem\":\\{\"v");// 将数字替换成　#
//        str = str.replace("{}},", "[").replace("}}}", "}}]}");
//
//        QQListInfoResult qqListInfos = JSONUtils.fromJsonString(str, QQListInfoResult.class);
//        result.put(2, qqListInfos);

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
package com.bibabo.fragment.watch.player;

import com.bibabo.entity.QQListInfoResult;

import java.util.List;

/**
 * Created by zijian.cheng on 2017/7/11.
 */

public class VideoCommonData {

    private static List<QQListInfoResult.DataBean> videoList;

    public static void setVideoList(List<QQListInfoResult.DataBean> videoList) {
        VideoCommonData.videoList = videoList;
    }

    public static List<QQListInfoResult.DataBean> getVideoList() {
        return videoList;
    }

    public static void clear() {
        if (videoList != null) {
            videoList.clear();
        }
    }

}

package com.bibabo.event;

/**
 * 获取视频info
 * Created by zijian.cheng on 2017/7/11.
 */

public class GetVideoInfoEvent {
    public String getInfo;
    public GetVideoInfoEvent(String getInfo) {
        this.getInfo = getInfo;
    }
}

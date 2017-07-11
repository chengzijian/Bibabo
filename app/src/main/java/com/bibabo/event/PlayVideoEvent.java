package com.bibabo.event;

/**
 * Created by zijian.cheng on 2017/7/11.
 */

public class PlayVideoEvent {

    public String title;
    public String vid;

    public PlayVideoEvent(String title, String currVideoVid) {
        this.title = title;
        this.vid = currVideoVid;
    }
}

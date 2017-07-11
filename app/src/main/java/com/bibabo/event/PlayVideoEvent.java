package com.bibabo.event;

/**
 * 点击剧集
 * Created by zijian.cheng on 2017/7/11.
 */

public class PlayVideoEvent {

    public int position;

    public PlayVideoEvent(int position) {
        this.position = position;
    }
}

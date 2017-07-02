package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 剧集简介
 * Created by zijian on 17/7/2.
 */

public class SummaryInfo {

    @SerializedName("pic")
    private String pic;

    @SerializedName("desc")
    private String desc;

    @SerializedName("title")
    private String title;

    @SerializedName("director")
    private String director;
    @SerializedName("performer")
    private String performer;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.desc = intro;
    }

    public void setImage(String image) {
        this.pic = image;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}

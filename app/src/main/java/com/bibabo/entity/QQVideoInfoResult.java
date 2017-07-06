package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zijian.cheng on 2017/7/6.
 */

public class QQVideoInfoResult implements Serializable {
    private static final long serialVersionUID = -2861619024433827268L;

    /**
     * title : 小小滑板车
     * duration : 116
     * episode : 0
     * vid : p0522hu3p8m
     * piantou : 0
     * pianwei : 0
     * showMark : 0
     * showBullet : true
     * showImageBullet : false
     * openBulletDefault : false
     * isNeedPay : false
     * isTrailer : 0
     * positive_trailer : 1
     * singlePrice : undefined
     * vipPrice : undefined
     * tryTime : 1306030
     * resolution : 39
     * type : 106
     */

    @SerializedName("title")
    private String title;
    @SerializedName("duration")
    private String duration;
    @SerializedName("episode")
    private String episode;
    @SerializedName("vid")
    private String vid;
    @SerializedName("piantou")
    private String piantou;
    @SerializedName("pianwei")
    private String pianwei;
    @SerializedName("showMark")
    private String showMark;
    @SerializedName("showBullet")
    private boolean showBullet;
    @SerializedName("showImageBullet")
    private boolean showImageBullet;
    @SerializedName("openBulletDefault")
    private boolean openBulletDefault;
    @SerializedName("isNeedPay")
    private boolean isNeedPay;
    @SerializedName("isTrailer")
    private int isTrailer;
    @SerializedName("positive_trailer")
    private int positive_trailer;
    @SerializedName("singlePrice")
    private String singlePrice;
    @SerializedName("vipPrice")
    private String vipPrice;
    @SerializedName("tryTime")
    private String tryTime;
    @SerializedName("resolution")
    private int resolution;
    @SerializedName("type")
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPiantou() {
        return piantou;
    }

    public void setPiantou(String piantou) {
        this.piantou = piantou;
    }

    public String getPianwei() {
        return pianwei;
    }

    public void setPianwei(String pianwei) {
        this.pianwei = pianwei;
    }

    public String getShowMark() {
        return showMark;
    }

    public void setShowMark(String showMark) {
        this.showMark = showMark;
    }

    public boolean isShowBullet() {
        return showBullet;
    }

    public void setShowBullet(boolean showBullet) {
        this.showBullet = showBullet;
    }

    public boolean isShowImageBullet() {
        return showImageBullet;
    }

    public void setShowImageBullet(boolean showImageBullet) {
        this.showImageBullet = showImageBullet;
    }

    public boolean isOpenBulletDefault() {
        return openBulletDefault;
    }

    public void setOpenBulletDefault(boolean openBulletDefault) {
        this.openBulletDefault = openBulletDefault;
    }

    public boolean isIsNeedPay() {
        return isNeedPay;
    }

    public void setIsNeedPay(boolean isNeedPay) {
        this.isNeedPay = isNeedPay;
    }

    public int getIsTrailer() {
        return isTrailer;
    }

    public void setIsTrailer(int isTrailer) {
        this.isTrailer = isTrailer;
    }

    public int getPositive_trailer() {
        return positive_trailer;
    }

    public void setPositive_trailer(int positive_trailer) {
        this.positive_trailer = positive_trailer;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getTryTime() {
        return tryTime;
    }

    public void setTryTime(String tryTime) {
        this.tryTime = tryTime;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

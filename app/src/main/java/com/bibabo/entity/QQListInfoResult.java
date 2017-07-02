package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zijian on 17/7/2.
 */

public class QQListInfoResult implements Serializable {

    private static final long serialVersionUID = -2864619024433827268L;

    @SerializedName("vid")
    private List<String> vid;
    @SerializedName("data")
    private List<DataBean> data;

    public List<String> getVid() {
        return vid;
    }

    public void setVid(List<String> vid) {
        this.vid = vid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * videoItem : {"vid":"e0518k7x8jy","playStartTime":0,"playEndTime":0,"duration":"149","title":"甩葱歌","episode":"0","showBullet":true,"showImageBullet":false,"openBulletDefault":false,"showMark":0,"isNeedPay":false,"tryTime":"1306030","preview":"//puui.qpic.cn/vpic/0/e0518k7x8jy_160_90_3.jpg/0","isTrailer":0,"positive_trailer":1,"isFull":0,"langue":null,"series_part_title":"第1季 第集","category_map":[11239,"正片",1248,"儿童音乐",106,"少儿"]}
         */

        @SerializedName("videoItem")
        private VideoItemBean videoItem;

        public VideoItemBean getVideoItem() {
            return videoItem;
        }

        public void setVideoItem(VideoItemBean videoItem) {
            this.videoItem = videoItem;
        }

        public static class VideoItemBean {
            /**
             * vid : e0518k7x8jy
             * playStartTime : 0
             * playEndTime : 0
             * duration : 149
             * title : 甩葱歌
             * episode : 0
             * showBullet : true
             * showImageBullet : false
             * openBulletDefault : false
             * showMark : 0
             * isNeedPay : false
             * tryTime : 1306030
             * preview : //puui.qpic.cn/vpic/0/e0518k7x8jy_160_90_3.jpg/0
             * isTrailer : 0
             * positive_trailer : 1
             * isFull : 0
             * langue : null
             * series_part_title : 第1季 第集
             * category_map : [11239,"正片",1248,"儿童音乐",106,"少儿"]
             */

            @SerializedName("vid")
            private String vid;
            @SerializedName("playStartTime")
            private long playStartTime;
            @SerializedName("playEndTime")
            private long playEndTime;
            @SerializedName("duration")
            private String duration;
            @SerializedName("title")
            private String title;
            @SerializedName("episode")
            private String episode;
            @SerializedName("showBullet")
            private boolean showBullet;
            @SerializedName("showImageBullet")
            private boolean showImageBullet;
            @SerializedName("openBulletDefault")
            private boolean openBulletDefault;
            @SerializedName("showMark")
            private int showMark;
            @SerializedName("isNeedPay")
            private boolean isNeedPay;
            @SerializedName("tryTime")
            private String tryTime;
            @SerializedName("preview")
            private String preview;
            @SerializedName("isTrailer")
            private int isTrailer;
            @SerializedName("positive_trailer")
            private int positive_trailer;
            @SerializedName("isFull")
            private int isFull;
            @SerializedName("langue")
            private String langue;
            @SerializedName("series_part_title")
            private String series_part_title;
            @SerializedName("category_map")
            private List<Object> category_map;

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public long getPlayStartTime() {
                return playStartTime;
            }

            public void setPlayStartTime(int playStartTime) {
                this.playStartTime = playStartTime;
            }

            public long getPlayEndTime() {
                return playEndTime;
            }

            public void setPlayEndTime(int playEndTime) {
                this.playEndTime = playEndTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getEpisode() {
                return episode;
            }

            public void setEpisode(String episode) {
                this.episode = episode;
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

            public int getShowMark() {
                return showMark;
            }

            public void setShowMark(int showMark) {
                this.showMark = showMark;
            }

            public boolean isIsNeedPay() {
                return isNeedPay;
            }

            public void setIsNeedPay(boolean isNeedPay) {
                this.isNeedPay = isNeedPay;
            }

            public String getTryTime() {
                return tryTime;
            }

            public void setTryTime(String tryTime) {
                this.tryTime = tryTime;
            }

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
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

            public int getIsFull() {
                return isFull;
            }

            public void setIsFull(int isFull) {
                this.isFull = isFull;
            }

            public String getLangue() {
                return langue;
            }

            public void setLangue(String langue) {
                this.langue = langue;
            }

            public String getSeries_part_title() {
                return series_part_title;
            }

            public void setSeries_part_title(String series_part_title) {
                this.series_part_title = series_part_title;
            }

            public List<Object> getCategory_map() {
                return category_map;
            }

            public void setCategory_map(List<Object> category_map) {
                this.category_map = category_map;
            }
        }
    }
}

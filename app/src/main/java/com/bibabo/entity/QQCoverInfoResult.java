package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zijian.cheng on 2017/7/6.
 */

public class QQCoverInfoResult implements Serializable {
    private static final long serialVersionUID = -2861619024433827268L;

    /**
     * title : 亲宝儿歌
     * secTitle : 亲亲我的宝贝
     * brief :
     * id : qpz749hwize3ats
     * columnid : 0
     * isColumnCanFollow : false
     * pic : //i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/q/qpz749hwize3ats.jpg
     * typeid : 106
     * type_name : 少儿
     * videoReName : true
     * sourceid : undefined
     * copyright : undefined
     * playright : 1,2,3,4,5,8,9
     * subtypeid : undefined
     * tag : []
     * score : {"c_mix_score":"7.4","hot":"5.0299","score":"7.4"}
     * variety_type : null
     * list_show_style : 1530991
     * digital_mode : 194711
     * isUpdate : false
     * pre_play_plats_id : null
     * isNeedPay : false
     * payStatus : 8
     * vipDiscount : undefined
     * vipOnlineDate : null
     * episodeAll :
     * vipPullText : 小企鹅乐园—腾讯视频儿童版，快去下载体验吧！
     * pcVipPullText : null
     * pcVipPullUrl : null
     * pcVipPullExpire : null
     * isZhengpian : undefined
     * isTrailer : 0
     * pre_play_timelong_id :
     * positive_trailer : 1
     * episodeCurrent : 120
     * episodeVip : 0
     * firstClipListVid :
     */

    @SerializedName("title")
    private String title;
    @SerializedName("secTitle")
    private String secTitle;
    @SerializedName("brief")
    private String brief;
    @SerializedName("id")
    private String id;
    @SerializedName("columnid")
    private int columnid;
    @SerializedName("isColumnCanFollow")
    private boolean isColumnCanFollow;
    @SerializedName("pic")
    private String pic;
    @SerializedName("typeid")
    private int typeid;
    @SerializedName("type_name")
    private String type_name;
    @SerializedName("videoReName")
    private boolean videoReName;
    @SerializedName("sourceid")
    private String sourceid;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("playright")
    private String playright;
    @SerializedName("subtypeid")
    private String subtypeid;
    @SerializedName("score")
    private ScoreBean score;
    @SerializedName("variety_type")
    private String variety_type;
    @SerializedName("list_show_style")
    private String list_show_style;
    @SerializedName("digital_mode")
    private String digital_mode;
    @SerializedName("isUpdate")
    private boolean isUpdate;
    @SerializedName("pre_play_plats_id")
    private String pre_play_plats_id;
    @SerializedName("isNeedPay")
    private boolean isNeedPay;
    @SerializedName("payStatus")
    private int payStatus;
    @SerializedName("vipDiscount")
    private String vipDiscount;
    @SerializedName("vipOnlineDate")
    private String vipOnlineDate;
    @SerializedName("episodeAll")
    private String episodeAll;
    @SerializedName("vipPullText")
    private String vipPullText;
    @SerializedName("pcVipPullText")
    private String pcVipPullText;
    @SerializedName("pcVipPullUrl")
    private String pcVipPullUrl;
    @SerializedName("pcVipPullExpire")
    private String pcVipPullExpire;
    @SerializedName("isZhengpian")
    private String isZhengpian;
    @SerializedName("isTrailer")
    private int isTrailer;
    @SerializedName("pre_play_timelong_id")
    private String pre_play_timelong_id;
    @SerializedName("positive_trailer")
    private int positive_trailer;
    @SerializedName("episodeCurrent")
    private String episodeCurrent;
    @SerializedName("episodeVip")
    private String episodeVip;
    @SerializedName("firstClipListVid")
    private String firstClipListVid;
    @SerializedName("tag")
    private List<String> tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecTitle() {
        return secTitle;
    }

    public void setSecTitle(String secTitle) {
        this.secTitle = secTitle;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getColumnid() {
        return columnid;
    }

    public void setColumnid(int columnid) {
        this.columnid = columnid;
    }

    public boolean isIsColumnCanFollow() {
        return isColumnCanFollow;
    }

    public void setIsColumnCanFollow(boolean isColumnCanFollow) {
        this.isColumnCanFollow = isColumnCanFollow;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public boolean isVideoReName() {
        return videoReName;
    }

    public void setVideoReName(boolean videoReName) {
        this.videoReName = videoReName;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getPlayright() {
        return playright;
    }

    public void setPlayright(String playright) {
        this.playright = playright;
    }

    public String getSubtypeid() {
        return subtypeid;
    }

    public void setSubtypeid(String subtypeid) {
        this.subtypeid = subtypeid;
    }

    public ScoreBean getScore() {
        return score;
    }

    public void setScore(ScoreBean score) {
        this.score = score;
    }

    public String getVariety_type() {
        return variety_type;
    }

    public void setVariety_type(String variety_type) {
        this.variety_type = variety_type;
    }

    public String getList_show_style() {
        return list_show_style;
    }

    public void setList_show_style(String list_show_style) {
        this.list_show_style = list_show_style;
    }

    public String getDigital_mode() {
        return digital_mode;
    }

    public void setDigital_mode(String digital_mode) {
        this.digital_mode = digital_mode;
    }

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getPre_play_plats_id() {
        return pre_play_plats_id;
    }

    public void setPre_play_plats_id(String pre_play_plats_id) {
        this.pre_play_plats_id = pre_play_plats_id;
    }

    public boolean isIsNeedPay() {
        return isNeedPay;
    }

    public void setIsNeedPay(boolean isNeedPay) {
        this.isNeedPay = isNeedPay;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(String vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public String getVipOnlineDate() {
        return vipOnlineDate;
    }

    public void setVipOnlineDate(String vipOnlineDate) {
        this.vipOnlineDate = vipOnlineDate;
    }

    public String getEpisodeAll() {
        return episodeAll;
    }

    public void setEpisodeAll(String episodeAll) {
        this.episodeAll = episodeAll;
    }

    public String getVipPullText() {
        return vipPullText;
    }

    public void setVipPullText(String vipPullText) {
        this.vipPullText = vipPullText;
    }

    public String getPcVipPullText() {
        return pcVipPullText;
    }

    public void setPcVipPullText(String pcVipPullText) {
        this.pcVipPullText = pcVipPullText;
    }

    public String getPcVipPullUrl() {
        return pcVipPullUrl;
    }

    public void setPcVipPullUrl(String pcVipPullUrl) {
        this.pcVipPullUrl = pcVipPullUrl;
    }

    public String getPcVipPullExpire() {
        return pcVipPullExpire;
    }

    public void setPcVipPullExpire(String pcVipPullExpire) {
        this.pcVipPullExpire = pcVipPullExpire;
    }

    public String getIsZhengpian() {
        return isZhengpian;
    }

    public void setIsZhengpian(String isZhengpian) {
        this.isZhengpian = isZhengpian;
    }

    public int getIsTrailer() {
        return isTrailer;
    }

    public void setIsTrailer(int isTrailer) {
        this.isTrailer = isTrailer;
    }

    public String getPre_play_timelong_id() {
        return pre_play_timelong_id;
    }

    public void setPre_play_timelong_id(String pre_play_timelong_id) {
        this.pre_play_timelong_id = pre_play_timelong_id;
    }

    public int getPositive_trailer() {
        return positive_trailer;
    }

    public void setPositive_trailer(int positive_trailer) {
        this.positive_trailer = positive_trailer;
    }

    public String getEpisodeCurrent() {
        return episodeCurrent;
    }

    public void setEpisodeCurrent(String episodeCurrent) {
        this.episodeCurrent = episodeCurrent;
    }

    public String getEpisodeVip() {
        return episodeVip;
    }

    public void setEpisodeVip(String episodeVip) {
        this.episodeVip = episodeVip;
    }

    public String getFirstClipListVid() {
        return firstClipListVid;
    }

    public void setFirstClipListVid(String firstClipListVid) {
        this.firstClipListVid = firstClipListVid;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public static class ScoreBean {
        /**
         * c_mix_score : 7.4
         * hot : 5.0299
         * score : 7.4
         */

        @SerializedName("c_mix_score")
        private String c_mix_score;
        @SerializedName("hot")
        private String hot;
        @SerializedName("score")
        private String score;

        public String getC_mix_score() {
            return c_mix_score;
        }

        public void setC_mix_score(String c_mix_score) {
            this.c_mix_score = c_mix_score;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}

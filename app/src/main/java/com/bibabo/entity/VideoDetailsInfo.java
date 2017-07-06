package com.bibabo.entity;

import java.io.Serializable;

/**
 * Created by zijian.cheng on 2017/7/6.
 */

public class VideoDetailsInfo implements Serializable {
    private static final long serialVersionUID = -2864681024433827268L;

    private String id;
    private String title;
    private String secTitle;
    private String pic;
    private String typeName;
    private QQCoverInfoResult.ScoreBean score;
    private String episodeCurrent;//分页

    private String currVideoTitle;
    private String currVideoVid;
    private String currVideoTryTime;
    private String currVideoType;
    private String currVideoPiantou;
    private String currVideoPianwei;

    private QQListInfoResult listInfo;

    public QQCoverInfoResult.ScoreBean getScore() {
        return score;
    }

    public void setScore(QQCoverInfoResult.ScoreBean score) {
        this.score = score;
    }

    public void setListInfo(QQListInfoResult listInfo) {
        this.listInfo = listInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEpisodeCurrent() {
        return episodeCurrent;
    }

    public void setEpisodeCurrent(String episodeCurrent) {
        this.episodeCurrent = episodeCurrent;
    }

    public String getCurrVideoTitle() {
        return currVideoTitle;
    }

    public void setCurrVideoTitle(String currVideoTitle) {
        this.currVideoTitle = currVideoTitle;
    }

    public String getCurrVideoVid() {
        return currVideoVid;
    }

    public void setCurrVideoVid(String currVideoVid) {
        this.currVideoVid = currVideoVid;
    }

    public String getCurrVideoTryTime() {
        return currVideoTryTime;
    }

    public void setCurrVideoTryTime(String currVideoTryTime) {
        this.currVideoTryTime = currVideoTryTime;
    }

    public String getCurrVideoType() {
        return currVideoType;
    }

    public void setCurrVideoType(String currVideoType) {
        this.currVideoType = currVideoType;
    }

    public QQListInfoResult getListInfo() {
        return listInfo;
    }

    public void setCurrVideoPiantou(String currVideoPiantou) {
        this.currVideoPiantou = currVideoPiantou;
    }

    public void setCurrVideoPianwei(String currVideoPianwei) {
        this.currVideoPianwei = currVideoPianwei;
    }
}

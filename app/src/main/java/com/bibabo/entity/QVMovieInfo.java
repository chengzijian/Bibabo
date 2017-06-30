package com.bibabo.entity;

import java.io.Serializable;

/**
 * 影片信息
 * Created by zijian.cheng on 2017/6/30.
 */

public class QVMovieInfo implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;
    private String videoHtmlUrl;
    private String vid;
    private String vpic;
    private String vtitle;
    private String figureInfo;
    private String figureDesc;
    private String playNum;

    //仅仅用来判断分页
    private boolean hasNextPage;

    public QVMovieInfo(){}

    public QVMovieInfo(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }

    public String getVideoHtmlUrl() {
        return videoHtmlUrl;
    }

    public void setVideoHtmlUrl(String videoHtmlUrl) {
        this.videoHtmlUrl = videoHtmlUrl;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVpic() {
        return vpic;
    }

    public void setVpic(String vpic) {
        this.vpic = vpic;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getFigureInfo() {
        return figureInfo;
    }

    public void setFigureInfo(String figureInfo) {
        this.figureInfo = figureInfo;
    }

    public String getFigureDesc() {
        return figureDesc;
    }

    public void setFigureDesc(String figureDesc) {
        this.figureDesc = figureDesc;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }
}

package com.bibabo.entity;

import com.shuyu.gsyvideoplayer.model.GSYVideoModel;

/**
 * Created by zijian.cheng on 2017/7/4.
 */

public class CustomVideoModel extends GSYVideoModel {

    private String vid;
    private String playPrefix;
    private String keyid;
    private String title;
    private String guid;
    private String fvkey;
    private String cd;
    private String td;

    public CustomVideoModel(String vid, String playPrefix, String title, String fvkey, String keyid, String cd, String td) {
        super(null, null);
        this.vid = vid;
        this.playPrefix = playPrefix;
        this.fvkey = fvkey;
        this.title = title;
        this.keyid = keyid.replaceAll("\\..*?10", ".p") + ".mp4";
        this.cd = cd;
        this.td = td;
    }

    public CustomVideoModel(String url, String title) {
        super(url, title);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getFvkey() {
        return fvkey;
    }

    public String getCd() {
        return cd;
    }

    public String getGuid() {
        return guid;
    }

    public String getKeyid() {
        return keyid;
    }

    public String getPlayPrefix() {
        return playPrefix;
    }

    public String getTd() {
        return td;
    }

    public String getVid() {
        return vid;
    }
}

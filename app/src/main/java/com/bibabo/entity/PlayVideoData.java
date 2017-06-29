package com.bibabo.entity;

import java.io.Serializable;

public class PlayVideoData implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;
    private String url;
    private String title;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
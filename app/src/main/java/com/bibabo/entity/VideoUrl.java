package com.bibabo.entity;

import java.io.Serializable;

public class VideoUrl implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;
    private String url;

    public VideoUrl(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
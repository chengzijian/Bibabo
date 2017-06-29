package com.bibabo.entity;

import java.io.Serializable;

/**
 * Created by zijian.cheng on 2017/6/29.
 */

public class VideoData implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;
    private long timestamp;
    private String vid;
    private String guid;
    private String ehost;

    public String getVid() {
        return vid;
    }

    public String getGuid() {
        return guid;
    }

    public String getEhost() {
        return ehost;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setEhost(String ehost) {
        this.ehost = ehost;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}

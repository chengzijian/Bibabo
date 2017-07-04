package com.bibabo.entity;

import java.io.Serializable;

/**
 *
 * Created by zijian.cheng on 2017/6/21.
 */

public class QQNextPackVideoInfo implements Serializable {

    private static final long serialVersionUID = -2864689024433827268L;

    /**
     * br : 67556.69
     * ct : 21600
     * filename : q00227uhbe9.p212.mp4
     * ip : 210.22.151.242
     * key : 48913302454324709CAAEA261422AACCE2071146F9C8B784168D782C62C970C9ECD38CAB307B19C9E45037FA23EC07CF8350C12826D7AFEA77384A3E4BDBFF8F0D5869226FF325FA43DDDD462DFBBC23A78D93EE84EAD3960F139B148E4B1A647822644C8226A3374EEEFE0C481EC0EABF53388E74CA3F4C
     * keyid : q00227uhbe9.10212.2
     * level : 0
     * levelvalid : 1
     * s : o
     * sp : 0
     * sr : 0
     */

    private double br;
    private int ct;
    private String filename;
    private String ip;
    private String key;
    private String keyid;
    private int level;
    private int levelvalid;
    private String s;
    private int sp;
    private int sr;

    public double getBr() {
        return br;
    }

    public void setBr(double br) {
        this.br = br;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelvalid() {
        return levelvalid;
    }

    public void setLevelvalid(int levelvalid) {
        this.levelvalid = levelvalid;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }
}

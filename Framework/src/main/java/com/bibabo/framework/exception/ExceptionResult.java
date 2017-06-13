package com.bibabo.framework.exception;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by liqiang.liao on 2014/7/11.
 *
 * @author liqiang.liao
 * @version 1.0.0
 */
public class ExceptionResult implements Serializable {

    /**
     * 包名
     */
    @SerializedName("package")
    private String mPackageName;

    /**
     * 错误时间
     */
    @SerializedName("error_time")
    private String mErrorTime;

    /**
     * 版本号
     */
    @SerializedName("ip")
    private String mIpAddress;

    /**
     * 版本号
     */
    @SerializedName("version")
    private String mAppVersion;

    /**
     * 渠道号
     */
    @SerializedName("channel")
    private String mAppChannel;

    /**
     * 手机名称
     */
    @SerializedName("mid")
    private String mMid;

    /**
     * Android版本号
     */
    @SerializedName("splus")
    private String mSplus;

    /**
     * rom名称
     */
    @SerializedName("rom")
    private String mRom;

    /**
     * 内存使用状态
     */
    @SerializedName("memory")
    private String memory;

    /**
     * getPackageName
     * @return String
     */
    public String getPackageName() {
        return mPackageName;
    }

    /**
     * setPackageName
     * @param  packageName packageName
     */
    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    /**
     * getErrorTime
     * @return String
     */
    public String getErrorTime() {
        return mErrorTime;
    }

    /**
     * setErrorTime
     * @param errorTime errorTime
     */
    public void setErrorTime(String errorTime) {
        this.mErrorTime = errorTime;
    }

    /**
     * getAppVersion
     * @return String
     */
    public String getAppVersion() {
        return mAppVersion;
    }

    /**
     * setAppVersion
     * @param appVersion appVersion
     */
    public void setAppVersion(String appVersion) {
        this.mAppVersion = appVersion;
    }

    /**
     * getAppChannel
     * @return String
     */
    public String getAppChannel() {
        return mAppChannel;
    }

    /**
     * setAppChannel
     * @param appChannel appChannel
     */
    public void setAppChannel(String appChannel) {
        this.mAppChannel = appChannel;
    }

    /**
     * getMid
     * @return getMid
     */
    public String getMid() {
        return mMid;
    }

    /**
     * setMid
     * @param mid mid
     */
    public void setMid(String mid) {
        this.mMid = mid;
    }

    /**
     * getSplus
     * @return getSplus
     */
    public String getSplus() {
        return mSplus;
    }

    /**
     * setSplus
     * @param splus splus
     */
    public void setSplus(String splus) {
        this.mSplus = splus;
    }

    /**
     * getRom
     * @return String
     */
    public String getRom() {
        return mRom;
    }

    /**
     * setRom
     * @param rom rom
     */
    public void setRom(String rom) {
        this.mRom = rom;
    }

    /**
     * getMemory
     * @return String
     */
    public String getMemory() {
        return memory;
    }

    /**
     * setMemory
     * @param tmemory tmemory
     */
    public void setMemory(String tmemory) {
        this.memory = tmemory;
    }

    /**
     * @return mIpAddress
     */
    public String getIpAddress() {
        return mIpAddress;
    }

    /**
     * @param ipAddress ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.mIpAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "ExceptionResult{" +
                "mPackageName='" + mPackageName + '\'' +
                ", mErrorTime='" + mErrorTime + '\'' +
                ", mIpAddress='" + mIpAddress + '\'' +
                ", mAppVersion='" + mAppVersion + '\'' +
                ", mAppChannel='" + mAppChannel + '\'' +
                ", mMid='" + mMid + '\'' +
                ", mSplus='" + mSplus + '\'' +
                ", mRom='" + mRom + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}

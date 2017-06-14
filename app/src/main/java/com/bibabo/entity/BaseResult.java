package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 返回结果数据结构基类
 * 本类以及继承类必须要有默认的构造方法
 * @author hao.xiong
 * @version 1.0.1
 */
public class BaseResult implements Serializable {

    /** 程序本身运行发生异常 */
    public final static int APP_RUN_EXCEPTION = -3;

    /** 无法解析服务器返回数据 */
    public final static int UNABLE_PARSE_DATA = -2;

    /** 连接不上服务器 */
    public final static int UNABLE_CONNECT_TO_SERVER = -1;

    /** 通用错误 */
    public final static int ERROR = 0;

    /** 成功 */
    public final static int OK = 1;

	@SerializedName("code")
    private int mCode;

	@SerializedName("msg")
    private String mMessage;

	@SerializedName("ttl")
    private int mTTL;

	@SerializedName("unfreeze_time")
	private long mFreezeTime;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "mCode=" + mCode + ", mMessage='" + mMessage + '\'' + '}';
    }

    /**
     * Default constructor
     */
    public BaseResult() {
    }

    /**
     * Constructor
     * @param code code
     * @param message  message
     */
    public BaseResult(int code, String message) {
        mCode = code;
        mMessage = message;
    }

    /**
     * 获取code码
     * @return 操作码
     */
    public int getCode() {
        return mCode;
    }

    /**
     * 返回数据是否成功
     * @return true if yes
     */
    public boolean isSuccess() {
        return mCode == OK;
    }

    /**
     * 是否返回错误
     * @return true if yes
     */
    public boolean isError() {
        return mCode == ERROR;
    }

    /**
     * 无法连接到服务器
     * @return true if yes
     */
    public boolean isUnableConnectServer() {
        return mCode == UNABLE_CONNECT_TO_SERVER;
    }

    /**
     * 无法解析服务器返回数据
     * @return true if yes
     */
    public boolean isUnableParseResultData() {
        return mCode == UNABLE_PARSE_DATA;
    }

    /**
     * 设置code码
     * @param code code
     */
    public void setCode(int code) {
        this.mCode = code;
    }

    /**
     * 获取Message
     * @return message
     */
    public String getMessage() {
        return mMessage == null ? "" : mMessage;
    }

    /**
     * 设置Message
     * @param message message
     */
    public void setMessage(String message) {
        this.mMessage = message;
    }

     /**
     * 获取ttl
     * <p>ttl代表的是Result数据在服务器上的缓存时间</p>
     * <p>http头中的age信息代表Result数据在服务器上的存活时间</p>
     * <p>Result数据缓存实际的有效时间为: ttl - age. 在这段时期内，客户端没有必要再次向服务器发出请求</p>
     * @return ttl. <b>单位：秒</b>
     */
    public int getTTL() {
        return mTTL;
    }

	public long getFreezeTime() {
		return mFreezeTime;
	}

}

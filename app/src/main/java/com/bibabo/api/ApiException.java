package com.bibabo.api;

import com.bibabo.entity.HttpResult;

/**
 * 异常类
 * 按照自己的接口需求来 不可直接套用
 */
public class ApiException extends RuntimeException {

    public ApiException(HttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(HttpResult httpResult){
        String message = "";
        switch (httpResult.getCode()) {
            case 0:
                message = "ERROR:解析失败";
                break;
            case -1:
                message = "ERROR:服务器通讯故障";
                break;
            default:
                message = "ERROR:网络连接异常";

        }
        return message;
    }
}


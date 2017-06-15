package com.bibabo.api;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *okHttp的配置
 * 缓存已经添加  目前只支持GET请求的缓存  POST的我在找合适的处理方法
 * 也可根据自己的需要进行相关的修改
 */
public class OkHttp3Utils {

    static volatile OkHttp3Utils defaultInstance;
    private static OkHttpClient mOkHttpClient;
    private static Handler mDelivery;

    public static OkHttp3Utils getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new OkHttp3Utils();
                }
            }
        }
        return defaultInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 获取OkHttpClient对象
     */
    public OkHttp3Utils() {
        //同样okhttp3后也使用build设计模式
        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)            // 是否重试
                .connectTimeout(30, TimeUnit.SECONDS)// 连接超时事件
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    /**
     * 同步的Get请求, 返回Response对象
     */
    public Response _getSync(String url) throws IOException {
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 同步的Get请求, 返回string
     */
    public String _getSyncString(String url) throws IOException {
        Response response = _getSync(url);
        return response.body().string();
    }

    /**
     * 异步的Get请求
     */
    public void _getAsync(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);
    }

    /**
     * 同步的Post请求, 返回Response对象
     */
    private Response _postSync(String url, RequestData[] params,
                               RequestData... headers) throws IOException {
        Request request = buildPostRequest(url, params, headers);
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步的Post请求, 返回string
     */
    public String _postSyncString(String url, RequestData[] params,
                                   RequestData... headers) throws IOException {
        Response response = _postSync(url, params, headers);
        return response.body().string();
    }

    /**
     * 异步的post请求
     */
    public void _postAsync(String url, final ResultCallback callback,
                            RequestData[] params, RequestData... headers) {
        Request request = buildPostRequest(url, params, headers);
        deliveryResult(callback, request);
    }

    /**
     * 异步的post请求
     */
    public void _postAsync(String url, final ResultCallback callback,
                            Map<String, String> params, Map<String, String> headers) {
        RequestData[] paramsArr = mapToRequestDatas(params);
        RequestData[] headersArr = mapToRequestDatas(headers);
        Request request = buildPostRequest(url, paramsArr, headersArr);
        deliveryResult(callback, request);
    }

    /**
     * 将Map键值对数据转化为RequestData数组
     */
    private RequestData[] mapToRequestDatas(Map<String, String> params) {
        int index = 0;

        if (params == null) {
            return new RequestData[0];
        }
        int size = params.size();

        RequestData[] res = new RequestData[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            res[index++] = new RequestData(entry.getKey(), entry.getValue());
        }
        return res;
    }

    /**
     * 构建post请求参数
     */
    private Request buildPostRequest(String url, RequestData[] params,
                                     RequestData... headers) {
        if (headers == null) {
            headers = new RequestData[0];
        }
        Headers.Builder headersBuilder = new Headers.Builder();
        for (RequestData header : headers) {
            headersBuilder.add(header.key, header.value);
        }
        Headers requestHeaders = headersBuilder.build();

        if (params == null) {
            params = new RequestData[0];
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (RequestData param : params) {
            formBodyBuilder.add(param.key, param.value);
        }
        RequestBody requestBody = formBodyBuilder.build();
        return new Request.Builder()
                .url(url)
                .headers(requestHeaders)
                .post(requestBody)
                .build();
    }

    /**
     * 调用call.enqueue，将call加入调度队列，执行完成后在callback中得到结果
     */
    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    switch (response.code()) {
                        case 200:
                            sendSuccessResultCallback(response, callback);
                            break;
                        case 500:
                            sendSuccessResultCallback(null, callback);
                            break;
                        default:
                            throw new IOException();
                    }

                } catch (IOException e) {
                    sendFailedStringCallback(call, e, callback);
                }
            }
        });
    }

    /**
     * 调用请求失败对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendFailedStringCallback(final Call call,
                                          final Exception e,
                                          final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(call, e);
            }
        });
    }

    /**
     * 调用请求成功对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendSuccessResultCallback(final Response response,
                                           final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(response);
                }
            }
        });
    }

    public static abstract class ResultCallback {
        public abstract void onError(Call call, Exception e);

        public abstract void onResponse(Response response);
    }

    private static class RequestData {
        String key;
        String value;

        public RequestData() {

        }

        public RequestData(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}

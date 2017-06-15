/*
 * Copyright (c) 2017 SamuelGjk <samuel.alva@outlook.com>
 *
 * This file is part of DiyCode
 *
 * DiyCode is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DiyCode is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DiyCode. If not, see <http://www.gnu.org/licenses/>.
 */

package com.bibabo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */
public class RetrofitUtils {

    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private APIService service;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    private RetrofitUtils() {
        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getDefault().getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(Constant.BASE_URL)
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create(GSON))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        service = mRetrofit.create(APIService.class);
    }

    private static class HolderClass {
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    public static APIService api() {
        return HolderClass.INSTANCE.service;
    }
}

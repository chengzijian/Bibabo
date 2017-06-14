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

import com.bibabo.entity.HttpResult;
import com.bibabo.entity.MainListDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 */
public interface APIService {

    //获取类别列表
    @GET(Constant.METHOD_PATH)
    Observable<HttpResult<List<MainListDto>>> fetchList(@Path("path") String path);

//    @POST(Constant.METHOD_SHOW_USER_INFO)
//    Flowable<UserInfoResult> fetchUserInfo(@Path("token") String accessToken);
//
//    @POST(Constant.METHOD_SENSITIVE_WORD)
//    Flowable<SensitiveWordResult> fetchSensitiveWord();
//
//    @POST(Constant.METHOD_KEY_WORD)
//    Flowable<KeyWordResult> fetchKeyWord();
//
//    @POST(Constant.METHOD_PUBLIC_INFORM)
//    Flowable<PublicInformResult> fetchPublicInform();
//
//    @POST(Constant.METHOD_PROPERTIES_LIST)
//    Flowable<PropertiesListResult> fetchProperties();
//
//    @POST(Constant.METHOD_DAY_LOGIN)
//    @FormUrlEncoded
//    void loginStatistics(@Path("id") String accessToken, @QueryMap Map<String, Object> params
//            , @Field("qd") Object qd, @Field("p") String p, @Field("smid") String smid);
//
//    @POST(Constant.METHOD_DEBUG_SOCKET)
//    void uploadSocketErrorInfo(@Field("info") String info);
//
//    @GET(Constant.METHOD_SEND_SMS_CODE)
//    Flowable<BaseResult> sendSms(@Query("china") String isChina, @Query("mobile") String mobile
//            , @Query("length") int length, @Query("type") int type);
//
//    @POST(Constant.METHOD_USER_EDIT)
//    @FormUrlEncoded
//    Flowable<BaseResult> modifyNickname(@Path("id") String accessToken, @Field("nick_name") String nickName);
//
//    @GET(Constant.METHOD_USER_EDIT)
//    Flowable<BaseResult> modifyUserInfo(@Path("id") String accessToken, @Query("nick_name") String nick_name, @Query("pic") String pic, @Query("sex") int sex,
//                                        @Query("birthday.year") String year, @Query("birthday.month") String month, @Query("birthday.day") String day, @Query("signature") String signature);
//
}

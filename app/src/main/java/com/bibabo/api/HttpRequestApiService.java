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

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 *
 */
public interface HttpRequestApiService {

    @GET(Constant.METHOD_CHILDREN_LIST_PATH)
    Flowable<String> fetchQVChildrenVideoList(@Query("itype") String itype, @Query("offset") String offset);

    @GET(Constant.METHOD_MOVIE_LIST_PATH)
    Flowable<String> fetchVideoPlayList(@Path("vid") String vid);

    @GET
    Flowable<String> fetchFullUrlInfo(@Url String url);
}

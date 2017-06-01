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

package com.bibabo.framework.exception;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bibabo.framework.R;
import com.bibabo.framework.utils.PromptUtils;

import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 */
public class ErrorHandler {

    public static Consumer<Throwable> errorConsumer(final Context context) {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) {
                try {
                    throwable = throwable.getCause();
                    String errorMessage = null;
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        errorMessage = httpException.getMessage();
                        //errorMessage = GSON.fromJson(httpException.response().errorBody().charStream(), Error.class).error;
                    } else if (throwable instanceof UnknownHostException) {
                        errorMessage = context.getString(R.string.internet_error);
                    } else {
                        errorMessage = throwable.getMessage();
                    }
                    PromptUtils.getInstance().showLongToast(errorMessage);
                } catch (Exception e){
                    PromptUtils.getInstance().showLongToast(e.getMessage());
                }
            }
        };
    }
}

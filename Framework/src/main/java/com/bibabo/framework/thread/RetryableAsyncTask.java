/*
 * Copyright (C) 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bibabo.framework.thread;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 支持重试操作的异步类
 * <p>
 * Created by zijian.cheng on 17/4/01
 */
public abstract class RetryableAsyncTask<A, B, C> extends AsyncTask<A, B, C> {

    private static final String TAG = "RetryableAsyncTask";

    public abstract boolean shouldRetry();

    public abstract void onPrepareForRetry();

    public C retrySelf(A[] params) {
        if (shouldRetry()) {
            onPrepareForRetry();
            return doInBackground(params);
        } else {
            Log.d(TAG, "Should not retry, canceling task");

            cancel(true);
            return null;
        }
    }

}

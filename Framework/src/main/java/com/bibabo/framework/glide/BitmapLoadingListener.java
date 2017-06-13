package com.bibabo.framework.glide;

import android.graphics.Bitmap;

/**
 * Created by zijian.cheng on 17/1/3
 * 加载监听接口
 * @version V1.0
 */

public interface BitmapLoadingListener {

    void onSuccess(Bitmap b);

    void onError();
}
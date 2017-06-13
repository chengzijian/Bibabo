package com.bibabo.framework.glide.transformation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by zijian.cheng on 17/1/3
 * RenderScript处理高斯模糊
 * android4.3之后可使用,需要在build.gradle中配置:
 *
 * @version V1.0
 */
public class RSBlur {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static Bitmap blur(Context context, Bitmap blurredBitmap, int radius) throws RSRuntimeException {
        try {
            RenderScript rs = RenderScript.create(context);
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            blur.setInput(input);
            blur.setRadius(radius);
            blur.forEach(output);
            output.copyTo(blurredBitmap);
            rs.destroy();
        } catch (RSRuntimeException e) {
            blurredBitmap = FastBlur.blur(blurredBitmap, radius, true);
        }
        return blurredBitmap;
    }
}
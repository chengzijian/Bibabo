package com.bibabo.framework.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bibabo.framework.BaseApplication;

/**
 * 提示Util总汇
 *
 * @author hao.xiong
 * @version 1.0.0
 */
public class PromptUtils {
    //--- 单例 -----------------------------------------------------------------------------------
    private volatile static PromptUtils mInstance;

    private Context sContext;
    private MaterialDialog sProgressDialog;
    private Toast sToast;

    private PromptUtils() {
        sContext = BaseApplication.getApplication();
        sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
    }

    public static PromptUtils getInstance() {
        if (null == mInstance) {
            synchronized (PromptUtils.class) {
                if (null == mInstance) {
                    mInstance = new PromptUtils();
                }
            }
        }
        return mInstance;
    }

    private void showToast(String message, int duration) {
        sToast.setText(message);
        sToast.setDuration(duration);
        sToast.show();
    }

    /**
     * 显示一个Toast提示
     * @param message  提示信息
     */
    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个Toast提示
     * @param resId    提示信息资源Id
     */
    public void showToast(int resId) {
        showToast(sContext.getString(resId));
    }

    /**
     * 显示一个长时间的Toast提示
     * @param message message
     */
    public void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    /**
     * 显示一个长时间的Toast提示
     * @param resId 提示信息资源Id
     */
    public void showLongToast(Context sContext, int resId) {
        showLongToast(sContext.getString(resId));
    }

    /**
     * 显示一个进度对话框
     *
     */
    public synchronized void showProgressDialog(Context mContext) {
        showProgressDialog(mContext, null, true);
    }

    /**
     * 显示一个进度对话框
     *
     * @param resId   提示信息资源ID
     */
    public synchronized void showProgressDialog(Context mContext, int resId) {
        showProgressDialog(mContext, mContext.getResources().getString(resId), true);
    }

    /**
     * 显示一个进度对话框
     *
     * @param message                      提示信息
     * @param canceledOnTouchOutsideEnable 是否允许触摸对话框以外的地方，关闭对话框
     */
    public synchronized void showProgressDialog(Context mContext, String message, boolean canceledOnTouchOutsideEnable) {
        showProgressDialog(mContext, message, canceledOnTouchOutsideEnable, true);
    }

    /**
     * 显示一个进度对话框
     *
     * @param message 提示信息
     */
    public synchronized void showProgressDialog(Context mContext, String message) {
        showProgressDialog(mContext, message, true, true);
    }

    /**
     * 显示一个进度对话框
     *
     * @param resId                        提示信息资源ID
     * @param canceledOnTouchOutsideEnable 是否允许触摸对话框以外的地方，关闭对话框
     */
    public synchronized void showProgressDialog(Context mContext, int resId, boolean canceledOnTouchOutsideEnable) {
        showProgressDialog(mContext, mContext.getResources().getString(resId), canceledOnTouchOutsideEnable, true);
    }


    /**
     * 显示一个进度对话框
     *
     * @param resId                        提示信息资源ID
     * @param canceledOnTouchOutsideEnable 是否允许触摸对话框以外的地方，关闭对话框
     * @param cancel                       点击back键时，是否关闭对话框
     */
    public synchronized void showProgressDialog(Context mContext, int resId, boolean canceledOnTouchOutsideEnable, boolean cancel) {
        showProgressDialog(mContext, mContext.getString(resId), canceledOnTouchOutsideEnable, cancel);
    }
    /**
     * 显示一个进度对话框
     *
     * @param message                      提示信息
     * @param canceledOnTouchOutsideEnable 是否允许触摸对话框以外的地方，关闭对话框
     * @param cancel                       点击back键时，是否关闭对话框
     */
    public synchronized void showProgressDialog(Context mContext, String message,
                                                       boolean canceledOnTouchOutsideEnable, boolean cancel) {
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null!!");
        }
        try {
            if (sProgressDialog != null) {
                sProgressDialog.dismiss();
                sProgressDialog = null;
            }
            sProgressDialog = new MaterialDialog.Builder(mContext)
                    .content(message)
                    .progress(true, 0)
                    .progressIndeterminateStyle(false)
                    .canceledOnTouchOutside(canceledOnTouchOutsideEnable)
                    .cancelable(cancel)
                    .show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭对话框
     */
    public void dismissProgressDialog() {
        try {
            if (sProgressDialog != null) {
                sProgressDialog.dismiss();
                sProgressDialog = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 进度对话框是否在显示中
     *
     * @return true - 显示中
     */
    public boolean isProgressDialogShowing() {
        return sProgressDialog != null && sProgressDialog.isShowing();
    }

    /**
     * 设置进度对话框关闭监听
     *
     * @param listener 进度对话框关闭监听
     */
    public void setProgressDialogDismissListener(DialogInterface.OnDismissListener listener) {
        if (sProgressDialog != null && listener != null) {
            sProgressDialog.setOnDismissListener(listener);
        }
    }
}

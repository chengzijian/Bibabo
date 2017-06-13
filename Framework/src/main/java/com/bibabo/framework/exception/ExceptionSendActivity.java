package com.bibabo.framework.exception;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bibabo.framework.R;
import com.bibabo.framework.fragmentation.SupportActivity;
import com.bibabo.framework.utils.AppManager;
import com.bibabo.framework.utils.PromptUtils;

/**
 * @author hao.xiong
 * @version 1.0.0
 */
public class ExceptionSendActivity extends SupportActivity {
    /**
     * 该Activity的action *
     */
    public static final String ACTION_EXCEPTION_SEND = "com.bibabo.ExceptionSend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String title = this.getIntent().getCharSequenceExtra(Intent.EXTRA_SUBJECT).toString();
        final String message = this.getIntent().getCharSequenceExtra(Intent.EXTRA_TEXT).toString();
        new MaterialDialog.Builder(this)
                .content(R.string.exception_dialog_message)
                .positiveText(R.string.ok)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        MobclickAgent.reportError(ExceptionSendActivity.this
//                                , new Throwable(new StringBuilder(title).append("\n").append(message).toString()));
                        AppManager.getAppManager().finishAllActivity();
//                        PromptUtils.getInstance().showToast(R.string.exception_send_success);
                        PromptUtils.getInstance().showToast("异常并未上报，待修改");
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppManager.getAppManager().finishAllActivity();
                    }
                })
                .show();
    }
}

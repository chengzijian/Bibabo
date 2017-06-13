package com.bibabo.framework.exception;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;

import com.bibabo.framework.utils.EnvironmentUtils;
import com.bibabo.framework.utils.LogUtils;
import com.bibabo.framework.utils.StringUtils;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异常捕获类
 *
 *
 * @author kun.zhang
 * @version 1.0.0
 */
public class ExceptionReporter {
    /**进入发送错误报告的消息.*/
    private static final int BYTES_PER_MB = 1024 * 1024;
    private static final String LOG_TAG = "ExceptionReporter";

    /**
     * 创建异常，测试用
     */
    public static void createException() {
        throw new OutOfMemoryError();
    }

    /**
     * 捕获未知异常
     *
     * @param context 主窗口句柄，不为空则弹出发送错误报告对话框
     * @param action  启动activity的action名称
     */
    public static void init(final Context context, final String action) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable ex) {
                Writer result = new StringWriter();
                ex.printStackTrace(new PrintWriter(result));
                StringBuilder exceptionResult = new StringBuilder(result.toString());
                exceptionResult.append(new Gson().toJson(buildReportParameters()));
                //如果用LogUtils.e那么发布出去的关闭日志的版本将无法看到用户的崩溃日志
                LogUtils.e(LOG_TAG, "Application_Crash_Exception:\n" + exceptionResult);
                if (context != null) {
                    Intent intent = new Intent(action);
                    intent.putExtra(Intent.EXTRA_SUBJECT, ex.toString());
                    intent.putExtra(Intent.EXTRA_TEXT, exceptionResult.toString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                System.exit(0);
            }
        });
    }

    public static ExceptionResult buildReportParameters() {
        ExceptionResult exceptionResult = new ExceptionResult();
        exceptionResult.setAppChannel(EnvironmentUtils.Config.getChannel());
        exceptionResult.setAppVersion(EnvironmentUtils.Config.getAppVersion());
        exceptionResult.setErrorTime(currentDate());
        exceptionResult.setMemory(memoryInfo());
        exceptionResult.setPackageName(EnvironmentUtils.getPackageName());
        exceptionResult.setMid(Build.MANUFACTURER + "#" + Build.MODEL);
        exceptionResult.setRom(Build.PRODUCT);
        exceptionResult.setSplus(Build.VERSION.RELEASE);
        String ip = EnvironmentUtils.Network.ipv4();
        if (StringUtils.isEmpty(ip)) {
            ip = EnvironmentUtils.Network.ipv6();
        }
        exceptionResult.setIpAddress(ip);
        return exceptionResult;
    }

    /**
     * 获取当前程序内存使用
     *
     * @return 返回内存使用
     */
    private static String memoryInfo() {
        double totalMem = (double) Runtime.getRuntime().totalMemory() / BYTES_PER_MB;
        double freeMem = (double) Runtime.getRuntime().freeMemory() / BYTES_PER_MB;
        double maxMem = (double) Runtime.getRuntime().maxMemory() / BYTES_PER_MB;
        double allocatedMem = (double) Debug.getNativeHeapAllocatedSize() / BYTES_PER_MB;

        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        StringBuilder stringBuilder = new StringBuilder("Runtime:{total:") .append(decimalFormat.format(totalMem))
                .append(",free:") .append(decimalFormat.format(freeMem))
                .append(",max:").append(decimalFormat.format(maxMem))
                .append("},HeapAllocated:").append(decimalFormat.format(allocatedMem));

        return stringBuilder.toString();
    }

    private static String currentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}

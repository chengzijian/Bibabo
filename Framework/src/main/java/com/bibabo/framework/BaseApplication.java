package com.bibabo.framework;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;

import com.bibabo.framework.utils.AppManager;

import io.reactivex.plugins.RxJavaPlugins;

import static com.bibabo.framework.exception.ErrorHandler.errorConsumer;

/**
 * Created by zijian.cheng on 2017/6/1.
 */
public abstract class BaseApplication extends Application {

    /**
     * Application实例
     */
    public abstract void exitProcess();

    /**
     * Application实例
     */
    protected static BaseApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

//        if (EnvironmentUtils.Config.isTestMode() && SDKVersionUtils.hasHoneycomb()) {
//            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//            builder.detectLeakedSqlLiteObjects()
//                    .penaltyLog()
//                    .penaltyDeath();
//            builder.detectLeakedClosableObjects();
//            StrictMode.setVmPolicy(builder.build());
//        }
//        EnvironmentUtils.init(this);
//
//        DisplayUtils.init(this);
//        Preferences.init(this);
//
//        ShowConfig.init(this);
//
//        Cache.open(this);
//
//        LogUtils.setEnable(EnvironmentUtils.Config.isLogEnable());
//        //设置LogUtils log日志路目录路径
//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
//        LogUtils.setLogFileFolder(path);
//        LogUtils.setWriteToFile(EnvironmentUtils.Config.isLogEnable());
//
//        StorageUtils.initSqlite(this, new Class[]{TaskInfo.class}, ShowConfig.DB_NAME, ShowConfig.DB_VERSION_V_1_6,
//                new SqliteStorageImpl.Callback() {
//                    @Override
//                    public void onUpgrade(int oldVersion, int newVersion) {
//                        if (oldVersion <= ShowConfig.DB_VERSION_V_1_4) {
//                            StorageUtils.getSqliteStorage().clearTable(TaskInfo.class);
//                        }
//                    }
//                });
//
//        ModuleManager.instance().init();
//
//        ExceptionReporter.init(this, ExceptionSendActivity.ACTION_EXCEPTION_SEND);
//
//        Preferences.edit().remove(SharedPreferenceKey.WIFI_TIP_DONE).apply();

        RxJavaPlugins.setErrorHandler(errorConsumer(this));
    }

    /**
     * 获取应用实例
     *
     * @return 应用实例
     */
    public static BaseApplication getApplication() {
        return sApplication;
    }

    /**
     * 在millSeconds后退出
     *
     * @param millSeconds millSeconds
     */
    public static void exitInMillSeconds(long millSeconds) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getApplication().exit();
            }
        }, millSeconds);
    }

    /**
     * 退出应用程序
     */
    public void exit() {
//        CommandCenter.instance().exeCommand(new Command(CommandID.DISCONNECT_SOCKET));
//        AppDataManager.getInstance(this).closeDB();
//        ShowConfig.exit();
        exitProcess();
        AppManager.getAppManager().AppExit(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try {
            System.gc();
        } catch (Exception e) { }
    }

}

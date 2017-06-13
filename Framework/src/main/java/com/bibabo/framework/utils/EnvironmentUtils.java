package com.bibabo.framework.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.bibabo.framework.BaseApplication;
import com.bibabo.framework.config.ConstantUtils;
import com.bibabo.framework.thread.TaskScheduler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Environment utility class
 *
 * @author hao.xiong
 * @version 1.0.0
 */
public class EnvironmentUtils {
    private static final String TAG = "EnvironmentUtils";

    private static String mPackageName;
    private static String mSDCardPath;
    private static String mSecondSDCardPath;
    private static String mSecondSDCardValidFolder;

    static {
        String path = "sdcard";
        File pathFile = new File(path);
        if (!pathFile.exists() || !pathFile.canWrite()) {
            pathFile = Environment.getExternalStorageDirectory();
        }
        try {
            path = pathFile.getCanonicalPath();
        } catch (Exception ignored) {
            path = pathFile.getAbsolutePath();
        }
        mSDCardPath = path;
    }

    /**
     * 初始化系统环境
     *
     * @param context 系统环境上下文
     */
    public static void init(Context context) {
        Config.init(context);
        Network.init(context);
        GeneralParameters.init(context);
        mPackageName = context.getPackageName();
        resetAsyncTaskDefaultExecutor();
    }

    /**
     * 判断手机系统是否是魅族
     * @return true - 是
     */
    public static boolean isFlymeOs() {
        String displayId = Build.DISPLAY;
        return displayId.contains("Flyme");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void resetAsyncTaskDefaultExecutor() {
        if (SDKVersionUtils.hasHoneycomb()) {
            try {
                Method setDefaultExecutorMethod = AsyncTask.class.getMethod("setDefaultExecutor", Executor.class);
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
                setDefaultExecutorMethod.invoke(null, threadPoolExecutor);

                final Field defaultHandler = ThreadPoolExecutor.class.getDeclaredField("defaultHandler");
                defaultHandler.setAccessible(true);
                defaultHandler.set(null, new ThreadPoolExecutor.DiscardOldestPolicy());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取包名
     *
     * @return 包名
     */
    public static String getPackageName() {
        return mPackageName;
    }

    /**
     * 存储信息
     */
    public static class Storage {

        private static final String TTPOD_NAME_IN_LOWER_CASE = "iwan";
        /**
         * 外部存储是否可读写
         *
         * @return 可读写返回true, 否则返回false
         */
        public static boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }

        /**
         * 外部存储是否可读
         *
         * @return 可读返回true, 否则返回false
         */
        public static boolean isExternalStorageReadable() {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state)
                    || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
        }

        /**
         * 获取目录可用字节数，目录不存在返回0
         *
         * @param path 目录文件
         * @return 字节数
         */
        public static long getUsableSpace(File path) {
            if (SDKVersionUtils.hasGingerbread()) {
                return path.getUsableSpace();
            }
            final StatFs stats = new StatFs(path.getPath());
            return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
        }

        /**
         * 获取外部目录缓存路径
         *
         * @param context context
         * @return 外部存储换成路径
         */
        public static File getExternalCacheDir(Context context) {
            File file = null;
            if (SDKVersionUtils.hasFroyo()) {
                file = context.getExternalCacheDir();
            }

            if (file == null) {
                final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
                file = new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
            }

            file.mkdirs();

            if (file.isDirectory()) {
                return file;
            }

            return null;
        }

        /**
         * 获取SDCard路径
         *
         * @return sdcard路径，不为空
         */
        public static String getSDCardPath() {
            return mSDCardPath;
        }

        /**
         * 获取缓存路径
         *
         * @param context context
         * @return 存储路径
         */
        public static String getCachePath(Context context) {
            File file = null;
            if (isExternalStorageWritable()) {
                file = getExternalCacheDir(context);
            }
            return file != null ? file.getAbsolutePath() : context.getCacheDir().getAbsolutePath();
        }

        /**
         * 判断是否路径是否可写
         * @param path path
         * @param context context
         * @return writable
         */
        public static boolean isWritablePath(Context context, String path) {
            if (!new File(path).canWrite()) {
                return false;
            }
            String filePathPrefix = path + File.separator;
            int i = 0;
            while (FileUtils.fileExists(filePathPrefix + i)) {
                i++;
            }
            File testFile = FileUtils.createFile(filePathPrefix + i);

            if (testFile != null) {
                testFile.delete();
                return true;
            }

            return false;
        }

        /**
         * 得到可用路径
         * @param context context
         * @return writablePath
         */
        public static String getWritablePath(Context context) {
            if (mSecondSDCardPath == null) {
                getSecondSdCardPath(context);
            }
            if (!StringUtils.isEmpty(mSecondSDCardPath) && FileUtils.exists(mSecondSDCardPath)) {
                return SDKVersionUtils.hasKitKat() ? mSecondSDCardValidFolder : mSecondSDCardPath + File.separator + TTPOD_NAME_IN_LOWER_CASE;
            } else {
                return mSDCardPath + File.separator + TTPOD_NAME_IN_LOWER_CASE;
            }
        }

        private static String getSecondSdCardAppFolder(Context context) {
            if (mSecondSDCardValidFolder != null) {
                return mSecondSDCardValidFolder;
            }
            File[] externalDataDirectories = ContextCompat.getExternalFilesDirs(context, null);
            if (externalDataDirectories == null || externalDataDirectories.length < 2 || externalDataDirectories[1] == null) {
                mSecondSDCardValidFolder = ConstantUtils.BLANK_STRING;
            } else {
                try {
                    mSecondSDCardValidFolder = externalDataDirectories[1].getCanonicalPath().replaceAll(File.separator + "files", "");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (mSecondSDCardValidFolder == null) {
                        mSecondSDCardValidFolder = ConstantUtils.BLANK_STRING;
                    }
                }
            }
            return mSecondSDCardValidFolder;
        }

        private static String getSecondSdCardPath(Context context) {
            if (mSecondSDCardValidFolder == null) {
                getSecondSdCardAppFolder(context);
            }
            if (mSecondSDCardPath != null) {
                return mSecondSDCardPath;
            }
            if (StringUtils.isEmpty(mSecondSDCardValidFolder)) {
                mSecondSDCardPath = ConstantUtils.BLANK_STRING;
                return mSecondSDCardPath;
            }
            StringBuffer sb = new StringBuffer(File.separator);
            sb.append("Android");
            sb.append(File.separator);
            sb.append("data");
            sb.append(File.separator);
            sb.append(mPackageName);
            mSecondSDCardPath = mSecondSDCardValidFolder.replaceAll(sb.toString(), "");
            return mSecondSDCardPath;
        }
    }
//
//    /**
//     * CPU信息
//     */
//    public static class CPU {
//        /** Android CPU Families */
//        /**
//         * Unknown cpu family
//         */
//        public static final int CPU_FAMILY_UNKNOWN = 0;
//        /**
//         * ARM cpu family
//         */
//        public static final int CPU_FAMILY_ARM = 1;
//        /**
//         * X86 cpu family
//         */
//        public static final int CPU_FAMILY_X86 = 2;
//        /**
//         * MIPS cpu family
//         */
//        public static final int CPU_FAMILY_MIPS = 3;
//
//        /** ARM cpu features */
//        /**
//         * ARM_FEATURE_VFP3
//         */
//        public static final int ARM_FEATURE_VFP3 = (1 << 1);
//        /**
//         * ARM_FEATURE_NEON
//         */
//        public static final int ARM_FEATURE_NEON = (1 << 2);
//        /**
//         * ARM_FEATURE_VFP2
//         */
//        public static final int ARM_FEATURE_VFP2 = (1 << 4);
//        /**
//         * ARM_FEATURE_VFP
//         */
//        public static final int ARM_FEATURE_VFP = ARM_FEATURE_VFP2 | ARM_FEATURE_VFP3;
//
//        /** X86 cpu features */
//        /**
//         * X86_FEATURE_SSE3
//         */
//        public static final int X86_FEATURE_SSE3 = (1 << 0);
//        /**
//         * X86_FEATURE_POPCNT
//         */
//        public static final int X86_FEATURE_POPCNT = (1 << 1);
//        /**
//         * X86_FEATURE_MOVBE
//         */
//        public static final int X86_FEATURE_MOVBE = (1 << 2);
//
//        /** ARM architecture */
//        /**
//         * ARM architecture unknown
//         */
//        public static final int ARM_ARCH_UNKNOWN = 0;
//        /**
//         * ARM architecture v5
//         */
//        public static final int ARM_ARCH_5 = 5;
//        /**
//         * ARM architecture v6
//         */
//        public static final int ARM_ARCH_6 = 6;
//        /**
//         * ARM architecture v7
//         */
//        public static final int ARM_ARCH_7 = 7;
//        static {
//            try {
//                System.loadLibrary("environmentutils_cpu");
//            } catch (UnsatisfiedLinkError error) {
//                error.printStackTrace();
//            }
//        }
//
//        /**
//         * 获取CPU family
//         *
//         * @return {@link #CPU_FAMILY_ARM}, {@link #CPU_FAMILY_X86} or {@link #CPU_FAMILY_MIPS}
//         */
//        public static native int cpuFamily();
//
//        /**
//         * 获取CPU feature
//         *
//         * @return {@link #CPU_FAMILY_ARM}返回ARM_FEATURE_XXX, {@link #CPU_FAMILY_X86}返回X86_FEATURE_XXX
//         */
//        public static native int cpuFeatures();
//
//        /**
//         * 返回ARM architecture
//         *
//         * @return {@link #CPU_FAMILY_ARM}返回{@link #ARM_ARCH_5}, {@link #ARM_ARCH_6} or {@link #ARM_ARCH_7}, 否则返回{@link #ARM_ARCH_UNKNOWN}
//         */
//        public static native int armArch();
//    }

    /**
     * 网络信息
     */
    public static class Network {
        /**
         * 无网络
         */
        public static final int NETWORK_INVALID = -1;
        /**
         * 2G网络
         */
        public static final int NETWORK_2G = 0;
        /**
         * wap网络
         */
        public static final int NETWORK_WAP = 1;
        /**
         * wifi网络
         */
        public static final int NETWORK_WIFI = 2;
        /**
         * 3G和3G以上网络，或统称为快速网络
         */
        public static final int NETWORK_3G = 3;

        private static final int[] NETWORK_MATCH_TABLE = {NETWORK_2G // NETWORK_TYPE_UNKNOWN
                , NETWORK_2G // NETWORK_TYPE_GPRS
                , NETWORK_2G // NETWORK_TYPE_EDGE
                , NETWORK_3G // NETWORK_TYPE_UMTS
                , NETWORK_2G // NETWORK_TYPE_CDMA
                , NETWORK_3G // NETWORK_TYPE_EVDO_O
                , NETWORK_3G // NETWORK_TYPE_EVDO_A
                , NETWORK_2G // NETWORK_TYPE_1xRTT
                , NETWORK_3G // NETWORK_TYPE_HSDPA
                , NETWORK_3G // NETWORK_TYPE_HSUPA
                , NETWORK_3G // NETWORK_TYPE_HSPA
                , NETWORK_2G // NETWORK_TYPE_IDEN
                , NETWORK_3G // NETWORK_TYPE_EVDO_B
                , NETWORK_3G // NETWORK_TYPE_LTE
                , NETWORK_3G // NETWORK_TYPE_EHRPD
                , NETWORK_3G // NETWORK_TYPE_HSPAP
        };

        private static String mIMEI = "";
        private static String mIMSI = "";
        private static String mWifiMac = "";

        private static NetworkInfo mNetworkInfo;
        private static int mDefaultNetworkType;
        private static ConnectivityManager mConnectManager;

        /**
         * 初始化默认网络参数
         *
         * @param context 上下文环境
         */
        public static void init(final Context context) {
            mDefaultNetworkType = NETWORK_MATCH_TABLE[telephonyNetworkType(context)];
            mConnectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnectManager.getActiveNetworkInfo();
        }

        public static void readPhoneState(final Context context){
            int hasWriteContactsPermission = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hasWriteContactsPermission = context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }

            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            mIMEI = telephonyManager.getDeviceId();
            if (mIMEI == null) {
                mIMEI = "";
            }

            mIMSI = telephonyManager.getSubscriberId();
            if (mIMSI == null) {
                mIMSI = "";
            }

            TaskScheduler.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mWifiMac = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
                    } catch (Exception e) { //有些机型调用getConnectionInfo方法会报异常
                        e.printStackTrace();
                    }
                    if (mWifiMac == null) {
                        mWifiMac = "";
                    }
                }
            });
        }


        /**
         * 获取IMEI串号
         *
         * @return IMEI串号。<b>有可能为空值</b>
         */
        public static String imei() {
            return mIMEI;
        }

        /**
         * 获取IMSI移动用户识别码
         *
         * @return IMSI移动用户识别码。<b>有可能为空值</b>
         */
        public static String imsi() {
            return mIMSI;
        }

        /**
         * 获取Wifi Mac地址
         *
         * @return Wifi Mac地址
         */
        public static String wifiMac() {
            return mWifiMac;
        }

        /**
         * 获取网络类型
         *
         * @return 网络类型
         */
        public static int type() {
            int networkType = mDefaultNetworkType;
            if (mConnectManager == null) {
                //当还未来得及初始化时，另一线程请求网络时通用参数中取此值时先运行到这儿，那么如不做处理将崩溃
                return NETWORK_WAP;
            }
            mNetworkInfo = mConnectManager.getActiveNetworkInfo();
            if (!networkConnected(mNetworkInfo)) {
                networkType = NETWORK_INVALID;
            } else if (isWifiNetwork(mNetworkInfo)) {
                networkType = NETWORK_WIFI;
            } else if (isWapNetwork(mNetworkInfo)) {
                networkType = NETWORK_WAP;
            }

            return networkType;
        }

        /**
         * 是否存在有效的网络连接.
         *
         * @return 存在有效的网络连接返回true，否则返回false
         */
        public static boolean isNetWorkAvailable() {
            return networkConnected(mConnectManager.getActiveNetworkInfo());
        }

        /**
         * 获取本机IPv4地址
         *
         * @return 本机IPv4地址
         */
        public static String ipv4() {
            return ipAddress(true);
        }

        /**
         * 获取本机IPv6地址
         *
         * @return 本机IPv6地址
         */
        public static String ipv6() {
            return ipAddress(false);
        }

        private static boolean isIPv4Address(String address) {
            boolean ret = false;

            if (!TextUtils.isEmpty(address)) {
                //192.168.0.1
                try {
                    String[] numArray = address.split("\\.");
                    if (numArray.length == 4) {
                        ret = true;
                    }
                } catch (Exception e) {}
            }

            return ret;
        }

        private static String ipAddress(boolean useIPv4) {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface netInterface = en.nextElement();
                    for (Enumeration<InetAddress> iNetEnum = netInterface.getInetAddresses(); iNetEnum.hasMoreElements();) {
                        InetAddress inetAddress = iNetEnum.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String address = inetAddress.getHostAddress();
                            if (useIPv4 == isIPv4Address(address)) {
                                return address;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        /**
         * 直接从系统函数里得到的network type
         *
         * @param context context
         * @return net type
         */
        private static int telephonyNetworkType(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = telephonyManager.getNetworkType();
            if (networkType < 0 || networkType >= NETWORK_MATCH_TABLE.length) {
                networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
            }
            return networkType;
        }

        private static boolean networkConnected(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }

        private static boolean isMobileNetwork(NetworkInfo networkInfo) {
            return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }

        @SuppressWarnings("deprecation")
        private static boolean isWapNetwork(NetworkInfo networkInfo) {
            return isMobileNetwork(networkInfo) && !StringUtils.isEmpty(android.net.Proxy.getDefaultHost());
        }

        private static boolean isWifiNetwork(NetworkInfo networkInfo) {
            return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
    }

    /**
     * @author hao.xiong
     * @version 7.0.0
     *          配置信息，来自与assets/config，build，channel文件
     */
    public static class Config {
        private static final String TAG = "Config";

        private static final String ASSET_PATH_CONFIG = "config";
        private static final String ASSET_PATH_CHANNEL = "channel";
        private static final String ASSET_PATH_BUILD = "build";

        /**
         * 预定的几个通用键值
         */
        private static final String XML_ATTRIBUTE_VALUE_APP_VERSION = "app_version";
        private static final String XML_ATTRIBUTE_VALUE_VERSION_NAME = "version_name";
        private static final String XML_ATTRIBUTE_VALUE_URL_PRINT_ENABLE = "url_print_enable";
        private static final String XML_ATTRIBUTE_VALUE_TEST_MODE = "test_mode";
        private static final String XML_ATTRIBUTE_VALUE_LOG_ENABLE = "log_enable";

        private static final int RADIX_16 = 16;

        private static String mAppVersion = "";
        private static String mVersionName = "";
        private static boolean mUrlPrintEnable = false;
        private static boolean mTestMode = false;
        private static boolean mLogEnable = true;
        private static String mChannel = "";

        private static Map<String, String> mConfigMap;
        private static boolean mInitialized;

        /**
         * 初始化配置文件
         *
         * @param context 上下文
         */
        public static void init(Context context) {
            initConfig(context);
        }

        private static void initConfig(Context context) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(ASSET_PATH_CONFIG);
                mConfigMap = XmlUtils.parse(inputStream);
                parseConfig();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static void parseConfig() {
            try {
                mAppVersion = mConfigMap.get(XML_ATTRIBUTE_VALUE_APP_VERSION);
                mVersionName = mConfigMap.get(XML_ATTRIBUTE_VALUE_VERSION_NAME);
                mUrlPrintEnable = parseBoolean(mConfigMap.get(XML_ATTRIBUTE_VALUE_URL_PRINT_ENABLE), false);
                mTestMode = parseBoolean(mConfigMap.get(XML_ATTRIBUTE_VALUE_TEST_MODE), false);
                mLogEnable = parseBoolean(mConfigMap.get(XML_ATTRIBUTE_VALUE_LOG_ENABLE), true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            LogUtils.i(TAG, "AppVersion:" + mAppVersion);
            LogUtils.i(TAG, "VersionName:" + mVersionName);
            LogUtils.i(TAG, "UrlPrintEnable:" + mUrlPrintEnable);
            LogUtils.i(TAG, "TestMode:" + mTestMode);
            LogUtils.i(TAG, "LogEnable:" + mLogEnable);
        }

        /**
         * 设置渠道号
         *
         * @param channel channel
         */
        public static void setChannel(String channel) {
            mChannel = channel;
        }


        /**
         * 获取渠道信息
         *
         * @return 渠道信息
         */
        public static String getChannel() {
            return mChannel;
        }

        /**
         * 获取程序版本信息
         *
         * @return 程序版本信息
         */
        public static String getAppVersion() {
            return mAppVersion;
        }

        /**
         * 返回版本号
         * @return 版本号
         */
        public static String getProcessAppVersion() {
            if (!StringUtils.isEmpty(mAppVersion)) {
                final int dateTimeLen = 11;
                String appVersion = new EnvironmentUtils.Config().getAppVersion();
                int length = appVersion.length() - dateTimeLen;

                if(length > 0){
                    String currVersion = appVersion.substring(0, length);
                    if (currVersion.endsWith(".")) {
                        currVersion = currVersion.substring(0, currVersion.length() - 1);
                    }
                    return currVersion;
                }
            }
            return "0.0.0";
        }

        /**
         * 获取版本类型名称，alpha,beta,release
         *
         * @return 版本类型名称
         */
        public static String getVersionName() {
            return mVersionName;
        }

        /**
         * 是否允许打印交互地址
         *
         * @return 是否允许打印交互地址
         */
        public static boolean isUrlPrintEnable() {
            return mUrlPrintEnable;
        }

        /**
         * 是否允许打Log
         *
         * @return 是否允许打Log
         */
        public static boolean isLogEnable() {
            return mLogEnable;
        }

        /**
         * 是否让程序在测试模式下运行
         *
         * @return 是否让程序在测试模式下运行
         */
        public static boolean isTestMode() {
            return mTestMode;
        }

        private static int optInt(String string) {
            return parseInt(string, 0);
        }

        private static int parseInt(String string, int defaultValue) {
            try {
                string = string.trim();
                if (string.startsWith("#")) {
                    return (int) Long.parseLong(string.substring(1), RADIX_16);
                } else if (string.startsWith("0x") || string.startsWith("0X")) {
                    return (int) Long.parseLong(string.substring(2), RADIX_16);
                } else {
                    return (int) Long.parseLong(string);
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            return defaultValue;
        }

        private static boolean parseBoolean(String string, boolean defaultValue) {
            if ("false".equalsIgnoreCase(string)) {
                return false;
            } else if ("true".equalsIgnoreCase(string)) {
                return true;
            }
            return defaultValue;
        }
    }


    /**
     * @author hao.xiong
     * @version 7.0.0
     */
    public static class GeneralParameters {

        /**
         * mac key
         */
        public static final String KEY_MAC = "mac";   //mac
        /**
         * device_id key
         */
        public static final String KEY_DEVICE_ID = "device_id";   //device_id
        /**
         * uid key
         */
        public static final String KEY_UID = "uid";   //唯一
        /**
         * hid key
         */
        public static final String KEY_WIFI_MAC = "hid";
        /**
         * mid key
         */
        public static final String KEY_MACHINE_ID = "mid";
        /**
         * imsi key
         */
        public static final String KEY_IMSI = "imsi";
        /**
         * platform key
         */
        public static final String KEY_PLATFORM_ID = "s";
        /**
         * splus key
         */
        public static final String KEY_ROM_VERSION = "splus";
        /**
         * rom key
         */
        public static final String KEY_ROM_FINGER_PRINTER = "rom";
        /**
         * app version key
         */
        public static final String KEY_APP_VERSION = "v";
        /**
         * channel key
         */
        public static final String KEY_CHANNEL_ID = "f";
        /**
         * net key
         */
        public static final String KEY_NETWORK_TYPE = "net";
        /**
         * st key
         */
        public static final String KEY_SERVICE_TYPE = "st";
        /**
         * active key
         */
        public static final String KEY_ACTIVE = "active";
        /**
         * app key
         */
        public static final String KEY_APP = "app";
        /**
         * openudid key
         */
        public static final String KEY_OPENUDID = "openudid";

        private static final String ANDROID_PLATFORM = "200";
        private static final String ACTIVE_FLAG_FILE = "flag";

        private static HashMap<String, Object> mParameters = new HashMap<String, Object>();
        private static JSONObject mJsonParameter;

        /**
         * 初始化通用参数
         *
         * @param context 环境上下文
         */
        public static void init(Context context) {
            try {
                String wifiMac = EnvironmentUtils.Network.wifiMac().replaceAll("[-:]", "");
                mParameters.put(KEY_WIFI_MAC, SecurityUtils.TEA.encrypt(wifiMac));
                String imei = EnvironmentUtils.Network.imei();
                String uid = StringUtils.isEmpty(imei) ? wifiMac : imei;
                if (StringUtils.isEmpty(uid)) {
                    uid = PreferenceManager.getDefaultSharedPreferences(context).getString("device_id", UUID.randomUUID().toString());
                }
                mParameters.put(KEY_UID, uid);
                mParameters.put(KEY_MACHINE_ID, URLEncoder.encode(Build.MODEL, "UTF-8"));
                mParameters.put(KEY_IMSI, URLEncoder.encode(EnvironmentUtils.Network.imsi(), "UTF-8"));
                mParameters.put(KEY_PLATFORM_ID, KEY_PLATFORM_ID + ANDROID_PLATFORM);
                mParameters.put(KEY_ROM_VERSION, URLEncoder.encode(Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT, "UTF-8"));
                mParameters.put(KEY_ROM_FINGER_PRINTER, URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                mParameters.put(KEY_APP_VERSION, KEY_APP_VERSION + Config.getAppVersion());
                mParameters.put(KEY_CHANNEL_ID, Config.getChannel());
                mParameters.put(KEY_ACTIVE, isActive(context) ? 1 : 0);
                mParameters.put(KEY_NETWORK_TYPE, 0);
                List<String> list = StringUtils.splitToStringList(context.getPackageName(), ".");
                int listSize = list.size();
                if (listSize > 0) {
                    mParameters.put(KEY_APP, list.get(--listSize));
                }

                String mac = EnvironmentUtils.Network.wifiMac();
                mParameters.put(KEY_MAC, mac);
                String deviceId = EnvironmentUtils.Network.imei();
                if (StringUtils.isEmpty(deviceId)) {
                    deviceId = mac;
                }
                if (StringUtils.isEmpty(deviceId)) {
                    deviceId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                }
                mParameters.put(KEY_DEVICE_ID, deviceId);
                mJsonParameter = new JSONObject(mParameters);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取平台号
         *
         * @return 返回平台号
         */
        public static String getPlatformId() {
            return (String) mParameters.get(KEY_PLATFORM_ID);
        }

        /**
         * 获取版本号
         *
         * @return 返回版本号
         */
        public static String getAppVersion() {
            return (String) mParameters.get(KEY_APP_VERSION);
        }

        /**
         * 获取设备号
         *
         * @return 返回设备号
         */
        public static String getDeviceId() {
            return (String) mParameters.get(KEY_DEVICE_ID);
        }

        /**
         * 获取mac
         *
         * @return 返回mac
         */
        public static String getMac() {
            return (String) mParameters.get(KEY_MAC);
        }

        /**
         * 获取渠道号
         *
         * @return 返回渠道号
         */
        public static String getFromId() {
            return (String) mParameters.get(KEY_CHANNEL_ID);
        }

        /**
         * 获取通用参数
         *
         * @return 通用参数
         */
        public static HashMap<String, Object> parameters() {
            mParameters.put(KEY_NETWORK_TYPE, EnvironmentUtils.Network.type());
            return mParameters;
        }

        /**
         * 获取通用参数
         *
         * @return 以&符号相连的url参数形式的通用参数
         */
        public static String parameter() {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Object> entry : parameters().entrySet()) {
                if (builder.length() > 0) {
                    builder.append('&');
                }
                builder.append(entry.getKey()).append('=').append(entry.getValue());
            }
            return builder.toString();
        }

        /**
         * 获取通用参数
         *
         * @return 通用参数
         */
        public static JSONObject jsonParameter() {
            try {
                mJsonParameter.put(KEY_NETWORK_TYPE, EnvironmentUtils.Network.type());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mJsonParameter;
        }

        /**
         * 获取数盟DNA
         *
         * @return 加密后的数盟DNA
         */
        public static String getDna() {
            String deviceID = "";
            SharedPreferences sp = BaseApplication.getApplication().getSharedPreferences(getPackageName() + "_dna"
                    , BaseApplication.getApplication().MODE_PRIVATE);
            if (sp != null) {
                deviceID = Base64Coder.encodeString(sp.getString("device_id", ""));
            }
            return deviceID;
        }

        private static boolean isActive(Context context) {
            boolean active = false;
            try {
                openPrivateFile(context, ACTIVE_FLAG_FILE);
            } catch (Exception ignored) {
                createPrivateFile(context, ACTIVE_FLAG_FILE);
                active = true;
            }
            return active;
        }

        private static void createPrivateFile(Context context, String privateFile) {
            try {
                FileOutputStream fileOutputStream = context.openFileOutput(privateFile, Context.MODE_PRIVATE);
                closeStream(fileOutputStream);
            } catch (Exception eOutputStream) {
                // 这里使用Exception捕获异常是为了在因为在某些机型上不仅仅抛出FileNotFoundException
                // 比如联想K800 4.0.4可能出现未知原因的NullPointerException
                eOutputStream.printStackTrace();
            }
        }

        private static void openPrivateFile(Context context, String privateFile) throws FileNotFoundException {
            FileInputStream fileInputStream = context.openFileInput(privateFile);
            closeStream(fileInputStream);
        }

        private static void closeStream(Closeable stream) {
            if (stream == null) {
                return;
            }

            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.bibabo.framework.config;

import android.content.Context;

import com.bibabo.framework.utils.EnvironmentUtils;

import java.io.File;

/**
 * 通用的设置信息
 *
 * @author guo.chen
 * @version 1.0.0
 */
public class ShowConfig {

//    public static final int DEFAULT_SIZE = 20;
//    /**
//     * 链接url *
//     */
//    public static final String INTENT_CLICK_URL = "click_url";
//    /**
//     * 标题 *
//     */
//    public static final String INTENT_TITLE = "title";
//
//    /**
//     * 类型 *
//     */
//    public static final String INTENT_TYPE = "type";
//
//    /**
//     * 数据库名称
//     */
//    public static final String DB_NAME = "show.db";
//    /**
//     * 版本 1.2
//     */
//    public static final int DB_VERSION_V_1_2 = 1;
//    /**
//     * 版本 1.3
//     */
//    public static final int DB_VERSION_V_1_3 = 16777216; //TaskInfo.DB_VERSION;
//    /**
//     * 版本 1.4
//     */
//    public static final int DB_VERSION_V_1_4 = DB_VERSION_V_1_3 + 1;
//    /**
//     * 版本 1.6
//     */
//    public static final int DB_VERSION_V_1_6 = DB_VERSION_V_1_4 + 1;
//
//    /**
//     * 正式服务器 WS
//     */
//    public static final int FORMAL_WS = APIConfig.FORMAL_WS;
//
//    private static int sConnectServerType = FORMAL_WS;
//
//    /**
//     * 清除七天前的图片缓存数据
//     */
//    public static final long CLEAR_CACHE_INTERVAL = 7 * ConstantUtils.MILLIS_PER_DAY; //一周
//
//    /**
//     * 图片缓存占用这个APP的内存比例
//     */
//    public static final float IMAGE_CACHE_MEM_PERCENT = 0.05f;
//
//    /**
//     * 对象缓存占用这个APP的内存比例
//     */
//    public static final float OBJECT_CACHE_MEM_PERCENT = 0.05f;
//
//    /**
//     * 退出倒计时
//     */
//    public static final long EXIT_COUNT_DOWN = 5 * ConstantUtils.THOUSAND;
//
//    /**
//     * 最大累积么么数量
//     */
//    public static final int MAX_FEATHER_COUNT = 10;
//
//    /**
//     * 直播间弹幕图标宽度
//     */
//    public static final int DANMU_ICON_WIDTH_DP = 20;
//
//    /**
//     * 直播间弹幕图标高度
//     */
//    public static final int DANMU_ICON_HEIGHT_DP = 20;
//
//    /**
//     * 图标宽度
//     */
//    public static final int COMMON_ICON_WIDTH_DP = 40;
//    /**
//     * 图标高度
//     */
//    public static final int COMMON_ICON_HEIGHT_DP = 40;

    /**
     * 宽屏最低dip值
     */
    public static final float LARGE_SCREEN_DIPS = 480;
//
//    /**
//     * 水果乐园游戏最低等级
//     */
//    public static final int PLAY_FRUIT_GAME_MIN_LEVEL = 4;
//
//    /**
//     * 礼物列表缓存有效时间
//     */
//    public static final long GIFT_LIST_CACHE_EXPIRED_DURATION = 2 * ConstantUtils.MILLIS_PER_HOUR;
//
//    /**
//     * 余额显示的最大阳光数
//     */
//    public static final long MAX_DISPLAY_COIN = 9999999;
//
//    /**
//     * 是否使用新控件TextureView代替GLSurfaceView
//     */
//    public static final boolean USE_TEXTURE_VIEW = SDKVersionUtils.hasIceCreamSandwich();
//
//    public static final String CUSTOM_SERVICE_QQ = "800072482";
//
//    /**
//     * 本地时间和服务器时间差
//     */
//    private static long sServerTimeDiffMills;
//
    private static String sCacheFolderPath;
//
//    private static String sDataFolderPath;
//
    private static boolean sIsLargeScreen = false;
//
//    private static boolean sSendGiftMarquee = true;
//
//	private static boolean sFriendApplyNotify = true;
//
//    private static boolean sFriendMessageNotify = true;
//
//    private static boolean sNewMessageSystemNotify = true;
//
//    private static boolean sNewMessageNoticeVoice = true;
//    private static boolean sNewMessageVibrate = true;
//
//    private static boolean sNobodyAddFriend = false;
//
//    private static boolean recommStarVisibilityState = false;
//
//    public static String sWebSocketIP = "";
//
    private static final String IMAGE_CACHE_FOLDER_NAME = "image";
//    private static final String GIF_CACHE_FOLDER_NAME = "gif";
//    private static final String SWF_CACHE_FOLDER_NAME = "swf";
//    private static final String MARKET_APP_CACHE_FOLDER_NAME = "marketApp";
//    private static final String PAY_APP_FOLDER_NAME = "payApp";
//    private static final String TENPAY_APP_NAME = "tenpay.apk";
//    private static final String RING_CACHE_FOLDER_NAME = "ring";
//    private static final String OBJECT_CACHE_FOLDER_NAME = "object";
//    private static final String RECORDER_CACHE_FOLDER_NAME = "recorder";
//    private static final String TMP_FOLDER_NAME = ".tmp";
//    private static final String LAUNCH_IMAGE_FOLDER_NAME = "launchImage";
//    private static final String LOGIN_IMAGE_FOLDER_NAME = "loginImage";
//    private static final String SOUND_EFFECT_FOLDER_NAME = "soundEffect";
//    private static final String SOCKET_IP = "socket_ip_v2";
//    /**
//     * 图片后缀名 *
//     */
//    public static final String FILE_EXTENSION_JPG = ".jpg";
//
//    // SocketIOProxy
//    private static final String TEST_WEB_SOCKET_WS_URL = "http://test-aiws.memeyule.com:6010";
//    private static String FORMAL_WEB_SOCKET_WS_URL = "http://aiws.memeyule.com:6010";
//
//    private static final String IM_SOCKET_URL = "http://aiim.memeyule.com:6070";
//    private static final String TEST_IM_SOCKET_URL = "http://test-aiim.memeyule.com:6070";

//    private static final String GROUP_SOCKET_URL = "http://wo.memeyule.com:100";
//    private static final String TEST_GROUP_SOCKET_URL = "http://test.wo.memeyule.com:100";

//    private static final String VIDEO_PULL_SECRET_KEY = "f4_d0s3gp_zfir5jr3qwxv19";
//    private static final String VIDEO_PUSH_SECRET_KEY = "jel0_d3_uwpzq7e1_3q4vkdyeig";

//    private static final String VIDEO_PUSH_URL = "rtmp://t.ws.2339.com/meme/";
//    private static final String VIDEO_PULL_URL = "rtmp://l.ws.2339.com/meme/";
//    private static final String VIDEO_PUSH_URL = "rtmp://t.ws.sumeme.com/meme/";
//    private static final String VIDEO_PULL_URL = "rtmp://l.ws.sumeme.com/meme/";
//
//    public static final String strAppKey = "YOUME6CB225CAAECAABBDC0BBE852E9A212C6021CE3DE";
//    public static final String strSecrect = "u/qFj3N880Gz9Fiiq0bMNyzrxqhoeBqXjzvcmeGN32CrPWoTNcx77/baXZ4L52i+UYf9JPg5wJgMDR9zrq7C9obodb0vnnzQkxsQEe7wXmJiF41TCrXgcRNsgqYwoWraR/P+fIQbkL1LtWXiGFZWQN1MNrndspNhXhbG3Rz5oiMBAAE=";
//
//    /**
//     * 2339.com 房间地址same section
//     */
//    public static final String ROOM_URL_SAME_SECTION = "http://www.2339.com/";
//
//    private static SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener =
//            new SharedPreferences.OnSharedPreferenceChangeListener() {
//                @Override
//                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                    if (SharedPreferenceKey.LAST_CONNECT_SERVER_TYPE_KEY.equals(key)) {
//                        setConnectServerType(sharedPreferences.getInt(key, ShowConfig.FORMAL_WS));
//                    } else if (SharedPreferenceKey.SEND_GIFT_MARQUEE.equals(key)) {
//                        setIsSendGiftMarquee(sharedPreferences.getBoolean(key, true));
//                    }
//                    if (SharedPreferenceKey.FRIEND_APPLY_NOTIFY.equals(key)) {
//                        setFriendApplyNotifyOn(sharedPreferences.getBoolean(key, true));
//                    } else if (SharedPreferenceKey.FRIEND_MESSAGE_NOTIFY.equals(key)) {
//						setFriendMessageNotifyOn(sharedPreferences.getBoolean(key, true));
//					  } else if (SharedPreferenceKey.NEW_MESSAGE_SYSTEM_NOTIFY.equals(key)) {
//                        setNewMessageSystemNotifyOn(sharedPreferences.getBoolean(key, true));
//                    } else if (SharedPreferenceKey.NEW_MESSAGE_NOTICE_VOICE.equals(key)) {
//                        setNewMessageNoticeVoice(sharedPreferences.getBoolean(key, true));
//                    } else if (SharedPreferenceKey.NEW_MESSAGE_VIBRATE.equals(key)) {
//                        setNewMessageVibrate(sharedPreferences.getBoolean(key, true));
//                    } else if (SharedPreferenceKey.NOBODY_CAN_ADD_FRIEND.equals(key)) {
//                        setNobodyCanAddFriend(sharedPreferences.getBoolean(key, false));
//                    } else if (SharedPreferenceKey.RECOMM_STAR_VISIBILITY_STATE.equals(key)) {
//                        setRecommStarVisibilityState(sharedPreferences.getBoolean(key, false));
//                    }
//                }
//            };

    /**
     * 初始化
     *
     * @param context context
     */
    public static void init(Context context) {
        sCacheFolderPath = EnvironmentUtils.Storage.getCachePath(context);
//        sDataFolderPath = context.getFilesDir().getPath();
//        FileUtils.createFolder(getTmpFolderPath());
//        FileUtils.createFolder(getSwfCacheFolderPath());
//        FileUtils.createFolder(getMarketAppCacheFolderPath());
//        setConnectServerType(Preferences.getInt(SharedPreferenceKey.LAST_CONNECT_SERVER_TYPE_KEY, ShowConfig.FORMAL_WS));
//        setIsSendGiftMarquee(Preferences.getBoolean(SharedPreferenceKey.SEND_GIFT_MARQUEE, true));
//		setFriendApplyNotifyOn(Preferences.getBoolean(SharedPreferenceKey.FRIEND_APPLY_NOTIFY, true));
//        setFriendMessageNotifyOn(Preferences.getBoolean(SharedPreferenceKey.FRIEND_MESSAGE_NOTIFY, true));
//        setNewMessageSystemNotifyOn(Preferences.getBoolean(SharedPreferenceKey.NEW_MESSAGE_SYSTEM_NOTIFY, true));
//        setNewMessageNoticeVoice(Preferences.getBoolean(SharedPreferenceKey.NEW_MESSAGE_NOTICE_VOICE, true));
//        setNewMessageVibrate(Preferences.getBoolean(SharedPreferenceKey.NEW_MESSAGE_VIBRATE, true));
//        setNobodyCanAddFriend(Preferences.getBoolean(SharedPreferenceKey.NOBODY_CAN_ADD_FRIEND, false));
//        setRecommStarVisibilityState(Preferences.getBoolean(SharedPreferenceKey.RECOMM_STAR_VISIBILITY_STATE, false));
//
//        Preferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
    }
//
//    /**
//     * exit
//     */
//    public static void exit() {
//        Preferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
//        mSharedPreferenceChangeListener = null;
//        if (Preferences.getBoolean(SharedPreferenceKey.USER_FIRST_USE_FLAG, true)) {
//            Preferences.edit().putBoolean(SharedPreferenceKey.USER_FIRST_USE_FLAG, false).apply();
//        }
//    }
//
//    /**
//     * 设置是否连接测试服务器
//     *
//     * @param connectServerType 链接服务器的类型
//     */
//    public static void setConnectServerType(int connectServerType) {
//        sConnectServerType = connectServerType;
//        APIConfig.setConnectToTestServer(connectServerType);
//    }
//
//    /**
//     * 是否连接测试服务器
//     *
//     * @return true表示连接测试服务器
//     */
//    public static int getConnectToServerType() {
//        return sConnectServerType;
//    }
//
//    /**
//     * 获取缓存文件夹路径
//     *
//     * @return 路径名
//     */
//    public static String getCacheFolderPath() {
//        return sCacheFolderPath;
//    }
//
//    /**
//     * 获取数据文件夹路径
//     *
//     * @return 路径名
//     */
//    public static String getDataFolderPath() {
//        return sDataFolderPath;
//    }

    /**
     * 获取图片缓存路径
     *
     * @return 图片缓存路径
     */
    public static String getImageCacheFolderPath() {
        return sCacheFolderPath + File.separator + IMAGE_CACHE_FOLDER_NAME;
    }

//    /**
//     * 获取对象缓存的路径
//     *
//     * @return 对象缓存路径
//     */
//    public static String getObjectCacheFolderPath() {
//        return  sCacheFolderPath + File.separator + OBJECT_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取gif缓存路径
//     *
//     * @return gif缓存路径
//     */
//    public static String getGifCacheFolderPath() {
//        return sCacheFolderPath + File.separator + GIF_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取临时文件目录
//     *
//     * @return 临时文件目录
//     */
//    public static String getTmpFolderPath() {
//        return sCacheFolderPath + File.separator + TMP_FOLDER_NAME;
//    }
//
//    /**
//     * 获取swf缓存路径
//     *
//     * @return gif缓存路径
//     */
//    public static String getSwfCacheFolderPath() {
//        return sCacheFolderPath + File.separator + SWF_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取市场app缓存路径
//     *
//     * @return app缓存路径
//     */
//    public static String getMarketAppCacheFolderPath() {
//        return sCacheFolderPath + File.separator + MARKET_APP_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取支付app缓存路径
//     *
//     * @return app缓存路径
//     */
//    public static String getPayAppCacheFolderPath() {
//        return sCacheFolderPath + File.separator + PAY_APP_FOLDER_NAME;
//    }
//
//    /**
//     * 获取财付通apk文件名
//     *
//     * @return 财付通apk文件名
//     */
//    public static String getTenpayApkName() {
//        return TENPAY_APP_NAME;
//    }
//
//    /**
//     * 获取铃声的缓存路径
//     *
//     * @return 铃声的缓存路径
//     */
//    public static String getRingCacheFolderPath() {
//        return sCacheFolderPath + File.separator + RING_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 图片存储路径
//     *
//     * @return String
//     */
//    public static String getPictureFolderPath() {
//        return EnvironmentUtils.Storage.getSDCardPath() + File.separator + IMAGE_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取声音特效缓存路径
//     *
//     * @return 声音特效缓存路径
//     */
//    public static String getSoundEffectCacheFolderPath() {
//        return sCacheFolderPath + File.separator + SOUND_EFFECT_FOLDER_NAME;
//    }
//
//    /**
//     * 获取WebSocket地址
//     *
//     * @return WebSocketURL
//     */
//    public static String getWebSocketUrl() {
//        if (sConnectServerType == TEST_WS) {
//            return TEST_WEB_SOCKET_WS_URL;
//        }
//        String tempIP = "";
//        synchronized (sWebSocketIP) {
//            tempIP = sWebSocketIP;
//        }
//        if (StringUtils.isEmpty(tempIP)) {
//            HashMap<String, String> map = Cache.getPropertiesList();
//            if (map != null) {
//                tempIP = map.get(SOCKET_IP);
//                if (!StringUtils.isEmpty(tempIP)) {
//                    sWebSocketIP = tempIP;
//                }
//            }
//        }
//        if (StringUtils.isEmpty(tempIP)) {
//            new ResolveSocketDNSTask().execute();
//            return FORMAL_WEB_SOCKET_WS_URL;
//        }
//        return tempIP;
//    }
//
//
//    /**
//     * 获取聊天的socket地址
//     *
//     * @return 聊天的socket地址
//     */
//    public static String getImSocketUrl() {
//        return sConnectServerType == FORMAL_WS ? IM_SOCKET_URL : TEST_IM_SOCKET_URL;
//    }
//
//    /**
//     * 设置WebSocket的Ip地址
//     *
//     * @param ip ip
//     */
//    public static void setWebSocketIp(String ip) {
//        sWebSocketIP = ip;
//    }
//
//    /**
//     * 获取推流地址
//     *
//     * @param timeHex   服务器十六进制时间
//     * @param roomId    房间id
//     * @param isMainMic 是否是主麦
//     * @return 视频URL
//     */
//    public static String getVideoPushUrl(String timeHex, long roomId, boolean isMainMic) {
//        return getVideoUrl(timeHex, roomId, true, isMainMic);
//    }
//
//    /**
//     * 获取视频播放地址
//     *
//     * @param timeHex   服务器十六进制时间
//     * @param roomId    房间id
//     * @param isMainMic 是否是主麦
//     * @return 视频URL
//     */
//    public static String getVideoPullUrl(String timeHex, long roomId, boolean isMainMic) {
//        return getVideoUrl(timeHex, roomId, false, isMainMic);
//    }
//
//    private static String getVideoUrl(String timeHex, long roomId, boolean isPush, boolean isMainMic) {
//        StringBuilder sb = new StringBuilder();
//        String roomID = roomId + (isMainMic ? "_1" : "_2");
//        String md5key = (isPush ? VIDEO_PUSH_SECRET_KEY : VIDEO_PULL_SECRET_KEY) + "/meme/" + roomID + timeHex;
//        String md5 = SecurityUtils.MD5.get32MD5String(md5key);
//        sb.append(isPush ? VIDEO_PUSH_URL : VIDEO_PULL_URL).append(roomID).append("?k=").append(md5).append("&t=").append(timeHex);
//        return sb.toString();
//    }
//
//    /**
//     * 获取录音缓存路径
//     *
//     * @return 录音缓存路径
//     */
//    public static String getRecorderCacheFolderPath() {
//        return sCacheFolderPath + File.separator + RECORDER_CACHE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取启动图片缓存路径
//     *
//     * @return 启动图片缓存路径
//     */
//    public static String getLaunchImageCacheFolderPath() {
//        return sCacheFolderPath + File.separator + LAUNCH_IMAGE_FOLDER_NAME;
//    }
//
//    /**
//     * 获取登录界面缓存路径
//     *
//     * @return 登录图片缓存路径
//     */
//    public static String getLoginImageCacheFolderPath() {
//        return sCacheFolderPath + File.separator + LOGIN_IMAGE_FOLDER_NAME;
//    }
//
//    /**
//     * 封面图片宽度
//     *
//     * @return 封面图片宽度
//     */
//    public static int getRoomCoverWidth() {
//        return DisplayUtils.getWidthPixels() / 3;
//    }
//
//    /**
//     * @return 封面图片高度
//     */
//    public static int getRoomCoverHeight() {
//        return getRoomCoverWidth();
//    }

    /**
     * 是否是大屏手机
     *
     * @return true - 是大屏手机
     */
    public static boolean isLargeScreen() {
        return sIsLargeScreen;
    }

    /**
     * 设置是否是大屏手机
     *
     * @param isLargeScreen 是大屏手机
     */
    public static void setIsLargeScreen(boolean isLargeScreen) {
        ShowConfig.sIsLargeScreen = isLargeScreen;
    }

//    /**
//     * 送礼是否上跑道
//     *
//     * @return 送礼是否上跑道
//     */
//    public static boolean isSendGiftMarquee() {
//        return sSendGiftMarquee;
//    }
//
//    /**
//     * 设置送礼是否上跑道
//     *
//     * @param isSendGiftMarquee isSendGiftMarquee
//     */
//    public static void setIsSendGiftMarquee(boolean isSendGiftMarquee) {
//        sSendGiftMarquee = isSendGiftMarquee;
//    }
//
//    /**
//     * 解析Socket的DNS
//     */
//    public static class ResolveSocketDNSTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//                URL url = new URL(FORMAL_WEB_SOCKET_WS_URL);
//                InetAddress address = InetAddress.getByName(url.getHost());
//                if (address.getHostAddress() != null) {
//                    synchronized (sWebSocketIP) {
//                        sWebSocketIP = new StringBuilder(url.getProtocol())
//                                .append("://")
//                                .append(address.getHostAddress())
//                                .append(":")
//                                .append(url.getPort())
//                                .toString();
//                        if (!StringUtils.isEmpty(sWebSocketIP) && !sWebSocketIP.contains("42.62.80.36")) {
//                            StringBuilder errBuilder = new StringBuilder("解析" + FORMAL_WEB_SOCKET_WS_URL + "异常:" + sWebSocketIP);
//                            errBuilder.append("\n" + ExceptionReporter.buildReportParameters().toString());
//                        }
//                    }
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (SecurityException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }

//    /**
//     * 获取本地时间和服务器时间差
//     *
//     * @return 本地时间和服务器时间差
//     */
//    public static long getServerTimeDiffMills() {
//        return sServerTimeDiffMills;
//    }
//
//    /**
//     * 设置本地时间和服务器时间差
//     *
//     * @param serverTimeDiffMills 本地时间和服务器时间差
//     */
//    public static void setServerTimeDiffMills(long serverTimeDiffMills) {
//        if (serverTimeDiffMills == 0) { //sServerTimeDiffMills == 0 表示没有请求成功过服务器时间，发送聊天消息的之前需要重新请求
//            serverTimeDiffMills = 1;
//        }
//        ShowConfig.sServerTimeDiffMills = serverTimeDiffMills;
//    }

//    /**
//     * 获取服务器当前时间
//     *
//     * @return 服务器当前时间
//     */
//    public static long getServerTimeMillis() {
//        return System.currentTimeMillis() - sServerTimeDiffMills;
//    }
//
//    /**
//     * 好友申请提醒是否开启
//     *
//     * @return true - 开启
//     */
//    public static boolean isFriendApplyNotifyOn() {
//        return sFriendApplyNotify;
//    }
//
//    /**
//     * 设置好友申请提醒是否开启
//     *
//     * @param friendApplyNotify friendApplyNotify
//     */
//    public static void setFriendApplyNotifyOn(boolean friendApplyNotify) {
//        ShowConfig.sFriendApplyNotify = friendApplyNotify;
//    }
//
//    /**
//     * 好友消息提醒是否开启
//     *
//     * @return true - 开启
//     */
//    public static boolean isFriendMessageNotifyOn() {
//        return sFriendMessageNotify;
//    }
//
//    /**
//     * 设置好友消息提醒是否开启
//     *
//     * @param friendMessageNotify friendMessageNotify
//     */
//    public static void setFriendMessageNotifyOn(boolean friendMessageNotify) {
//        ShowConfig.sFriendMessageNotify = friendMessageNotify;
//    }
//
//    /**
//     * 设置新消息系统通知提醒
//     *
//     * @param newMessageNotify newMessageNotify
//     */
//    public static void setNewMessageSystemNotifyOn(boolean newMessageNotify) {
//        ShowConfig.sNewMessageSystemNotify = newMessageNotify;
//    }
//    /**
//     * 新消息系统通知提醒是否开启
//     */
//    public static boolean isNewMessageSystemNotifyOn() {
//        return sNewMessageSystemNotify;
//    }
//    /**
//     * 新消息提示音
//     * @param onFlag 开关标志
//     */
//    public static void setNewMessageNoticeVoice(boolean onFlag) {
//        ShowConfig.sNewMessageNoticeVoice = onFlag;
//    }
//    /**
//     * 新消息提示音是否开起
//     */
//    public static boolean isNewMessageNoticeVoice() {
//        return  sNewMessageNoticeVoice;
//    }
//
//    /**
//     * 设置新消息振动提示
//     * @param onFlag 开关标志
//     */
//    public static void setNewMessageVibrate(boolean onFlag) {
//        ShowConfig.sNewMessageVibrate = onFlag;
//    }
//    /**
//     * 新消息振动提示是否开始
//     */
//    public static boolean isNewMessageVibrate() {
//        return  sNewMessageVibrate;
//    }
//
//    /**
//     * 不能被任何人添加为好友
//     * @return boolean
//     */
//    public static boolean isNobodyCanAddFriend() {
//        return sNobodyAddFriend;
//    }
//
//    /**
//     * 设置不能被任何人添加为好友
//     * @param onFlag 开关标志
//     */
//    public static void setNobodyCanAddFriend(boolean onFlag) {
//        ShowConfig.sNobodyAddFriend = onFlag;
//    }
//
//
//    /**
//     * 推荐主播是否隐身
//     * @return boolean
//     */
//    public static boolean isRecommStarVisibilityState() {
//        return recommStarVisibilityState;
//    }
//
//    /**
//     * 设置推荐主播是否隐身
//     * @param onFlag 开关标志
//     */
//    public static void setRecommStarVisibilityState(boolean onFlag) {
//        ShowConfig.recommStarVisibilityState = onFlag;
//    }

}

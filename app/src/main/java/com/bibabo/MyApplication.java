package com.bibabo;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.bibabo.framework.BaseApplication;

/**
 * Created by zijian.cheng on 2017/5/8.
 */

public class MyApplication extends BaseApplication {

    private final static String TAG = MyApplication.class.getSimpleName();

    private static final String BUGLY_APP_ID = "9cdcd6e968";

    /**
     * push推送打开么么APP
     **/
    private static final int ACTION_TYPE_OF_OPEN_APP = 1;
    /**
     * push推送打开么么URL
     **/
    private static final int ACTION_TYPE_OF_OPEN_URL = 2;
    /**
     * push推送打开么么界面
     **/
    private static final int ACTION_TYPE_OF_OPEN_ACTIVITY = 3;
    /**
     * push推送打开么么其他
     **/
    private static final int ACTION_TYPE_OF_OPEN_CUSTOM = 4;

    @Override
    public void exitProcess() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this); //App的策略Bean
//        String channel = EnvironmentUtils.Config.getChannel();
//        strategy.setAppChannel(channel);     //设置渠道
//        strategy.setAppReportDelay(1000);  //设置SDK处理延时，毫秒
//        CrashReport.initCrashReport(this, BUGLY_APP_ID, false, strategy);  //初始化SDK

        // 初始化友盟推送SDK
//        initUmengPush();

    }

    /**
     * 初始化友盟推送
     */
//    private void initUmengPush() {
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void launchApp(Context context, UMessage msg) {
//                execute(ACTION_TYPE_OF_OPEN_APP, msg);
//            }
//
//            @Override
//            public void openUrl(Context context, UMessage msg) {
//                execute(ACTION_TYPE_OF_OPEN_URL, msg);
//            }
//
//            @Override
//            public void openActivity(Context context, UMessage msg) {
//                execute(ACTION_TYPE_OF_OPEN_ACTIVITY, msg);
//            }
//
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                execute(ACTION_TYPE_OF_OPEN_CUSTOM, msg);
//            }
//        };
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//        mPushAgent.setDebugMode(false);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                LogUtils.d(TAG, "device_token:" + deviceToken);
//                Preferences.edit().putString(SharedPreferenceKey.GETUI_PUSH_CLIENT_ID, deviceToken).apply();
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//
//        mPushAgent.onAppStart();
//        AdvisePush.getInstance(this).init();
//    }
//
//    /**
//     * 执行友盟推送解析后的动作
//     */
//    public void execute(int type, UMessage msg) {
//        LogUtils.d(TAG, "type:" + type);
//        LogUtils.d(TAG, "msg:" + msg.getRaw().toString());
//        switch (type) {
//            /** push推送打开URL **/
//            case ACTION_TYPE_OF_OPEN_URL:
////                if (!StringUtils.isEmpty(msg.title) && !StringUtils.isEmpty(msg.url)) {
////                    StringBuilder stringBuilder = new StringBuilder(msg.url);
////                    if (UserUtils.isAlreadyLogin() && UserUtils.getAccessToken() != null) {
////                        stringBuilder.append(stringBuilder.indexOf("?") == -1 ? "?" : "&")
////                                .append("access_token=").append(UserUtils.getAccessToken());
////                    }
////                    Intent intent = new Intent(this, BannerActivity.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent.putExtra(BannerOptions.INTENT_CLICK_URL, stringBuilder.toString());
////                    intent.putExtra(BannerOptions.INTENT_TITLE, msg.title);
////                    startActivity(intent);
////                    return;
////                }
//                break;
//            case ACTION_TYPE_OF_OPEN_ACTIVITY:
//                /** push推送打开主播房间 **/
////                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
////                    String key = entry.getKey();
////                    if (key.equals("roomId")) {
////                        String value = entry.getValue();
////                        if (Long.parseLong(value) != 0) {
////                            Intent intent = new Intent(this, LiveActivity.class);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                            intent.putExtra(StarRoomKey.ROOM_ID_KEY, Long.parseLong(value));
////                            startActivity(intent);
////                            break;
////                        }
////                    }
////                }
//                break;
//            case ACTION_TYPE_OF_OPEN_CUSTOM:
////                if (UserUtils.isAlreadyLogin()) {
////                    Intent intent = new Intent(this, CoinMissionActivity.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    startActivity(intent);
////                    return;
////                }
//                break;
//            case ACTION_TYPE_OF_OPEN_APP:
//                /** 打开消息列表页面*/
//                String value = msg.extra.get("event");
//                LogUtils.d(TAG, "value:" + value);
//                // 打开APP
//                if (StringUtils.equal(value, "redirect_app")) {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//                // 跳转到系统消息页
//                else if (StringUtils.equal(value, "redirect_msg")) {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
////                    DataChangeNotification.getInstance().notifyDataChanged(IssueKey.ISSUE_START_BROTHER, SystemMsgFragment.newInstance());
//                }
//                // 跳转到直播间页
//                else if (StringUtils.equal(value, "redirect_room")) {
////                    try {
////                        String roomId = msg.extra.get("room_id");
////                        Intent intent = new Intent(this, MainActivity.class);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(intent);
////
////                        Intent intentLive = new Intent();
////                        intentLive.setClass(BaseApplication.getApplication(), MobileLiveActivity.class);
////                        intentLive.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        intentLive.putExtra(StarRoomKey.ROOM_ID_KEY, Long.parseLong(roomId));
////                        startActivity(intentLive);
////                    } catch (NumberFormatException e) {
////                        e.printStackTrace();
////                    }
//                }
//                // 跳转到H5页
//                else if (StringUtils.equal(value, "redirect_url")) {
////                    String title = msg.title;
//                    String linkUrl = msg.extra.get("url");
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    if (!TextUtils.isEmpty(linkUrl)) {
////                        AppManager.getAppManager().openWebView("", linkUrl);
//                    }
//                } else {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

package com.bibabo.framework.utils;

import android.text.Html;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hao.xiong
 * @version 1.0.1
 * @since 2012-03-22
 */
public class StringUtils {
    private static final int CACHE_SIZE = 4096;

    /**
     * 将s中字符用delimiter分割连接起来
     *
     * @param delimiter 分隔符
     * @param segments  被连接的数据
     * @return 返回连接号的字符串
     * @see StringUtils#join(String, Object[])
     */
    public static String join(String delimiter, Collection<?> segments) {
        StringBuilder stringBuilder = new StringBuilder();
        if (segments != null) {
            appendCollectionObjectToStringBuilder(stringBuilder, delimiter, segments);
        }
        String outString = stringBuilder.toString();
        if (outString.endsWith(delimiter)) {
            return outString.substring(0, outString.length() - delimiter.length());
        }
        return outString;
    }

    /**
     * 将对象链接成一个字符串，使用delimiter传入的字符串分割，
     * <p><b>注意:</b>如果前一个片段为字符串且以delimiter结束或者为空(null获取为"")，将不会重复添加此字符串</p>
     * <p>字符串末尾不会自动添加delimiter字符串</p>
     * <p>如果没有传入参数，返回一个<b>空字符串</b></p>
     *
     * @param delimiter 分割字符串
     * @param segments  所有传入的部分
     * @return 连接完毕后的字符串
     */
    public static String join(String delimiter, Object... segments) {
        StringBuilder stringBuilder = new StringBuilder();
        if (segments != null) {
            int size = segments.length;
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    appendObjectToStringBuilder(stringBuilder, delimiter, segments[i]);
                }
            }
        }
        String outString = stringBuilder.toString();
        if (outString.endsWith(delimiter)) {
            return outString.substring(0, outString.length() - delimiter.length());
        }
        return outString;
    }

    /**
     * html to String
     *
     * @param str 原字符串
     * @return 转换后的字符串
     */
    public static String html2String(String str) {
        try {
            return StringUtils.isEmpty(str) ? "" : Html.fromHtml(str).toString();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return StringUtils.isEmpty(str) ? "" : str;
    }

    private static void appendArrayObjectToStringBuilder(StringBuilder stringBuilder, String delimiter, Object array) {
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            appendObjectToStringBuilder(stringBuilder, delimiter, Array.get(array, i));
        }
    }

    private static void appendCollectionObjectToStringBuilder(StringBuilder stringBuilder, String delimiter, Collection<?> collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            appendObjectToStringBuilder(stringBuilder, delimiter, iterator.next());
        }
    }

    private static void appendObjectToStringBuilder(StringBuilder stringBuilder, String delimiter, Object object) {
        if (object == null) {
            return;
        }
        if (object.getClass().isArray()) {
            appendArrayObjectToStringBuilder(stringBuilder, delimiter, object);
        } else if (object instanceof Collection) {
            appendCollectionObjectToStringBuilder(stringBuilder, delimiter, (Collection) object);
        } else {
            String objectString = object.toString();
            stringBuilder.append(objectString);
            if (!isEmpty(objectString) && !objectString.endsWith(delimiter)) {
                stringBuilder.append(delimiter);
            }
        }
    }

    /**
     * 测试传入的字符串是否为空
     *
     * @param string 需要测试的字符串
     * @return 如果字符串为空（包括不为空但其中为空白字符串的情况）返回true，否则返回false
     */
    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    /**
     * 传入的字符串是否相等
     *
     * @param a a字符串
     * @param b b字符串
     * @return 如果字符串相等（值比较）返回true，否则返回false
     */
    public static boolean equal(String a, String b) {
        return a == b || (a != null && b != null && a.length() == b.length() && a.equals(b));
    }

    /**
     * 将字符串用分隔符分割为long数组
     * <p><b>只支持10进制的数值转换</b></p>
     * <p><b>如果格式错误，将抛出NumberFormatException</b></p>
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 分割后的long数组.
     */
    public static long[] splitToLongArray(String string, String delimiter) {
        List<String> stringList = splitToStringList(string, delimiter);

        long[] longArray = new long[stringList.size()];
        int i = 0;
        for (String str : stringList) {
            longArray[i++] = Long.parseLong(str);
        }
        return longArray;
    }

    /**
     * 将字符串用分隔符分割为Long列表
     * <p><b>只支持10进制的数值转换</b></p>
     * <p><b>如果格式错误，将抛出NumberFormatException</b></p>
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 分割后的Long列表.
     */
    public static List<Long> splitToLongList(String string, String delimiter) {
        List<String> stringList = splitToStringList(string, delimiter);

        List<Long> longList = new ArrayList<Long>(stringList.size());
        for (String str : stringList) {
            longList.add(Long.parseLong(str));
        }
        return longList;
    }

    /**
     * 将字符串用分隔符分割为字符串数组
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 分割后的字符串数组.
     */
    public static String[] splitToStringArray(String string, String delimiter) {
        List<String> stringList = splitToStringList(string, delimiter);
        return stringList.toArray(new String[stringList.size()]);
    }

    /**
     * 将字符串用分隔符分割为字符串列表
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 分割后的字符串数组.
     */
    public static List<String> splitToStringList(String string, String delimiter) {
        List<String> stringList = new ArrayList<String>();
        if (!isEmpty(string)) {
            int length = string.length();
            int pos = 0;

            do {
                int end = string.indexOf(delimiter, pos);
                if (end == -1) {
                    end = length;
                }
                stringList.add(string.substring(pos, end));
                pos = end + 1; // skip the delimiter
            } while (pos < length);
        }
        return stringList;
    }

    /**
     * InputSteam 转换到 String，会把输入流关闭
     *
     * @param inputStream 输入流
     * @return String 如果有异常则返回null
     */
    public static String stringFromInputStream(InputStream inputStream) {
        try {
            byte[] readBuffer = new byte[CACHE_SIZE];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int readLen = inputStream.read(readBuffer, 0, CACHE_SIZE);
                if (readLen <= 0) {
                    break;
                }

                byteArrayOutputStream.write(readBuffer, 0, readLen);
            }

            return byteArrayOutputStream.toString("UTF-8");
        } catch (OutOfMemoryError e) {
            System.gc();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 测试是否为有效的注册电子邮件格式
     *
     * @param string 内容
     * @return true if yes
     */
    public static boolean isEmailFormat(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        String str = "^([a-zA-Z0-9_\\-\\.]{1,12}+)@((\\[[0-9]{1,8}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]{1,8}+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    public static boolean checkNumLength(String number, int numLength) {
        if (StringUtils.isEmpty(number)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        if (numLength == 0) {
            return pattern.matcher(number).matches();
        }
        return pattern.matcher(number).matches() && number.length() == numLength;
    }


    /**
     * 测试是否为有效的么么号
     *
     * @param memeNum memeNum
     * @return true if yes
     */
    public static boolean isMemeNumFormat(String memeNum) {
        if (StringUtils.isEmpty(memeNum)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[^0][0-9]*");
        return pattern.matcher(memeNum).matches();
    }

    /**
     * 测试是否为有效的身份证号码
     *
     * @param IDCard IDCard
     * @return true if yes
     */
    public static boolean isIDCardFormat(String IDCard) {
        if (StringUtils.isEmpty(IDCard)) {
            return false;
        }
        final int idCardLength = 18;
        return IDCard.length() == idCardLength;
    }

    /**
     * 测试是否为有效的登录电子邮件格式
     *
     * @param string 内容
     * @return true if yes
     */
    public static boolean isLoginEmailFormat(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        String emailFormat = "^\\s*\\w+(?:\\.?[\\w-]+\\.?)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.matches(emailFormat, string);
    }

    /**
     * 测试是否为有效的用户名
     *
     * @param string 内容
     * @return true if yes
     */
    public static boolean isUserNameFormat(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        String emailFormat = "^[a-zA-Z0-9_-]{0,}$";
        return Pattern.matches(emailFormat, string);
    }

    /**
     * 是否在长度范围之类
     *
     * @param string 内容
     * @param begin  最小长度（inclusive）
     * @param end    最大长度（inclusive）
     * @return 字符串长度在begin和end之内返回true，否则返回false。<p><b>输入字符串为null时，返回false</b></p>
     */
    public static boolean lengthInRange(String string, int begin, int end) {
        return string != null && string.length() >= begin && string.length() <= end;
    }

    /**
     * 去除文件名中的无效字符
     *
     * @param srcStr srcStr
     * @return 去除文件名后的字符
     */
    public static String validateFilePath(String srcStr) {
        return StringUtils.isEmpty(srcStr) ? srcStr : srcStr.replaceAll("[\\\\/:\"*?<>|]+", "_");
    }

    /**
     * 半角转全角
     *
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * 全角转半角
     *
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {


        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }

    //判断一个字符串的首字符是否为字母
    public static boolean isBeginAbc(String s) {
        char c = s.charAt(0);
        int i = (int) c;
        if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
            return true;
        } else {
            return false;
        }
    }

    // 格式化金钱显示方式,每三位中间加逗号.
    public static String formatNumber(long money) {
        return new DecimalFormat("###,###").format(money);
    }

    /**
     * 格式化带有数字的文字
     *
     * @param str
     * @return
     */
    public static String formatNumber(String str) {
        if (str.contains("年") && str.contains("月")) {
            return str;
        }
        int maxNumber = 10;
        str = str.trim();
        int index = 0;
        boolean isAdd = false;
        String[] str2 = new String[maxNumber];
        List<Integer> dots = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    if (str2[index] == null) {
                        str2[index] = String.valueOf(str.charAt(i));
                    } else {
                        str2[index] += str.charAt(i);
                    }
                    if (!isAdd) {
                        isAdd = true;
                        dots.add(i);
                    }
                } else {
                    sb.append(str.charAt(i));
                    if (isAdd) {
                        index++;
                        isAdd = false;
                        if (index >= maxNumber - 1) {
                            return str;
                        }
                    }
                }
            }
            for (int i = 0; i < dots.size(); i++) {
                sb.insert(dots.get(i), formatNumber(Long.parseLong(str2[i])));
            }
        } catch (Exception e) {
            return str;
        }
        return sb.toString();
    }

    /**
     * 删除Url中的参数
     */
    public static String delQueStr(String url, String ref){
        String str = "";

        if (url.indexOf("?") != -1)
            str = url.substring(url.indexOf("?") + 1);
        else
            return url;
        String[] arr ;
        String returnurl = "";
        if (str.indexOf("&") != -1) {
            arr = str.split("&");
            for (int i = 0; i < arr.length; i++) {
                if (!equal(arr[i].split("=")[0],ref)) {
                    returnurl = returnurl + arr[i].split("=")[0] + "=" + arr[i].split("=")[1] + "&";
                }
            }
            return url.substring(0, url.indexOf('?')) + "?" + returnurl.substring(0, returnurl.length() - 1);
        }
        else {
            arr = str.split("=");
            if (equal(arr[0],ref))
                return url.substring(0, url.indexOf("?"));
            else
                return url;
        }
    }

    public static String trim(CharSequence text) {
        return text.toString().trim();
    }


    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    private static final ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public StringUtils() {
    }

    public static boolean isEmpty(CharSequence input) {
        if(input != null && !"".equals(input)) {
            for(int i = 0; i < input.length(); ++i) {
                char c = input.charAt(i);
                if(c != 32 && c != 9 && c != 13 && c != 10) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isEmail(CharSequence email) {
        return isEmpty(email)?false:emailer.matcher(email).matches();
    }

    public static boolean isPhone(CharSequence phoneNum) {
        return isEmpty(phoneNum)?false:phone.matcher(phoneNum).matches();
    }

    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var3) {
            return defValue;
        }
    }

    public static int toInt(Object obj) {
        return obj == null?0:toInt(obj.toString(), 0);
    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        byte[] var5 = data;
        int var4 = data.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte b = var5[var3];
            int v = b & 255;
            if(v < 16) {
                sb.append('0');
            }

            sb.append(Integer.toHexString(v));
        }

        return sb.toString().toUpperCase(Locale.getDefault());
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            d[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return d;
    }

    public static String friendlyTime(String sdate) {
        Date time = null;
        if(isInEasternEightZones()) {
            time = toDate(sdate);
        } else {
            time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        }

        if(time == null) {
            return "Unknown";
        } else {
            String ftime = "";
            Calendar cal = Calendar.getInstance();
            String curDate = ((SimpleDateFormat)dateFormater2.get()).format(cal.getTime());
            String paramDate = ((SimpleDateFormat)dateFormater2.get()).format(time);
            if(curDate.equals(paramDate)) {
                int lt1 = (int)((cal.getTimeInMillis() - time.getTime()) / 3600000L);
                if(lt1 == 0) {
                    ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000L, 1L) + "分钟前";
                } else {
                    ftime = lt1 + "小时前";
                }

                return ftime;
            } else {
                long lt = time.getTime() / 86400000L;
                long ct = cal.getTimeInMillis() / 86400000L;
                int days = (int)(ct - lt);
                if(days == 0) {
                    int hour = (int)((cal.getTimeInMillis() - time.getTime()) / 3600000L);
                    if(hour == 0) {
                        ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000L, 1L) + "分钟前";
                    } else {
                        ftime = hour + "小时前";
                    }
                } else if(days == 1) {
                    ftime = "昨天";
                } else if(days == 2) {
                    ftime = "前天 ";
                } else if(days > 2 && days < 31) {
                    ftime = days + "天前";
                } else if(days >= 31 && days <= 62) {
                    ftime = "一个月前";
                } else if(days > 62 && days <= 93) {
                    ftime = "2个月前";
                } else if(days > 93 && days <= 124) {
                    ftime = "3个月前";
                } else {
                    ftime = ((SimpleDateFormat)dateFormater2.get()).format(time);
                }

                return ftime;
            }
        }
    }

    public static Date toDate(String sdate) {
        return toDate(sdate, (SimpleDateFormat)dateFormater.get());
    }

    public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException var3) {
            return null;
        }
    }

    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        if(TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08")) {
            defaultVaule = true;
        } else {
            defaultVaule = false;
        }

        return defaultVaule;
    }

    public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
        Date finalDate = null;
        if(date != null) {
            int timeOffset = oldZone.getOffset(date.getTime()) - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - (long)timeOffset);
        }

        return finalDate;
    }

    /**
     * 用指定字符替换字符串中的所有空白字符
     *
     * @param str 需要替换的字符串
     * @param replacement 指定字符
     *
     * @return 返回替换后的字符串
     */
    public static String replaceAppointChars(String str, String replacement) {
        String sTemp = str.replaceAll("\\s{1,}", replacement);
        return sTemp;

    }

    /**
     * 获取字符数量
     * 汉字占2个，英文占一个
     *
     * @param text
     *
     * @return
     */
    public static int getTextLengthInt(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            int num = text.charAt(i);
            if (num > 0 && num < 127) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 格式化数字，当大于万的时候，显示以万为单位的字符串
     * @param scale 表示表示需要精确到小数点以后几位，比如1就表示1位。
     */
    public static String formatNumberForWan(long number, int scale){
        if (number < 10000) {
            return String.valueOf(number);
        } else {
            double n = Arith.div(number, 10000, scale);
            return String.valueOf(n + "万");
        }
    }
}



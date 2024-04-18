package com.xiaopeng.ota.sdk.common.util;

import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.log.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.tar.TarConstants;
/* loaded from: classes2.dex */
public class TimeUtils {
    private static final String DEFAULT_FORMAT = "yyyyMMdd";
    private static final String FORMAT_HOUR_MINUTE = "HH:mm";
    private static final String FORMAT_TIME = "HH:mm:ss.SSS";
    public static final String SEPARATOR = ":";
    private static final String TAG = "TimeUtils";

    public static String getHourMinute() {
        return new SimpleDateFormat(FORMAT_HOUR_MINUTE).format(new Date());
    }

    public static String getDay() {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat(FORMAT_TIME).format(new Date());
    }

    public static int getHour(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str.split(":")[0]);
        } catch (Exception e) {
            Logger.e(TAG, e, "Parse hour fail", new Object[0]);
            return 0;
        }
    }

    public static int getMinute(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return 0;
        }
        try {
            return Integer.parseInt(split[1]);
        } catch (Exception e) {
            Logger.e(TAG, e, "Parse minute fail", new Object[0]);
            return 0;
        }
    }

    public static int getSecond(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split.length < 3) {
            return 0;
        }
        try {
            return Integer.parseInt(split[2]);
        } catch (Exception e) {
            Logger.e(TAG, e, "Parse minute fail", new Object[0]);
            return 0;
        }
    }

    public static boolean isTimeFormat(String str) {
        return Pattern.compile("(([0-1][0-9])|2[0-3]):[0-5][0-9]").matcher(str).matches() || Pattern.compile("(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9]").matcher(str).matches();
    }

    public static String getFullTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!str.contains(":")) {
            return str + ":" + TarConstants.VERSION_POSIX + ":" + TarConstants.VERSION_POSIX;
        } else if (str.indexOf(":") == str.lastIndexOf(":")) {
            return str + ":" + TarConstants.VERSION_POSIX;
        } else {
            return str;
        }
    }
}

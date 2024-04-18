package com.xiaopeng.ota.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.xiaopeng.ota.R;
import java.util.Calendar;
import org.apache.commons.compress.archivers.tar.TarConstants;
/* loaded from: classes2.dex */
public class TimeUtils {
    private static final String TAG = "TimeUtils";
    public static final String TIME_SEPARATOR = ":";
    public static final String ZERO = "0";

    public static String getMinuteString(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] split = str.split(":");
        if (split.length < 3) {
            return str;
        }
        return split[0].trim() + ":" + split[1].trim();
    }

    public static Pair<Integer, Integer> getHourAndMinute(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length == 1) {
            return null;
        }
        try {
            return new Pair<>(Integer.valueOf(Integer.parseInt(split[0])), Integer.valueOf(Integer.parseInt(split[1])));
        } catch (Exception e) {
            LogUtils.e(TAG, e, "getHourAndMinute fail");
            return null;
        }
    }

    public static String getReminder(String str) {
        String[] stringArray;
        if (TextUtils.isEmpty(str)) {
            return ResourceUtils.getString(R.string.change_dialog_noon_reminder_default);
        }
        String[] split = str.split(":");
        if (split.length == 2) {
            split = (str + ":0").split(":");
        }
        if (split.length != 3) {
            return ResourceUtils.getString(R.string.change_dialog_noon_reminder_default);
        }
        int[] iArr = new int[3];
        try {
            iArr[0] = Integer.parseInt(split[0].trim());
            iArr[1] = Integer.parseInt(split[1].trim());
            iArr[2] = Integer.parseInt(split[2].trim());
            boolean isTodaySchedule = isTodaySchedule(iArr);
            int i = iArr[0];
            if (isTodaySchedule) {
                stringArray = ResourceUtils.getStringArray(R.array.change_dialog_noon_reminder_today);
            } else {
                stringArray = ResourceUtils.getStringArray(R.array.change_dialog_noon_reminder_tomorrow);
            }
            return getReminderString(stringArray, i);
        } catch (Exception e) {
            LogUtils.w(TAG, e, "parseInt fail, times: " + JsonUtils.toJson(split));
            return ResourceUtils.getString(R.string.change_dialog_noon_reminder_default);
        }
    }

    private static String getReminderString(String[] strArr, int i) {
        if (i < 6) {
            return strArr[0];
        }
        if (i < 12) {
            return strArr[1];
        }
        if (i < 17) {
            return strArr[2];
        }
        if (i < 19) {
            return strArr[3];
        }
        return strArr[4];
    }

    private static boolean isTodaySchedule(int[] iArr) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, iArr[0]);
        calendar.set(12, iArr[1]);
        calendar.set(13, iArr[2]);
        calendar.set(14, 0);
        return calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis();
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

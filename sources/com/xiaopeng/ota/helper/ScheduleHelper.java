package com.xiaopeng.ota.helper;

import android.text.TextUtils;
import com.lzy.okgo.OkGo;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PreferencesUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class ScheduleHelper {
    private static final String FORMAT = "%02d:%02d";
    private static final String FORMAT_WITH_SECOND = "%02d:%02d:%02d";
    private static final String KEY_AUTO_SCHEDULE_CREATE_TIME = "auto_schedule_create_time";
    private static final String KEY_AUTO_SCHEDULE_TIME = "auto_schedule_time";
    private static final String KEY_INTERVAL = "schedule_interval";
    private static final String KEY_LAST_SCHEDULE = "last_schedule_time";
    private static final String KEY_SCHEDULE_TIME = "schedule_time";
    private static final String KEY_SETTING = "schedule_setting";
    private static final String TAG = "ScheduleHelper";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ScheduleHelperHolder {
        static final ScheduleHelper INSTANCE = new ScheduleHelper();

        private ScheduleHelperHolder() {
        }
    }

    public static ScheduleHelper getInstance() {
        return ScheduleHelperHolder.INSTANCE;
    }

    public void saveScheduleTime(int i, int i2) {
        saveScheduleTime(i, i2, 0);
    }

    public void saveScheduleTime(int i, int i2, int i3) {
        String format = String.format(Locale.getDefault(), FORMAT, Integer.valueOf(i), Integer.valueOf(i2));
        PreferencesUtils.putString(OTAManager.getContext(), KEY_SCHEDULE_TIME, format);
        PreferencesUtils.putString(OTAManager.getContext(), KEY_LAST_SCHEDULE, format);
        Date date = new Date();
        PreferencesUtils.putLong(OTAManager.getContext(), KEY_SETTING, date.getTime());
        PreferencesUtils.putString(OTAManager.getContext(), KEY_INTERVAL, getSpanTime(date, i, i2));
    }

    public void cancelSchedule() {
        PreferencesUtils.remove(OTAManager.getContext(), KEY_SCHEDULE_TIME);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_SETTING);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_INTERVAL);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_AUTO_SCHEDULE_TIME);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_AUTO_SCHEDULE_CREATE_TIME);
    }

    public void cancelAutoSchedule() {
        PreferencesUtils.remove(OTAManager.getContext(), KEY_AUTO_SCHEDULE_TIME);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_AUTO_SCHEDULE_CREATE_TIME);
    }

    public void saveAutoScheduleTime(int i, int i2, int i3) {
        PreferencesUtils.putString(OTAManager.getContext(), KEY_AUTO_SCHEDULE_TIME, String.format(Locale.getDefault(), FORMAT, Integer.valueOf(i), Integer.valueOf(i2)));
        PreferencesUtils.putLong(OTAManager.getContext(), KEY_AUTO_SCHEDULE_CREATE_TIME, new Date().getTime());
    }

    public boolean isSetAutoScheduled() {
        return System.currentTimeMillis() - PreferencesUtils.getLong(OTAManager.getContext(), KEY_AUTO_SCHEDULE_CREATE_TIME, 0L) <= TimeUnit.HOURS.toMillis(24L) && PreferencesUtils.getString(OTAManager.getContext(), KEY_AUTO_SCHEDULE_TIME, null) != null;
    }

    public String getScheduleTime() {
        return PreferencesUtils.getString(OTAManager.getContext(), KEY_SCHEDULE_TIME, String.format(Locale.getDefault(), FORMAT, 0, 0));
    }

    public boolean isSetScheduleTime() {
        return System.currentTimeMillis() - PreferencesUtils.getLong(OTAManager.getContext(), KEY_SETTING, 0L) <= TimeUnit.HOURS.toMillis(24L) && PreferencesUtils.getString(OTAManager.getContext(), KEY_SCHEDULE_TIME, null) != null;
    }

    public String getDefaultTime() {
        return ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME);
    }

    public int getScheduleHour() {
        String scheduleTime = getScheduleTime();
        if (TextUtils.isEmpty(scheduleTime)) {
            return -1;
        }
        try {
            return Integer.parseInt(scheduleTime.split(":")[0]);
        } catch (Exception unused) {
            LogUtils.e(TAG, "Convert hour error");
            return -1;
        }
    }

    public int getScheduleMinute() {
        String scheduleTime = getScheduleTime();
        if (TextUtils.isEmpty(scheduleTime)) {
            return -1;
        }
        String[] split = scheduleTime.split(":");
        if (split.length < 2) {
            return -1;
        }
        try {
            return Integer.parseInt(split[1]);
        } catch (Exception unused) {
            LogUtils.e(TAG, "Convert minute error");
            return -1;
        }
    }

    public int getLastScheduleHour() {
        String string = PreferencesUtils.getString(OTAManager.getContext(), KEY_LAST_SCHEDULE, null);
        if (TextUtils.isEmpty(string)) {
            string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME);
        }
        if (string.contains(":")) {
            return Integer.valueOf(string.split(":")[0]).intValue();
        }
        return 0;
    }

    public int getLastScheduleMinute() {
        String string = PreferencesUtils.getString(OTAManager.getContext(), KEY_LAST_SCHEDULE, null);
        if (TextUtils.isEmpty(string)) {
            string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME);
        }
        if (string.contains(":")) {
            return Integer.valueOf(string.split(":")[1]).intValue();
        }
        return 0;
    }

    public void clear() {
        PreferencesUtils.remove(OTAManager.getContext(), KEY_SCHEDULE_TIME);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_SETTING);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_INTERVAL);
        PreferencesUtils.remove(OTAManager.getContext(), KEY_LAST_SCHEDULE);
    }

    public String getSpanTimeWidSecond(Date date, int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, 0);
        TimeSpan timeSpan = new TimeSpan(date, calendar.getTime());
        if (timeSpan.spanHour() == -1 || timeSpan.spanMinute() == -1) {
            return null;
        }
        return String.format(Locale.getDefault(), FORMAT_WITH_SECOND, Long.valueOf(timeSpan.spanHour()), Long.valueOf(timeSpan.spanMinute()), Long.valueOf(timeSpan.spanSecond()));
    }

    public String getSpanTime(Date date, int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        TimeSpan timeSpan = new TimeSpan(date, calendar.getTime());
        if (timeSpan.spanHour() == -1 || timeSpan.spanMinute() == -1) {
            return null;
        }
        return String.format(Locale.getDefault(), FORMAT, Long.valueOf(timeSpan.spanHour()), Long.valueOf(timeSpan.spanMinute()));
    }

    private boolean isObsolete(int i) {
        long j = PreferencesUtils.getLong(OTAManager.getContext(), KEY_SETTING, -1L);
        if (j == -1) {
            return false;
        }
        String string = PreferencesUtils.getString(OTAManager.getContext(), KEY_INTERVAL, null);
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(":");
            if (split.length == 2) {
                if (System.currentTimeMillis() - ((j + (Integer.valueOf(split[0]).intValue() * Config.MIL_SECONDS_IN_HOUR)) + (Integer.valueOf(split[1]).intValue() * OkGo.DEFAULT_MILLISECONDS)) >= i * OkGo.DEFAULT_MILLISECONDS) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTimesUp() {
        return getRemainTimeString().equals(String.format(Locale.getDefault(), FORMAT_WITH_SECOND, 0, 0, 0));
    }

    public String getRemainTimeString() {
        String format = String.format(Locale.getDefault(), FORMAT_WITH_SECOND, 0, 0, 0);
        if (isObsolete(5)) {
            cancelSchedule();
            return format;
        }
        String[] split = PreferencesUtils.getString(OTAManager.getContext(), KEY_SCHEDULE_TIME, format).split(":");
        return (split[0].equals("0") && split[1].equals("0")) ? format : getSpanTimeWidSecond(new Date(), Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TimeSpan {
        private static final long MIL_SECONDS_IN_HOUR = 3600000;
        private static final long MIL_SECONDS_IN_MINUTE = 60000;
        private static final long MIL_SECONDS_IN_SECOND = 1000;
        private long mEnd;
        private long mStart;

        TimeSpan(Date date, Date date2) {
            this.mStart = date.getTime();
            this.mEnd = date2.getTime();
            long j = this.mEnd;
            if (j < this.mStart) {
                this.mEnd = j + 86400000;
            }
        }

        long spanHour() {
            long j = this.mEnd - this.mStart;
            if (j < 0) {
                return -1L;
            }
            return j / 3600000;
        }

        long spanMinute() {
            long j = this.mEnd - this.mStart;
            if (j < 0) {
                return -1L;
            }
            return (j - (spanHour() * 3600000)) / 60000;
        }

        long spanSecond() {
            long j = this.mEnd - this.mStart;
            if (j < 0) {
                return -1L;
            }
            return ((j - (spanHour() * 3600000)) - (spanMinute() * 60000)) / 1000;
        }
    }
}

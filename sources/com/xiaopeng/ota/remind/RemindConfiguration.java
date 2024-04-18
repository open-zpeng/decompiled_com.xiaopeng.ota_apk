package com.xiaopeng.ota.remind;

import com.xiaopeng.ota.OTAManager;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class RemindConfiguration {
    private static final long DEFAULT_DAY_MILLIS = 86400000;
    private static final String KEY_DAY_MILLIS = "UPDATE_REMIND.DAY_MILLIS";
    private static final String KEY_GEAR_CHANGED_MILEAGE_INTERVAL_MAX = "UPDATE_REMIND.GEAR_CHANGED_MILEAGE_INTERVAL_MAX";
    private static final String KEY_GEAR_CHANGED_TIME_INTERVAL_MAX = "UPDATE_REMIND.GEAR_CHANGED_TIME_INTERVAL_MAX";
    private static final String KEY_POWER_OFF_DURATION_MIN = "UPDATE_REMIND.POWER_OFF_DURATION_MIN";
    private static final String KEY_REMIND_INTERVAL_MIN = "UPDATE_REMIND.REMIND_INTERVAL_MIN";
    private static final String KEY_REMIND_TIME_LINE = "UPDATE_REMIND.TIME_LINE";
    private static final String KEY_VOICE_CONTENT = "UPDATE_REMIND.VOICE_CONTENT";
    private static final String KEY_VOICE_MODE = "UPDATE_REMIND.VOICE_MODE";
    private static final String KEY_VOICE_RINGTONE_URI = "UPDATE_REMIND.VOICE_RINGTONE_URI";
    private static final String REMIND_TIME_LINE_DEFAULT = "12:00:00";
    public static final int VOICE_MODE_CONTENT = 1;
    public static final int VOICE_MODE_DEFAULT = 0;
    public static final int VOICE_MODE_RINGTONE = 2;

    public static int voiceMode() {
        return OTAManager.getConfigManager().getInt(KEY_VOICE_MODE, 0);
    }

    public static String voiceContent() {
        return OTAManager.getConfigManager().getString(KEY_VOICE_CONTENT, "嗨！又有新升级来啦！请注意查看！");
    }

    public static String getVoiceRingtoneUri() {
        return OTAManager.getConfigManager().getString(KEY_VOICE_RINGTONE_URI, "content://settings/system/notification_sound");
    }

    public static long powerOffDurationMin() {
        return OTAManager.getConfigManager().getLong(KEY_POWER_OFF_DURATION_MIN, 7200000L);
    }

    public static long gearChangedTimeIntervalMax() {
        return OTAManager.getConfigManager().getLong(KEY_GEAR_CHANGED_TIME_INTERVAL_MAX, TimeUnit.MINUTES.toMillis(5L));
    }

    public static float gearChangedMileageIntervalMax() {
        return OTAManager.getConfigManager().getFloat(KEY_GEAR_CHANGED_MILEAGE_INTERVAL_MAX, 1000.0f) / 1000.0f;
    }

    public static long remindIntervalMin() {
        return OTAManager.getConfigManager().getLong(KEY_REMIND_INTERVAL_MIN, TimeUnit.MINUTES.toMillis(30L));
    }

    public static long dayMillis() {
        return OTAManager.getConfigManager().getLong(KEY_DAY_MILLIS, DEFAULT_DAY_MILLIS);
    }

    public static String remindTimeLine() {
        return OTAManager.getConfigManager().getString(KEY_REMIND_TIME_LINE, REMIND_TIME_LINE_DEFAULT);
    }
}

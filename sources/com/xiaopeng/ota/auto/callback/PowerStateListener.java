package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public interface PowerStateListener {
    public static final int BAT_CHARGE_ST = 7;
    public static final int INVALID = -1;
    public static final int LOCAL_IGON = 4;
    public static final int LOCAL_LIGHT = 8;
    public static final int REMOTE_CAMERA = 5;
    public static final int REMOTE_LIGHT = 6;
    public static final int REMOTE_OTA = 9;
    public static final int REMOTE_SCU = 10;
    public static final int SOLDIER_MODE = 11;
    public static final int STATE_ON = 6;
    public static final int STATE_ON_SCREEN_OFF = 9;
    public static final int STATE_SHUTDOWN_CANCELLED = 8;
    public static final int STATE_SHUTDOWN_ENTER = 5;
    public static final int STATE_SHUTDOWN_PREPARE = 7;
    public static final int STATE_SUSPEND_ENTER = 2;
    public static final int STATE_SUSPEND_EXIT = 3;
    public static final int STATE_WAIT_FOR_VHAL = 1;
    public static final int TIMER = 2;
    public static final int USER_POWER_ON = 0;
    public static final int USER_UNLOCK = 1;

    void onCarNotConnected(Exception exc);

    void onStateChanged(int i, int i2);
}

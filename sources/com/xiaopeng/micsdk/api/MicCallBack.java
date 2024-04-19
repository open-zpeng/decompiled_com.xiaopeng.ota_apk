package com.xiaopeng.micsdk.api;
/* loaded from: classes2.dex */
public interface MicCallBack {
    public static final int MIC_POWER_OFF = 6;
    public static final int MIC_POWER_ON = 5;
    public static final int SERVICE_ON_CONNECT = 1;
    public static final int SERVICE_ON_DISCONNECT = 2;
    public static final int UDB_DONGLE_OFF = 4;
    public static final int UDB_DONGLE_ON = 3;

    int micServiceCallBack(int i);
}

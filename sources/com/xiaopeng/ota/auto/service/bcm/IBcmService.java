package com.xiaopeng.ota.auto.service.bcm;

import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public interface IBcmService extends ICarService {
    public static final int BCM_POWERMODE_CRANK = 2;
    public static final int BCM_POWERMODE_OFF = 0;
    public static final int BCM_POWERMODE_ON = 1;

    int getATwsState() throws Exception;

    int getDoorLockState() throws Exception;

    int[] getDoorsState() throws Exception;

    boolean getDriverBeltWarning() throws Exception;

    int getDriverOnSeat() throws Exception;

    int getPowerMode() throws Exception;

    int getWindowLockState() throws Exception;

    float[] getWindowsState() throws Exception;
}

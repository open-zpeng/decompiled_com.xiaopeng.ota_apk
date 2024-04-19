package com.xiaopeng.ota.auto.service.mcu;

import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public interface IMcuService extends ICarService {
    public static final int MCU_IG_STATUS_IG_OFF = 0;
    public static final int MCU_IG_STATUS_LOCAL_IG_ON = 1;
    public static final int MCU_IG_STATUS_REMOTE_IG_ON = 2;
    public static final int MCU_STATUS_OFF = 0;
    public static final int MCU_STATUS_ON = 1;

    int getFactoryModeSwitchStatus() throws Exception;

    String getHardwareCarStage() throws Exception;

    String getHardwareCarType() throws Exception;

    int getIgStatusFromMcu() throws Exception;

    int getOtaMcuReqStatus() throws Exception;

    int getOtaMcuReqUpdateFile() throws Exception;

    int getOtaMcuUpdateStatus() throws Exception;

    String getXpCduType() throws Exception;

    void sendOta1MsgToMcu(int[] iArr) throws Exception;

    void sendPsuOtaMsgToMcu(int[] iArr) throws Exception;

    void setMcuDelaySleep(int i) throws Exception;

    void setOtaMcuReqStatus(int i) throws Exception;

    void setOtaMcuReqUpdateFile(int i) throws Exception;

    void setOtaMcuSendUpdateFile(String str) throws Exception;
}

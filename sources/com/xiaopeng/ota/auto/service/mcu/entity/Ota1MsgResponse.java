package com.xiaopeng.ota.auto.service.mcu.entity;
/* loaded from: classes2.dex */
public class Ota1MsgResponse {
    private int mcuAckArdRstNotify;
    private int mcuAckOtaTestAck;
    private int mcuPacketSt;
    private int mcuProCondStAck;

    public Ota1MsgResponse(int[] iArr) {
        if (iArr != null && iArr.length == 4) {
            this.mcuProCondStAck = iArr[0] & 255;
            this.mcuPacketSt = (iArr[0] >> 8) & 255;
            this.mcuAckOtaTestAck = (iArr[0] >> 16) & 255;
            this.mcuAckArdRstNotify = (iArr[0] >> 24) & 255;
            return;
        }
        throw new IllegalArgumentException("data length not equals 4");
    }

    public int getMcuProCondStAck() {
        return this.mcuProCondStAck;
    }

    public int getMcuPacketSt() {
        return this.mcuPacketSt;
    }

    public int getMcuAckOtaTestAck() {
        return this.mcuAckOtaTestAck;
    }

    public int getMcuAckArdRstNotify() {
        return this.mcuAckArdRstNotify;
    }
}

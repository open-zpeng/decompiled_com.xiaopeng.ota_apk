package com.xiaopeng.ota.auto.service.mcu.entity;
/* loaded from: classes2.dex */
public class PsuOtaMsg {
    private static final int OPERATE_CANCEL = 2;
    private static final int OPERATE_SET = 1;
    private static final int PSU_RESET_REQ = 1;
    private int otaState;
    private int psuResetReq;
    private int wakeUpHour;
    private int wakeUpMin;
    private int wakeUpSecond;
    private int wakeUpSetReq;
    private int wakeUpSetType;

    private PsuOtaMsg() {
    }

    void setPsuResetReq(int i) {
        this.psuResetReq = i;
    }

    void setWakeUpSetReq(int i) {
        this.wakeUpSetReq = i;
    }

    void setWakeUpSetType(int i) {
        this.wakeUpSetType = i;
    }

    void setWakeUpHour(int i) {
        this.wakeUpHour = i;
    }

    void setWakeUpMin(int i) {
        this.wakeUpMin = i;
    }

    void setWakeUpSecond(int i) {
        this.wakeUpSecond = i;
    }

    void setOtaState(int i) {
        this.otaState = i;
    }

    public int[] getData() {
        int[] iArr = new int[4];
        iArr[0] = this.psuResetReq & 255;
        iArr[0] = iArr[0] | ((this.wakeUpSetReq & 255) << 8);
        iArr[0] = iArr[0] | ((this.wakeUpSetType & 255) << 16);
        iArr[0] = iArr[0] | ((this.wakeUpHour & 255) << 24);
        iArr[1] = this.wakeUpMin & 255;
        iArr[1] = iArr[1] | ((this.wakeUpSecond & 255) << 8);
        iArr[1] = iArr[1] | ((this.otaState & 255) << 16);
        return iArr;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        public PsuOtaMsg buildResetPsu() {
            PsuOtaMsg psuOtaMsg = new PsuOtaMsg();
            psuOtaMsg.setPsuResetReq(1);
            return psuOtaMsg;
        }

        public PsuOtaMsg buildUpdateState(int i) {
            PsuOtaMsg psuOtaMsg = new PsuOtaMsg();
            psuOtaMsg.setOtaState(i);
            return psuOtaMsg;
        }

        public PsuOtaMsg buildScheduleWakeUp(int i, int i2, int i3, int i4) {
            PsuOtaMsg psuOtaMsg = new PsuOtaMsg();
            psuOtaMsg.setWakeUpSetReq(1);
            psuOtaMsg.setWakeUpSetType(i);
            psuOtaMsg.setWakeUpHour(i2);
            psuOtaMsg.setWakeUpMin(i3);
            psuOtaMsg.setWakeUpSecond(i4);
            return psuOtaMsg;
        }

        public PsuOtaMsg buildCancelWakeUp() {
            PsuOtaMsg psuOtaMsg = new PsuOtaMsg();
            psuOtaMsg.setWakeUpSetReq(2);
            return psuOtaMsg;
        }
    }
}

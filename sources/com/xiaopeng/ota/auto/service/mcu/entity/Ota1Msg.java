package com.xiaopeng.ota.auto.service.mcu.entity;
/* loaded from: classes2.dex */
public class Ota1Msg {
    private static final int ANDROID_RESET = 1;
    private static final int ANDROID_UPGRADE_FAILED = 2;
    private static final int ANDROID_UPGRADE_SUCCESS = 1;
    private static final int PRO_COND_STATE = 2;
    private int ardOtaCheck;
    private int ardOtaState;
    private int ardResetNotify;
    private int otaMode;
    private int proCondReq;

    private Ota1Msg() {
    }

    public int[] getData() {
        int[] iArr = new int[4];
        iArr[0] = this.proCondReq & 255;
        iArr[0] = iArr[0] | ((this.otaMode & 255) << 8);
        iArr[0] = iArr[0] | ((this.ardOtaState & 255) << 16);
        iArr[0] = iArr[0] | ((this.ardOtaCheck & 255) << 24);
        iArr[1] = this.ardResetNotify & 255;
        return iArr;
    }

    public int getProCondReq() {
        return this.proCondReq;
    }

    void setProCondReq(int i) {
        this.proCondReq = i;
    }

    public int getOtaMode() {
        return this.otaMode;
    }

    void setOtaMode(int i) {
        this.otaMode = i;
    }

    public int getArdOtaState() {
        return this.ardOtaState;
    }

    void setArdOtaState(int i) {
        this.ardOtaState = i;
    }

    public int getArdOtaCheck() {
        return this.ardOtaCheck;
    }

    void setArdOtaCheck(int i) {
        this.ardOtaCheck = i;
    }

    public int getArdResetNotify() {
        return this.ardResetNotify;
    }

    void setArdResetNotify(int i) {
        this.ardResetNotify = i;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        public Ota1Msg buildSendProgramRequest() {
            Ota1Msg ota1Msg = new Ota1Msg();
            ota1Msg.setProCondReq(2);
            return ota1Msg;
        }

        public Ota1Msg buildOtaMode(int i) {
            Ota1Msg ota1Msg = new Ota1Msg();
            ota1Msg.setOtaMode(i);
            return ota1Msg;
        }

        public Ota1Msg buildUpgradeSuccess() {
            Ota1Msg ota1Msg = new Ota1Msg();
            ota1Msg.setArdOtaState(1);
            return ota1Msg;
        }

        public Ota1Msg buildUpgradeFail() {
            Ota1Msg ota1Msg = new Ota1Msg();
            ota1Msg.setArdOtaState(2);
            return ota1Msg;
        }

        public Ota1Msg buildReset() {
            Ota1Msg ota1Msg = new Ota1Msg();
            ota1Msg.setArdResetNotify(1);
            return ota1Msg;
        }
    }
}

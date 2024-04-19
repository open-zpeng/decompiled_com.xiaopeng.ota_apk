package com.xiaopeng.ota.presenter.update.bean;

import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class EcuVersion {
    private static final String TAG = "EcuVersion";
    private long checkTime;
    private String desc;
    private String fingerprint;
    private String hv;
    private String name;
    private String sv;
    private long syncTime;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getSv() {
        return this.sv;
    }

    public void setSv(String str) {
        this.sv = str;
    }

    public String getHv() {
        return this.hv;
    }

    public void setHv(String str) {
        this.hv = str;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String str) {
        this.fingerprint = str;
    }

    public long getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(long j) {
        this.checkTime = j;
    }

    public long getSyncTime() {
        return this.syncTime;
    }

    public void setSyncTime(long j) {
        this.syncTime = j;
    }

    public void createDesc() {
        try {
            this.desc = EcuType.valueByName(this.name).desc();
        } catch (Exception e) {
            LogUtils.w(TAG, e, "Create desc by name(%s) fail", this.name);
        }
    }

    public boolean versionEquals(EcuVersion ecuVersion) {
        return this.name.equals(ecuVersion.name) && this.sv.equals(ecuVersion.sv) && this.hv.equals(ecuVersion.hv) && this.fingerprint.equals(ecuVersion.fingerprint);
    }

    public void refresh(EcuVersion ecuVersion) {
        this.sv = ecuVersion.sv;
        this.hv = ecuVersion.hv;
        this.fingerprint = ecuVersion.fingerprint;
        this.desc = ecuVersion.desc;
        this.checkTime = ecuVersion.checkTime;
        this.syncTime = ecuVersion.syncTime;
    }
}

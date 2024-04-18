package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
public class AppVersion {
    private String packageName;
    private long versionCode;
    private String versionName;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(long j) {
        this.versionCode = j;
    }
}

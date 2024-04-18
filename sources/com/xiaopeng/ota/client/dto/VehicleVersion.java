package com.xiaopeng.ota.client.dto;
/* loaded from: classes2.dex */
public class VehicleVersion {
    private int source;
    private String versionCode;
    private String versionName;

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String str) {
        this.versionCode = str;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }
}

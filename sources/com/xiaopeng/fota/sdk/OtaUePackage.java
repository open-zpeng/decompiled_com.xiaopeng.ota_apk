package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
public class OtaUePackage {
    private long campaignId;
    private int ecuId;
    private String file;
    private int packageId;
    private String packageName;
    private long versionCode;
    private String versionName;
    private boolean waitExit;

    public long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(long j) {
        this.campaignId = j;
    }

    public int getPackageId() {
        return this.packageId;
    }

    public void setPackageId(int i) {
        this.packageId = i;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String str) {
        this.file = str;
    }

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

    public boolean isWaitExit() {
        return this.waitExit;
    }

    public void setWaitExit(boolean z) {
        this.waitExit = z;
    }
}

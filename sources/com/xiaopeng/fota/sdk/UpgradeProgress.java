package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
public class UpgradeProgress {
    private long campaignId;
    private int category;
    private int ecuId;
    private int pkgCount;
    private int pkgIndex;
    private int progress;
    private int remainingSeconds;
    private int upgradeState;

    public long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(long j) {
        this.campaignId = j;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }

    public int getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(int i) {
        this.ecuId = i;
    }

    public int getUpgradeState() {
        return this.upgradeState;
    }

    public void setUpgradeState(int i) {
        this.upgradeState = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getPkgIndex() {
        return this.pkgIndex;
    }

    public void setPkgIndex(int i) {
        this.pkgIndex = i;
    }

    public int getPkgCount() {
        return this.pkgCount;
    }

    public void setPkgCount(int i) {
        this.pkgCount = i;
    }

    public int getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public void setRemainingSeconds(int i) {
        this.remainingSeconds = i;
    }
}

package com.xiaopeng.ota.bean;

import java.util.List;
/* loaded from: classes2.dex */
public class VersionInfo {
    private String htmlReleaseNotes;
    private List<String> imageUrls;
    private String upgradeTime;
    private String version;
    private List<String> videoUrls;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getUpgradeTime() {
        return this.upgradeTime;
    }

    public void setUpgradeTime(String str) {
        this.upgradeTime = str;
    }

    public String getHtmlReleaseNotes() {
        return this.htmlReleaseNotes;
    }

    public void setHtmlReleaseNotes(String str) {
        this.htmlReleaseNotes = str;
    }

    public List<String> getImageUrls() {
        return this.imageUrls;
    }

    public void setImageUrls(List<String> list) {
        this.imageUrls = list;
    }

    public List<String> getVideoUrls() {
        return this.videoUrls;
    }

    public void setVideoUrls(List<String> list) {
        this.videoUrls = list;
    }
}

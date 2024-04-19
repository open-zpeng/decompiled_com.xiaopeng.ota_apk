package com.xiaopeng.sota.sdk.manager.campaign;

import java.util.List;
/* loaded from: classes2.dex */
public class CampaignPackage {
    private List<String> downloadUrls;
    private String hash;
    private Long id;
    private String name;
    private String packageName;
    private Long size;
    private String versionCode;
    private String versionName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }

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

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public List<String> getDownloadUrls() {
        return this.downloadUrls;
    }

    public void setDownloadUrls(List<String> list) {
        this.downloadUrls = list;
    }

    public String toString() {
        return "CampaignPackage{id=" + this.id + ", name='" + this.name + "', packageName='" + this.packageName + "', size=" + this.size + ", versionName='" + this.versionName + "', versionCode='" + this.versionCode + "', hash='" + this.hash + "', downloadUrls=" + this.downloadUrls + '}';
    }
}

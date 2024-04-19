package com.xiaopeng.ota.presenter.update.bean;

import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public class Package {
    private String bid;
    private long campaignId;
    private Integer category;
    private Integer downloadUrlIndex;
    private List<String> downloadUrls;
    private String encrypt;
    private String environment;
    private Integer estimateCostSecs;
    private String hash;
    private List<HashPart> hashParts;
    private String name;
    private Integer recovery;
    private String releaseNotes;
    private long size;
    private String typeName;
    private String version;

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(long j) {
        this.campaignId = j;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String str) {
        this.bid = str;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public Integer getRecovery() {
        return this.recovery;
    }

    public void setRecovery(Integer num) {
        this.recovery = num;
    }

    public String getReleaseNotes() {
        return this.releaseNotes;
    }

    public void setReleaseNotes(String str) {
        this.releaseNotes = str;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public List<HashPart> getHashParts() {
        return this.hashParts;
    }

    public void setHashParts(List<HashPart> list) {
        this.hashParts = list;
    }

    public List<String> getDownloadUrls() {
        return this.downloadUrls;
    }

    public void setDownloadUrls(List<String> list) {
        this.downloadUrls = list;
    }

    public Integer getDownloadUrlIndex() {
        return this.downloadUrlIndex;
    }

    public void setDownloadUrlIndex(Integer num) {
        this.downloadUrlIndex = num;
    }

    public String getEncrypt() {
        return this.encrypt;
    }

    public void setEncrypt(String str) {
        this.encrypt = str;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer num) {
        this.category = num;
    }

    public Integer getEstimateCostSecs() {
        return this.estimateCostSecs;
    }

    public void setEstimateCostSecs(Integer num) {
        this.estimateCostSecs = num;
    }

    /* loaded from: classes2.dex */
    public static class HashPart {
        private long length;
        private long offset;
        private String value;

        public long getOffset() {
            return this.offset;
        }

        public void setOffset(long j) {
            this.offset = j;
        }

        public long getLength() {
            return this.length;
        }

        public void setLength(long j) {
            this.length = j;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HashPart hashPart = (HashPart) obj;
            return this.offset == hashPart.offset && this.length == hashPart.length && Objects.equals(this.value, hashPart.value);
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.offset), Long.valueOf(this.length), this.value);
        }
    }
}

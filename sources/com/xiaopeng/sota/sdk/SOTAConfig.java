package com.xiaopeng.sota.sdk;
/* loaded from: classes2.dex */
public class SOTAConfig {
    private String appId;
    private String appSecret;
    private String host;

    public SOTAConfig() {
    }

    public SOTAConfig(String str, String str2, String str3) {
        this.host = str;
        this.appId = str2;
        this.appSecret = str3;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String str) {
        this.appSecret = str;
    }
}

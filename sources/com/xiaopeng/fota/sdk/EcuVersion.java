package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
public class EcuVersion {
    private byte activeZone;
    private String fingerprint;
    private String hardwareVersion;
    private String softwareVersion;

    public byte getActiveZone() {
        return this.activeZone;
    }

    public void setActiveZone(byte b) {
        this.activeZone = b;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String str) {
        this.fingerprint = str;
    }
}

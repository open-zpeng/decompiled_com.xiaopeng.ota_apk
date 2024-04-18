package com.xiaopeng.ota.sdk.download;
/* loaded from: classes2.dex */
public class DownloadErrorException extends Exception {
    private int code;

    public DownloadErrorException(int i, String str) {
        super(str);
        this.code = i;
    }

    public DownloadErrorException(int i, String str, Throwable th) {
        super(str, th);
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}

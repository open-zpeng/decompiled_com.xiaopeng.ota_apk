package com.xiaopeng.ota.sdk.common.log;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class LogMessage implements Serializable {
    private int level;
    private String message;
    private String tag;
    private long threadId;
    private Throwable throwable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogMessage(String str, int i, String str2) {
        this.threadId = Thread.currentThread().getId();
        this.tag = str;
        this.level = i;
        this.message = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogMessage(String str, int i, String str2, Throwable th) {
        this(str, i, str2);
        this.throwable = th;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable th) {
        this.throwable = th;
    }

    public long getThreadId() {
        return this.threadId;
    }
}

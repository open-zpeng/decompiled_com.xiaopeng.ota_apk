package com.xiaopeng.ota.sdk.common.util.retry;
/* loaded from: classes2.dex */
public class RetryContext {
    private int count;
    private Exception lastException;

    public int getCount() {
        return this.count;
    }

    public Throwable getLastException() {
        return this.lastException;
    }

    public void registerException(Exception exc) {
        this.lastException = exc;
        if (exc != null) {
            this.count++;
        }
    }
}

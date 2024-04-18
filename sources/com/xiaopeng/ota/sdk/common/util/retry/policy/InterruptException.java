package com.xiaopeng.ota.sdk.common.util.retry.policy;
/* loaded from: classes2.dex */
public class InterruptException extends Exception {
    private Exception original;

    public InterruptException(String str) {
        super(str);
    }

    public InterruptException(String str, Exception exc) {
        super(str, exc);
        this.original = exc;
    }

    public InterruptException(Exception exc) {
        super(exc);
        this.original = exc;
    }

    public Exception getOriginal() {
        return this.original;
    }
}

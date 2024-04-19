package com.xiaopeng.ota.sdk.common.util.retry;
/* loaded from: classes2.dex */
public abstract class RetryPolicy {
    public abstract boolean canRetry(RetryContext retryContext);

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerException(RetryContext retryContext, Exception exc) {
        retryContext.registerException(exc);
    }
}

package com.xiaopeng.ota.sdk.common.util.retry;

import java.lang.Exception;
/* loaded from: classes2.dex */
public interface RetryExecution<T, E extends Exception> {
    T doWithRetry(RetryContext retryContext) throws Exception;
}

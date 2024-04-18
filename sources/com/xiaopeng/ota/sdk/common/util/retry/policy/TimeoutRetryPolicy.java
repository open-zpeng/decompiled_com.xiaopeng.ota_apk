package com.xiaopeng.ota.sdk.common.util.retry.policy;

import com.xiaopeng.ota.sdk.common.util.retry.RetryContext;
import com.xiaopeng.ota.sdk.common.util.retry.RetryPolicy;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class TimeoutRetryPolicy extends RetryPolicy {
    private static final long DEFAULT_RETRY_INTERVAL = 5000;
    private static final int DEFAULT_RETRY_TIMES = 3;
    private long interval;
    private int retryTimes;

    private TimeoutRetryPolicy(int i, long j) {
        this.retryTimes = i;
        this.interval = j;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public long getInterval() {
        return this.interval;
    }

    @Override // com.xiaopeng.ota.sdk.common.util.retry.RetryPolicy
    public boolean canRetry(RetryContext retryContext) {
        Throwable lastException = retryContext.getLastException();
        if ((lastException == null || !interruptForException(lastException)) && retryContext.getCount() <= getRetryTimes()) {
            if (getInterval() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(getInterval());
                    return true;
                } catch (InterruptedException unused) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private long interval;
        private int retryTimes;

        public Builder setRetryTimes(int i) {
            this.retryTimes = i;
            return this;
        }

        public Builder setInterval(long j) {
            this.interval = j;
            return this;
        }

        public TimeoutRetryPolicy build() {
            return new TimeoutRetryPolicy(this.retryTimes, this.interval);
        }

        public TimeoutRetryPolicy buildDefault() {
            return new TimeoutRetryPolicy(3, TimeoutRetryPolicy.DEFAULT_RETRY_INTERVAL);
        }
    }

    public boolean interruptForException(Throwable th) {
        return th != null && (th instanceof InterruptException);
    }
}

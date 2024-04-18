package com.xiaopeng.ota.sdk.common.util.retry;

import com.xiaopeng.ota.sdk.common.util.retry.policy.TimeoutRetryPolicy;
/* loaded from: classes2.dex */
public class RetryTemplate {
    private RetryContext context;
    private volatile boolean interrupted;
    private RetryPolicy policy;

    public RetryTemplate(RetryPolicy retryPolicy) {
        this.policy = retryPolicy;
        this.context = new RetryContext();
    }

    public RetryTemplate(RetryPolicy retryPolicy, RetryContext retryContext) {
        this.policy = retryPolicy;
        this.context = retryContext;
    }

    public final <T, E extends Exception> T execute(RetryExecution<T, E> retryExecution) throws Exception {
        try {
            return retryExecution.doWithRetry(this.context);
        } catch (Exception e) {
            getPolicy().registerException(getContext(), e);
            if (!isInterrupted() && canRetry(getPolicy(), getContext())) {
                return (T) execute(retryExecution);
            }
            throw e;
        }
    }

    private boolean canRetry(RetryPolicy retryPolicy, RetryContext retryContext) {
        return retryPolicy.canRetry(retryContext);
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    public void interrupt() {
        this.interrupted = true;
    }

    public RetryPolicy getPolicy() {
        return this.policy;
    }

    public RetryContext getContext() {
        return this.context;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private RetryPolicy policy;

        public Builder setPolicy(RetryPolicy retryPolicy) {
            this.policy = retryPolicy;
            return this;
        }

        public RetryTemplate build() {
            return new RetryTemplate(this.policy);
        }

        public RetryTemplate buildDefault() {
            return new RetryTemplate(new TimeoutRetryPolicy.Builder().buildDefault());
        }
    }
}

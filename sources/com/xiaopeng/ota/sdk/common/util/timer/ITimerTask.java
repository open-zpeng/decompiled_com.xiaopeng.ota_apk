package com.xiaopeng.ota.sdk.common.util.timer;
/* loaded from: classes2.dex */
public interface ITimerTask {
    public static final String DEFAULT_NAME = "TimerTask";

    void adjustInterval(long j);

    boolean isStopped();

    void resume();

    void start(long j, long j2);

    void stop();

    void suspend();

    void task();
}

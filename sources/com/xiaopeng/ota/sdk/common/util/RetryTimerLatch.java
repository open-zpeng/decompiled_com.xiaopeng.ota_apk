package com.xiaopeng.ota.sdk.common.util;

import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class RetryTimerLatch {
    private static String TAG = "RetryTimerLatch";
    private volatile boolean active;
    private volatile int count;
    private volatile long lastUpdateTime;
    private int max;
    private long maxTime;
    private String name;
    private int start;
    private long unitTime;
    public static long SECOND_1 = TimeUnit.SECONDS.toMillis(1);
    public static long MINUTE_1 = TimeUnit.MINUTES.toMillis(1);
    public static long HOUR_1 = TimeUnit.HOURS.toMillis(1);

    public RetryTimerLatch(String str, int i, long j, long j2) {
        this(str, i, Integer.MAX_VALUE, j, j2);
    }

    public RetryTimerLatch(String str, int i, int i2, long j, long j2) {
        this.name = str;
        this.start = i;
        this.max = i2;
        this.unitTime = j;
        this.maxTime = j2;
        this.active = false;
    }

    public synchronized long check() {
        long min;
        min = Math.min(this.maxTime, this.count < this.start ? 0L : ((this.count - this.start) * (this.count - this.start)) * this.unitTime) - (System.currentTimeMillis() - this.lastUpdateTime);
        if (min <= 0) {
            min = 0;
        }
        return min;
    }

    public synchronized void increase() {
        int i = this.count;
        this.count = i + 1;
        if (i > this.max) {
            inactivate();
        }
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public synchronized void reset() {
        this.count = 0;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public synchronized void activate() {
        activate(this.max);
    }

    public synchronized void activate(int i) {
        Logger.d(TAG, "%s activate(max=%d)", this.name, Integer.valueOf(i));
        if (!this.active) {
            this.active = true;
            reset();
        }
        this.max = i;
    }

    public synchronized void inactivate() {
        Logger.d(TAG, "%s inactivate(count=%d, max=%d)", this.name, Integer.valueOf(this.count), Integer.valueOf(this.max));
        this.active = false;
        reset();
    }

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public boolean isActive() {
        return this.active;
    }
}

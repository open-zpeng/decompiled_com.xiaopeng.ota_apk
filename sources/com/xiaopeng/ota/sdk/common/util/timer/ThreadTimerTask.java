package com.xiaopeng.ota.sdk.common.util.timer;

import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public abstract class ThreadTimerTask implements ITimerTask {
    private long mDelayMillis;
    private volatile long mInterval;
    private String mName;
    private volatile boolean mResumed;
    private volatile boolean mTaskFinished;
    private Thread mThread;

    public ThreadTimerTask() {
        this.mName = ITimerTask.DEFAULT_NAME;
        this.mDelayMillis = 0L;
        this.mInterval = 0L;
    }

    public ThreadTimerTask(String str) {
        this.mName = ITimerTask.DEFAULT_NAME;
        this.mDelayMillis = 0L;
        this.mInterval = 0L;
        this.mName = str;
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public void start(long j, long j2) {
        this.mTaskFinished = false;
        this.mResumed = true;
        this.mDelayMillis = j;
        this.mInterval = j2;
        this.mThread = new Thread(new Runnable() { // from class: com.xiaopeng.ota.sdk.common.util.timer.-$$Lambda$ThreadTimerTask$7B939RsS_X1V6O_me4irQloGXuM
            @Override // java.lang.Runnable
            public final void run() {
                ThreadTimerTask.this.lambda$start$0$ThreadTimerTask();
            }
        });
        this.mThread.start();
    }

    public /* synthetic */ void lambda$start$0$ThreadTimerTask() {
        Logger.d(this.mName, "Tid(%d) task running...delay(%d), resumed(%b)", Long.valueOf(Thread.currentThread().getId()), Long.valueOf(this.mDelayMillis), Boolean.valueOf(this.mResumed));
        if (this.mDelayMillis > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(this.mDelayMillis);
            } catch (InterruptedException unused) {
            }
        }
        if (this.mInterval == 0) {
            if (!this.mTaskFinished) {
                if (this.mResumed) {
                    task();
                }
            } else {
                Logger.d(this.mName, "Tid(%d) Task has stopped", Long.valueOf(Thread.currentThread().getId()));
            }
        } else {
            while (!this.mTaskFinished && Thread.currentThread().getId() == this.mThread.getId()) {
                if (this.mResumed) {
                    task();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(this.mInterval);
                } catch (InterruptedException unused2) {
                }
            }
        }
        Logger.d(this.mName, "Tid(%d) exit task", Long.valueOf(Thread.currentThread().getId()));
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public void adjustInterval(long j) {
        if (this.mInterval == 0) {
            Logger.d(this.mName, "Task has start as single task, can not adjust interval", new Object[0]);
        } else {
            this.mInterval = j;
        }
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public void resume() {
        if (this.mResumed) {
            Logger.d(this.mName, "Task has resumed", new Object[0]);
        } else {
            this.mResumed = true;
        }
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public void suspend() {
        this.mResumed = false;
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public void stop() {
        String str = this.mName;
        Object[] objArr = new Object[1];
        Thread thread = this.mThread;
        objArr[0] = Long.valueOf(thread == null ? 0L : thread.getId());
        Logger.d(str, "Tid(%d) stop", objArr);
        this.mResumed = false;
        this.mTaskFinished = true;
    }

    @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
    public boolean isStopped() {
        return this.mTaskFinished;
    }
}

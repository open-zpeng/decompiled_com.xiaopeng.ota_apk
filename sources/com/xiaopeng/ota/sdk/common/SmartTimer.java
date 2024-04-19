package com.xiaopeng.ota.sdk.common;

import com.xiaopeng.libconfig.ipc.AccountConfig;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.timer.ThreadTimerTask;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class SmartTimer {
    private static final String TAG = "SmartTimer";
    private long mCurrentInterval = MIN_INTERVAL;
    private Runnable mRunnable;
    private String mTimerName;
    private ThreadTimerTask mTimerTask;
    private static final long MIN_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    private static final long MAX_INTERVAL = TimeUnit.SECONDS.toMillis(60);
    private static final long DELAY = TimeUnit.SECONDS.toMillis(5);

    public SmartTimer(String str, Runnable runnable) {
        this.mTimerName = str;
        this.mRunnable = runnable;
        this.mTimerTask = new ThreadTimerTask(this.mTimerName) { // from class: com.xiaopeng.ota.sdk.common.SmartTimer.1
            @Override // com.xiaopeng.ota.sdk.common.util.timer.ITimerTask
            public void task() {
                if (SmartTimer.this.mRunnable != null) {
                    SmartTimer.this.mRunnable.run();
                }
            }
        };
    }

    public synchronized void increase() {
    }

    public synchronized void reduce() {
    }

    public void start() {
        Logger.d(TAG, AccountConfig.FaceIDRegisterAction.STATUS_START, new Object[0]);
        this.mTimerTask.start(DELAY, this.mCurrentInterval);
    }

    public void resume() {
        Logger.d(TAG, "resume", new Object[0]);
        this.mTimerTask.resume();
    }

    public void suspend() {
        Logger.d(TAG, "suspend", new Object[0]);
        this.mTimerTask.suspend();
    }

    public void stop() {
        Logger.d(TAG, "stop", new Object[0]);
        this.mTimerTask.stop();
    }
}

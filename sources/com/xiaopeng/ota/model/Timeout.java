package com.xiaopeng.ota.model;

import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes2.dex */
public abstract class Timeout {
    private Timer mTimer;

    public abstract void timeout();

    public void block(long j) {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.xiaopeng.ota.model.Timeout.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Timeout.this.timeout();
            }
        }, j);
    }

    public void reset() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }
}

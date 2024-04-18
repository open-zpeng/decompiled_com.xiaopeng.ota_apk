package com.xiaopeng.ota.model;

import android.os.CountDownTimer;
import com.xiaopeng.ota.view.dialog.BaseDialog;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public abstract class TimeCountDown<T> extends CountDownTimer {
    private WeakReference<T> mRef;

    protected abstract void onTick(boolean z);

    public TimeCountDown(T t, long j, long j2) {
        super(j, j2);
        this.mRef = new WeakReference<>(t);
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        T t = this.mRef.get();
        if (t == null) {
            cancel();
        } else if ((t instanceof BaseDialog) && !((BaseDialog) t).isShowing()) {
            cancel();
        } else {
            onTick(false);
        }
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        T t = this.mRef.get();
        if (t == null) {
            cancel();
        } else if ((t instanceof BaseDialog) && !((BaseDialog) t).isShowing()) {
            cancel();
        } else {
            onTick(true);
        }
    }
}

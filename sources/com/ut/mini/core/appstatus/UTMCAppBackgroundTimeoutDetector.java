package com.ut.mini.core.appstatus;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import com.alibaba.mtl.log.c;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class UTMCAppBackgroundTimeoutDetector implements UTMCAppStatusCallbacks {
    private static UTMCAppBackgroundTimeoutDetector a;
    private long B = 0;

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityStopped(Activity activity) {
    }

    private UTMCAppBackgroundTimeoutDetector() {
    }

    public static synchronized UTMCAppBackgroundTimeoutDetector getInstance() {
        UTMCAppBackgroundTimeoutDetector uTMCAppBackgroundTimeoutDetector;
        synchronized (UTMCAppBackgroundTimeoutDetector.class) {
            if (a == null) {
                a = new UTMCAppBackgroundTimeoutDetector();
            }
            uTMCAppBackgroundTimeoutDetector = a;
        }
        return uTMCAppBackgroundTimeoutDetector;
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onSwitchBackground() {
        this.B = SystemClock.elapsedRealtime();
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onSwitchForeground() {
        if (0 != this.B && SystemClock.elapsedRealtime() - this.B > OTAConstants.CHECK_WAIT_INTERVAL) {
            c.a().c(new HashMap());
        }
        this.B = 0L;
    }
}

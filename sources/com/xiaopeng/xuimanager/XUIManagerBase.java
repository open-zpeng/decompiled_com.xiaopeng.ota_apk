package com.xiaopeng.xuimanager;

import android.os.IBinder;
/* loaded from: classes2.dex */
public interface XUIManagerBase {
    default void onServerDisconnected() {
    }

    default void onXUIConnected(IBinder iBinder) {
    }

    void onXUIDisconnected();

    default void setServiceName(String str) {
    }

    default String getServiceName() {
        return getClass().getSimpleName();
    }
}

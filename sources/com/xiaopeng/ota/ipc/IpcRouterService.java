package com.xiaopeng.ota.ipc;

import com.xiaopeng.lib.apirouter.server.IServicePublisher;
import com.xiaopeng.lib.apirouter.server.Publish;
import com.xiaopeng.ota.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes2.dex */
public class IpcRouterService implements IServicePublisher {
    private static final String TAG = "IpcRouterService";

    @Publish
    public void onReceiverData(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = str == null ? "null" : str;
        LogUtils.d(TAG, "Receive data id:%d, bundle:%s", objArr);
        IpcRouterEvent ipcRouterEvent = new IpcRouterEvent(i, str);
        if (EventBus.getDefault().hasSubscriberForEvent(IpcRouterEvent.class)) {
            EventBus.getDefault().post(ipcRouterEvent);
        } else {
            EventBus.getDefault().postSticky(ipcRouterEvent);
        }
    }
}

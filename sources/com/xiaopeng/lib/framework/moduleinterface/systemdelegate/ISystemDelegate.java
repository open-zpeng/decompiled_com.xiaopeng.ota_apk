package com.xiaopeng.lib.framework.moduleinterface.systemdelegate;

import android.os.RemoteException;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ISystemDelegate {
    @Nullable
    String getCertificate() throws RemoteException;

    void setSystemProperty(String key, String value) throws RemoteException;
}

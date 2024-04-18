package com.xiaopeng.lib.framework.moduleinterface.locationmodule;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public interface ILocationProvider {
    ILocation buildLocation();

    void publishLocation(@NonNull ILocation iLocation) throws ILocationServiceException;

    boolean serviceStarted();

    void start(@NonNull Context context) throws ILocationServiceException;
}

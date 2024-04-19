package com.xiaopeng.lib.framework.moduleinterface.syncmodule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface IOTPCallback {
    void onError(@NonNull String seq, @Nullable Integer code, @Nullable String errMsg);

    void onGetOTP(@NonNull String seq, @NonNull String deviceID, @NonNull String otp, long uid);
}

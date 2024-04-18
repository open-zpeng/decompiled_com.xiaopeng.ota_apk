package com.xiaopeng.fota.sdk;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public interface UpgradeExecutorBase {
    public static final byte RESET_EITHER = 0;
    public static final byte RESET_FORCE = 3;
    public static final byte RESET_HARD = 2;
    public static final byte RESET_SKIP = 4;
    public static final byte RESET_SOFT = 1;

    int activate(long j, byte b);

    int campaignCompleted(long j);

    int campaignStarted(long j);

    String challenge(@NonNull String str);

    int commit(long j);

    int getId();

    int getStatus();

    Object[] getVersions(Object obj);

    int prepare(@NonNull OtaUePackage otaUePackage);

    int reset(long j, byte b);

    int rollback(long j, byte b);

    int upgrade(@NonNull OtaUePackage otaUePackage, byte b);
}

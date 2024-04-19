package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
interface UpgradeAgentBase {
    int decrypt(long j, long j2, String str, String str2, String str3);

    int decrypt(long j, byte[] bArr);

    int download(long j, long j2, String str, long j3, String str2, String str3);

    int httpRequest(long j, int i, String str, String str2, String str3);
}

package com.xiaopeng.ota.sdk.common.util;
/* loaded from: classes2.dex */
public class CharUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPrintable(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}

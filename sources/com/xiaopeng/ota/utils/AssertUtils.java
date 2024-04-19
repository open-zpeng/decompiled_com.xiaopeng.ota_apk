package com.xiaopeng.ota.utils;
/* loaded from: classes2.dex */
public class AssertUtils {
    public static void notNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }
}

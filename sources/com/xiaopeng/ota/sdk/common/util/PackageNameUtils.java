package com.xiaopeng.ota.sdk.common.util;

import android.text.TextUtils;
/* loaded from: classes2.dex */
public class PackageNameUtils {
    private static final String DATA_PACKAGE_NAME_PREFIX = "package:";

    public static String getPackageName(String str) {
        return (TextUtils.isEmpty(str) || !str.startsWith(DATA_PACKAGE_NAME_PREFIX)) ? str : str.substring(8);
    }
}

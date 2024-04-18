package com.xiaopeng.ota.sdk.common.util;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes2.dex */
public class CheckUtils {
    private static final String NULL_STRING = "null";

    public static boolean isNotEmptyOrNullStr(String str) {
        return (TextUtils.isEmpty(str) || NULL_STRING.equalsIgnoreCase(str)) ? false : true;
    }

    public static void isNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object is null");
        }
    }

    public static void isNotEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("string is empty");
        }
    }

    public static void isAllNotNull(Object... objArr) {
        isNotNull(objArr);
        for (Object obj : objArr) {
            isNotNull(obj);
        }
    }

    public static void isNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void isNotEmpty(List<?> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
    }

    public static void isApplicationContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (!(context instanceof Application)) {
            throw new IllegalArgumentException("context is not application context");
        }
    }

    public static boolean equals(Integer num, int i) {
        return num != null && num.intValue() == i;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}

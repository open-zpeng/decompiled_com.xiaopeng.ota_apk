package com.xiaopeng.ota.sdk.common.util;

import android.text.TextUtils;
import android.util.Log;
/* loaded from: classes2.dex */
public class ExceptionUtils {
    public static String collectStack(Exception exc) {
        if (exc == null) {
            return "";
        }
        String stackTraceString = Log.getStackTraceString(exc);
        if (TextUtils.isEmpty(stackTraceString)) {
            stackTraceString = exc.getMessage();
        }
        return TextUtils.isEmpty(stackTraceString) ? exc.getClass().getSimpleName() : stackTraceString;
    }
}

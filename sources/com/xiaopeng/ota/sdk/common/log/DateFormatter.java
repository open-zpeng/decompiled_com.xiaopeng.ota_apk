package com.xiaopeng.ota.sdk.common.log;

import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class DateFormatter {
    private static ThreadLocal<SimpleDateFormat> sDefaultDateFormat = new ThreadLocal<>();

    public static SimpleDateFormat getDefaultDateFormat() {
        return getDateFormat(sDefaultDateFormat, "yyyy-MM-dd HH:mm:ss");
    }

    private static SimpleDateFormat getDateFormat(ThreadLocal<SimpleDateFormat> threadLocal, String str) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str, Locale.getDefault());
            threadLocal.set(simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }
}

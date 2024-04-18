package com.xiaopeng.ota.sdk.common.util;

import android.database.Cursor;
import android.util.Log;
import java.io.Closeable;
/* loaded from: classes2.dex */
public class IoUtils {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Log.e("IoUtils", "Close exception", e);
            }
        }
    }

    public static void close(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                Log.e("IoUtils", "Close exception", e);
            }
        }
    }
}

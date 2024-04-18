package com.xiaopeng.ota.sdk.common.log;

import android.util.Log;
import java.io.FilterWriter;
import java.io.Writer;
/* loaded from: classes2.dex */
public class QuietWriter extends FilterWriter {
    private static final String TAG = "QuietWriter";

    public QuietWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str != null) {
            try {
                this.out.write(str);
            } catch (Exception e) {
                String str2 = TAG;
                Log.e(str2, "Failed to write [" + str + "].", e);
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() {
        try {
            this.out.flush();
        } catch (Exception e) {
            Log.e(TAG, "Failed to flush writer,", e);
        }
    }
}

package com.xiaopeng.ota.sdk.common.log;

import android.util.Log;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import org.tukaani.xz.common.Util;
/* loaded from: classes2.dex */
public class Logger {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 3;
    public static final int LOG_LEVEL_ERROR = 6;
    public static final int LOG_LEVEL_INFO = 4;
    public static final int LOG_LEVEL_NONE = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARN = 5;
    private static Writer mWriter = null;
    private static long mWriterExpireIn = 0;
    private static Runnable mWriterTimeoutRunnable = null;
    private static int sLogLevel = Integer.MAX_VALUE;

    public static void setDefaultLogLevel(int i) {
        sLogLevel = i;
    }

    public static int getDefaultLogLevel() {
        return sLogLevel;
    }

    private static void setOutputStream(OutputStream outputStream) {
        setOutputStream(outputStream, Util.VLI_MAX, null);
    }

    private static void setOutputStream(OutputStream outputStream, long j, Runnable runnable) {
        closeOutputStream();
        mWriter = outputStream == null ? null : new PrintWriter(outputStream);
        mWriterExpireIn = System.currentTimeMillis() + j;
        mWriterTimeoutRunnable = runnable;
    }

    public static void closeOutputStream() {
        Writer writer = mWriter;
        if (writer != null) {
            IoUtils.close(writer);
            mWriter = null;
        }
    }

    public static void releaseSpace() {
        LoggerThread.enqueue(new LogMessage(null, Integer.MAX_VALUE, null));
    }

    public static void i(String str, String str2, Object... objArr) {
        write(4, str, strFormat(str2, objArr), null);
    }

    public static void d(String str, String str2, Object... objArr) {
        write(3, str, strFormat(str2, objArr), null);
    }

    public static void e(String str, String str2, Object... objArr) {
        write(6, str, strFormat(str2, objArr), null);
    }

    public static void w(String str, String str2, Object... objArr) {
        write(5, str, strFormat(str2, objArr), null);
    }

    public static void i(String str, Throwable th, String str2, Object... objArr) {
        write(4, str, strFormat(str2, objArr), th);
    }

    public static void d(String str, Throwable th, String str2, Object... objArr) {
        write(3, str, strFormat(str2, objArr), th);
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        write(6, str, strFormat(str2, objArr), th);
    }

    public static void w(String str, Throwable th, String str2, Object... objArr) {
        write(5, str, strFormat(str2, objArr), th);
    }

    private static String strFormat(String str, Object[] objArr) {
        return (objArr == null || objArr.length <= 0) ? str : String.format(str, objArr);
    }

    private static void write(int i, String str, String str2, Throwable th) {
        if (i < getDefaultLogLevel()) {
            return;
        }
        if (mWriter != null) {
            writeOutputStream(i, str, str2, th);
            if (System.currentTimeMillis() > mWriterExpireIn) {
                Runnable runnable = mWriterTimeoutRunnable;
                if (runnable != null) {
                    runnable.run();
                }
                closeOutputStream();
                return;
            }
            return;
        }
        writeLogcat(i, str, str2, th);
    }

    private static void writeLogcat(int i, String str, String str2, Throwable th) {
        LoggerThread.enqueue(new LogMessage(str, i, str2, th));
        if ("OTAHttp".equals(str)) {
            return;
        }
        if (th == null) {
            Log.println(i, str, str2);
            return;
        }
        Log.println(i, str, str2 + "\n" + Log.getStackTraceString(th));
    }

    private static void writeOutputStream(int i, String str, String str2, Throwable th) {
        String str3;
        String str4 = "V";
        switch (i) {
            case 3:
                str4 = "D";
                break;
            case 4:
                str4 = "I";
                break;
            case 5:
                str4 = "W";
                break;
            case 6:
                str4 = "E";
                break;
            case 7:
                str4 = "A";
                break;
        }
        try {
            Writer writer = mWriter;
            Object[] objArr = new Object[5];
            objArr[0] = com.xiaopeng.ota.sdk.common.util.DateFormatter.getDefaultDateFormat().format(new Date());
            objArr[1] = str4;
            objArr[2] = str;
            objArr[3] = str2;
            if (th == null) {
                str3 = "";
            } else {
                str3 = "\n" + Log.getStackTraceString(th);
            }
            objArr[4] = str3;
            writer.write(String.format("%s %s/%s %s%s\n", objArr));
            mWriter.flush();
        } catch (Exception unused) {
            writeLogcat(i, str, str2, th);
        }
    }
}

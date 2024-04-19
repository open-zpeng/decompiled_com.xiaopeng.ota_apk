package com.xiaopeng.ota.sdk.common.log;

import android.util.Log;
import java.util.Date;
/* loaded from: classes2.dex */
public class SimpleLayout {
    public static final String HEADER_SEP = "############################";
    public static final String LINE_SEP = System.getProperty("line.separator");
    private static final String SLASH = "/";
    private static final String SPACE = " ";
    private static final String UNDER_LINE = "-";
    StringBuffer buffer;
    private String moduleName;

    private String getLogLevel(int i) {
        switch (i) {
            case 2:
            default:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
        }
    }

    public boolean ignoresThrowable() {
        return false;
    }

    public SimpleLayout() {
        this.moduleName = "unknown";
        this.buffer = new StringBuffer(128);
    }

    public SimpleLayout(String str) {
        this.moduleName = "unknown";
        this.buffer = new StringBuffer(128);
        this.moduleName = str;
    }

    public String format(LogMessage logMessage) {
        Throwable throwable;
        this.buffer.setLength(0);
        this.buffer.append(DateFormatter.getDefaultDateFormat().format(new Date()));
        this.buffer.append(SPACE);
        this.buffer.append(this.moduleName);
        this.buffer.append(UNDER_LINE);
        this.buffer.append(logMessage.getThreadId());
        this.buffer.append(SPACE);
        this.buffer.append(getLogLevel(logMessage.getLevel()));
        this.buffer.append("/");
        this.buffer.append(logMessage.getTag());
        this.buffer.append(SPACE);
        this.buffer.append(logMessage.getMessage());
        if (!ignoresThrowable() && (throwable = logMessage.getThrowable()) != null) {
            this.buffer.append(LINE_SEP);
            this.buffer.append(Log.getStackTraceString(throwable));
        }
        this.buffer.append(LINE_SEP);
        return this.buffer.toString();
    }
}

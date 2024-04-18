package com.xiaopeng.ota.sdk.common.log;

import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.ota.sdk.common.util.CheckUtils;
import com.xiaopeng.ota.sdk.common.util.FileUtils;
import com.xiaopeng.ota.sdk.common.util.TimeUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes2.dex */
public class LogWriter {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final int DEFAULT_BUF_SIZE = 8192;
    private static final String LOG_DIRECTORY = "/data/Log/ota/";
    private static final String LOG_SUFFIX = "log";
    public static final int MAX_DAYS_LOG = 7;
    private static final String TAG = "LogWriter";
    private int mCapacity;
    private boolean mDeleteExpired;
    private String mFileName;
    private String mHeader;
    private SimpleLayout mLayout;
    private QuietWriter mQuietWriter;
    private boolean mBufferedIO = false;
    private boolean mAppend = true;
    private int mBufferedSize = 8192;
    private boolean mImmediatelyFlush = true;

    public LogWriter() {
        this.mDeleteExpired = false;
        this.mCapacity = 7;
        LogConfiguration configuration = LogFactory.getConfiguration();
        Log.d(TAG, configuration != null ? configuration.toString() : "log configuration is null");
        if (configuration != null) {
            this.mLayout = new SimpleLayout(configuration.getModuleName());
            this.mDeleteExpired = configuration.isDeleteExpired();
            this.mHeader = configuration.getHeader();
            this.mCapacity = Math.max(7, configuration.getCapacity());
            return;
        }
        this.mLayout = new SimpleLayout();
    }

    public void releaseSpace() {
        deleteExpiredLogs(sortLogFiles(new File("/data/Log/ota/")), 3);
    }

    public void write(LogMessage logMessage) {
        if (this.mQuietWriter == null) {
            setWrite();
        } else if (TimeUtils.getDay().compareTo(this.mFileName) > 0) {
            setWrite();
        }
        doWrite(logMessage);
    }

    private void setWrite() {
        closeWriter();
        FileUtils.mkdirsIfNotExist("/data/Log/ota/");
        List<String> sortLogFiles = sortLogFiles(new File("/data/Log/ota/"));
        if (isDeleteExpired()) {
            deleteExpiredLogs(sortLogFiles, getCapacity());
        }
        String fileName = getFileName(sortLogFiles);
        try {
            File file = new File("/data/Log/ota/" + fileName);
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            Writer createWriter = createWriter(new FileOutputStream(file, this.mAppend));
            if (this.mBufferedIO) {
                createWriter = new BufferedWriter(createWriter, this.mBufferedSize);
            }
            setQuietWriter(createWriter);
            setFileName(fileName);
            this.mQuietWriter.write(getHeader());
            if (this.mImmediatelyFlush) {
                this.mQuietWriter.flush();
            }
        } catch (IOException e) {
            Log.e(TAG, "Set log writer occurs exception", e);
        }
    }

    private void doWrite(LogMessage logMessage) {
        QuietWriter quietWriter = this.mQuietWriter;
        if (quietWriter == null) {
            Log.e(TAG, "No file write created");
            return;
        }
        quietWriter.write(this.mLayout.format(logMessage));
        if (this.mImmediatelyFlush) {
            this.mQuietWriter.flush();
        }
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder(128);
        if (TextUtils.isEmpty(this.mHeader)) {
            sb.append(SimpleLayout.HEADER_SEP);
            sb.append(SimpleLayout.LINE_SEP);
            sb.append(SimpleLayout.LINE_SEP);
        } else {
            sb.append(SimpleLayout.HEADER_SEP);
            sb.append(SimpleLayout.LINE_SEP);
            sb.append(this.mHeader);
            sb.append(SimpleLayout.LINE_SEP);
            sb.append(SimpleLayout.HEADER_SEP);
            sb.append(SimpleLayout.LINE_SEP);
            sb.append(SimpleLayout.LINE_SEP);
        }
        return sb.toString();
    }

    private OutputStreamWriter createWriter(OutputStream outputStream) {
        return new OutputStreamWriter(outputStream);
    }

    private void setQuietWriter(Writer writer) {
        this.mQuietWriter = new QuietWriter(writer);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        if (r4.mQuietWriter == null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0028, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.xiaopeng.ota.sdk.common.log.QuietWriter, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void closeWriter() {
        /*
            r4 = this;
            r0 = 0
            com.xiaopeng.ota.sdk.common.log.QuietWriter r1 = r4.mQuietWriter     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            if (r1 == 0) goto Lf
            com.xiaopeng.ota.sdk.common.log.QuietWriter r1 = r4.mQuietWriter     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            r1.flush()     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            com.xiaopeng.ota.sdk.common.log.QuietWriter r1 = r4.mQuietWriter     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            r1.close()     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
        Lf:
            com.xiaopeng.ota.sdk.common.log.QuietWriter r1 = r4.mQuietWriter
            if (r1 == 0) goto L15
        L13:
            r4.mQuietWriter = r0
        L15:
            r4.setFileName(r0)
            goto L28
        L19:
            r1 = move-exception
            goto L29
        L1b:
            r1 = move-exception
            java.lang.String r2 = com.xiaopeng.ota.sdk.common.log.LogWriter.TAG     // Catch: java.lang.Throwable -> L19
            java.lang.String r3 = "Close log writer occurs exception"
            android.util.Log.d(r2, r3, r1)     // Catch: java.lang.Throwable -> L19
            com.xiaopeng.ota.sdk.common.log.QuietWriter r1 = r4.mQuietWriter
            if (r1 == 0) goto L15
            goto L13
        L28:
            return
        L29:
            com.xiaopeng.ota.sdk.common.log.QuietWriter r2 = r4.mQuietWriter
            if (r2 == 0) goto L2f
            r4.mQuietWriter = r0
        L2f:
            r4.setFileName(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.common.log.LogWriter.closeWriter():void");
    }

    private String getFileName(List<String> list) {
        String str = TimeUtils.getDay() + "." + LOG_SUFFIX;
        if (CheckUtils.isEmpty(list)) {
            return str;
        }
        String str2 = list.get(0);
        return str.compareTo(str2) > 0 ? str : str2;
    }

    private void setFileName(String str) {
        this.mFileName = str;
    }

    public boolean isDeleteExpired() {
        return this.mDeleteExpired;
    }

    public int getCapacity() {
        return this.mCapacity;
    }

    private List<String> sortLogFiles(File file) {
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.xiaopeng.ota.sdk.common.log.LogWriter.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                String fileSuffix;
                return file2 != null && file2.isFile() && (fileSuffix = FileUtils.getFileSuffix(file2.getName())) != null && LogWriter.LOG_SUFFIX.equalsIgnoreCase(fileSuffix);
            }
        });
        ArrayList arrayList = new ArrayList();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (isValidFileName(file2.getName())) {
                    arrayList.add(file2.getName());
                }
            }
        }
        if (!CheckUtils.isEmpty(arrayList)) {
            Collections.sort(arrayList, new Comparator<String>() { // from class: com.xiaopeng.ota.sdk.common.log.LogWriter.2
                @Override // java.util.Comparator
                public int compare(String str, String str2) {
                    return str.compareTo(str2) * (-1);
                }
            });
        }
        return arrayList;
    }

    private boolean isValidFileName(String str) {
        String name = FileUtils.getName(str);
        if (name != null) {
            try {
                DATE_FORMAT.parse(name);
                return true;
            } catch (ParseException unused) {
            }
        }
        return false;
    }

    private void deleteExpiredLogs(List<String> list, int i) {
        int size;
        if (CheckUtils.isEmpty(list) || (size = list.size()) <= i) {
            return;
        }
        for (int i2 = size - 1; i2 > i; i2--) {
            String str = "/data/Log/ota/" + list.get(i2);
            try {
                FileUtils.deleteFile(str);
                Logger.d(TAG, "Delete expired log: %s", str);
            } catch (Exception unused) {
                Logger.w(TAG, "Delete expired log: %s failed", str);
            }
        }
    }
}

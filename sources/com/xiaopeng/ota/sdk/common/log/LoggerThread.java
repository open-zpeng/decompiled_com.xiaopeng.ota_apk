package com.xiaopeng.ota.sdk.common.log;

import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class LoggerThread extends Thread {
    private static final int POLL_TIMEOUT_SECONDS = 30;
    private static final int QUEUE_CAPACITY = 500;
    private static final String TAG = "LoggerThread";
    private static volatile BlockingQueue<LogMessage> logMessageQueue = new LinkedBlockingQueue(500);
    private static volatile boolean mExitFlag = false;
    private final LogWriter mLogWriter = new LogWriter();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void enqueue(LogMessage logMessage) {
        synchronized (LoggerThread.class) {
            if (logMessage == null) {
                return;
            }
            try {
                if (!logMessageQueue.add(logMessage)) {
                    Log.e(TAG, "Log message enqueue failed");
                }
            } catch (Exception e) {
                Log.e(TAG, "Log message enqueue occurs exception", e);
            }
        }
    }

    public static void exit() {
        mExitFlag = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.d(TAG, "Log thread start...");
        while (!mExitFlag) {
            try {
                LogMessage poll = logMessageQueue.poll(30L, TimeUnit.SECONDS);
                if (poll != null) {
                    if (Integer.MAX_VALUE == poll.getLevel()) {
                        this.mLogWriter.releaseSpace();
                    } else {
                        this.mLogWriter.write(poll);
                    }
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "Poll log message error", e);
            }
        }
    }
}

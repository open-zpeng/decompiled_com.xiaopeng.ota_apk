package com.xiaopeng.ota.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class ThreadUtils {
    private static final String BACKGROUND = "background";
    private static final String BACKGROUND_LONG = "background-long";
    private static final String MAIN = "main";
    private static ConcurrentHashMap<String, Handler> sHandlers = new ConcurrentHashMap<>();

    private static synchronized Handler getHandler(String str) {
        Handler handler;
        synchronized (ThreadUtils.class) {
            handler = sHandlers.get(str);
            if (handler == null) {
                if (str.equals(MAIN)) {
                    handler = new Handler(Looper.getMainLooper());
                } else {
                    HandlerThread handlerThread = new HandlerThread(str);
                    handlerThread.start();
                    handler = new Handler(handlerThread.getLooper());
                }
                sHandlers.put(str, handler);
            }
        }
        return handler;
    }

    public static boolean post(String str, Runnable runnable) {
        return post(str, runnable, 0L);
    }

    public static boolean post(String str, Runnable runnable, long j) {
        Handler handler = getHandler(str);
        return j > 0 ? handler.postDelayed(runnable, j) : handler.post(runnable);
    }

    public static boolean postMainThread(Runnable runnable) {
        return post(MAIN, runnable, 0L);
    }

    public static boolean postMainThread(Runnable runnable, long j) {
        return post(MAIN, runnable, j);
    }

    public static boolean postBackground(Runnable runnable) {
        return post("background", runnable, 0L);
    }

    public static boolean postBackground(Runnable runnable, long j) {
        return post("background", runnable, j);
    }

    public static boolean postBackgroundLong(Runnable runnable) {
        return post(BACKGROUND_LONG, runnable, 0L);
    }

    public static boolean postBackgroundLong(Runnable runnable, long j) {
        return post(BACKGROUND_LONG, runnable, j);
    }
}

package com.xiaopeng.xuimanager.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/* loaded from: classes2.dex */
public class ProcessUtil {
    public static String getCurrentProcessName() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String trim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return trim;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

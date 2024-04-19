package com.xiaopeng.ota.sdk.common.util;

import java.io.IOException;
/* loaded from: classes2.dex */
public class PermissionUtils {
    public static final String MODE_ALL = "777";

    public static void changeMode(String str, String str2) throws IOException {
        new ProcessBuilder("chmod", "-R", str2, str).start();
    }
}

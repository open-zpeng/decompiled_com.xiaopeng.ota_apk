package com.xiaopeng.ota.sdk.common.util;

import java.util.UUID;
/* loaded from: classes2.dex */
public class TraceIdGenerator {
    public static String create() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

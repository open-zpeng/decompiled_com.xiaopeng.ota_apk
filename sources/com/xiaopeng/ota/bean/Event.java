package com.xiaopeng.ota.bean;

import com.xiaopeng.ota.sdk.common.OTAConstants;
/* loaded from: classes2.dex */
public abstract class Event {
    public String getName() {
        return getClass().getSimpleName();
    }

    public String getFormatName() {
        StringBuilder sb = new StringBuilder("EVENT");
        String replace = getName().replace("Event", "");
        for (int i = 0; i < replace.length(); i++) {
            char charAt = replace.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append(OTAConstants.LINKER_UNDER_LINE);
            }
            sb.append(Character.toUpperCase(charAt));
        }
        return sb.toString();
    }

    public String toString() {
        return getName();
    }
}

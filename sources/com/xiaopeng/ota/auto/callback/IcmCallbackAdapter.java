package com.xiaopeng.ota.auto.callback;

import java.util.Arrays;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public class IcmCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = new int[0];
    private static final String TAG = "IcmCallbackAdapter";

    protected void onMeterConnectMsg(boolean z) {
    }

    protected void onSendResultMsg(int[] iArr) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    protected void onChangeEvent(int i, Object obj) {
        if (i == 557848078) {
            onMeterConnectMsg(((Integer) obj).intValue() == 1);
        } else if (i != 557913610) {
        } else {
            onSendResultMsg(Arrays.stream((Integer[]) obj).mapToInt(new ToIntFunction() { // from class: com.xiaopeng.ota.auto.callback.-$$Lambda$gfCssnBJI7TKfXb_Jmv7raVYNkY
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj2) {
                    return Integer.valueOf(((Integer) obj2).intValue()).intValue();
                }
            }).toArray());
        }
    }
}

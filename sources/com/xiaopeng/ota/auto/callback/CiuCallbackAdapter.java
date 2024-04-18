package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class CiuCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = new int[0];
    private static final String TAG = "CiuCallbackAdapter";

    protected void onDvrModeChanged(int i) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        if (i != 557852673) {
            return;
        }
        onDvrModeChanged(((Integer) obj).intValue());
    }
}

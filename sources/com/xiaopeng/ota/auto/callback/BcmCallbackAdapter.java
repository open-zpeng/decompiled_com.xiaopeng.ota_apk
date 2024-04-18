package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class BcmCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = {557849648};
    private static final String TAG = "BcmCallbackAdapter";

    protected void onBuckleStateChanged(boolean z) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        if (i == 557849648 && obj != null) {
            onBuckleStateChanged(((Integer) obj).intValue() == 0);
        }
    }
}

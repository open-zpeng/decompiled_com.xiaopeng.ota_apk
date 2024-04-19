package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class SrsCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = {557849612};
    private static final String TAG = "SrsCallbackAdapter";

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBuckleStateChanged(boolean z) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        if (i == 557849612 && obj != null) {
            onBuckleStateChanged(((Integer) obj).intValue() == 0);
        }
    }
}

package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class TBoxCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = new int[0];
    private static final String TAG = "TBoxCallbackAdapter";

    protected void onOtaProgress(int i) {
    }

    protected void onOtaUpdateResult(String str) {
    }

    protected void onStartTboxOtaResponse(String str) {
    }

    protected void onStopTboxOtaResponse(String str) {
    }

    protected void onVersionInfoResponse(String str) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        if (i != 557846541) {
            switch (i) {
                case 554700807:
                    onVersionInfoResponse((String) obj);
                    return;
                case 554700808:
                    onStartTboxOtaResponse((String) obj);
                    return;
                case 554700809:
                    onStopTboxOtaResponse((String) obj);
                    return;
                default:
                    return;
            }
        }
        onOtaProgress(((Integer) obj).intValue());
    }
}

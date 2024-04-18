package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class VcuCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = {557847045};
    private static final String TAG = "VcuCallbackAdapter";

    protected void onChargeGunStatusChanged(int i) {
    }

    protected void onEbsBattSocChanged(int i) {
    }

    protected void onElectricitChanged(int i) {
    }

    protected void onGearLeverChanged(int i) {
    }

    protected void onRawCarSpeed(float f) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        switch (i) {
            case 557847042:
                onElectricitChanged(((Integer) obj).intValue());
                return;
            case 557847045:
                onGearLeverChanged(((Integer) obj).intValue());
                return;
            case 557847078:
                onEbsBattSocChanged(((Integer) obj).intValue());
                return;
            case 557847080:
                onChargeGunStatusChanged(((Integer) obj).intValue());
                return;
            case 559944229:
                onRawCarSpeed(((Float) obj).floatValue());
                return;
            default:
                return;
        }
    }
}

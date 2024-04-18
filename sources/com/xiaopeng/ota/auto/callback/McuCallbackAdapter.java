package com.xiaopeng.ota.auto.callback;
/* loaded from: classes2.dex */
public class McuCallbackAdapter extends BaseCallbackAdapter {
    public static final int[] PROP_IDS = {356516106, 557913151, 557847592, 557847595, 557847593};
    public static final int REQ_TYPE_FIRMWARE_A = 1;
    public static final int REQ_TYPE_FIRMWARE_B = 2;
    public static final int REQ_TYPE_FLASH_DRIVER = 0;
    private static final String TAG = "McuCallbackAdapter";

    protected void onReceiveOta1Msg(int[] iArr) {
    }

    protected void onReceivePsuOtaMsg(Integer[] numArr) {
    }

    protected void onReceiveReqStatus(int i) {
    }

    protected void onReceiveReqUpdateFile(int i) {
    }

    protected void onReceiveUpdateStatus(int i) {
    }

    @Override // com.xiaopeng.ota.auto.callback.BaseCallbackAdapter
    public final void onChangeEvent(int i, Object obj) {
        switch (i) {
            case 356516106:
                onReceiveOta1Msg((int[]) obj);
                return;
            case 557847592:
                onReceiveReqStatus(((Integer) obj).intValue());
                return;
            case 557847593:
                onReceiveReqUpdateFile(((Integer) obj).intValue());
                return;
            case 557847595:
                onReceiveUpdateStatus(((Integer) obj).intValue());
                return;
            case 557913151:
                onReceivePsuOtaMsg((Integer[]) obj);
                return;
            default:
                return;
        }
    }
}

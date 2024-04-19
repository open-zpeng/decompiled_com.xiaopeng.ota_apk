package com.xiaopeng.ota.presenter.update.cdu;

import android.hardware.boot.V1_0.CommandResult;
import android.hardware.boot.V1_0.IBootControl;
import android.os.RemoteException;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class BootController {
    public static final int RESULT_OK = 0;
    public static final int SLOT_0 = 0;
    public static final int SLOT_1 = 1;
    private static final String TAG = "BootController";

    public boolean prepareSlot(boolean z) {
        try {
            IBootControl service = IBootControl.getService(true);
            int currentSlot = service.getCurrentSlot();
            int i = z ? currentSlot == 0 ? 1 : 0 : currentSlot;
            LogUtils.d(TAG, "Current slot: %d, active slot: %d", Integer.valueOf(currentSlot), Integer.valueOf(i));
            if (z && service.isSlotBootable(i) != 0) {
                LogUtils.e(TAG, "Slot %d not bootable", Integer.valueOf(i));
                return false;
            }
            CommandResult activeBootSlot = service.setActiveBootSlot(i);
            if (activeBootSlot == null || !activeBootSlot.success) {
                LogUtils.e(TAG, "Change slot from %d to %d fail", Integer.valueOf(currentSlot), Integer.valueOf(i));
                return false;
            }
            return true;
        } catch (RemoteException e) {
            LogUtils.e(TAG, "get BootControl service fail", e);
            return false;
        }
    }

    public int getCurrentSlot() throws Exception {
        return IBootControl.getService(true).getCurrentSlot();
    }
}

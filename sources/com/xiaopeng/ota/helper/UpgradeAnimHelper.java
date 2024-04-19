package com.xiaopeng.ota.helper;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class UpgradeAnimHelper {
    private static final String TAG = "UpgradeAnimHelper";
    private static final String UPGRADE_CANCEL_ACTION = "com.xiaopeng.ota.intent.action.ACTION_UPGRADE_CANCEL";
    private static final String UPGRADE_READY_ACTION = "com.xiaopeng.ota.intent.action.ACTION_UPGRADE_READY";

    public static void showUpgradeAnim(Context context) {
        if (context == null) {
            return;
        }
        LogUtils.d(TAG, "Send upgrade action:com.xiaopeng.ota.intent.action.ACTION_UPGRADE_READY");
        Intent intent = new Intent();
        intent.setAction(UPGRADE_READY_ACTION);
        context.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    public static void hideUpgradeAnim(Context context) {
        if (context == null) {
            return;
        }
        LogUtils.d(TAG, "Send upgrade action:com.xiaopeng.ota.intent.action.ACTION_UPGRADE_CANCEL");
        Intent intent = new Intent();
        intent.setAction(UPGRADE_CANCEL_ACTION);
        context.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }
}

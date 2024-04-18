package com.xiaopeng.ota.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class SotaBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SotaBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z;
        LogUtils.d(TAG, "onReceive action: " + intent.getAction());
        if (TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Intent intent2 = new Intent(context, SotaService.class);
        intent2.setAction(intent.getAction());
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode == -1454123155) {
            if (action.equals(Config.ACTION_SCREEN_ON)) {
                z = true;
            }
            z = true;
        } else if (hashCode != -517734172) {
            if (hashCode == 798292259 && action.equals(Config.ACTION_BOOT_COMPLETE)) {
                z = false;
            }
            z = true;
        } else {
            if (action.equals(Config.ACTION_SOTA_SERVER_MESSAGE)) {
                z = true;
            }
            z = true;
        }
        if (!z) {
            intent2.putExtra("type", 3);
        } else if (z) {
            intent2.putExtra("type", Config.ACTION_SCREEN_ON);
        } else if (z) {
            intent2.putExtra("type", intent.getIntExtra("type", -1));
        }
        context.startServiceAsUser(intent2, UserHandle.CURRENT);
    }
}

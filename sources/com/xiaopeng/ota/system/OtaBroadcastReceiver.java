package com.xiaopeng.ota.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.ota.Config;
/* loaded from: classes2.dex */
public class OtaBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "OtaBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Action: " + intent.getAction());
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        Intent intent2 = new Intent(context, OtaService.class);
        Intent intent3 = new Intent(context, SotaService.class);
        intent2.setAction(action);
        char c = 65535;
        switch (action.hashCode()) {
            case -2110209792:
                if (action.equals(Config.ACTION_APP_INSTALL)) {
                    c = 2;
                    break;
                }
                break;
            case -1454123155:
                if (action.equals(Config.ACTION_SCREEN_ON)) {
                    c = 1;
                    break;
                }
                break;
            case 798292259:
                if (action.equals(Config.ACTION_BOOT_COMPLETE)) {
                    c = 0;
                    break;
                }
                break;
            case 2135852351:
                if (action.equals(Config.ACTION_CLEAN_APP_CACHES)) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            intent3.putExtra("type", 3);
        } else if (c == 1) {
            intent3.putExtra("type", Config.ACTION_SCREEN_ON);
        } else if (c == 2 || c == 3) {
            intent2.putExtras(intent);
            context.startServiceAsUser(intent2, UserHandle.CURRENT);
            return;
        }
        context.startServiceAsUser(intent2, UserHandle.CURRENT);
        context.startServiceAsUser(intent3, UserHandle.CURRENT);
    }
}

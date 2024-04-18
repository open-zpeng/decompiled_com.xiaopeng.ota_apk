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
        Log.d(TAG, "onReceive action: " + intent.getAction());
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        Intent intent2 = new Intent(context, OtaService.class);
        intent2.setAction(action);
        char c = 65535;
        switch (action.hashCode()) {
            case -2110209792:
                if (action.equals(Config.ACTION_APP_INSTALL)) {
                    c = 0;
                    break;
                }
                break;
            case -2080500012:
                if (action.equals("com.xiaopeng.broadcast.ACTION_ADB_OTA")) {
                    c = 2;
                    break;
                }
                break;
            case -1443749852:
                if (action.equals(Config.ACTION_XUI_BUSINESS_EVENT)) {
                    c = 1;
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
        if (c == 0 || c == 1 || c == 2 || c == 3) {
            intent2.putExtras(intent);
        }
        context.startServiceAsUser(intent2, UserHandle.CURRENT);
    }
}

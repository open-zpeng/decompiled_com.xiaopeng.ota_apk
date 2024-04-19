package com.xiaopeng.sota.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.xiaopeng.libconfig.settings.SettingsUtil;
import com.xiaopeng.ota.sdk.campaign.event.NetworkConnectedEvent;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
/* loaded from: classes2.dex */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkReceiver";
    private static int sCurrentNetworkType;

    public static int getCurrentNetworkType() {
        return sCurrentNetworkType;
    }

    public static String getCurrentNetworkName() {
        int i = sCurrentNetworkType;
        return i != 0 ? i != 1 ? "Unknown" : SettingsUtil.PAGE_WIFI : "4G";
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return;
        }
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$NetworkBroadcastReceiver$y5cQlCxvq0iQGQMfwmWtyRTC2Cc
            @Override // java.lang.Runnable
            public final void run() {
                NetworkBroadcastReceiver.lambda$onReceive$4(connectivityManager);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onReceive$4(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo != null && networkInfo.isConnected()) {
            Logger.d(TAG, "Wifi network connected", new Object[0]);
            sCurrentNetworkType = 1;
            SOTAEventBroker.getEventBroker().postEvent(new NetworkConnectedEvent.Builder().type(sCurrentNetworkType).build());
        } else if (networkInfo2 != null && networkInfo2.isConnected()) {
            Logger.d(TAG, "Data network connected", new Object[0]);
            sCurrentNetworkType = 0;
            SOTAEventBroker.getEventBroker().postEvent(new NetworkConnectedEvent.Builder().type(sCurrentNetworkType).build());
        } else {
            Logger.e(TAG, "Network disconnected", new Object[0]);
            sCurrentNetworkType = -1;
            SOTAEventBroker.getEventBroker().postEvent(new NetworkConnectedEvent.Builder().type(sCurrentNetworkType).build());
        }
    }
}

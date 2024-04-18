package com.xiaopeng.sota.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.AppManager;
import com.xiaopeng.ota.sdk.common.util.PackageNameUtils;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
import com.xiaopeng.sota.sdk.common.Constants;
/* loaded from: classes2.dex */
public class AppReceiver extends BroadcastReceiver {
    private static final String TAG = "AppReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$AppReceiver$1BCXWD69FssPRtUD1jSdsHp33O0
            @Override // java.lang.Runnable
            public final void run() {
                AppReceiver.this.lambda$onReceive$0$AppReceiver(intent, context);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public /* synthetic */ void lambda$onReceive$0$AppReceiver(Intent intent, final Context context) {
        char c;
        String action = intent.getAction();
        Logger.d(TAG, "Receive action:" + action, new Object[0]);
        switch (action.hashCode()) {
            case -1990857588:
                if (action.equals(AppManager.ACTION_PACKAGE_INSTALL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -810471698:
                if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 525384130:
                if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1544582882:
                if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            onPackageInstalled(intent);
        } else if (c == 1) {
            Logger.d(TAG, "Package (" + PackageNameUtils.getPackageName(intent.getDataString()) + ") added", new Object[0]);
        } else if (c == 2) {
            final String packageName = PackageNameUtils.getPackageName(intent.getDataString());
            Logger.d(TAG, "Package (" + packageName + ") replaced", new Object[0]);
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.AppReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (context.getPackageName().equals(packageName)) {
                        try {
                            Thread.sleep(1500L);
                        } catch (InterruptedException unused) {
                            Logger.d(AppReceiver.TAG, "Sleep %d fail when OTA self upgrade", Integer.valueOf((int) Constants.SELF_UPGRADE_DELAY));
                        }
                    }
                    SOTAManager.getCampaignUpdater().checkPackage(packageName);
                }
            });
        } else if (c == 3) {
            String dataString = intent.getDataString();
            Logger.d(TAG, "Package (" + PackageNameUtils.getPackageName(dataString) + ") removed", new Object[0]);
        } else {
            Logger.d(TAG, "Unknown action", new Object[0]);
        }
    }

    private void onPackageInstalled(Intent intent) {
        final String stringExtra = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
        final int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        String stringExtra2 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        Logger.d(TAG, "Package installer packageName: %s, status:%d, message=%s", stringExtra, Integer.valueOf(intExtra), stringExtra2);
        if (intExtra == 0) {
            Logger.d(TAG, "Package(%s) install success", stringExtra);
            return;
        }
        Logger.d(TAG, "Package(%s) install failed, %s", stringExtra, stringExtra2);
        SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$AppReceiver$dU_w7NLNrrCxCUlLdwicX7aW0RU
            @Override // java.lang.Runnable
            public final void run() {
                AppReceiver.lambda$onPackageInstalled$1(intExtra, stringExtra);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onPackageInstalled$1(int i, String str) {
        SOTAManager.getCampaignUpdater().installFailed(i, str);
        SOTAManager.getCampaignUpdater().installNextPackage();
    }
}

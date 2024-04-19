package com.xiaopeng.ota;

import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import androidx.multidex.MultiDexApplication;
import com.xiaopeng.lib.utils.ProcessUtils;
import com.xiaopeng.ota.system.OtaService;
import com.xiaopeng.ota.system.SotaService;
import com.xiaopeng.xui.Xui;
/* loaded from: classes2.dex */
public class App extends MultiDexApplication {
    private static final String TAG = "App";
    private static AsyncInflateViewManager sInflateViewManager;

    @Override // android.app.Application
    public void onCreate() {
        Log.d(TAG, "OTA app create");
        super.onCreate();
        Xui.init(this);
        Xui.setVuiEnable(true);
        sInflateViewManager = new AsyncInflateViewManager(this);
        String curProcessName = ProcessUtils.getCurProcessName();
        Log.d(TAG, "Current process name:" + curProcessName);
        if (curProcessName.equals("com.xiaopeng.ota")) {
            OTAManager.init(this, 0, new Runnable() { // from class: com.xiaopeng.ota.App.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(App.TAG, "OTA start fota service");
                    App.this.startServiceAsUser(new Intent(App.this, OtaService.class), UserHandle.CURRENT);
                    App.this.startServiceAsUser(new Intent(App.this, SotaService.class), UserHandle.CURRENT);
                }
            });
        } else {
            OTAManager.init(this, 1, new Runnable() { // from class: com.xiaopeng.ota.App.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(App.TAG, "OTA start sota service");
                    App.this.startServiceAsUser(new Intent(App.this, SotaService.class), UserHandle.CURRENT);
                }
            });
        }
        Log.d(TAG, "OTA app create completed");
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        OTAManager.dispose();
    }

    public static AsyncInflateViewManager getInflateViewManager() {
        return sInflateViewManager;
    }
}

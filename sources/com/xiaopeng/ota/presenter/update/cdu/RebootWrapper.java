package com.xiaopeng.ota.presenter.update.cdu;

import android.car.Car;
import android.content.Context;
import android.os.PowerManager;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class RebootWrapper {
    private static final String REBOOT_SOC = "soc";
    private static final String TAG = "RebootWrapper";
    private Context mContext;
    private PowerManager mPowerManager;

    public RebootWrapper(Context context) {
        this.mContext = context;
        this.mPowerManager = (PowerManager) this.mContext.getSystemService(Car.POWER_SERVICE);
    }

    public void reboot(String str) {
        String carVersion = CarApi.getCarVersion();
        String str2 = TAG;
        LogUtils.d(str2, "Car type: " + carVersion + ", reboot reason: " + str);
        if ("D55".equalsIgnoreCase(carVersion)) {
            this.mPowerManager.reboot(REBOOT_SOC);
        } else {
            this.mPowerManager.reboot(null);
        }
    }
}

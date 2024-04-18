package com.xiaopeng.ota.helper;

import android.car.Car;
import android.os.PowerManager;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.callback.BcmCallbackAdapter;
import com.xiaopeng.ota.auto.callback.IcmCallbackAdapter;
import com.xiaopeng.ota.auto.callback.PowerStateListener;
import com.xiaopeng.ota.auto.callback.SrsCallbackAdapter;
import com.xiaopeng.ota.auto.callback.VcuCallbackAdapter;
import com.xiaopeng.ota.auto.service.ICarConnect;
import com.xiaopeng.ota.auto.service.bcm.BcmService;
import com.xiaopeng.ota.auto.service.icm.IcmService;
import com.xiaopeng.ota.auto.service.mcu.McuService;
import com.xiaopeng.ota.auto.service.srs.SrsService;
import com.xiaopeng.ota.auto.service.vcu.VcuService;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class CarServiceHelper {
    private static final String TAG = "CarServiceHelper";

    public static void addVcuServiceListener(VcuCallbackAdapter vcuCallbackAdapter) {
        try {
            VcuService vcuService = (VcuService) CarApi.getCarApi(102);
            if (vcuService != null) {
                vcuService.addCarServiceCallback(vcuCallbackAdapter);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addVcuServiceListener failed");
        }
    }

    public static void addIcmServiceListener(IcmCallbackAdapter icmCallbackAdapter) {
        try {
            IcmService icmService = (IcmService) CarApi.getCarApi(103);
            if (icmService != null) {
                icmService.addCarServiceCallback(icmCallbackAdapter);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addIcmServiceListener failed");
        }
    }

    public static void addBcmServiceListener(BcmCallbackAdapter bcmCallbackAdapter) {
        try {
            BcmService bcmService = (BcmService) CarApi.getCarApi(100);
            if (bcmService != null) {
                bcmService.addCarServiceCallback(bcmCallbackAdapter);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addBcmServiceListener failed");
        }
    }

    public static void addSrsServiceListener(SrsCallbackAdapter srsCallbackAdapter) {
        try {
            SrsService srsService = (SrsService) CarApi.getCarApi(113);
            if (srsService != null) {
                srsService.addCarServiceCallback(srsCallbackAdapter);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addSrsServiceListener failed");
        }
    }

    public static void addPowerStateListener(PowerStateListener powerStateListener) {
        try {
            CarApi.addPowerStateListener(powerStateListener);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addPowerStateListener failed");
        }
    }

    public static void addRegisterConnectListener(ICarConnect iCarConnect) {
        try {
            CarApi.registerConnectListener(iCarConnect);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "addRegisterConnectListener failed");
        }
    }

    public static int getGearLever() {
        try {
            VcuService vcuService = (VcuService) CarApi.getCarApi(102);
            if (vcuService != null) {
                return vcuService.getGearLever();
            }
            return -1;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get gear lever failed");
            return -1;
        }
    }

    public static float getLastStartUpMileage() {
        try {
            IcmService icmService = (IcmService) CarApi.getCarApi(103);
            if (icmService != null) {
                return icmService.getLastStartUpMileage();
            }
            return 0.0f;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get last start up mileage failed");
            return 0.0f;
        }
    }

    public static boolean isIGRemoteStatus() {
        return 2 == getIGStatus();
    }

    public static boolean isIGLocalStatus() {
        return 1 == getIGStatus();
    }

    private static int getIGStatus() {
        try {
            McuService mcuService = (McuService) CarApi.getCarApi(101);
            if (mcuService != null) {
                LogUtils.d(TAG, "Mcu ig status: %d", Integer.valueOf(mcuService.getIgStatusFromMcu()));
                return mcuService.getIgStatusFromMcu();
            }
            return -1;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get Mcu ig status failed");
            return -1;
        }
    }

    @Deprecated
    public static boolean isInteractive() {
        try {
            PowerManager powerManager = (PowerManager) OTAManager.getContext().getSystemService(Car.POWER_SERVICE);
            if (powerManager != null) {
                return powerManager.isInteractive();
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get last start up mileage failed");
            return false;
        }
    }
}

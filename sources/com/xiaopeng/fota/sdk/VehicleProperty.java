package com.xiaopeng.fota.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import com.xiaopeng.lib.utils.NetUtils;
import com.xiaopeng.lib.utils.config.CommonConfig;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.service.bcm.IBcmService;
import com.xiaopeng.ota.auto.service.hvac.IHvacService;
import com.xiaopeng.ota.auto.service.mcu.IMcuService;
import com.xiaopeng.ota.auto.service.tbox.ITBoxService;
import com.xiaopeng.ota.auto.service.vcu.IVcuService;
/* loaded from: classes2.dex */
final class VehicleProperty {
    private static final int PROP_VAL_CHARGE_CONNECTED = 1;
    private static final int PROP_VAL_CHARGE_INVALID = -1;
    private static final int PROP_VAL_CHARGE_NOT_CONNECTED = 0;
    private static String TAG = "VehicleProperty";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Context context) {
    }

    VehicleProperty() {
    }

    private static IBcmService getBcm() {
        IBcmService iBcmService = (IBcmService) CarApi.getCarApi(100);
        if (iBcmService != null) {
            return iBcmService;
        }
        throw new NullPointerException("BCM is null");
    }

    private static IHvacService getHvac() {
        IHvacService iHvacService = (IHvacService) CarApi.getCarApi(114);
        if (iHvacService != null) {
            return iHvacService;
        }
        throw new NullPointerException("HVAC is null");
    }

    private static IMcuService getMcu() {
        IMcuService iMcuService = (IMcuService) CarApi.getCarApi(101);
        if (iMcuService != null) {
            return iMcuService;
        }
        throw new NullPointerException("MCU is null");
    }

    private static ITBoxService getTbox() {
        ITBoxService iTBoxService = (ITBoxService) CarApi.getCarApi(112);
        if (iTBoxService != null) {
            return iTBoxService;
        }
        throw new NullPointerException("TBOX is null");
    }

    private static IVcuService getVcu() {
        IVcuService iVcuService = (IVcuService) CarApi.getCarApi(102);
        if (iVcuService != null) {
            return iVcuService;
        }
        throw new NullPointerException("VCU is null");
    }

    static int getInt(String str) {
        char c;
        float[] windowsState;
        try {
            switch (str.hashCode()) {
                case -2025222195:
                    if (str.equals("OTA_PROP_GEAR_LEVEL")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1903586016:
                    if (str.equals("OTA_PROP_CHARGE_GUN_STATE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1322692990:
                    if (str.equals("OTA_PROP_POWER_BATTERY_SOC")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -74543894:
                    if (str.equals("OTA_PROP_ALARM_STATE")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 40733067:
                    if (str.equals("OTA_PROP_DRIVER_EXIST_STATE")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 286523350:
                    if (str.equals("OTA_PROP_POWER_MODE")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 319820586:
                    if (str.equals("OTA_PROP_EV_SYS_STATE")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 399495060:
                    if (str.equals("OTA_PROP_WINDOW_CLOSE_STATE")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case 622522396:
                    if (str.equals("OTA_PROP_BATTERY_SOC")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 734119659:
                    if (str.equals("OTA_PROP_APP_VERSION_CODE")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 1032478062:
                    if (str.equals("OTA_PROP_SPEED")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1308360773:
                    if (str.equals("OTA_PROP_AUTO_UPGRADE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1509814599:
                    if (str.equals("OTA_PROP_NETWORK_STATE")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1516668129:
                    if (str.equals("OTA_PROP_HVAC_POWER_STATE")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 1928473777:
                    if (str.equals("OTA_PROP_FACTORY_MODE")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case 2011098111:
                    if (str.equals("OTA_PROP_CHARGE_STATE")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Get property " + str + " failed: " + android.util.Log.getStackTraceString(e), new Object[0]);
        }
        switch (c) {
            case 0:
                if (NetUtils.isWifiEnabled(UpgradeUtils.context())) {
                    return 10;
                }
                return NetUtils.isNetworkAvailable(UpgradeUtils.context()) ? 3 : 0;
            case 1:
                return UpgradeUtils.context().getSharedPreferences("com.xiaopeng.ota.preferences", 0).getBoolean("AUTO_UPGRADE_FLAG", false) ? 1 : 0;
            case 2:
                return getVcu().getEbsBatterySoc();
            case 3:
                int chargeGunStatus = getVcu().getChargeGunStatus();
                if (chargeGunStatus == 0) {
                    return 0;
                }
                return (1 > chargeGunStatus || 6 < chargeGunStatus) ? -1 : 1;
            case 4:
                return 2 == getVcu().getChargeStatus() ? 1 : 0;
            case 5:
                return getVcu().getGearLever();
            case 6:
                return getVcu().getSystemReady();
            case 7:
                return getVcu().getElectricityPercent();
            case '\b':
                return (int) (getVcu().getRawCarSpeed() * 1000.0f);
            case '\t':
                return getBcm().getATwsState();
            case '\n':
                return getBcm().getDriverOnSeat();
            case 11:
                float f = 100.0f;
                for (float f2 : getBcm().getWindowsState()) {
                    if (f > f2) {
                        f = f2;
                    }
                }
                return (int) (f * 100.0f);
            case '\f':
                return getHvac().getHvacPowerMode();
            case '\r':
                return getMcu().getIgStatusFromMcu();
            case 14:
                return getMcu().getFactoryModeSwitchStatus();
            case 15:
                return UpgradeUtils.context().getPackageManager().getPackageInfo(UpgradeUtils.context().getPackageName(), 0).versionCode;
            default:
                Log.e(TAG, "Unknown vehicle property " + str, new Object[0]);
                return -1;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static String getStr(String str) {
        char c;
        switch (str.hashCode()) {
            case -2025222195:
                if (str.equals("OTA_PROP_GEAR_LEVEL")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -74543894:
                if (str.equals("OTA_PROP_ALARM_STATE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -73984826:
                if (str.equals("OTA_PROP_HTTP_HOST")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 286523350:
                if (str.equals("OTA_PROP_POWER_MODE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 319820586:
                if (str.equals("OTA_PROP_EV_SYS_STATE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 734434185:
                if (str.equals("OTA_PROP_APP_VERSION_NAME")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1740658163:
                if (str.equals("OTA_PROP_LOCALE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return CommonConfig.HTTP_HOST;
            case 1:
                return UpgradeUtils.context().getResources().getConfiguration().getLocales().get(0).toString();
            case 2:
                int i = getInt(str);
                return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "P" : "R" : "N" : "D";
            case 3:
                return 1 == getInt(str) ? "ON" : "OFF";
            case 4:
                return 4 == getInt(str) ? "LOCKED" : "UNLOCKED";
            case 5:
                int i2 = getInt(str);
                return i2 != 0 ? i2 != 1 ? i2 != 2 ? "" : "REMOTE IG ON" : "LOCAL IG ON" : "IG OFF";
            case 6:
                try {
                    return UpgradeUtils.context().getPackageManager().getPackageInfo(UpgradeUtils.context().getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(TAG, "Get version name failed: " + android.util.Log.getStackTraceString(e), new Object[0]);
                    return "";
                }
            default:
                return "";
        }
    }

    static int keepAwake(int i) {
        Log.i(TAG, "Keep awake %d", Integer.valueOf(i));
        try {
            getMcu().setMcuDelaySleep(i);
            return 0;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Keep awake failed: " + android.util.Log.getStackTraceString(e), new Object[0]);
            return -11;
        }
    }

    static int suppressWarning(boolean z) {
        try {
            getTbox().sendTboxOtaWorkingStatus(z ? 1 : 0);
            return 0;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Send OTA status to TBOX failed: " + android.util.Log.getStackTraceString(e), new Object[0]);
            return -11;
        }
    }
}

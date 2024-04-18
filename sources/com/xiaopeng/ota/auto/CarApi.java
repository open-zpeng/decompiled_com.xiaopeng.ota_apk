package com.xiaopeng.ota.auto;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.power.CarPowerManager;
import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.xiaopeng.ota.auto.callback.PowerStateListener;
import com.xiaopeng.ota.auto.service.ICarConnect;
import com.xiaopeng.ota.auto.service.ICarService;
import com.xiaopeng.ota.auto.service.avm.AvmService;
import com.xiaopeng.ota.auto.service.bcm.BcmService;
import com.xiaopeng.ota.auto.service.ciu.CiuService;
import com.xiaopeng.ota.auto.service.hvac.HvacService;
import com.xiaopeng.ota.auto.service.icm.IcmService;
import com.xiaopeng.ota.auto.service.mcu.McuService;
import com.xiaopeng.ota.auto.service.srs.SrsService;
import com.xiaopeng.ota.auto.service.tbox.TBoxService;
import com.xiaopeng.ota.auto.service.vcu.VcuService;
import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
/* loaded from: classes2.dex */
public final class CarApi {
    public static final int API_AVAS = 109;
    public static final int API_AVM = 110;
    public static final int API_BCM = 100;
    public static final int API_CIU = 111;
    public static final int API_EPS = 105;
    public static final int API_ESP = 107;
    public static final int API_HVAC = 114;
    public static final int API_ICM = 103;
    public static final int API_INPUT = 108;
    public static final int API_MCU = 101;
    public static final int API_SCU = 106;
    public static final int API_SRS = 113;
    public static final int API_TBOX = 112;
    public static final int API_TPMS = 104;
    public static final int API_VCU = 102;
    private static final String TAG = "CarApi";
    private static XPCar sCar;
    private static CarPowerManager sCarPowerManager;
    private static SparseArray<ICarService> mServiceCache = new SparseArray<>();
    private static Set<ICarService> mRegisters = new HashSet();
    private static ConcurrentLinkedDeque<PowerStateListener> sPowerStateListeners = new ConcurrentLinkedDeque<>();
    private static ICarConnect sCarConnectInternal = new ICarConnect() { // from class: com.xiaopeng.ota.auto.CarApi.1
        @Override // com.xiaopeng.ota.auto.service.ICarConnect
        public void onConnected() {
            Logger.d(CarApi.TAG, "Car internal connected", new Object[0]);
            for (ICarService iCarService : CarApi.mRegisters) {
                iCarService.registerCarService();
            }
            CarApi.mRegisters.clear();
            CarApi.listenPowerState();
        }

        @Override // com.xiaopeng.ota.auto.service.ICarConnect
        public void onDisconnected() {
            Logger.d(CarApi.TAG, "Car internal disconnected", new Object[0]);
            CarApi.clearCachedService();
        }
    };

    public static synchronized void init(Context context) {
        synchronized (CarApi.class) {
            connectCar(context);
        }
    }

    public static void addPowerStateListener(PowerStateListener powerStateListener) {
        sPowerStateListeners.add(powerStateListener);
    }

    public static void removePowerStateListener(PowerStateListener powerStateListener) {
        sPowerStateListeners.remove(powerStateListener);
    }

    private static void connectCar(Context context) {
        Log.i(TAG, "connect car...");
        sCar = new XPCar(context);
        sCar.register(sCarConnectInternal);
        sCar.connect();
    }

    public static synchronized void registerConnectListener(ICarConnect iCarConnect) {
        synchronized (CarApi.class) {
            if (sCar != null) {
                sCar.register(iCarConnect);
            }
        }
    }

    public static synchronized void unregisterConnectListener(ICarConnect iCarConnect) {
        synchronized (CarApi.class) {
            if (sCar != null) {
                sCar.unregister(iCarConnect);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearCachedService() {
        Logger.d(TAG, "Clear cached service", new Object[0]);
        mServiceCache.clear();
    }

    public static Car getCar() {
        XPCar xPCar = sCar;
        if (xPCar != null) {
            return xPCar.getCar();
        }
        throw new NullPointerException();
    }

    private static ICarService createCarService(int i) {
        assertApiName(i);
        switch (i) {
            case 100:
                return new BcmService();
            case 101:
                return new McuService();
            case 102:
                return new VcuService();
            case 103:
                return new IcmService();
            default:
                switch (i) {
                    case 110:
                        return new AvmService();
                    case 111:
                        return new CiuService();
                    case 112:
                        return new TBoxService();
                    case 113:
                        return new SrsService();
                    case 114:
                        return new HvacService();
                    default:
                        return null;
                }
        }
    }

    public static synchronized ICarService getCarApi(int i) {
        ICarService iCarService;
        synchronized (CarApi.class) {
            iCarService = mServiceCache.get(i);
            if (iCarService == null) {
                iCarService = createCarService(i);
                if (sCar != null && sCar.isConnected()) {
                    iCarService.registerCarService();
                } else {
                    mRegisters.add(iCarService);
                }
                mServiceCache.put(i, iCarService);
            }
        }
        return iCarService;
    }

    public static String getCarVersion() {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        return !TextUtils.isEmpty(str) ? str.substring(9, 12) : str;
    }

    private static void assertApiName(int i) {
        if (i != 100 && i != 101 && i != 102 && i != 103 && i != 104 && i != 105 && i != 106 && i != 107 && i != 108 && i != 109 && i != 110 && i != 111 && i != 113 && i != 114 && i != 112) {
            throw new IllegalArgumentException("Error Car API name!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void listenPowerState() {
        try {
            sCarPowerManager = (CarPowerManager) getCar().getCarManager(Car.POWER_SERVICE);
            sCarPowerManager.clearListener();
            sCarPowerManager.setListener(new CarPowerManager.CarPowerStateListener() { // from class: com.xiaopeng.ota.auto.-$$Lambda$CarApi$1vzRsOcF6mLlS4YGTH1yGaeUjO4
                @Override // android.car.hardware.power.CarPowerManager.CarPowerStateListener
                public final void onStateChanged(int i) {
                    CarApi.lambda$listenPowerState$0(i);
                }
            }, CarApiThreadPool.getPooledExecutors());
        } catch (CarNotConnectedException e) {
            CarApiThreadPool.getPooledExecutors().execute(new Runnable() { // from class: com.xiaopeng.ota.auto.-$$Lambda$CarApi$VAVwBYKrMiE8daNEpU-0Op9L03I
                @Override // java.lang.Runnable
                public final void run() {
                    CarApi.lambda$listenPowerState$1(CarNotConnectedException.this);
                }
            });
        } catch (Exception e2) {
            Logger.e(TAG, e2, "Listen power state fail", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0022 A[LOOP:0: B:10:0x001c->B:12:0x0022, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$listenPowerState$0(int r3) {
        /*
            r0 = 3
            if (r3 == r0) goto L4
            goto L15
        L4:
            android.car.hardware.power.CarPowerManager r0 = com.xiaopeng.ota.auto.CarApi.sCarPowerManager     // Catch: android.car.CarNotConnectedException -> Lb
            int r0 = r0.getBootReason()     // Catch: android.car.CarNotConnectedException -> Lb
            goto L16
        Lb:
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = "CarApi"
            java.lang.String r2 = "Get power boot reason failed: CarNotConnectedException"
            com.xiaopeng.ota.sdk.common.log.Logger.e(r1, r2, r0)
        L15:
            r0 = -1
        L16:
            java.util.concurrent.ConcurrentLinkedDeque<com.xiaopeng.ota.auto.callback.PowerStateListener> r1 = com.xiaopeng.ota.auto.CarApi.sPowerStateListeners
            java.util.Iterator r1 = r1.iterator()
        L1c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L2c
            java.lang.Object r2 = r1.next()
            com.xiaopeng.ota.auto.callback.PowerStateListener r2 = (com.xiaopeng.ota.auto.callback.PowerStateListener) r2
            r2.onStateChanged(r3, r0)
            goto L1c
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.auto.CarApi.lambda$listenPowerState$0(int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$listenPowerState$1(CarNotConnectedException carNotConnectedException) {
        Iterator<PowerStateListener> it = sPowerStateListeners.iterator();
        while (it.hasNext()) {
            it.next().onCarNotConnected(carNotConnectedException);
        }
    }

    public static synchronized void dispose() {
        synchronized (CarApi.class) {
            if (sCar != null) {
                sCar.disconnect();
            }
        }
    }
}

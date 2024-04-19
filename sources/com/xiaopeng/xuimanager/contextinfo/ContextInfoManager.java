package com.xiaopeng.xuimanager.contextinfo;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManager;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.contextinfo.IContextInfo;
import com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
@SystemApi
/* loaded from: classes2.dex */
public class ContextInfoManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MSG_CONTEXTINFO_ACCELERATION_EVENT = 1;
    private static final int MSG_CONTEXTINFO_ANGULARVELOCITY_EVENT = 2;
    private static final int MSG_CONTEXTINFO_ASSIST_SYS = 35;
    private static final int MSG_CONTEXTINFO_ATLS_STATUS = 23;
    private static final int MSG_CONTEXTINFO_AUTO_BRIGHTNESS = 18;
    private static final int MSG_CONTEXTINFO_AVP_WIFI = 20;
    private static final int MSG_CONTEXTINFO_BELT_STATUS = 24;
    private static final int MSG_CONTEXTINFO_CAMERAINTERVAL_EVENT = 8;
    private static final int MSG_CONTEXTINFO_CAMERA_EVENT = 7;
    private static final int MSG_CONTEXTINFO_CAR_GEAR = 33;
    private static final int MSG_CONTEXTINFO_CAR_SPPED = 16;
    private static final int MSG_CONTEXTINFO_CHARGING_GUN = 26;
    private static final int MSG_CONTEXTINFO_COMMON_EVENT = 36;
    private static final int MSG_CONTEXTINFO_CROSS_EVENT = 10;
    private static final int MSG_CONTEXTINFO_DEVICE_CHARGE = 27;
    private static final int MSG_CONTEXTINFO_DOOR_OPEN = 34;
    private static final int MSG_CONTEXTINFO_DRIVER_SEAT = 28;
    private static final int MSG_CONTEXTINFO_ERROR_EVENT = 0;
    private static final int MSG_CONTEXTINFO_IG_EVENT = 17;
    private static final int MSG_CONTEXTINFO_LANE_EVENT = 6;
    private static final int MSG_CONTEXTINFO_LIGHT_MODE = 29;
    private static final int MSG_CONTEXTINFO_LLU_STATUS = 22;
    private static final int MSG_CONTEXTINFO_LOCATION_EVENT = 11;
    private static final int MSG_CONTEXTINFO_MANEUVER_EVENT = 3;
    private static final int MSG_CONTEXTINFO_MSG_EVENT = 14;
    private static final int MSG_CONTEXTINFO_NAVIGATION_ENABLE_EVENT = 13;
    private static final int MSG_CONTEXTINFO_NAVIGATION_INFO = 12;
    private static final int MSG_CONTEXTINFO_NAVI_EVENT = 5;
    private static final int MSG_CONTEXTINFO_POWEROFF_CNT = 32;
    private static final int MSG_CONTEXTINFO_POWER_ACTION = 25;
    private static final int MSG_CONTEXTINFO_PSD_MOTO = 31;
    private static final int MSG_CONTEXTINFO_REMAININFO_EVENT = 4;
    private static final int MSG_CONTEXTINFO_REMOTE_CMD = 21;
    private static final int MSG_CONTEXTINFO_SAPA_EVENT = 9;
    private static final int MSG_CONTEXTINFO_VPM_EVENT = 30;
    private static final int MSG_CONTEXTINFO_WARNING = 19;
    private static final int MSG_CONTEXTINFO_WEATHER_INFO = 15;
    public static final String TAG = "ContextInfoManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IContextInfo mService;
    private final ArraySet<ContextInfoEventListener> mAllListeners = new ArraySet<>();
    private final ArraySet<ContextNaviInfoEventListener> mNaviListeners = new ArraySet<>();
    private final ArraySet<ContextCarInfoEventListener> mCarListeners = new ArraySet<>();
    private final ArraySet<ContextWeatherInfoEventListener> mWeatherListeners = new ArraySet<>();
    private final ArraySet<ContextAutoBrightnessListener> mAutoBrightnessListeners = new ArraySet<>();
    private final ArraySet<ContextCarStatusEventListener> mCarStatusListeners = new ArraySet<>();
    private final ArraySet<ContextInfoCommonEventListener> mCommonEventListeners = new ArraySet<>();
    private ContextInfoEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface ContextAutoBrightnessListener {
        default void onAutoBrightness(int i, int i2) {
        }

        default void onErrorEvent(int i, int i2) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextCarInfoEventListener {
        default void onAccelerationEvent(float f) {
        }

        default void onAngularVelocityEvent(float f) {
        }

        default void onErrorEvent(int i, int i2) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextCarStatusEventListener {
        default void onErrorEvent(int i, int i2) {
        }

        default void onGearChanged(int i) {
        }

        default void onIGStatus(int i) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextInfoCommonEventListener {
        default void onCommonEvent(int i, int i2) {
        }

        default void onErrorEvent(int i, int i2) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextInfoEventListener {
        void onAccelerationEvent(float f);

        void onAngularVelocityEvent(float f);

        default void onCameraEvent(Camera camera, int i) {
        }

        default void onCameraIntervalEvent(CameraInterval cameraInterval, int i) {
        }

        default void onCrossEvent(Cross cross, int i) {
        }

        void onErrorEvent(int i, int i2);

        default void onLaneEvent(Lane lane, int i) {
        }

        default void onLocationEvent(Location location, int i) {
        }

        default void onManeuverEvent(Maneuver maneuver, int i) {
        }

        default void onMsgEvent(int i) {
        }

        default void onNaviEvent(Navi navi, int i) {
        }

        default void onNavigationEnable(boolean z) {
        }

        default void onNavigationInfo(String str) {
        }

        default void onRemainInfoEvent(RemainInfo remainInfo, int i) {
        }

        default void onSapaEvent(Sapa sapa, int i) {
        }

        default void onWeatherInfo(String str) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextNaviInfoEventListener {
        default void onCameraEvent(Camera camera, int i) {
        }

        default void onCameraIntervalEvent(CameraInterval cameraInterval, int i) {
        }

        default void onCarSpeed(float f) {
        }

        default void onCrossEvent(Cross cross, int i) {
        }

        default void onErrorEvent(int i, int i2) {
        }

        default void onGearChanged(int i) {
        }

        default void onLaneEvent(Lane lane, int i) {
        }

        default void onLocationEvent(Location location, int i) {
        }

        default void onManeuverEvent(Maneuver maneuver, int i) {
        }

        default void onMsgEvent(int i) {
        }

        default void onNaviEvent(Navi navi, int i) {
        }

        default void onNavigationEnable(boolean z) {
        }

        default void onNavigationInfo(String str) {
        }

        default void onRemainInfoEvent(RemainInfo remainInfo, int i) {
        }

        default void onSapaEvent(Sapa sapa, int i) {
        }

        default void onWeatherInfo(String str) {
        }
    }

    /* loaded from: classes2.dex */
    public interface ContextWeatherInfoEventListener {
        default void onErrorEvent(int i, int i2) {
        }

        default void onWeatherInfo(String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchATLSStatusToClient(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAvpWifiToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchBcmLightModeToClient(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchBeltStatusToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCarSpeedToClient(float f) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchChargingGunStatusToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDeviceChargeStatusToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDriverSeatStatusToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLLUStatusToClient(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPowerActionToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPowerOffCountToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPsdMotoToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRemoteCommandToClient(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchVpmEventToClient(int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchXPilotWarningToClient(int i, int i2) {
    }

    @Deprecated
    public void updateWeatherFromServer() {
    }

    public ContextInfoManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IContextInfo.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    private boolean checkAllListenerEmpty() {
        return this.mNaviListeners.isEmpty() && this.mCarListeners.isEmpty() && this.mWeatherListeners.isEmpty() && this.mAutoBrightnessListeners.isEmpty() && this.mCarStatusListeners.isEmpty() && this.mCommonEventListeners.isEmpty() && this.mAllListeners.isEmpty();
    }

    private void initRegisterListener() throws XUIServiceNotConnectedException {
        if (checkAllListenerEmpty()) {
            try {
                this.mListenerToService = new ContextInfoEventListenerToService(this);
                this.mService.registerListeners(this.mListenerToService, 1);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            } catch (Exception e3) {
                Log.e(TAG, ": " + e3.toString());
            }
        }
    }

    private void initUnregisterListener() throws XUIServiceNotConnectedException {
        ContextInfoEventListenerToService contextInfoEventListenerToService;
        if (!checkAllListenerEmpty() || (contextInfoEventListenerToService = this.mListenerToService) == null) {
            return;
        }
        try {
            this.mService.unregisterListener(contextInfoEventListenerToService);
            this.mListenerToService = null;
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public synchronized void registerListener(ContextInfoEventListener contextInfoEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mAllListeners.add(contextInfoEventListener);
    }

    public synchronized void unregisterListener(ContextInfoEventListener contextInfoEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextInfoEventListener listener)");
        this.mAllListeners.remove(contextInfoEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextInfoCommonEventListener contextInfoCommonEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mCommonEventListeners.add(contextInfoCommonEventListener);
    }

    public synchronized void unregisterListener(ContextInfoCommonEventListener contextInfoCommonEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextInfoCommonEventListener listener)");
        this.mCommonEventListeners.remove(contextInfoCommonEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextNaviInfoEventListener contextNaviInfoEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mNaviListeners.add(contextNaviInfoEventListener);
    }

    public synchronized void unregisterListener(ContextNaviInfoEventListener contextNaviInfoEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextNaviInfoEventListener listener)");
        this.mNaviListeners.remove(contextNaviInfoEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextCarInfoEventListener contextCarInfoEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mCarListeners.add(contextCarInfoEventListener);
    }

    public synchronized void unregisterListener(ContextCarInfoEventListener contextCarInfoEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextCarInfoEventListener listener)");
        this.mCarListeners.remove(contextCarInfoEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextCarStatusEventListener contextCarStatusEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mCarStatusListeners.add(contextCarStatusEventListener);
    }

    public synchronized void unregisterListener(ContextCarStatusEventListener contextCarStatusEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextCarInfoEventListener listener)");
        this.mCarStatusListeners.remove(contextCarStatusEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextWeatherInfoEventListener contextWeatherInfoEventListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mWeatherListeners.add(contextWeatherInfoEventListener);
    }

    public synchronized void unregisterListener(ContextWeatherInfoEventListener contextWeatherInfoEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextWeatherInfoEventListener listener)");
        this.mWeatherListeners.remove(contextWeatherInfoEventListener);
        initUnregisterListener();
    }

    public synchronized void registerListener(ContextAutoBrightnessListener contextAutoBrightnessListener) throws XUIServiceNotConnectedException {
        initRegisterListener();
        this.mAutoBrightnessListeners.add(contextAutoBrightnessListener);
    }

    public synchronized void unregisterListener(ContextAutoBrightnessListener contextAutoBrightnessListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextAutoBrightnessListener listener)");
        this.mAutoBrightnessListeners.remove(contextAutoBrightnessListener);
        initUnregisterListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCommonEventToClient(int i, int i2) {
        ArraySet<ContextInfoCommonEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCommonEventListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextInfoCommonEventListener contextInfoCommonEventListener : arraySet) {
            try {
                contextInfoCommonEventListener.onCommonEvent(i, i2);
            } catch (Exception e) {
                Log.e("test", "dispatchCommonEventToClient  " + e);
            }
        }
    }

    private void dispatchWeatherErrorEvent(int i, int i2) {
        ArraySet<ContextWeatherInfoEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mWeatherListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextWeatherInfoEventListener contextWeatherInfoEventListener : arraySet) {
            contextWeatherInfoEventListener.onErrorEvent(i, i2);
        }
    }

    private void dispatchNaviErrorEvent(int i, int i2) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
            contextNaviInfoEventListener.onErrorEvent(i, i2);
        }
    }

    private void dispatchCarinfoErrorEvent(int i, int i2) {
        ArraySet<ContextCarInfoEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCarListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextCarInfoEventListener contextCarInfoEventListener : arraySet) {
            contextCarInfoEventListener.onErrorEvent(i, i2);
        }
    }

    private void dispatchErrorEventToAll(int i, int i2) {
        ArraySet<ContextInfoEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mAllListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet) {
            contextInfoEventListener.onErrorEvent(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        if (i2 == 0) {
            dispatchWeatherErrorEvent(i, i2);
        } else if (i2 == 1) {
            dispatchNaviErrorEvent(i, i2);
        } else if (i2 == 2) {
            dispatchCarinfoErrorEvent(i, i2);
        }
        dispatchErrorEventToAll(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAccelerationEventToClient(float f) {
        ArraySet<ContextCarInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mCarListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextCarInfoEventListener contextCarInfoEventListener : arraySet) {
                contextCarInfoEventListener.onAccelerationEvent(f);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onAccelerationEvent(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAngularVelocityEventToClient(float f) {
        ArraySet<ContextCarInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mCarListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextCarInfoEventListener contextCarInfoEventListener : arraySet) {
                contextCarInfoEventListener.onAngularVelocityEvent(f);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onAngularVelocityEvent(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchManeuverEventToClient(Maneuver maneuver, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onManeuverEvent(maneuver, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onManeuverEvent(maneuver, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRemainEventToClient(RemainInfo remainInfo, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onRemainInfoEvent(remainInfo, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onRemainInfoEvent(remainInfo, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchNaviEventToClient(Navi navi, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onNaviEvent(navi, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onNaviEvent(navi, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLaneEventToClient(Lane lane, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onLaneEvent(lane, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onLaneEvent(lane, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCameraEventToClient(Camera camera, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onCameraEvent(camera, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onCameraEvent(camera, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCameraIntervalEventToClient(CameraInterval cameraInterval, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onCameraIntervalEvent(cameraInterval, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onCameraIntervalEvent(cameraInterval, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchSapaEventToClient(Sapa sapa, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onSapaEvent(sapa, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onSapaEvent(sapa, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCrossEventToClient(Cross cross, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onCrossEvent(cross, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onCrossEvent(cross, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLocationEventToClient(Location location, int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onLocationEvent(location, i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onLocationEvent(location, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchNavigationInfoToClient(String str) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onNavigationInfo(str);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onNavigationInfo(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchNavigationEnableToClient(boolean z) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onNavigationEnable(z);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onNavigationEnable(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchMsgToClient(int i) {
        ArraySet<ContextNaviInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mNaviListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextNaviInfoEventListener contextNaviInfoEventListener : arraySet) {
                contextNaviInfoEventListener.onMsgEvent(i);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onMsgEvent(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchWeatherInfoToClient(String str) {
        ArraySet<ContextWeatherInfoEventListener> arraySet;
        ArraySet<ContextInfoEventListener> arraySet2;
        synchronized (this) {
            arraySet = this.mWeatherListeners;
        }
        if (!arraySet.isEmpty()) {
            for (ContextWeatherInfoEventListener contextWeatherInfoEventListener : arraySet) {
                contextWeatherInfoEventListener.onWeatherInfo(str);
            }
        }
        synchronized (this) {
            arraySet2 = this.mAllListeners;
        }
        if (arraySet2.isEmpty()) {
            return;
        }
        for (ContextInfoEventListener contextInfoEventListener : arraySet2) {
            contextInfoEventListener.onWeatherInfo(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchGearStatusToClient(int i) {
        ArraySet<ContextCarStatusEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCarStatusListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextCarStatusEventListener contextCarStatusEventListener : arraySet) {
            contextCarStatusEventListener.onGearChanged(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchIGStatusToClient(int i) {
        ArraySet<ContextCarStatusEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCarStatusListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextCarStatusEventListener contextCarStatusEventListener : arraySet) {
            contextCarStatusEventListener.onIGStatus(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAutoBrightnessToClient(int i, int i2) {
        ArraySet<ContextAutoBrightnessListener> arraySet;
        synchronized (this) {
            arraySet = this.mAutoBrightnessListeners;
        }
        if (arraySet.isEmpty()) {
            return;
        }
        for (ContextAutoBrightnessListener contextAutoBrightnessListener : arraySet) {
            contextAutoBrightnessListener.onAutoBrightness(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCommonEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 36;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAccelerationEvent(float f) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, Float.valueOf(f)));
    }

    public void handleAngularVelocityEvent(float f) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(2, Float.valueOf(f)));
    }

    public void handleManeuverEvent(Maneuver maneuver, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.obj = maneuver;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleRemainInfoEvent(RemainInfo remainInfo, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 4;
        obtainMessage.obj = remainInfo;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleNaviEvent(Navi navi, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 5;
        obtainMessage.obj = navi;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleLaneEvent(Lane lane, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 6;
        obtainMessage.obj = lane;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleCameraEvent(Camera camera, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 7;
        obtainMessage.obj = camera;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleCameraIntervalEvent(CameraInterval cameraInterval, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 8;
        obtainMessage.obj = cameraInterval;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleSapaEvent(Sapa sapa, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 9;
        obtainMessage.obj = sapa;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleCrossEvent(Cross cross, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10;
        obtainMessage.obj = cross;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleLocationEvent(Location location, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 11;
        obtainMessage.obj = location;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleNavigationInfo(String str) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(12, str));
    }

    public void handleNavigationEnableEvent(boolean z) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(13, Boolean.valueOf(z)));
    }

    public void handleMsgEvent(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(14, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleErrorEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleWeatherInfo(String str) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(15, str));
    }

    public void handleGearStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(33, Integer.valueOf(i)));
    }

    public void handleCarSpeed(float f) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(16, Float.valueOf(f)));
    }

    public void handleIGStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(17, Integer.valueOf(i)));
    }

    public void handleAutoBrightness(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 18;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleXPilotWarnings(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 19;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleAvpWifi(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(20, Integer.valueOf(i)));
    }

    public void handleRemoteCommand(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(21, Integer.valueOf(i)));
    }

    public void handleLLUStatus(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 22;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleATLSStatus(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 23;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleBeltStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(24, Integer.valueOf(i)));
    }

    public void handlePowerAction(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(25, Integer.valueOf(i)));
    }

    public void handleChargingGunStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(26, Integer.valueOf(i)));
    }

    public void handleDeviceChargeStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(27, Integer.valueOf(i)));
    }

    public void handleDriverSeatStatus(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(28, Integer.valueOf(i)));
    }

    public void handleLightmode(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 29;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleVpmEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 30;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handlePsdMoto(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(31, Integer.valueOf(i)));
    }

    public void handlePowerOffCount(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(32, Integer.valueOf(i)));
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<ContextNaviInfoEventListener> it = this.mNaviListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
        Iterator<ContextCarInfoEventListener> it2 = this.mCarListeners.iterator();
        while (it2.hasNext()) {
            try {
                unregisterListener(it2.next());
            } catch (XUIServiceNotConnectedException unused2) {
            }
        }
        Iterator<ContextWeatherInfoEventListener> it3 = this.mWeatherListeners.iterator();
        while (it3.hasNext()) {
            try {
                unregisterListener(it3.next());
            } catch (XUIServiceNotConnectedException unused3) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IContextInfo.Stub.asInterface(iBinder);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void setServiceName(String str) {
        mServiceName = str;
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public String getServiceName() {
        if (mServiceName == null) {
            mServiceName = getClass().getSimpleName();
        }
        return mServiceName;
    }

    public void setCarSpeed(float f, int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setCarSpeed");
        try {
            this.mService.setCarSpeed(f, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setCarSpeed: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setCarAngular(float f, int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setCarAngular");
        try {
            this.mService.setCarAngular(f, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setCarAngular: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setCarAngularVelocity(float f) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setCarAngularVelocity");
        try {
            this.mService.setCarAngularVelocity(f);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setCarAngularVelocity: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getIntelligentEffectEnable() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getIntelligentEffectEnable");
        try {
            return this.mService.getIntelligentEffectEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getIntelligentEffectEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setIntelligentEffectEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setIntelligentEffectEnable");
        try {
            this.mService.setIntelligentEffectEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setIntelligentEffectEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setNavigationInfo(String str) throws XUIServiceNotConnectedException {
        try {
            this.mService.setNavigationInfo(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setNavigationInfo: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setNavigationEnable(boolean z) throws XUIServiceNotConnectedException {
        try {
            Log.d(TAG, "setNavigationEnable, enable: " + z);
            this.mService.setNavigationEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setNavigationEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String getWeather() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getWeather");
        try {
            return this.mService.getWeather();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getWeather: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void sendContextEvent(int i, int i2) throws XUIServiceNotConnectedException {
        Log.d(TAG, "sendContextEvent " + i + " " + i2);
        try {
            this.mService.sendContextEvent(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not sendContextEvent: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<ContextInfoManager> mMgr;

        EventCallbackHandler(ContextInfoManager contextInfoManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(contextInfoManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ContextInfoManager contextInfoManager = this.mMgr.get();
            switch (message.what) {
                case 0:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                        return;
                    }
                    return;
                case 1:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchAccelerationEventToClient(((Float) message.obj).floatValue());
                        return;
                    }
                    return;
                case 2:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchAngularVelocityEventToClient(((Float) message.obj).floatValue());
                        return;
                    }
                    return;
                case 3:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchManeuverEventToClient((Maneuver) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 4:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchRemainEventToClient((RemainInfo) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 5:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchNaviEventToClient((Navi) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 6:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchLaneEventToClient((Lane) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 7:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchCameraEventToClient((Camera) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 8:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchCameraIntervalEventToClient((CameraInterval) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 9:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchSapaEventToClient((Sapa) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 10:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchCrossEventToClient((Cross) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 11:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchLocationEventToClient((Location) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 12:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchNavigationInfoToClient((String) message.obj);
                        return;
                    }
                    return;
                case 13:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchNavigationEnableToClient(((Boolean) message.obj).booleanValue());
                        return;
                    }
                    return;
                case 14:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchMsgToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 15:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchWeatherInfoToClient((String) message.obj);
                        return;
                    }
                    return;
                case 16:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchCarSpeedToClient(((Float) message.obj).floatValue());
                        return;
                    }
                    return;
                case 17:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchIGStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 18:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchAutoBrightnessToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 19:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchXPilotWarningToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 20:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchAvpWifiToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 21:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchRemoteCommandToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 22:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchLLUStatusToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 23:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchATLSStatusToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 24:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchBeltStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 25:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchPowerActionToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 26:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchChargingGunStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 27:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchDeviceChargeStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 28:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchDriverSeatStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 29:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchBcmLightModeToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 30:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchVpmEventToClient(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 31:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchPsdMotoToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 32:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchPowerOffCountToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 33:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchGearStatusToClient(((Integer) message.obj).intValue());
                        return;
                    }
                    return;
                case 34:
                case 35:
                    return;
                case 36:
                    if (contextInfoManager != null) {
                        contextInfoManager.dispatchCommonEventToClient(((Integer) message.obj).intValue(), message.arg1);
                        return;
                    }
                    return;
                default:
                    Log.e(ContextInfoManager.TAG, "Event type not handled?" + message);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ContextInfoEventListenerToService extends IContextInfoEventListener.Stub {
        private final WeakReference<ContextInfoManager> mManager;

        public ContextInfoEventListenerToService(ContextInfoManager contextInfoManager) {
            this.mManager = new WeakReference<>(contextInfoManager);
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCommonEvent(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            Log.d(ContextInfoManager.TAG, "onCommonEvent " + i + " " + i2);
            if (contextInfoManager != null) {
                contextInfoManager.handleCommonEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAccelerationEvent(float f) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleAccelerationEvent(f);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAngularVelocityEvent(float f) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleAngularVelocityEvent(f);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onManeuverEvent(Maneuver maneuver, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleManeuverEvent(maneuver, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onRemainInfoEvent(RemainInfo remainInfo, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleRemainInfoEvent(remainInfo, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNaviEvent(Navi navi, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleNaviEvent(navi, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLaneEvent(Lane lane, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleLaneEvent(lane, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCameraEvent(Camera camera, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleCameraEvent(camera, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCameraIntervalEvent(CameraInterval cameraInterval, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleCameraIntervalEvent(cameraInterval, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onSapaEvent(Sapa sapa, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleSapaEvent(sapa, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCrossEvent(Cross cross, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleCrossEvent(cross, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLocationEvent(Location location, int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleLocationEvent(location, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNavigationInfo(String str) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleNavigationInfo(str);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNavigationEnable(boolean z) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleNavigationEnableEvent(z);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onMsgEvent(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleMsgEvent(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onWeatherInfo(String str) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleWeatherInfo(str);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onGearChanged(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleGearStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCarSpeed(float f) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleCarSpeed(f);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onIGStatus(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleIGStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAutoBrightness(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleAutoBrightness(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onXPilotWarning(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleXPilotWarnings(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAvpWifi(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleAvpWifi(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onRemoteCommand(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleRemoteCommand(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLLUStatus(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleLLUStatus(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onATLSStatus(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleATLSStatus(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onBeltStatus(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleBeltStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPowerAction(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handlePowerAction(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onChargingGunStatus(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleChargingGunStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onDeviceChargeStatus(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleDeviceChargeStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onDriverSeatStatus(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleDriverSeatStatus(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onBcmLightMode(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleLightmode(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onVpmEvent(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleVpmEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPsdMoto(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handlePsdMoto(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPowerOffCount(int i) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handlePowerOffCount(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onError(int i, int i2) {
            ContextInfoManager contextInfoManager = this.mManager.get();
            if (contextInfoManager != null) {
                contextInfoManager.handleErrorEvent(i, i2);
            }
        }
    }
}

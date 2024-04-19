package com.xiaopeng.xuimanager.smart;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Bundle;
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
import com.xiaopeng.xuimanager.smart.ISmart;
import com.xiaopeng.xuimanager.smart.ISmartCommonEventListener;
import com.xiaopeng.xuimanager.smart.ISmartEventListener;
import java.lang.ref.WeakReference;
import java.util.List;
@SystemApi
/* loaded from: classes2.dex */
public class SmartManager implements XUIManagerBase {
    public static final int ALARM_VOLUME_HIGH = 2;
    public static final int ALARM_VOLUME_LOW = 0;
    public static final int ALARM_VOLUME_MIDDLE = 1;
    public static final int AVAS_SOUND_EFFECT_A = 1;
    public static final int AVAS_SOUND_EFFECT_B = 2;
    public static final int AVAS_SOUND_EFFECT_C = 3;
    public static final int BOOT_SOUND_EFFECT_A = 1;
    public static final int BOOT_SOUND_EFFECT_B = 2;
    public static final int BOOT_SOUND_EFFECT_C = 3;
    public static final int BOOT_SOUND_EFFECT_DISABLE = 0;
    public static final int COMMON_TYPE_AI_LLU = 4096;
    public static final boolean DBG = true;
    public static final String EFFECT_AC_CHARGE = "android_ac";
    public static final String EFFECT_AWAKE = "android_unlock_01";
    public static final String EFFECT_DC_CHARGE = "android_dc";
    public static final String EFFECT_FIND_CAR = "android_findcar";
    public static final String EFFECT_FULL_CHARGED = "fullcharged";
    public static final String EFFECT_LOCK = "android_lock";
    public static final String EFFECT_LOCK_01 = "android_lock_01";
    public static final String EFFECT_LOCK_02 = "android_lock_02";
    public static final String EFFECT_LOCK_03 = "android_lock_03";
    public static final String EFFECT_SAYHI = "android_sayhi";
    public static final String EFFECT_SAYHI_01 = "android_sayhi_01";
    public static final String EFFECT_SAYHI_02 = "android_sayhi_02";
    public static final String EFFECT_SAYHI_03 = "android_sayhi_03";
    public static final String EFFECT_SHOW_OFF = "android_sayhi_01";
    public static final String EFFECT_SLEEP = "android_lock_01";
    public static final String EFFECT_TAKE_PHOTO = "takephoto";
    public static final String EFFECT_UNLOCK = "android_unlock";
    public static final String EFFECT_UNLOCK_01 = "android_unlock_01";
    public static final String EFFECT_UNLOCK_02 = "android_unlock_02";
    public static final String EFFECT_UNLOCK_03 = "android_unlock_03";
    public static final int ErrorCode_Common = -4;
    public static final int ErrorCode_DanceError = -4096;
    public static final int ErrorCode_Forbidden = -1;
    public static final int ErrorCode_NoFocus = -2;
    public static final int LIGHT_EFFECT_ACCHARGE = 6;
    public static final int LIGHT_EFFECT_AWAKE = 2;
    public static final int LIGHT_EFFECT_DCCHARGE = 7;
    public static final int LIGHT_EFFECT_FINDCAR = 1;
    public static final int LIGHT_EFFECT_SHOWOFF = 10;
    public static final int LIGHT_EFFECT_SLEEP = 5;
    public static final int LIGHT_EFFECT_TAKEPHOTO = 9;
    public static final int LLU_EFFECT_CLOSE = 0;
    public static final int LLU_EFFECT_MODE_A = 1;
    public static final int LLU_EFFECT_MODE_B = 2;
    public static final int LLU_EFFECT_MODE_C = 3;
    public static final int LLU_MODE1 = 1;
    public static final int LLU_MODE2 = 2;
    public static final int LLU_MODE3 = 3;
    private static final int MSG_SMART_AI_LLU_EVENT = 6;
    private static final int MSG_SMART_ERROR_EVENT = 0;
    private static final int MSG_SMART_LIGHT_EFFECT_FINISH_EVENT = 1;
    private static final int MSG_SMART_LIGHT_EFFECT_SHOW_ERROR = 5;
    private static final int MSG_SMART_LIGHT_EFFECT_SHOW_FINISH_EVENT = 4;
    private static final int MSG_SMART_LIGHT_EFFECT_SHOW_START_EVENT = 2;
    private static final int MSG_SMART_LIGHT_EFFECT_SHOW_STOP_EVENT = 3;
    private static final int MSG_SMART_SPEECH_TTS_EVENT = 7;
    public static final int SPEED_VOLUME_SOFT = 3;
    public static final int SPEED_VOLUME_STANDARD = 1;
    public static final int SPEED_VOLUME_SURGE = 2;
    public static final String TAG = "SmartManager";
    public static final int TOUCH_ROTATION_DIRECTION_INWARD = 0;
    public static final int TOUCH_ROTATION_DIRECTION_OUTWARD = 1;
    public static final String Type_Dance = "dance";
    public static final String Type_LightDance = "lightanddance";
    public static final int XBOSS_MUTE_UNMUTE = 2;
    public static final int XBOSS_NONE = 0;
    public static final int XBOSS_SHOW_OFF = 4;
    public static final int XBOSS_SWITCH_MEDIA = 3;
    public static final int XBOSS_TAKE_PHOTO = 5;
    public static final int XBOSS_TEAM_COMMUNICATION = 6;
    public static final int XBOSS_VOICE_ACTIVE = 1;
    public static final int XKEY_AUTO_PARK = 4;
    public static final int XKEY_AUTO_PILOT = 5;
    public static final int XKEY_NONE = 0;
    public static final int XKEY_SHOW_OFF = 2;
    public static final int XKEY_SWITCH_MEDIA = 1;
    public static final int XKEY_TAKE_PHOTO = 3;
    public static final int XKEY_TEAM_COMMUNICATION = 7;
    public static final int XKEY_VOICE_CHAT = 6;
    private static String mServiceName;
    private final Handler mHandler;
    private ISmart mService;
    private final String DEFAULT_LDANCE_NAME = "PianoConcerto";
    private final ArraySet<SmartEventListener> mListeners = new ArraySet<>();
    private SmartEventListenerToService mListenerToService = null;
    private final ArraySet<SmartCommonEventListener> mCommonListeners = new ArraySet<>();
    private SmartCommonEventListenerToService mCommonListenerToService = null;

    /* loaded from: classes2.dex */
    public interface SmartCommonEventListener {
        default void onCommonEvent(int i, int i2, int i3) {
        }

        default void onSpeechTtsEvent(int i, String str) {
        }
    }

    /* loaded from: classes2.dex */
    public interface SmartEventListener {
        void onErrorEvent(int i, int i2);

        void onLightEffectFinishEvent(int i, int i2);

        default void onLightEffectShowError(String str, int i) {
        }

        default void onLightEffectShowFinishEvent(String str, String str2) {
        }

        default void onLightEffectShowStartEvent(String str, String str2) {
        }

        default void onLightEffectShowStopEvent(String str, String str2) {
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
    }

    public SmartManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = ISmart.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(SmartEventListener smartEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new SmartEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(smartEventListener);
    }

    public synchronized void unregisterListener(SmartEventListener smartEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mListeners.remove(smartEventListener);
        if (this.mListeners.isEmpty() && this.mListenerToService != null) {
            try {
                this.mService.unregisterListener(this.mListenerToService);
                this.mListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public synchronized void registerCommonListener(SmartCommonEventListener smartCommonEventListener) throws XUIServiceNotConnectedException {
        if (this.mCommonListeners.isEmpty()) {
            try {
                this.mCommonListenerToService = new SmartCommonEventListenerToService(this);
                this.mService.registerCommonListener(this.mCommonListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mCommonListeners.add(smartCommonEventListener);
    }

    public synchronized void unregisterCommonListener(SmartCommonEventListener smartCommonEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mCommonListeners.remove(smartCommonEventListener);
        if (this.mCommonListeners.isEmpty() && this.mCommonListenerToService != null) {
            try {
                this.mService.unregisterCommonListener(this.mCommonListenerToService);
                this.mCommonListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onErrorEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectFinishEventToClient(int i, int i2) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onLightEffectFinishEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectShowStartEventToClient(String str, String str2) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onLightEffectShowStartEvent(str, str2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching onLightEffectShowStartEvent event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectShowStopEventToClient(String str, String str2) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onLightEffectShowStopEvent(str, str2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching onLightEffectShowStopEvent event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectShowFinishEventToClient(String str, String str2) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onLightEffectShowFinishEvent(str, str2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching onLightEffectShowFinishEvent event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectErrorToClient(String str, int i) {
        ArraySet<SmartEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartEventListener smartEventListener : arraySet) {
                smartEventListener.onLightEffectShowError(str, i);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching onLightEffectShowError event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCommonEventToClient(int i, int i2, int i3) {
        ArraySet<SmartCommonEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCommonListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartCommonEventListener smartCommonEventListener : arraySet) {
                smartCommonEventListener.onCommonEvent(i, i2, i3);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchSpeechTtsEventToClient(int i, String str) {
        ArraySet<SmartCommonEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mCommonListeners;
        }
        if (!arraySet.isEmpty()) {
            for (SmartCommonEventListener smartCommonEventListener : arraySet) {
                smartCommonEventListener.onSpeechTtsEvent(i, str);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightEffectFinishEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightEffectShowStartEvent(String str, String str2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = str;
        Bundle bundle = new Bundle();
        bundle.putString("type", str2);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightEffectShowStopEvent(String str, String str2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.obj = str;
        Bundle bundle = new Bundle();
        bundle.putString("type", str2);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightEffectShowFinishEvent(String str, String str2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 4;
        obtainMessage.obj = str;
        Bundle bundle = new Bundle();
        bundle.putString("type", str2);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightEffectShowError(String str, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 5;
        obtainMessage.obj = str;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleErrorEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCommonEvent(int i, int i2, int i3) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 6;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        obtainMessage.arg2 = i3;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSpeechTtsEvent(int i, String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 7;
        obtainMessage.obj = Integer.valueOf(i);
        Bundle bundle = new Bundle();
        bundle.putString("ttsId", str);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = ISmart.Stub.asInterface(iBinder);
        if (!this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new SmartEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (Exception e) {
                Log.e(TAG, "Could not connect: " + e.toString());
            }
        }
        if (this.mCommonListeners.isEmpty()) {
            return;
        }
        try {
            this.mCommonListenerToService = new SmartCommonEventListenerToService(this);
            this.mService.registerCommonListener(this.mCommonListenerToService);
        } catch (Exception e2) {
            Log.e(TAG, "Could not connect: " + e2.toString());
        }
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

    public boolean getVolumeDownWithDoorOpen() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getVolumeDownWithDoorOpen");
        try {
            return this.mService.getVolumeDownWithDoorOpen();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getVolumeDownWithDoorOpen: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setVolumeDownWithDoorOpen(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setVolumeDownWithDoorOpen " + z);
        try {
            this.mService.setVolumeDownWithDoorOpen(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setVolumeDownWithDoorOpen: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getVolumeDownInReverseMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getVolumeDownInReverseMode");
        try {
            return this.mService.getVolumeDownInReverseMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getVolumeDownInReverseMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setVolumeDownInReverseMode(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setVolumeDownInReverseMode " + z);
        try {
            this.mService.setVolumeDownInReverseMode(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setVolumeDownInReverseMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getAlarmFromAzimuthOrIcm() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getAlarmFromAzimuthOrIcm");
        try {
            return this.mService.getAlarmFromAzimuthOrIcm();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAlarmFromAzimuthOrIcm: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAlarmFromAzimuthOrIcm(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setAlarmFromAzimuthOrIcm " + z);
        try {
            this.mService.setAlarmFromAzimuthOrIcm(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAlarmFromAzimuthOrIcm: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getKeyBoardTouchPrompt() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getKeyBoardTouchPrompt");
        try {
            return this.mService.getKeyBoardTouchPrompt();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getVolumeDownWithDoorOpen: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setKeyBoardTouchPrompt(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setKeyBoardTouchPrompt " + z);
        try {
            this.mService.setKeyBoardTouchPrompt(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setKeyBoardTouchPrompt: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getXKeyForCustomer() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getXKeyForCustomer");
        try {
            return this.mService.getXKeyForCustomer();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getXKeyForCustomer: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setXKeyForCustomer(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setXKeyForCustomer " + i);
        try {
            this.mService.setXKeyForCustomer(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setXKeyForCustomer: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getBossKeyForCustomer() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getBossKeyForCustomer");
        try {
            return this.mService.getBossKeyForCustomer();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getBossKeyForCustomer: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setBossKeyForCustomer(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setBossKeyForCustomer " + i);
        try {
            this.mService.setBossKeyForCustomer(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setBossKeyForCustomer: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getTouchRotationDirection() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getTouchRotationDirection");
        try {
            return this.mService.getTouchRotationDirection();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getTouchRotationDirection: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setTouchRotationDirection(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setTouchRotationDirection");
        try {
            this.mService.setTouchRotationDirection(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setTouchRotationDirection: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public List<String> getLangLightEffectNameList(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLangLightEffectNameList(" + i + ")");
        try {
            return this.mService.getLangLightEffectNameList(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLangLightEffectNameList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLangLightEffectMode(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLangLightEffectMode() " + str);
        setLangLightEffectMode(str, 1);
    }

    public void setLangLightEffectMode(String str, int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLangLightEffectMode() " + str + " " + i);
        try {
            this.mService.setLangLightEffectMode(str, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLangLightEffectMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLangLightEffectWithMode(String str, int i, int i2) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLangLightEffectWithMode() " + str + " " + i + " " + i2);
        try {
            this.mService.setLangLightEffectWithMode(str, i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLangLightEffectWithMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String getRunnningLluEffectName() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getRunnningLluEffectName();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getRunnningLluEffectName ");
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getLluWakeWaitMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLluWakeWaitMode");
        try {
            return this.mService.getLluWakeWaitMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLluWakeWaitMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLluWakeWaitMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLluWakeWaitMode() " + i);
        try {
            this.mService.setLluWakeWaitMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLluWakeWaitMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getLluSleepMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLluSleepMode");
        try {
            return this.mService.getLluSleepMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLluSleepMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLluSleepMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLluSleepMode() " + i);
        try {
            this.mService.setLluSleepMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLluSleepMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setFftLLUEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setFftLLUEnable " + z);
        try {
            this.mService.setFftLLUEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setFftLLUEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getFftLLUEnable() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getFftLLUEnable");
        try {
            return this.mService.getFftLLUEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getFftLLUEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setPause(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setPause() :" + z);
        try {
            this.mService.setPause(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setPause: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stopLightEffectShow() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopLightEffectShow()");
        try {
            this.mService.stopLightEffectShow();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopLightEffectShow: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void updateEffectFiles(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "updateEffectFiles()" + i);
        try {
            this.mService.updateEffectFiles(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not updateEffectFiles: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getLangLightEffectEnable() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLangLightEffectEnable");
        try {
            return this.mService.getLangLightEffectEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLangLightEffectEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLangLightEffectEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLangLightEffectEnable() enable:" + z);
        try {
            this.mService.setLangLightEffectEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLangLightEffectEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicSpectrumToLangLight(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMusicSpectrumToLangLight() " + i);
        try {
            this.mService.setMusicSpectrumToLangLight(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicSpectrumToLangLight: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int isLightDanceAvailable() throws XUIServiceNotConnectedException {
        try {
            return this.mService.isLightDanceAvailable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not get LightDanceAvailable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int startLightDancing() {
        return startLightDancing(1);
    }

    public int startLightDancing(int i) {
        try {
            return startLightDancing("PianoConcerto", i);
        } catch (Exception e) {
            Log.e(TAG, "Could not startLightDancing: " + e.toString());
            return -1;
        }
    }

    public int startLightDancing(String str, int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "startLightDancing() " + str);
        try {
            return this.mService.startLightDancing(str, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not startLightDancing: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int stopLightDancing() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopLightDancing() ");
        try {
            return this.mService.stopLightDancing();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopLightDancing: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean isLightDancing() throws XUIServiceNotConnectedException {
        Log.d(TAG, "isLightDancing() ");
        try {
            return this.mService.isLightDancing();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not isLightDancing: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getAlarmVolume() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getAlarmVolume()");
        try {
            return this.mService.getAlarmVolume();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAlarmVolume: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAlarmVolume(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setAlarmVolume(int type):" + i);
        try {
            this.mService.setAlarmVolume(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAlarmVolume: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicTableForDebug(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMusicTableForDebug(int musicTable):" + i);
        try {
            this.mService.setMusicTableForDebug(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicTableForDebug: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicStartTickNumForDebug(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMusicStartTickNumForDebug(int tickNum):" + i);
        try {
            this.mService.setMusicStartTickNumForDebug(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicStartTickNumForDebug: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicStopTickNumForDebug(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMusicStopTickNumForDebug(int tickNum):" + i);
        try {
            this.mService.setMusicStopTickNumForDebug(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicStopTickNumForDebug: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicDelayTimeForDebug(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMusicDelayTimeForDebug(int delayTime):" + i);
        try {
            this.mService.setMusicDelayTimeForDebug(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicDelayTimeForDebug: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLangLightMusicEffect(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLangLightMusicEffect() " + str);
        try {
            this.mService.setLangLightMusicEffect(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLangLightMusicEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getLightEffect(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLightEffect(effectName): effectName:" + i);
        try {
            return this.mService.getLightEffect(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLightEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLightEffect(int i, int i2) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLightEffect(effectName,effectMode): effectName:" + i + " effectMode:" + i2);
        try {
            this.mService.setLightEffect(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLightEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setLightEffectAndMusic(int i, int i2, int i3) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setLightEffectAndMusic(int messageID, int effectName, int music): messageID:" + i + " effectName:" + i2 + " effectMusic:" + i3);
        try {
            this.mService.setLightEffectAndMusic(i, i2, i3);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setLightEffectAndMusic: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getSayHiEnable() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getSayHiEnable");
        try {
            return this.mService.getSayHiEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getSayHiEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setSayHiEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setSayHiEnable(boolean enable):" + z);
        try {
            this.mService.setSayHiEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setSayHiEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getSayHiEffect() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getSayHiEffect");
        try {
            return this.mService.getSayHiEffect();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getSayHiEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setSayHiEffect(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setSayHiEffect(int type):" + i);
        try {
            this.mService.setSayHiEffect(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setSayHiEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getBootSoundEffect() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getBootSoundEffect");
        try {
            return this.mService.getBootSoundEffect();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getBootSoundEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setBootSoundEffect(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setBootSoundEffect(int type):" + i);
        try {
            this.mService.setBootSoundEffect(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setBootSoundEffect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void enableCarSpeedVolume(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "enableCarSpeedVolume() :" + z);
        try {
            this.mService.enableCarSpeedVolume(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not enableCarSpeedVolume: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setSpeedVolumeMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setSpeedVolumeMode() :" + i);
        try {
            this.mService.setSpeedVolumeMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setSpeedVolumeMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void startAiLluMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "startAiLluMode() :" + i);
        try {
            this.mService.startAiLluMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not startAiLluMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stopAiLluMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopAiLluMode()");
        try {
            this.mService.stopAiLluMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopAiLluMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getSpeechStatus() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getSpeechStatus()");
        try {
            return this.mService.getSpeechStatus();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getSpeechStatus: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String speakByNormal(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "speakByNormal()");
        try {
            return this.mService.speakByNormal(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not speakByNormal: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String speakByImportant(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "speakByImportant()");
        try {
            return this.mService.speakByImportant(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not speakByImportant: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String speakByUrgent(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "speakByUrgent()");
        try {
            return this.mService.speakByUrgent(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not speakByUrgent: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String speakByInstant(String str, boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "speakByInstant()");
        try {
            return this.mService.speakByInstant(str, z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not speakByInstant: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stopSpeech(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopSpeech()");
        try {
            this.mService.stopSpeech(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopSpeech: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stopAllSpeech() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopAllSpeech()");
        try {
            this.mService.stopAllSpeech();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopAllSpeech: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int startMicRecordMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "startMicRecordMode(" + i + ")");
        try {
            return this.mService.startMicRecordMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not startMicRecordMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int stopMicRecordMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopMicRecordMode()");
        try {
            return this.mService.stopMicRecordMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopMicRecordMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<SmartManager> mMgr;

        EventCallbackHandler(SmartManager smartManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(smartManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SmartManager smartManager = this.mMgr.get();
            switch (message.what) {
                case 0:
                    if (smartManager != null) {
                        smartManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                        return;
                    }
                    return;
                case 1:
                    if (smartManager != null) {
                        smartManager.dispatchLightEffectFinishEventToClient(((Integer) message.obj).intValue(), message.arg1);
                        return;
                    }
                    return;
                case 2:
                    if (smartManager != null) {
                        smartManager.dispatchLightEffectShowStartEventToClient((String) message.obj, message.getData().getString("type"));
                        return;
                    }
                    return;
                case 3:
                    if (smartManager != null) {
                        smartManager.dispatchLightEffectShowStopEventToClient((String) message.obj, message.getData().getString("type"));
                        return;
                    }
                    return;
                case 4:
                    if (smartManager != null) {
                        smartManager.dispatchLightEffectShowFinishEventToClient((String) message.obj, message.getData().getString("type"));
                        return;
                    }
                    return;
                case 5:
                    if (smartManager != null) {
                        smartManager.dispatchLightEffectErrorToClient((String) message.obj, message.arg1);
                        return;
                    }
                    return;
                case 6:
                    if (smartManager != null) {
                        smartManager.dispatchCommonEventToClient(((Integer) message.obj).intValue(), message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 7:
                    if (smartManager != null) {
                        smartManager.dispatchSpeechTtsEventToClient(((Integer) message.obj).intValue(), message.getData().getString("ttsId"));
                        return;
                    }
                    return;
                default:
                    Log.e(SmartManager.TAG, "Event type not handled?" + message);
                    return;
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class SmartEventListenerToService extends ISmartEventListener.Stub {
        private final WeakReference<SmartManager> mManager;

        public SmartEventListenerToService(SmartManager smartManager) {
            this.mManager = new WeakReference<>(smartManager);
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onError(int i, int i2) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleErrorEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectFinishEvent(int i, int i2) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleLightEffectFinishEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowStartEvent(String str, String str2) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleLightEffectShowStartEvent(str, str2);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowStopEvent(String str, String str2) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleLightEffectShowStopEvent(str, str2);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowFinishEvent(String str, String str2) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleLightEffectShowFinishEvent(str, str2);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowError(String str, int i) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleLightEffectShowError(str, i);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class SmartCommonEventListenerToService extends ISmartCommonEventListener.Stub {
        private final WeakReference<SmartManager> mManager;

        public SmartCommonEventListenerToService(SmartManager smartManager) {
            this.mManager = new WeakReference<>(smartManager);
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
        public void onSmartCommonEvent(int i, int i2, int i3) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleCommonEvent(i, i2, i3);
            }
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
        public void onSmartSpeechTtsEvent(int i, String str) {
            SmartManager smartManager = this.mManager.get();
            if (smartManager != null) {
                smartManager.handleSpeechTtsEvent(i, str);
            }
        }
    }
}

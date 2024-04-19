package com.xiaopeng.xuimanager.ambientlight;

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
import com.xiaopeng.xuimanager.ambientlight.IAmbientLight;
import com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
@SystemApi
/* loaded from: classes2.dex */
public class AmbientLightManager implements XUIManagerBase {
    public static final boolean DBG = false;
    public static final String EFFECT_FOLLOW_SPEED = "follow_speed";
    public static final String EFFECT_GENTLE_BREATHING = "gentle_breathing";
    public static final String EFFECT_MUSIC = "music";
    public static final String EFFECT_STABLE_EFFECT = "stable_effect";
    public static final int LIGHT_COLOR_1 = 1;
    public static final int LIGHT_COLOR_10 = 10;
    public static final int LIGHT_COLOR_11 = 11;
    public static final int LIGHT_COLOR_12 = 12;
    public static final int LIGHT_COLOR_13 = 13;
    public static final int LIGHT_COLOR_14 = 14;
    public static final int LIGHT_COLOR_15 = 15;
    public static final int LIGHT_COLOR_16 = 16;
    public static final int LIGHT_COLOR_17 = 17;
    public static final int LIGHT_COLOR_18 = 18;
    public static final int LIGHT_COLOR_19 = 19;
    public static final int LIGHT_COLOR_2 = 2;
    public static final int LIGHT_COLOR_20 = 20;
    public static final int LIGHT_COLOR_3 = 3;
    public static final int LIGHT_COLOR_4 = 4;
    public static final int LIGHT_COLOR_5 = 5;
    public static final int LIGHT_COLOR_6 = 6;
    public static final int LIGHT_COLOR_7 = 7;
    public static final int LIGHT_COLOR_8 = 8;
    public static final int LIGHT_COLOR_9 = 9;
    private static final int MSG_AMBIENTLIGHT_DOUBLE_COLOR_ENABLE_EVENT = 2;
    private static final int MSG_AMBIENTLIGHT_DOUBLE_COLOR_SET_EVENT = 4;
    private static final int MSG_AMBIENTLIGHT_EFFECT_TYPE_SET_EVENT = 1;
    private static final int MSG_AMBIENTLIGHT_ERROR_EVENT = 0;
    private static final int MSG_AMBIENTLIGHT_MONO_COLOR_SET_EVENT = 3;
    public static final String TAG = "AmbientLightManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IAmbientLight mService;
    private final ArraySet<AmbientLightEventListener> mListeners = new ArraySet<>();
    private AmbientLightEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface AmbientLightEventListener {
        void onErrorEvent(int i, int i2);

        default void onLightDoubleColorEnableEvent(String str, boolean z) {
        }

        default void onLightDoubleColorSetEvent(String str, int i, int i2) {
        }

        default void onLightEffectTypeSetEvent(String str) {
        }

        default void onLightMonoColorSetEvent(String str, int i) {
        }
    }

    public AmbientLightManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IAmbientLight.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(AmbientLightEventListener ambientLightEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new AmbientLightEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(ambientLightEventListener);
    }

    public synchronized void unregisterListener(AmbientLightEventListener ambientLightEventListener) throws XUIServiceNotConnectedException {
        this.mListeners.remove(ambientLightEventListener);
        if (this.mListeners.isEmpty()) {
            try {
                this.mService.unregisterListener(this.mListenerToService);
                this.mListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<AmbientLightEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AmbientLightEventListener ambientLightEventListener : arraySet) {
                ambientLightEventListener.onErrorEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightEffectTypeSetEventToClient(String str) {
        ArraySet<AmbientLightEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AmbientLightEventListener ambientLightEventListener : arraySet) {
                ambientLightEventListener.onLightEffectTypeSetEvent(str);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightDoubleColorEnableEventToClient(String str, boolean z) {
        ArraySet<AmbientLightEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AmbientLightEventListener ambientLightEventListener : arraySet) {
                ambientLightEventListener.onLightDoubleColorEnableEvent(str, z);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightMonoColorSetEventToClient(String str, int i) {
        ArraySet<AmbientLightEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AmbientLightEventListener ambientLightEventListener : arraySet) {
                ambientLightEventListener.onLightMonoColorSetEvent(str, i);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchLightDoubleColorSetEventToClient(String str, int i, int i2) {
        ArraySet<AmbientLightEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AmbientLightEventListener ambientLightEventListener : arraySet) {
                ambientLightEventListener.onLightDoubleColorSetEvent(str, i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
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
    public void handleLightEffectTypeSetEvent(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = str;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightDoubleColorEnableEvent(String str, boolean z) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = str;
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", z);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightMonoColorSetEvent(String str, int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.obj = str;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightDoubleColorSetEvent(String str, int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 4;
        obtainMessage.obj = str;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<AmbientLightEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IAmbientLight.Stub.asInterface(iBinder);
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

    public boolean getMusicSpectrumEnable() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getMusicSpectrumEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getMusicSpectrumEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicSpectrumEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setMusicSpectrumEnable() " + z);
        try {
            this.mService.setMusicSpectrumEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicSpectrumEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMusicRhythmMode(boolean z) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setMusicRhythmMode()" + z);
        try {
            this.mService.setMusicRhythmMode(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMusicRhythmMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getAmbientLightMonoColor(String str) throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightMonoColor(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightMonoColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAmbientLightMonoColor(String str, int i) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setAmbientLightMonoColor(), effectType:" + str + " color:" + i);
        try {
            this.mService.setAmbientLightMonoColor(str, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAmbientLightMonoColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getAmbientLightDoubleFirstColor(String str) throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightDoubleFirstColor(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightDoubleFirstColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getAmbientLightDoubleSecondColor(String str) throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightDoubleSecondColor(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightDoubleSecondColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAmbientLightDoubleColor(String str, int i, int i2) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setAmbientLightDoubleColor() " + str + " " + i + " " + i2);
        try {
            this.mService.setAmbientLightDoubleColor(str, i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAmbientLightDoubleColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getDoubleThemeColorEnable(String str) throws XUIServiceNotConnectedException {
        try {
            return this.mService.getDoubleThemeColorEnable(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getDoubleThemeColorEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setDoubleThemeColorEnable(String str, boolean z) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setDoubleThemeColorEnable() " + str + " " + z);
        try {
            this.mService.setDoubleThemeColorEnable(str, z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setDoubleThemeColorEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String getAmbientLightEffectType() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightEffectType();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightEffectType: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAmbientLightEffectType(String str) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setAmbientLightEffectType() " + str);
        try {
            this.mService.setAmbientLightEffectType(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAmbientLightEffectType: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getAmbientLightOpen() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightOpen();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightOpen: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setAmbientLightOpen(boolean z) throws XUIServiceNotConnectedException {
        Log.i(TAG, "setAmbientLightOpen() " + z);
        try {
            this.mService.setAmbientLightOpen(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAmbientLightOpen: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public List<String> getAmbientLightEffectTypeList() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getAmbientLightEffectTypeList();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAmbientLightEffectTypeList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean isSupportDoubleThemeColor(String str) throws XUIServiceNotConnectedException {
        try {
            return this.mService.isSupportDoubleThemeColor(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not isSupportDoubleThemeColor: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<AmbientLightManager> mMgr;

        EventCallbackHandler(AmbientLightManager ambientLightManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(ambientLightManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AmbientLightManager ambientLightManager = this.mMgr.get();
            int i = message.what;
            if (i == 0) {
                if (ambientLightManager != null) {
                    ambientLightManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                }
            } else if (i == 1) {
                if (ambientLightManager != null) {
                    ambientLightManager.dispatchLightEffectTypeSetEventToClient((String) message.obj);
                }
            } else if (i == 2) {
                if (ambientLightManager != null) {
                    ambientLightManager.dispatchLightDoubleColorEnableEventToClient((String) message.obj, message.getData().getBoolean("enable"));
                }
            } else if (i == 3) {
                if (ambientLightManager != null) {
                    ambientLightManager.dispatchLightMonoColorSetEventToClient((String) message.obj, message.arg1);
                }
            } else if (i == 4) {
                if (ambientLightManager != null) {
                    ambientLightManager.dispatchLightDoubleColorSetEventToClient((String) message.obj, message.arg1, message.arg2);
                }
            } else {
                Log.e(AmbientLightManager.TAG, "Event type not handled?" + message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AmbientLightEventListenerToService extends IAmbientLightEventListener.Stub {
        private final WeakReference<AmbientLightManager> mManager;

        public AmbientLightEventListenerToService(AmbientLightManager ambientLightManager) {
            this.mManager = new WeakReference<>(ambientLightManager);
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onError(int i, int i2) {
            AmbientLightManager ambientLightManager = this.mManager.get();
            if (ambientLightManager != null) {
                ambientLightManager.handleErrorEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightEffectTypeSetEvent(String str) {
            AmbientLightManager ambientLightManager = this.mManager.get();
            if (ambientLightManager != null) {
                ambientLightManager.handleLightEffectTypeSetEvent(str);
            }
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightDoubleColorEnableEvent(String str, boolean z) {
            AmbientLightManager ambientLightManager = this.mManager.get();
            if (ambientLightManager != null) {
                ambientLightManager.handleLightDoubleColorEnableEvent(str, z);
            }
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightMonoColorSetEvent(String str, int i) {
            AmbientLightManager ambientLightManager = this.mManager.get();
            if (ambientLightManager != null) {
                ambientLightManager.handleLightMonoColorSetEvent(str, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightDoubleColorSetEvent(String str, int i, int i2) {
            AmbientLightManager ambientLightManager = this.mManager.get();
            if (ambientLightManager != null) {
                ambientLightManager.handleLightDoubleColorSetEvent(str, i, i2);
            }
        }
    }
}

package com.xiaopeng.xuimanager.karaoke;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.karaoke.IKaraoke;
import com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
@SystemApi
/* loaded from: classes2.dex */
public class KaraokeManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MSG_KARAOKE_ERROR_EVENT = 0;
    private static final int MSG_KARAOKE_EVENT = 1;
    public static final String TAG = "KaraokeManager";
    private static String mServiceName;
    private String callingApp;
    private final Context mContext;
    private final Handler mHandler;
    private MicCallBack mMicCallBack;
    private IKaraoke mService;
    private final IBinder mICallBack = new Binder();
    private final ArraySet<MicCallBack> mListeners = new ArraySet<>();
    private KaraokeEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface MicCallBack {
        public static final int MIC_POWER_OFF = 6;
        public static final int MIC_POWER_ON = 5;
        public static final int UDB_DONGLE_OFF = 4;
        public static final int UDB_DONGLE_ON = 3;

        void micServiceCallBack(int i);

        void onErrorEvent(int i, int i2);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<KaraokeManager> mMgr;

        EventCallbackHandler(KaraokeManager karaokeManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(karaokeManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                KaraokeManager karaokeManager = this.mMgr.get();
                if (karaokeManager != null) {
                    karaokeManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                }
            } else if (i == 1) {
                KaraokeManager karaokeManager2 = this.mMgr.get();
                if (karaokeManager2 != null) {
                    karaokeManager2.dispatchEventToClient(message.arg1);
                }
            } else {
                Log.e(KaraokeManager.TAG, "Event type not handled?" + message);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class KaraokeEventListenerToService extends IKaraokeEventListener.Stub {
        private final WeakReference<KaraokeManager> mManager;

        public KaraokeEventListenerToService(KaraokeManager karaokeManager) {
            this.mManager = new WeakReference<>(karaokeManager);
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
        public void onError(int i, int i2) {
            KaraokeManager karaokeManager = this.mManager.get();
            if (karaokeManager != null) {
                karaokeManager.handleErrorEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
        public void MicDevChangeCallBack(int i) {
            KaraokeManager karaokeManager = this.mManager.get();
            if (karaokeManager != null) {
                karaokeManager.handleEvent(i);
            }
        }
    }

    public KaraokeManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IKaraoke.Stub.asInterface(iBinder);
        this.mContext = context;
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
        this.callingApp = context.getPackageManager().getNameForUid(Binder.getCallingUid());
    }

    public synchronized void registerListener(MicCallBack micCallBack) throws XUIServiceNotConnectedException {
        this.mListeners.add(micCallBack);
    }

    public synchronized void unregisterListener(MicCallBack micCallBack) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mListeners.remove(micCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<MicCallBack> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (MicCallBack micCallBack : arraySet) {
                micCallBack.onErrorEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener died, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEventToClient(int i) {
        Log.d(TAG, "dispatchEventToClient  event:" + i + "  mMicCallBack:" + this.mMicCallBack);
        MicCallBack micCallBack = this.mMicCallBack;
        if (micCallBack != null) {
            micCallBack.micServiceCallBack(i);
        }
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
    public void handleEvent(int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.arg1 = i;
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<MicCallBack> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IKaraoke.Stub.asInterface(iBinder);
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

    public int XMA_registerCallback(MicCallBack micCallBack) throws RemoteException {
        this.mMicCallBack = micCallBack;
        try {
            if (this.mListenerToService == null) {
                this.mListenerToService = new KaraokeEventListenerToService(this);
            }
            this.mService.XMS_RegisterCallback(this.callingApp, this.mListenerToService);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int XMA_unRegisterCallback() throws RemoteException {
        this.mMicCallBack = null;
        try {
            this.mService.XMS_UnRegisterCallback(this.callingApp, this.mListenerToService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mListenerToService = null;
        return 0;
    }

    public int XMA_init(String str) {
        if (this.mService == null) {
            return -1;
        }
        try {
            Log.d(TAG, "XMA_init  " + str);
            this.mService.XMS_Create(this.callingApp, 0, "", this.mICallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int XMA_deinit() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke == null) {
            return -1;
        }
        try {
            iKaraoke.XMS_Distroy(this.callingApp);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int XMA_getToken() {
        Context context = this.mContext;
        if (context != null) {
            this.callingApp = context.getPackageManager().getNameForUid(Binder.getCallingUid());
        }
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke == null) {
            return -1;
        }
        try {
            return iKaraoke.XMS_GetToken(this.callingApp);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int XMA_getSignedToken(String str) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke == null) {
            return -1;
        }
        try {
            iKaraoke.XMS_SetSignedToken(this.callingApp, str);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int XMA_getHandShakeStatus() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_GetHandShakeStatus(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_getMicStatus() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_GetMicStatus(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_getMicPowerStatus() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_GetMicPowerStatus(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_setVolume(int i, int i2) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke == null) {
            return -1;
        }
        try {
            iKaraoke.XMS_SetVolume(this.callingApp, i, i2);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int XMA_getVolume(int i) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_GetVolume(this.callingApp, i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_trackGetMinBuf(int i, int i2) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_TrackGetMinBuf(this.callingApp, i, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_trackCreate(int i, int i2, int i3) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke == null) {
            return -1;
        }
        try {
            iKaraoke.XMS_TrackCreate(this.callingApp, i, i2, i3);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int XMA_trackGetLatency() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_TrackGetLatency(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_write(byte[] bArr, int i, int i2) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_Write(this.callingApp, bArr, i, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_trackDestroy() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_TrackDestroy(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_pause() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_Pause(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_resume() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_Resume(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_pausePlay() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_PausePlay(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_resumePlay() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_ResumePlay(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_recGetMinBuf(int i, int i2) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_RecGetMinBuf(this.callingApp, i, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_recCreate(int i, int i2, int i3) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_RecCreate(this.callingApp, i, i2, i3);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_recGetAvail() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_RecGetAvail(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_readRec(byte[] bArr) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_Read(this.callingApp, bArr, -1);
            } catch (RemoteException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int XMA_recDestroy() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_RecDestroy(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_micGetMinBuf(int i, int i2) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_MicGetMinBuf(this.callingApp, i, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_micCreate(int i, int i2, int i3) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_MicCreate(this.callingApp, i, i2, i3);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_micGetAvail() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_MicGetAvail(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_readMic(byte[] bArr) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_MicRead(this.callingApp, bArr, -1);
            } catch (RemoteException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int XMA_micDestroy() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_MicDestroy(this.callingApp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_atlSwitch(boolean z) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_atlSwitch(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int XMA_aiWakeUp() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_aiWakeUp();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean XMA_hasAtl() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_hasAtl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void XMA_setAtlEnable(boolean z) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                iKaraoke.XMS_setAtlEnable(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean XMA_isAtlEnabled() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_isAtlEnabled();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String XMA_getMicName() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_getMicName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String XMA_getBuyMicUrl() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                return iKaraoke.XMS_getBuyMicUrl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "https://www.xiaopeng.com/";
    }

    public void XMA_connectMicFlow() {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                iKaraoke.XMS_connectMicFlow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void XMA_ShowToast(String str) {
        IKaraoke iKaraoke = this.mService;
        if (iKaraoke != null) {
            try {
                iKaraoke.XMS_ShowToast(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

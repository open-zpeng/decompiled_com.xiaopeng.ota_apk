package com.xiaopeng.xuimanager.musicrecognize;

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
import com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize;
import com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
@SystemApi
/* loaded from: classes2.dex */
public class MusicRecognizeManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MODE_HUMMING = 2;
    private static final int MODE_ORIGINAL_SOUND = 1;
    private static final int MSG_MUSICRECOGNIZE_ERROR_EVENT = 0;
    private static final int MSG_MUSICRECOGNIZE_FINDCOVER_EVENT = 1;
    private static final int MSG_MUSICRECOGNIZE_RECOGNIZE_EVENT = 2;
    public static final String TAG = "MusicRecognizeManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IMusicRecognize mService;
    private final ArraySet<MusicRecognizeEventListener> mListeners = new ArraySet<>();
    private MusicRecognizeEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface MusicRecognizeEventListener {
        void onErrorEvent(int i, int i2);

        void onFindCoverEvent(String str);

        void onRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<MusicRecognizeManager> mMgr;

        EventCallbackHandler(MusicRecognizeManager musicRecognizeManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(musicRecognizeManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MusicRecognizeManager musicRecognizeManager = this.mMgr.get();
            int i = message.what;
            if (i == 0) {
                if (musicRecognizeManager != null) {
                    musicRecognizeManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                }
            } else if (i == 1) {
                if (musicRecognizeManager != null) {
                    musicRecognizeManager.dispatchFindCoverEventToClient((String) message.obj);
                }
            } else if (i == 2) {
                if (musicRecognizeManager != null) {
                    musicRecognizeManager.dispatchRecognizeEventToClient((MusicRecognizeEvent) message.obj);
                }
            } else {
                Log.e(MusicRecognizeManager.TAG, "Event type not handled?" + message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MusicRecognizeEventListenerToService extends IMusicRecognizeEventListener.Stub {
        private final WeakReference<MusicRecognizeManager> mManager;

        public MusicRecognizeEventListenerToService(MusicRecognizeManager musicRecognizeManager) {
            this.mManager = new WeakReference<>(musicRecognizeManager);
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onFindCoverEvent(String str) {
            MusicRecognizeManager musicRecognizeManager = this.mManager.get();
            if (musicRecognizeManager != null) {
                musicRecognizeManager.handleFindCoverEvent(str);
            }
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent) {
            MusicRecognizeManager musicRecognizeManager = this.mManager.get();
            if (musicRecognizeManager != null) {
                musicRecognizeManager.handleRecognizeEvent(musicRecognizeEvent);
            }
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onError(int i, int i2) {
            MusicRecognizeManager musicRecognizeManager = this.mManager.get();
            if (musicRecognizeManager != null) {
                musicRecognizeManager.handleErrorEvent(i, i2);
            }
        }
    }

    public MusicRecognizeManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IMusicRecognize.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(MusicRecognizeEventListener musicRecognizeEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new MusicRecognizeEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(musicRecognizeEventListener);
    }

    public synchronized void unregisterListener(MusicRecognizeEventListener musicRecognizeEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mListeners.remove(musicRecognizeEventListener);
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
    public void dispatchFindCoverEventToClient(String str) {
        ArraySet<MusicRecognizeEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (MusicRecognizeEventListener musicRecognizeEventListener : arraySet) {
                musicRecognizeEventListener.onFindCoverEvent(str);
            }
            return;
        }
        Log.e(TAG, "Listener died, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRecognizeEventToClient(MusicRecognizeEvent musicRecognizeEvent) {
        ArraySet<MusicRecognizeEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (MusicRecognizeEventListener musicRecognizeEventListener : arraySet) {
                musicRecognizeEventListener.onRecognizeEvent(musicRecognizeEvent);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<MusicRecognizeEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (MusicRecognizeEventListener musicRecognizeEventListener : arraySet) {
                musicRecognizeEventListener.onErrorEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFindCoverEvent(String str) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, str));
    }

    public void handleRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(2, musicRecognizeEvent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleErrorEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<MusicRecognizeEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IMusicRecognize.Stub.asInterface(iBinder);
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

    public void start() throws XUIServiceNotConnectedException {
        Log.d(TAG, "start()");
        try {
            this.mService.start();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not start: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stop() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stop()");
        try {
            this.mService.stop();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stop: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void stopAndRecognize() throws XUIServiceNotConnectedException {
        Log.d(TAG, "stopAndRecognize()");
        try {
            this.mService.stopAndRecognize();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not stopAndRecognize: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMode(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMode()");
        try {
            this.mService.setMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int getMode() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getMode()");
        try {
            return this.mService.getMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getMode: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void setMinScore(float f) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setMinScore()");
        try {
            this.mService.setMinScore(f);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setMinScore: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public float getMinScore() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getMinScore()");
        try {
            return this.mService.getMinScore();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getMinScore: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void findSongCover(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getMinScore()");
        try {
            this.mService.findSongCover(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getMinScore: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }
}

package com.xiaopeng.xuimanager.awareness;

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
import com.xiaopeng.xuimanager.awareness.IAwareness;
import com.xiaopeng.xuimanager.awareness.IAwarenessEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
@SystemApi
/* loaded from: classes2.dex */
public class AwarenessManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MSG_AWARENESS_ERROR_EVENT = 0;
    public static final String TAG = "AwarenessManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IAwareness mService;
    private final ArraySet<AwarenessEventListener> mListeners = new ArraySet<>();
    private AwarenessEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface AwarenessEventListener {
        void onErrorEvent(int i, int i2);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<AwarenessManager> mMgr;

        EventCallbackHandler(AwarenessManager awarenessManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(awarenessManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                AwarenessManager awarenessManager = this.mMgr.get();
                if (awarenessManager != null) {
                    awarenessManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                    return;
                }
                return;
            }
            Log.e(AwarenessManager.TAG, "Event type not handled?" + message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AwarenessEventListenerToService extends IAwarenessEventListener.Stub {
        private final WeakReference<AwarenessManager> mManager;

        public AwarenessEventListenerToService(AwarenessManager awarenessManager) {
            this.mManager = new WeakReference<>(awarenessManager);
        }

        @Override // com.xiaopeng.xuimanager.awareness.IAwarenessEventListener
        public void onError(int i, int i2) {
            AwarenessManager awarenessManager = this.mManager.get();
            if (awarenessManager != null) {
                awarenessManager.handleErrorEvent(i, i2);
            }
        }
    }

    public AwarenessManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IAwareness.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(AwarenessEventListener awarenessEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new AwarenessEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(awarenessEventListener);
    }

    public synchronized void unregisterListener(AwarenessEventListener awarenessEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mListeners.remove(awarenessEventListener);
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
        ArraySet<AwarenessEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (AwarenessEventListener awarenessEventListener : arraySet) {
                awarenessEventListener.onErrorEvent(i, i2);
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

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<AwarenessEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IAwareness.Stub.asInterface(iBinder);
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
}

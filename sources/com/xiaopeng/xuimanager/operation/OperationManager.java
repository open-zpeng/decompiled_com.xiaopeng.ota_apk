package com.xiaopeng.xuimanager.operation;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.operation.IOperation;
import com.xiaopeng.xuimanager.operation.IOperationEventListener;
import java.lang.ref.WeakReference;
@SystemApi
/* loaded from: classes2.dex */
public class OperationManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MSG_OPERATION_ERROR_EVENT = 0;
    private static final int MSG_OPERATION_EVENT = 1;
    public static final String TAG = "OperationManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IOperation mService;
    private final ArraySet<OperationEventListener> mAllListeners = new ArraySet<>();
    private OperationEventListenerToService mListenerToService = null;

    /* loaded from: classes2.dex */
    public interface OperationEventListener {
        void onErrorEvent(int i, int i2);

        void onEvent(int i, String str, int i2, String str2);
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
    }

    public OperationManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IOperation.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IOperation.Stub.asInterface(iBinder);
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

    public synchronized void registerListener(OperationEventListener operationEventListener) throws XUIServiceNotConnectedException {
        if (this.mAllListeners.isEmpty()) {
            try {
                this.mListenerToService = new OperationEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (Exception e) {
                Log.e(TAG, "registerListener: " + e.toString());
            }
        }
        this.mAllListeners.add(operationEventListener);
    }

    public synchronized void unregisterListener(OperationEventListener operationEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener(ContextInfoEventListener listener)");
        this.mAllListeners.remove(operationEventListener);
        if (this.mAllListeners.isEmpty() && this.mListenerToService != null) {
            try {
                this.mService.unregisterListener(this.mListenerToService);
            } catch (Exception e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
            }
            this.mListenerToService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<OperationEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mAllListeners;
        }
        if (!arraySet.isEmpty()) {
            for (OperationEventListener operationEventListener : arraySet) {
                operationEventListener.onErrorEvent(i, i2);
            }
            return;
        }
        Log.e(TAG, "Listener is null, not dispatching event.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEventToClient(int i, String str, int i2, String str2) {
        ArraySet<OperationEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mAllListeners;
        }
        if (!arraySet.isEmpty()) {
            for (OperationEventListener operationEventListener : arraySet) {
                operationEventListener.onEvent(i, str, i2, str2);
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
    public void handleEvent(int i, String str, int i2, String str2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        OperationDataClass operationDataClass = new OperationDataClass();
        operationDataClass.setCode(i);
        operationDataClass.setId(str);
        operationDataClass.setType(i2);
        operationDataClass.setObject(str2);
        obtainMessage.obj = operationDataClass;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class OperationDataClass {
        int code;
        String id;
        String mObject;
        int type;

        private OperationDataClass() {
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getId() {
            return this.id;
        }

        public void setObject(String str) {
            this.mObject = str;
        }

        public String getObject() {
            return this.mObject;
        }
    }

    public int addNewResource(String str, int i, String str2, String str3) throws XUIServiceNotConnectedException {
        Log.d(TAG, "addNewResource");
        try {
            return this.mService.addNewResource(str, i, str2, str3);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not addNewResource: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String getLocalResourceList(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getLocalResourceList");
        try {
            return this.mService.getLocalResourceList(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getLocalResourceList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public String getDownLoadResourceList(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getDownLoadResourceList");
        try {
            return this.mService.getDownLoadResourceList(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getDownLoadResourceList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean checkResourceExist(int i, String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "checkResourceExist");
        try {
            return this.mService.checkResourceExist(i, str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not checkResourceExist: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int deleteResource(String str, int i, String str2) throws XUIServiceNotConnectedException {
        Log.d(TAG, "deleteResource");
        try {
            return this.mService.deleteResource(str, i, str2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not deleteResource: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<OperationManager> mMgr;

        EventCallbackHandler(OperationManager operationManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(operationManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OperationManager operationManager = this.mMgr.get();
            int i = message.what;
            if (i == 0) {
                if (operationManager != null) {
                    operationManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                }
            } else if (i == 1) {
                OperationDataClass operationDataClass = (OperationDataClass) message.obj;
                if (operationManager != null) {
                    operationManager.dispatchEventToClient(operationDataClass.getCode(), operationDataClass.getId(), operationDataClass.getType(), operationDataClass.getObject());
                }
            } else {
                Log.e(OperationManager.TAG, "Event type not handled?" + message);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class OperationEventListenerToService extends IOperationEventListener.Stub {
        private final WeakReference<OperationManager> mManager;

        public OperationEventListenerToService(OperationManager operationManager) {
            this.mManager = new WeakReference<>(operationManager);
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
        public void onError(int i, int i2) {
            OperationManager operationManager = this.mManager.get();
            if (operationManager != null) {
                operationManager.handleErrorEvent(i, i2);
            }
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
        public void onEvent(int i, String str, int i2, String str2) {
            OperationManager operationManager = this.mManager.get();
            if (operationManager != null) {
                operationManager.handleEvent(i, str, i2, str2);
            }
        }
    }
}

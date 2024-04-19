package com.xiaopeng.xuimanager.download;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManager;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.download.IDownloadListenerInterface;
import com.xiaopeng.xuimanager.download.IDownloadServiceInterface;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
@SystemApi
/* loaded from: classes2.dex */
public class XPDownloadManager implements XUIManagerBase {
    private static final String TAG = "XPDownloadManager";
    public static final int TYPE_CANCEL = 5;
    public static final int TYPE_COMPLETED = 3;
    public static final int TYPE_ERROR = 6;
    public static final int TYPE_IN_PROGRESS = 2;
    public static final int TYPE_PAUSE = 4;
    public static final int TYPE_START = 1;
    private static String mServiceName;
    private IDownloadServiceInterface mXpDownloadService;
    private IDownloadListenerInterface mIDownloadListenerToService = null;
    private ArraySet<DownloadListener> mDownloadListenerArraySet = new ArraySet<>();

    /* loaded from: classes2.dex */
    public interface DownloadListener {
        void onDownloadCancel(long j, String str);

        void onDownloadCompleted(long j, String str, String str2);

        void onDownloadError(long j, String str, String str2);

        void onDownloadInProgress(long j, String str, long j2, float f, long j3);

        void onDownloadPause(long j, String str);

        void onDownloadStart(long j, String str);

        void onRegisterError(DownloadListener downloadListener, String str);

        void onRegisterSuccess(DownloadListener downloadListener);

        void onUnRegisterError(DownloadListener downloadListener, String str);

        void onUnRegisterSuccess(DownloadListener downloadListener);
    }

    public XPDownloadManager(IBinder iBinder, Context context, Handler handler) {
        this.mXpDownloadService = null;
        this.mXpDownloadService = IDownloadServiceInterface.Stub.asInterface(iBinder);
    }

    public synchronized long enqueue(String str) throws XUIServiceNotConnectedException {
        try {
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
        return this.mXpDownloadService.enqueue(str);
    }

    public synchronized long enqueueWtihTitleDescription(String str, String str2, String str3, Map map) throws XUIServiceNotConnectedException {
        try {
        } catch (RemoteException e) {
            String str4 = TAG;
            Log.e(str4, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
        return this.mXpDownloadService.enqueueWithHeader(str, str2, str3, map);
    }

    public synchronized ArrayList<Integer> getStatusListByPackageName(String str, String str2) throws XUIServiceNotConnectedException {
        ArrayList<Integer> arrayList;
        try {
            int[] statusListByPackageName = this.mXpDownloadService.getStatusListByPackageName(str, str2);
            arrayList = new ArrayList<>(statusListByPackageName.length);
            for (int i : statusListByPackageName) {
                arrayList.add(Integer.valueOf(i));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
        return arrayList;
    }

    public synchronized long enqueueWithHeader(String str, Map map) throws XUIServiceNotConnectedException {
        try {
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
        return this.mXpDownloadService.enqueueWithHeader(str, null, null, map);
    }

    public synchronized String getState(long j, String str) throws XUIServiceNotConnectedException {
        try {
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
        return this.mXpDownloadService.getState(j, str);
    }

    public synchronized boolean registerListener(DownloadListener downloadListener) throws XUIServiceNotConnectedException {
        if (this.mDownloadListenerArraySet.isEmpty()) {
            try {
                this.mIDownloadListenerToService = new DownloadListenerInterfaceToService(this);
                this.mXpDownloadService.registerListener(this.mIDownloadListenerToService);
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        return this.mDownloadListenerArraySet.add(downloadListener);
    }

    public synchronized void unregisterListener(DownloadListener downloadListener) throws XUIServiceNotConnectedException {
        this.mDownloadListenerArraySet.remove(downloadListener);
        if (this.mDownloadListenerArraySet.isEmpty()) {
            try {
                this.mXpDownloadService.unRegisterListener(this.mIDownloadListenerToService);
                this.mIDownloadListenerToService = null;
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<DownloadListener> it = this.mDownloadListenerArraySet.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mXpDownloadService = IDownloadServiceInterface.Stub.asInterface(iBinder);
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dispatchDownloadCallbackToClientByType(int i, long j, String str, long j2, float f, long j3, String str2, String str3) {
        if (!this.mDownloadListenerArraySet.isEmpty()) {
            try {
                Iterator<DownloadListener> it = this.mDownloadListenerArraySet.iterator();
                while (it.hasNext()) {
                    DownloadListener next = it.next();
                    switch (i) {
                        case 1:
                            String str4 = TAG;
                            Log.d(str4, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadStart start");
                            next.onDownloadStart(j, str);
                            String str5 = TAG;
                            Log.d(str5, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadStart end");
                            break;
                        case 2:
                            String str6 = TAG;
                            Log.d(str6, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadInProgress start");
                            next.onDownloadInProgress(j, str, j2, f, j3);
                            String str7 = TAG;
                            Log.d(str7, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadInProgress end");
                            break;
                        case 3:
                            String str8 = TAG;
                            Log.d(str8, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCompleted start");
                            next.onDownloadCompleted(j, str, str2);
                            String str9 = TAG;
                            Log.d(str9, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCompleted end");
                            break;
                        case 4:
                            String str10 = TAG;
                            Log.d(str10, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCancel start");
                            next.onDownloadPause(j, str);
                            String str11 = TAG;
                            Log.d(str11, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCancel end");
                            break;
                        case 5:
                            String str12 = TAG;
                            Log.d(str12, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCancel start");
                            next.onDownloadCancel(j, str);
                            String str13 = TAG;
                            Log.d(str13, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadCancel end");
                            break;
                        case 6:
                            String str14 = TAG;
                            Log.d(str14, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadError start");
                            next.onDownloadError(j, str, str3);
                            String str15 = TAG;
                            Log.d(str15, "dispatchDownloadCallbackToClientByType listener is " + next + " onDownloadError end");
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Log.d(TAG, "dispatchDownloadCallbackByType listeners is null");
        }
        return true;
    }

    /* loaded from: classes2.dex */
    private static class DownloadListenerInterfaceToService extends IDownloadListenerInterface.Stub {
        private final WeakReference<XPDownloadManager> mManager;

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        public DownloadListenerInterfaceToService(XPDownloadManager xPDownloadManager) {
            this.mManager = new WeakReference<>(xPDownloadManager);
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
            Log.d(XPDownloadManager.TAG, "mIDownloadListener onRegisterSuccess ");
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
            Log.d(XPDownloadManager.TAG, "mIDownloadListener onRegisterError ");
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onUnRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
            Log.d(XPDownloadManager.TAG, "mIDownloadListener onUnRegisterSuccess ");
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onUnRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
            Log.d(XPDownloadManager.TAG, "mIDownloadListener onUnRegisterError ");
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadStart(long j, String str) throws RemoteException {
            String str2 = XPDownloadManager.TAG;
            Log.d(str2, "mIDownloadListener onDownloadStart task id  = " + j);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(1, j, str, 0L, 0.0f, 0L, "", "");
            }
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadInProgress(long j, String str, long j2, float f, long j3) throws RemoteException {
            String str2 = XPDownloadManager.TAG;
            Log.d(str2, "mIDownloadListener onDownloadInProgress task id  = " + j + "   percentage = " + f + "   byteTillNow = " + j3);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(2, j, str, j2, f, j3, "", "");
            }
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadPause(long j, String str) throws RemoteException {
            String str2 = XPDownloadManager.TAG;
            Log.d(str2, "mIDownloadListener onDownloadStart task id  = " + j);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(4, j, str, 0L, 0.0f, 0L, "", "");
            }
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadCancel(long j, String str) throws RemoteException {
            String str2 = XPDownloadManager.TAG;
            Log.d(str2, "mIDownloadListener onDownloadStart task id  = " + j);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(5, j, str, 0L, 0.0f, 0L, "", "");
            }
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadError(long j, String str, String str2) throws RemoteException {
            String str3 = XPDownloadManager.TAG;
            Log.d(str3, "mIDownloadListener onDownloadError task id  = " + j + " errorMessage = " + str2);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(6, j, str, 0L, 0.0f, 0L, "", str2);
            }
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadCompleted(long j, String str, String str2) throws RemoteException {
            String str3 = XPDownloadManager.TAG;
            Log.d(str3, "mIDownloadListener onDownloadCompleted task id  = " + j + " fileUri = " + str2);
            XPDownloadManager xPDownloadManager = this.mManager.get();
            if (xPDownloadManager != null) {
                xPDownloadManager.dispatchDownloadCallbackToClientByType(3, j, str, 0L, 0.0f, 0L, str2, "");
            }
        }
    }
}

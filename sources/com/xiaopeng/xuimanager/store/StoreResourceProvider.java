package com.xiaopeng.xuimanager.store;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.store.IResourceService;
import com.xiaopeng.xuimanager.store.bean.ResourceBean;
import com.xiaopeng.xuimanager.store.bean.ResourceContainerBean;
import com.xiaopeng.xuimanager.store.bean.ResourceDownloadInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class StoreResourceProvider implements XUIManagerBase {
    public static final long INVALID_DOWNLOAD_BYTES = -1;
    public static final long INVALID_DOWNLOAD_ID = -1;
    public static final int INVALID_DOWNLOAD_STATUS = -1;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    private static final String STORE_RESOURCE_MANAGER_SERVICE = "com.xiaopeng.appstore";
    public static final String STORE_RESOURCE_MANAGER_SERVICE_INTERFACE_NAME = "com.xiaopeng.appstore.resourceservice.ResourceService";
    private static final String TAG = "StoreResourceProvider";
    private static volatile StoreResourceProvider sResourceManager;
    private int mConnectionState;
    private final Context mContext;
    private IResourceService mService;
    private ServiceConnection mServiceConnectionListenerClient;
    private final Object mResourceServiceReady = new Object();
    private final ServiceConnection mServiceConnectionListener = new ServiceConnection() { // from class: com.xiaopeng.xuimanager.store.StoreResourceProvider.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(StoreResourceProvider.TAG, "service connected, Service.  1");
            Log.d(StoreResourceProvider.TAG, "service connected, Service.  2");
            StoreResourceProvider.this.mService = IResourceService.Stub.asInterface(iBinder);
            synchronized (StoreResourceProvider.this.mResourceServiceReady) {
                Log.d(StoreResourceProvider.TAG, "service connected, Service.  3");
                StoreResourceProvider.this.mConnectionState = 2;
                StoreResourceProvider.this.mResourceServiceReady.notifyAll();
                try {
                    StoreResourceProvider.this.mService.registerDownloadListener(StoreResourceProvider.this.mIRMDownloadCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String str = StoreResourceProvider.TAG;
                Log.d(str, "service connected, Service = " + StoreResourceProvider.this.mService + " state = " + StoreResourceProvider.this.mConnectionState);
                if (StoreResourceProvider.this.mServiceConnectionListenerClient != null) {
                    StoreResourceProvider.this.mServiceConnectionListenerClient.onServiceConnected(componentName, iBinder);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            String str = StoreResourceProvider.TAG;
            Log.d(str, "onServiceDisconnected, " + componentName);
            synchronized (StoreResourceProvider.this) {
                StoreResourceProvider.this.mService = null;
                if (StoreResourceProvider.this.mConnectionState == 0) {
                    Log.w(StoreResourceProvider.TAG, "onServiceDisconnected, already disconnected.");
                    return;
                }
                StoreResourceProvider.this.mConnectionState = 0;
                if (StoreResourceProvider.this.mServiceConnectionListenerClient != null) {
                    StoreResourceProvider.this.mServiceConnectionListenerClient.onServiceDisconnected(componentName);
                }
            }
        }
    };
    private IRMDownloadCallback mIRMDownloadCallback = new RMDownloadCallbackListenerToService(this);
    private List<RMDownloadListener> mClientRMDownloadListeners = new ArrayList();

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
    }

    public StoreResourceProvider(Context context) {
        this.mContext = context;
    }

    boolean isResourceServiceConnected() {
        synchronized (this.mResourceServiceReady) {
            while (this.mConnectionState != 2) {
                try {
                    String str = TAG;
                    Log.d(str, "Waiting Resource service connected, mConnectionState = " + this.mConnectionState);
                    startResourceManagerService();
                    this.mResourceServiceReady.wait();
                } catch (InterruptedException e) {
                    String str2 = TAG;
                    Log.d(str2, "Waiting Resource service connected, InterruptedException " + e);
                }
            }
        }
        return true;
    }

    public void setServiceConnectionListenerClient(ServiceConnection serviceConnection) {
        this.mServiceConnectionListenerClient = serviceConnection;
    }

    private void startResourceManagerService() {
        Intent intent = new Intent();
        intent.setClassName("com.xiaopeng.appstore", STORE_RESOURCE_MANAGER_SERVICE_INTERFACE_NAME);
        this.mContext.bindService(intent, this.mServiceConnectionListener, 1);
    }

    public ResourceContainerBean queryResourceData(String str) {
        try {
            isResourceServiceConnected();
            return this.mService.queryResourceData(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ResourceDownloadInfo> queryDownloadInfo(String[] strArr) {
        try {
            isResourceServiceConnected();
            return this.mService.queryDownloadInfo(strArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registerDownloadListener(RMDownloadListener rMDownloadListener) {
        if (rMDownloadListener != null && !this.mClientRMDownloadListeners.contains(rMDownloadListener)) {
            this.mClientRMDownloadListeners.add(rMDownloadListener);
            return;
        }
        String str = TAG;
        Log.i(str, "registerDownloadListener, ignore this listener:" + rMDownloadListener);
    }

    public void unregisterDownloadListener(RMDownloadListener rMDownloadListener) {
        if (rMDownloadListener != null) {
            this.mClientRMDownloadListeners.remove(rMDownloadListener);
        }
    }

    public boolean start(ResourceBean resourceBean) {
        String str = TAG;
        Log.d(str, "start resourceBean:" + resourceBean);
        try {
            isResourceServiceConnected();
            return this.mService.start(resourceBean);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public boolean resume(String str) {
        String str2 = TAG;
        Log.d(str2, "resume resourceId：" + str);
        try {
            isResourceServiceConnected();
            return this.mService.resume(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public boolean remove(String str) {
        String str2 = TAG;
        Log.d(str2, "remove resourceId：" + str);
        try {
            isResourceServiceConnected();
            return this.mService.remove(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public boolean pause(String str) {
        String str2 = TAG;
        Log.d(str2, "pause resourceId：" + str);
        try {
            isResourceServiceConnected();
            return this.mService.pause(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resumeResDownload(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "resumeResDownload resType:" + str + ", resourceId：" + str2);
        try {
            isResourceServiceConnected();
            return this.mService.resumeResDownload(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelResDownload(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "cancelResDownload resType:" + str + ", resourceId：" + str2);
        try {
            isResourceServiceConnected();
            return this.mService.cancelResDownload(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pauseResDownload(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "pauseResDownload resType:" + str + ", resourceId：" + str2);
        try {
            isResourceServiceConnected();
            return this.mService.pauseResDownload(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resumeDownload(String str) {
        String str2 = TAG;
        Log.d(str2, "resumeDownload url:" + str);
        try {
            isResourceServiceConnected();
            return this.mService.resumeDownload(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelDownload(String str) {
        String str2 = TAG;
        Log.d(str2, "cancelDownload url:" + str);
        try {
            isResourceServiceConnected();
            return this.mService.cancelDownload(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pauseDownload(String str) {
        String str2 = TAG;
        Log.d(str2, "pauseDownload url:" + str);
        try {
            isResourceServiceConnected();
            return this.mService.pauseDownload(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long enqueue(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "enqueue url : " + str + "  title = " + str2);
        try {
            isResourceServiceConnected();
            return this.mService.enqueue(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public List<ResourceDownloadInfo> queryAllInfo() {
        List<ResourceDownloadInfo> list;
        Log.d(TAG, "queryAllInfo");
        try {
            isResourceServiceConnected();
            list = this.mService.queryAllInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
            list = null;
        }
        String str = TAG;
        Log.d(str, "queryAllInfo " + list);
        return list;
    }

    public int fetchDownloadStatus(long j) {
        String str = TAG;
        Log.d(str, "fetchDownloadStatusById id : " + j);
        try {
            isResourceServiceConnected();
            return this.mService.fetchDownloadStatusById(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int fetchDownloadStatus(String str) {
        String str2 = TAG;
        Log.d(str2, "fetchDownloadStatusByUrl url : " + str);
        try {
            isResourceServiceConnected();
            return this.mService.fetchDownloadStatusByUrl(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDownloadStatus(String str) {
        String str2 = TAG;
        Log.d(str2, "getDownloadStatusByUrl url : " + str);
        try {
            isResourceServiceConnected();
            return this.mService.getDownloadStatusByUrl(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDownloadStatus(long j) {
        String str = TAG;
        Log.d(str, "getDownloadStatusById id : " + j);
        try {
            isResourceServiceConnected();
            return this.mService.getDownloadStatusById(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getTotalBytes(long j) {
        String str = TAG;
        Log.d(str, "getTotalBytesById id : " + j);
        try {
            isResourceServiceConnected();
            return this.mService.getTotalBytesById(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public long getTotalBytes(String str) {
        String str2 = TAG;
        Log.d(str2, "getDownloadStatusById url : " + str);
        try {
            isResourceServiceConnected();
            return this.mService.getTotalBytesByUrl(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public long getDownloadedBytes(long j) {
        String str = TAG;
        Log.d(str, "getDownloadedBytesById id : " + j);
        try {
            isResourceServiceConnected();
            return this.mService.getDownloadedBytesById(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public long getDownloadedBytes(String str) {
        String str2 = TAG;
        Log.d(str2, "getDownloadedBytesByUrl url : " + str);
        try {
            isResourceServiceConnected();
            return this.mService.getDownloadedBytesByUrl(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public void removeLocalData(long j) {
        String str = TAG;
        Log.d(str, "removeLocalData id : " + j);
        try {
            isResourceServiceConnected();
            this.mService.removeLocalDataById(j);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void removeLocalData(String str) {
        String str2 = TAG;
        Log.d(str2, "removeLocalData url : " + str);
        try {
            isResourceServiceConnected();
            this.mService.removeLocalDataByUrl(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public File getLocalFile(String str) {
        String str2 = TAG;
        Log.d(str2, "getLocalFileByUrl url : " + str);
        try {
            isResourceServiceConnected();
            String localFilePath = this.mService.getLocalFilePath(str);
            if (localFilePath != null) {
                return new File(localFilePath);
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void dispatchDownloadCallback(int i, ResourceDownloadInfo resourceDownloadInfo) {
        List<RMDownloadListener> list = this.mClientRMDownloadListeners;
        if (list != null) {
            for (RMDownloadListener rMDownloadListener : list) {
                rMDownloadListener.onDownloadCallback(i, resourceDownloadInfo);
            }
        }
    }

    public void dispatchMenuOpenCallback(String str) {
        List<RMDownloadListener> list = this.mClientRMDownloadListeners;
        if (list != null) {
            for (RMDownloadListener rMDownloadListener : list) {
                rMDownloadListener.onMenuOpenCallback(str);
            }
        }
    }

    public void unbindService() {
        List<RMDownloadListener> list = this.mClientRMDownloadListeners;
        if (list != null) {
            for (RMDownloadListener rMDownloadListener : list) {
                rMDownloadListener.unbindService();
            }
        }
        Log.d(TAG, "unbindService, disconnect  automatically");
        disconnect();
    }

    public void connect() {
        synchronized (this) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            startResourceManagerService();
        }
    }

    public void disconnect() {
        synchronized (this) {
            if (this.mConnectionState == 0) {
                return;
            }
            try {
                this.mService.unregisterDownloadListener(this.mIRMDownloadCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            this.mService = null;
            this.mConnectionState = 0;
            this.mContext.unbindService(this.mServiceConnectionListener);
        }
    }

    public void releaseService() {
        if (this.mService != null) {
            isResourceServiceConnected();
            try {
                this.mService.unregisterDownloadListener(this.mIRMDownloadCallback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.w(TAG, "releaseService, service is null.");
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.mService != null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }
}

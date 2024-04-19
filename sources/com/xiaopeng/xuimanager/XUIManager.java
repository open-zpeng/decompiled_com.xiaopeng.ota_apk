package com.xiaopeng.xuimanager;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.xiaopeng.xuimanager.IXUIService;
import com.xiaopeng.xuimanager.ambientlight.AmbientLightManager;
import com.xiaopeng.xuimanager.awareness.AwarenessManager;
import com.xiaopeng.xuimanager.contextinfo.ContextInfoManager;
import com.xiaopeng.xuimanager.download.XPDownloadManager;
import com.xiaopeng.xuimanager.iot.IoTManager;
import com.xiaopeng.xuimanager.karaoke.KaraokeManager;
import com.xiaopeng.xuimanager.mediacenter.MediaCenterManager;
import com.xiaopeng.xuimanager.musicrecognize.MusicRecognizeManager;
import com.xiaopeng.xuimanager.operation.OperationManager;
import com.xiaopeng.xuimanager.smart.SmartManager;
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
import com.xiaopeng.xuimanager.xapp.XAppManager;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class XUIManager {
    @SystemApi
    public static final String AMBIENTLIGHT_SERVICE = "ambientlight";
    @SystemApi
    public static final String AWARENESS_SERVICE = "awareness";
    @SystemApi
    public static final String CONTEXTINFO_SERVICE = "contextinfo";
    @SystemApi
    public static final String IOT_MANAGER = "iotmanager";
    @SystemApi
    public static final String KARAOKE_SERVICE = "karaoke";
    @SystemApi
    public static final String MEDIACENTER_SERVICE = "mediacenter";
    @SystemApi
    public static final String MUSICRECOGNIZE_SERVICE = "musicrecognize";
    @SystemApi
    public static final String OPERATION_SERVICE = "operation";
    public static final String PERMISSION_AMBIENTLIGHT = "com.xiaopeng.xuimanager.permission.XUI_AMBIENTLIGHT";
    public static final String PERMISSION_AWARENESS = "com.xiaopeng.xuimanager.permission.XUI_AWARENESS";
    public static final String PERMISSION_CONTEXTINFO = "com.xiaopeng.xuimanager.permission.XUI_CONTEXTINFO";
    public static final String PERMISSION_KARAOKE = "com.xiaopeng.xuimanager.permission.XUI_KARAOKE";
    public static final String PERMISSION_LANGLIGHT = "com.xiaopeng.xuimanager.permission.XUI_LANGLIGHT";
    public static final String PERMISSION_MEDIACENTER = "com.xiaopeng.xuimanager.permission.XUI_MEDIACENTER";
    public static final String PERMISSION_MUSICRECOGNIZE = "com.xiaopeng.xuimanager.permission.XUI_MUSICRECOGNIZE";
    public static final String PERMISSION_OPERATION = "com.xiaopeng.xuimanager.permission.XUI_OPERATION";
    public static final String PERMISSION_SMART = "com.xiaopeng.xuimanager.permission.XUI_SMART";
    public static final String PERMISSION_XAPP = "com.xiaopeng.xuimanager.permission.XUI_XAPP";
    @SystemApi
    public static final String RESOURCE_PROVIDER = "resource_provider";
    @SystemApi
    public static final String SMART_SERVICE = "smart";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    @SystemApi
    public static final String USER_SCENARIO_SERVICE = "userscenario";
    @SystemApi
    public static final String XAPP_SERVICE = "xapp";
    @SystemApi
    public static final String XPDOWNLOAD_SERVICE = "xpdownload";
    @SystemApi
    public static final String XUI_MANAGER_SERVICE = "XuiServiceManager";
    private static final long XUI_SERVICE_BIND_MAX_RETRY = 20;
    private static final long XUI_SERVICE_BIND_RETRY_INTERVAL_MS = 500;
    private static final String XUI_SERVICE_CLASS = "com.xiaopeng.xuiservice.XUIService";
    public static final String XUI_SERVICE_INTERFACE_NAME = "com.xiaopeng.xuimanager.IXUIService";
    public static final String XUI_SERVICE_NOT_CONNECTED_EXCEPTION_MSG = "XUIServiceNotConnected";
    private static final String XUI_SERVICE_PACKAGE = "com.xiaopeng.xuiservice";
    private static HandlerThread handlerThread;
    @GuardedBy({"this"})
    private int mConnectionRetryCount;
    private final Runnable mConnectionRetryFailedRunnable;
    private final Runnable mConnectionRetryRunnable;
    @GuardedBy({"this"})
    private int mConnectionState;
    private final Context mContext;
    private final Handler mEventHandler;
    private final Handler mMainThreadEventHandler;
    @GuardedBy({"this"})
    private IXUIService mService;
    private final ServiceConnection mServiceConnectionListener;
    private final ServiceConnection mServiceConnectionListenerClient;
    @GuardedBy({"mXUIManagerLock"})
    private final HashMap<String, XUIManagerBase> mServiceMap;
    private final Object mXUIManagerLock;

    private XUIManager(Context context, ServiceConnection serviceConnection, Handler handler) {
        this.mXUIManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mServiceConnectionListener = new ServiceConnection() { // from class: com.xiaopeng.xuimanager.XUIManager.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (XUIManager.this) {
                    XUIManager.this.mService = IXUIService.Stub.asInterface(iBinder);
                    XUIManager.this.mConnectionState = 2;
                }
                XUIManager.this.bringUpXUIManagers();
                if (XUIManager.this.mServiceConnectionListenerClient != null) {
                    XUIManager.this.mServiceConnectionListenerClient.onServiceConnected(componentName, iBinder);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (XUIManager.this) {
                    XUIManager.this.mService = null;
                    if (XUIManager.this.mConnectionState == 0) {
                        return;
                    }
                    XUIManager.this.mConnectionState = 0;
                    XUIManager.this.notifyServerDisconnectForXUIManagers();
                    if (XUIManager.this.mServiceConnectionListenerClient != null) {
                        XUIManager.this.mServiceConnectionListenerClient.onServiceDisconnected(componentName);
                    }
                }
            }
        };
        this.mConnectionRetryFailedRunnable = new Runnable() { // from class: com.xiaopeng.xuimanager.XUIManager.2
            @Override // java.lang.Runnable
            public void run() {
                XUIManager.this.mServiceConnectionListener.onServiceDisconnected(new ComponentName("com.xiaopeng.xuiservice", XUIManager.XUI_SERVICE_CLASS));
            }
        };
        this.mConnectionRetryRunnable = new Runnable() { // from class: com.xiaopeng.xuimanager.XUIManager.3
            @Override // java.lang.Runnable
            public void run() {
                XUIManager.this.startXUIService();
            }
        };
        this.mContext = context;
        this.mServiceConnectionListenerClient = serviceConnection;
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
    }

    public XUIManager(Context context, IXUIService iXUIService, Handler handler) {
        this.mXUIManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mServiceConnectionListener = new ServiceConnection() { // from class: com.xiaopeng.xuimanager.XUIManager.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (XUIManager.this) {
                    XUIManager.this.mService = IXUIService.Stub.asInterface(iBinder);
                    XUIManager.this.mConnectionState = 2;
                }
                XUIManager.this.bringUpXUIManagers();
                if (XUIManager.this.mServiceConnectionListenerClient != null) {
                    XUIManager.this.mServiceConnectionListenerClient.onServiceConnected(componentName, iBinder);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (XUIManager.this) {
                    XUIManager.this.mService = null;
                    if (XUIManager.this.mConnectionState == 0) {
                        return;
                    }
                    XUIManager.this.mConnectionState = 0;
                    XUIManager.this.notifyServerDisconnectForXUIManagers();
                    if (XUIManager.this.mServiceConnectionListenerClient != null) {
                        XUIManager.this.mServiceConnectionListenerClient.onServiceDisconnected(componentName);
                    }
                }
            }
        };
        this.mConnectionRetryFailedRunnable = new Runnable() { // from class: com.xiaopeng.xuimanager.XUIManager.2
            @Override // java.lang.Runnable
            public void run() {
                XUIManager.this.mServiceConnectionListener.onServiceDisconnected(new ComponentName("com.xiaopeng.xuiservice", XUIManager.XUI_SERVICE_CLASS));
            }
        };
        this.mConnectionRetryRunnable = new Runnable() { // from class: com.xiaopeng.xuimanager.XUIManager.3
            @Override // java.lang.Runnable
            public void run() {
                XUIManager.this.startXUIService();
            }
        };
        this.mContext = context;
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
        this.mService = iXUIService;
        this.mConnectionState = 2;
        this.mServiceConnectionListenerClient = null;
    }

    public static XUIManager createXUIManager(Context context, ServiceConnection serviceConnection, Handler handler) {
        if (!context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            Log.e(XUILog.TAG_XUIMANAGER, "FEATURE_AUTOMOTIVE not declared while android.car is used");
            return null;
        }
        try {
            return new XUIManager(context, serviceConnection, handler);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static XUIManager createXUIManager(Context context, ServiceConnection serviceConnection) {
        return createXUIManager(context, serviceConnection, null);
    }

    private static Handler determineMainThreadEventHandler(Handler handler) {
        Looper mainLooper = Looper.getMainLooper();
        return handler.getLooper() == mainLooper ? handler : new Handler(mainLooper);
    }

    private static Handler determineEventHandler(Handler handler) {
        return handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }

    public static void checkXUIServiceNotConnectedExceptionFromXUIService(IllegalStateException illegalStateException) throws XUIServiceNotConnectedException, IllegalStateException {
        if (illegalStateException.getMessage().equals(XUI_SERVICE_NOT_CONNECTED_EXCEPTION_MSG)) {
            throw new XUIServiceNotConnectedException();
        }
        throw illegalStateException;
    }

    public void connect() throws IllegalStateException {
        synchronized (this) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            startXUIService();
        }
    }

    public void disconnect() {
        synchronized (this) {
            if (this.mConnectionState == 0) {
                return;
            }
            this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
            this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
            this.mConnectionRetryCount = 0;
            tearDownXUIManagers();
            this.mService = null;
            this.mConnectionState = 0;
            this.mContext.unbindService(this.mServiceConnectionListener);
        }
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

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[Catch: RemoteException -> 0x009d, all -> 0x00a3, TRY_LEAVE, TryCatch #0 {, blocks: (B:13:0x001f, B:15:0x0029, B:17:0x002f, B:19:0x004d, B:20:0x0054, B:33:0x009e, B:27:0x0066, B:28:0x007c, B:30:0x007e, B:24:0x005f, B:34:0x00a1), top: B:53:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e A[Catch: RemoteException -> 0x009d, all -> 0x00a3, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:13:0x001f, B:15:0x0029, B:17:0x002f, B:19:0x004d, B:20:0x0054, B:33:0x009e, B:27:0x0066, B:28:0x007c, B:30:0x007e, B:24:0x005f, B:34:0x00a1), top: B:53:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getXUIServiceManager(java.lang.String r7) throws com.xiaopeng.xuimanager.XUIServiceNotConnectedException {
        /*
            r6 = this;
            int r0 = r7.hashCode()
            r1 = -1080235041(0xffffffffbf9cebdf, float:-1.2259482)
            if (r0 == r1) goto La
            goto L14
        La:
            java.lang.String r0 = "iotmanager"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L14
            r0 = 0
            goto L15
        L14:
            r0 = -1
        L15:
            r1 = 0
            if (r0 == 0) goto La6
            com.xiaopeng.xuimanager.IXUIService r0 = r6.getIXUIServiceOrThrow()
            java.lang.Object r2 = r6.mXUIManagerLock
            monitor-enter(r2)
            java.util.HashMap<java.lang.String, com.xiaopeng.xuimanager.XUIManagerBase> r3 = r6.mServiceMap     // Catch: java.lang.Throwable -> La3
            java.lang.Object r3 = r3.get(r7)     // Catch: java.lang.Throwable -> La3
            com.xiaopeng.xuimanager.XUIManagerBase r3 = (com.xiaopeng.xuimanager.XUIManagerBase) r3     // Catch: java.lang.Throwable -> La3
            if (r3 != 0) goto La1
            android.os.IBinder r0 = r0.getXUIService(r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            if (r0 != 0) goto L5f
            java.lang.String r0 = "XUIManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r4.<init>()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r5 = "getXUIServiceManager could not get binder for service:"
            r4.append(r5)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r4.append(r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r4 = r4.toString()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            android.util.Log.w(r0, r4)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r0 = "resource_provider"
            boolean r0 = r7.equals(r0)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            if (r0 == 0) goto L64
            com.xiaopeng.xuimanager.store.StoreResourceProvider r0 = new com.xiaopeng.xuimanager.store.StoreResourceProvider     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            android.content.Context r4 = r6.mContext     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r0.<init>(r4)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r3 = "XUIManager"
            java.lang.String r4 = "getXUIServiceManager get StoreResourceProvider"
            android.util.Log.i(r3, r4)     // Catch: android.os.RemoteException -> L5c java.lang.Throwable -> La3
            goto L63
        L5c:
            r7 = move-exception
            r3 = r0
            goto L9e
        L5f:
            com.xiaopeng.xuimanager.XUIManagerBase r0 = r6.createXUIServiceManager(r7, r0)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
        L63:
            r3 = r0
        L64:
            if (r3 != 0) goto L7e
            java.lang.String r0 = "XUIManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r4.<init>()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r5 = "getCarManager could not create manager for service:"
            r4.append(r5)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r4.append(r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r7 = r4.toString()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            android.util.Log.w(r0, r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            monitor-exit(r2)     // Catch: java.lang.Throwable -> La3
            return r1
        L7e:
            r3.setServiceName(r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r0 = "##xuimanager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r1.<init>()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r4 = "setname="
            r1.append(r4)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r1.append(r7)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.lang.String r1 = r1.toString()     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            android.util.Log.d(r0, r1)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            java.util.HashMap<java.lang.String, com.xiaopeng.xuimanager.XUIManagerBase> r0 = r6.mServiceMap     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            r0.put(r7, r3)     // Catch: android.os.RemoteException -> L9d java.lang.Throwable -> La3
            goto La1
        L9d:
            r7 = move-exception
        L9e:
            r6.handleRemoteException(r7)     // Catch: java.lang.Throwable -> La3
        La1:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> La3
            return r3
        La3:
            r7 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> La3
            throw r7
        La6:
            java.lang.Object r0 = r6.mXUIManagerLock
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, com.xiaopeng.xuimanager.XUIManagerBase> r2 = r6.mServiceMap     // Catch: java.lang.Throwable -> Lc6
            java.lang.Object r2 = r2.get(r7)     // Catch: java.lang.Throwable -> Lc6
            com.xiaopeng.xuimanager.XUIManagerBase r2 = (com.xiaopeng.xuimanager.XUIManagerBase) r2     // Catch: java.lang.Throwable -> Lc6
            if (r2 != 0) goto Lc4
            com.xiaopeng.xuimanager.XUIManagerBase r2 = r6.createXUIServiceManager(r7, r1)     // Catch: java.lang.Throwable -> Lc6
            if (r2 == 0) goto Lc2
            r2.setServiceName(r7)     // Catch: java.lang.Throwable -> Lc6
            java.util.HashMap<java.lang.String, com.xiaopeng.xuimanager.XUIManagerBase> r1 = r6.mServiceMap     // Catch: java.lang.Throwable -> Lc6
            r1.put(r7, r2)     // Catch: java.lang.Throwable -> Lc6
            goto Lc4
        Lc2:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc6
            return r1
        Lc4:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc6
            return r2
        Lc6:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc6
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.xuimanager.XUIManager.getXUIServiceManager(java.lang.String):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private XUIManagerBase createXUIServiceManager(String str, IBinder iBinder) throws XUIServiceNotConnectedException {
        char c;
        switch (str.hashCode()) {
            case -1608162213:
                if (str.equals(USER_SCENARIO_SERVICE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1250402432:
                if (str.equals(XPDOWNLOAD_SERVICE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1080235041:
                if (str.equals(IOT_MANAGER)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -936045084:
                if (str.equals(KARAOKE_SERVICE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -102225187:
                if (str.equals(CONTEXTINFO_SERVICE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3671721:
                if (str.equals(XAPP_SERVICE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 106938681:
                if (str.equals(MEDIACENTER_SERVICE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 109549001:
                if (str.equals(SMART_SERVICE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 649601406:
                if (str.equals(AMBIENTLIGHT_SERVICE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1260496309:
                if (str.equals(AWARENESS_SERVICE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1662702951:
                if (str.equals(OPERATION_SERVICE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1727280137:
                if (str.equals(MUSICRECOGNIZE_SERVICE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new XPDownloadManager(iBinder, this.mContext, this.mEventHandler);
            case 1:
                return new ContextInfoManager(iBinder, this.mContext, this.mEventHandler);
            case 2:
                return new AwarenessManager(iBinder, this.mContext, this.mEventHandler);
            case 3:
                return new SmartManager(iBinder, this.mContext, this.mEventHandler);
            case 4:
                return new AmbientLightManager(iBinder, this.mContext, this.mEventHandler);
            case 5:
                return new MediaCenterManager(iBinder, this.mContext, this.mEventHandler);
            case 6:
                return new MusicRecognizeManager(iBinder, this.mContext, this.mEventHandler);
            case 7:
                return new XAppManager(iBinder, this.mContext, this.mEventHandler);
            case '\b':
                return new OperationManager(iBinder, this.mContext, this.mEventHandler);
            case '\t':
                return new KaraokeManager(iBinder, this.mContext, this.mEventHandler);
            case '\n':
                IoTManager ioTManager = IoTManager.getInstance();
                ioTManager.init(this.mContext);
                return ioTManager;
            case 11:
                return new UserScenarioManager(iBinder, this.mContext, this.mEventHandler);
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startXUIService() {
        Intent intent = new Intent();
        intent.setPackage("com.xiaopeng.xuiservice");
        intent.setAction(XUI_SERVICE_INTERFACE_NAME);
        if (!this.mContext.bindServiceAsUser(intent, this.mServiceConnectionListener, 1, UserHandle.CURRENT_OR_SELF)) {
            this.mConnectionRetryCount++;
            if (this.mConnectionRetryCount > XUI_SERVICE_BIND_MAX_RETRY) {
                Log.w(XUILog.TAG_XUIMANAGER, "cannot bind to XUIService after max retry");
                this.mMainThreadEventHandler.post(this.mConnectionRetryFailedRunnable);
                return;
            }
            this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, XUI_SERVICE_BIND_RETRY_INTERVAL_MS);
            return;
        }
        this.mConnectionRetryCount = 0;
    }

    private synchronized IXUIService getIXUIServiceOrThrow() throws IllegalStateException {
        if (this.mService == null) {
            this.mService = IXUIService.Stub.asInterface(ServiceManager.getService(XUI_MANAGER_SERVICE));
            if (this.mService == null) {
                throw new IllegalStateException("not connected");
            }
        }
        return this.mService;
    }

    private void handleRemoteException(RemoteException remoteException) {
        Log.w(XUILog.TAG_XUIMANAGER, "RemoteException", remoteException);
        disconnect();
    }

    private void tearDownXUIManagers() {
        synchronized (this.mXUIManagerLock) {
            for (XUIManagerBase xUIManagerBase : this.mServiceMap.values()) {
                xUIManagerBase.onXUIDisconnected();
            }
            this.mServiceMap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyServerDisconnectForXUIManagers() {
        synchronized (this.mXUIManagerLock) {
            for (XUIManagerBase xUIManagerBase : this.mServiceMap.values()) {
                xUIManagerBase.onServerDisconnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bringUpXUIManagers() {
        synchronized (this.mXUIManagerLock) {
            for (XUIManagerBase xUIManagerBase : this.mServiceMap.values()) {
                try {
                    xUIManagerBase.onXUIConnected(this.mService.getXUIService(xUIManagerBase.getServiceName()));
                } catch (RemoteException e) {
                    Log.w(XUILog.TAG_XUIMANAGER, "bringUpXUIManagers,e=" + e);
                }
            }
        }
    }
}

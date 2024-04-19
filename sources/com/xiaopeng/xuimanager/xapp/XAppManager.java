package com.xiaopeng.xuimanager.xapp;

import android.annotation.SystemApi;
import android.app.IActivityManager;
import android.app.INotificationManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManager;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
import com.xiaopeng.xuimanager.xapp.IXApp;
import com.xiaopeng.xuimanager.xapp.IXAppEventListener;
import com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@SystemApi
/* loaded from: classes2.dex */
public class XAppManager implements XUIManagerBase {
    public static final boolean DBG = true;
    private static final int MSG_XAPP_ERROR_EVENT = 0;
    public static final String TAG = "XAppManager";
    private static final ArraySet<XMiniProgEventListener> mMiniProgListeners = new ArraySet<>();
    private static String mServiceName;
    private final Handler mHandler;
    private IXApp mService;
    private final ArraySet<XAppEventListener> mListeners = new ArraySet<>();
    private XAppEventListenerToService mListenerToService = null;
    private XMiniProgEventListenerToService mMiniProgListenerToService = null;

    /* loaded from: classes2.dex */
    public interface AppType {
        public static final int APP_TYPE_EDUCATION = 5;
        public static final int APP_TYPE_GAME = 3;
        public static final int APP_TYPE_INFO = 4;
        public static final int APP_TYPE_KARAOKE = 7;
        public static final int APP_TYPE_MUSIC = 1;
        public static final int APP_TYPE_OTHER = 0;
        public static final int APP_TYPE_TOOL = 6;
        public static final int APP_TYPE_VIDEO = 2;
    }

    /* loaded from: classes2.dex */
    public interface MiniProgType {
        public static final int MINI_PROG_TYPE_EXIT = 5;
        public static final int MINI_PROG_TYPE_INIT = 0;
        public static final int MINI_PROG_TYPE_LAUNCH = 3;
        public static final int MINI_PROG_TYPE_LOGIN = 1;
        public static final int MINI_PROG_TYPE_LOGIN_INFO = 6;
        public static final int MINI_PROG_TYPE_LOGOUT = 2;
        public static final int MINI_PROG_TYPE_MINI_LIST = 7;
        public static final int MINI_PROG_TYPE_PRELOAD = 4;
    }

    /* loaded from: classes2.dex */
    public interface XAppEventListener {
        void onErrorEvent(int i, int i2);
    }

    /* loaded from: classes2.dex */
    public interface XMiniProgEventListener {
        void onMiniProgCallBack(int i, MiniProgramResponse miniProgramResponse);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<XAppManager> mMgr;

        EventCallbackHandler(XAppManager xAppManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(xAppManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                XAppManager xAppManager = this.mMgr.get();
                if (xAppManager != null) {
                    xAppManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                    return;
                }
                return;
            }
            Log.e(XAppManager.TAG, "Event type not handled?" + message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class XAppEventListenerToService extends IXAppEventListener.Stub {
        private final WeakReference<XAppManager> mManager;

        public XAppEventListenerToService(XAppManager xAppManager) {
            this.mManager = new WeakReference<>(xAppManager);
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXAppEventListener
        public void onError(int i, int i2) {
            XAppManager xAppManager = this.mManager.get();
            if (xAppManager != null) {
                xAppManager.handleErrorEvent(i, i2);
            }
        }
    }

    public XAppManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IXApp.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(XAppEventListener xAppEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new XAppEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(xAppEventListener);
    }

    public synchronized void unregisterListener(XAppEventListener xAppEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterListener");
        this.mListeners.remove(xAppEventListener);
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
        ArraySet<XAppEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (XAppEventListener xAppEventListener : arraySet) {
                xAppEventListener.onErrorEvent(i, i2);
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

    public void setAppUsedLimitEnable(boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "setAppUsedLimitEnable(boolean enable):" + z);
        try {
            this.mService.setAppUsedLimitEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not setAppUsedLimitEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean getAppUsedLimitEnable() throws XUIServiceNotConnectedException {
        Log.d(TAG, "getAppUsedLimitEnable()");
        try {
            return this.mService.getAppUsedLimitEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAppUsedLimitEnable: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int checkAppStart(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "checkAppStart()");
        try {
            return this.mService.checkAppStart(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not checkAppStart: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int onAppConfigUpload(String str, String str2) throws XUIServiceNotConnectedException {
        Log.d(TAG, "onAppConfigUpload()");
        try {
            return this.mService.onAppConfigUpload(str, str2);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not onAppConfigUpload: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<XAppEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IXApp.Stub.asInterface(iBinder);
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

    public synchronized void registerMiniProgListener(XMiniProgEventListener xMiniProgEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "registerMiniProgListener");
        if (mMiniProgListeners.isEmpty()) {
            try {
                this.mMiniProgListenerToService = new XMiniProgEventListenerToService(this);
                this.mService.registerMiniProgListener(this.mMiniProgListenerToService);
            } catch (Exception e) {
                Log.e(TAG, "Could not connect: " + e.toString());
            }
        }
        mMiniProgListeners.add(xMiniProgEventListener);
    }

    public synchronized void unregisterMiniProgListener(XMiniProgEventListener xMiniProgEventListener) throws XUIServiceNotConnectedException {
        Log.d(TAG, "unregisterMiniProgListener");
        mMiniProgListeners.remove(xMiniProgEventListener);
        if (mMiniProgListeners.isEmpty()) {
            try {
                this.mService.unregisterMiniProgListener(this.mMiniProgListenerToService);
                this.mMiniProgListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public void startMiniProgram(String str, String str2, Map<String, String> map) throws XUIServiceNotConnectedException {
        Log.d(TAG, "startMiniProgram id:" + str + " name:" + str2 + " params:" + map);
        try {
            this.mService.startMiniProgram(str, str2, map);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not startMiniProgram: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void logoutAccount() throws XUIServiceNotConnectedException {
        Log.d(TAG, "logoutAccount");
        try {
            this.mService.logoutAccount();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not logoutAccount: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void attachContext() throws XUIServiceNotConnectedException {
        Log.d(TAG, "attachContext");
        try {
            this.mService.attachContext();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not attachContext: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void initService() throws XUIServiceNotConnectedException {
        Log.d(TAG, "initService");
        try {
            this.mService.initService();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not initService: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean isServiceOnline() throws XUIServiceNotConnectedException {
        Log.d(TAG, "isServiceOnline");
        try {
            return this.mService.isServiceOnline();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not isServiceOnline: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void activeArome(Map map) throws XUIServiceNotConnectedException {
        Log.d(TAG, "activeArome");
        try {
            this.mService.activeArome(map);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not activeArome: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void loginAccount() throws XUIServiceNotConnectedException {
        Log.d(TAG, "loginAccount");
        try {
            this.mService.loginAccount();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not loginAccount: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void exitApp(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "exitApp");
        try {
            this.mService.exitApp(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not exitApp: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void preloadApp(String str, boolean z) throws XUIServiceNotConnectedException {
        Log.d(TAG, "preloadApp");
        try {
            this.mService.preloadApp(str, z);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not preloadApp: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void requestLoginInfo() throws XUIServiceNotConnectedException {
        Log.d(TAG, "requestLoginInfo");
        try {
            this.mService.requestLoginInfo();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not requestLoginInfo: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void requestMiniList(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "requestMiniList");
        try {
            this.mService.requestMiniList(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not requestMiniList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void uploadAlipayLog() throws XUIServiceNotConnectedException {
        Log.d(TAG, "uploadAlipayLog");
        try {
            this.mService.uploadAlipayLog();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not uploadAlipayLog: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public boolean isTopActivityFullscreen() throws Exception {
        return IActivityManager.Stub.asInterface(ServiceManager.getService(UserScenarioManager.SOURCE_ACTIVITY)).isTopActivityFullscreen();
    }

    public void forceGrantFolderPermission(String str) throws Exception {
        IActivityManager.Stub.asInterface(ServiceManager.getService(UserScenarioManager.SOURCE_ACTIVITY)).forceGrantFolderPermission(str);
    }

    public static List<String> getSpeechObserver() throws Exception {
        return IActivityManager.Stub.asInterface(ServiceManager.getService(UserScenarioManager.SOURCE_ACTIVITY)).getSpeechObserver();
    }

    public static void setToastEnabled(boolean z) throws Exception {
        INotificationManager service = NotificationManager.getService();
        if (service != null) {
            service.setToastEnabled(z);
        }
    }

    public int getAppType(String str) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getAppType with pkgName:" + str);
        try {
            return this.mService.getAppType(str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getAppType: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public List<String> getInstalledAppList(int i) throws XUIServiceNotConnectedException {
        Log.d(TAG, "getInstalledAppList with type:" + i);
        try {
            return this.mService.getInstalledAppList(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not getInstalledAppList: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* loaded from: classes2.dex */
    private static class XMiniProgEventListenerToService extends IXMiniProgEventListener.Stub {
        private final WeakReference<XAppManager> mManager;

        public XMiniProgEventListenerToService(XAppManager xAppManager) {
            this.mManager = new WeakReference<>(xAppManager);
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener
        public void onMiniProgCallBack(int i, MiniProgramResponse miniProgramResponse) {
            try {
                Iterator it = XAppManager.mMiniProgListeners.iterator();
                while (it.hasNext()) {
                    ((XMiniProgEventListener) it.next()).onMiniProgCallBack(i, miniProgramResponse);
                }
            } catch (Exception unused) {
            }
        }
    }
}

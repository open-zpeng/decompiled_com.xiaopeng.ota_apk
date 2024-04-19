package com.xiaopeng.xuimanager.xapp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.app.xpPackageInfo;
import com.xiaopeng.xuimanager.xapp.IXAppEventListener;
import com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IXApp extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IXApp {
        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void activeArome(Map map) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void attachContext() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public int checkAppStart(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void exitApp(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public int getAppType(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public boolean getAppUsedLimitEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public int getCarGearLevel() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public List<String> getInstalledAppList(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void initService() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public boolean isServiceOnline() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void loginAccount() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void logoutAccount() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public int onAppConfigUpload(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void onAppModeChanged(String str, xpPackageInfo xppackageinfo) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void preloadApp(String str, boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void registerListener(IXAppEventListener iXAppEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void registerMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void requestLoginInfo() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void requestMiniList(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void setAppUsedLimitEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void startMiniProgram(String str, String str2, Map map) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void unregisterListener(IXAppEventListener iXAppEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void unregisterMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXApp
        public void uploadAlipayLog() throws RemoteException {
        }
    }

    void activeArome(Map map) throws RemoteException;

    void attachContext() throws RemoteException;

    int checkAppStart(String str) throws RemoteException;

    void exitApp(String str) throws RemoteException;

    int getAppType(String str) throws RemoteException;

    boolean getAppUsedLimitEnable() throws RemoteException;

    int getCarGearLevel() throws RemoteException;

    List<String> getInstalledAppList(int i) throws RemoteException;

    void initService() throws RemoteException;

    boolean isServiceOnline() throws RemoteException;

    void loginAccount() throws RemoteException;

    void logoutAccount() throws RemoteException;

    int onAppConfigUpload(String str, String str2) throws RemoteException;

    void onAppModeChanged(String str, xpPackageInfo xppackageinfo) throws RemoteException;

    void preloadApp(String str, boolean z) throws RemoteException;

    void registerListener(IXAppEventListener iXAppEventListener) throws RemoteException;

    void registerMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException;

    void requestLoginInfo() throws RemoteException;

    void requestMiniList(String str) throws RemoteException;

    void setAppUsedLimitEnable(boolean z) throws RemoteException;

    void startMiniProgram(String str, String str2, Map map) throws RemoteException;

    void unregisterListener(IXAppEventListener iXAppEventListener) throws RemoteException;

    void unregisterMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException;

    void uploadAlipayLog() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IXApp {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.xapp.IXApp";
        static final int TRANSACTION_activeArome = 15;
        static final int TRANSACTION_attachContext = 12;
        static final int TRANSACTION_checkAppStart = 22;
        static final int TRANSACTION_exitApp = 17;
        static final int TRANSACTION_getAppType = 23;
        static final int TRANSACTION_getAppUsedLimitEnable = 4;
        static final int TRANSACTION_getCarGearLevel = 5;
        static final int TRANSACTION_getInstalledAppList = 24;
        static final int TRANSACTION_initService = 13;
        static final int TRANSACTION_isServiceOnline = 14;
        static final int TRANSACTION_loginAccount = 16;
        static final int TRANSACTION_logoutAccount = 11;
        static final int TRANSACTION_onAppConfigUpload = 7;
        static final int TRANSACTION_onAppModeChanged = 6;
        static final int TRANSACTION_preloadApp = 18;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_registerMiniProgListener = 8;
        static final int TRANSACTION_requestLoginInfo = 19;
        static final int TRANSACTION_requestMiniList = 20;
        static final int TRANSACTION_setAppUsedLimitEnable = 3;
        static final int TRANSACTION_startMiniProgram = 10;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_unregisterMiniProgListener = 9;
        static final int TRANSACTION_uploadAlipayLog = 21;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXApp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IXApp)) {
                return (IXApp) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IXAppEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IXAppEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppUsedLimitEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean appUsedLimitEnable = getAppUsedLimitEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(appUsedLimitEnable ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carGearLevel = getCarGearLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(carGearLevel);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAppModeChanged(parcel.readString(), parcel.readInt() != 0 ? (xpPackageInfo) xpPackageInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int onAppConfigUpload = onAppConfigUpload(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(onAppConfigUpload);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMiniProgListener(IXMiniProgEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMiniProgListener(IXMiniProgEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    startMiniProgram(parcel.readString(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    logoutAccount();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    attachContext();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    initService();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isServiceOnline = isServiceOnline();
                    parcel2.writeNoException();
                    parcel2.writeInt(isServiceOnline ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    activeArome(parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    loginAccount();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    exitApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    preloadApp(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestLoginInfo();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestMiniList(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadAlipayLog();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int checkAppStart = checkAppStart(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkAppStart);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int appType = getAppType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(appType);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> installedAppList = getInstalledAppList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(installedAppList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IXApp {
            public static IXApp sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void registerListener(IXAppEventListener iXAppEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXAppEventListener != null ? iXAppEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iXAppEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void unregisterListener(IXAppEventListener iXAppEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXAppEventListener != null ? iXAppEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iXAppEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void setAppUsedLimitEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppUsedLimitEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public boolean getAppUsedLimitEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppUsedLimitEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public int getCarGearLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarGearLevel();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void onAppModeChanged(String str, xpPackageInfo xppackageinfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (xppackageinfo != null) {
                        obtain.writeInt(1);
                        xppackageinfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppModeChanged(str, xppackageinfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public int onAppConfigUpload(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onAppConfigUpload(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void registerMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXMiniProgEventListener != null ? iXMiniProgEventListener.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMiniProgListener(iXMiniProgEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void unregisterMiniProgListener(IXMiniProgEventListener iXMiniProgEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXMiniProgEventListener != null ? iXMiniProgEventListener.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMiniProgListener(iXMiniProgEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void startMiniProgram(String str, String str2, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startMiniProgram(str, str2, map);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void logoutAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().logoutAccount();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void attachContext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().attachContext();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void initService() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initService();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public boolean isServiceOnline() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceOnline();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void activeArome(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activeArome(map);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void loginAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().loginAccount();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void exitApp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exitApp(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void preloadApp(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().preloadApp(str, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void requestLoginInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestLoginInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void requestMiniList(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestMiniList(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public void uploadAlipayLog() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().uploadAlipayLog();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public int checkAppStart(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkAppStart(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public int getAppType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppType(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.xapp.IXApp
            public List<String> getInstalledAppList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledAppList(i);
                    }
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXApp iXApp) {
            if (Proxy.sDefaultImpl != null || iXApp == null) {
                return false;
            }
            Proxy.sDefaultImpl = iXApp;
            return true;
        }

        public static IXApp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

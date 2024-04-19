package com.xiaopeng.xuimanager.contextinfo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener;
/* loaded from: classes2.dex */
public interface IContextInfo extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IContextInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public boolean getIntelligentEffectEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public String getWeather() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void registerListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void registerListeners(IContextInfoEventListener iContextInfoEventListener, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void sendContextEvent(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setCarAngular(float f, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setCarAngularVelocity(float f) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setCarSpeed(float f, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setIntelligentEffectEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setNavigationEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void setNavigationInfo(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void unregisterListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
        public void updateWeatherFromServer() throws RemoteException {
        }
    }

    boolean getIntelligentEffectEnable() throws RemoteException;

    String getWeather() throws RemoteException;

    void registerListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException;

    void registerListeners(IContextInfoEventListener iContextInfoEventListener, int i) throws RemoteException;

    void sendContextEvent(int i, int i2) throws RemoteException;

    void setCarAngular(float f, int i) throws RemoteException;

    void setCarAngularVelocity(float f) throws RemoteException;

    void setCarSpeed(float f, int i) throws RemoteException;

    void setIntelligentEffectEnable(boolean z) throws RemoteException;

    void setNavigationEnable(boolean z) throws RemoteException;

    void setNavigationInfo(String str) throws RemoteException;

    void unregisterListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException;

    void updateWeatherFromServer() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IContextInfo {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.contextinfo.IContextInfo";
        static final int TRANSACTION_getIntelligentEffectEnable = 7;
        static final int TRANSACTION_getWeather = 10;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_registerListeners = 13;
        static final int TRANSACTION_sendContextEvent = 12;
        static final int TRANSACTION_setCarAngular = 4;
        static final int TRANSACTION_setCarAngularVelocity = 5;
        static final int TRANSACTION_setCarSpeed = 3;
        static final int TRANSACTION_setIntelligentEffectEnable = 6;
        static final int TRANSACTION_setNavigationEnable = 9;
        static final int TRANSACTION_setNavigationInfo = 8;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_updateWeatherFromServer = 11;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContextInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IContextInfo)) {
                return (IContextInfo) queryLocalInterface;
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
                    registerListener(IContextInfoEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IContextInfoEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCarSpeed(parcel.readFloat(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCarAngular(parcel.readFloat(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCarAngularVelocity(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIntelligentEffectEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean intelligentEffectEnable = getIntelligentEffectEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(intelligentEffectEnable ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNavigationInfo(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNavigationEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String weather = getWeather();
                    parcel2.writeNoException();
                    parcel2.writeString(weather);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateWeatherFromServer();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendContextEvent(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListeners(IContextInfoEventListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IContextInfo {
            public static IContextInfo sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void registerListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iContextInfoEventListener != null ? iContextInfoEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iContextInfoEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void unregisterListener(IContextInfoEventListener iContextInfoEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iContextInfoEventListener != null ? iContextInfoEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iContextInfoEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setCarSpeed(float f, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarSpeed(f, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setCarAngular(float f, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarAngular(f, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setCarAngularVelocity(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarAngularVelocity(f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setIntelligentEffectEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIntelligentEffectEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public boolean getIntelligentEffectEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntelligentEffectEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setNavigationInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNavigationInfo(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void setNavigationEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNavigationEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public String getWeather() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWeather();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void updateWeatherFromServer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateWeatherFromServer();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void sendContextEvent(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendContextEvent(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfo
            public void registerListeners(IContextInfoEventListener iContextInfoEventListener, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iContextInfoEventListener != null ? iContextInfoEventListener.asBinder() : null);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListeners(iContextInfoEventListener, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContextInfo iContextInfo) {
            if (Proxy.sDefaultImpl != null || iContextInfo == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContextInfo;
            return true;
        }

        public static IContextInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

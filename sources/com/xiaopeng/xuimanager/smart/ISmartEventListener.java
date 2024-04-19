package com.xiaopeng.xuimanager.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISmartEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements ISmartEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectFinishEvent(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowError(String str, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowFinishEvent(String str, String str2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowStartEvent(String str, String str2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
        public void onLightEffectShowStopEvent(String str, String str2) throws RemoteException {
        }
    }

    void onError(int i, int i2) throws RemoteException;

    void onLightEffectFinishEvent(int i, int i2) throws RemoteException;

    void onLightEffectShowError(String str, int i) throws RemoteException;

    void onLightEffectShowFinishEvent(String str, String str2) throws RemoteException;

    void onLightEffectShowStartEvent(String str, String str2) throws RemoteException;

    void onLightEffectShowStopEvent(String str, String str2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISmartEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.smart.ISmartEventListener";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onLightEffectFinishEvent = 2;
        static final int TRANSACTION_onLightEffectShowError = 6;
        static final int TRANSACTION_onLightEffectShowFinishEvent = 5;
        static final int TRANSACTION_onLightEffectShowStartEvent = 3;
        static final int TRANSACTION_onLightEffectShowStopEvent = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartEventListener)) {
                return (ISmartEventListener) queryLocalInterface;
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
                    onError(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLightEffectFinishEvent(parcel.readInt(), parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLightEffectShowStartEvent(parcel.readString(), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLightEffectShowStopEvent(parcel.readString(), parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLightEffectShowFinishEvent(parcel.readString(), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLightEffectShowError(parcel.readString(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISmartEventListener {
            public static ISmartEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onLightEffectFinishEvent(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectFinishEvent(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onLightEffectShowStartEvent(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectShowStartEvent(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onLightEffectShowStopEvent(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectShowStopEvent(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onLightEffectShowFinishEvent(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectShowFinishEvent(str, str2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartEventListener
            public void onLightEffectShowError(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectShowError(str, i);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartEventListener iSmartEventListener) {
            if (Proxy.sDefaultImpl != null || iSmartEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmartEventListener;
            return true;
        }

        public static ISmartEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

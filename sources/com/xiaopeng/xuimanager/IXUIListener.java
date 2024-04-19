package com.xiaopeng.xuimanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IXUIListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IXUIListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.IXUIListener
        public void onConnected() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.IXUIListener
        public void onDisconnected() throws RemoteException {
        }
    }

    void onConnected() throws RemoteException;

    void onDisconnected() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IXUIListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.IXUIListener";
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDisconnected = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXUIListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IXUIListener)) {
                return (IXUIListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnected();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisconnected();
                return true;
            } else if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IXUIListener {
            public static IXUIListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.IXUIListener
            public void onConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnected();
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.IXUIListener
            public void onDisconnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisconnected();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXUIListener iXUIListener) {
            if (Proxy.sDefaultImpl != null || iXUIListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iXUIListener;
            return true;
        }

        public static IXUIListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

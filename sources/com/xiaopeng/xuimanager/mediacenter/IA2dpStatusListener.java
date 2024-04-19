package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IA2dpStatusListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IA2dpStatusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
        public void onA2dpConnected() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
        public void onA2dpDisconnected() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
        public void onA2dpServiceReady() throws RemoteException {
        }
    }

    void onA2dpConnected() throws RemoteException;

    void onA2dpDisconnected() throws RemoteException;

    void onA2dpServiceReady() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IA2dpStatusListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener";
        static final int TRANSACTION_onA2dpConnected = 2;
        static final int TRANSACTION_onA2dpDisconnected = 3;
        static final int TRANSACTION_onA2dpServiceReady = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IA2dpStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IA2dpStatusListener)) {
                return (IA2dpStatusListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onA2dpServiceReady();
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onA2dpConnected();
                parcel2.writeNoException();
                return true;
            } else if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onA2dpDisconnected();
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IA2dpStatusListener {
            public static IA2dpStatusListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
            public void onA2dpServiceReady() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onA2dpServiceReady();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
            public void onA2dpConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onA2dpConnected();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IA2dpStatusListener
            public void onA2dpDisconnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onA2dpDisconnected();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IA2dpStatusListener iA2dpStatusListener) {
            if (Proxy.sDefaultImpl != null || iA2dpStatusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iA2dpStatusListener;
            return true;
        }

        public static IA2dpStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

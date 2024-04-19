package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IVisualizerViewEnableListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IVisualizerViewEnableListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener
        public void onVisualizerViewEnable(boolean z) throws RemoteException {
        }
    }

    void onVisualizerViewEnable(boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVisualizerViewEnableListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener";
        static final int TRANSACTION_onVisualizerViewEnable = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVisualizerViewEnableListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IVisualizerViewEnableListener)) {
                return (IVisualizerViewEnableListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onVisualizerViewEnable(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVisualizerViewEnableListener {
            public static IVisualizerViewEnableListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener
            public void onVisualizerViewEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVisualizerViewEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVisualizerViewEnableListener iVisualizerViewEnableListener) {
            if (Proxy.sDefaultImpl != null || iVisualizerViewEnableListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVisualizerViewEnableListener;
            return true;
        }

        public static IVisualizerViewEnableListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

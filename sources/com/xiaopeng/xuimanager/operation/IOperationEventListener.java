package com.xiaopeng.xuimanager.operation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IOperationEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IOperationEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
        public void onEvent(int i, String str, int i2, String str2) throws RemoteException {
        }
    }

    void onError(int i, int i2) throws RemoteException;

    void onEvent(int i, String str, int i2, String str2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOperationEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.operation.IOperationEventListener";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onEvent = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOperationEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOperationEventListener)) {
                return (IOperationEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onEvent(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
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
        public static class Proxy implements IOperationEventListener {
            public static IOperationEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
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

            @Override // com.xiaopeng.xuimanager.operation.IOperationEventListener
            public void onEvent(int i, String str, int i2, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEvent(i, str, i2, str2);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOperationEventListener iOperationEventListener) {
            if (Proxy.sDefaultImpl != null || iOperationEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOperationEventListener;
            return true;
        }

        public static IOperationEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

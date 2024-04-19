package com.xiaopeng.xuimanager.operation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.operation.IOperationEventListener;
/* loaded from: classes2.dex */
public interface IOperation extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IOperation {
        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public int addNewResource(String str, int i, String str2, String str3) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public boolean checkResourceExist(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public int deleteResource(String str, int i, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public String getDownLoadResourceList(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public String getLocalResourceList(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public void registerListener(IOperationEventListener iOperationEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.operation.IOperation
        public void unregisterListener(IOperationEventListener iOperationEventListener) throws RemoteException {
        }
    }

    int addNewResource(String str, int i, String str2, String str3) throws RemoteException;

    boolean checkResourceExist(int i, String str) throws RemoteException;

    int deleteResource(String str, int i, String str2) throws RemoteException;

    String getDownLoadResourceList(int i) throws RemoteException;

    String getLocalResourceList(int i) throws RemoteException;

    void registerListener(IOperationEventListener iOperationEventListener) throws RemoteException;

    void unregisterListener(IOperationEventListener iOperationEventListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOperation {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.operation.IOperation";
        static final int TRANSACTION_addNewResource = 3;
        static final int TRANSACTION_checkResourceExist = 5;
        static final int TRANSACTION_deleteResource = 6;
        static final int TRANSACTION_getDownLoadResourceList = 7;
        static final int TRANSACTION_getLocalResourceList = 4;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_unregisterListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOperation asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOperation)) {
                return (IOperation) queryLocalInterface;
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
                    registerListener(IOperationEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IOperationEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int addNewResource = addNewResource(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(addNewResource);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String localResourceList = getLocalResourceList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(localResourceList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkResourceExist = checkResourceExist(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkResourceExist ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int deleteResource = deleteResource(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deleteResource);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String downLoadResourceList = getDownLoadResourceList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(downLoadResourceList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOperation {
            public static IOperation sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public void registerListener(IOperationEventListener iOperationEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOperationEventListener != null ? iOperationEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iOperationEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public void unregisterListener(IOperationEventListener iOperationEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOperationEventListener != null ? iOperationEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iOperationEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public int addNewResource(String str, int i, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addNewResource(str, i, str2, str3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public String getLocalResourceList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocalResourceList(i);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public boolean checkResourceExist(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkResourceExist(i, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public int deleteResource(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteResource(str, i, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.operation.IOperation
            public String getDownLoadResourceList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownLoadResourceList(i);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOperation iOperation) {
            if (Proxy.sDefaultImpl != null || iOperation == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOperation;
            return true;
        }

        public static IOperation getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

package com.xiaopeng.xuimanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IXUIService extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IXUIService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.IXUIService
        public IBinder getXUIService(String str) throws RemoteException {
            return null;
        }
    }

    IBinder getXUIService(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IXUIService {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.IXUIService";
        static final int TRANSACTION_getXUIService = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.xiaopeng.xuimanager.IXUIService");
        }

        public static IXUIService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaopeng.xuimanager.IXUIService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IXUIService)) {
                return (IXUIService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.xiaopeng.xuimanager.IXUIService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.xiaopeng.xuimanager.IXUIService");
            IBinder xUIService = getXUIService(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(xUIService);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IXUIService {
            public static IXUIService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.xiaopeng.xuimanager.IXUIService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.xuimanager.IXUIService
            public IBinder getXUIService(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xuimanager.IXUIService");
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getXUIService(str);
                    }
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXUIService iXUIService) {
            if (Proxy.sDefaultImpl != null || iXUIService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iXUIService;
            return true;
        }

        public static IXUIService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

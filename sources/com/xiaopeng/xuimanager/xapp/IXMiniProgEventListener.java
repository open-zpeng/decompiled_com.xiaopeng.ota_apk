package com.xiaopeng.xuimanager.xapp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IXMiniProgEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IXMiniProgEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener
        public void onMiniProgCallBack(int i, MiniProgramResponse miniProgramResponse) throws RemoteException {
        }
    }

    void onMiniProgCallBack(int i, MiniProgramResponse miniProgramResponse) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IXMiniProgEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener";
        static final int TRANSACTION_onMiniProgCallBack = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXMiniProgEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IXMiniProgEventListener)) {
                return (IXMiniProgEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onMiniProgCallBack(parcel.readInt(), parcel.readInt() != 0 ? MiniProgramResponse.CREATOR.createFromParcel(parcel) : null);
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
        public static class Proxy implements IXMiniProgEventListener {
            public static IXMiniProgEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.xapp.IXMiniProgEventListener
            public void onMiniProgCallBack(int i, MiniProgramResponse miniProgramResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (miniProgramResponse != null) {
                        obtain.writeInt(1);
                        miniProgramResponse.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMiniProgCallBack(i, miniProgramResponse);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXMiniProgEventListener iXMiniProgEventListener) {
            if (Proxy.sDefaultImpl != null || iXMiniProgEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iXMiniProgEventListener;
            return true;
        }

        public static IXMiniProgEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

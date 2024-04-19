package com.xiaopeng.xuimanager.karaoke;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IKaraokeEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IKaraokeEventListener {
        @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
        public void MicDevChangeCallBack(int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
        public void onError(int i, int i2) throws RemoteException {
        }
    }

    void MicDevChangeCallBack(int i) throws RemoteException;

    void onError(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKaraokeEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener";
        static final int TRANSACTION_MicDevChangeCallBack = 1;
        static final int TRANSACTION_onError = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKaraokeEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IKaraokeEventListener)) {
                return (IKaraokeEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                MicDevChangeCallBack(parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt(), parcel.readInt());
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
        public static class Proxy implements IKaraokeEventListener {
            public static IKaraokeEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
            public void MicDevChangeCallBack(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().MicDevChangeCallBack(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener
            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKaraokeEventListener iKaraokeEventListener) {
            if (Proxy.sDefaultImpl != null || iKaraokeEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKaraokeEventListener;
            return true;
        }

        public static IKaraokeEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

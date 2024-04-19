package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IPlaybackInfoListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IPlaybackInfoListener {
        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnPlaybackChanged(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnUpdatePosition(long j, long j2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void OnMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException;

    void OnPlaybackChanged(int i) throws RemoteException;

    void OnUpdatePosition(long j, long j2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPlaybackInfoListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener";
        static final int TRANSACTION_OnMediaInfoNotify = 3;
        static final int TRANSACTION_OnPlaybackChanged = 1;
        static final int TRANSACTION_OnUpdatePosition = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlaybackInfoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPlaybackInfoListener)) {
                return (IPlaybackInfoListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                OnPlaybackChanged(parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                OnUpdatePosition(parcel.readLong(), parcel.readLong());
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
                OnMediaInfoNotify(parcel.readInt() != 0 ? MediaInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPlaybackInfoListener {
            public static IPlaybackInfoListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
            public void OnPlaybackChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnPlaybackChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
            public void OnUpdatePosition(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnUpdatePosition(j, j2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
            public void OnMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaInfo != null) {
                        obtain.writeInt(1);
                        mediaInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnMediaInfoNotify(mediaInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlaybackInfoListener iPlaybackInfoListener) {
            if (Proxy.sDefaultImpl != null || iPlaybackInfoListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlaybackInfoListener;
            return true;
        }

        public static IPlaybackInfoListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

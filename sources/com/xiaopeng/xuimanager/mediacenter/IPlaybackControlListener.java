package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IPlaybackControlListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IPlaybackControlListener {
        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnPlaybackControl(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnSetFavorite(boolean z, String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnSwitchSource(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    int OnPlaybackControl(int i, int i2) throws RemoteException;

    int OnSetFavorite(boolean z, String str) throws RemoteException;

    int OnSwitchSource(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPlaybackControlListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener";
        static final int TRANSACTION_OnPlaybackControl = 1;
        static final int TRANSACTION_OnSetFavorite = 3;
        static final int TRANSACTION_OnSwitchSource = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlaybackControlListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPlaybackControlListener)) {
                return (IPlaybackControlListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int OnPlaybackControl = OnPlaybackControl(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(OnPlaybackControl);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int OnSwitchSource = OnSwitchSource(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(OnSwitchSource);
                return true;
            } else if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                int OnSetFavorite = OnSetFavorite(parcel.readInt() != 0, parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(OnSetFavorite);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPlaybackControlListener {
            public static IPlaybackControlListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
            public int OnPlaybackControl(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnPlaybackControl(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
            public int OnSwitchSource(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnSwitchSource(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
            public int OnSetFavorite(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnSetFavorite(z, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlaybackControlListener iPlaybackControlListener) {
            if (Proxy.sDefaultImpl != null || iPlaybackControlListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlaybackControlListener;
            return true;
        }

        public static IPlaybackControlListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

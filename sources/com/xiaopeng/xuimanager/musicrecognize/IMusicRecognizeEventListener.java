package com.xiaopeng.xuimanager.musicrecognize;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMusicRecognizeEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IMusicRecognizeEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onFindCoverEvent(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
        public void onRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent) throws RemoteException {
        }
    }

    void onError(int i, int i2) throws RemoteException;

    void onFindCoverEvent(String str) throws RemoteException;

    void onRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMusicRecognizeEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener";
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onFindCoverEvent = 1;
        static final int TRANSACTION_onRecognizeEvent = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicRecognizeEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMusicRecognizeEventListener)) {
                return (IMusicRecognizeEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onFindCoverEvent(parcel.readString());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onRecognizeEvent(parcel.readInt() != 0 ? MusicRecognizeEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i == 3) {
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
        public static class Proxy implements IMusicRecognizeEventListener {
            public static IMusicRecognizeEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
            public void onFindCoverEvent(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFindCoverEvent(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
            public void onRecognizeEvent(MusicRecognizeEvent musicRecognizeEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (musicRecognizeEvent != null) {
                        obtain.writeInt(1);
                        musicRecognizeEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecognizeEvent(musicRecognizeEvent);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener
            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicRecognizeEventListener iMusicRecognizeEventListener) {
            if (Proxy.sDefaultImpl != null || iMusicRecognizeEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMusicRecognizeEventListener;
            return true;
        }

        public static IMusicRecognizeEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

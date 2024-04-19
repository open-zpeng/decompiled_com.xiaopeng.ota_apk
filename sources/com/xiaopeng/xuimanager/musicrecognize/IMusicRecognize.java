package com.xiaopeng.xuimanager.musicrecognize;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.musicrecognize.IMusicRecognizeEventListener;
/* loaded from: classes2.dex */
public interface IMusicRecognize extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IMusicRecognize {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void findSongCover(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public float getMinScore() throws RemoteException {
            return 0.0f;
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public int getMode() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void registerListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void setMinScore(float f) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void setMode(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void start() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void stop() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void stopAndRecognize() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
        public void unregisterListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException {
        }
    }

    void findSongCover(String str) throws RemoteException;

    float getMinScore() throws RemoteException;

    int getMode() throws RemoteException;

    void registerListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException;

    void setMinScore(float f) throws RemoteException;

    void setMode(int i) throws RemoteException;

    void start() throws RemoteException;

    void stop() throws RemoteException;

    void stopAndRecognize() throws RemoteException;

    void unregisterListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMusicRecognize {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize";
        static final int TRANSACTION_findSongCover = 10;
        static final int TRANSACTION_getMinScore = 9;
        static final int TRANSACTION_getMode = 7;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setMinScore = 8;
        static final int TRANSACTION_setMode = 6;
        static final int TRANSACTION_start = 3;
        static final int TRANSACTION_stop = 4;
        static final int TRANSACTION_stopAndRecognize = 5;
        static final int TRANSACTION_unregisterListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicRecognize asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMusicRecognize)) {
                return (IMusicRecognize) queryLocalInterface;
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
                    registerListener(IMusicRecognizeEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IMusicRecognizeEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    start();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopAndRecognize();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mode = getMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(mode);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMinScore(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    float minScore = getMinScore();
                    parcel2.writeNoException();
                    parcel2.writeFloat(minScore);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    findSongCover(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMusicRecognize {
            public static IMusicRecognize sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void registerListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMusicRecognizeEventListener != null ? iMusicRecognizeEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iMusicRecognizeEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void unregisterListener(IMusicRecognizeEventListener iMusicRecognizeEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMusicRecognizeEventListener != null ? iMusicRecognizeEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iMusicRecognizeEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void start() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().start();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void stopAndRecognize() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAndRecognize();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void setMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public int getMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void setMinScore(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMinScore(f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public float getMinScore() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinScore();
                    }
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.musicrecognize.IMusicRecognize
            public void findSongCover(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().findSongCover(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicRecognize iMusicRecognize) {
            if (Proxy.sDefaultImpl != null || iMusicRecognize == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMusicRecognize;
            return true;
        }

        public static IMusicRecognize getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

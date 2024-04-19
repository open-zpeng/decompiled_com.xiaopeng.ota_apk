package com.xiaopeng.xuimanager.download;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IDownloadListenerInterface extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IDownloadListenerInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadCancel(long j, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadCompleted(long j, String str, String str2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadError(long j, String str, String str2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadInProgress(long j, String str, long j2, float f, long j3) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadPause(long j, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onDownloadStart(long j, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onUnRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
        public void onUnRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
        }
    }

    void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException;

    void onDownloadCancel(long j, String str) throws RemoteException;

    void onDownloadCompleted(long j, String str, String str2) throws RemoteException;

    void onDownloadError(long j, String str, String str2) throws RemoteException;

    void onDownloadInProgress(long j, String str, long j2, float f, long j3) throws RemoteException;

    void onDownloadPause(long j, String str) throws RemoteException;

    void onDownloadStart(long j, String str) throws RemoteException;

    void onRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException;

    void onRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException;

    void onUnRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException;

    void onUnRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDownloadListenerInterface {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.download.IDownloadListenerInterface";
        static final int TRANSACTION_basicTypes = 1;
        static final int TRANSACTION_onDownloadCancel = 9;
        static final int TRANSACTION_onDownloadCompleted = 11;
        static final int TRANSACTION_onDownloadError = 10;
        static final int TRANSACTION_onDownloadInProgress = 7;
        static final int TRANSACTION_onDownloadPause = 8;
        static final int TRANSACTION_onDownloadStart = 6;
        static final int TRANSACTION_onRegisterError = 3;
        static final int TRANSACTION_onRegisterSuccess = 2;
        static final int TRANSACTION_onUnRegisterError = 5;
        static final int TRANSACTION_onUnRegisterSuccess = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadListenerInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadListenerInterface)) {
                return (IDownloadListenerInterface) queryLocalInterface;
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
                    basicTypes(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRegisterSuccess(asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRegisterError(asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUnRegisterSuccess(asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUnRegisterError(asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadStart(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadInProgress(parcel.readLong(), parcel.readString(), parcel.readLong(), parcel.readFloat(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadPause(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadCancel(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadError(parcel.readLong(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadCompleted(parcel.readLong(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IDownloadListenerInterface {
            public static IDownloadListenerInterface sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeFloat(f);
                    obtain.writeDouble(d);
                    obtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().basicTypes(i, j, z, f, d, str);
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    obtain2.readException();
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRegisterSuccess(iDownloadListenerInterface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRegisterError(iDownloadListenerInterface, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onUnRegisterSuccess(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnRegisterSuccess(iDownloadListenerInterface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onUnRegisterError(IDownloadListenerInterface iDownloadListenerInterface, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnRegisterError(iDownloadListenerInterface, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadStart(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadStart(j, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadInProgress(long j, String str, long j2, float f, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j3);
                    try {
                        if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().onDownloadInProgress(j, str, j2, f, j3);
                            obtain2.recycle();
                            obtain.recycle();
                            return;
                        }
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadPause(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadPause(j, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadCancel(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadCancel(j, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadError(long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadError(j, str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadListenerInterface
            public void onDownloadCompleted(long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadCompleted(j, str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadListenerInterface iDownloadListenerInterface) {
            if (Proxy.sDefaultImpl != null || iDownloadListenerInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadListenerInterface;
            return true;
        }

        public static IDownloadListenerInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

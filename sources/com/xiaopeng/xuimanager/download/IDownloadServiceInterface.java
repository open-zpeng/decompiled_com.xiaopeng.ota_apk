package com.xiaopeng.xuimanager.download;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.download.IDownloadListenerInterface;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IDownloadServiceInterface extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IDownloadServiceInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public long enqueue(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public long enqueueWithHeader(String str, String str2, String str3, Map map) throws RemoteException {
            return 0L;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public long enqueueWtihTitleDescription(String str, String str2, String str3, Map map) throws RemoteException {
            return 0L;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public String getState(long j, String str) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public int[] getStatusListByPackageName(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public void registerListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
        public void unRegisterListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
        }
    }

    void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException;

    long enqueue(String str) throws RemoteException;

    long enqueueWithHeader(String str, String str2, String str3, Map map) throws RemoteException;

    long enqueueWtihTitleDescription(String str, String str2, String str3, Map map) throws RemoteException;

    String getState(long j, String str) throws RemoteException;

    int[] getStatusListByPackageName(String str, String str2) throws RemoteException;

    void registerListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException;

    void unRegisterListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDownloadServiceInterface {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.download.IDownloadServiceInterface";
        static final int TRANSACTION_basicTypes = 1;
        static final int TRANSACTION_enqueue = 2;
        static final int TRANSACTION_enqueueWithHeader = 4;
        static final int TRANSACTION_enqueueWtihTitleDescription = 3;
        static final int TRANSACTION_getState = 5;
        static final int TRANSACTION_getStatusListByPackageName = 8;
        static final int TRANSACTION_registerListener = 6;
        static final int TRANSACTION_unRegisterListener = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadServiceInterface)) {
                return (IDownloadServiceInterface) queryLocalInterface;
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
                    long enqueue = enqueue(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(enqueue);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    long enqueueWtihTitleDescription = enqueueWtihTitleDescription(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    parcel2.writeLong(enqueueWtihTitleDescription);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long enqueueWithHeader = enqueueWithHeader(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    parcel2.writeLong(enqueueWithHeader);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String state = getState(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(state);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IDownloadListenerInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterListener(IDownloadListenerInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] statusListByPackageName = getStatusListByPackageName(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(statusListByPackageName);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IDownloadServiceInterface {
            public static IDownloadServiceInterface sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
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

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public long enqueue(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueue(str);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public long enqueueWtihTitleDescription(String str, String str2, String str3, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueueWtihTitleDescription(str, str2, str3, map);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public long enqueueWithHeader(String str, String str2, String str3, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueueWithHeader(str, str2, str3, map);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public String getState(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(j, str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public void registerListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iDownloadListenerInterface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public void unRegisterListener(IDownloadListenerInterface iDownloadListenerInterface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadListenerInterface != null ? iDownloadListenerInterface.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterListener(iDownloadListenerInterface);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.download.IDownloadServiceInterface
            public int[] getStatusListByPackageName(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusListByPackageName(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadServiceInterface iDownloadServiceInterface) {
            if (Proxy.sDefaultImpl != null || iDownloadServiceInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadServiceInterface;
            return true;
        }

        public static IDownloadServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

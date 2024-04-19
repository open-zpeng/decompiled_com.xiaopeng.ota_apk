package com.xiaopeng.xuimanager.pipebus;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.pipebus.IPipeBusListener;
/* loaded from: classes2.dex */
public interface IPipeBus extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IPipeBus {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
        public int ioControl(String str, String str2, String[] strArr) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
        public int ioControlWithPocket(String str, String str2, String[] strArr, String[] strArr2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
        public void registerListener(IPipeBusListener iPipeBusListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
        public void unRegisterListener(IPipeBusListener iPipeBusListener) throws RemoteException {
        }
    }

    int ioControl(String str, String str2, String[] strArr) throws RemoteException;

    int ioControlWithPocket(String str, String str2, String[] strArr, String[] strArr2) throws RemoteException;

    void registerListener(IPipeBusListener iPipeBusListener) throws RemoteException;

    void unRegisterListener(IPipeBusListener iPipeBusListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPipeBus {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.pipebus.IPipeBus";
        static final int TRANSACTION_ioControl = 1;
        static final int TRANSACTION_ioControlWithPocket = 2;
        static final int TRANSACTION_registerListener = 3;
        static final int TRANSACTION_unRegisterListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPipeBus asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPipeBus)) {
                return (IPipeBus) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int ioControl = ioControl(parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                parcel2.writeInt(ioControl);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String[] createStringArray = parcel.createStringArray();
                int readInt = parcel.readInt();
                String[] strArr = readInt < 0 ? null : new String[readInt];
                int ioControlWithPocket = ioControlWithPocket(readString, readString2, createStringArray, strArr);
                parcel2.writeNoException();
                parcel2.writeInt(ioControlWithPocket);
                parcel2.writeStringArray(strArr);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                registerListener(IPipeBusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                unRegisterListener(IPipeBusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPipeBus {
            public static IPipeBus sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
            public int ioControl(String str, String str2, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ioControl(str, str2, strArr);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
            public int ioControlWithPocket(String str, String str2, String[] strArr, String[] strArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (strArr2 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr2.length);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ioControlWithPocket(str, str2, strArr, strArr2);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr2);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
            public void registerListener(IPipeBusListener iPipeBusListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPipeBusListener != null ? iPipeBusListener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iPipeBusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.pipebus.IPipeBus
            public void unRegisterListener(IPipeBusListener iPipeBusListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPipeBusListener != null ? iPipeBusListener.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterListener(iPipeBusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPipeBus iPipeBus) {
            if (Proxy.sDefaultImpl != null || iPipeBus == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPipeBus;
            return true;
        }

        public static IPipeBus getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

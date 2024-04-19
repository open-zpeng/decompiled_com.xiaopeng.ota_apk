package com.xiaopeng.xuimanager.userscenario;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.userscenario.IUserScenarioListener;
/* loaded from: classes2.dex */
public interface IUserScenario extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IUserScenario {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public String checkEnterUserScenario(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public String enterUserScenario(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public String exitUserScenario(String str) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public String getCurrentUserScenario() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public int getUserScenarioStatus(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public void registerBinderObserver(IBinder iBinder) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public void registerListener(IUserScenarioListener iUserScenarioListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public void reportStatus(String str, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
        public void unregisterListener(IUserScenarioListener iUserScenarioListener) throws RemoteException {
        }
    }

    String checkEnterUserScenario(String str, String str2) throws RemoteException;

    String enterUserScenario(String str, String str2) throws RemoteException;

    String exitUserScenario(String str) throws RemoteException;

    String getCurrentUserScenario() throws RemoteException;

    int getUserScenarioStatus(String str) throws RemoteException;

    void registerBinderObserver(IBinder iBinder) throws RemoteException;

    void registerListener(IUserScenarioListener iUserScenarioListener) throws RemoteException;

    void reportStatus(String str, int i) throws RemoteException;

    void unregisterListener(IUserScenarioListener iUserScenarioListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IUserScenario {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.userscenario.IUserScenario";
        static final int TRANSACTION_checkEnterUserScenario = 9;
        static final int TRANSACTION_enterUserScenario = 1;
        static final int TRANSACTION_exitUserScenario = 2;
        static final int TRANSACTION_getCurrentUserScenario = 3;
        static final int TRANSACTION_getUserScenarioStatus = 4;
        static final int TRANSACTION_registerBinderObserver = 8;
        static final int TRANSACTION_registerListener = 5;
        static final int TRANSACTION_reportStatus = 7;
        static final int TRANSACTION_unregisterListener = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserScenario asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUserScenario)) {
                return (IUserScenario) queryLocalInterface;
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
                    String enterUserScenario = enterUserScenario(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(enterUserScenario);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String exitUserScenario = exitUserScenario(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(exitUserScenario);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentUserScenario = getCurrentUserScenario();
                    parcel2.writeNoException();
                    parcel2.writeString(currentUserScenario);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userScenarioStatus = getUserScenarioStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(userScenarioStatus);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IUserScenarioListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IUserScenarioListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportStatus(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBinderObserver(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String checkEnterUserScenario = checkEnterUserScenario(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(checkEnterUserScenario);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IUserScenario {
            public static IUserScenario sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public String enterUserScenario(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enterUserScenario(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public String exitUserScenario(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exitUserScenario(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public String getCurrentUserScenario() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentUserScenario();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public int getUserScenarioStatus(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserScenarioStatus(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public void registerListener(IUserScenarioListener iUserScenarioListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUserScenarioListener != null ? iUserScenarioListener.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iUserScenarioListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public void unregisterListener(IUserScenarioListener iUserScenarioListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUserScenarioListener != null ? iUserScenarioListener.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iUserScenarioListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public void reportStatus(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportStatus(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public void registerBinderObserver(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBinderObserver(iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenario
            public String checkEnterUserScenario(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkEnterUserScenario(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserScenario iUserScenario) {
            if (Proxy.sDefaultImpl != null || iUserScenario == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUserScenario;
            return true;
        }

        public static IUserScenario getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

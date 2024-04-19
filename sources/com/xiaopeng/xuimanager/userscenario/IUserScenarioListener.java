package com.xiaopeng.xuimanager.userscenario;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IUserScenarioListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IUserScenarioListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenarioListener
        public void onUserScenarioStateChanged(String str, int i) throws RemoteException {
        }
    }

    void onUserScenarioStateChanged(String str, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IUserScenarioListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.userscenario.IUserScenarioListener";
        static final int TRANSACTION_onUserScenarioStateChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserScenarioListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUserScenarioListener)) {
                return (IUserScenarioListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onUserScenarioStateChanged(parcel.readString(), parcel.readInt());
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
        public static class Proxy implements IUserScenarioListener {
            public static IUserScenarioListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.userscenario.IUserScenarioListener
            public void onUserScenarioStateChanged(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUserScenarioStateChanged(str, i);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserScenarioListener iUserScenarioListener) {
            if (Proxy.sDefaultImpl != null || iUserScenarioListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUserScenarioListener;
            return true;
        }

        public static IUserScenarioListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

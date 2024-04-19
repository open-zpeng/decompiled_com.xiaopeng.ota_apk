package com.xiaopeng.xuimanager.ambientlight;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IAmbientLightEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IAmbientLightEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightDoubleColorEnableEvent(String str, boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightDoubleColorSetEvent(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightEffectTypeSetEvent(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
        public void onLightMonoColorSetEvent(String str, int i) throws RemoteException {
        }
    }

    void onError(int i, int i2) throws RemoteException;

    void onLightDoubleColorEnableEvent(String str, boolean z) throws RemoteException;

    void onLightDoubleColorSetEvent(String str, int i, int i2) throws RemoteException;

    void onLightEffectTypeSetEvent(String str) throws RemoteException;

    void onLightMonoColorSetEvent(String str, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAmbientLightEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener";
        static final int TRANSACTION_onError = 5;
        static final int TRANSACTION_onLightDoubleColorEnableEvent = 2;
        static final int TRANSACTION_onLightDoubleColorSetEvent = 4;
        static final int TRANSACTION_onLightEffectTypeSetEvent = 1;
        static final int TRANSACTION_onLightMonoColorSetEvent = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAmbientLightEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAmbientLightEventListener)) {
                return (IAmbientLightEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onLightEffectTypeSetEvent(parcel.readString());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onLightDoubleColorEnableEvent(parcel.readString(), parcel.readInt() != 0);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onLightMonoColorSetEvent(parcel.readString(), parcel.readInt());
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onLightDoubleColorSetEvent(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 5) {
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
        public static class Proxy implements IAmbientLightEventListener {
            public static IAmbientLightEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
            public void onLightEffectTypeSetEvent(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightEffectTypeSetEvent(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
            public void onLightDoubleColorEnableEvent(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightDoubleColorEnableEvent(str, z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
            public void onLightMonoColorSetEvent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightMonoColorSetEvent(str, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
            public void onLightDoubleColorSetEvent(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLightDoubleColorSetEvent(str, i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener
            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAmbientLightEventListener iAmbientLightEventListener) {
            if (Proxy.sDefaultImpl != null || iAmbientLightEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAmbientLightEventListener;
            return true;
        }

        public static IAmbientLightEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

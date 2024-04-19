package com.xiaopeng.xuimanager.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISmartCommonEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements ISmartCommonEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
        public void onSmartCommonEvent(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
        public void onSmartSpeechTtsEvent(int i, String str) throws RemoteException {
        }
    }

    void onSmartCommonEvent(int i, int i2, int i3) throws RemoteException;

    void onSmartSpeechTtsEvent(int i, String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISmartCommonEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.smart.ISmartCommonEventListener";
        static final int TRANSACTION_onSmartCommonEvent = 1;
        static final int TRANSACTION_onSmartSpeechTtsEvent = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartCommonEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartCommonEventListener)) {
                return (ISmartCommonEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSmartCommonEvent(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSmartSpeechTtsEvent(parcel.readInt(), parcel.readString());
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
        public static class Proxy implements ISmartCommonEventListener {
            public static ISmartCommonEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
            public void onSmartCommonEvent(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSmartCommonEvent(i, i2, i3);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmartCommonEventListener
            public void onSmartSpeechTtsEvent(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSmartSpeechTtsEvent(i, str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartCommonEventListener iSmartCommonEventListener) {
            if (Proxy.sDefaultImpl != null || iSmartCommonEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmartCommonEventListener;
            return true;
        }

        public static ISmartCommonEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

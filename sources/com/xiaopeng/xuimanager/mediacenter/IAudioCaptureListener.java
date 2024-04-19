package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IAudioCaptureListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IAudioCaptureListener {
        @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
        public void OnFftDataCapture(byte[] bArr, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
        public void OnRatioData(float f, float f2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void OnFftDataCapture(byte[] bArr, int i) throws RemoteException;

    void OnRatioData(float f, float f2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAudioCaptureListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener";
        static final int TRANSACTION_OnFftDataCapture = 1;
        static final int TRANSACTION_OnRatioData = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAudioCaptureListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAudioCaptureListener)) {
                return (IAudioCaptureListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                OnFftDataCapture(parcel.createByteArray(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                OnRatioData(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IAudioCaptureListener {
            public static IAudioCaptureListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
            public void OnFftDataCapture(byte[] bArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnFftDataCapture(bArr, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
            public void OnRatioData(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnRatioData(f, f2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAudioCaptureListener iAudioCaptureListener) {
            if (Proxy.sDefaultImpl != null || iAudioCaptureListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAudioCaptureListener;
            return true;
        }

        public static IAudioCaptureListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

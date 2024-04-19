package com.xiaopeng.xuimanager.ambientlight;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.ambientlight.IAmbientLightEventListener;
import java.util.List;
/* loaded from: classes2.dex */
public interface IAmbientLight extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IAmbientLight {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public int getAmbientLightDoubleFirstColor(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public int getAmbientLightDoubleSecondColor(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public String getAmbientLightEffectType() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public List<String> getAmbientLightEffectTypeList() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public int getAmbientLightMonoColor(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public boolean getAmbientLightOpen() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public boolean getDoubleThemeColorEnable(String str) throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public boolean getMusicSpectrumEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public boolean isSupportDoubleThemeColor(String str) throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void registerListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setAmbientLightDoubleColor(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setAmbientLightEffectType(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setAmbientLightMonoColor(String str, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setAmbientLightOpen(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setDoubleThemeColorEnable(String str, boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setMusicRhythmMode(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void setMusicSpectrumEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
        public void unregisterListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException {
        }
    }

    int getAmbientLightDoubleFirstColor(String str) throws RemoteException;

    int getAmbientLightDoubleSecondColor(String str) throws RemoteException;

    String getAmbientLightEffectType() throws RemoteException;

    List<String> getAmbientLightEffectTypeList() throws RemoteException;

    int getAmbientLightMonoColor(String str) throws RemoteException;

    boolean getAmbientLightOpen() throws RemoteException;

    boolean getDoubleThemeColorEnable(String str) throws RemoteException;

    boolean getMusicSpectrumEnable() throws RemoteException;

    boolean isSupportDoubleThemeColor(String str) throws RemoteException;

    void registerListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException;

    void setAmbientLightDoubleColor(String str, int i, int i2) throws RemoteException;

    void setAmbientLightEffectType(String str) throws RemoteException;

    void setAmbientLightMonoColor(String str, int i) throws RemoteException;

    void setAmbientLightOpen(boolean z) throws RemoteException;

    void setDoubleThemeColorEnable(String str, boolean z) throws RemoteException;

    void setMusicRhythmMode(boolean z) throws RemoteException;

    void setMusicSpectrumEnable(boolean z) throws RemoteException;

    void unregisterListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAmbientLight {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.ambientlight.IAmbientLight";
        static final int TRANSACTION_getAmbientLightDoubleFirstColor = 9;
        static final int TRANSACTION_getAmbientLightDoubleSecondColor = 10;
        static final int TRANSACTION_getAmbientLightEffectType = 13;
        static final int TRANSACTION_getAmbientLightEffectTypeList = 17;
        static final int TRANSACTION_getAmbientLightMonoColor = 7;
        static final int TRANSACTION_getAmbientLightOpen = 15;
        static final int TRANSACTION_getDoubleThemeColorEnable = 11;
        static final int TRANSACTION_getMusicSpectrumEnable = 3;
        static final int TRANSACTION_isSupportDoubleThemeColor = 18;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setAmbientLightDoubleColor = 8;
        static final int TRANSACTION_setAmbientLightEffectType = 14;
        static final int TRANSACTION_setAmbientLightMonoColor = 6;
        static final int TRANSACTION_setAmbientLightOpen = 16;
        static final int TRANSACTION_setDoubleThemeColorEnable = 12;
        static final int TRANSACTION_setMusicRhythmMode = 5;
        static final int TRANSACTION_setMusicSpectrumEnable = 4;
        static final int TRANSACTION_unregisterListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAmbientLight asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAmbientLight)) {
                return (IAmbientLight) queryLocalInterface;
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
                    registerListener(IAmbientLightEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IAmbientLightEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean musicSpectrumEnable = getMusicSpectrumEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(musicSpectrumEnable ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicSpectrumEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicRhythmMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAmbientLightMonoColor(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ambientLightMonoColor = getAmbientLightMonoColor(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ambientLightMonoColor);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAmbientLightDoubleColor(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ambientLightDoubleFirstColor = getAmbientLightDoubleFirstColor(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ambientLightDoubleFirstColor);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ambientLightDoubleSecondColor = getAmbientLightDoubleSecondColor(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ambientLightDoubleSecondColor);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean doubleThemeColorEnable = getDoubleThemeColorEnable(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(doubleThemeColorEnable ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDoubleThemeColorEnable(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String ambientLightEffectType = getAmbientLightEffectType();
                    parcel2.writeNoException();
                    parcel2.writeString(ambientLightEffectType);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAmbientLightEffectType(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ambientLightOpen = getAmbientLightOpen();
                    parcel2.writeNoException();
                    parcel2.writeInt(ambientLightOpen ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAmbientLightOpen(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> ambientLightEffectTypeList = getAmbientLightEffectTypeList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(ambientLightEffectTypeList);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSupportDoubleThemeColor = isSupportDoubleThemeColor(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSupportDoubleThemeColor ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IAmbientLight {
            public static IAmbientLight sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void registerListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAmbientLightEventListener != null ? iAmbientLightEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iAmbientLightEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void unregisterListener(IAmbientLightEventListener iAmbientLightEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAmbientLightEventListener != null ? iAmbientLightEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iAmbientLightEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public boolean getMusicSpectrumEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicSpectrumEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setMusicSpectrumEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicSpectrumEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setMusicRhythmMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicRhythmMode(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setAmbientLightMonoColor(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbientLightMonoColor(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public int getAmbientLightMonoColor(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightMonoColor(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setAmbientLightDoubleColor(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbientLightDoubleColor(str, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public int getAmbientLightDoubleFirstColor(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightDoubleFirstColor(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public int getAmbientLightDoubleSecondColor(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightDoubleSecondColor(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public boolean getDoubleThemeColorEnable(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDoubleThemeColorEnable(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setDoubleThemeColorEnable(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDoubleThemeColorEnable(str, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public String getAmbientLightEffectType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightEffectType();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setAmbientLightEffectType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbientLightEffectType(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public boolean getAmbientLightOpen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightOpen();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public void setAmbientLightOpen(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbientLightOpen(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public List<String> getAmbientLightEffectTypeList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientLightEffectTypeList();
                    }
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.ambientlight.IAmbientLight
            public boolean isSupportDoubleThemeColor(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportDoubleThemeColor(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAmbientLight iAmbientLight) {
            if (Proxy.sDefaultImpl != null || iAmbientLight == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAmbientLight;
            return true;
        }

        public static IAmbientLight getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

package com.xiaopeng.xuimanager.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener;
import com.xiaopeng.xuimanager.mediacenter.IBTStatusListener;
import com.xiaopeng.xuimanager.mediacenter.ILyricUpdateListener;
import com.xiaopeng.xuimanager.mediacenter.IMediaCenterEventListener;
import com.xiaopeng.xuimanager.mediacenter.IModeChangedListener;
import com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener;
import com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener;
import com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener;
/* loaded from: classes2.dex */
public interface IMediaCenter extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IMediaCenter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int getBtStatus() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public MediaInfo getCurrentMediaInfo() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int getCurrentMode() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int getCurrentPlayStatus() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public long[] getCurrentPosition() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void notifyLyricUpdate(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void pauseBtMedia() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void playBtMedia() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int playbackControl(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void registerVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int setFavorite(boolean z, String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void setVisualizerViewEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public int switchSource(int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unRegisterVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void unregisterListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorRegister() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorStartAudioSession(int i, int i2, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorStopAudioSession(int i, String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorUnRegister() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorUnSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorUpdatePlaybackStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
        public void vendorUpdatePosition(long j, long j2) throws RemoteException {
        }
    }

    int getBtStatus() throws RemoteException;

    MediaInfo getCurrentMediaInfo() throws RemoteException;

    int getCurrentMode() throws RemoteException;

    int getCurrentPlayStatus() throws RemoteException;

    long[] getCurrentPosition() throws RemoteException;

    void notifyLyricUpdate(String str) throws RemoteException;

    void pauseBtMedia() throws RemoteException;

    void playBtMedia() throws RemoteException;

    int playbackControl(int i, int i2) throws RemoteException;

    void registerBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException;

    void registerListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException;

    void registerLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException;

    void registerModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException;

    void registerPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException;

    void registerVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException;

    void registerVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException;

    int setFavorite(boolean z, String str) throws RemoteException;

    void setVisualizerViewEnable(boolean z) throws RemoteException;

    int switchSource(int i) throws RemoteException;

    void unRegisterBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException;

    void unRegisterLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException;

    void unRegisterModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException;

    void unRegisterPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException;

    void unRegisterVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException;

    void unRegisterVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException;

    void unregisterListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException;

    void vendorMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException;

    void vendorRegister() throws RemoteException;

    void vendorSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException;

    void vendorStartAudioSession(int i, int i2, String str) throws RemoteException;

    void vendorStopAudioSession(int i, String str) throws RemoteException;

    void vendorUnRegister() throws RemoteException;

    void vendorUnSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException;

    void vendorUpdatePlaybackStatus(int i) throws RemoteException;

    void vendorUpdatePosition(long j, long j2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaCenter {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.mediacenter.IMediaCenter";
        static final int TRANSACTION_getBtStatus = 34;
        static final int TRANSACTION_getCurrentMediaInfo = 19;
        static final int TRANSACTION_getCurrentMode = 29;
        static final int TRANSACTION_getCurrentPlayStatus = 18;
        static final int TRANSACTION_getCurrentPosition = 20;
        static final int TRANSACTION_notifyLyricUpdate = 24;
        static final int TRANSACTION_pauseBtMedia = 33;
        static final int TRANSACTION_playBtMedia = 32;
        static final int TRANSACTION_playbackControl = 16;
        static final int TRANSACTION_registerBtStatusListener = 30;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_registerLyricUpdateListener = 25;
        static final int TRANSACTION_registerModeChangedListener = 27;
        static final int TRANSACTION_registerPlaybackInfoListener = 14;
        static final int TRANSACTION_registerVisualizerListener = 12;
        static final int TRANSACTION_registerVisualizerViewEnableListener = 22;
        static final int TRANSACTION_setFavorite = 35;
        static final int TRANSACTION_setVisualizerViewEnable = 21;
        static final int TRANSACTION_switchSource = 17;
        static final int TRANSACTION_unRegisterBtStatusListener = 31;
        static final int TRANSACTION_unRegisterLyricUpdateListener = 26;
        static final int TRANSACTION_unRegisterModeChangedListener = 28;
        static final int TRANSACTION_unRegisterPlaybackInfoListener = 15;
        static final int TRANSACTION_unRegisterVisualizerListener = 13;
        static final int TRANSACTION_unRegisterVisualizerViewEnableListener = 23;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_vendorMediaInfoNotify = 7;
        static final int TRANSACTION_vendorRegister = 3;
        static final int TRANSACTION_vendorSetControlListener = 8;
        static final int TRANSACTION_vendorStartAudioSession = 10;
        static final int TRANSACTION_vendorStopAudioSession = 11;
        static final int TRANSACTION_vendorUnRegister = 4;
        static final int TRANSACTION_vendorUnSetControlListener = 9;
        static final int TRANSACTION_vendorUpdatePlaybackStatus = 5;
        static final int TRANSACTION_vendorUpdatePosition = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMediaCenter)) {
                return (IMediaCenter) queryLocalInterface;
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
                    registerListener(IMediaCenterEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IMediaCenterEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorRegister();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorUnRegister();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorUpdatePlaybackStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorUpdatePosition(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorMediaInfoNotify(parcel.readInt() != 0 ? MediaInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorSetControlListener(parcel.readString(), IPlaybackControlListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorUnSetControlListener(parcel.readString(), IPlaybackControlListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorStartAudioSession(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    vendorStopAudioSession(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerVisualizerListener(IAudioCaptureListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterVisualizerListener(IAudioCaptureListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerPlaybackInfoListener(IPlaybackInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterPlaybackInfoListener(IPlaybackInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playbackControl = playbackControl(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(playbackControl);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int switchSource = switchSource(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(switchSource);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentPlayStatus = getCurrentPlayStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentPlayStatus);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaInfo currentMediaInfo = getCurrentMediaInfo();
                    parcel2.writeNoException();
                    if (currentMediaInfo != null) {
                        parcel2.writeInt(1);
                        currentMediaInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] currentPosition = getCurrentPosition();
                    parcel2.writeNoException();
                    parcel2.writeLongArray(currentPosition);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVisualizerViewEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerVisualizerViewEnableListener(IVisualizerViewEnableListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterVisualizerViewEnableListener(IVisualizerViewEnableListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyLyricUpdate(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerLyricUpdateListener(ILyricUpdateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterLyricUpdateListener(ILyricUpdateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerModeChangedListener(IModeChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterModeChangedListener(IModeChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentMode = getCurrentMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentMode);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBtStatusListener(IBTStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterBtStatusListener(IBTStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    playBtMedia();
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseBtMedia();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int btStatus = getBtStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(btStatus);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int favorite = setFavorite(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(favorite);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaCenter {
            public static IMediaCenter sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaCenterEventListener != null ? iMediaCenterEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iMediaCenterEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unregisterListener(IMediaCenterEventListener iMediaCenterEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaCenterEventListener != null ? iMediaCenterEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iMediaCenterEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorRegister() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorRegister();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorUnRegister() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorUnRegister();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorUpdatePlaybackStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorUpdatePlaybackStatus(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorUpdatePosition(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorUpdatePosition(j, j2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorMediaInfoNotify(MediaInfo mediaInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaInfo != null) {
                        obtain.writeInt(1);
                        mediaInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorMediaInfoNotify(mediaInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPlaybackControlListener != null ? iPlaybackControlListener.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorSetControlListener(str, iPlaybackControlListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorUnSetControlListener(String str, IPlaybackControlListener iPlaybackControlListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPlaybackControlListener != null ? iPlaybackControlListener.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorUnSetControlListener(str, iPlaybackControlListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorStartAudioSession(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorStartAudioSession(i, i2, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void vendorStopAudioSession(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vendorStopAudioSession(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAudioCaptureListener != null ? iAudioCaptureListener.asBinder() : null);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVisualizerListener(iAudioCaptureListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterVisualizerListener(IAudioCaptureListener iAudioCaptureListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAudioCaptureListener != null ? iAudioCaptureListener.asBinder() : null);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterVisualizerListener(iAudioCaptureListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPlaybackInfoListener != null ? iPlaybackInfoListener.asBinder() : null);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerPlaybackInfoListener(iPlaybackInfoListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterPlaybackInfoListener(IPlaybackInfoListener iPlaybackInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPlaybackInfoListener != null ? iPlaybackInfoListener.asBinder() : null);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterPlaybackInfoListener(iPlaybackInfoListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int playbackControl(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playbackControl(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int switchSource(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchSource(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int getCurrentPlayStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPlayStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public MediaInfo getCurrentMediaInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentMediaInfo();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MediaInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public long[] getCurrentPosition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPosition();
                    }
                    obtain2.readException();
                    return obtain2.createLongArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void setVisualizerViewEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVisualizerViewEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iVisualizerViewEnableListener != null ? iVisualizerViewEnableListener.asBinder() : null);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVisualizerViewEnableListener(iVisualizerViewEnableListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterVisualizerViewEnableListener(IVisualizerViewEnableListener iVisualizerViewEnableListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iVisualizerViewEnableListener != null ? iVisualizerViewEnableListener.asBinder() : null);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterVisualizerViewEnableListener(iVisualizerViewEnableListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void notifyLyricUpdate(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyLyricUpdate(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLyricUpdateListener != null ? iLyricUpdateListener.asBinder() : null);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerLyricUpdateListener(iLyricUpdateListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterLyricUpdateListener(ILyricUpdateListener iLyricUpdateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLyricUpdateListener != null ? iLyricUpdateListener.asBinder() : null);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterLyricUpdateListener(iLyricUpdateListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iModeChangedListener != null ? iModeChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerModeChangedListener(iModeChangedListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterModeChangedListener(IModeChangedListener iModeChangedListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iModeChangedListener != null ? iModeChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterModeChangedListener(iModeChangedListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int getCurrentMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void registerBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBTStatusListener != null ? iBTStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBtStatusListener(iBTStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void unRegisterBtStatusListener(IBTStatusListener iBTStatusListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBTStatusListener != null ? iBTStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterBtStatusListener(iBTStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void playBtMedia() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playBtMedia();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public void pauseBtMedia() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseBtMedia();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int getBtStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBtStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenter
            public int setFavorite(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFavorite(z, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaCenter iMediaCenter) {
            if (Proxy.sDefaultImpl != null || iMediaCenter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaCenter;
            return true;
        }

        public static IMediaCenter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

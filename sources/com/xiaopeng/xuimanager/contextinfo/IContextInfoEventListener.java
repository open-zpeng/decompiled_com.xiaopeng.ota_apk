package com.xiaopeng.xuimanager.contextinfo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IContextInfoEventListener extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IContextInfoEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onATLSStatus(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAccelerationEvent(float f) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAngularVelocityEvent(float f) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAutoBrightness(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onAvpWifi(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onBcmLightMode(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onBeltStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCameraEvent(Camera camera, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCameraIntervalEvent(CameraInterval cameraInterval, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCarSpeed(float f) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onChargingGunStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCommonEvent(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onCrossEvent(Cross cross, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onDeviceChargeStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onDriverSeatStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onGearChanged(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onIGStatus(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLLUStatus(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLaneEvent(Lane lane, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onLocationEvent(Location location, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onManeuverEvent(Maneuver maneuver, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onMsgEvent(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNaviEvent(Navi navi, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNavigationEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onNavigationInfo(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPowerAction(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPowerOffCount(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onPsdMoto(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onRemainInfoEvent(RemainInfo remainInfo, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onRemoteCommand(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onSapaEvent(Sapa sapa, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onVpmEvent(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onWeatherInfo(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
        public void onXPilotWarning(int i, int i2) throws RemoteException {
        }
    }

    void onATLSStatus(int i, int i2) throws RemoteException;

    void onAccelerationEvent(float f) throws RemoteException;

    void onAngularVelocityEvent(float f) throws RemoteException;

    void onAutoBrightness(int i, int i2) throws RemoteException;

    void onAvpWifi(int i) throws RemoteException;

    void onBcmLightMode(int i, int i2) throws RemoteException;

    void onBeltStatus(int i) throws RemoteException;

    void onCameraEvent(Camera camera, int i) throws RemoteException;

    void onCameraIntervalEvent(CameraInterval cameraInterval, int i) throws RemoteException;

    void onCarSpeed(float f) throws RemoteException;

    void onChargingGunStatus(int i) throws RemoteException;

    void onCommonEvent(int i, int i2) throws RemoteException;

    void onCrossEvent(Cross cross, int i) throws RemoteException;

    void onDeviceChargeStatus(int i) throws RemoteException;

    void onDriverSeatStatus(int i) throws RemoteException;

    void onError(int i, int i2) throws RemoteException;

    void onGearChanged(int i) throws RemoteException;

    void onIGStatus(int i) throws RemoteException;

    void onLLUStatus(int i, int i2) throws RemoteException;

    void onLaneEvent(Lane lane, int i) throws RemoteException;

    void onLocationEvent(Location location, int i) throws RemoteException;

    void onManeuverEvent(Maneuver maneuver, int i) throws RemoteException;

    void onMsgEvent(int i) throws RemoteException;

    void onNaviEvent(Navi navi, int i) throws RemoteException;

    void onNavigationEnable(boolean z) throws RemoteException;

    void onNavigationInfo(String str) throws RemoteException;

    void onPowerAction(int i) throws RemoteException;

    void onPowerOffCount(int i) throws RemoteException;

    void onPsdMoto(int i) throws RemoteException;

    void onRemainInfoEvent(RemainInfo remainInfo, int i) throws RemoteException;

    void onRemoteCommand(int i) throws RemoteException;

    void onSapaEvent(Sapa sapa, int i) throws RemoteException;

    void onVpmEvent(int i, int i2) throws RemoteException;

    void onWeatherInfo(String str) throws RemoteException;

    void onXPilotWarning(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IContextInfoEventListener {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener";
        static final int TRANSACTION_onATLSStatus = 24;
        static final int TRANSACTION_onAccelerationEvent = 1;
        static final int TRANSACTION_onAngularVelocityEvent = 2;
        static final int TRANSACTION_onAutoBrightness = 19;
        static final int TRANSACTION_onAvpWifi = 21;
        static final int TRANSACTION_onBcmLightMode = 30;
        static final int TRANSACTION_onBeltStatus = 25;
        static final int TRANSACTION_onCameraEvent = 7;
        static final int TRANSACTION_onCameraIntervalEvent = 8;
        static final int TRANSACTION_onCarSpeed = 17;
        static final int TRANSACTION_onChargingGunStatus = 27;
        static final int TRANSACTION_onCommonEvent = 36;
        static final int TRANSACTION_onCrossEvent = 10;
        static final int TRANSACTION_onDeviceChargeStatus = 28;
        static final int TRANSACTION_onDriverSeatStatus = 29;
        static final int TRANSACTION_onError = 37;
        static final int TRANSACTION_onGearChanged = 16;
        static final int TRANSACTION_onIGStatus = 18;
        static final int TRANSACTION_onLLUStatus = 23;
        static final int TRANSACTION_onLaneEvent = 6;
        static final int TRANSACTION_onLocationEvent = 11;
        static final int TRANSACTION_onManeuverEvent = 3;
        static final int TRANSACTION_onMsgEvent = 14;
        static final int TRANSACTION_onNaviEvent = 5;
        static final int TRANSACTION_onNavigationEnable = 13;
        static final int TRANSACTION_onNavigationInfo = 12;
        static final int TRANSACTION_onPowerAction = 26;
        static final int TRANSACTION_onPowerOffCount = 33;
        static final int TRANSACTION_onPsdMoto = 32;
        static final int TRANSACTION_onRemainInfoEvent = 4;
        static final int TRANSACTION_onRemoteCommand = 22;
        static final int TRANSACTION_onSapaEvent = 9;
        static final int TRANSACTION_onVpmEvent = 31;
        static final int TRANSACTION_onWeatherInfo = 15;
        static final int TRANSACTION_onXPilotWarning = 20;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContextInfoEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IContextInfoEventListener)) {
                return (IContextInfoEventListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 36) {
                parcel.enforceInterface(DESCRIPTOR);
                onCommonEvent(parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 37) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        onAccelerationEvent(parcel.readFloat());
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        onAngularVelocityEvent(parcel.readFloat());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        onManeuverEvent(parcel.readInt() != 0 ? Maneuver.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        onRemainInfoEvent(parcel.readInt() != 0 ? RemainInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        onNaviEvent(parcel.readInt() != 0 ? Navi.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        onLaneEvent(parcel.readInt() != 0 ? Lane.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        onCameraEvent(parcel.readInt() != 0 ? Camera.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        onCameraIntervalEvent(parcel.readInt() != 0 ? CameraInterval.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        onSapaEvent(parcel.readInt() != 0 ? Sapa.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        onCrossEvent(parcel.readInt() != 0 ? Cross.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        onLocationEvent(parcel.readInt() != 0 ? Location.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        onNavigationInfo(parcel.readString());
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        onNavigationEnable(parcel.readInt() != 0);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        onMsgEvent(parcel.readInt());
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        onWeatherInfo(parcel.readString());
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        onGearChanged(parcel.readInt());
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        onCarSpeed(parcel.readFloat());
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        onIGStatus(parcel.readInt());
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        onAutoBrightness(parcel.readInt(), parcel.readInt());
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        onXPilotWarning(parcel.readInt(), parcel.readInt());
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        onAvpWifi(parcel.readInt());
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        onRemoteCommand(parcel.readInt());
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        onLLUStatus(parcel.readInt(), parcel.readInt());
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        onATLSStatus(parcel.readInt(), parcel.readInt());
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        onBeltStatus(parcel.readInt());
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        onPowerAction(parcel.readInt());
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        onChargingGunStatus(parcel.readInt());
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        onDeviceChargeStatus(parcel.readInt());
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        onDriverSeatStatus(parcel.readInt());
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        onBcmLightMode(parcel.readInt(), parcel.readInt());
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        onVpmEvent(parcel.readInt(), parcel.readInt());
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        onPsdMoto(parcel.readInt());
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        onPowerOffCount(parcel.readInt());
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IContextInfoEventListener {
            public static IContextInfoEventListener sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onAccelerationEvent(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAccelerationEvent(f);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onAngularVelocityEvent(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAngularVelocityEvent(f);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onManeuverEvent(Maneuver maneuver, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (maneuver != null) {
                        obtain.writeInt(1);
                        maneuver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onManeuverEvent(maneuver, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onRemainInfoEvent(RemainInfo remainInfo, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remainInfo != null) {
                        obtain.writeInt(1);
                        remainInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRemainInfoEvent(remainInfo, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onNaviEvent(Navi navi, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (navi != null) {
                        obtain.writeInt(1);
                        navi.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNaviEvent(navi, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onLaneEvent(Lane lane, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lane != null) {
                        obtain.writeInt(1);
                        lane.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLaneEvent(lane, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onCameraEvent(Camera camera, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (camera != null) {
                        obtain.writeInt(1);
                        camera.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(7, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraEvent(camera, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onCameraIntervalEvent(CameraInterval cameraInterval, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cameraInterval != null) {
                        obtain.writeInt(1);
                        cameraInterval.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(8, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraIntervalEvent(cameraInterval, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onSapaEvent(Sapa sapa, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sapa != null) {
                        obtain.writeInt(1);
                        sapa.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(9, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSapaEvent(sapa, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onCrossEvent(Cross cross, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cross != null) {
                        obtain.writeInt(1);
                        cross.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(10, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCrossEvent(cross, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onLocationEvent(Location location, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(11, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLocationEvent(location, i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onNavigationInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(12, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNavigationInfo(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onNavigationEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(13, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNavigationEnable(z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onMsgEvent(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(14, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMsgEvent(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onWeatherInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(15, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWeatherInfo(str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onGearChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(16, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGearChanged(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onCarSpeed(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(17, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCarSpeed(f);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onIGStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(18, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onIGStatus(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onAutoBrightness(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(19, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAutoBrightness(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onXPilotWarning(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(20, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onXPilotWarning(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onAvpWifi(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(21, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAvpWifi(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onRemoteCommand(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(22, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRemoteCommand(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onLLUStatus(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(23, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLLUStatus(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onATLSStatus(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(24, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onATLSStatus(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onBeltStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(25, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBeltStatus(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onPowerAction(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(26, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPowerAction(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onChargingGunStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(27, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChargingGunStatus(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onDeviceChargeStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(28, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceChargeStatus(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onDriverSeatStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(29, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDriverSeatStatus(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onBcmLightMode(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(30, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBcmLightMode(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onVpmEvent(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(31, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVpmEvent(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onPsdMoto(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(32, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPsdMoto(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onPowerOffCount(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(33, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPowerOffCount(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onCommonEvent(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(36, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCommonEvent(i, i2);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.contextinfo.IContextInfoEventListener
            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(37, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContextInfoEventListener iContextInfoEventListener) {
            if (Proxy.sDefaultImpl != null || iContextInfoEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContextInfoEventListener;
            return true;
        }

        public static IContextInfoEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

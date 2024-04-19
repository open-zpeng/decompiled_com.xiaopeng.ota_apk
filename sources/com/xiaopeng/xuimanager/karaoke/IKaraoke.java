package com.xiaopeng.xuimanager.karaoke;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.karaoke.IKaraokeEventListener;
/* loaded from: classes2.dex */
public interface IKaraoke extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements IKaraoke {
        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Create(String str, int i, String str2, IBinder iBinder) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Distroy(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_GetHandShakeStatus(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_GetMicPowerStatus(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_GetMicStatus(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_GetToken(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_GetVolume(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_MicCreate(String str, int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_MicDestroy(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_MicGetAvail(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_MicGetMinBuf(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_MicRead(String str, byte[] bArr, int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Pause(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_PausePlay(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Read(String str, byte[] bArr, int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecCreate(String str, int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecDestroy(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecGetAvail(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecGetMinBuf(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecStart(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_RecStop(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public void XMS_RegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Resume(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_ResumePlay(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_SaveRec(String str, int i, String str2, String str3) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_SetRecMode(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_SetSignedToken(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_SetVolume(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public void XMS_ShowToast(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_StopSaveRec(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_TrackCreate(String str, int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_TrackDestroy(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_TrackGetAvail(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_TrackGetLatency(String str) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_TrackGetMinBuf(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public void XMS_UnRegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_Write(String str, byte[] bArr, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_aiWakeUp() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public int XMS_atlSwitch(boolean z) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public void XMS_connectMicFlow() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public String XMS_getBuyMicUrl() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public String XMS_getMicName() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public boolean XMS_hasAtl() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public boolean XMS_isAtlEnabled() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
        public void XMS_setAtlEnable(boolean z) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    int XMS_Create(String str, int i, String str2, IBinder iBinder) throws RemoteException;

    int XMS_Distroy(String str) throws RemoteException;

    int XMS_GetHandShakeStatus(String str) throws RemoteException;

    int XMS_GetMicPowerStatus(String str) throws RemoteException;

    int XMS_GetMicStatus(String str) throws RemoteException;

    int XMS_GetToken(String str) throws RemoteException;

    int XMS_GetVolume(String str, int i) throws RemoteException;

    int XMS_MicCreate(String str, int i, int i2, int i3) throws RemoteException;

    int XMS_MicDestroy(String str) throws RemoteException;

    int XMS_MicGetAvail(String str) throws RemoteException;

    int XMS_MicGetMinBuf(String str, int i, int i2) throws RemoteException;

    int XMS_MicRead(String str, byte[] bArr, int i) throws RemoteException;

    int XMS_Pause(String str) throws RemoteException;

    int XMS_PausePlay(String str) throws RemoteException;

    int XMS_Read(String str, byte[] bArr, int i) throws RemoteException;

    int XMS_RecCreate(String str, int i, int i2, int i3) throws RemoteException;

    int XMS_RecDestroy(String str) throws RemoteException;

    int XMS_RecGetAvail(String str) throws RemoteException;

    int XMS_RecGetMinBuf(String str, int i, int i2) throws RemoteException;

    int XMS_RecStart(String str) throws RemoteException;

    int XMS_RecStop(String str) throws RemoteException;

    void XMS_RegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException;

    int XMS_Resume(String str) throws RemoteException;

    int XMS_ResumePlay(String str) throws RemoteException;

    int XMS_SaveRec(String str, int i, String str2, String str3) throws RemoteException;

    int XMS_SetRecMode(String str, int i) throws RemoteException;

    int XMS_SetSignedToken(String str, String str2) throws RemoteException;

    int XMS_SetVolume(String str, int i, int i2) throws RemoteException;

    void XMS_ShowToast(String str) throws RemoteException;

    int XMS_StopSaveRec(String str) throws RemoteException;

    int XMS_TrackCreate(String str, int i, int i2, int i3) throws RemoteException;

    int XMS_TrackDestroy(String str) throws RemoteException;

    int XMS_TrackGetAvail(String str) throws RemoteException;

    int XMS_TrackGetLatency(String str) throws RemoteException;

    int XMS_TrackGetMinBuf(String str, int i, int i2) throws RemoteException;

    void XMS_UnRegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException;

    int XMS_Write(String str, byte[] bArr, int i, int i2) throws RemoteException;

    int XMS_aiWakeUp() throws RemoteException;

    int XMS_atlSwitch(boolean z) throws RemoteException;

    void XMS_connectMicFlow() throws RemoteException;

    String XMS_getBuyMicUrl() throws RemoteException;

    String XMS_getMicName() throws RemoteException;

    boolean XMS_hasAtl() throws RemoteException;

    boolean XMS_isAtlEnabled() throws RemoteException;

    void XMS_setAtlEnable(boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKaraoke {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.karaoke.IKaraoke";
        static final int TRANSACTION_XMS_Create = 1;
        static final int TRANSACTION_XMS_Distroy = 4;
        static final int TRANSACTION_XMS_GetHandShakeStatus = 7;
        static final int TRANSACTION_XMS_GetMicPowerStatus = 9;
        static final int TRANSACTION_XMS_GetMicStatus = 8;
        static final int TRANSACTION_XMS_GetToken = 5;
        static final int TRANSACTION_XMS_GetVolume = 11;
        static final int TRANSACTION_XMS_MicCreate = 33;
        static final int TRANSACTION_XMS_MicDestroy = 36;
        static final int TRANSACTION_XMS_MicGetAvail = 34;
        static final int TRANSACTION_XMS_MicGetMinBuf = 32;
        static final int TRANSACTION_XMS_MicRead = 35;
        static final int TRANSACTION_XMS_Pause = 18;
        static final int TRANSACTION_XMS_PausePlay = 23;
        static final int TRANSACTION_XMS_Read = 30;
        static final int TRANSACTION_XMS_RecCreate = 27;
        static final int TRANSACTION_XMS_RecDestroy = 31;
        static final int TRANSACTION_XMS_RecGetAvail = 28;
        static final int TRANSACTION_XMS_RecGetMinBuf = 26;
        static final int TRANSACTION_XMS_RecStart = 20;
        static final int TRANSACTION_XMS_RecStop = 19;
        static final int TRANSACTION_XMS_RegisterCallback = 2;
        static final int TRANSACTION_XMS_Resume = 21;
        static final int TRANSACTION_XMS_ResumePlay = 22;
        static final int TRANSACTION_XMS_SaveRec = 24;
        static final int TRANSACTION_XMS_SetRecMode = 29;
        static final int TRANSACTION_XMS_SetSignedToken = 6;
        static final int TRANSACTION_XMS_SetVolume = 10;
        static final int TRANSACTION_XMS_ShowToast = 45;
        static final int TRANSACTION_XMS_StopSaveRec = 25;
        static final int TRANSACTION_XMS_TrackCreate = 13;
        static final int TRANSACTION_XMS_TrackDestroy = 17;
        static final int TRANSACTION_XMS_TrackGetAvail = 16;
        static final int TRANSACTION_XMS_TrackGetLatency = 14;
        static final int TRANSACTION_XMS_TrackGetMinBuf = 12;
        static final int TRANSACTION_XMS_UnRegisterCallback = 3;
        static final int TRANSACTION_XMS_Write = 15;
        static final int TRANSACTION_XMS_aiWakeUp = 38;
        static final int TRANSACTION_XMS_atlSwitch = 37;
        static final int TRANSACTION_XMS_connectMicFlow = 44;
        static final int TRANSACTION_XMS_getBuyMicUrl = 43;
        static final int TRANSACTION_XMS_getMicName = 40;
        static final int TRANSACTION_XMS_hasAtl = 39;
        static final int TRANSACTION_XMS_isAtlEnabled = 42;
        static final int TRANSACTION_XMS_setAtlEnable = 41;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKaraoke asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IKaraoke)) {
                return (IKaraoke) queryLocalInterface;
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
                    int XMS_Create = XMS_Create(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Create);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    XMS_RegisterCallback(parcel.readString(), IKaraokeEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    XMS_UnRegisterCallback(parcel.readString(), IKaraokeEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_Distroy = XMS_Distroy(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Distroy);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_GetToken = XMS_GetToken(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_GetToken);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_SetSignedToken = XMS_SetSignedToken(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_SetSignedToken);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_GetHandShakeStatus = XMS_GetHandShakeStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_GetHandShakeStatus);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_GetMicStatus = XMS_GetMicStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_GetMicStatus);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_GetMicPowerStatus = XMS_GetMicPowerStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_GetMicPowerStatus);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_SetVolume = XMS_SetVolume(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_SetVolume);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_GetVolume = XMS_GetVolume(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_GetVolume);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_TrackGetMinBuf = XMS_TrackGetMinBuf(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_TrackGetMinBuf);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_TrackCreate = XMS_TrackCreate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_TrackCreate);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_TrackGetLatency = XMS_TrackGetLatency(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_TrackGetLatency);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    byte[] createByteArray = parcel.createByteArray();
                    int XMS_Write = XMS_Write(readString, createByteArray, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Write);
                    parcel2.writeByteArray(createByteArray);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_TrackGetAvail = XMS_TrackGetAvail(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_TrackGetAvail);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_TrackDestroy = XMS_TrackDestroy(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_TrackDestroy);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_Pause = XMS_Pause(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Pause);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecStop = XMS_RecStop(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecStop);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecStart = XMS_RecStart(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecStart);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_Resume = XMS_Resume(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Resume);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_ResumePlay = XMS_ResumePlay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_ResumePlay);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_PausePlay = XMS_PausePlay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_PausePlay);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_SaveRec = XMS_SaveRec(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_SaveRec);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_StopSaveRec = XMS_StopSaveRec(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_StopSaveRec);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecGetMinBuf = XMS_RecGetMinBuf(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecGetMinBuf);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecCreate = XMS_RecCreate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecCreate);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecGetAvail = XMS_RecGetAvail(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecGetAvail);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_SetRecMode = XMS_SetRecMode(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_SetRecMode);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString2 = parcel.readString();
                    byte[] createByteArray2 = parcel.createByteArray();
                    int XMS_Read = XMS_Read(readString2, createByteArray2, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_Read);
                    parcel2.writeByteArray(createByteArray2);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_RecDestroy = XMS_RecDestroy(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_RecDestroy);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_MicGetMinBuf = XMS_MicGetMinBuf(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_MicGetMinBuf);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_MicCreate = XMS_MicCreate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_MicCreate);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_MicGetAvail = XMS_MicGetAvail(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_MicGetAvail);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString3 = parcel.readString();
                    byte[] createByteArray3 = parcel.createByteArray();
                    int XMS_MicRead = XMS_MicRead(readString3, createByteArray3, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_MicRead);
                    parcel2.writeByteArray(createByteArray3);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_MicDestroy = XMS_MicDestroy(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_MicDestroy);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_atlSwitch = XMS_atlSwitch(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_atlSwitch);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int XMS_aiWakeUp = XMS_aiWakeUp();
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_aiWakeUp);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean XMS_hasAtl = XMS_hasAtl();
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_hasAtl ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    String XMS_getMicName = XMS_getMicName();
                    parcel2.writeNoException();
                    parcel2.writeString(XMS_getMicName);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    XMS_setAtlEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean XMS_isAtlEnabled = XMS_isAtlEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(XMS_isAtlEnabled ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    String XMS_getBuyMicUrl = XMS_getBuyMicUrl();
                    parcel2.writeNoException();
                    parcel2.writeString(XMS_getBuyMicUrl);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    XMS_connectMicFlow();
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    XMS_ShowToast(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKaraoke {
            public static IKaraoke sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Create(String str, int i, String str2, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Create(str, i, str2, iBinder);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public void XMS_RegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iKaraokeEventListener != null ? iKaraokeEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().XMS_RegisterCallback(str, iKaraokeEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public void XMS_UnRegisterCallback(String str, IKaraokeEventListener iKaraokeEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iKaraokeEventListener != null ? iKaraokeEventListener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().XMS_UnRegisterCallback(str, iKaraokeEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Distroy(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Distroy(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_GetToken(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_GetToken(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_SetSignedToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_SetSignedToken(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_GetHandShakeStatus(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_GetHandShakeStatus(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_GetMicStatus(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_GetMicStatus(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_GetMicPowerStatus(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_GetMicPowerStatus(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_SetVolume(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_SetVolume(str, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_GetVolume(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_GetVolume(str, i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_TrackGetMinBuf(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_TrackGetMinBuf(str, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_TrackCreate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_TrackCreate(str, i, i2, i3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_TrackGetLatency(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_TrackGetLatency(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Write(String str, byte[] bArr, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Write(str, bArr, i, i2);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_TrackGetAvail(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_TrackGetAvail(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_TrackDestroy(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_TrackDestroy(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Pause(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Pause(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecStop(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecStop(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecStart(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecStart(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Resume(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Resume(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_ResumePlay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_ResumePlay(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_PausePlay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_PausePlay(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_SaveRec(String str, int i, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_SaveRec(str, i, str2, str3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_StopSaveRec(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_StopSaveRec(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecGetMinBuf(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecGetMinBuf(str, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecCreate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecCreate(str, i, i2, i3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecGetAvail(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecGetAvail(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_SetRecMode(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_SetRecMode(str, i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_Read(String str, byte[] bArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_Read(str, bArr, i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_RecDestroy(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_RecDestroy(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_MicGetMinBuf(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_MicGetMinBuf(str, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_MicCreate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_MicCreate(str, i, i2, i3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_MicGetAvail(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_MicGetAvail(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_MicRead(String str, byte[] bArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_MicRead(str, bArr, i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_MicDestroy(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_MicDestroy(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_atlSwitch(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_atlSwitch(z);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public int XMS_aiWakeUp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_aiWakeUp();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public boolean XMS_hasAtl() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_hasAtl();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public String XMS_getMicName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_getMicName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public void XMS_setAtlEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().XMS_setAtlEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public boolean XMS_isAtlEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_isAtlEnabled();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public String XMS_getBuyMicUrl() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().XMS_getBuyMicUrl();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public void XMS_connectMicFlow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().XMS_connectMicFlow();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.karaoke.IKaraoke
            public void XMS_ShowToast(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().XMS_ShowToast(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKaraoke iKaraoke) {
            if (Proxy.sDefaultImpl != null || iKaraoke == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKaraoke;
            return true;
        }

        public static IKaraoke getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

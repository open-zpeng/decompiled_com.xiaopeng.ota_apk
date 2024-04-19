package com.xiaopeng.xuimanager.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.xuimanager.smart.ISmartCommonEventListener;
import com.xiaopeng.xuimanager.smart.ISmartEventListener;
import java.util.List;
/* loaded from: classes2.dex */
public interface ISmart extends IInterface {

    /* loaded from: classes2.dex */
    public static class Default implements ISmart {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void enableCarSpeedVolume(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getAlarmFromAzimuthOrIcm() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getAlarmVolume() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getBootSoundEffect() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getBossKeyForCustomer() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getFftLLUEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getKeyBoardTouchPrompt() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getLangLightEffectEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public List<String> getLangLightEffectNameList(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getLightEffect(int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getLluSleepMode() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getLluWakeWaitMode() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public String getRunnningLluEffectName() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getSayHiEffect() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getSayHiEnable() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getSpeechStatus() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getTouchRotationDirection() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getVolumeDownInReverseMode() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean getVolumeDownWithDoorOpen() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int getXKeyForCustomer() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int isLightDanceAvailable() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public boolean isLightDancing() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void registerCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void registerListener(ISmartEventListener iSmartEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setAlarmFromAzimuthOrIcm(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setAlarmVolume(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setBootSoundEffect(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setBossKeyForCustomer(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setFftLLUEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setKeyBoardTouchPrompt(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLangLightEffectEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLangLightEffectMode(String str, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLangLightEffectWithMode(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLangLightMusicEffect(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLightEffect(int i, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLightEffectAndMusic(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLluSleepMode(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setLluWakeWaitMode(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setMusicDelayTimeForDebug(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setMusicSpectrumToLangLight(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setMusicStartTickNumForDebug(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setMusicStopTickNumForDebug(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setMusicTableForDebug(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setPause(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setSayHiEffect(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setSayHiEnable(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setSpeedVolumeMode(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setTouchRotationDirection(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setVolumeDownInReverseMode(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setVolumeDownWithDoorOpen(boolean z) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void setXKeyForCustomer(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public String speakByImportant(String str) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public String speakByInstant(String str, boolean z) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public String speakByNormal(String str) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public String speakByUrgent(String str) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void startAiLluMode(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int startLightDancing(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int startMicRecordMode(int i) throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void stopAiLluMode() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void stopAllSpeech() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int stopLightDancing() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void stopLightEffectShow() throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public int stopMicRecordMode() throws RemoteException {
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void stopSpeech(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void unregisterCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void unregisterListener(ISmartEventListener iSmartEventListener) throws RemoteException {
        }

        @Override // com.xiaopeng.xuimanager.smart.ISmart
        public void updateEffectFiles(int i) throws RemoteException {
        }
    }

    void enableCarSpeedVolume(boolean z) throws RemoteException;

    boolean getAlarmFromAzimuthOrIcm() throws RemoteException;

    int getAlarmVolume() throws RemoteException;

    int getBootSoundEffect() throws RemoteException;

    int getBossKeyForCustomer() throws RemoteException;

    boolean getFftLLUEnable() throws RemoteException;

    boolean getKeyBoardTouchPrompt() throws RemoteException;

    boolean getLangLightEffectEnable() throws RemoteException;

    List<String> getLangLightEffectNameList(int i) throws RemoteException;

    int getLightEffect(int i) throws RemoteException;

    int getLluSleepMode() throws RemoteException;

    int getLluWakeWaitMode() throws RemoteException;

    String getRunnningLluEffectName() throws RemoteException;

    int getSayHiEffect() throws RemoteException;

    boolean getSayHiEnable() throws RemoteException;

    boolean getSpeechStatus() throws RemoteException;

    int getTouchRotationDirection() throws RemoteException;

    boolean getVolumeDownInReverseMode() throws RemoteException;

    boolean getVolumeDownWithDoorOpen() throws RemoteException;

    int getXKeyForCustomer() throws RemoteException;

    int isLightDanceAvailable() throws RemoteException;

    boolean isLightDancing() throws RemoteException;

    void registerCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException;

    void registerListener(ISmartEventListener iSmartEventListener) throws RemoteException;

    void setAlarmFromAzimuthOrIcm(boolean z) throws RemoteException;

    void setAlarmVolume(int i) throws RemoteException;

    void setBootSoundEffect(int i) throws RemoteException;

    void setBossKeyForCustomer(int i) throws RemoteException;

    void setFftLLUEnable(boolean z) throws RemoteException;

    void setKeyBoardTouchPrompt(boolean z) throws RemoteException;

    void setLangLightEffectEnable(boolean z) throws RemoteException;

    void setLangLightEffectMode(String str, int i) throws RemoteException;

    void setLangLightEffectWithMode(String str, int i, int i2) throws RemoteException;

    void setLangLightMusicEffect(String str) throws RemoteException;

    void setLightEffect(int i, int i2) throws RemoteException;

    void setLightEffectAndMusic(int i, int i2, int i3) throws RemoteException;

    void setLluSleepMode(int i) throws RemoteException;

    void setLluWakeWaitMode(int i) throws RemoteException;

    void setMusicDelayTimeForDebug(int i) throws RemoteException;

    void setMusicSpectrumToLangLight(int i) throws RemoteException;

    void setMusicStartTickNumForDebug(int i) throws RemoteException;

    void setMusicStopTickNumForDebug(int i) throws RemoteException;

    void setMusicTableForDebug(int i) throws RemoteException;

    void setPause(boolean z) throws RemoteException;

    void setSayHiEffect(int i) throws RemoteException;

    void setSayHiEnable(boolean z) throws RemoteException;

    void setSpeedVolumeMode(int i) throws RemoteException;

    void setTouchRotationDirection(int i) throws RemoteException;

    void setVolumeDownInReverseMode(boolean z) throws RemoteException;

    void setVolumeDownWithDoorOpen(boolean z) throws RemoteException;

    void setXKeyForCustomer(int i) throws RemoteException;

    String speakByImportant(String str) throws RemoteException;

    String speakByInstant(String str, boolean z) throws RemoteException;

    String speakByNormal(String str) throws RemoteException;

    String speakByUrgent(String str) throws RemoteException;

    void startAiLluMode(int i) throws RemoteException;

    int startLightDancing(String str, int i) throws RemoteException;

    int startMicRecordMode(int i) throws RemoteException;

    void stopAiLluMode() throws RemoteException;

    void stopAllSpeech() throws RemoteException;

    int stopLightDancing() throws RemoteException;

    void stopLightEffectShow() throws RemoteException;

    int stopMicRecordMode() throws RemoteException;

    void stopSpeech(String str) throws RemoteException;

    void unregisterCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException;

    void unregisterListener(ISmartEventListener iSmartEventListener) throws RemoteException;

    void updateEffectFiles(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISmart {
        private static final String DESCRIPTOR = "com.xiaopeng.xuimanager.smart.ISmart";
        static final int TRANSACTION_enableCarSpeedVolume = 50;
        static final int TRANSACTION_getAlarmFromAzimuthOrIcm = 8;
        static final int TRANSACTION_getAlarmVolume = 29;
        static final int TRANSACTION_getBootSoundEffect = 40;
        static final int TRANSACTION_getBossKeyForCustomer = 14;
        static final int TRANSACTION_getFftLLUEnable = 46;
        static final int TRANSACTION_getKeyBoardTouchPrompt = 10;
        static final int TRANSACTION_getLangLightEffectEnable = 26;
        static final int TRANSACTION_getLangLightEffectNameList = 17;
        static final int TRANSACTION_getLightEffect = 41;
        static final int TRANSACTION_getLluSleepMode = 24;
        static final int TRANSACTION_getLluWakeWaitMode = 22;
        static final int TRANSACTION_getRunnningLluEffectName = 52;
        static final int TRANSACTION_getSayHiEffect = 38;
        static final int TRANSACTION_getSayHiEnable = 36;
        static final int TRANSACTION_getSpeechStatus = 59;
        static final int TRANSACTION_getTouchRotationDirection = 16;
        static final int TRANSACTION_getVolumeDownInReverseMode = 6;
        static final int TRANSACTION_getVolumeDownWithDoorOpen = 4;
        static final int TRANSACTION_getXKeyForCustomer = 12;
        static final int TRANSACTION_isLightDanceAvailable = 54;
        static final int TRANSACTION_isLightDancing = 49;
        static final int TRANSACTION_registerCommonListener = 55;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setAlarmFromAzimuthOrIcm = 7;
        static final int TRANSACTION_setAlarmVolume = 28;
        static final int TRANSACTION_setBootSoundEffect = 39;
        static final int TRANSACTION_setBossKeyForCustomer = 13;
        static final int TRANSACTION_setFftLLUEnable = 45;
        static final int TRANSACTION_setKeyBoardTouchPrompt = 9;
        static final int TRANSACTION_setLangLightEffectEnable = 25;
        static final int TRANSACTION_setLangLightEffectMode = 18;
        static final int TRANSACTION_setLangLightEffectWithMode = 53;
        static final int TRANSACTION_setLangLightMusicEffect = 34;
        static final int TRANSACTION_setLightEffect = 42;
        static final int TRANSACTION_setLightEffectAndMusic = 43;
        static final int TRANSACTION_setLluSleepMode = 23;
        static final int TRANSACTION_setLluWakeWaitMode = 21;
        static final int TRANSACTION_setMusicDelayTimeForDebug = 33;
        static final int TRANSACTION_setMusicSpectrumToLangLight = 27;
        static final int TRANSACTION_setMusicStartTickNumForDebug = 31;
        static final int TRANSACTION_setMusicStopTickNumForDebug = 32;
        static final int TRANSACTION_setMusicTableForDebug = 30;
        static final int TRANSACTION_setPause = 19;
        static final int TRANSACTION_setSayHiEffect = 37;
        static final int TRANSACTION_setSayHiEnable = 35;
        static final int TRANSACTION_setSpeedVolumeMode = 51;
        static final int TRANSACTION_setTouchRotationDirection = 15;
        static final int TRANSACTION_setVolumeDownInReverseMode = 5;
        static final int TRANSACTION_setVolumeDownWithDoorOpen = 3;
        static final int TRANSACTION_setXKeyForCustomer = 11;
        static final int TRANSACTION_speakByImportant = 61;
        static final int TRANSACTION_speakByInstant = 63;
        static final int TRANSACTION_speakByNormal = 60;
        static final int TRANSACTION_speakByUrgent = 62;
        static final int TRANSACTION_startAiLluMode = 57;
        static final int TRANSACTION_startLightDancing = 47;
        static final int TRANSACTION_startMicRecordMode = 64;
        static final int TRANSACTION_stopAiLluMode = 58;
        static final int TRANSACTION_stopAllSpeech = 67;
        static final int TRANSACTION_stopLightDancing = 48;
        static final int TRANSACTION_stopLightEffectShow = 44;
        static final int TRANSACTION_stopMicRecordMode = 65;
        static final int TRANSACTION_stopSpeech = 66;
        static final int TRANSACTION_unregisterCommonListener = 56;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_updateEffectFiles = 20;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmart asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmart)) {
                return (ISmart) queryLocalInterface;
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
                    registerListener(ISmartEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(ISmartEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolumeDownWithDoorOpen(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volumeDownWithDoorOpen = getVolumeDownWithDoorOpen();
                    parcel2.writeNoException();
                    parcel2.writeInt(volumeDownWithDoorOpen ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolumeDownInReverseMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volumeDownInReverseMode = getVolumeDownInReverseMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(volumeDownInReverseMode ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAlarmFromAzimuthOrIcm(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean alarmFromAzimuthOrIcm = getAlarmFromAzimuthOrIcm();
                    parcel2.writeNoException();
                    parcel2.writeInt(alarmFromAzimuthOrIcm ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setKeyBoardTouchPrompt(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean keyBoardTouchPrompt = getKeyBoardTouchPrompt();
                    parcel2.writeNoException();
                    parcel2.writeInt(keyBoardTouchPrompt ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setXKeyForCustomer(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int xKeyForCustomer = getXKeyForCustomer();
                    parcel2.writeNoException();
                    parcel2.writeInt(xKeyForCustomer);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBossKeyForCustomer(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bossKeyForCustomer = getBossKeyForCustomer();
                    parcel2.writeNoException();
                    parcel2.writeInt(bossKeyForCustomer);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTouchRotationDirection(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int touchRotationDirection = getTouchRotationDirection();
                    parcel2.writeNoException();
                    parcel2.writeInt(touchRotationDirection);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> langLightEffectNameList = getLangLightEffectNameList(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(langLightEffectNameList);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLangLightEffectMode(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPause(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateEffectFiles(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLluWakeWaitMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lluWakeWaitMode = getLluWakeWaitMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(lluWakeWaitMode);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLluSleepMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lluSleepMode = getLluSleepMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(lluSleepMode);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLangLightEffectEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean langLightEffectEnable = getLangLightEffectEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(langLightEffectEnable ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicSpectrumToLangLight(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAlarmVolume(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int alarmVolume = getAlarmVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(alarmVolume);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicTableForDebug(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicStartTickNumForDebug(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicStopTickNumForDebug(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicDelayTimeForDebug(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLangLightMusicEffect(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSayHiEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean sayHiEnable = getSayHiEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(sayHiEnable ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSayHiEffect(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sayHiEffect = getSayHiEffect();
                    parcel2.writeNoException();
                    parcel2.writeInt(sayHiEffect);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBootSoundEffect(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bootSoundEffect = getBootSoundEffect();
                    parcel2.writeNoException();
                    parcel2.writeInt(bootSoundEffect);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lightEffect = getLightEffect(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(lightEffect);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLightEffect(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLightEffectAndMusic(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopLightEffectShow();
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFftLLUEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean fftLLUEnable = getFftLLUEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(fftLLUEnable ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    int startLightDancing = startLightDancing(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(startLightDancing);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int stopLightDancing = stopLightDancing();
                    parcel2.writeNoException();
                    parcel2.writeInt(stopLightDancing);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isLightDancing = isLightDancing();
                    parcel2.writeNoException();
                    parcel2.writeInt(isLightDancing ? 1 : 0);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableCarSpeedVolume(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSpeedVolumeMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    String runnningLluEffectName = getRunnningLluEffectName();
                    parcel2.writeNoException();
                    parcel2.writeString(runnningLluEffectName);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLangLightEffectWithMode(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int isLightDanceAvailable = isLightDanceAvailable();
                    parcel2.writeNoException();
                    parcel2.writeInt(isLightDanceAvailable);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCommonListener(ISmartCommonEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCommonListener(ISmartCommonEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    startAiLluMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopAiLluMode();
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean speechStatus = getSpeechStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(speechStatus ? 1 : 0);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speakByNormal = speakByNormal(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(speakByNormal);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speakByImportant = speakByImportant(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(speakByImportant);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speakByUrgent = speakByUrgent(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(speakByUrgent);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speakByInstant = speakByInstant(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeString(speakByInstant);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int startMicRecordMode = startMicRecordMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(startMicRecordMode);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int stopMicRecordMode = stopMicRecordMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(stopMicRecordMode);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopSpeech(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopAllSpeech();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISmart {
            public static ISmart sDefaultImpl;
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

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void registerListener(ISmartEventListener iSmartEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSmartEventListener != null ? iSmartEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iSmartEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void unregisterListener(ISmartEventListener iSmartEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSmartEventListener != null ? iSmartEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iSmartEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setVolumeDownWithDoorOpen(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVolumeDownWithDoorOpen(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getVolumeDownWithDoorOpen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeDownWithDoorOpen();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setVolumeDownInReverseMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVolumeDownInReverseMode(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getVolumeDownInReverseMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeDownInReverseMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setAlarmFromAzimuthOrIcm(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAlarmFromAzimuthOrIcm(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getAlarmFromAzimuthOrIcm() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlarmFromAzimuthOrIcm();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setKeyBoardTouchPrompt(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setKeyBoardTouchPrompt(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getKeyBoardTouchPrompt() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyBoardTouchPrompt();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setXKeyForCustomer(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setXKeyForCustomer(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getXKeyForCustomer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getXKeyForCustomer();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setBossKeyForCustomer(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBossKeyForCustomer(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getBossKeyForCustomer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBossKeyForCustomer();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setTouchRotationDirection(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTouchRotationDirection(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getTouchRotationDirection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTouchRotationDirection();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public List<String> getLangLightEffectNameList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLangLightEffectNameList(i);
                    }
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLangLightEffectMode(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLangLightEffectMode(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setPause(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPause(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void updateEffectFiles(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateEffectFiles(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLluWakeWaitMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLluWakeWaitMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getLluWakeWaitMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLluWakeWaitMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLluSleepMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLluSleepMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getLluSleepMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLluSleepMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLangLightEffectEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLangLightEffectEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getLangLightEffectEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLangLightEffectEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setMusicSpectrumToLangLight(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicSpectrumToLangLight(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setAlarmVolume(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAlarmVolume(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getAlarmVolume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlarmVolume();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setMusicTableForDebug(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicTableForDebug(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setMusicStartTickNumForDebug(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicStartTickNumForDebug(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setMusicStopTickNumForDebug(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicStopTickNumForDebug(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setMusicDelayTimeForDebug(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicDelayTimeForDebug(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLangLightMusicEffect(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLangLightMusicEffect(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setSayHiEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSayHiEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getSayHiEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSayHiEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setSayHiEffect(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSayHiEffect(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getSayHiEffect() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSayHiEffect();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setBootSoundEffect(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBootSoundEffect(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getBootSoundEffect() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBootSoundEffect();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int getLightEffect(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightEffect(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLightEffect(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLightEffect(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLightEffectAndMusic(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.mRemote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLightEffectAndMusic(i, i2, i3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void stopLightEffectShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopLightEffectShow();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setFftLLUEnable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFftLLUEnable(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getFftLLUEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFftLLUEnable();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int startLightDancing(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startLightDancing(str, i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int stopLightDancing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopLightDancing();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean isLightDancing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(49, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLightDancing();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void enableCarSpeedVolume(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableCarSpeedVolume(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setSpeedVolumeMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(51, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSpeedVolumeMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public String getRunnningLluEffectName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(52, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRunnningLluEffectName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void setLangLightEffectWithMode(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(53, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLangLightEffectWithMode(str, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int isLightDanceAvailable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(54, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLightDanceAvailable();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void registerCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSmartCommonEventListener != null ? iSmartCommonEventListener.asBinder() : null);
                    if (!this.mRemote.transact(55, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCommonListener(iSmartCommonEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void unregisterCommonListener(ISmartCommonEventListener iSmartCommonEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSmartCommonEventListener != null ? iSmartCommonEventListener.asBinder() : null);
                    if (!this.mRemote.transact(56, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCommonListener(iSmartCommonEventListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void startAiLluMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(57, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAiLluMode(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void stopAiLluMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(58, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAiLluMode();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public boolean getSpeechStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(59, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeechStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public String speakByNormal(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(60, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().speakByNormal(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public String speakByImportant(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(61, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().speakByImportant(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public String speakByUrgent(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(62, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().speakByUrgent(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public String speakByInstant(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(63, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().speakByInstant(str, z);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int startMicRecordMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(64, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startMicRecordMode(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public int stopMicRecordMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(65, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopMicRecordMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void stopSpeech(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(66, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopSpeech(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.xuimanager.smart.ISmart
            public void stopAllSpeech() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(67, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAllSpeech();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmart iSmart) {
            if (Proxy.sDefaultImpl != null || iSmart == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmart;
            return true;
        }

        public static ISmart getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}

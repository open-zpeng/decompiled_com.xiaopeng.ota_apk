package com.xiaopeng.speech.protocol.query.speech;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechQueryEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SpeechSensorQuery extends SpeechQuery<ISpeechQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SOUND_LOCATION)
    public int getSoundLocation(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getSoundLocation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_APP_FOREGROUND)
    public boolean isAppForeground(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("package");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "";
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).isAppForeground(str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_ACCOUNT_LOGIN)
    public boolean isAccountLogin(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).isAccountLogin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_WAKEUP_ENABLE)
    public boolean isEnableGlobalWakeup(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).isEnableGlobalWakeup();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.GET_CURRENT_MODE)
    public int getCurrentMode(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getCurrentMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.GET_CAR_PLATFORM)
    public String getCarPlatform(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getCarPlatForm();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.GET_SCENE_SWITCH_STATUS)
    public int getVuiSceneSwitchStatus(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getVuiSceneSwitchStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.GET_FIRST_SPEECH_STATUS)
    public int getFirstSpeechState(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getFirstSpeechState();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.CURRENT_TTS_ENGINE)
    public int getCurrentTtsEngine(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).getCurrentTtsEngine();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.APP_IS_INSTALLED)
    public boolean appIsInstalled(String str, String str2) {
        LogUtils.d("SpeechUiQuery", "enter appIsInstalled , event = " + str + ", data = " + str2);
        try {
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            return ((ISpeechQueryCaller) this.mQueryCaller).appIsInstalled(new JSONObject(str2).optString("packageName", ""));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_USEREXPRESSION_OPENED)
    public boolean isUserExpressionOpened(String str, String str2) {
        return ((ISpeechQueryCaller) this.mQueryCaller).isUserExpressionOpened();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_NAPA_TOP)
    public boolean isNapaTop(String str, String str2) {
        int i = -1;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).isNapaTop(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_NAVI_TOP)
    public boolean isNaviTop(String str, String str2) {
        int i = -1;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).isNaviTop(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_SCREEN_ON)
    public boolean is(String str, String str2) {
        int i = -1;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).isScreenOn(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.GET_CAR_LEVEL)
    public int getCfcVehicleLevel() {
        return ((ISpeechQueryCaller) this.mQueryCaller).getCfcVehicleLevel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SUPPORT_FAST_SPEECH)
    public int ifSupportFastSpeech() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifSupportFastSpeech();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SUPPORT_WAIT_AWAKE)
    public int ifSupportWaitAwake() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifSupportWaitAwake();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SUPPORT_MULTI_SPEECH)
    public int ifSupportMulti() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifSupportMulti();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_SUPPROT_XSPORT)
    public boolean isSupportXSport() {
        return ((ISpeechQueryCaller) this.mQueryCaller).isSupportXSport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_FAST_SPEECH_OPEN)
    public int isFastSpeechOpen() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifFastSpeechEnable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_MULTI_SPEECH_OPEN)
    public int isMultiSpeechOpen() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifMultiSpeechEnable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IS_FULL_TIME_SPEECH_OPEN)
    public int isFullTimeSpeechOpen() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifFullTimeSpeechEnable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.IF_ADVANCE_SETTINGS_OPEN)
    public int isAdvancedSettingsOpen() {
        return ((ISpeechQueryCaller) this.mQueryCaller).ifAdvancedSettingsOpen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.CAR_HAS_SCREEN)
    public int carHasScreen(String str, String str2) {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).carHasScreen(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SCREEN_IS_DESKTOP)
    public int screenisDesktop(String str, String str2) {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).screenisDesktop(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechQueryEvent.SCREEN_GO_HOME)
    public int screenGoHome(String str, String str2) {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(str2)) {
                i = new JSONObject(str2).optInt("display_location");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((ISpeechQueryCaller) this.mQueryCaller).screenGoHome(i);
    }
}

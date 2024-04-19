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
}

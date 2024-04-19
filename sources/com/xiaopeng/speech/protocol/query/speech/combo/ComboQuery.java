package com.xiaopeng.speech.protocol.query.speech.combo;

import android.os.PowerManager;
import android.text.TextUtils;
import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.speech.ComboQueryEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ComboQuery extends SpeechQuery<IComboQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_ENTER_USERMODE)
    public String enterMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).enterUserMode(getModeFromJson(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_EXIT_USERMODE)
    public String exitMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).exitUserMode(getModeFromJson(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_CHECK_ENTER_USERMODE)
    public String checkEnterUserMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).checkEnterUserMode(getModeFromJson(str2));
    }

    private String getModeFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has(PowerManager.EXTRA_POWER_SAVE_MODE) ? jSONObject.optString(PowerManager.EXTRA_POWER_SAVE_MODE) : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_GET_CURRENT_USERMODE)
    public String getCurrentUserMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).getCurrentUserMode();
    }
}

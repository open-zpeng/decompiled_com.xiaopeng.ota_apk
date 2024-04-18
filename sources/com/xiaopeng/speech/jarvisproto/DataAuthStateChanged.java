package com.xiaopeng.speech.jarvisproto;

import com.xiaopeng.ota.Config;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DataAuthStateChanged extends JarvisProto {
    public static final String EVENT = "jarvis.data.auth.state.changed";
    public boolean state;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.KEY_REASON, this.state);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}

package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AsrEvent extends JarvisProto {
    public static final String EVENT = "jarvis.asr.event";
    public static final int STATE_ASR_BEGIN = 1;
    public static final int STATE_ASR_END = 2;
    public static final int STATE_START_LISTEN = 0;
    public int mEvent;
    public int soundArea;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public AsrEvent() {
        this.mEvent = -1;
        this.soundArea = -1;
    }

    public AsrEvent(int i) {
        this.mEvent = -1;
        this.soundArea = -1;
        this.mEvent = i;
    }

    public AsrEvent(int i, int i2) {
        this.mEvent = -1;
        this.soundArea = -1;
        this.mEvent = i;
        this.soundArea = i2;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", this.mEvent);
            jSONObject.put("soundArea", this.soundArea);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static AsrEvent fromJson(String str) {
        int i;
        int i2 = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.optInt("event");
            try {
                i2 = jSONObject.optInt("soundArea");
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                return new AsrEvent(i, i2);
            }
        } catch (JSONException e2) {
            e = e2;
            i = -1;
        }
        return new AsrEvent(i, i2);
    }
}

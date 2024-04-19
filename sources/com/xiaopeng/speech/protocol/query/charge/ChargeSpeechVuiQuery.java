package com.xiaopeng.speech.protocol.query.charge;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.speech.ChargeSpeechVuiEvent;
import com.xiaopeng.speech.protocol.query.speech.vui.ISpeechVuiElementStateQueryCaller;
import com.xiaopeng.util.FeatureOption;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ChargeSpeechVuiQuery extends SpeechQuery<ISpeechVuiElementStateQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_XSWITCH_CHECKED)
    public boolean isSwitchChecked(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                if (jSONObject2.has("value")) {
                    return jSONObject2.optJSONObject("value").optJSONObject("SetCheck").optBoolean("value", false);
                }
                if (jSONObject2.has("values")) {
                    return jSONObject2.optJSONObject("values").optJSONObject("SetCheck").optBoolean("value", false);
                }
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_XTABLAYOUT_SELECTED)
    public boolean isTableLayoutSelected(String str, String str2) {
        int optInt;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int optInt2 = jSONObject.optInt("index", 0);
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), jSONObject.optString("elementId", ""));
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                JSONObject jSONObject3 = null;
                if (jSONObject2.has("value")) {
                    jSONObject3 = jSONObject2.optJSONObject("value");
                } else if (jSONObject2.has("values")) {
                    jSONObject3 = jSONObject2.optJSONObject("values");
                }
                if (jSONObject3 != null) {
                    if (jSONObject3.has("SetValue")) {
                        optInt = jSONObject3.optJSONObject("SetValue").optInt("value", 0);
                    } else {
                        optInt = jSONObject3.has("SelectTab") ? jSONObject3.optJSONObject("SelectTab").optInt("value", 0) : 0;
                    }
                    return optInt == optInt2;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_XSLIDER_SETVALUE)
    public int getXSliderIndex(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (TextUtils.isEmpty(vuiElementState)) {
                return 0;
            }
            JSONObject jSONObject2 = new JSONObject(vuiElementState);
            boolean has = jSONObject2.has("value");
            double d = FeatureOption.FO_BOOT_POLICY_CPU;
            if (has) {
                d = jSONObject2.optJSONObject("value").optJSONObject("SetValue").optDouble("value", FeatureOption.FO_BOOT_POLICY_CPU);
            } else if (jSONObject2.has("values")) {
                d = jSONObject2.optJSONObject("values").optJSONObject("SetValue").optDouble("value", FeatureOption.FO_BOOT_POLICY_CPU);
            }
            return (int) d;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_STATEFULBUTTON_CHECKED)
    public boolean isStatefulButtonChecked(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                if (jSONObject2.has("value")) {
                    return jSONObject2.optJSONObject("value").optJSONObject("SetCheck").optBoolean("value", false);
                }
                if (jSONObject2.has("values")) {
                    return jSONObject2.optJSONObject("values").optJSONObject("SetCheck").optBoolean("value", false);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_STATEFULBUTTON_SETVALUE)
    public String getStatefulButtonValue(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                if (jSONObject2.has("value")) {
                    return jSONObject2.optJSONObject("value").optJSONObject("SetValue").optString("value", null);
                }
                if (jSONObject2.has("values")) {
                    return jSONObject2.optJSONObject("values").optJSONObject("SetValue").optString("value", null);
                }
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_ELEMENT_ENABLED)
    public boolean isElementEnabled(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (TextUtils.isEmpty(vuiElementState)) {
                return false;
            }
            return new JSONObject(vuiElementState).optBoolean("enabled", true);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0074, code lost:
        if (r2 == 1) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0076, code lost:
        if (r2 == 2) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
        if (r2 == 3) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007b, code lost:
        r10 = "canScrollDown";
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007e, code lost:
        r10 = "canScrollRight";
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0081, code lost:
        r10 = "canScrollUp";
     */
    @com.xiaopeng.speech.annotation.QueryAnnotation(event = com.xiaopeng.speech.protocol.event.query.speech.ChargeSpeechVuiEvent.CHARGE_VUI_ELEMENT_SCROLL_STATE)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isElementCanScrolled(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r9 = ""
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8b
            r1.<init>(r10)     // Catch: org.json.JSONException -> L8b
            java.lang.String r10 = "elementId"
            java.lang.String r10 = r1.optString(r10, r9)     // Catch: org.json.JSONException -> L8b
            java.lang.String r2 = "sceneId"
            java.lang.String r2 = r1.optString(r2, r9)     // Catch: org.json.JSONException -> L8b
            java.lang.String r3 = "direction"
            java.lang.String r9 = r1.optString(r3, r9)     // Catch: org.json.JSONException -> L8b
            T r1 = r8.mQueryCaller     // Catch: org.json.JSONException -> L8b
            com.xiaopeng.speech.protocol.query.speech.vui.ISpeechVuiElementStateQueryCaller r1 = (com.xiaopeng.speech.protocol.query.speech.vui.ISpeechVuiElementStateQueryCaller) r1     // Catch: org.json.JSONException -> L8b
            java.lang.String r10 = r1.getVuiElementState(r2, r10)     // Catch: org.json.JSONException -> L8b
            boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch: org.json.JSONException -> L8b
            if (r1 != 0) goto L8f
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8b
            r1.<init>(r10)     // Catch: org.json.JSONException -> L8b
            r10 = 0
            r2 = -1
            int r3 = r9.hashCode()     // Catch: org.json.JSONException -> L8b
            r4 = 3739(0xe9b, float:5.24E-42)
            r5 = 3
            r6 = 2
            r7 = 1
            if (r3 == r4) goto L68
            r4 = 3089570(0x2f24a2, float:4.32941E-39)
            if (r3 == r4) goto L5e
            r4 = 3317767(0x32a007, float:4.649182E-39)
            if (r3 == r4) goto L54
            r4 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r3 == r4) goto L4a
            goto L72
        L4a:
            java.lang.String r3 = "right"
            boolean r9 = r9.equals(r3)     // Catch: org.json.JSONException -> L8b
            if (r9 == 0) goto L72
            r2 = r6
            goto L72
        L54:
            java.lang.String r3 = "left"
            boolean r9 = r9.equals(r3)     // Catch: org.json.JSONException -> L8b
            if (r9 == 0) goto L72
            r2 = r0
            goto L72
        L5e:
            java.lang.String r3 = "down"
            boolean r9 = r9.equals(r3)     // Catch: org.json.JSONException -> L8b
            if (r9 == 0) goto L72
            r2 = r5
            goto L72
        L68:
            java.lang.String r3 = "up"
            boolean r9 = r9.equals(r3)     // Catch: org.json.JSONException -> L8b
            if (r9 == 0) goto L72
            r2 = r7
        L72:
            if (r2 == 0) goto L84
            if (r2 == r7) goto L81
            if (r2 == r6) goto L7e
            if (r2 == r5) goto L7b
            goto L86
        L7b:
            java.lang.String r10 = "canScrollDown"
            goto L86
        L7e:
            java.lang.String r10 = "canScrollRight"
            goto L86
        L81:
            java.lang.String r10 = "canScrollUp"
            goto L86
        L84:
            java.lang.String r10 = "canScrollLeft"
        L86:
            boolean r9 = r1.optBoolean(r10, r7)     // Catch: org.json.JSONException -> L8b
            return r9
        L8b:
            r9 = move-exception
            r9.printStackTrace()
        L8f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.query.charge.ChargeSpeechVuiQuery.isElementCanScrolled(java.lang.String, java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_ELEMENT_CHECKBOX_CHECKED)
    public boolean isCheckBoxChecked(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                if (jSONObject2.has("value")) {
                    return jSONObject2.optJSONObject("value").optJSONObject("SetCheck").optBoolean("value", false);
                }
                if (jSONObject2.has("values")) {
                    return jSONObject2.optJSONObject("values").optJSONObject("SetCheck").optBoolean("value", false);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ChargeSpeechVuiEvent.CHARGE_VUI_ELEMENT_RADIOBUTTON_CHECKED)
    public boolean isRaduoButtonChecked(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("elementId", "");
            String vuiElementState = ((ISpeechVuiElementStateQueryCaller) this.mQueryCaller).getVuiElementState(jSONObject.optString("sceneId", ""), optString);
            if (!TextUtils.isEmpty(vuiElementState)) {
                JSONObject jSONObject2 = new JSONObject(vuiElementState);
                if (jSONObject2.has("value")) {
                    return jSONObject2.optJSONObject("value").optJSONObject("SetCheck").optBoolean("value", false);
                }
                if (jSONObject2.has("values")) {
                    return jSONObject2.optJSONObject("values").optJSONObject("SetCheck").optBoolean("value", false);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}

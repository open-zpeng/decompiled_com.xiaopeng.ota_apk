package com.xiaopeng.speech.protocol.node.internal;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.SpeechEvent;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.event.InternalEvent;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InternalNode extends SpeechNode<InternalListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = InternalEvent.DM_OUTPUT)
    public void onDmOutput(String str, String str2) {
        try {
            JSONObject optJSONObject = new JSONObject(str2).optJSONObject("dm");
            if (optJSONObject.optString("dataFrom", "").equals("native")) {
                String optString = optJSONObject.optString(SpeechWidget.DATA_SOURCE_API);
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                String str3 = SpeechEvent.NATIVE_API_SCHEME + optString;
            }
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(WakeupResult.REASON_COMMAND);
            if (optJSONObject2 != null) {
                String optString2 = optJSONObject2.optString(SpeechWidget.DATA_SOURCE_API);
                if (!TextUtils.isEmpty(optString2)) {
                    String str4 = SpeechEvent.COMMAND_SCHEME + optString2;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((InternalListener) obj).onDmOutput(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = InternalEvent.INPUT_DM_DATA)
    public void onInputDmData(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((InternalListener) obj).onInputDmData(str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044 A[ORIG_RETURN, RETURN] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.InternalEvent.LOCAL_WAKEUP_RESULT)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLocalWakeupResult(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r5 = ""
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L21
            r0.<init>(r6)     // Catch: org.json.JSONException -> L21
            java.lang.String r6 = "type"
            java.lang.String r6 = r0.optString(r6)     // Catch: org.json.JSONException -> L21
            java.lang.String r1 = "word"
            java.lang.String r1 = r0.optString(r1)     // Catch: org.json.JSONException -> L1e
            java.lang.String r2 = "channel"
            java.lang.String r5 = r0.optString(r2)     // Catch: org.json.JSONException -> L1c
            goto L27
        L1c:
            r0 = move-exception
            goto L24
        L1e:
            r0 = move-exception
            r1 = r5
            goto L24
        L21:
            r0 = move-exception
            r6 = r5
            r1 = r6
        L24:
            r0.printStackTrace()
        L27:
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r0 = r4.mListenerList
            java.lang.Object[] r0 = r0.collectCallbacks()
            if (r0 == 0) goto L44
            r2 = 0
        L30:
            int r3 = r0.length
            if (r2 >= r3) goto L44
            r3 = r0[r2]
            com.xiaopeng.speech.protocol.node.internal.InternalListener r3 = (com.xiaopeng.speech.protocol.node.internal.InternalListener) r3
            r3.onLocalWakeupResult(r6, r1)
            r3 = r0[r2]
            com.xiaopeng.speech.protocol.node.internal.InternalListener r3 = (com.xiaopeng.speech.protocol.node.internal.InternalListener) r3
            r3.onLocalWakeupResultWithChannel(r6, r1, r5)
            int r2 = r2 + 1
            goto L30
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.internal.InternalNode.onLocalWakeupResult(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = InternalEvent.RESOURCE_UPDATE_FINISH)
    public void onResourceUpdateFinish(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((InternalListener) obj).onResourceUpdateFinish(str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = InternalEvent.REBOOT_COMPLETE)
    public void onRebootComplete(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((InternalListener) obj).onRebootComplete(str, str2);
            }
        }
    }
}

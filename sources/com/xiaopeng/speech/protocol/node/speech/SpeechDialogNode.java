package com.xiaopeng.speech.protocol.node.speech;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.SpeechDialogEvent;
/* loaded from: classes2.dex */
public class SpeechDialogNode extends SpeechNode<SpeechDialogListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_CLOSE_SPEECH_WINDOW)
    public void onCloseWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_OPEN_SPEECH_WINDOW)
    public void onOpenWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_OPEN_SCENE_GUIDE_WINDOW)
    public void onOpenSceneGuideWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSceneGuideWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_CLOSE_SCENE_GUIDE_WINDOW)
    public void onCloseSceneGuideWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSceneGuideWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_ENABLE)
    public void onOpenSpeechSceneSetting(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSpeechSceneSetting();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_DISABLED)
    public void onCloseSpeechSceneSetting(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSpeechSceneSetting();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_OPEN)
    public void onOpenSuperDialogue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSuperDialogue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_CLOSE)
    public void onCloseSuperDialogue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSuperDialogue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024 A[LOOP:0: B:9:0x0024->B:11:0x0027, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v2 int) binds: [B:8:0x0022, B:11:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.SpeechDialogEvent.REFRESH_CARSPEECHSERVICE_UI)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onRefreshUi(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r4 = r3.mListenerList
            java.lang.Object[] r4 = r4.collectCallbacks()
            r0 = -1
            r1 = 0
            boolean r2 = com.xiaopeng.speech.protocol.SpeechUtils.isJson(r5)     // Catch: java.lang.Exception -> L21
            if (r2 == 0) goto L21
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L21
            r2.<init>(r5)     // Catch: java.lang.Exception -> L21
            java.lang.String r5 = "type"
            int r0 = r2.optInt(r5, r0)     // Catch: java.lang.Exception -> L21
            java.lang.String r5 = "state"
            boolean r5 = r2.optBoolean(r5, r1)     // Catch: java.lang.Exception -> L21
            goto L22
        L21:
            r5 = r1
        L22:
            if (r4 == 0) goto L31
        L24:
            int r2 = r4.length
            if (r1 >= r2) goto L31
            r2 = r4[r1]
            com.xiaopeng.speech.protocol.node.speech.SpeechDialogListener r2 = (com.xiaopeng.speech.protocol.node.speech.SpeechDialogListener) r2
            r2.onRefreshUi(r0, r5)
            int r1 = r1 + 1
            goto L24
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.speech.SpeechDialogNode.onRefreshUi(java.lang.String, java.lang.String):void");
    }
}

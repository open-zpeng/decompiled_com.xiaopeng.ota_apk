package com.xiaopeng.speech.protocol.node.dialog;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.DMEnd;
import com.xiaopeng.speech.jarvisproto.DMStart;
import com.xiaopeng.speech.jarvisproto.DMWait;
import com.xiaopeng.speech.protocol.event.DialogEvent;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogEndReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.WakeupReason;
/* loaded from: classes2.dex */
public class DialogNode extends SpeechNode<DialogListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMStart.EVENT)
    public void onDialogStart(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        WakeupReason fromJson = WakeupReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogStart(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_ERROR)
    public void onDialogError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMEnd.EVENT)
    public void onDialogEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DialogEndReason fromJson = DialogEndReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogEnd(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMWait.EVENT)
    public void onDialogWait(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DMWait dMWait = new DMWait();
        if (!TextUtils.isEmpty(str2)) {
            dMWait = DMWait.fromJson(str2);
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogWait(dMWait);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_CONTINUE)
    public void onDialogContinue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogContinue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.WAKEUP_RESULT)
    public void onWakeupResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onWakeupResult();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.VAD_BEGIN)
    public void onVadBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onVadBegin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.VAD_END)
    public void onVadEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onVadEnd();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_STATUS_CHANGED)
    public void onDialogStatusChanged(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogStatusChanged(str2);
            }
        }
    }
}

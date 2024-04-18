package com.xiaopeng.ota.helper;

import android.content.Context;
import android.os.ConditionVariable;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class TextToSpeechHelper {
    private static final String TAG = "TextToSpeechHelper";
    private volatile int mInitStatus;
    private TextToSpeech mSpeaker;

    /* loaded from: classes2.dex */
    private static class TextToSpeechHelperHolder {
        static final TextToSpeechHelper INSTANCE = new TextToSpeechHelper();

        private TextToSpeechHelperHolder() {
        }
    }

    public static TextToSpeechHelper getInstance() {
        return TextToSpeechHelperHolder.INSTANCE;
    }

    public void init(Context context) {
        final ConditionVariable conditionVariable = new ConditionVariable();
        this.mInitStatus = -1;
        for (int i = 0; i <= 3; i++) {
            conditionVariable.close();
            this.mSpeaker = new TextToSpeech(context, new TextToSpeech.OnInitListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$TextToSpeechHelper$Qg7Oln68vcYic029tvc9hxsTHMM
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i2) {
                    TextToSpeechHelper.this.lambda$init$0$TextToSpeechHelper(conditionVariable, i2);
                }
            });
            conditionVariable.block(TimeUnit.SECONDS.toMillis(3L));
            if (this.mInitStatus == 0) {
                break;
            }
            if (i < 3) {
                LogUtils.i(TAG, "Retry[%d] init TextToSpeech delay 1s", Integer.valueOf(i + 1));
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException unused) {
                }
            }
        }
        if (this.mSpeaker == null || this.mInitStatus != 0) {
            return;
        }
        this.mSpeaker.setSpeechRate(1.0f);
        this.mSpeaker.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.xiaopeng.ota.helper.TextToSpeechHelper.1
            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                LogUtils.d(TextToSpeechHelper.TAG, "onStart utteranceId: %s", str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                LogUtils.d(TextToSpeechHelper.TAG, "onDone utteranceId: %s", str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str, int i2) {
                super.onError(str, i2);
                LogUtils.d(TextToSpeechHelper.TAG, "onError utteranceId: %s, errorCode: %d", str, Integer.valueOf(i2));
            }
        });
    }

    public /* synthetic */ void lambda$init$0$TextToSpeechHelper(ConditionVariable conditionVariable, int i) {
        this.mInitStatus = i;
        if (i == 0) {
            LogUtils.d(TAG, "TextToSpeech init success");
        } else {
            LogUtils.e(TAG, "TextToSpeech init fail");
        }
        conditionVariable.open();
    }

    public void dispose() {
        TextToSpeech textToSpeech = this.mSpeaker;
        if (textToSpeech != null) {
            textToSpeech.stop();
            this.mSpeaker.shutdown();
        }
    }

    public void speak(String str) {
        if (this.mSpeaker == null) {
            LogUtils.e(TAG, "Speaker is null");
            return;
        }
        String format = String.format(Locale.getDefault(), "uid%d", Long.valueOf(System.currentTimeMillis()));
        this.mSpeaker.speak(str, 1, null, format);
        LogUtils.d(TAG, "speak utteranceId: %s", format);
    }
}

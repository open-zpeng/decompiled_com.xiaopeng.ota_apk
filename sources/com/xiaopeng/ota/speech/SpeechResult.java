package com.xiaopeng.ota.speech;
/* loaded from: classes2.dex */
public class SpeechResult {
    private String event;
    private Object result;

    public SpeechResult() {
    }

    public SpeechResult(String str, Object obj) {
        this.event = str;
        this.result = obj;
    }

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object obj) {
        this.result = obj;
    }
}

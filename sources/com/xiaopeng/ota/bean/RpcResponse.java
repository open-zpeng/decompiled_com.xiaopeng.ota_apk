package com.xiaopeng.ota.bean;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    private Integer mode;
    private String stackTrace;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Integer getMode() {
        return this.mode;
    }

    public void setMode(Integer num) {
        this.mode = num;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public void setStackTrace(String str) {
        this.stackTrace = str;
    }

    public String toString() {
        return "RpcResponse{code=" + this.code + ", message='" + this.message + "', mode=" + this.mode + ", stackTrace='" + this.stackTrace + "'}";
    }
}

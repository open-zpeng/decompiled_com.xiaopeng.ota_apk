package com.xiaopeng.ota.bean;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DidResponse implements Serializable {
    private int address;
    private int code;
    private int did;
    private String message;
    private String value;

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int i) {
        this.address = i;
    }

    public int getDid() {
        return this.did;
    }

    public void setDid(int i) {
        this.did = i;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

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

    /* loaded from: classes2.dex */
    public enum ResultEnum {
        OK(0, "success"),
        REQUEST_INVALID(1, "Request content invalid"),
        ADDRESS_INVALID(2, "Address invalid"),
        UPGRADING(3, "OTA upgrading"),
        UDS_FAIL(4, "UDS service failure"),
        SERVICE_INITIALIZATION(5, "Service initialization"),
        READ_ECU_FAIL(100, "Read ECU data fail");
        
        private int code;
        private String message;

        ResultEnum(int i, String str) {
            this.code = i;
            this.message = str;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}

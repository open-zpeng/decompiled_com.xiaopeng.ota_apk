package com.xiaopeng.ota.presenter.update.bean;
/* loaded from: classes2.dex */
public class Condition {
    private String alert;
    private String name;
    private String operator;
    private String type;
    private Object value;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public String getAlert() {
        return this.alert;
    }

    public void setAlert(String str) {
        this.alert = str;
    }
}

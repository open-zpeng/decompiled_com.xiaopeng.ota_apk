package com.xiaopeng.ota.sdk.common.condition;

import java.util.Objects;
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

    public String toString() {
        return "Condition{type='" + this.type + "', name='" + this.name + "', operator='" + this.operator + "', value=" + this.value + ", alert=" + this.alert + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Condition condition = (Condition) obj;
        return Objects.equals(this.type, condition.type) && Objects.equals(this.name, condition.name) && Objects.equals(this.operator, condition.operator) && Objects.equals(this.value, condition.value);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.name, this.operator, this.value);
    }
}

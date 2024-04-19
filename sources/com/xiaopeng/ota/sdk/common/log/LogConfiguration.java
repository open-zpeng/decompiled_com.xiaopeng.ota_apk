package com.xiaopeng.ota.sdk.common.log;
/* loaded from: classes2.dex */
public class LogConfiguration {
    private int capacity;
    private boolean deleteExpired;
    private String header;
    private String moduleName;

    private LogConfiguration() {
    }

    private LogConfiguration(Builder builder) {
        setHeader(builder.header);
        setModuleName(builder.moduleName);
        setDeleteExpired(builder.deleteExpired);
        setCapacity(builder.capacity);
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String str) {
        this.moduleName = str;
    }

    public boolean isDeleteExpired() {
        return this.deleteExpired;
    }

    public void setDeleteExpired(boolean z) {
        this.deleteExpired = z;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int i) {
        this.capacity = i;
    }

    public String toString() {
        return "LogConfiguration{header='" + this.header + "', moduleName='" + this.moduleName + "', deleteExpired=" + this.deleteExpired + ", capacity=" + this.capacity + '}';
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int capacity;
        private boolean deleteExpired;
        private String header;
        private String moduleName;

        public Builder header(String str) {
            this.header = str;
            return this;
        }

        public Builder moduleName(String str) {
            this.moduleName = str;
            return this;
        }

        public Builder deleteExpired(boolean z) {
            this.deleteExpired = z;
            return this;
        }

        public Builder capacity(int i) {
            this.capacity = i;
            return this;
        }

        public LogConfiguration build() {
            return new LogConfiguration(this);
        }
    }
}

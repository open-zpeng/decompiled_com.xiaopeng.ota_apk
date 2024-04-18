package com.xiaopeng.ota.presenter.db;
/* loaded from: classes2.dex */
public class OperationEntity {
    public static final String AUTO_INCREASE_ID = "_id";
    public static final String CREATE_TIME = "create_time";
    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final String UPDATE_TIME = "update_time";
    private long createTime;
    private long id;
    private long updateTime;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }
}

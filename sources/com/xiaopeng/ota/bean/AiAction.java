package com.xiaopeng.ota.bean;
/* loaded from: classes2.dex */
public enum AiAction {
    NONE("NONE", ""),
    OP_UPGRADE_NOW("OP_UPGRADE_NOW", "ACTION_AI_OP_UPGRADE_NOW_%d"),
    OP_SHOW_UPGRADE_INFO("OP_SHOW_UPGRADE_INFO", "ACTION_AI_OP_SHOW_UPGRADE_INFO_%d"),
    OP_SHOW_UPGRADE_HISTORY("OP_SHOW_UPGRADE_HISTORY", "ACTION_AI_OP_SHOW_UPGRADE_INFO_%d"),
    OP_SETTING_SCHEDULE("OP_SETTING_SCHEDULE", "ACTION_AI_OP_SCHEDULE_SETTING_%d"),
    OP_SETTING_SCHEDULE_DEFAULT("OP_SETTING_SCHEDULE_DEFAULT", "ACTION_AI_OP_SCHEDULE_SETTING_%d"),
    OP_SETTING_SCHEDULE_AUTO("OP_SETTING_SCHEDULE_AUTO", "ACTION_AI_OP_SCHEDULE_SETTING_%d"),
    OP_ENABLE_AUTO_UPGRADE("OP_ENABLE_AUTO_UPGRADE", ActionEvent.ACTION_AI_OP_ENABLE_AUTO_UPGRADE_FORMAT),
    OP_SCHEDULE("OP_SCHEDULE", "ACTION_AI_OP_SCHEDULE_UPGRADE_%d"),
    OP_CALL_CS("OP_CALL_CS", "ACTION_AI_OP_CALL_CS_%d"),
    OP_SHOW_MAIN("OP_SHOW_MAIN", ActionEvent.ACTION_AI_OP_SHOW_MAIN_FORMAT);
    
    private String eventFormat;
    private String op;

    AiAction(String str, String str2) {
        this.op = str;
        this.eventFormat = str2;
    }

    public String getOp() {
        return this.op;
    }

    public String getEventFormat() {
        return this.eventFormat;
    }

    public static AiAction getActionByOp(String str) {
        AiAction[] values;
        for (AiAction aiAction : values()) {
            if (aiAction.op.equals(str)) {
                return aiAction;
            }
        }
        return null;
    }
}

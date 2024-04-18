package com.xiaopeng.ota.bean;
/* loaded from: classes2.dex */
public enum AiScene {
    COMMON_FAIL_RETRY(11001, "ACTION_AI_COMMON_FAIL_CAN_RETRY"),
    COMMON_FAIL_NO_RETRY(11002, "ACTION_AI_COMMON_FAIL_CANNOT_RETRY"),
    SCHEDULE_FAIL_RETRY(11003, "ACTION_AI_SCHEDULE_FAIL_CAN_RETRY"),
    SCHEDULE_FAIL_NO_RETRY(11004, "ACTION_AI_SCHEDULE_FAIL_CANNOT_RETRY"),
    COMMON_SUCCESS(11005, "ACTION_AI_COMMON_SUCCESS"),
    SCHEDULE_SUCCESS(11006, "ACTION_AI_SCHEDULE_SUCCESS"),
    SCHEDULE_ARRIVED(11007, "ACTION_AI_SCHEDULE_ARRIVED"),
    REGULAR_POS_SCHEDULE_UPGRADE(11008, "ACTION_AI_REGULAR_POS_SCHEDULE_UPGRADE"),
    REGULAR_POS_UPGRADE(11009, "ACTION_AI_REGULAR_POS_UPGRADE"),
    NOT_REGULAR_POS_SCHEDULE_UPGRADE(11010, "ACTION_AI_NOT_REGULAR_POS_SCHEDULE_UPGRADE"),
    NOT_REGULAR_POS_UPGRADE(11011, "ACTION_AI_NOT_REGULAR_POS_UPGRADE");
    
    private String event;
    private int scene;

    AiScene(int i, String str) {
        this.scene = i;
        this.event = str;
    }

    public int getScene() {
        return this.scene;
    }

    public String getEvent() {
        return this.event;
    }

    public static AiScene getByScene(int i) {
        AiScene[] values;
        for (AiScene aiScene : values()) {
            if (aiScene.scene == i) {
                return aiScene;
            }
        }
        return null;
    }
}

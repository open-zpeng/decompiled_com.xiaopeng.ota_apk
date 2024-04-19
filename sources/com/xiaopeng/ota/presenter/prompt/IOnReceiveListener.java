package com.xiaopeng.ota.presenter.prompt;
/* loaded from: classes2.dex */
public interface IOnReceiveListener {
    void onAutoUpgradeEnable();

    void onCallCustomerService();

    void onSchedule();

    void onScheduleDefault(boolean z);

    void onScheduleWithTime(int i, int i2);

    void onSendAiResponseEvent(AiContent aiContent);

    void onSendPromptEvent(AiContent aiContent);

    void onSettingSchedule();

    void onShowMain();

    void onShowUpgrade();

    void onUpgradeNow();

    void onUpgradeSuccess();
}

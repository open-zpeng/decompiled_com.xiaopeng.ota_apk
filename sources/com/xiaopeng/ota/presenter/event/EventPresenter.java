package com.xiaopeng.ota.presenter.event;

import android.text.TextUtils;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.bean.AiAction;
import com.xiaopeng.ota.bean.AiScene;
import com.xiaopeng.ota.presenter.event.CampaignEvent;
import com.xiaopeng.ota.presenter.prompt.AiContent;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.ConditionUtils;
import com.xiaopeng.ota.sdk.common.util.Dictionary;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.speech.protocol.bean.recommend.RecommendBean;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
public class EventPresenter {
    private static final String KEY_ACTION = "action";
    private static final String KEY_AI_TYPE = "type";
    private static final String KEY_CONDITION_NAME = "conditionName";
    private static final String KEY_FROM = "from";
    private static final String KEY_GEAR_LEVEL = "gearLevel";
    private static final String KEY_HAS_CAMPAIGN = "hasCampaign";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCENE = "scene";
    private static final String KEY_SCHEDULE = "schedule";
    private static final String KEY_SWITCH = "switch";
    private static final String TYPE_PROMPT = "dialog";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class EventPresenterHolder {
        static final EventPresenter INSTANCE = new EventPresenter();

        private EventPresenterHolder() {
        }
    }

    public static EventPresenter getInstance() {
        return EventPresenterHolder.INSTANCE;
    }

    public void sendXlogoClickedEvent(boolean z) {
        Dictionary dictionary = new Dictionary();
        dictionary.put(KEY_HAS_CAMPAIGN, Boolean.valueOf(z));
        sendEventWithoutCampaign(ActionEvent.ACTION_XLOGO_CLICKED, JsonUtils.toJson(dictionary));
    }

    public void sendCloseClickedEvent(String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("name", str);
        sendEventWithoutCampaign(ActionEvent.ACTION_CLOSE, JsonUtils.toJson(dictionary));
    }

    public void sendBackClickedEvent(String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("name", str);
        sendEventWithoutCampaign(ActionEvent.ACTION_BACK, JsonUtils.toJson(dictionary));
    }

    public void sendOkClickedEvent(String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("name", str);
        sendEventWithoutCampaign(ActionEvent.ACTION_OK, JsonUtils.toJson(dictionary));
    }

    public void sendCancelClickedEvent(String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("name", str);
        sendEventWithoutCampaign(ActionEvent.ACTION_CANCEL, JsonUtils.toJson(dictionary));
    }

    public void sendAutoUpgradeClicked(boolean z) {
        sendAutoUpgradeClicked(z, null);
    }

    public void sendAutoUpgradeClicked(boolean z, String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put(KEY_SWITCH, z ? "on" : "off");
        if (!TextUtils.isEmpty(str)) {
            dictionary.put("from", str);
        }
        sendEventWithoutCampaign(ActionEvent.ACTION_AUTO_UPGRADE, JsonUtils.toJson(dictionary));
    }

    public void sendGearLevel(int i) {
        Dictionary dictionary = new Dictionary();
        dictionary.put(KEY_GEAR_LEVEL, Integer.valueOf(i));
        sendEventWithoutCampaign(ActionEvent.ACTION_GEAR_LEVEL, JsonUtils.toJson(dictionary));
    }

    public void sendH5Link(String str, int i, boolean z) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("link", str);
        dictionary.put(RecommendBean.SHOW_TIME_RESULT, Boolean.valueOf(z));
        if (i > 0) {
            dictionary.put("osVersion", Integer.valueOf(i));
        }
        sendEventWithoutCampaign(ActionEvent.ACTION_H5_LINK, JsonUtils.toJson(dictionary));
    }

    public void sendSpeechEvent(String str) {
        sendEventWithoutCampaign(ActionEvent.ACTION_SPEECH, str);
    }

    private void sendEventWithoutCampaign(String str) {
        sendEventWithoutCampaign(str, null);
    }

    private void sendEventWithoutCampaign(String str, String str2) {
        sendEvent(0L, str, str2);
    }

    public void sendUpgradeIconClickedEvent(long j) {
        sendEvent(j, ActionEvent.ACTION_UPGRADE_ICON_CLICKED);
    }

    public void sendConfirmAutoUpgradeEvent(long j, Dictionary dictionary) {
        sendEvent(j, ActionEvent.ACTION_CONFIRM_AUTO_UPGRADE, JsonUtils.toJson(dictionary));
    }

    public void sendCancelAutoUpgradeEvent(long j, Dictionary dictionary) {
        sendEvent(j, ActionEvent.ACTION_CANCEL_AUTO_UPGRADE, JsonUtils.toJson(dictionary));
    }

    public void sendUpgradeEvent(long j) {
        sendEvent(j, "ACTION_UPGRADE_IMMEDIATELY");
    }

    public void sendConfirmNotSatisfiedEvent(long j) {
        sendEvent(j, "ACTION_CONDITION_MISMATCH_DIALOG_CLOSE");
    }

    public void sendConfirmExpiredEvent(long j) {
        sendEvent(j, "ACTION_CAMPAIGN_EXPIRE_DIALOG_CLOSE");
    }

    public void sendNotSatisfiedEvent(long j, List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            sendEvent(j, "ACTION_CONDITION_MISMATCH");
        } else {
            sendNotSatisfiedEvent(j, null, ConditionUtils.convert2Context(list));
        }
    }

    public void sendNotSatisfiedEvent(long j, List<String> list, String str) {
        Dictionary dictionary = new Dictionary();
        if (list != null && !list.isEmpty()) {
            dictionary.put(KEY_CONDITION_NAME, list);
        }
        if (!TextUtils.isEmpty(str)) {
            dictionary.put("message", str);
        }
        sendEvent(j, "ACTION_CONDITION_MISMATCH", JsonUtils.toJson(dictionary));
    }

    public void sendScheduleUpgradeEvent(long j) {
        sendEvent(j, "ACTION_SCHEDULE_UPGRADE");
    }

    public void sendModifyScheduleTimeEvent(long j) {
        sendEvent(j, "ACTION_MODIFY_SCHEDULE_TIME");
    }

    public void sendCancelScheduleEvent(long j, String str) {
        sendEvent(j, "ACTION_CANCEL_SCHEDULE", str);
    }

    public void sendScheduleFailEvent(long j, String str) {
        sendEvent(j, ActionEvent.ACTION_SCHEDULE_FAIL, str);
    }

    public void sendWakeUpEvent(long j) {
        sendEvent(j, "ACTION_WAKE_UP");
    }

    public void sendWakeUpInitEvent(long j) {
        sendEvent(j, "ACTION_WAKE_UP", null, CampaignEvent.State.INIT);
    }

    public void sendWakeUpUpgradeNotMeetEvent(long j, String str) {
        sendEvent(j, "ACTION_WAKE_UP", str, CampaignEvent.State.INTERRUPTED);
    }

    public void sendWakeUp2UpgradeEvent(long j) {
        sendEvent(j, "ACTION_WAKE_UP", null, CampaignEvent.State.OK);
    }

    public void sendPromptAiEvent(AiContent aiContent) {
        AiScene scene = aiContent.getScene();
        AiAction action = aiContent.getAction();
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(scene.getScene()));
        dictionary.put("name", scene.getEvent());
        dictionary.put("action", action.getOp());
        if (aiContent.isDialog()) {
            dictionary.put("type", TYPE_PROMPT);
        }
        sendEvent(aiContent.getCampaignId(), scene.getEvent(), JsonUtils.toJson(dictionary));
    }

    @Deprecated
    public void sendPromptAiEvent(long j, AiScene aiScene) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(aiScene.getScene()));
        sendEvent(j, aiScene.getEvent(), JsonUtils.toJson(dictionary));
    }

    @Deprecated
    public void sendPromptSimulateEvent(long j, AiScene aiScene) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(aiScene.getScene()));
        dictionary.put("type", TYPE_PROMPT);
        sendEvent(j, aiScene.getEvent(), JsonUtils.toJson(dictionary));
    }

    public void sendAiResponseEvent(AiContent aiContent) {
        AiScene scene = aiContent.getScene();
        String op = aiContent.getAction().getOp();
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(scene.getScene()));
        dictionary.put("name", scene.getEvent());
        if (aiContent.isDialog()) {
            dictionary.put("type", TYPE_PROMPT);
        }
        sendEvent(aiContent.getCampaignId(), op, JsonUtils.toJson(dictionary));
    }

    @Deprecated
    public void sendAiActionEvent(long j, AiAction aiAction, AiScene aiScene) {
        String format = String.format(Locale.getDefault(), aiAction.getEventFormat(), Integer.valueOf(aiScene.getScene()));
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(aiScene.getScene()));
        dictionary.put("name", aiScene.getEvent());
        sendEvent(j, format, JsonUtils.toJson(dictionary));
    }

    @Deprecated
    public void sendSimulateActionEvent(long j, AiAction aiAction, AiScene aiScene) {
        String format = String.format(Locale.getDefault(), aiAction.getEventFormat(), Integer.valueOf(aiScene.getScene()));
        Dictionary dictionary = new Dictionary();
        dictionary.put("scene", Integer.valueOf(aiScene.getScene()));
        dictionary.put("name", aiScene.getEvent());
        dictionary.put("type", TYPE_PROMPT);
        sendEvent(j, format, JsonUtils.toJson(dictionary));
    }

    public void sendPromptUpgradeEvent(long j, int i) {
        if (i == 0) {
            sendEvent(j, "ACTION_PROMPT_UPGRADE_COMMON");
        } else {
            sendEvent(j, "ACTION_PROMPT_UPGRADE_WARN");
        }
    }

    public void sendScheduleSettingEvent(long j) {
        sendEvent(j, "ACTION_SCHEDULE");
    }

    public void sendUpgradeLaterEvent(long j) {
        sendEvent(j, "ACTION_UPGRADE_LATER");
    }

    public void sendStillUpgradeEvent(long j) {
        sendEvent(j, "ACTION_STILL_UPGRADE");
    }

    public void sendConfirmUpgradeEvent(long j, boolean z) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("schedule", Boolean.valueOf(z));
        sendEvent(j, "ACTION_CONFIRM_UPGRADE", JsonUtils.toJson(dictionary));
    }

    public void sendConfirmParkEvent(long j) {
        sendEvent(j, "ACTION_CONFIRM_PARK");
    }

    public void sendAutoUpgradeDialogPopup(long j, String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("trigger", str);
        sendEvent(j, ActionEvent.ACTION_AUTO_UPGRADE_DIALOG_POPUP, JsonUtils.toJson(dictionary));
    }

    public void sendAutoUpgradeDialogDismiss(long j, String str) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("trigger", str);
        sendEvent(j, ActionEvent.ACTION_AUTO_UPGRADE_DIALOG_DISMISS, JsonUtils.toJson(dictionary));
    }

    public void sendAutoUpgradeAutomatic(long j) {
        sendEvent(j, ActionEvent.ACTION_AUTO_UPGRADE_AUTOMATIC);
    }

    public void sendAutoUpgradeScheduleTime(long j) {
        sendEvent(j, ActionEvent.ACTION_AUTO_UPGRADE_SCHEDULE_TIME);
    }

    private void sendEvent(long j, String str) {
        UpgradeUtils.OtaSendEvent(j, str, null, CampaignEvent.State.OK.getCode());
    }

    private void sendEvent(long j, String str, String str2) {
        UpgradeUtils.OtaSendEvent(j, str, str2, CampaignEvent.State.OK.getCode());
    }

    private void sendEvent(long j, String str, String str2, CampaignEvent.State state) {
        UpgradeUtils.OtaSendEvent(j, str, str2, state.getCode());
    }
}

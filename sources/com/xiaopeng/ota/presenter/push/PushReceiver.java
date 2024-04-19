package com.xiaopeng.ota.presenter.push;

import android.content.Context;
import android.telecom.Log;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PushReceiver {
    private static final String MESSAGE_ID = "messageId";
    private static final String SCENE = "scene";
    private static final String TAG = "PushReceiver";
    private Context mContext;
    private PushPresenter mPushPresenter;
    private LinkedHashSet<String> mReceivedMessageIds = new LinkedHashSet<>();

    public PushReceiver(Context context) {
        this.mContext = context;
    }

    public void initialize() {
        Log.d(TAG, "Initialize", new Object[0]);
        this.mPushPresenter = new PushPresenter(this.mContext);
        EventBus.getDefault().register(this);
    }

    private void addMessageId(String str) {
        while (this.mReceivedMessageIds.size() > 100) {
            Iterator<String> it = this.mReceivedMessageIds.iterator();
            if (it.hasNext()) {
                LogUtils.d(TAG, "Remove message id " + it.next());
                it.remove();
            }
        }
        this.mReceivedMessageIds.add(str);
        LogUtils.d(TAG, "Add message id " + str);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReceiveIpcMessageEvent(IIpcService.IpcMessageEvent ipcMessageEvent) {
        if (ipcMessageEvent == null) {
            LogUtils.d(TAG, "Receive ipc message is null");
            return;
        }
        int msgID = ipcMessageEvent.getMsgID();
        String senderPackageName = ipcMessageEvent.getSenderPackageName();
        String string = ipcMessageEvent.getPayloadData().getString(IpcConfig.IPCKey.STRING_MSG);
        LogUtils.d(TAG, "Receive ipc message msgId:" + msgID + ", senderPackageName:" + senderPackageName + ", messageContent:" + string);
        try {
            JSONObject jSONObject = new JSONObject(string);
            int i = jSONObject.getInt("scene");
            String string2 = jSONObject.getString("messageId");
            if (this.mReceivedMessageIds.contains(string2)) {
                LogUtils.d(TAG, "Duplicate message messageId:" + string2);
                return;
            }
            LogUtils.d(TAG, "onReceiveIpcMessageEvent, scene:%d, messageId:%s", Integer.valueOf(i), string2);
            addMessageId(string2);
            switch (i) {
                case 11001:
                    this.mPushPresenter.checkCampaign(string2);
                    return;
                case 11002:
                    this.mPushPresenter.updateRemoteConfig(string2);
                    return;
                case 11003:
                    this.mPushPresenter.uploadLog(string2, jSONObject);
                    return;
                case 11004:
                    this.mPushPresenter.syncEcu(string2);
                    return;
                case 11005:
                    this.mPushPresenter.clearDatabase(string2);
                    return;
                case 11006:
                    this.mPushPresenter.clearDownloadPackages(string2);
                    return;
                case 11007:
                    this.mPushPresenter.promptNewVersion(string2);
                    return;
                case 11008:
                    this.mPushPresenter.cancelCampaign(string2);
                    return;
                case 11009:
                case 11010:
                case 11011:
                case 11012:
                case 11014:
                    this.mPushPresenter.handleSotaAction(string2, i);
                    return;
                case 11013:
                    this.mPushPresenter.retryDownload(string2);
                    return;
                case 11015:
                    this.mPushPresenter.upgradeNow(string2, jSONObject);
                    return;
                case 11016:
                    this.mPushPresenter.cancelSchedule(string2, jSONObject);
                    return;
                case 11017:
                    this.mPushPresenter.schedule(string2, jSONObject);
                    return;
                case 11018:
                    this.mPushPresenter.resetPeps(string2);
                    return;
                case 11019:
                    this.mPushPresenter.resetApp(string2);
                    return;
                case 11020:
                    this.mPushPresenter.queryVehicleStatus(string2);
                    return;
                case 11021:
                    this.mPushPresenter.resetPsu(string2);
                    return;
                case IpcConfig.AIAssistantConfig.IPC_IDENTIFIED_FACE /* 11022 */:
                default:
                    LogUtils.d(TAG, "Receive ipc message unknown scene:" + i);
                    this.mPushPresenter.forwardToSdk(i, string2);
                    return;
                case 11023:
                    this.mPushPresenter.cleanXOSVersion(string2);
                    return;
                case 11024:
                    this.mPushPresenter.refreshXOSVersion(string2);
                    return;
                case 11025:
                    this.mPushPresenter.resetCampaignRetries(string2);
                    return;
                case 11026:
                    this.mPushPresenter.abortCampaign(string2);
                    return;
                case 11027:
                    this.mPushPresenter.clearVersions(string2);
                    return;
                case 11028:
                case 11029:
                case 11030:
                case 11031:
                    this.mPushPresenter.forwardToSdk(i, string2);
                    return;
            }
        } catch (JSONException e) {
            LogUtils.e(TAG, e, "Parse ipc message exception");
        }
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}

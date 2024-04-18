package com.xiaopeng.ota.presenter.push;

import android.content.Context;
import android.telecom.Log;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.ipc.IpcRouterEvent;
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
    public void onReceiveIpcMessageEvent(IpcRouterEvent ipcRouterEvent) {
        if (ipcRouterEvent == null) {
            LogUtils.d(TAG, "Receive ipc message is null");
            return;
        }
        try {
            int id = ipcRouterEvent.getId();
            JSONObject jSONObject = new JSONObject(ipcRouterEvent.getBundle());
            String string = jSONObject.getString("senderPackageName");
            String string2 = jSONObject.getString(IpcConfig.IPCKey.STRING_MSG);
            LogUtils.d(TAG, "Receive ipc message msgId:" + id + ", senderPackageName:" + string + ", messageContent:" + string2);
            handleReceivedMessage(string2);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Handle receive ipc message fail");
        }
    }

    private void handleReceivedMessage(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("scene");
            String string = jSONObject.getString("messageId");
            if (this.mReceivedMessageIds.contains(string)) {
                LogUtils.d(TAG, "Duplicate message messageId:" + string);
                return;
            }
            LogUtils.d(TAG, "onReceiveIpcMessageEvent, scene:%d, messageId:%s", Integer.valueOf(i), string);
            addMessageId(string);
            startFotaServiceByScene(i);
            switch (i) {
                case 11001:
                    this.mPushPresenter.forwardToSdk(i, string);
                    return;
                case 11002:
                    this.mPushPresenter.updateRemoteConfig(string);
                    return;
                case 11003:
                    this.mPushPresenter.uploadLog(string, jSONObject);
                    return;
                case 11004:
                    this.mPushPresenter.forwardToSdk(i, string);
                    return;
                case 11005:
                    this.mPushPresenter.clearDatabase(string);
                    return;
                case 11006:
                case 11007:
                case 11008:
                case 11013:
                    this.mPushPresenter.forwardToSdk(i, string);
                    return;
                case 11009:
                case 11010:
                case 11011:
                case 11012:
                case 11014:
                    this.mPushPresenter.handleSotaAction(string, i);
                    return;
                case 11015:
                    this.mPushPresenter.upgradeNow(string, jSONObject);
                    return;
                case 11016:
                    this.mPushPresenter.cancelSchedule(string, jSONObject);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case 11017:
                    this.mPushPresenter.schedule(string, jSONObject);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case 11018:
                    this.mPushPresenter.resetPeps(string);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case 11019:
                    this.mPushPresenter.resetApp(string);
                    return;
                case 11020:
                    this.mPushPresenter.queryVehicleStatus(string);
                    return;
                case 11021:
                    this.mPushPresenter.resetPsu(string);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case IpcConfig.AIAssistantConfig.IPC_IDENTIFIED_FACE /* 11022 */:
                default:
                    LogUtils.d(TAG, "Receive ipc message unknown scene:" + i);
                    this.mPushPresenter.forwardToSdk(i, string);
                    return;
                case 11023:
                    this.mPushPresenter.cleanXOSVersion(string);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case 11024:
                    this.mPushPresenter.refreshXOSVersion(string);
                    OTAManager.sendStopFotaServiceAndActivity();
                    return;
                case 11025:
                case 11026:
                case 11027:
                case 11028:
                case 11029:
                case 11030:
                case 11031:
                    this.mPushPresenter.forwardToSdk(i, string);
                    return;
            }
        } catch (JSONException e) {
            LogUtils.e(TAG, e, "Parse ipc message exception");
        }
    }

    private void startFotaServiceByScene(int i) {
        switch (i) {
            case 11009:
            case 11010:
            case 11011:
            case 11012:
            case 11014:
                return;
            case 11013:
            default:
                OTAManager.sendStartFotaService();
                return;
        }
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}

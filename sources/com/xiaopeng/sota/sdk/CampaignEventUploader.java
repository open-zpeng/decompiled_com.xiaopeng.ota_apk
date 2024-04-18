package com.xiaopeng.sota.sdk;

import android.text.TextUtils;
import com.xiaopeng.ota.sdk.campaign.event.CampaignEventProducedEvent;
import com.xiaopeng.ota.sdk.campaign.event.NetworkConnectedEvent;
import com.xiaopeng.ota.sdk.common.SmartTimer;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.eventbroker.Subscribe;
import com.xiaopeng.ota.sdk.eventbroker.ThreadMode;
import com.xiaopeng.sota.sdk.client.SOTAClient;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEvent;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEventManager;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CampaignEventUploader {
    private static final int LIMIT_EVENT_PER_TRACE_ID = 15;
    private static final String TAG = "CampaignEventUploader";
    private static final String TIMER_NAME = "SOTAUploader";
    private volatile boolean isStarted;
    private CampaignEventManager mCampaignEventManager;
    private SmartTimer mSmartTimer;
    private byte[] mUploadLock = new byte[0];

    public CampaignEventUploader(CampaignEventManager campaignEventManager) {
        this.mCampaignEventManager = campaignEventManager;
        SOTAEventBroker.getEventBroker().register(this);
    }

    public void dispose() {
        SOTAEventBroker.getEventBroker().unregister(this);
        SmartTimer smartTimer = this.mSmartTimer;
        if (smartTimer != null) {
            smartTimer.stop();
        }
    }

    public synchronized void start() {
        if (this.isStarted) {
            Logger.d(TAG, "Campaign uploader has started already", new Object[0]);
            return;
        }
        this.mSmartTimer = new SmartTimer(TIMER_NAME, new Runnable() { // from class: com.xiaopeng.sota.sdk.CampaignEventUploader.1
            @Override // java.lang.Runnable
            public void run() {
                CampaignEventUploader.this.upload();
            }
        });
        this.mSmartTimer.start();
        this.isStarted = true;
    }

    public void uploadImmediately() {
        upload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upload() {
        synchronized (this.mUploadLock) {
            List<String> allTraceId = this.mCampaignEventManager.getAllTraceId();
            if (allTraceId != null && !allTraceId.isEmpty()) {
                Logger.d(TAG, "Scan traceIds:" + allTraceId, new Object[0]);
                int size = allTraceId.size();
                for (int i = 0; i < size; i++) {
                    String str = allTraceId.get(i);
                    try {
                        upload(str);
                    } catch (Exception e) {
                        Logger.e(TAG, e, "Upload event occurs exception, traceId:" + str, new Object[0]);
                    }
                }
            } else {
                Logger.d(TAG, "No campaign event to upload", new Object[0]);
                if (this.mSmartTimer != null) {
                    this.mSmartTimer.suspend();
                }
            }
        }
    }

    public void upload(String str) throws Exception {
        List<CampaignEvent> byTraceId = this.mCampaignEventManager.getByTraceId(str, 15);
        if (byTraceId != null && !byTraceId.isEmpty()) {
            long longValue = byTraceId.get(0).getCampaignId().longValue();
            Campaign campaign = SOTAManager.getCampaignManager().get(longValue);
            if (campaign == null) {
                campaign = SOTAManager.getCampaignManager().findById(longValue);
            }
            String host = campaign != null ? campaign.getHost() : null;
            if (TextUtils.isEmpty(host)) {
                host = SOTAManager.getConfig().getHost();
            }
            Logger.d(TAG, "serverUrl:" + host, new Object[0]);
            String report = SOTAClient.report(host, byTraceId);
            if (report != null) {
                JSONObject jSONObject = new JSONObject(report);
                if (jSONObject.has("code")) {
                    if (200 == jSONObject.getInt("code")) {
                        int size = byTraceId.size();
                        Logger.d(TAG, String.format(Locale.getDefault(), "Uploaded success size: %d, delete uploaded events: %d", Integer.valueOf(size), Integer.valueOf(this.mCampaignEventManager.deleteByTraceId(str, byTraceId.get(size - 1).getId()))), new Object[0]);
                        return;
                    }
                    Logger.w(TAG, "Upload event failed, traceId=" + str, new Object[0]);
                    return;
                }
                return;
            }
            Logger.w(TAG, "Upload campaign event does not response", new Object[0]);
            return;
        }
        Logger.d(TAG, "Campaign has no event, traceId=" + str, new Object[0]);
    }

    @Subscribe(events = {CampaignEventProducedEvent.class}, threadMode = ThreadMode.THREAD)
    public void onReceiveCampaignEventProducedEvent(CampaignEventProducedEvent campaignEventProducedEvent) {
        SmartTimer smartTimer = this.mSmartTimer;
        if (smartTimer != null) {
            smartTimer.resume();
        }
    }

    @Subscribe(events = {NetworkConnectedEvent.class}, threadMode = ThreadMode.THREAD)
    public void onReceiveNetworkEvent(NetworkConnectedEvent networkConnectedEvent) {
        SmartTimer smartTimer;
        int type = networkConnectedEvent.getType();
        if (type == -1) {
            SmartTimer smartTimer2 = this.mSmartTimer;
            if (smartTimer2 != null) {
                smartTimer2.suspend();
            }
        } else if ((type == 0 || type == 1) && (smartTimer = this.mSmartTimer) != null) {
            smartTimer.resume();
        }
    }
}

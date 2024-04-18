package com.xiaopeng.ota.speech;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaopeng.lib.apirouter.ApiRouter;
import com.xiaopeng.lib.apirouter.server.Authority;
import com.xiaopeng.lib.apirouter.server.IServicePublisher;
import com.xiaopeng.lib.apirouter.server.Publish;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.sdk.common.util.Dictionary;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.speech.protocol.bean.recommend.RecommendBean;
@Authority(packageName = "com.xiaopeng.ota", serviceName = SpeechEventObserver.TAG)
/* loaded from: classes2.dex */
public class SpeechEventObserver implements IServicePublisher {
    private static final String EVENT_IS_LATEST_VERSION = "is.latest.ota.version";
    private static final String EVENT_OPEN_OTA_PAGE = "command://gui.ota.page.open";
    private static final String EVENT_OPEN_OTA_RESERVATION_PAGE = "command://gui.otareservation.page.open";
    private static final String PACKAGE_CAR_SPEECH = "com.xiaopeng.carspeechservice";
    private static final String TAG = "SpeechEventObserver";

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (r6.equals("command://gui.ota.page.open") != false) goto L8;
     */
    @com.xiaopeng.lib.apirouter.server.Publish
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onEvent(final java.lang.String r6, final java.lang.String r7) {
        /*
            r5 = this;
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            r0[r1] = r6
            r2 = 1
            r0[r2] = r7
            java.lang.String r3 = "SpeechEventObserver"
            java.lang.String r4 = "onEvent event=%s, data=%s"
            com.xiaopeng.ota.utils.LogUtils.d(r3, r4, r0)
            com.xiaopeng.ota.speech.-$$Lambda$SpeechEventObserver$BWdGrsmKKWAccMCkbOQQ6NE6Dl8 r0 = new com.xiaopeng.ota.speech.-$$Lambda$SpeechEventObserver$BWdGrsmKKWAccMCkbOQQ6NE6Dl8
            r0.<init>()
            com.xiaopeng.ota.utils.ThreadUtils.postBackground(r0)
            int r7 = r6.hashCode()
            r0 = 478800373(0x1c89e9f5, float:9.126376E-22)
            if (r7 == r0) goto L31
            r0 = 1009711693(0x3c2efa4d, float:0.010679794)
            if (r7 == r0) goto L27
            goto L3a
        L27:
            java.lang.String r7 = "command://gui.otareservation.page.open"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L3a
            r1 = r2
            goto L3b
        L31:
            java.lang.String r7 = "command://gui.ota.page.open"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L3a
            goto L3b
        L3a:
            r1 = -1
        L3b:
            java.lang.String r6 = "com.xiaopeng.carspeechservice"
            java.lang.String r7 = "packageName"
            if (r1 == 0) goto L58
            if (r1 == r2) goto L44
            goto L6b
        L44:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r0.put(r7, r6)
            android.content.Context r6 = com.xiaopeng.ota.OTAManager.getContext()
            java.lang.Class r7 = com.xiaopeng.ota.helper.CampaignFeatureHelper.getScheduleFragmentClass()
            com.xiaopeng.ota.helper.ActivityHelper.startFragment(r6, r7, r0)
            goto L6b
        L58:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r0.put(r7, r6)
            android.content.Context r6 = com.xiaopeng.ota.OTAManager.getContext()
            java.lang.Class r7 = com.xiaopeng.ota.helper.CampaignFeatureHelper.getMainFragmentClass()
            com.xiaopeng.ota.helper.ActivityHelper.startFragment(r6, r7, r0)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.speech.SpeechEventObserver.onEvent(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onEvent$0(String str, String str2) {
        Dictionary dictionary = new Dictionary();
        if (!TextUtils.isEmpty(str)) {
            dictionary.put("event", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            dictionary.put("data", str2);
        }
        EventPresenter.getInstance().sendSpeechEvent(JsonUtils.toJson(dictionary));
    }

    @Publish
    public void onQuery(String str, String str2, String str3) {
        LogUtils.d(TAG, "onEvent event=%s, data=%s, callback=%s", str, str2, str3);
        if ("is.latest.ota.version".equals(str)) {
            String json = JsonUtils.toJson(new SpeechResult(str, Boolean.valueOf(!OTAManager.getCampaignManager().hasCampaign())));
            if (TextUtils.isEmpty(str3)) {
                return;
            }
            try {
                ApiRouter.route(Uri.parse(str3).buildUpon().appendQueryParameter(RecommendBean.SHOW_TIME_RESULT, json).build());
            } catch (RemoteException e) {
                LogUtils.e(TAG, e, "Callback result occurs RemoteException");
            }
        }
    }
}

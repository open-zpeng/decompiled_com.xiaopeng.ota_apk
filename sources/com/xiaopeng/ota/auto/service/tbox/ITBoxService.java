package com.xiaopeng.ota.auto.service.tbox;

import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public interface ITBoxService extends ICarService {

    /* loaded from: classes2.dex */
    public interface ResponseListener {
        void onReceived(String str);
    }

    void getAsyncTboxVersion(ResponseListener responseListener) throws Exception;

    int getOtaProgress() throws Exception;

    String getStartTboxOtaResponse() throws Exception;

    String getStopTboxOtaResponse() throws Exception;

    String getTboxVersionInfoResponse() throws Exception;

    void sendTboxOtaWorkingStatus(int i) throws Exception;

    void setTboxVersionInfoRequest() throws Exception;

    void startTboxOta(String str) throws Exception;

    void stopTboxOta() throws Exception;
}

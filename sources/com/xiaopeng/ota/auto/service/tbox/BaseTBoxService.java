package com.xiaopeng.ota.auto.service.tbox;

import android.car.Car;
import android.car.hardware.tbox.CarTboxManager;
import com.xiaopeng.ota.auto.CarApiThreadPool;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.TBoxCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.auto.service.tbox.ITBoxService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseTBoxService extends AbsService implements ITBoxService {
    private static final String TAG = "BaseTBoxService";
    protected CarTboxManager mTboxManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mTboxManager = (CarTboxManager) getCarManagerService(Car.XP_TBOX_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, TBoxCallbackAdapter.PROP_IDS);
            this.mTboxManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception e) {
            Logger.d(TAG, e, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void setTboxVersionInfoRequest() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("setTboxVersionInfoRequest error, Car not connected");
        }
        carTboxManager.setTboxVersionInfoRequest();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getTboxVersionInfoResponse() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("getTboxVersionInfoResponse error, Car not connected");
        }
        return carTboxManager.getTboxVersionInfoResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void startTboxOta(String str) throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("startTboxOta error, Car not connected");
        }
        carTboxManager.startTboxOta(str);
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getStartTboxOtaResponse() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("getStartTboxOtaResponse error, Car not connected");
        }
        return carTboxManager.getStartTboxOtaResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void stopTboxOta() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("stopTboxOta error, Car not connected");
        }
        carTboxManager.stopTboxOta();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getStopTboxOtaResponse() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("getStopTboxOtaResponse error, Car not connected");
        }
        return carTboxManager.getStopTboxOtaResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public int getOtaProgress() throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("getOtaProgress error, Car not connected");
        }
        return carTboxManager.getOtaProgress();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void getAsyncTboxVersion(final ITBoxService.ResponseListener responseListener) throws Exception {
        if (this.mTboxManager == null) {
            throw new Exception("getAsyncTboxVersion error, Car not connected");
        }
        CarApiThreadPool.execute(new Runnable() { // from class: com.xiaopeng.ota.auto.service.tbox.-$$Lambda$BaseTBoxService$uQlW-XEZV20VxfRIt9dbiDQvQpE
            @Override // java.lang.Runnable
            public final void run() {
                BaseTBoxService.this.lambda$getAsyncTboxVersion$0$BaseTBoxService(responseListener);
            }
        });
    }

    public /* synthetic */ void lambda$getAsyncTboxVersion$0$BaseTBoxService(ITBoxService.ResponseListener responseListener) {
        try {
            this.mTboxManager.setTboxVersionInfoRequest();
            Thread.sleep(5000L);
            if (responseListener != null) {
                responseListener.onReceived(this.mTboxManager.getTboxVersionInfoResponse());
            }
        } catch (Exception e) {
            Logger.e(TAG, e, "Get Tbox version fail", new Object[0]);
            if (responseListener != null) {
                responseListener.onReceived(null);
            }
        }
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void sendTboxOtaWorkingStatus(int i) throws Exception {
        CarTboxManager carTboxManager = this.mTboxManager;
        if (carTboxManager == null) {
            throw new Exception("sendTboxOtaWorkingStatus error, Car not connected");
        }
        carTboxManager.sendTboxOtaWorkingStatus(i);
    }
}

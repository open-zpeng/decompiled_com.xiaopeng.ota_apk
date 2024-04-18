package com.xiaopeng.ota.client;

import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.client.dto.DrivingRoutesResponse;
import com.xiaopeng.ota.client.dto.OSVersionResponse;
import com.xiaopeng.ota.client.dto.VehicleVersion;
import com.xiaopeng.ota.client.dto.VersionResponse;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.remind.DrivingRoute;
import com.xiaopeng.ota.sdk.common.ContentType;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.util.HttpHeaderUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.exception.NotFoundException;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.version.os.OsVersion;
import java.util.Map;
/* loaded from: classes2.dex */
public class OTAClient {
    private static final String TAG = "OTAClient";
    private IHttp mHttp;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class OTAClientHolder {
        static final OTAClient INSTANCE = new OTAClient();

        private OTAClientHolder() {
        }
    }

    public static OTAClient getInstance() {
        return OTAClientHolder.INSTANCE;
    }

    private OTAClient() {
        this.mHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
    }

    public DrivingRoute requestDrivingRoute() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        String buildXpClient = HttpHeaderUtils.buildXpClient(OTAManager.getContext(), currentTimeMillis);
        Map<String, String> buildHeaders = HttpHeaderUtils.buildHeaders(currentTimeMillis);
        buildHeaders.put("Content-Type", ContentType.JSON_UTF8);
        buildHeaders.put(OTAConstants.HttpHeader.XP_APP_ID, Constants.CONFIG_APP_ID);
        buildHeaders.put(OTAConstants.HttpHeader.XP_CLIENT, buildXpClient);
        Map<String, String> buildHeaders2 = HttpHeaderUtils.buildHeaders(Constants.CONFIG_AP_SECRET, OTAConstants.HttpMethod.GET, buildHeaders, null, null);
        IHttp iHttp = this.mHttp;
        IRequest iRequest = iHttp.get(ConfigHelper.getString("SERVER_URL") + OTAConstants.Server.OTA_BASE + OTAConstants.Server.OTA_REQUEST_DRIVING_ROUTES_URI);
        for (String str : buildHeaders2.keySet()) {
            iRequest.headers(str, buildHeaders2.get(str));
        }
        IResponse execute = iRequest.execute();
        DrivingRoutesResponse drivingRoutesResponse = (DrivingRoutesResponse) JsonUtils.fromJson(execute.body(), (Class<Object>) DrivingRoutesResponse.class);
        if (drivingRoutesResponse == null) {
            LogUtils.d(TAG, "Request driving routes failed, unknown body %s", execute.body());
            throw new IllegalArgumentException("Unknown body");
        }
        LogUtils.d(TAG, "requestDrivingRoute: " + JsonUtils.toJson(drivingRoutesResponse));
        if (drivingRoutesResponse.getCode() == 200 && drivingRoutesResponse.getData() != null) {
            return drivingRoutesResponse.getData();
        }
        throw new NotFoundException("Code " + drivingRoutesResponse.getCode() + " Message " + drivingRoutesResponse.getMsg());
    }

    @Deprecated
    private VehicleVersion requestOsVersion() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        String buildXpClient = HttpHeaderUtils.buildXpClient(OTAManager.getContext(), currentTimeMillis);
        Map<String, String> buildHeaders = HttpHeaderUtils.buildHeaders(currentTimeMillis);
        buildHeaders.put("Content-Type", ContentType.JSON_UTF8);
        buildHeaders.put(OTAConstants.HttpHeader.XP_APP_ID, Constants.CONFIG_APP_ID);
        buildHeaders.put(OTAConstants.HttpHeader.XP_CLIENT, buildXpClient);
        Map<String, String> buildHeaders2 = HttpHeaderUtils.buildHeaders(Constants.CONFIG_AP_SECRET, OTAConstants.HttpMethod.GET, buildHeaders, null, null);
        IHttp iHttp = this.mHttp;
        IRequest iRequest = iHttp.get(ConfigHelper.getString("SERVER_URL") + OTAConstants.Server.OTA_BASE + OTAConstants.Server.OTA_REQUEST_OS_VERSION_URI);
        for (String str : buildHeaders2.keySet()) {
            iRequest.headers(str, buildHeaders2.get(str));
        }
        IResponse execute = iRequest.execute();
        VersionResponse versionResponse = (VersionResponse) JsonUtils.fromJson(execute.body(), (Class<Object>) VersionResponse.class);
        if (versionResponse == null) {
            LogUtils.d(TAG, "Request os version failed, unknown body %s", execute.body());
            throw new IllegalArgumentException("Unknown body");
        }
        LogUtils.d(TAG, "versionResponse: " + JsonUtils.toJson(versionResponse));
        if (versionResponse.getCode() == 200 && versionResponse.getData() != null) {
            return versionResponse.getData();
        }
        throw new NotFoundException("Code " + versionResponse.getCode() + " Message " + versionResponse.getMsg());
    }

    public OsVersion pullOSVersion() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        String buildXpClient = HttpHeaderUtils.buildXpClient(OTAManager.getContext(), currentTimeMillis);
        Map<String, String> buildHeaders = HttpHeaderUtils.buildHeaders(currentTimeMillis);
        buildHeaders.put("Content-Type", ContentType.JSON_UTF8);
        buildHeaders.put(OTAConstants.HttpHeader.XP_APP_ID, Constants.CONFIG_APP_ID);
        buildHeaders.put(OTAConstants.HttpHeader.XP_CLIENT, buildXpClient);
        Map<String, String> buildHeaders2 = HttpHeaderUtils.buildHeaders(Constants.CONFIG_AP_SECRET, OTAConstants.HttpMethod.GET, buildHeaders, null, null);
        IHttp iHttp = this.mHttp;
        IRequest iRequest = iHttp.get(ConfigHelper.getString("SERVER_URL") + OTAConstants.Server.OTA_BASE + OTAConstants.Server.OTA_PULL_OS_VERSION_URI);
        for (String str : buildHeaders2.keySet()) {
            iRequest.headers(str, buildHeaders2.get(str));
        }
        IResponse execute = iRequest.execute();
        OSVersionResponse oSVersionResponse = (OSVersionResponse) JsonUtils.fromJson(execute.body(), (Class<Object>) OSVersionResponse.class);
        if (oSVersionResponse == null) {
            LogUtils.d(TAG, "Request os version failed, unknown body %s", execute.body());
            throw new IllegalArgumentException("Unknown body");
        }
        LogUtils.d(TAG, "osVersionResponse: " + JsonUtils.toJson(oSVersionResponse));
        if (oSVersionResponse.getCode() == 200 && oSVersionResponse.getData() != null) {
            return oSVersionResponse.getData();
        }
        throw new NotFoundException("Code " + oSVersionResponse.getCode() + " Message " + oSVersionResponse.getMsg());
    }
}

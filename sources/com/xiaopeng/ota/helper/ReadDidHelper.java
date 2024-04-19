package com.xiaopeng.ota.helper;

import com.google.gson.reflect.TypeToken;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.bean.DidRequest;
import com.xiaopeng.ota.bean.DidResponse;
import com.xiaopeng.ota.sdk.common.util.Base64Utils;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ReadDidHelper {
    public static final int DID_VCU_MODE = 256;
    private static final String TAG = "ReadDidHelper";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ReadDidHelperHolder {
        static final ReadDidHelper INSTANCE = new ReadDidHelper();

        private ReadDidHelperHolder() {
        }
    }

    public static ReadDidHelper getInstance() {
        return ReadDidHelperHolder.INSTANCE;
    }

    public String readDid(String str) {
        DidRequest didRequest;
        LogUtils.d(TAG, "json: " + str);
        List<DidRequest> list = null;
        try {
            didRequest = parseContent(str);
        } catch (Exception unused) {
            didRequest = null;
        }
        try {
            list = parseArrayContent(str);
        } catch (Exception unused2) {
        }
        if (didRequest != null) {
            return readDid(didRequest);
        }
        if (list != null && !list.isEmpty()) {
            return readDid(list);
        }
        return JsonUtils.toJson(errorResponse(DidResponse.ResultEnum.REQUEST_INVALID));
    }

    private DidRequest parseContent(String str) {
        return (DidRequest) JsonUtils.fromJson(str, new TypeToken<DidRequest>() { // from class: com.xiaopeng.ota.helper.ReadDidHelper.1
        }.getType());
    }

    private List<DidRequest> parseArrayContent(String str) {
        return (List) JsonUtils.fromJson(str, new TypeToken<List<DidRequest>>() { // from class: com.xiaopeng.ota.helper.ReadDidHelper.2
        }.getType());
    }

    private String readDid(DidRequest didRequest) {
        return JsonUtils.toJson(readDidInner(didRequest));
    }

    private String readDid(List<DidRequest> list) {
        ArrayList arrayList = new ArrayList();
        for (DidRequest didRequest : list) {
            arrayList.add(readDidInner(didRequest));
        }
        return JsonUtils.toJson(arrayList);
    }

    private DidResponse readDidInner(DidRequest didRequest) {
        int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
        if (7 < OtaGetCampaignStatus && OtaGetCampaignStatus < 10) {
            LogUtils.e(TAG, "OTA upgrading");
            return errorResponse(DidResponse.ResultEnum.UPGRADING);
        }
        try {
            byte[] OtaReadDid = UpgradeUtils.OtaReadDid(didRequest.getAddress(), didRequest.getDid());
            if (OtaReadDid == null) {
                LogUtils.w(TAG, "Read did value=null");
                return errorResponse(DidResponse.ResultEnum.READ_ECU_FAIL);
            }
            return responseFromValue(didRequest, OtaReadDid);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Read did fail: address=" + didRequest.getAddress());
            return errorResponse(DidResponse.ResultEnum.READ_ECU_FAIL);
        }
    }

    private DidResponse responseFromValue(DidRequest didRequest, byte[] bArr) {
        DidResponse didResponse = new DidResponse();
        didResponse.setCode(DidResponse.ResultEnum.OK.getCode());
        didResponse.setMessage(DidResponse.ResultEnum.OK.getMessage());
        didResponse.setAddress(didRequest.getAddress());
        didResponse.setDid(didRequest.getDid());
        didResponse.setValue(Base64Utils.encode(bArr));
        return didResponse;
    }

    private DidResponse errorResponse(DidResponse.ResultEnum resultEnum) {
        DidResponse didResponse = new DidResponse();
        didResponse.setCode(resultEnum.getCode());
        didResponse.setMessage(resultEnum.getMessage());
        return didResponse;
    }
}

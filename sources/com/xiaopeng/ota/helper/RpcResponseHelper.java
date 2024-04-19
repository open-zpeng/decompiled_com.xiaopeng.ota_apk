package com.xiaopeng.ota.helper;

import android.text.TextUtils;
import com.xiaopeng.ota.bean.DidResponse;
import com.xiaopeng.ota.bean.RpcResponse;
/* loaded from: classes2.dex */
public class RpcResponseHelper {

    /* loaded from: classes2.dex */
    private static class Holder {
        static final RpcResponseHelper INSTANCE = new RpcResponseHelper();

        private Holder() {
        }
    }

    public static RpcResponseHelper getInstance() {
        return Holder.INSTANCE;
    }

    public RpcResponse successResponse() {
        DidResponse.ResultEnum resultEnum = DidResponse.ResultEnum.OK;
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(resultEnum.getCode());
        rpcResponse.setMessage(resultEnum.getMessage());
        return rpcResponse;
    }

    public RpcResponse successResponse(Integer num) {
        RpcResponse successResponse = successResponse();
        successResponse.setMode(num);
        return successResponse;
    }

    public RpcResponse errorResponse(String str) {
        RpcResponse errorResponse = errorResponse(DidResponse.ResultEnum.UDS_FAIL);
        if (!TextUtils.isEmpty(str)) {
            errorResponse.setStackTrace(str);
        }
        return errorResponse;
    }

    private RpcResponse errorResponse(DidResponse.ResultEnum resultEnum) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(resultEnum.getCode());
        rpcResponse.setMessage(resultEnum.getMessage());
        return rpcResponse;
    }
}

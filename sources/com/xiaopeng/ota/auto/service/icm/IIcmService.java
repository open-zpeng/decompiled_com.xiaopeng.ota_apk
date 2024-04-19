package com.xiaopeng.ota.auto.service.icm;

import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public interface IIcmService extends ICarService {
    boolean getIcmConnectionState() throws Exception;

    float getLastStartUpMileage() throws Exception;

    void sendBinMsg(int i, byte[] bArr, byte[] bArr2) throws Exception;

    void sendRomBinMsg(byte[] bArr) throws Exception;
}

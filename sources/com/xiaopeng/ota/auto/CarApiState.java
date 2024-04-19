package com.xiaopeng.ota.auto;

import com.xiaopeng.ota.auto.service.avm.AvmService;
import com.xiaopeng.ota.auto.service.bcm.BcmService;
import com.xiaopeng.ota.auto.service.ciu.CiuService;
import com.xiaopeng.ota.auto.service.icm.IcmService;
import com.xiaopeng.ota.auto.service.mcu.McuService;
import com.xiaopeng.ota.auto.service.srs.SrsService;
import com.xiaopeng.ota.auto.service.tbox.TBoxService;
import com.xiaopeng.ota.auto.service.vcu.VcuService;
/* loaded from: classes2.dex */
public class CarApiState {
    public static BcmService getBcm() {
        return (BcmService) CarApi.getCarApi(100);
    }

    public static McuService getMcu() {
        return (McuService) CarApi.getCarApi(101);
    }

    public static VcuService getVcu() {
        return (VcuService) CarApi.getCarApi(102);
    }

    public static IcmService getIcm() {
        return (IcmService) CarApi.getCarApi(103);
    }

    public static AvmService getAvm() {
        return (AvmService) CarApi.getCarApi(110);
    }

    public static CiuService getCiu() {
        return (CiuService) CarApi.getCarApi(111);
    }

    public static TBoxService getTBox() {
        return (TBoxService) CarApi.getCarApi(112);
    }

    public static SrsService getSrs() {
        return (SrsService) CarApi.getCarApi(113);
    }
}

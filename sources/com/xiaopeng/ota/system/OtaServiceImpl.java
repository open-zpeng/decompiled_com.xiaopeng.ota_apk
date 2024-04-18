package com.xiaopeng.ota.system;

import android.content.Context;
import android.os.RemoteException;
import com.xiaopeng.ota.IOtaService;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
/* loaded from: classes2.dex */
public class OtaServiceImpl extends IOtaService.Stub {
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OtaServiceImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.xiaopeng.ota.IOtaService
    public void promptSotaUpgrade(int i, int i2) throws RemoteException {
        DialogHelper.getInstance().promptSotaUpgrade(i, i2);
    }

    @Override // com.xiaopeng.ota.IOtaService
    public void closeSotaUpgrade() throws RemoteException {
        DialogHelper.getInstance().closeSotaUpgrade();
    }

    @Override // com.xiaopeng.ota.IOtaService
    public String getConfigString(String str) throws RemoteException {
        return ConfigHelper.getString(str);
    }

    @Override // com.xiaopeng.ota.IOtaService
    public long getConfigLong(String str) throws RemoteException {
        return ConfigHelper.getInt(str);
    }

    @Override // com.xiaopeng.ota.IOtaService
    public boolean hasCampaign() throws RemoteException {
        return OTAManager.getCampaignManager().hasCampaign();
    }
}

package com.xiaopeng.sota.sdk.client.dto;

import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import java.util.List;
/* loaded from: classes2.dex */
public class UpgradeResponse extends BaseResponse {
    private List<Campaign> data;

    public List<Campaign> getData() {
        return this.data;
    }

    public void setData(List<Campaign> list) {
        this.data = list;
    }
}

package com.xiaopeng.lib.apirouter.server;

import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class OtaService_Manifest {
    public static final String DESCRIPTOR = "com.xiaopeng.ota.system.OtaService";
    public static final int TRANSACTION_existNewVersion = 1;
    public static final int TRANSACTION_getOTAState = 0;
    public static final int TRANSACTION_getVcuMode = 2;
    public static final int TRANSACTION_readDid = 4;
    public static final int TRANSACTION_setVcuMode = 3;

    public static String toJsonManifest() {
        return "{\"authority\":\"com.xiaopeng.ota.OTAService\",\"DESCRIPTOR\":\"com.xiaopeng.ota.system.OtaService\",\"TRANSACTION\":[{\"path\":\"getOTAState\",\"METHOD\":\"getOTAState\",\"ID\":0,\"parameter\":[]},{\"path\":\"existNewVersion\",\"METHOD\":\"existNewVersion\",\"ID\":1,\"parameter\":[]},{\"path\":\"getVcuMode\",\"METHOD\":\"getVcuMode\",\"ID\":2,\"parameter\":[]},{\"path\":\"setVcuMode\",\"METHOD\":\"setVcuMode\",\"ID\":3,\"parameter\":[{\"alias\":\"mode\",\"name\":\"mode\"}]},{\"path\":\"readDid\",\"METHOD\":\"readDid\",\"ID\":4,\"parameter\":[{\"alias\":\"requestJsonContent\",\"name\":\"requestJsonContent\"}]}]}";
    }

    public static Set<String> getKey() {
        HashSet hashSet = new HashSet(2);
        hashSet.add("OtaService");
        hashSet.add("OTAService");
        return hashSet;
    }
}

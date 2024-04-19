package com.xiaopeng.ota.presenter.update.bean;

import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public class UpgradeResult {
    private List<String> conditionName;
    private List<String> conditionNotMatch;
    private List<String> ecuNotReady;
    private String from;
    private List<String> message;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.presenter.update.bean.UpgradeResult$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger = new int[UpgradeTrigger.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.CDU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.APP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.BACKEND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.SCHEDULE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.RPM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static boolean isUserTrigger(String str) {
        return AnonymousClass1.$SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.getByCode(str).ordinal()] == 1;
    }

    public static boolean isTimerTrigger(String str) {
        int i = AnonymousClass1.$SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.getByCode(str).ordinal()];
        return i == 2 || i == 3 || i == 4 || i == 5 || i == 6;
    }

    public static boolean isAppTrigger(String str) {
        return AnonymousClass1.$SwitchMap$com$xiaopeng$ota$presenter$update$bean$UpgradeTrigger[UpgradeTrigger.getByCode(str).ordinal()] == 3;
    }

    public boolean isUserTrigger() {
        return isUserTrigger(getFrom());
    }

    public boolean isEcuNotReady() {
        return (getEcuNotReady() == null || getEcuNotReady().isEmpty()) ? false : true;
    }

    public List<String> getDecodeList(List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            return list;
        }
        final ArrayList arrayList = new ArrayList();
        list.stream().forEach(new Consumer() { // from class: com.xiaopeng.ota.presenter.update.bean.-$$Lambda$UpgradeResult$y4BcoVi13JLEzaIPuR2z1sknMPc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                UpgradeResult.lambda$getDecodeList$0(arrayList, (String) obj);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getDecodeList$0(List list, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        list.add(Base64Utils.decodeString(str).trim());
    }

    public List<String> getMessage() {
        return this.message;
    }

    public void setMessage(List<String> list) {
        this.message = list;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public List<String> getEcuNotReady() {
        return this.ecuNotReady;
    }

    public void setEcuNotReady(List<String> list) {
        this.ecuNotReady = list;
    }

    public List<String> getConditionNotMatch() {
        return this.conditionNotMatch;
    }

    public void setConditionNotMatch(List<String> list) {
        this.conditionNotMatch = list;
    }

    public List<String> getConditionName() {
        return this.conditionName;
    }

    public void setConditionName(List<String> list) {
        this.conditionName = list;
    }
}

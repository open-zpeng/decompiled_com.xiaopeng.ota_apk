package com.xiaopeng.ota.presenter.prompt;

import android.text.TextUtils;
import com.xiaopeng.ota.bean.AiAction;
import com.xiaopeng.ota.bean.AiScene;
/* loaded from: classes2.dex */
public class AiContent {
    private static final String SHARP = "#";
    private AiAction action;
    private long campaignId;
    private boolean dialog;
    private AiScene scene;

    public static AiContent parse(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split("#")) == null || split.length <= 0) {
            return null;
        }
        if (split.length < 3) {
            return parsePair(split);
        }
        return parse(split);
    }

    private static AiContent parsePair(String[] strArr) {
        long longValue;
        AiAction actionByOp = AiAction.getActionByOp(strArr[0]);
        if (strArr.length > 1) {
            try {
                longValue = Long.valueOf(strArr[1]).longValue();
            } catch (NumberFormatException unused) {
            }
            return new AiContent(actionByOp, longValue);
        }
        longValue = 0;
        return new AiContent(actionByOp, longValue);
    }

    private static AiContent parse(String[] strArr) {
        if (strArr != null && strArr.length < 3) {
            return parsePair(strArr);
        }
        AiScene aiScene = null;
        try {
            aiScene = AiScene.getByScene(Integer.parseInt(strArr[0]));
        } catch (NumberFormatException unused) {
        }
        AiAction actionByOp = AiAction.getActionByOp(strArr[1]);
        long j = 0;
        try {
            j = Long.valueOf(strArr[2]).longValue();
        } catch (NumberFormatException unused2) {
        }
        return new AiContent(aiScene, actionByOp, j);
    }

    public String toContentString() {
        return this.scene.getScene() + "#" + this.action.getOp() + "#" + this.campaignId;
    }

    public AiContent(AiAction aiAction, long j) {
        this(null, aiAction, j);
    }

    public AiContent(AiScene aiScene, AiAction aiAction, long j) {
        this.dialog = false;
        this.scene = aiScene;
        this.action = aiAction;
        this.campaignId = j;
    }

    public AiScene getScene() {
        return this.scene;
    }

    public void setScene(AiScene aiScene) {
        this.scene = aiScene;
    }

    public AiAction getAction() {
        return this.action;
    }

    public void setAction(AiAction aiAction) {
        this.action = aiAction;
    }

    public long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(long j) {
        this.campaignId = j;
    }

    public boolean isDialog() {
        return this.dialog;
    }

    public void setDialog(boolean z) {
        this.dialog = z;
    }
}

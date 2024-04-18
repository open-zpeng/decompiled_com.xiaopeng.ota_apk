package com.xiaopeng.ota.presenter.update;

import android.content.Context;
import com.xiaopeng.fota.sdk.OtaUePackage;
import com.xiaopeng.fota.sdk.UpgradeExecutorBase;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.presenter.config.PreferencesManager;
import com.xiaopeng.ota.presenter.update.cdu.RebootWrapper;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
/* loaded from: classes2.dex */
public abstract class BaseExecutor implements UpgradeExecutorBase {
    private static final String TAG = "BaseExecutor";
    protected Context mContext;
    protected volatile int mProgress;
    protected RebootWrapper mRebootWrapper;
    protected PreferencesManager mPreferencesManager = OTAManager.getPreferencesManager();
    protected volatile short mState = this.mPreferencesManager.getUeState(getId());

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public interface TaskListener {
        void onExecuteTask() throws Exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$commit$7() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$rollback$6() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$upgrade$4() throws Exception {
    }

    /* loaded from: classes2.dex */
    public class ExecutorException extends Exception {
        private int mResult;

        public ExecutorException(int i, String str) {
            super(str);
            this.mResult = i;
        }

        int getResult() {
            return this.mResult;
        }
    }

    public BaseExecutor(Context context) {
        this.mContext = context;
        this.mRebootWrapper = new RebootWrapper(context);
        if (6 == this.mState || 5 == this.mState || 2 == this.mState) {
            this.mProgress = 100;
        }
    }

    /* renamed from: release */
    public void lambda$campaignCompleted$1$BaseExecutor() {
        this.mPreferencesManager.setCampaignId(getId(), 0L);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getStatus() {
        UpgradeUtils.OtaUpgradeProgress(this.mPreferencesManager.getCampaignId(getId()), (short) getId(), this.mState, this.mProgress);
        return 0;
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int campaignStarted(final long j) {
        return runTask(j, (short) 3, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$2SB1Ouck6yiGZ12FL1suxMSZUO8
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.this.lambda$campaignStarted$0$BaseExecutor(j);
            }
        });
    }

    public /* synthetic */ void lambda$campaignStarted$0$BaseExecutor(long j) throws Exception {
        this.mPreferencesManager.setCampaignId(getId(), j);
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int campaignCompleted(long j) {
        int runTask = runTask(j, (short) 9, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$mGYe8GNQeMSwqbP-N8FldNmWXHk
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.this.lambda$campaignCompleted$1$BaseExecutor();
            }
        });
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$uLQVNh9UxO2L2IR30HU2ffBf1PE
            @Override // java.lang.Runnable
            public final void run() {
                BaseExecutor.this.lambda$campaignCompleted$2$BaseExecutor();
            }
        }, 5000L);
        return runTask;
    }

    public /* synthetic */ void lambda$campaignCompleted$2$BaseExecutor() {
        this.mState = (short) 0;
        this.mProgress = 0;
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int prepare(final OtaUePackage otaUePackage) {
        LogUtils.d(TAG, "prepare, campaignId: " + otaUePackage.getCampaignId());
        return runTask(otaUePackage.getCampaignId(), (short) 2, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$mBLzzxxthRPPPEcP9nXj_bExyOo
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.this.lambda$prepare$3$BaseExecutor(otaUePackage);
            }
        });
    }

    public /* synthetic */ void lambda$prepare$3$BaseExecutor(OtaUePackage otaUePackage) throws Exception {
        this.mPreferencesManager.setCampaignId(getId(), otaUePackage.getCampaignId());
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int upgrade(OtaUePackage otaUePackage, byte b) {
        LogUtils.d(TAG, "upgrade, zone: " + ((int) b) + ", package: " + JsonUtils.toJson(otaUePackage));
        return runTask(otaUePackage.getCampaignId(), (short) 4, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$StPPsn3hE2U51Xm0dmFLu-iF-0o
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.lambda$upgrade$4();
            }
        });
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int activate(long j, byte b) {
        LogUtils.d(TAG, "activate, campaignId: " + j + ", zone: " + ((int) b));
        if (this.mState != 4) {
            LogUtils.e(TAG, "State or progress not right, state: %d, progress: %d", Short.valueOf(this.mState), Integer.valueOf(this.mProgress));
            return 0;
        }
        return runTask(j, (short) 5, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$Pi1wI5aTmQTuFDJpRtEgptYNo-0
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.this.lambda$activate$5$BaseExecutor();
            }
        });
    }

    public /* synthetic */ void lambda$activate$5$BaseExecutor() throws Exception {
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int rollback(long j, byte b) {
        LogUtils.d(TAG, "rollback, campaignId: " + j + ", zone: " + ((int) b));
        return runTask(j, (short) 7, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$1CywIqYJW4ldhnOr_6YeqbJdoHI
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.lambda$rollback$6();
            }
        });
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int commit(long j) {
        LogUtils.d(TAG, "commit, campaignId: " + j);
        return runTask(j, (short) 8, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$r13SHbt159KmrR7k-XYEYinmDtc
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.lambda$commit$7();
            }
        });
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int reset(long j, byte b) {
        LogUtils.d(TAG, "reset, campaignId: " + j + ", mode: " + ((int) b));
        sendProgress(j, (short) 6, 0);
        return runTask(j, (short) 6, new TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$BaseExecutor$wgO73M7xMKvPvZ7qAdi9AgGYfV0
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                BaseExecutor.this.lambda$reset$8$BaseExecutor();
            }
        });
    }

    public /* synthetic */ void lambda$reset$8$BaseExecutor() throws Exception {
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public String challenge(String str) {
        LogUtils.d(TAG, "challenge, question: " + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int runTask(long j, short s, TaskListener taskListener) {
        sendProgress(j, s, 0);
        try {
            taskListener.onExecuteTask();
            sendProgress(j, s, 100);
            return 0;
        } catch (ExecutorException e) {
            sendProgress(j, s, e.getResult(), e.getMessage());
            return e.getResult();
        } catch (Exception e2) {
            sendProgress(j, s, -49, e2.getMessage());
            LogUtils.w(TAG, e2, "Exception, state: " + ((int) this.mState));
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendProgress(long j, short s, int i) {
        sendProgress(j, s, i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendProgress(long j, short s, int i, String str) {
        this.mState = s;
        this.mProgress = i;
        UpgradeUtils.OtaUpgradeProgress(j, (short) getId(), this.mState, this.mProgress, str);
    }
}

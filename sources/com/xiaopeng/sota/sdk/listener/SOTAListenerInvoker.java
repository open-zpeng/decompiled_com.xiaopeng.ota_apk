package com.xiaopeng.sota.sdk.listener;

import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.CheckUtils;
import com.xiaopeng.sota.sdk.SOTAExecutor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class SOTAListenerInvoker {
    private static final String TAG = "SOTAListenerInvoker";
    private final CopyOnWriteArrayList<SOTAListener> mListeners = new CopyOnWriteArrayList<>();

    public void register(SOTAListener sOTAListener) {
        CheckUtils.isNotNull(sOTAListener);
        synchronized (this.mListeners) {
            if (!this.mListeners.contains(sOTAListener)) {
                this.mListeners.add(sOTAListener);
            }
        }
    }

    public void unregister(SOTAListener sOTAListener) {
        CheckUtils.isNotNull(sOTAListener);
        synchronized (this.mListeners) {
            if (this.mListeners.contains(sOTAListener)) {
                this.mListeners.remove(sOTAListener);
            }
        }
    }

    private List<SOTAListener> getListeners() {
        return this.mListeners;
    }

    public void fireNewVersion(final List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            Logger.d(TAG, "fireNewVersion, param empty", new Object[0]);
            return;
        }
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.1
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onNewVersion(list);
                }
            });
        }
    }

    public void fireUpgradeInvalid() {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.2
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onUpgradeInvalid();
                }
            });
        }
    }

    public void fireDownloadReady(final List<String> list, final long j) {
        if (ArrayUtils.isEmpty(list)) {
            Logger.d(TAG, "fireDownloadReady, package name list empty", new Object[0]);
            return;
        }
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.3
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloadReady(list, j);
                }
            });
        }
    }

    public void fireDownloadStart(final String str, final long j) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.4
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloadStart(str, j);
                }
            });
        }
    }

    public void fireDownloading(final String str, final long j, final long j2) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.5
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloading(str, j, j2);
                }
            });
        }
    }

    public void fireDownloadEnd(final String str) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.6
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloadEnd(str);
                }
            });
        }
    }

    public void fireDownloadError(final String str, final Exception exc) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.7
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloadError(str, exc);
                }
            });
        }
    }

    public void fireDownloadComplete() {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.8
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onDownloadComplete();
                }
            });
        }
    }

    public void fireInstallReady(final int i) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.9
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onInstallReady(i);
                }
            });
        }
    }

    public void fireInstallStart(final String str, final int i, final int i2, final int i3) {
        Logger.d(TAG, "fireInstallStart, listener list size:" + getListeners().size(), new Object[0]);
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.10
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onInstallStart(str, i, i2, i3);
                }
            });
        }
    }

    public void canPackageInstalled(String str) throws Exception {
        for (SOTAListener sOTAListener : getListeners()) {
            sOTAListener.canPackageInstalled(str);
        }
    }

    public void fireInstallError(final String str, final Exception exc) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.11
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onInstallError(str, exc);
                }
            });
        }
    }

    public void fireInstallationResult(final String str, final boolean z) {
        Logger.d(TAG, "fireInstallationResult(packageName=%s, success=%b), listener list size:" + getListeners().size(), str, Boolean.valueOf(z));
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.12
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onInstallationResult(str, z);
                }
            });
        }
    }

    public void fireInstallComplete() {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.13
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onInstallComplete();
                }
            });
        }
    }

    public void fireCampaignCheckResult(final boolean z) {
        for (final SOTAListener sOTAListener : getListeners()) {
            SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker.14
                @Override // java.lang.Runnable
                public void run() {
                    sOTAListener.onCampaignCheckResult(z);
                }
            });
        }
    }

    public void dispose() {
        this.mListeners.clear();
    }
}

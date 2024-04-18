package com.xiaopeng.sota.sdk.listener;

import java.util.List;
/* loaded from: classes2.dex */
public abstract class SOTAListener {
    public void canPackageInstalled(String str) throws Exception {
    }

    public void onCampaignCheckResult(boolean z) {
    }

    public void onDownloadComplete() {
    }

    public void onDownloadEnd(String str) {
    }

    public void onDownloadError(String str, Exception exc) {
    }

    public void onDownloadReady(List<String> list, long j) {
    }

    public void onDownloadStart(String str, long j) {
    }

    public void onDownloading(String str, long j, long j2) {
    }

    public void onInstallComplete() {
    }

    public void onInstallError(String str, Exception exc) {
    }

    public void onInstallReady(int i) {
    }

    public void onInstallStart(String str, int i, int i2, int i3) {
    }

    public void onInstallationResult(String str, boolean z) {
    }

    public void onNewVersion(List<String> list) {
    }

    public void onUpgradeInvalid() {
    }
}

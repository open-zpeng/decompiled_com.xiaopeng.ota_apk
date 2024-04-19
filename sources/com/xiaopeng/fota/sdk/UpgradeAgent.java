package com.xiaopeng.fota.sdk;

import android.app.Application;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import java.io.File;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class UpgradeAgent implements UpgradeAgentBase {
    private static final String TAG = "OtaAgent";
    private IHttp httpAccessor;
    private Downloader downloader = null;
    private Decrypter fileDecrypter = null;
    private Decrypter dataDecrypter = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int decryptDataFinished(long j, byte[] bArr, int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int decryptFileFinished(long j, long j2, String str, String str2, int i, String str3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int downloadFinished(long j, long j2, String str, int i, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int downloadProgress(long j, long j2, String str, long j3);

    static native int httpResponse(long j, int i, String str, int i2, String[] strArr, String str2);

    private synchronized IHttp getHttpAccessor() {
        if (this.httpAccessor == null) {
            this.httpAccessor = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            if (this.httpAccessor == null) {
                Log.w(TAG, "Http accessor is null", new Object[0]);
            } else {
                int httpConnectTimeout = UpgradeUtils.getHttpConnectTimeout(30);
                int httpReadTimeout = UpgradeUtils.getHttpReadTimeout(60);
                Log.i(TAG, "Http timeout %d, %d", Integer.valueOf(httpConnectTimeout), Integer.valueOf(httpReadTimeout));
                this.httpAccessor.config().applicationContext((Application) UpgradeUtils.context()).retryCount(1).connectTimeout(httpConnectTimeout * 1000).readTimeout(httpReadTimeout * 1000).enableTrafficStats().apply();
            }
        }
        return this.httpAccessor;
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeAgentBase
    public int httpRequest(final long j, final int i, final String str, String str2, String str3) {
        IRequest post;
        String[] split;
        IHttp httpAccessor = getHttpAccessor();
        if (httpAccessor == null) {
            return -52;
        }
        if (i == 0) {
            post = httpAccessor.get(str);
        } else {
            post = httpAccessor.post(str);
        }
        if (str2 != null) {
            for (String str4 : str2.split("\\[]")) {
                int indexOf = str4.indexOf(58);
                if (indexOf > 0) {
                    post.headers(str4.substring(0, indexOf), str4.substring(indexOf + 1).trim());
                }
            }
        }
        if (2 == i) {
            post.isMultipart(true).params("file", new File(str3));
        } else if (!str3.isEmpty()) {
            post.uploadJson(str3);
        }
        post.execute(new Callback() { // from class: com.xiaopeng.fota.sdk.UpgradeAgent.1
            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onSuccess(IResponse iResponse) {
                UpgradeAgent.httpResponse(j, i, str, iResponse.code(), null, iResponse.body());
            }

            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onFailure(IResponse iResponse) {
                if (iResponse == null) {
                    UpgradeAgent.httpResponse(j, i, str, -27, null, null);
                    return;
                }
                int code = iResponse.code();
                if (code == 0) {
                    code = -27;
                }
                UpgradeAgent.httpResponse(j, i, str, code, null, iResponse.body());
            }
        });
        return 0;
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeAgentBase
    public int download(long j, long j2, String str, long j3, String str2, String str3) {
        IHttp httpAccessor = getHttpAccessor();
        if (httpAccessor == null) {
            return -52;
        }
        Downloader downloader = this.downloader;
        if (downloader != null) {
            downloader.stop();
            String[] split = str3.split("\\[]");
            if (this.downloader.isSame(str, str2)) {
                this.downloader.update(j, j2, j3, split);
                return this.downloader.start(httpAccessor);
            }
            this.downloader = null;
        }
        if (0 == j2 || str.isEmpty()) {
            return 0;
        }
        this.downloader = new Downloader(j, j2, str, j3, str2, str3.split("\\[]"));
        return this.downloader.start(httpAccessor);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeAgentBase
    public int decrypt(long j, long j2, String str, String str2, String str3) {
        if (str2.isEmpty() || str3.isEmpty()) {
            Decrypter decrypter = this.fileDecrypter;
            if (decrypter != null && !decrypter.isFinished()) {
                this.fileDecrypter.abort();
            }
            this.fileDecrypter = null;
            return 0;
        }
        Decrypter decrypter2 = this.fileDecrypter;
        if (decrypter2 != null && !decrypter2.isFinished()) {
            return this.fileDecrypter.isSame(str, str2) ? -18 : -54;
        }
        this.fileDecrypter = new Decrypter(j, j2, str, str2, str3);
        return 0;
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeAgentBase
    public int decrypt(long j, byte[] bArr) {
        Decrypter decrypter = this.dataDecrypter;
        if (decrypter != null && !decrypter.isFinished()) {
            return this.dataDecrypter.isSame(j) ? -18 : -54;
        }
        this.dataDecrypter = new Decrypter(j, bArr);
        return 0;
    }
}

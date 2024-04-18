package com.airbnb.lottie.network;

import androidx.annotation.NonNull;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes.dex */
public class DefaultLottieNetworkFetcher implements LottieNetworkFetcher {
    @Override // com.airbnb.lottie.network.LottieNetworkFetcher
    @NonNull
    public LottieFetchResult fetchSync(@NonNull String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(OTAConstants.HttpMethod.GET);
        httpURLConnection.connect();
        return new DefaultLottieFetchResult(httpURLConnection);
    }
}

package com.xiaopeng.lib.apirouter.server;

import android.os.IBinder;
import android.util.Pair;
import androidx.annotation.Keep;
import java.util.HashMap;
@Keep
/* loaded from: classes2.dex */
public class ManifestHelper {
    public static HashMap<String, Pair<IBinder, String>> mapping = new HashMap<>();

    static {
        Pair<IBinder, String> pair = new Pair<>(new OtaService_Stub(), OtaService_Manifest.toJsonManifest());
        for (String str : OtaService_Manifest.getKey()) {
            mapping.put(str, pair);
        }
        Pair<IBinder, String> pair2 = new Pair<>(new SpeechEventObserver_Stub(), SpeechEventObserver_Manifest.toJsonManifest());
        for (String str2 : SpeechEventObserver_Manifest.getKey()) {
            mapping.put(str2, pair2);
        }
    }
}

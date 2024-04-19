package com.xiaopeng.ota.system;

import android.content.Context;
import android.os.FileUtils;
import android.os.storage.StorageEventListener;
import android.text.TextUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.activity.UsbFragment;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class UsbDiskListener extends StorageEventListener {
    private static final String SEPARATOR = "/";
    private static final String TAG = "UsbDiskListener";
    private static final String USB_KEY_NAME = "key";
    private static final String USB_PATH_PREFIX = "/storage/";
    private Context mContext;

    public UsbDiskListener(Context context) {
        this.mContext = context;
    }

    public void onStorageStateChanged(final String str, String str2, String str3) {
        super.onStorageStateChanged(str, str2, str3);
        LogUtils.d(TAG, "onStorageStateChanged: " + str);
        if (!TextUtils.isEmpty(str) && str.startsWith(USB_PATH_PREFIX)) {
            LogUtils.d(TAG, "newState: " + str3);
            if (str3.equals("mounted")) {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$UsbDiskListener$z-HDUqq6i4Y_X3sjr373nIzMKHc
                    @Override // java.lang.Runnable
                    public final void run() {
                        UsbDiskListener.this.lambda$onStorageStateChanged$0$UsbDiskListener(str);
                    }
                });
            } else if (str3.equals("unmounted")) {
                ActivityHelper.finishActivity(FragmentActivity.class);
            }
        }
    }

    public /* synthetic */ void lambda$onStorageStateChanged$0$UsbDiskListener(String str) {
        verifyKeyAndShowActivity(this.mContext, str);
    }

    public static void verifyKeyAndShowActivity(Context context, String str) {
        String str2 = str + "/" + ConfigHelper.getString(Constants.ConfigKey.USB_UPDATE_FOLDER) + "/";
        String str3 = null;
        try {
            str3 = FileUtils.readTextFile(new File(str2 + "key"), 1024, null);
        } catch (IOException e) {
            LogUtils.w(TAG, e, "ready key file fail");
        }
        if ("1234567890".equals(str3)) {
            HashMap hashMap = new HashMap();
            hashMap.put(Config.EXTRA_KEY_OTA_FOLDER, str2);
            ActivityHelper.startFragment(context, UsbFragment.class, hashMap);
        }
    }
}

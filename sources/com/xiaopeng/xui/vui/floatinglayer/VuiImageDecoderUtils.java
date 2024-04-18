package com.xiaopeng.xui.vui.floatinglayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.xiaopeng.sota.sdk.common.Constants;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.utils.XLogUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class VuiImageDecoderUtils {
    private static final String TAG = "VuiImageDecoderUtils";
    private static final String TOUCH_DEFAULT_WEBP = "anim/floating_touch.webp";
    private static final String TOUCH_DEFAULT_WEBP_NIGHT = "anim/floating_touch_night.webp";
    private static List<String> sAssetsFileList;

    public static int getAnimateTimeOut(int i) {
        if (i != 1) {
            return Constants.SELF_UPGRADE_DELAY;
        }
        return 5500;
    }

    public static boolean isSupportAlpha(int i) {
        return false;
    }

    public static boolean isSupportNight(int i) {
        return i == 1;
    }

    @TargetApi(28)
    public static Drawable decoderImage(@NonNull Context context, int i, boolean z) {
        boolean animAssetsContains;
        XLogUtils.d(TAG, "decoderImage type : " + i + ", isNight : " + z);
        if (i != 1) {
            String str = TOUCH_DEFAULT_WEBP_NIGHT;
            boolean z2 = false;
            if (z) {
                animAssetsContains = false;
                z2 = animAssetsContains(context, TOUCH_DEFAULT_WEBP_NIGHT);
            } else {
                animAssetsContains = animAssetsContains(context, TOUCH_DEFAULT_WEBP);
            }
            if (!z || !z2) {
                str = animAssetsContains ? TOUCH_DEFAULT_WEBP : null;
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    return ImageDecoder.decodeDrawable(ImageDecoder.createSource(context.getAssets(), str));
                } catch (IOException e) {
                    XLogUtils.w(TAG, "decodeException:", e);
                    return null;
                }
            }
            XLogUtils.e(TAG, "decode error, file not found");
            return null;
        }
        return new VuiFloatingDrawable(BitmapFactory.decodeResource(context.getResources(), R.drawable.floating_element));
    }

    @TargetApi(28)
    public static Drawable decoderImage(@NonNull Context context, int i) {
        return decoderImage(context, i, false);
    }

    private static boolean animAssetsContains(Context context, String str) {
        if (str.startsWith("anim/")) {
            str = str.replace("anim/", "");
        }
        try {
            if (sAssetsFileList == null) {
                sAssetsFileList = Arrays.asList(context.getResources().getAssets().list("anim"));
            }
            String str2 = TAG;
            XLogUtils.d(str2, "assetsContains, assets:" + sAssetsFileList);
            return sAssetsFileList.contains(str);
        } catch (Exception e) {
            String str3 = TAG;
            XLogUtils.w(str3, "assetsContains call ex:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

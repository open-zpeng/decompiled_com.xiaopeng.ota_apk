package com.xiaopeng.ota.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes2.dex */
public class BitmapUtils {
    public static Bitmap revitionImageSize(String str, int i, int i2) throws IOException {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(bufferedInputStream, null, options);
            bufferedInputStream.close();
            int i3 = 0;
            while (true) {
                if ((options.outWidth >> i3) <= i && (options.outHeight >> i3) <= i2) {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(new File(str)));
                    options.inSampleSize = (int) Math.pow(2.0d, i3);
                    options.inJustDecodeBounds = false;
                    return BitmapFactory.decodeStream(bufferedInputStream2, null, options);
                }
                i3++;
            }
        } catch (Exception unused) {
            return null;
        }
    }
}

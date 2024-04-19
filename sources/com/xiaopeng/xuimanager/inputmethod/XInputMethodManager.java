package com.xiaopeng.xuimanager.inputmethod;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
/* loaded from: classes2.dex */
public class XInputMethodManager {
    public static boolean getInputShown(Context context) {
        return ((InputMethodManager) context.getSystemService("input_method")).getInputShown();
    }

    public static void forceHideSoftInputMethod(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).forceHideSoftInputMethod();
    }
}

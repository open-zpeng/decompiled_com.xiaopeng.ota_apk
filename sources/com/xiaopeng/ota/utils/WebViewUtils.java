package com.xiaopeng.ota.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaopeng.libtheme.ThemeManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* loaded from: classes2.dex */
public class WebViewUtils {
    private static final String CONTENT_DAY_TAG = "\"ota-day-mode";
    private static final String CONTENT_NIGHT_TAG = "\"ota-night-mode";
    private static final String TAG = "WebViewUtils";
    private static final long WEBVIEW_DATA_MAX = 2097152;

    public static void hookWebView() {
        Method declaredMethod;
        int i = Build.VERSION.SDK_INT;
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                Log.i(TAG, "sProviderInstance isn't null");
                return;
            }
            if (i > 22) {
                declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            } else if (i == 22) {
                declaredMethod = cls.getDeclaredMethod("getFactoryClass", new Class[0]);
            } else {
                Log.i(TAG, "Don't need to Hook WebView");
                return;
            }
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            if (i < 26) {
                Constructor constructor = cls2.getConstructor(cls3);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    obj = constructor.newInstance(declaredConstructor.newInstance(new Object[0]));
                }
            } else {
                Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                declaredField2.setAccessible(true);
                String str = (String) declaredField2.get(null);
                if (str == null) {
                    str = "create";
                }
                Method method = cls2.getMethod(str, cls3);
                if (method != null) {
                    obj = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
                }
            }
            if (obj != null) {
                declaredField.set("sProviderInstance", obj);
                Log.i(TAG, "Hook success!");
                return;
            }
            Log.i(TAG, "Hook failed!");
        } catch (Throwable th) {
            Log.w(TAG, th);
        }
    }

    public static void initWebView(WebView webView) {
        initWebView(webView, null, null);
    }

    public static void initWebView(WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient) {
        webView.setBackgroundColor(0);
        webView.setLayerType(2, null);
        if (webChromeClient != null) {
            webView.setWebChromeClient(webChromeClient);
        } else {
            webView.setWebChromeClient(new WebChromeClient());
        }
        if (webViewClient != null) {
            webView.setWebViewClient(webViewClient);
        } else {
            webView.setWebViewClient(new WebViewClient());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            WebSettings settings = webView.getSettings();
            webView.getSettings();
            settings.setMixedContentMode(0);
        }
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setAllowFileAccessFromFileURLs(false);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(false);
    }

    public static void showWVContent(Context context, WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.d(TAG, "WebView data is empty");
        } else if (str.length() > 2097152) {
            LogUtils.d(TAG, "WebView data over length(max=2097152)");
        } else {
            if (ThemeManager.isNightMode(context)) {
                str = switch2NightTheme(str);
            }
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }
    }

    private static String switch2NightTheme(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace(CONTENT_DAY_TAG, CONTENT_NIGHT_TAG);
    }
}

package com.xiaopeng.ota.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.WebViewVersionFragment;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.presenter.update.bean.FeatureResource;
import com.xiaopeng.ota.sdk.common.util.DigestUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.WebViewUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public abstract class WebViewVersionFragment extends BaseFragment {
    private static final String DIR_FOTA = "fota";
    private static final String JS_CLIENT_NAME = "OtaClient";
    private static final int NEW_VERSION_WINDOW_HEIGHT = 750;
    private static final int PORTRAIT_NEW_VERSION_WINDOW_HEIGHT = 1355;
    private static final int PORTRAIT_VERSION_DETAIL_WINDOW_HEIGHT = 1480;
    private static final String RES_CURRENT = "res_cur";
    private static final String RES_NEW = "res_new";
    protected static final int SOURCE_DETAIL = 2;
    protected static final int SOURCE_NEW_VERSION = 1;
    private static final String TAG = "WebViewVersionFragment";
    private static final int VERSION_DETAIL_WINDOW_HEIGHT = 860;
    private static final double WINDOW_HEIGHT_RATE = 0.4d;
    private TitleIndexChangedListener mListener;
    private int mScrollY;
    private int[] mTitleOffset;
    private int mWindowHeight;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface TitleIndexChangedListener {
        void onChanged(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$init$0(View view) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int i, WebView webView, List<Feature> list) {
        setWindowHeight(getWindowHeight(i));
        WebViewUtils.initWebView(webView, new AnonymousClass1(CampaignFeatureHelper.getResourceMap(list)), null);
        webView.setLongClickable(false);
        webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$nXGwqo7BsDvAL-LA5da3_mm8Tmc
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return WebViewVersionFragment.lambda$init$0(view);
            }
        });
        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$2iMNQotijKPLM27c1ldshByrDaI
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i2, int i3, int i4, int i5) {
                WebViewVersionFragment.this.lambda$init$1$WebViewVersionFragment(view, i2, i3, i4, i5);
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$rLoTROkG694dfHQvdsdGg0wwHOI
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return WebViewVersionFragment.this.lambda$init$2$WebViewVersionFragment(view, motionEvent);
            }
        });
        webView.addJavascriptInterface(new JsInterface(i), JS_CLIENT_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.WebViewVersionFragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends WebViewClient {
        final /* synthetic */ Map val$resourceMap;

        AnonymousClass1(Map map) {
            this.val$resourceMap = map;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(final WebView webView, String str) {
            super.onPageFinished(webView, str);
            webView.post(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$1$ySfRMSvoZE8GlsFnKML70deUp9M
                @Override // java.lang.Runnable
                public final void run() {
                    WebViewVersionFragment.AnonymousClass1.this.lambda$onPageFinished$1$WebViewVersionFragment$1(webView);
                }
            });
        }

        public /* synthetic */ void lambda$onPageFinished$1$WebViewVersionFragment$1(WebView webView) {
            webView.evaluateJavascript("javascript:getTabContentOffsets()", new ValueCallback() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$1$-iJXWeDocQLl3-N78h1Cb2Oy0_s
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    WebViewVersionFragment.AnonymousClass1.this.lambda$null$0$WebViewVersionFragment$1((String) obj);
                }
            });
        }

        public /* synthetic */ void lambda$null$0$WebViewVersionFragment$1(String str) {
            String[] split;
            try {
                if (TextUtils.isEmpty(str) || (split = str.replaceAll("\"", "").split(",")) == null || split.length <= 0) {
                    return;
                }
                WebViewVersionFragment.this.mTitleOffset = Arrays.stream(split).mapToInt(new ToIntFunction() { // from class: com.xiaopeng.ota.activity.-$$Lambda$wddj3-hVVrg0MkscpMtYt3BzY8Y
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return Integer.parseInt((String) obj);
                    }
                }).toArray();
            } catch (Exception unused) {
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebResourceResponse interceptRequest = WebViewVersionFragment.this.interceptRequest(this.val$resourceMap, webView, webResourceRequest);
            return interceptRequest != null ? interceptRequest : super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            if (webResourceRequest.getUrl().toString().startsWith("xiaopeng")) {
                Intent intent = new Intent("android.intent.action.VIEW", webResourceRequest.getUrl());
                boolean z = false;
                if (intent.resolveActivity(OTAManager.getContext().getPackageManager()) != null) {
                    intent.setFlags(270532608);
                    WebViewVersionFragment.this.startActivity(intent);
                    z = true;
                }
                WebViewVersionFragment.this.sendLinkClickedEventAsync(webResourceRequest.getUrl().toString(), z);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }
    }

    public /* synthetic */ void lambda$init$1$WebViewVersionFragment(View view, int i, int i2, int i3, int i4) {
        this.mScrollY = i2;
    }

    public /* synthetic */ boolean lambda$init$2$WebViewVersionFragment(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        changeIndexByScroll();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLinkClickedEventAsync(final String str, final boolean z) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$9kgBh2mCquBWTwB3rwA1n9YRM8k
            @Override // java.lang.Runnable
            public final void run() {
                EventPresenter.getInstance().sendH5Link(str, OTAManager.getOsVersionCode(), z);
            }
        });
    }

    protected File getWebViewCachePath(String str) {
        return new File(getContext().getDir("fota", 0), str);
    }

    protected WebResourceResponse interceptRequest(Map<String, FeatureResource> map, WebView webView, WebResourceRequest webResourceRequest) {
        if (map == null || webResourceRequest.getUrl() == null || !map.containsKey(webResourceRequest.getUrl().toString())) {
            return null;
        }
        FeatureResource featureResource = map.get(webResourceRequest.getUrl().toString());
        File webViewCachePath = getWebViewCachePath(RES_NEW);
        LogUtils.d(getFragmentName(), "Web view cache path: %s", webViewCachePath.getAbsolutePath());
        File file = new File(webViewCachePath, DigestUtils.MD5(featureResource.getDownloadUrl()));
        if (!file.isFile() || !file.exists()) {
            File webViewCachePath2 = getWebViewCachePath(RES_CURRENT);
            LogUtils.d(getFragmentName(), "Web view cache path: %s", webViewCachePath2.getAbsolutePath());
            file = new File(webViewCachePath2, DigestUtils.MD5(featureResource.getDownloadUrl()));
        }
        if (file.isFile() && file.exists()) {
            try {
                return new WebResourceResponse(featureResource.getMimeType(), "utf-8", new FileInputStream(file));
            } catch (Exception e) {
                LogUtils.e(getFragmentName(), e, "Load local file fail");
                return null;
            }
        }
        LogUtils.d(getFragmentName(), "File(%s) not found", file.getAbsolutePath());
        return null;
    }

    private void changeIndexByScroll() {
        TitleIndexChangedListener titleIndexChangedListener = this.mListener;
        if (titleIndexChangedListener != null) {
            titleIndexChangedListener.onChanged(calculateIndex());
        }
    }

    private int calculateIndex() {
        int[] iArr = this.mTitleOffset;
        if (iArr != null && iArr.length != 0) {
            if (this.mScrollY < iArr[iArr.length - 1]) {
                int i = 0;
                while (true) {
                    int[] iArr2 = this.mTitleOffset;
                    if (i >= iArr2.length) {
                        break;
                    }
                    int i2 = this.mScrollY;
                    if (i2 <= iArr2[i]) {
                        return ((double) (iArr2[i] - i2)) <= ((double) this.mWindowHeight) * WINDOW_HEIGHT_RATE ? i : i - 1;
                    }
                    i++;
                }
            } else {
                return iArr.length - 1;
            }
        }
        return 0;
    }

    private int getWindowHeight(int i) {
        return VehicleFeature.isD55() ? i == 2 ? PORTRAIT_VERSION_DETAIL_WINDOW_HEIGHT : PORTRAIT_NEW_VERSION_WINDOW_HEIGHT : i == 2 ? VERSION_DETAIL_WINDOW_HEIGHT : NEW_VERSION_WINDOW_HEIGHT;
    }

    public void setWindowHeight(int i) {
        this.mWindowHeight = i;
    }

    public void setTitleIndexChangedListener(TitleIndexChangedListener titleIndexChangedListener) {
        this.mListener = titleIndexChangedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showCheckedContent(final WebView webView, int i) {
        final String format = String.format("javascript:onTabClicked('%d')", Integer.valueOf(i));
        webView.post(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$WebViewVersionFragment$nz4GTlPFwEPZvBZZ4zTURB1sbT8
            @Override // java.lang.Runnable
            public final void run() {
                webView.evaluateJavascript(format, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showAllInWebView(Context context, WebView webView, List<VersionTitle> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getContent());
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            return;
        }
        WebViewUtils.showWVContent(context, webView, sb.toString());
    }
}

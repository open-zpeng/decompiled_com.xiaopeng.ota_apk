package com.xiaopeng.xui.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaopeng.xui.app.delegate.XActivityDelegate;
import com.xiaopeng.xui.app.delegate.XActivityDismiss;
import com.xiaopeng.xui.app.delegate.XActivityTemplate;
import com.xiaopeng.xui.app.delegate.XActivityWindowAttributes;
import com.xiaopeng.xui.app.delegate.XActivityWindowVisible;
/* loaded from: classes2.dex */
public abstract class XActivity extends AppCompatActivity {
    private XActivityDelegate mDelegate;

    @NonNull
    public XActivityDelegate getXuiDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = XActivityDelegate.create(this);
        }
        return this.mDelegate;
    }

    @NonNull
    protected <T extends XActivityTemplate> T getActivityTemplate() {
        return (T) getXuiDelegate().getActivityTemplate();
    }

    @NonNull
    protected XActivityDismiss getActivityDismiss() {
        return getXuiDelegate().getActivityDismiss();
    }

    @NonNull
    protected XActivityWindowAttributes getWindowAttributes() {
        return getXuiDelegate().getWindowAttributes();
    }

    @NonNull
    protected XActivityWindowVisible getWindowVisible() {
        return getXuiDelegate().getWindowVisible();
    }

    public void dismissActivity() {
        getActivityDismiss().dismiss(0);
    }

    public void dispatchUserEvent() {
        getXuiDelegate().dispatchUserEvent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        XActivityDelegate xuiDelegate = getXuiDelegate();
        xuiDelegate.installViewFactory();
        xuiDelegate.onCreate(bundle);
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        getXuiDelegate().onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        getXuiDelegate().onPostCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getXuiDelegate().onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getXuiDelegate().onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        getXuiDelegate().onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getXuiDelegate().onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getXuiDelegate().onSaveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getXuiDelegate().onDestroy();
    }

    @Override // android.app.Activity
    public void recreate() {
        super.recreate();
        getXuiDelegate().onRecreate();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getXuiDelegate().onTouchEvent(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (getXuiDelegate().dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        getXuiDelegate().onBackPressed();
    }
}

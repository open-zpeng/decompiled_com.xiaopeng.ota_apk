package com.xiaopeng.ota.sdk.eventbroker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Subscribe {
    Class<?>[] events();

    int priority() default 0;

    String threadGroup() default "";

    ThreadMode threadMode() default ThreadMode.THREAD;
}

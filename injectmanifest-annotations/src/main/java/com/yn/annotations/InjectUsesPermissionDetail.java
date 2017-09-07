package com.yn.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Whyn on 2017/9/7.
 */

@Retention(RetentionPolicy.SOURCE)
public @interface InjectUsesPermissionDetail {
    String permission() default "";

    String maxSdkVersion() default "";
}

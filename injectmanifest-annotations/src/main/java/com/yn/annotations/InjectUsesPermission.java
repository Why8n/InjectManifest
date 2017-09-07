package com.yn.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


//<uses-permission android:name="string"
//        android:maxSdkVersion="integer" />

@Retention(RetentionPolicy.SOURCE)
public @interface InjectUsesPermission {
    String[] value() default {};

    InjectUsesPermissionDetail[] permission() default {};

}




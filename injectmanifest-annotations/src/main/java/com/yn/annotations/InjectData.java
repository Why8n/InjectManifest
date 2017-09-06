package com.yn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/9/6.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectData {
    //    android:scheme="string"
    String scheme() default "";

    //    android:host="string"
    String host() default "";

    //    android:port="string"
    String port() default "";

    //    android:path="string"
    String path() default "";

    //    android:pathPattern="string"
    String pathPattern() default "";

    //    android:pathPrefix="string"
    String pathPrefix() default "";

    //    android:mimeType="string"
    String mimeType() default "";
}

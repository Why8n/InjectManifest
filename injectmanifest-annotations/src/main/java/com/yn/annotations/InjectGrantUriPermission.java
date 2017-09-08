package com.yn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/9/8.
 */


//<grant-uri-permission android:path="string"
//        android:pathPattern="string"
//        android:pathPrefix="string" />
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface InjectGrantUriPermission {

    String path() default "";

    String pathPattern() default "";

    String pathPrefix() default "";

}

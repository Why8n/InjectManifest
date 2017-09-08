package com.yn.annotations;

/**
 * Created by Whyn on 2017/9/8.
 */

public @interface InjectPathPermission {
    //    android:path="string"
    String path() default "";

    //    android:pathPrefix="string"
    String pathPrefix() default "";

    //    android:pathPattern="string"
    String pathPattern() default "";

    //    android:permission="string"
    String permission() default "";

    //    android:readPermission="string"
    String readPermission() default "";

    //    android:writePermission="string"
    String writePermission() default "";

}

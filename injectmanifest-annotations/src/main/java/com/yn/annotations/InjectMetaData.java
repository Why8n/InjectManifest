package com.yn.annotations;

/**
 * Created by Whyn on 2017/9/7.
 */

//<meta-data android:name="string"
//        android:resource="resource specification"
//        android:value="string" />

public @interface InjectMetaData {
    String name() default "";

    String resource() default "";

    String value() default "";
}

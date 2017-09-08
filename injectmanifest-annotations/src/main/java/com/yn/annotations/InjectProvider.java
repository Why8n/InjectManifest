package com.yn.annotations;

import com.yn.annotations.enums.Correct;

/**
 * Created by Whyn on 2017/9/8.
 */

public @interface InjectProvider {

    //    android:authorities="list"
    String authorities() default "";

    //    android:directBootAware=["true" | "false"]
    Correct directBootAware() default Correct.NONE;

    //    android:enabled=["true" | "false"]
    Correct enabled() default Correct.NONE;

    //    android:exported=["true" | "false"]
    Correct exported() default Correct.NONE;

    //    android:grantUriPermissions=["true" | "false"]
    Correct grantUriPermissions() default Correct.NONE;

    //    android:icon="drawable resource"
    String icon() default "";

    //    android:initOrder="integer"
    String initOrder() default "";

    //    android:label="string resource"
    String label() default "";

    //    android:multiprocess=["true" | "false"]
    Correct multiprocess() default Correct.NONE;

    //    android:name="string"
    String name() default "";

    //    android:permission="string"
    String permission() default "";

    //    android:process="string"
    String process() default "";

    //    android:readPermission="string"
    String readPermission() default "";

    //    android:syncable=["true" | "false"]
    Correct syncable() default Correct.NONE;

    //    android:writePermission="string"
    String writePermission() default "";

    InjectGrantUriPermission[] grantUriPermission() default {};

    InjectMetaData[] metaData() default {};

    InjectPathPermission[] pathPermission() default {};
}

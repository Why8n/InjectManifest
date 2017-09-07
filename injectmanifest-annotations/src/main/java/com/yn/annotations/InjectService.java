package com.yn.annotations;

import com.yn.annotations.enums.Correct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/9/5.
 */


@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectService {
    //    android:name="string"
    String name() default "";

    //    android:description="string resource"
    String description() default "";

    //    android:directBootAware=["true" | "false"]
    Correct directBootAware() default Correct.NONE;

    //    android:enabled=["true" | "false"]
    Correct enabled() default Correct.NONE;

    //    android:exported=["true" | "false"]
    Correct exported() default Correct.NONE;

    //    android:icon="drawable resource"
    String icon() default "";

    //    android:isolatedProcess=["true" | "false"]
    Correct isolatedProcess() default Correct.NONE;

    //    android:label="string resource"
    String label() default "";

    //    android:permission="string"
    String permission() default "";

    //    android:process="string"
    String process() default "";

    InjectIntentFilter intentFilter() default @InjectIntentFilter;
    InjectMetaData[] metaData() default {};
}

package com.yn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/8/30.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectReceiver {
    String name() default "";

    String label() default "";

    InjectIntentFilter intentFilter() default @InjectIntentFilter;
}

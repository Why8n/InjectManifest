package com.yn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/8/30.
 */

@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectIntentFilter {
    String[] action() default {};

    String[] category() default {};

    InjectData[] data() default @InjectData;

}

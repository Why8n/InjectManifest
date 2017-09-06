package com.yn.annotations.defaults;

/**
 * Created by Whyn on 2017/8/30.
 */
//@Retention(RetentionPolicy.SOURCE)
//@Target(ElementType.TYPE)
////public @interface DefaultMainIntentFilter implements InjectIntentFilter {
////    String[] action() default {"sdfasdfadsf"};
////
////    String category() default "dafsdfasdf";
////}

import com.yn.annotations.InjectIntentFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//TODO:: remove
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface DefaultMainIntentFilter {
    InjectIntentFilter value() default @InjectIntentFilter(
            action = "android.intent.action.MAIN",
            category = "android.intent.category.LAUNCHER"
    );
}
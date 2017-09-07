package com.yn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/9/6.
 */


/*
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="string"
        android:sharedUserId="string"
        android:sharedUserLabel="string resource"
        android:versionCode="integer"
        android:versionName="string"
        android:installLocation=["auto"|"internalOnly"|"preferExternal"]>
        ...
</manifest>
*/

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectManifest {
    String xmlns() default "http://schemas.android.com/apk/res/android";

    String pkName() default "";

    String sharedUserId() default "";

    String sharedUserLabel() default "";

    String versionCode() default "";

    String versionName() default "";

    InstallLocation installLocation() default InstallLocation.AUTO;

    enum InstallLocation {
        AUTO("auto"),

        INTERNAL_ONLY("internalOnly"),

        PREFER_EXTERNAL("preferExternal");

        private String name;

        InstallLocation(String installLocation) {
            name = installLocation;
        }

        public String getName() {
            return name;
        }
    }
}

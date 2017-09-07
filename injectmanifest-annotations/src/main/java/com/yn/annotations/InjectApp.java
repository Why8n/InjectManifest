package com.yn.annotations;

import com.yn.annotations.enums.Correct;
import com.yn.annotations.enums.UiOptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/9/6.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectApp {
    //    android:allowTaskReparenting=["true" | "false"]
    Correct allowTaskReparenting() default Correct.NONE;

    //    android:allowBackup=["true" | "false"]
    Correct allowBackup() default Correct.NONE;

    //    android:allowClearUserData=["true" | "false"]
    Correct allowClearUserData() default Correct.NONE;

    //    android:backupAgent="string"
    String backupAgent() default "";

    //    android:backupInForeground=["true" | "false"]
    Correct backupInForeground() default Correct.NONE;

    //    android:banner="drawable resource"
    String banner() default "";

    //    android:debuggable=["true" | "false"]
    Correct debuggable() default Correct.NONE;

    //    android:description="string resource"
    String description() default "";

    //    android:directBootAware=["true" | "false"]
    Correct directBootAware() default Correct.NONE;

    //    android:enabled=["true" | "false"]
    Correct enabled() default Correct.NONE;

    //    android:extractNativeLibs=["true" | "false"]
    Correct extractNativeLibs() default Correct.NONE;

    //    android:fullBackupContent="string"
    String fullBackupContent() default "";

    //    android:fullBackupOnly=["true" | "false"]
    Correct fullBackupOnly() default Correct.NONE;

    //    android:hasCode=["true" | "false"]
    Correct hasCode() default Correct.NONE;

    //    android:hardwareAccelerated=["true" | "false"]
    Correct hardwareAccelerated() default Correct.NONE;

    //    android:icon="drawable resource"
    String icon() default "";

    //    android:isGame=["true" | "false"]
    Correct isGame() default Correct.NONE;

    //    android:killAfterRestore=["true" | "false"]
    Correct killAfterRestore() default Correct.NONE;

    //    android:largeHeap=["true" | "false"]
    Correct largeHeap() default Correct.NONE;

    //    android:label="string resource"
    String label() default "";

    //    android:logo="drawable resource"
    String logo() default "";

    //    android:manageSpaceActivity="string"
    String manageSpaceActivity() default "";

    //    android:name="string"
    String name() default "";

    //    android:networkSecurityConfig="xml resource"
    String networkSecurityConfig() default "";

    //    android:permission="string"
    String permission() default "";

    //    android:persistent=["true" | "false"]
    Correct persistent() default Correct.NONE;

    //    android:process="string"
    String process() default "";

    //    android:restoreAnyVersion=["true" | "false"]
    Correct restoreAnyVersion() default Correct.NONE;

    //    android:requiredAccountType="string"
    String requiredAccountType() default "";

    //    android:resizeableActivity=["true" | "false"]
    Correct resizeableActivity() default Correct.NONE;

    //    android:restrictedAccountType="string"
    String restrictedAccountType() default "";

    //    android:supportsRtl=["true" | "false"]
    Correct supportsRtl() default Correct.NONE;

    //    android:taskAffinity="string"
    String taskAffinity() default "";

    //    android:testOnly=["true" | "false"]
    Correct testOnly() default Correct.NONE;

    //    android:theme="resource or theme"
    String theme() default "";

    //    android:uiOptions=["none" | "splitActionBarWhenNarrow"]
    UiOptions uiOptions() default UiOptions.NULL;

    //    android:usesCleartextTraffic=["true" | "false"]
    Correct usesCleartextTraffic() default Correct.NONE;

    //    android:vmSafeMode=["true" | "false"]
    Correct vmSafeMode() default Correct.NONE;

    InjectMetaData[] metaData() default {};
}

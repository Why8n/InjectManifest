package com.yn.annotations;

import com.yn.annotations.enums.Correct;
import com.yn.annotations.enums.UiOptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Whyn on 2017/8/30.
 */


@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectActivity {
    //    android:allowEmbedded=["true"|"false"]
    Correct allowEmbedded() default Correct.NONE/*Correct.FALSE*/;


    //    android:allowTaskReparenting=["true"|"false"]
    Correct allowTaskReparenting() default Correct.NONE/*Correct.FALSE*/;


    //    android:alwaysRetainTaskState=["true"|"false"]
    Correct alwaysRetainTaskState() default Correct.NONE/*Correct.FALSE*/;


    //    android:autoRemoveFromRecents=["true"|"false"]
    Correct autoRemoveFromRecents() default Correct.NONE/*Correct.FALSE*/;


    //    android:banner="drawable resource"
    //TODO::"@drawable/banner"
    String banner() default "";

    //    android:clearTaskOnLaunch=["true"|"false"]
    Correct clearTaskOnLaunch() default Correct.NONE/*Correct.FALSE*/;


    //    android:configChanges=["mcc","mnc","locale",
//            "touchscreen","keyboard","keyboardHidden",
//            "navigation","screenLayout","fontScale",
//            "uiMode","orientation","screenSize",
//            "smallestScreenSize"]
    ConfigChanges configChanges() default ConfigChanges.NONE;

    enum ConfigChanges {
        NONE(""),
        MCC("mcc"),
        MNC("mnc"),
        LOCALE("locale"),
        TOUCH_SCREEN("touchscreen"),
        KEYBOARD("keyboard"),
        KEYBOARD_HIDDEN("keyboardHidden"),
        NAVIGATION("navigation"),
        SCREEN_LAYOUT("screenLayout"),
        FONT_SCALE("fontScale"),
        UI_MODE("uiMode"),
        ORIENTATION("orientation"),
        SCREEN_SIZE("screenSize"),
        SMALL_SCREEN_SIZE("smallestScreenSize");

        private String name;

        ConfigChanges(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    //    android:documentLaunchMode=["intoExisting"|"always"|
//            "none"|"never"]
    DocumentLaunchMode documentLaunchMode() default DocumentLaunchMode.NULL;

    enum DocumentLaunchMode {
        NULL(""),
        INTO_EXISTING("intoExisting"),
        ALWAYS("always"),
        NONE("none"),
        NEVER("never");

        private String mode;

        DocumentLaunchMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }

    }

    //    android:enabled=["true"|"false"]
    Correct enabled() default Correct.NONE/*Correct.TRUE*/;


    //    android:excludeFromRecents=["true"|"false"]
    Correct excludeFromRecents() default Correct.NONE/*Correct.FALSE*/;


    //    android:exported=["true"|"false"]
    Correct exported() default Correct.NONE;

    //    android:finishOnTaskLaunch=["true"|"false"]
    Correct finishOnTaskLaunch() default Correct.NONE/*Correct.FALSE*/;


    //    android:hardwareAccelerated=["true"|"false"]
    Correct hardwareAccelerated() default Correct.NONE/*Correct.FALSE*/;


    //    android:icon="drawable resource"
    //TODO:;android:icon="@mipmap/ic_launcher"
    String icon() default "";

    //    android:label="string resource"
    //TODO:;
    String label() default "";

    //    android:launchMode=["standard"|"singleTop"|
//            "singleTask"|"singleInstance"]
    LaunchMode launchMode() default LaunchMode.NONE;

    enum LaunchMode {
        NONE(""),
        STANDARD("standard"),
        SINGLE_TOP("singleTop"),
        SINGLE_TASK("singleTask"),
        SINGLE_INSTANCE("singleInstance");

        private String mode;

        LaunchMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }

    //    android:maxRecents="integer"
    //TODO::
    String maxRecents() default "";

    //    android:multiprocess=["true"|"false"]
    Correct multiprocess() default Correct.NONE/*Correct.FALSE*/;


    //    android:name="string"
    String name() default "";

    //    android:noHistory=["true"|"false"]
    Correct noHistory() default Correct.NONE/*Correct.FALSE*/;


    //    android:parentActivityName="string"
    String parentActivityName() default "";

    //    android:permission="string"
    String permission() default "";

    //    android:process="string"
    String process() default "";

    //    android:relinquishTaskIdentity=["true"|"false"]
    Correct relinquishTaskIdentity() default Correct.NONE/*Correct.FALSE*/;


    //    android:resizeableActivity=["true"|"false"]
    Correct resizeableActivity() default Correct.NONE;

    //    android:screenOrientation=["unspecified"|"behind"|
//            "landscape"|"portrait"|
//            "reverseLandscape"|"reversePortrait"|
//            "sensorLandscape"|"sensorPortrait"|
//            "userLandscape"|"userPortrait"|
//            "sensor"|"fullSensor"|"nosensor"|
//            "user"|"fullUser"|"locked"]
    ScreenOrientation screenOrientation() default ScreenOrientation.NONE;

    enum ScreenOrientation {
        NONE(""),
        UNSPECIFIED("unspecified"),
        BEHID("behind"),
        LANDSCAPE("landscape"),
        PORTRAIT("portrait"),
        REVERSE_LANDSCAPE("reverseLandscape"),
        REVERSE_PORTRAIT("reversePortrait"),
        SENSOR_LANDSCAPE("sensorLandscape"),
        SENSOR_PORTRAIT("sensorPortrait"),
        USER_LANDSCAPE("userLandscape"),
        USER_PORTRAIT("userPortrait"),
        SENSOR("sensor"),
        FULL_SENSOR("fullSensor"),
        NO_SENSOR("nosensor"),
        USER("user"),
        FULL_USER("fullUser"),
        LOCKED("locked");

        private String orientation;

        ScreenOrientation(String orientation) {
            this.orientation = orientation;
        }

        public String getOrientation() {
            return orientation;
        }
    }

    //    android:stateNotNeeded=["true"|"false"]
    Correct stateNotNeeded() default Correct.NONE/*Correct.FALSE*/;


    //    android:supportsPictureInPicture=["true"|"false"]
    Correct supportsPictureInPicture() default Correct.NONE;

    //    android:taskAffinity="string"
    String taskAffinity() default "";

    //    android:theme="resource or theme"
    //TODO::
    String theme() default "";

    //    android:uiOptions=["none"|"splitActionBarWhenNarrow"]
    UiOptions uiOptions() default UiOptions.NULL/*UiOptions.NONE*/;

    //    android:windowSoftInputMode=["stateUnspecified",
//            "stateUnchanged","stateHidden",
//            "stateAlwaysHidden","stateVisible",
//            "stateAlwaysVisible","adjustUnspecified",
//            "adjustResize","adjustPan"]>
    WindowSoftInputMode windowSoftInputMode() default WindowSoftInputMode.NONE/*WindowSoftInputMode.STATE_UNSPECIFIED*/;

    enum WindowSoftInputMode {
        NONE(""),
        STATE_UNSPECIFIED("stateUnspecified"),
        STATE_UNCHANGED("stateUnchanged"),
        STATE_HIDDEN("stateHidden"),
        STATE_ALWAYS_HIDDEN("stateAlwaysHidden"),
        STATE_VISIBLE("stateVisible"),
        STATE_ALWAYS_VISIBLE("stateAlwaysVisible"),
        ADJUST_UNSPECIFIED("adjustUnspecified"),
        ADJUST_RESIZE("adjustResize"),
        ADJUST_PAN("adjustPan");

        private String mode;

        WindowSoftInputMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }

    InjectIntentFilter intentFilter() default @InjectIntentFilter;
    InjectMetaData[] metaData() default {};
}

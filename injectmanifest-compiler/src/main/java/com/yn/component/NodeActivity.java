package com.yn.component;

import com.yn.annotations.InjectActivity;
import com.yn.annotations.enums.UiOptions;
import com.yn.utils.Utils;

/**
 * Created by Whyn on 2017/8/30.
 */

public class NodeActivity extends ComponentBasic {
    private static final String KEY_STAND_MODE = "android:standMode";

    public NodeActivity(String name) {
        super(name);
    }

    //    android:allowEmbedded=["true"|"false"]
    public NodeActivity allowEmbedded(String bRet) {
        attrs.addAttr("android:allowEmbedded", bRet);
        return this;
    }

    //    android:allowTaskReparenting=["true"|"false"]
    public NodeActivity allowTaskReparenting(String bRet) {
        attrs.addAttr("android:allowTaskReparenting", bRet);
        return this;
    }

    //    android:alwaysRetainTaskState=["true"|"false"]
    public NodeActivity alwaysRetainTaskState(String bRet) {
        attrs.addAttr("android:alwaysRetainTaskState", bRet);
        return this;
    }

    //    android:autoRemoveFromRecents=["true"|"false"]
    public NodeActivity autoRemoveFromRecents(String bRet) {
        attrs.addAttr("android:autoRemoveFromRecents", bRet);
        return this;
    }

    //    android:banner="drawable resource"
    public NodeActivity banner(String resource) {
        attrs.addAttr("android:banner", resource);
        return this;
    }

    //    android:clearTaskOnLaunch=["true"|"false"]
    public NodeActivity clearTaskOnLaunch(String bRet) {
        attrs.addAttr("android:clearTaskOnLaunch", bRet);
        return this;
    }

    //    android:configChanges=["mcc","mnc","locale",
//            "touchscreen","keyboard","keyboardHidden",
//            "navigation","screenLayout","fontScale",
//            "uiMode","orientation","screenSize",
//            "smallestScreenSize"]
    public NodeActivity configChanges(InjectActivity.ConfigChanges configChanges) {
        attrs.addAttr("android:configChanges", configChanges.getName());
        return this;
    }

    //    android:documentLaunchMode=["intoExisting"|"always"|
//            "none"|"never"]
    public NodeActivity documentLaunchMode(InjectActivity.DocumentLaunchMode documentLaunchMode) {
        attrs.addAttr("android:documentLaunchMode", documentLaunchMode.getMode());
        return this;
    }

    //    android:enabled=["true"|"false"]
    public NodeActivity enabled(String bRet) {
        attrs.addAttr("android:enabled", bRet);
        return this;
    }

    //    android:excludeFromRecents=["true"|"false"]
    public NodeActivity excludeFromRecents(String bRet) {
        attrs.addAttr("android:excludeFromRecents", bRet);
        return this;
    }

    //    android:exported=["true"|"false"]
    public NodeActivity exported(String bRet) {
        attrs.addAttr("android:exported", bRet);
        return this;
    }

    //    android:finishOnTaskLaunch=["true"|"false"]
    public NodeActivity finishOnTaskLaunch(String bRet) {
        attrs.addAttr("android:finishOnTaskLaunch", bRet);
        return this;
    }

    //    android:hardwareAccelerated=["true"|"false"]
    public NodeActivity hardwareAccelerated(String bRet) {
        attrs.addAttr("android:hardwareAccelerated", bRet);
        return this;
    }

    //    android:icon="drawable resource"
    public NodeActivity icon(String res) {
        attrs.addAttr("android:icon", res);
        return this;
    }

    //    android:label="string resource"
    public NodeActivity label(String label) {
        attrs.addAttr("android:label", label);
        return this;
    }

    //    android:launchMode=["standard"|"singleTop"|
//            "singleTask"|"singleInstance"]
    public NodeActivity launchMode(InjectActivity.LaunchMode launchMode) {
        attrs.addAttr("android:launchMode", launchMode.getMode());
        return this;
    }

    //    android:maxRecents="integer"
    public NodeActivity maxRecents(String maxRecents) {
        attrs.addAttr("android:maxRecents", maxRecents);
        return this;
    }

    //    android:multiprocess=["true"|"false"]
    public NodeActivity multiprocess(String bRet) {
        attrs.addAttr("android:multiprocess", bRet);
        return this;
    }

    //    android:name="string"
    public NodeActivity name(String name) {
        Utils.checkNotNull(name, "activity name must not be null");
        attrs.addAttr("android:name", name);
        return this;
    }

    //    android:noHistory=["true"|"false"]
    public NodeActivity noHistory(String noHistory) {
        attrs.addAttr("android:noHistory", noHistory);
        return this;
    }

    //    android:parentActivityName="string"
    public NodeActivity parentActivityName(String name) {
        attrs.addAttr("android:parentActivityName", name);
        return this;
    }

    //    android:permission="string"
    public NodeActivity permission(String permission) {
        attrs.addAttr("android:permission", permission);
        return this;
    }

    //    android:process="string"
    public NodeActivity process(String process) {
        attrs.addAttr("android:process", process);
        return this;
    }

    //    android:relinquishTaskIdentity=["true"|"false"]
    public NodeActivity relinquishTaskIdentity(String bRet) {
        attrs.addAttr("android:relinquishTaskIdentity", bRet);
        return this;
    }

    //    android:resizeableActivity=["true"|"false"]
    public NodeActivity resizeableActivity(String bRet) {
        attrs.addAttr("android:resizeableActivity", bRet);
        return this;
    }

    //    android:screenOrientation=["unspecified"|"behind"|
//            "landscape"|"portrait"|
//            "reverseLandscape"|"reversePortrait"|
//            "sensorLandscape"|"sensorPortrait"|
//            "userLandscape"|"userPortrait"|
//            "sensor"|"fullSensor"|"nosensor"|
//            "user"|"fullUser"|"locked"]
    public NodeActivity screenOrientation(InjectActivity.ScreenOrientation screenOrientation) {
        attrs.addAttr("android:screenOrientation", screenOrientation.getOrientation());
        return this;
    }

    //    android:stateNotNeeded=["true"|"false"]
    public NodeActivity stateNotNeeded(String bRet) {
        attrs.addAttr("android:stateNotNeeded", bRet);
        return this;
    }

    //    android:supportsPictureInPicture=["true"|"false"]
    public NodeActivity supportsPictureInPicture(String bRet) {
        attrs.addAttr("android:supportsPictureInPicture", bRet);
        return this;
    }

    //    android:taskAffinity="string"
    public NodeActivity taskAffinity(String taskAffinity) {
        attrs.addAttr("android:taskAffinity", taskAffinity);
        return this;
    }

    //    android:theme="resource or theme"
    public NodeActivity theme(String theme) {
        attrs.addAttr("android:theme", theme);
        return this;
    }

    //    android:uiOptions=["none"|"splitActionBarWhenNarrow"]
    public NodeActivity uiOptions(UiOptions uiOptions) {
        attrs.addAttr("android:uiOptions", uiOptions.getUiOptions());
        return this;
    }

    //    android:windowSoftInputMode=["stateUnspecified",
//            "stateUnchanged","stateHidden",
//            "stateAlwaysHidden","stateVisible",
//            "stateAlwaysVisible","adjustUnspecified",
//            "adjustResize","adjustPan"]>
    public NodeActivity windowSoftInputMode(InjectActivity.WindowSoftInputMode windowSoftInputMode) {
        attrs.addAttr("android:windowSoftInputMode", windowSoftInputMode.getMode());
        return this;
    }
}

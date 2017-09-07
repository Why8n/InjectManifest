package com.yn.component;

import com.yn.utils.Utils;

/**
 * Created by Whyn on 2017/9/5.
 */

public class NodeReceiver extends ComponentBasic {

    public NodeReceiver(String name) {
        super(name);
    }

    //    android:directBootAware=["true" | "false"]
    public NodeReceiver directBootAware(String bRet) {
        attrs.addAttr("android:directBootAware", bRet);
        return this;
    }

    //    android:enabled=["true" | "false"]
    public NodeReceiver enabled(String bRet) {
        attrs.addAttr("android:enabled", bRet);
        return this;
    }

    //    android:exported=["true" | "false"]
    public NodeReceiver exported(String bRet) {
        attrs.addAttr("android:exported", bRet);
        return this;
    }

    //    android:icon="drawable resource"
    public NodeReceiver icon(String icon) {
        attrs.addAttr("android:icon", icon);
        return this;
    }

    //    android:label="string resource"
    public NodeReceiver label(String label) {
        attrs.addAttr("android:label", label);
        return this;
    }

    //    android:name="string"
    public NodeReceiver name(String name) {
        Utils.checkNotNull(name, "broadcast recevier's name must not be null");
        attrs.addAttr("android:name", name);
        return this;
    }

    //    android:permission="string"
    public NodeReceiver permission(String permission) {
        attrs.addAttr("android:permission", permission);
        return this;
    }

    //    android:process="string"
    public NodeReceiver process(String process) {
        attrs.addAttr("android:process", process);
        return this;
    }
}

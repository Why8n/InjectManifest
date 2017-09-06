package com.yn.component;

import com.yn.utils.Utils;

/**
 * Created by Whyn on 2017/9/5.
 */

public class NodeService extends ComponentBasic {
    public NodeService(String name) {
        super(name);
    }

    //    android:description="string resource"
    public NodeService description(String res) {
        attrs.addAttr("android:description", res);
        return this;
    }

    //    android:directBootAware=["true" | "false"]
    public NodeService directBootAware(String bRet) {
        attrs.addAttr("android:directBootAware", bRet);
        return this;
    }

    //    android:enabled=["true" | "false"]
    public NodeService enabled(String bRet) {
        attrs.addAttr("android:enabled", bRet);
        return this;
    }

    //    android:exported=["true" | "false"]
    public NodeService exported(String bRet) {
        attrs.addAttr("android:exported", bRet);
        return this;
    }

    //    android:icon="drawable resource"
    public NodeService icon(String res) {
        attrs.addAttr("android:icon", res);
        return this;
    }

    //    android:isolatedProcess=["true" | "false"]
    public NodeService isolatedProcess(String bRet) {
        attrs.addAttr("android:isolatedProcess", bRet);
        return this;
    }

    //    android:label="string resource"
    public NodeService label(String label) {
        attrs.addAttr("android:label", label);
        return this;
    }

    //    android:name="string"
    public NodeService name(String name) {
        Utils.checkNotNull(name, "service name must not be null");
        attrs.addAttr("android:name", name);
        return this;
    }

    //    android:permission="string"
    public NodeService permission(String permission) {
        attrs.addAttr("android:permission", permission);
        return this;
    }

    //    android:process="string"
    public NodeService process(String process) {
        attrs.addAttr("android:process", process);
        return this;
    }

}

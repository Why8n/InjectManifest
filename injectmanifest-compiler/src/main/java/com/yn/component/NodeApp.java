package com.yn.component;

import com.yn.annotations.enums.UiOptions;
import com.yn.component.bean.Attribute;
import com.yn.component.bean.MetaData;

import java.util.Set;

/**
 * Created by Whyn on 2017/9/6.
 */

public class NodeApp {
    final Attrs attrs = new Attrs();
    final NodeMetaData metaDatas = new NodeMetaData();


    public NodeApp addAllAttr(Set<Attribute> attributes) {
        this.attrs.addAllAttr(attributes);
        return this;
    }

    public void copy(NodeApp newItem) {
        this.attrs.addAllAttr(newItem.attrs.all());
        this.metaDatas.addAll(newItem.metaDatas);
    }

    //    android:allowTaskReparenting=["true" | "false"]
    public NodeApp allowTaskReparenting(String bRet) {
        attrs.addAttr("android:allowTaskReparenting", bRet);
        return this;
    }

    //    android:allowBackup=["true" | "false"]
    public NodeApp allowBackup(String bRet) {
        attrs.addAttr("android:allowBackup", bRet);
        return this;
    }

    //    android:allowClearUserData=["true" | "false"]
    public NodeApp allowClearUserData(String bRet) {
        attrs.addAttr("android:allowClearUserData", bRet);
        return this;
    }

    //    android:backupAgent="string"
    public NodeApp backupAgent(String backupAgent) {
        attrs.addAttr("android:backupAgent", backupAgent);
        return this;
    }

    //    android:backupInForeground=["true" | "false"]
    public NodeApp backupInForeground(String bRet) {
        attrs.addAttr("android:backupInForeground", bRet);
        return this;
    }

    //    android:banner="drawable resource"
    public NodeApp banner(String banner) {
        attrs.addAttr("android:banner", banner);
        return this;
    }

    //    android:debuggable=["true" | "false"]
    public NodeApp debuggable(String bRet) {
        attrs.addAttr("android:debuggable", bRet);
        return this;
    }

    //    android:description="string resource"
    public NodeApp description(String description) {
        attrs.addAttr("android:description", description);
        return this;
    }

    //    android:directBootAware=["true" | "false"]
    public NodeApp directBootAware(String bRet) {
        attrs.addAttr("android:directBootAware", bRet);
        return this;
    }

    //    android:enabled=["true" | "false"]
    public NodeApp enabled(String bRet) {
        attrs.addAttr("android:enabled", bRet);
        return this;
    }

    //    android:extractNativeLibs=["true" | "false"]
    public NodeApp extractNativeLibs(String bRet) {
        attrs.addAttr("android:extractNativeLibs", bRet);
        return this;
    }

    //    android:fullBackupContent="string"
    public NodeApp fullBackupContent(String fullBackupContent) {
        attrs.addAttr("android:fullBackupContent", fullBackupContent);
        return this;
    }

    //    android:fullBackupOnly=["true" | "false"]
    public NodeApp fullBackupOnly(String fullBackupOnly) {
        attrs.addAttr("android:fullBackupOnly", fullBackupOnly);
        return this;
    }

    //    android:hasCode=["true" | "false"]
    public NodeApp hasCode(String bRet) {
        attrs.addAttr("android:hasCode", bRet);
        return this;
    }

    //    android:hardwareAccelerated=["true" | "false"]
    public NodeApp hardwareAccelerated(String bRet) {
        attrs.addAttr("android:hardwareAccelerated", bRet);
        return this;
    }

    //    android:icon="drawable resource"
    public NodeApp icon(String icon) {
        attrs.addAttr("android:icon", icon);
        return this;
    }

    //    android:isGame=["true" | "false"]
    public NodeApp isGame(String isGame) {
        attrs.addAttr("android:isGame", isGame);
        return this;
    }

    //    android:killAfterRestore=["true" | "false"]
    public NodeApp killAfterRestore(String bRet) {
        attrs.addAttr("android:killAfterRestore", bRet);
        return this;
    }

    //    android:largeHeap=["true" | "false"]
    public NodeApp largeHeap(String bRet) {
        attrs.addAttr("android:largeHeap", bRet);
        return this;
    }

    //    android:label="string resource"
    public NodeApp label(String label) {
        attrs.addAttr("android:label", label);
        return this;
    }

    //    android:logo="drawable resource"
    public NodeApp logo(String logo) {
        attrs.addAttr("android:logo", logo);
        return this;
    }

    //    android:manageSpaceActivity="string"
    public NodeApp manageSpaceActivity(String manageSpaceActivity) {
        attrs.addAttr("android:manageSpaceActivity", manageSpaceActivity);
        return this;
    }

    //    android:name="string"
    public NodeApp name(String name) {
        attrs.addAttr("android:name", name);
        return this;
    }

    //    android:networkSecurityConfig="xml resource"
    public NodeApp networkSecurityConfig(String networkSecurityConfig) {
        attrs.addAttr("android:networkSecurityConfig", networkSecurityConfig);
        return this;
    }

    //    android:permission="string"
    public NodeApp permission(String permission) {
        attrs.addAttr("android:permission", permission);
        return this;
    }

    //    android:persistent=["true" | "false"]
    public NodeApp persistent(String bRet) {
        attrs.addAttr("android:persistent", bRet);
        return this;
    }

    //    android:process="string"
    public NodeApp process(String process) {
        attrs.addAttr("android:process", process);
        return this;
    }

    //    android:restoreAnyVersion=["true" | "false"]
    public NodeApp restoreAnyVersion(String bRet) {
        attrs.addAttr("android:restoreAnyVersion", bRet);
        return this;
    }

    //    android:requiredAccountType="string"
    public NodeApp requiredAccountType(String requiredAccountType) {
        attrs.addAttr("android:requiredAccountType", requiredAccountType);
        return this;
    }

    //    android:resizeableActivity=["true" | "false"]
    public NodeApp resizeableActivity(String bRet) {
        attrs.addAttr("android:resizeableActivity", bRet);
        return this;
    }

    //    android:restrictedAccountType="string"
    public NodeApp restrictedAccountType(String restrictedAccountType) {
        attrs.addAttr("android:restrictedAccountType", restrictedAccountType);
        return this;
    }

    //    android:supportsRtl=["true" | "false"]
    public NodeApp supportsRtl(String bRet) {
        attrs.addAttr("android:supportsRtl", bRet);
        return this;
    }

    //    android:taskAffinity="string"
    public NodeApp taskAffinity(String taskAffinity) {
        attrs.addAttr("android:taskAffinity", taskAffinity);
        return this;
    }

    //    android:testOnly=["true" | "false"]
    public NodeApp testOnly(String bRet) {
        attrs.addAttr("android:testOnly", bRet);
        return this;
    }

    //    android:theme="resource or theme"
    public NodeApp theme(String theme) {
        attrs.addAttr("android:theme", theme);
        return this;
    }

    //    android:uiOptions=["none" | "splitActionBarWhenNarrow"]
    public NodeApp allowTaskReparenting(UiOptions uiOptions) {
        attrs.addAttr("android:uiOptions", uiOptions.getUiOptions());
        return this;
    }

    //    android:usesCleartextTraffic=["true" | "false"]
    public NodeApp usesCleartextTraffic(String bRet) {
        attrs.addAttr("android:usesCleartextTraffic", bRet);
        return this;
    }

    //    android:vmSafeMode=["true" | "false"]
    public NodeApp vmSafeMode(String bRet) {
        attrs.addAttr("android:vmSafeMode", bRet);
        return this;
    }

    public NodeApp addMetaData(MetaData metaData) {
        metaDatas.addMetaData(metaData);
        return this;
    }
    public NodeApp addMetaData(Set<Attribute> metaDataAttrs) {
        metaDatas.addMetaData(metaDataAttrs);
        return this;
    }

}


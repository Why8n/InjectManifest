package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.component.bean.MetaData;
import com.yn.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/9/8.
 */

public class NodeProvider extends NodeApp {
    Set<NodeGrantUriPermission> grantUriPermissions = new HashSet<>();
    Set<NodePathPermission> pathPermissions = new HashSet<>();
    //    NodeMetaData metaDatas = new NodeMetaData();


    public NodeProvider(String name) {
        super(name);
        Utils.checkNotNull(name, "provider's name must not be null");
    }

    public NodeProvider addAttr(Set<Attribute> attribute) {
        attrs.addAllAttr(attribute);
        return this;
    }

    public NodeProvider addGrantUriPermission(NodeGrantUriPermission grantUriPermission) {
        this.grantUriPermissions.add(grantUriPermission);
        return this;
    }

    public NodeProvider addGrantUriPermission(Set<Attribute> grantUriPermissionAttr) {
        this.grantUriPermissions.add(new NodeGrantUriPermission().fromAttrs(grantUriPermissionAttr));
        return this;
    }

    public NodeProvider addMetaData(MetaData metaData) {
        this.metaDatas.addMetaData(metaData);
        return this;
    }

    public NodeProvider addPathPermissions(NodePathPermission pathPermission) {
        pathPermissions.add(pathPermission);
        return this;
    }

    public NodeProvider addPathPermissions(Set<Attribute> pathPermissionAttr) {
        pathPermissions.add(new NodePathPermission().<NodePathPermission>fromAttrs(pathPermissionAttr));
        return this;
    }

    //    android:authorities="list"
    public NodeProvider authorities(String authorities) {
        attrs.addAttr("android:authorities", authorities);
        return this;
    }

    //    android:directBootAware=["true" | "false"]
    public NodeProvider directBootAware(String bRet) {
        attrs.addAttr("android:directBootAware", bRet);
        return this;
    }

    //    android:enabled=["true" | "false"]
    public NodeProvider enabled(String bRet) {
        attrs.addAttr("android:enabled", bRet);
        return this;
    }

    //    android:exported=["true" | "false"]
    public NodeProvider exported(String bRet) {
        attrs.addAttr("android:exported", bRet);
        return this;
    }

    //    android:grantUriPermissions=["true" | "false"]
    public NodeProvider grantUriPermissions(String bRet) {
        attrs.addAttr("android:grantUriPermissions", bRet);
        return this;
    }

    //    android:icon="drawable resource"
    public NodeProvider icon(String icon) {
        attrs.addAttr("android:icon", icon);
        return this;
    }

    //    android:initOrder="integer"
    public NodeProvider initOrder(String initOrder) {
        attrs.addAttr("android:initOrder", initOrder);
        return this;
    }

    //    android:label="string resource"
    public NodeProvider label(String label) {
        attrs.addAttr("android:label", label);
        return this;
    }

    //    android:multiprocess=["true" | "false"]
    public NodeProvider multiprocess(String bRet) {
        attrs.addAttr("android:multiprocess", bRet);
        return this;
    }

    //    android:name="string"
    public NodeProvider name(String name) {
        attrs.addAttr("android:name", name);
        return this;
    }

    //    android:permission="string"
    public NodeProvider permission(String permission) {
        attrs.addAttr("android:permission", permission);
        return this;
    }

    //    android:process="string"
    public NodeProvider process(String process) {
        attrs.addAttr("android:process", process);
        return this;
    }

    //    android:readPermission="string"
    public NodeProvider readPermission(String readPermission) {
        attrs.addAttr("android:readPermission", readPermission);
        return this;
    }

    //    android:syncable=["true" | "false"]
    public NodeProvider syncable(String bRet) {
        attrs.addAttr("android:syncable", bRet);
        return this;
    }

    //    android:writePermission="string"
    public NodeProvider writePermission(String writePermission) {
        attrs.addAttr("android:writePermission", writePermission);
        return this;
    }

    @Override
    public <T extends NodeApp> void copy(T newItem) {
        super.copy(newItem);
        NodeProvider other = (NodeProvider)newItem;
        grantUriPermissions.addAll(other.grantUriPermissions);
        pathPermissions.addAll(other.pathPermissions);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (!(bRet = (o instanceof NodeProvider)))
                break;
            NodeProvider other = (NodeProvider) o;
            bRet = this.name.equals(other.name);
        } while (false);
        return bRet;
    }
}

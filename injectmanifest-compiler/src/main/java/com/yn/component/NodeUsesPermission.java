package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.utils.Utils;

import java.util.HashSet;
import java.util.Set;

import static com.yn.component.AndroidManifest.KEY_ATTR_NAME;

/**
 * Created by Whyn on 2017/9/7.
 */

public class NodeUsesPermission {
    private static final String KEY_ATTR_MAXSDKVERSION = "android:maxSdkVersion";

    String permission;
    String maxSdkVersion;

    public NodeUsesPermission permission(String permission) {
        Utils.checkNotNull(permission, "permission must not be null");
        this.permission = permission;
        return this;
    }

    public NodeUsesPermission maxSdkVersion(String maxSdkVersion) {
        this.maxSdkVersion = maxSdkVersion;
        return this;
    }

    public NodeUsesPermission addAttr(Attribute attribute) {
        if (KEY_ATTR_NAME.equals(attribute.key)) {
            permission = attribute.value;
        } else if (KEY_ATTR_MAXSDKVERSION.equals(attribute.key)) {
            maxSdkVersion = attribute.value;
        }
        return this;
    }

    public NodeUsesPermission addAttr(Set<Attribute> attributes) {
        for (Attribute attr : attributes) {
            addAttr(attr);
        }
        return this;
    }

    @Override
    public int hashCode() {
        return permission.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (!(bRet = (o instanceof NodeUsesPermission)))
                break;
            NodeUsesPermission other = (NodeUsesPermission) o;
            bRet = this.permission.equals(other.permission);
        } while (false);
        return bRet;
    }

    @Override
    public String toString() {
        if (maxSdkVersion != null && !maxSdkVersion.isEmpty())
            return String.format("[%s,%s]", permission, maxSdkVersion);
        return String.format("[%s]", permission);
    }

    public Set<Attribute> asSet() {
        Set<Attribute> uses_permission = new HashSet<>();
        if (!Utils.isEmpty(permission))
            uses_permission.add(new Attribute(KEY_ATTR_NAME, permission));
        if (!Utils.isEmpty(maxSdkVersion))
            uses_permission.add(new Attribute(KEY_ATTR_MAXSDKVERSION, maxSdkVersion));
        return uses_permission;
    }


}

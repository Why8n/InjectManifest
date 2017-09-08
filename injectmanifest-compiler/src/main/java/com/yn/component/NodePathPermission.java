package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/9/8.
 */

public class NodePathPermission extends NodeGrantUriPermission {
    //    android:path="string"
//    android:pathPrefix="string"
//    android:pathPattern="string"
//    android:permission="string"
//    android:readPermission="string"
//    android:writePermission="string"
    private static final String KEY_ATTR_PERMISSION = "android:permission";
    private static final String KEY_ATTR_READ_PERMISSION = "android:readPermission";
    private static final String KEY_ATTR_WRITE_PERMISSION = "android:writePermission";

    String permission;
    String readPermission;
    String writePermission;

    public NodePathPermission permission(String permission) {
        this.permission = permission;
        return this;
    }

    public NodePathPermission readPermission(String readPermission) {
        this.readPermission = readPermission;
        return this;
    }

    public NodePathPermission writePermission(String writePermission) {
        this.writePermission = writePermission;
        return this;
    }

    @Override
    public <T extends NodeGrantUriPermission> T fromAttrs(Set<Attribute> attrs) {
        for (Attribute attr : attrs) {
            if (KEY_ATTR_PERMISSION.equals(attr.key))
                this.permission = attr.value;
            if (KEY_ATTR_READ_PERMISSION.equals(attr.key))
                this.readPermission = attr.value;
            if (KEY_ATTR_WRITE_PERMISSION.equals(attr.key))
                this.writePermission = attr.value;
        }
        return super.fromAttrs(attrs);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result *= 31 + (Utils.isEmpty(permission) ? 0 : permission.hashCode());
        result *= 31 + (Utils.isEmpty(readPermission) ? 0 : readPermission.hashCode());
        result *= 31 + (Utils.isEmpty(writePermission) ? 0 : writePermission.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (!(bRet = super.equals(o)))
                break;
            if (!(bRet = (o instanceof NodePathPermission)))
                break;
            NodePathPermission other = (NodePathPermission) o;
            if (bRet = (this.isEmpty() && other.isEmpty()))
                break;
            if (permission != null)
                bRet = permission.equals(other.permission);
            if (readPermission != null)
                bRet = readPermission.equals(other.readPermission);
            if (writePermission != null)
                bRet = writePermission.equals(other.writePermission);
        } while (false);
        return bRet;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty()
                && Utils.isEmpty(permission)
                && Utils.isEmpty(readPermission)
                && Utils.isEmpty(writePermission);
    }

    @Override
    public Set<Attribute> asSet() {
        Set<Attribute> metaSet = new HashSet<>();
        if (!Utils.isEmpty(permission))
            metaSet.add(new Attribute(KEY_ATTR_PERMISSION, permission));
        if (!Utils.isEmpty(readPermission))
            metaSet.add(new Attribute(KEY_ATTR_READ_PERMISSION, readPermission));
        if (!Utils.isEmpty(writePermission))
            metaSet.add(new Attribute(KEY_ATTR_WRITE_PERMISSION, writePermission));
        metaSet.addAll(super.asSet());
        return metaSet;

    }
}

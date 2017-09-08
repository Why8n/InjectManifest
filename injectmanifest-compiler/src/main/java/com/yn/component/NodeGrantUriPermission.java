package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/9/8.
 */

public class NodeGrantUriPermission {
    private static final String KEY_ATTR_PATH = "android:path";
    private static final String KEY_ATTR_PATH_PATTERN = "android:pathPattern";
    private static final String KEY_ATTR_PATH_PREFIX = "android:pathPrefix";

    private String path;
    private String pathPattern;
    private String pathPrefix;

    public <T extends NodeGrantUriPermission> T path(String path) {
        this.path = path;
        return (T) this;
    }

    public <T extends NodeGrantUriPermission> T pathPattern(String pathPattern) {
        this.pathPattern = pathPattern;
        return (T) this;
    }

    public <T extends NodeGrantUriPermission> T pathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
        return (T) this;
    }

    public <T extends NodeGrantUriPermission> T fromAttrs(Set<Attribute> attrs) {
        for (Attribute attr : attrs) {
            if (KEY_ATTR_PATH.equals(attr.key))
                this.path = attr.value;
            if (KEY_ATTR_PATH_PATTERN.equals(attr.key))
                this.pathPattern = attr.value;
            if (KEY_ATTR_PATH_PREFIX.equals(attr.key))
                this.pathPrefix = attr.value;
        }
        return (T) this;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result *= 31 + (Utils.isEmpty(path) ? 0 : path.hashCode());
        result *= 31 + (Utils.isEmpty(pathPattern) ? 0 : pathPattern.hashCode());
        result *= 31 + (Utils.isEmpty(pathPrefix) ? 0 : pathPrefix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (!(bRet = (o instanceof NodeGrantUriPermission)))
                break;
            NodeGrantUriPermission other = (NodeGrantUriPermission) o;
            if (bRet = (this.isEmpty() && other.isEmpty()))
                break;
            if (this.path != null)
                bRet = this.path.equals(other.path);
            if (this.pathPattern != null)
                bRet = this.pathPattern.equals(other.pathPattern);
            if (this.pathPrefix != null)
                bRet = this.pathPrefix.equals(other.pathPrefix);
        } while (false);
        return bRet;
    }

    boolean isEmpty() {
        return Utils.isEmpty(path)
                && Utils.isEmpty(pathPattern)
                && Utils.isEmpty(pathPrefix);
    }

    public Set<Attribute> asSet() {
        Set<Attribute> metaSet = new HashSet<>();
        if (!Utils.isEmpty(path))
            metaSet.add(new Attribute(KEY_ATTR_PATH, path));
        if (!Utils.isEmpty(pathPattern))
            metaSet.add(new Attribute(KEY_ATTR_PATH_PATTERN, pathPattern));
        if (!Utils.isEmpty(pathPrefix))
            metaSet.add(new Attribute(KEY_ATTR_PATH_PREFIX, pathPrefix));
        return metaSet;
    }

}

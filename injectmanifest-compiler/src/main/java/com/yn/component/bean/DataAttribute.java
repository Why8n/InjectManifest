package com.yn.component.bean;

import com.yn.structure.KVSameList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/9/6.
 */

public class DataAttribute {
    private static final String QUALIFIED_NAME_SCHEME = "android:scheme";
    private static final String QUALIFIED_NAME_HOST = "android:host";
    private static final String QUALIFIED_NAME_PORT = "android:port";
    private static final String QUALIFIED_NAME_PATH = "android:path";
    private static final String QUALIFIED_NAME_PATHPATTERN = "android:pathPattern";
    private static final String QUALIFIED_NAME_PATHPREFIX = "android:pathPrefix";
    private static final String QUALIFIED_NAME_MIMETYPE = "android:mimeType";

    private KVSameList<Attribute> data = new KVSameList<>();

    private boolean check(Attribute att) {
        return att.value != null && !att.value.isEmpty();
    }

    public DataAttribute addAttr(String key, String value) {
        addAttr(new Attribute(key, value));
        return this;
    }

    public DataAttribute addAttr(Attribute att) {
        if (check(att))
            data.add(att);
        return this;
    }

    //    android:scheme="string"
    public DataAttribute scheme(String scheme) {
        addAttr(QUALIFIED_NAME_SCHEME, scheme);
        return this;
    }

    //    android:host="string"
    public DataAttribute host(String host) {
        addAttr(QUALIFIED_NAME_HOST, host);
        return this;
    }

    //    android:port="string"
    public DataAttribute port(String port) {
        addAttr(QUALIFIED_NAME_PORT, port);
        return this;
    }

    //    android:path="string"
    public DataAttribute path(String path) {
        addAttr(QUALIFIED_NAME_PATH, path);
        return this;
    }

    //    android:pathPattern="string"
    public DataAttribute pathPattern(String pathPattern) {
        addAttr(QUALIFIED_NAME_PATHPATTERN, pathPattern);
        return this;
    }

    //    android:pathPrefix="string"
    public DataAttribute pathPrefix(String pathPrefix) {
        addAttr(QUALIFIED_NAME_PATHPREFIX, pathPrefix);
        return this;
    }

    //    android:mimeType="string"
    public DataAttribute mimeType(String mimeType) {
        addAttr(QUALIFIED_NAME_MIMETYPE, mimeType);
        return this;
    }


    @Override
    public int hashCode() {
        int result = 17;
        Attribute attribute = data.get(QUALIFIED_NAME_SCHEME);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_HOST);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_PORT);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_PATH);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_PATHPATTERN);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_PATHPREFIX);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        attribute = data.get(QUALIFIED_NAME_MIMETYPE);
        if (attribute != null) {
            result *= 31 + attribute.key.hashCode();
            result *= 31 + attribute.value.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (bRet = !(o instanceof DataAttribute))
                break;
            DataAttribute other = (DataAttribute) o;
            if (!(bRet = (this.data.size() == other.data.size())))
                break;
            for (Attribute attr : this.data) {
                Attribute otherAttr = other.data.get(attr.key);
                if (otherAttr != null && !(bRet = attr.equals(otherAttr)))
                    break;
            }
        } while (false);
        return bRet;
    }

    public Set<Attribute> asSet() {
        return new HashSet<>(data);
    }

}

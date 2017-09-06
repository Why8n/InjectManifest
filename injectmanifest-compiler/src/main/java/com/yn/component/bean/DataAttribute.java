package com.yn.component.bean;

import com.yn.component.Attrs;

/**
 * Created by Whyn on 2017/9/6.
 */

//TODO:: unfinish
public class DataAttribute {
    Attrs data = new Attrs();

    public DataAttribute addAttr(String key, String value) {
        data.addAttr(key, value);
        return this;
    }

    public DataAttribute addAttr(Attribute att) {
        data.addAttr(att);
        return this;
    }

    //    android:scheme="string"
    public DataAttribute scheme(String scheme) {
        data.addAttr("android:scheme", scheme);
        return this;
    }

    //    android:host="string"
    public DataAttribute host(String host) {
        data.addAttr("android:host", host);
        return this;
    }

    //    android:port="string"
    public DataAttribute port(String port) {
        data.addAttr("android:port", port);
        return this;
    }

    //    android:path="string"
    public DataAttribute path(String path) {
        data.addAttr("android:path", path);
        return this;
    }

    //    android:pathPattern="string"
    public DataAttribute pathPattern(String pathPattern) {
        data.addAttr("android:pathPattern", pathPattern);
        return this;
    }

    //    android:pathPrefix="string"
    public DataAttribute pathPrefix(String pathPrefix) {
        data.addAttr("android:pathPrefix", pathPrefix);
        return this;
    }

    //    android:mimeType="string"
    public DataAttribute mimeType(String mimeType) {
        data.addAttr("android:mimeType", mimeType);
        return this;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (Attribute attr : data.all()) {
            result *= 31 + attr.hashCode();
        }
        return result;
    }
}

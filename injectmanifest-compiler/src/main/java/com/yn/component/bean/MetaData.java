package com.yn.component.bean;

import com.yn.utils.Utils;

import java.util.HashSet;
import java.util.Set;

import static com.yn.component.AndroidManifest.KEY_ATTR_NAME;

/**
 * Created by Whyn on 2017/9/7.
 */

public class MetaData {
    public static final String KEY_ATTR_RESOURCE = "android:resource";
    public static final String KEY_ATTR_VALUE = "android:value";

    private String name;
    private String resource;
    private String value;

    public MetaData name(String name) {
        Utils.checkNotNull(name,"meta-data'name must not be null");
        this.name = name;
        return this;
    }

    public MetaData resource(String resource) {
        this.resource = resource;
        return this;
    }

    public MetaData value(String value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public String getValue() {
        return value;
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
            if (!(bRet = (o instanceof MetaData)))
                break;
            MetaData other = (MetaData) o;
            bRet = this.name.equals(other.name);
        } while (false);
        return bRet;
    }

    public Set<Attribute> asSet() {
        Set<Attribute> metaSet = new HashSet<>();
        if (!Utils.isEmpty(name))
            metaSet.add(new Attribute(KEY_ATTR_NAME, name));
        if (!Utils.isEmpty(resource))
            metaSet.add(new Attribute(KEY_ATTR_RESOURCE, resource));
        if (!Utils.isEmpty(value))
            metaSet.add(new Attribute(KEY_ATTR_VALUE, value));
        return metaSet;
    }
}

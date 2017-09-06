package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.structure.RidLastSet;

import java.util.Map;
import java.util.Set;

/**
 * Created by Whyn on 2017/8/31.
 */

public class Attrs {
    private Set<Attribute> attrs = new RidLastSet<>();

    private boolean check(final String value) {
        return value != null && !value.isEmpty();
    }

    public void addAttr(String key, String value) {
        if (check(value)) {
            attrs.add(new Attribute(key, value));
        }
    }

    public void addAttr(Attribute attr) {
        if (check(attr.value)) {
            attrs.add(attr);
        }
    }

    public void addAllAttr(Set<Attribute> _attrs) {
        for (Attribute attr : _attrs) {
            addAttr(attr);
        }
    }

    public void addAttr(Map<String, String> attr) {
        for (String key : attr.keySet()) {
            addAttr(key, attr.get(key));
        }
    }

    public Set<Attribute> all() {
        return attrs;
    }

}

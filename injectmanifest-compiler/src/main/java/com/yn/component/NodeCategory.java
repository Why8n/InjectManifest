package com.yn.component;


import com.yn.component.bean.Attribute;
import com.yn.structure.RidLastSet;

import java.util.Map;
import java.util.Set;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeCategory {
    private Set<Attribute> attrs = new RidLastSet<>();

    public void addAttr(String key, String value) {
        attrs.add(new Attribute(key, value));
    }

    public void addAttr(Map<String, String> attr) {
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            addAttr(entry.getKey(), entry.getValue());
        }
    }

    public void addAttr(Attribute attr) {
        attrs.add(attr);
    }

    public boolean isEmpty() {
        return attrs.isEmpty();
    }

    public Set<Attribute> all() {
        return attrs;
    }
}

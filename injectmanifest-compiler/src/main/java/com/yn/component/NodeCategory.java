package com.yn.component;


import com.yn.component.bean.Attribute;
import com.yn.structure.KVSameList;

import java.util.List;
import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeCategory {
    private final List<Attribute> categories = new KVSameList<>();

    public void addAttr(String key, String value) {
        categories.add(new Attribute(key, value));
    }

    public void addAttr(Map<String, String> attr) {
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            addAttr(entry.getKey(), entry.getValue());
        }
    }

    public void addAttr(Attribute newAttr) {
        categories.add(newAttr);
    }

    public boolean isEmpty() {
        return categories.isEmpty();
    }

    public List<Attribute> all() {
        return categories;
    }
}

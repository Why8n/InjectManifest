package com.yn.component;

import com.yn.component.bean.DataAttribute;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/9/6.
 */

public class NodeData {
    private final Set<DataAttribute> attrs = new HashSet<>();

    public void addData(DataAttribute data) {
        attrs.add(data);
    }

    public boolean isEmpty() {
        return attrs.isEmpty();
    }

    public Set<DataAttribute> all() {
        return attrs;
    }
}

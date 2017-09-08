package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.structure.KVSameList;

import java.util.List;
import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeAction {
    private final List<Attribute> actions = new KVSameList<>();

    public void addAction(String key, String value) {
        actions.add(new Attribute(key, value));
    }

    public void addAction(Map<String, String> actions) {
        for (Map.Entry<String, String> action : actions.entrySet()) {
            addAction(action.getKey(), action.getValue());
        }
    }

    public void addAction(Attribute newAttr) {
        actions.add(newAttr);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public List<Attribute> all() {
        return actions;
    }
}

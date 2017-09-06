package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.structure.RidLastSet;

import java.util.Map;
import java.util.Set;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeAction {
    private Set<Attribute> actions = new RidLastSet<>();

    public void addAction(String key, String value) {
        actions.add(new Attribute(key, value));
    }

    public void addAction(Map<String, String> actions) {
        for (Map.Entry<String, String> action : actions.entrySet()) {
            addAction(action.getKey(), action.getValue());
        }
    }

    public void addAction(Attribute attr) {
        actions.add(attr);
    }
    public boolean isEmpty(){
        return actions.isEmpty();
    }
    public Set<Attribute> all(){
        return actions;
    }
}

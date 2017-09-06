package com.yn.component;

import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeIntentFilter {
    NodeAction actions = new NodeAction();
    NodeCategory categories = new NodeCategory();

    public void copy(NodeIntentFilter other) {
        if (other == null)
            return;
        actions.all().addAll(other.actions.all());
        categories.all().addAll(other.categories.all());
    }

    public void addAction(String key, String value) {
        actions.addAction(key, value);
    }

    public void addAction(Map<String, String> _actions) {
        actions.addAction(_actions);
    }

    public void addCategory(String key, String value) {
        categories.addAttr(key, value);
    }

    public void addCategory(Map<String, String> _category) {
        categories.addAttr(_category);
    }

    public boolean isEmpty() {
        return actions.isEmpty() && categories.isEmpty();
    }
}

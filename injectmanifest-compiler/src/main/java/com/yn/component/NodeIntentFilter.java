package com.yn.component;

import com.yn.component.bean.DataAttribute;

import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeIntentFilter {
    NodeAction actions = new NodeAction();
    NodeCategory categories = new NodeCategory();
    NodeData datas = new NodeData();

    public void copy(NodeIntentFilter other) {
        if (other == null)
            return;
        actions.all().addAll(other.actions.all());
        categories.all().addAll(other.categories.all());
        datas.all().addAll(other.datas.all());
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
    public void addData(DataAttribute data)
    {
        datas.addData(data);
    }

    public boolean isEmpty() {
        return actions.isEmpty() && categories.isEmpty();
    }
}

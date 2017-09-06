package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.define.ConstValue;

import java.util.Set;

/**
 * Created by Whyn on 2017/9/6.
 */

public class NodeApp {
    final Attrs attrs = new Attrs();

    public NodeApp setName(String name) {
        attrs.addAttr(AndroidManifest.ApplicationCollection.KEY_ATTR_NAME, name);
        return this;
    }

    public NodeApp setLabel(String label) {
        attrs.addAttr(AndroidManifest.ApplicationCollection.KEY_ATTR_NAME, label);
        return this;
    }

    public NodeApp addAllAttr(Set<Attribute> attributes) {
        this.attrs.addAllAttr(attributes);
        return this;
    }

    public void copy(NodeApp newItem) {
        this.attrs.addAllAttr(newItem.attrs.all());
    }

}

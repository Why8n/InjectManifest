package com.yn.component;

/**
 * Created by Whyn on 2017/9/5.
 */

import com.yn.component.bean.Attribute;
import com.yn.component.bean.DataAttribute;
import com.yn.component.bean.MetaData;

import java.util.Set;

/**
 * for activity,service,broadcast
 */
public class ComponentBasic extends NodeBasic {
    NodeIntentFilter intentFilter = new NodeIntentFilter();
    NodeMetaData metaDatas = new NodeMetaData();

    public ComponentBasic(String name) {
        super(name);
    }

    public ComponentBasic addAttr(String key, String value) {
        if (value == null || value.isEmpty())
            return this;
        attrs.addAttr(key, value);
        return this;
    }


    public ComponentBasic addAttr(Set<Attribute> attributes) {
        attrs.addAllAttr(attributes);
        return this;
    }


    public ComponentBasic addAction(String key, String value) {
        intentFilter.addAction(key, value);
        return this;
    }


    public ComponentBasic addAction(Set<Attribute> actions) {
        for (Attribute action : actions) {
            intentFilter.addAction(action.key, action.value);
        }
        return this;
    }

    public ComponentBasic addCategory(String key, String value) {
        intentFilter.addCategory(key, value);
        return this;
    }

    public ComponentBasic addCategory(Set<Attribute> categories) {
        for (Attribute category : categories) {
            intentFilter.addCategory(category.key, category.value);
        }
        return this;
    }

    public ComponentBasic addData(DataAttribute data) {
        intentFilter.addData(data);
        return this;
    }

    public ComponentBasic addData(Set<Attribute> data) {
        DataAttribute dataAttribute = new DataAttribute();
        for (Attribute _data : data) {
            dataAttribute.addAttr(_data);
        }
        intentFilter.addData(dataAttribute);
        return this;
    }

    public <T extends ComponentBasic> void copy(T other) {
        if (other == null)
            return;
//        this.name = other.name;
        this.attrs.addAllAttr(other.attrs.all());
        this.intentFilter.copy(other.intentFilter);
        this.metaDatas.addAll(other.metaDatas);
    }

    public ComponentBasic addMetaData(MetaData metaData) {
        this.metaDatas.addMetaData(metaData);
        return this;
    }

    public ComponentBasic addMetaData(Set<Attribute> metaDatas) {
        this.metaDatas.addMetaData(metaDatas);
        return this;
    }
}

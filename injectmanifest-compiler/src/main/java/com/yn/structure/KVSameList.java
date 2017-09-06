package com.yn.structure;

import com.yn.component.bean.Attribute;
import com.yn.utils.Utils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Whyn on 2017/9/7.
 */

public class KVSameList<T extends Attribute> extends LinkedList<T> {
    @Override
    public boolean add(T t) {
        T item = Utils.getSameItemFromCollection(this, t);
        if (item != null && item.value.equals(t.value))
            return false;
        return super.add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {

        boolean bRet = false;
        for (T item : collection) {
            bRet = add(item);
        }
        return bRet;
    }
}

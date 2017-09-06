package com.yn.structure;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Whyn on 2017/9/6.
 */

public class RidLastSet<T> extends HashSet<T> {
    @Override
    public boolean add(T t) {
        if (this.contains(t))
            this.remove(t);
        return super.add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean bRet = true;
        for (T t : collection)
            bRet = add(t);
        return bRet;
    }
}

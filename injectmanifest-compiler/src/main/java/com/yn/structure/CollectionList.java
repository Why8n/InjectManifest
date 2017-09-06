package com.yn.structure;

import com.yn.component.AndroidManifest;
import com.yn.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Whyn on 2017/8/30.
 */

public class CollectionList<T extends AndroidManifest<?>> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        T item = containsType(t);
        if (item != null) {
            return ((AndroidManifest) item).copy(t);
        }
        return super.add(t);
    }

    private T containsType(T t) {
        for (T item : this) {
            if (Utils.isSameType(item, t))
                return item;
        }
        return null;
    }
}

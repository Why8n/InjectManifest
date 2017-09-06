package com.yn.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class UpdateMap<K, V> extends HashMap<K, V> {

    @Override
    public V put(K k, V v) {
        if (containsKey(k))
            remove(k);
        return super.put(k, v);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        super.putAll(map);
    }
}

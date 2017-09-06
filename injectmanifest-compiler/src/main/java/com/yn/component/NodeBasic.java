package com.yn.component;

import com.yn.utils.Utils;

/**
 * Created by Whyn on 2017/9/5.
 */

public class NodeBasic {
    protected final String name;
    protected final Attrs attrs = new Attrs();

    public NodeBasic(String name) {
        Utils.checkNotNull(name, "component name must not be null");
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result *= 31 + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet = false;
        do {
            if (bRet = (this == o))
                break;
            if (bRet = !(o instanceof NodeBasic))
                break;
            NodeBasic other = (NodeBasic) o;
            bRet = this.name.equals(other.name);
        } while (false);
        return bRet;
    }
}

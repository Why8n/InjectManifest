package com.yn.component;

/**
 * Created by Whyn on 2017/9/5.
 */

public class NodeBasic {
    final String name;
    final Attrs attrs = new Attrs();

    public NodeBasic(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (!(bRet = (o instanceof NodeBasic)))
                break;
            NodeBasic other = (NodeBasic) o;
            bRet = this.name.equals(other.name);
        } while (false);
        return bRet;
    }
}

package com.yn.component;

import java.util.Map;

/**
 * Created by Whyn on 2017/8/31.
 */

public class NodeApplication {
    String name;
    private Attrs attrs;

    public NodeApplication(String name) {
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
            if (bRet = !(o instanceof NodeApplication))
                break;
            NodeApplication other = (NodeApplication) o;
            if (this.name.startsWith(".")) {
                if (bRet = other.name.contains(this.name)) {
                    this.name = other.name;
                }
                break;
            }
            bRet = this.name.equals(other.name);
        } while (false);
        return bRet;
    }

    public void addAttr(String key, String value) {
        attrs.addAttr(key, value);
    }

    public NodeApplication addAllAttr(Map<String, String> attributes) {
        attrs.addAttr(attributes);
        return this;
    }
}

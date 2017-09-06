package com.yn.component.bean;

/**
 * Created by Whyn on 2017/8/31.
 */

public class Attribute {
    public final String key;
    public final String value;

    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result *= 31 + key.hashCode();
//        result *= 31 + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean bRet;
        do {
            if (bRet = (this == o))
                break;
            if (bRet = !(o instanceof Attribute))
                break;
            Attribute other = (Attribute) o;
            bRet = this.key.equals(other.key)/* && this.value.equals(other.value)*/;
        } while (false);
        return bRet;
    }
}

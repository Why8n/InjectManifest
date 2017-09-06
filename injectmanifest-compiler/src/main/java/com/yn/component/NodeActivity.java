package com.yn.component;

/**
 * Created by Whyn on 2017/8/30.
 */

public class NodeActivity extends ComponentBasic {
    //    String name;
//    Attrs attrs = new Attrs();
//    NodeIntentFilter intentFilter = new NodeIntentFilter();
    private static final String KEY_STAND_MODE = "android:standMode";

    public NodeActivity(String name) {
        super(name);
    }

    public NodeActivity setStandMode(final String mode) {
        attrs.addAttr(KEY_STAND_MODE, mode);
        return this;
    }

    @Override
    public <T extends ComponentBasic> void copy(T other) {
        if (other == null)
            return;
        super.copy(other);
//        NodeActivity activity = (NodeActivity) other;
    }
//
//    public NodeActivity addAttr(String key, String value) {
//        if (value == null || value.isEmpty())
//            return this;
//        attrs.addAttr(key, value);
//        return this;
//    }
//
//
//    public NodeActivity addAttr(Set<Attribute> attributes) {
//        attrs.addAllAttr(attributes);
//        return this;
//    }
//
//
//    public NodeActivity addAction(String key, String value) {
//        intentFilter.addAction(key, value);
//        return this;
//    }
//
//
//    public NodeActivity addAction(Set<Attribute> actions) {
//        for (Attribute action : actions) {
//            intentFilter.addAction(action.key, action.value);
//        }
//        return this;
//    }
//
//    public NodeActivity addCategory(String key, String value) {
//        intentFilter.addCategory(key, value);
//        return this;
//    }
//
//    public NodeActivity addCategory(Set<Attribute> categories) {
//        for (Attribute category : categories) {
//            intentFilter.addCategory(category.key, category.value);
//        }
//        return this;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result *= 31 + name.hashCode();
////        result *= 31 + label.hashCode();
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        boolean bRet = false;
//        do {
//            if (bRet = (this == o))
//                break;
//            if (bRet = !(o instanceof NodeActivity))
//                break;
//            NodeActivity other = (NodeActivity) o;
//            if (this.name.startsWith(".")) {
//                if (bRet = other.name.contains(this.name)) {
//                    this.name = other.name;
//                }
//                break;
//            }
//            bRet = this.name.equals(other.name);
//        } while (false);
//        return bRet;
//    }

}

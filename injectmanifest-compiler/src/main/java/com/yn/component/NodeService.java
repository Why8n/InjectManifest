package com.yn.component;

/**
 * Created by Whyn on 2017/9/5.
 */

public class NodeService extends ComponentBasic {
//    String name;
//    Attrs attrs = new Attrs();
//    NodeIntentFilter intentFilter = new NodeIntentFilter();

    public NodeService(String name) {
        super(name);
    }

    @Override
    public <T extends ComponentBasic> void copy(T other) {
        if (other == null)
            return;
        super.copy(other);
        NodeService service = (NodeService) other;
        //TODO::add service spec filed
    }
//
//    public NodeService addAttr(String key, String value) {
//        if (value == null || value.isEmpty())
//            return this;
//        attrs.addAttr(key, value);
//        return this;
//    }
//
//    public NodeService addAction(String key, String value) {
//        intentFilter.addAction(key, value);
//        return this;
//    }
//
//    public NodeService addAction(Set<Attribute> actions) {
//        for (Attribute action : actions) {
//            intentFilter.addAction(action.key, action.value);
//        }
//        return this;
//    }
//
//    public NodeService addCategory(String key, String value) {
//        intentFilter.addCategory(key, value);
//        return this;
//    }
//    public NodeService addCategory(Set<Attribute> categories) {
//        for (Attribute actegory : categories) {
//            intentFilter.addCategory(actegory.key, actegory.value);
//        }
//        return this;
//    }
//
//    public void copy(NodeService other) {
//        if (other == null)
//            return;
//        this.name = other.name;
//        this.attrs.addAllAttr(other.attrs.all());
//        this.intentFilter.copy(other.intentFilter);
//    }
//
//    public NodeService addAllAttr(Set<Attribute> attributes) {
//        attrs.addAllAttr(attributes);
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

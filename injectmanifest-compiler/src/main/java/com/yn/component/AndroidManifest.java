package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.define.ConstValue;
import com.yn.utils.Utils;
import com.yn.xmls.interfaces.INode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Whyn on 2017/8/30.
 */

public abstract class AndroidManifest<T> {
    private static final String QUALIFIED_NAME_MANIFEST = "manifest";
    private static final String QUALIFIED_NAME_APPLICATION = "application";
    private static final String QUALIFIED_NAME_ACTIVITY = "activity";
    private static final String QUALIFIED_NAME_SERVICE = "service";
    private static final String QUALIFIED_NAME_PERMISSION = "uses-permission";
    private static final String QUALIFIED_NAME_INTENTFILTER = "intent-filter";
    private static final String QUALIFIED_NAME_ACTION = "action";
    private static final String QUALIFIED_NAME_CATEGORY = "category";

    private static final int ACTION_NONE = 0x00;
    private static final int ACTION_ACTIVITY = 0x01;
    private static final int ACTION_SERVICE = 0x02;
    private static final int ACTION_RECEIVER = 0x03;
    private static final int ACTION_INTENTFILTER = 0x04;
    private static final int ACTION_ACTION = 0x05;
    private static final int ACTION_CATEGORY = 0x06;

    protected static String sPackageName;
    protected final String mQualifiedName;
    protected final Set<T> mCollections = new HashSet<>();

    {
        mQualifiedName = setQName();
    }

    public final String getQName() {
        return mQualifiedName;
    }

    public boolean collect(T item) {
        if (mCollections.contains(item))
            mCollections.remove(item);
        return mCollections.add(item);
    }


    public boolean isEmpty() {
        return mCollections.isEmpty();
    }

    public boolean copy(AndroidManifest<T> other) {
        if (other.mCollections.isEmpty())
            return false;
        return this.mCollections.addAll(other.mCollections);
    }


    public abstract void write2File(INode nodeWriter);

    public abstract void collect(String uri, String localName, String qName, Set<Attribute> attributes);

    public abstract String setQName();


    public static class ManifestCollection extends AndroidManifest<Attribute> {

        @Override
        public void write2File(INode nodeWriter) {
            try {
//                nodeWriter.startDocument();
                nodeWriter.startTag(mQualifiedName, mCollections);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean collect(Attribute item) {
            if ("package".equals(item.key))
                sPackageName = item.value;
            return mCollections.add(item);
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (!qName.equals(mQualifiedName))
                return;
            if (attributes != null && !attributes.isEmpty()) {
                for (Attribute attr : attributes)
                    collect(attr);
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_MANIFEST;
        }
    }

    public static class ApplicationCollection extends AndroidManifest<NodeApp> {
        NodeApp mApp = new NodeApp();

        {
            mCollections.add(mApp);
        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
                nodeWriter.startTag(mQualifiedName, mApp.attrs.all());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean collect(NodeApp newItem) {
            mApp.copy(newItem);
            return true;
        }


        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (!qName.equals(mQualifiedName))
                return;
            if (attributes != null && !attributes.isEmpty()) {
                mApp.addAllAttr(attributes);
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_APPLICATION;
        }
    }

    public static class PermissionCollection extends AndroidManifest<String> {
        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (String permission : mCollections) {
                    nodeWriter.startTag(mQualifiedName, new Attribute(ConstValue.KEY_ATTR_NAME, permission));
                    nodeWriter.endTag(mQualifiedName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (!qName.equals(mQualifiedName))
                return;
            for (Attribute attr : attributes) {
                collect(attr.value);
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_PERMISSION;
        }
    }

    private abstract static class ComponentBasicCollection<T extends ComponentBasic> extends AndroidManifest<T> {

        protected static int lastAction = ACTION_NONE;
        protected String lastComponentName;
        protected boolean isLastElement = false;

        @Override
        public boolean collect(T item) {
            if (mCollections.contains(item)) {
                update(item);
                return true;
            }
            return mCollections.add(item);
        }

        private void update(final T item) {
            for (T curItem : mCollections) {
                if (curItem.equals(item)) {
                    curItem.copy(item);
                }
            }
        }

        protected <V extends Attribute> String getNameFromSet(String targetName, Set<V> set) {
            for (V item : set) {
                if (item.key.equals(targetName))
                    return item.value;
            }
            return null;
        }

        public ComponentBasicCollection<T> isLastElement(boolean lastElement) {
            isLastElement = lastElement;
            return this;
        }
    }

    public static class ActivityCollection extends ComponentBasicCollection<NodeActivity> {

//        private String lastActivityName;
//        private int lastAction = ACTION_NONE;
//        private boolean isLastElement = false;


//        @Override
//        public boolean collect(NodeActivity item) {
//            if (mCollections.contains(item)) {
//                update(item);
//                return true;
//            }
//            return mCollections.add(item);
//        }

//        private void update(final NodeActivity item) {
//            for (NodeActivity curItem : mCollections) {
//                if (curItem.equals(item)) {
//                    curItem.copy(item);
//                }
//            }
//        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeActivity activity : mCollections) {
                    Utils.note("%s: attr= %s", activity.name, activity.attrs.all().toArray());
                    nodeWriter.startTag(mQualifiedName, activity.attrs.all());
                    if (!activity.intentFilter.isEmpty()) {
                        nodeWriter.startTag(QUALIFIED_NAME_INTENTFILTER, new HashSet<Attribute>());
                        for (Attribute action : activity.intentFilter.actions.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_ACTION, action);
                            nodeWriter.endTag(QUALIFIED_NAME_ACTION);
                        }
                        for (Attribute category : activity.intentFilter.categories.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_CATEGORY, category);
                            nodeWriter.endTag(QUALIFIED_NAME_CATEGORY);
                        }
                        nodeWriter.endTag(QUALIFIED_NAME_INTENTFILTER);
                    }
                    nodeWriter.endTag(mQualifiedName);
                }
                if (isLastElement) {
                    nodeWriter.endTag(QUALIFIED_NAME_APPLICATION);
                    nodeWriter.endTag(QUALIFIED_NAME_MANIFEST);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        private <T extends Attribute> String getFromSet(String targetName, Set<T> set) {
//            for (T item : set) {
//                if (item.key.equals(targetName))
//                    return item.value;
//            }
//            return null;
//        }


        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_ACTIVITY;
                String activityName = getNameFromSet(ConstValue.KEY_ATTR_NAME, attributes);
                activityName = Utils.getProperName(sPackageName, activityName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName = activityName).addAttr(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_ACTION)) {
                if (!(lastAction == ACTION_ACTIVITY))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addAction(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_CATEGORY)) {
                if (!(lastAction == ACTION_ACTIVITY))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addCategory(attributes));
                }
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_ACTIVITY;
        }

//        @Override
//        public boolean copy(AndroidManifest<NodeActivity> other) {
//            for (NodeActivity item : other.mCollections) {
//                collect(item);
//            }
//            return true;
//        }
//
//        public ActivityCollection isLastElement(boolean lastElement) {
//            isLastElement = lastElement;
//            return this;
//        }
    }

    public static class ServiceCollection extends ComponentBasicCollection<NodeService> {
//        private String lastServiceName;
//        private int lastAction = ACTION_NONE;
//        private boolean isLastElement = false;

//        @Override
//        public boolean collect(NodeService item) {
//            if (mCollections.contains(item)) {
//                update(item);
//                return true;
//            }
//            return mCollections.add(item);
//        }

//        private void update(final NodeService item) {
//            for (NodeService curItem : mCollections) {
//                if (curItem.equals(item)) {
//                    curItem.copy(item);
//                }
//            }
//        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeService service : mCollections) {
                    nodeWriter.startTag(mQualifiedName, service.attrs.all());
                    if (!service.intentFilter.isEmpty()) {
                        nodeWriter.startTag(QUALIFIED_NAME_INTENTFILTER, new HashSet<Attribute>());
                        for (Attribute action : service.intentFilter.actions.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_ACTION, action);
                            nodeWriter.endTag(QUALIFIED_NAME_ACTION);
                        }
                        for (Attribute category : service.intentFilter.categories.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_CATEGORY, category);
                            nodeWriter.endTag(QUALIFIED_NAME_CATEGORY);
                        }
                        nodeWriter.endTag(QUALIFIED_NAME_INTENTFILTER);
                    }
                    nodeWriter.endTag(mQualifiedName);
                }
                if (isLastElement) {
                    nodeWriter.endTag(QUALIFIED_NAME_APPLICATION);
                    nodeWriter.endTag(QUALIFIED_NAME_MANIFEST);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        private <T extends Attribute> String getFromSet(String targetName, Set<T> set) {
//            for (T item : set) {
//                if (item.key.equals(targetName))
//                    return item.value;
//            }
//            return null;
//        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_SERVICE;
                String serviceName = getNameFromSet(ConstValue.KEY_ATTR_NAME, attributes);
                serviceName = Utils.getProperName(sPackageName, serviceName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName = serviceName).addAttr(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_ACTION)) {
                if (!(lastAction == ACTION_SERVICE))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName).addAction(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_CATEGORY)) {
                if (!(lastAction == ACTION_SERVICE))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName).addCategory(attributes));
                }
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_SERVICE;
        }

//        @Override
//        public boolean copy(AndroidManifest<NodeService> other) {
//            for (NodeService item : other.mCollections) {
//                collect(item);
//            }
//            return true;
//        }

//        public ServiceCollection isLastElement(boolean lastElement) {
//            isLastElement = lastElement;
//            return this;
//        }
    }

    public static class ReceiverCollection extends ComponentBasicCollection<NodeReceiver> {

        private static final String QUALIFIED_NAME_RECEIVER = "receiver";

        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeReceiver receiver : mCollections) {
                    nodeWriter.startTag(mQualifiedName, receiver.attrs.all());
                    if (!receiver.intentFilter.isEmpty()) {
                        nodeWriter.startTag(QUALIFIED_NAME_INTENTFILTER, new HashSet<Attribute>());
                        for (Attribute action : receiver.intentFilter.actions.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_ACTION, action);
                            nodeWriter.endTag(QUALIFIED_NAME_ACTION);
                        }
                        for (Attribute category : receiver.intentFilter.categories.all()) {
                            nodeWriter.startTag(QUALIFIED_NAME_CATEGORY, category);
                            nodeWriter.endTag(QUALIFIED_NAME_CATEGORY);
                        }
                        nodeWriter.endTag(QUALIFIED_NAME_INTENTFILTER);
                    }
                    nodeWriter.endTag(mQualifiedName);
                }
                if (isLastElement) {
                    nodeWriter.endTag(QUALIFIED_NAME_APPLICATION);
                    nodeWriter.endTag(QUALIFIED_NAME_MANIFEST);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_RECEIVER;
                String receiverName = getNameFromSet(ConstValue.KEY_ATTR_NAME, attributes);
                receiverName = Utils.getProperName(sPackageName, receiverName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName = receiverName).addAttr(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_ACTION)) {
                if (!(lastAction == ACTION_RECEIVER))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addAction(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_CATEGORY)) {
                if (!(lastAction == ACTION_RECEIVER))
                    return;
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addCategory(attributes));
                }
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_RECEIVER;
        }
    }

}

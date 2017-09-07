package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.component.bean.DataAttribute;
import com.yn.component.bean.MetaData;
import com.yn.utils.Utils;
import com.yn.xmls.interfaces.INode;

import java.util.Arrays;
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
    private static final String QUALIFIED_NAME_USESPERMISSION = "uses-permission";
    private static final String QUALIFIED_NAME_INTENTFILTER = "intent-filter";
    private static final String QUALIFIED_NAME_ACTION = "action";
    private static final String QUALIFIED_NAME_CATEGORY = "category";
    private static final String QUALIFIED_NAME_DATA = "data";
    private static final String QUALIFIED_NAME_METADATA = "meta-data";


    public static final String DOT = ".";

    public static final String KEY_ATTR_NAME = "android:name";
    public static final String KEY_ATTR_LABEL = "android:label";


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

    public void collect(T item) {
        if (mCollections.contains(item))
            mCollections.remove(item);
        mCollections.add(item);
    }


    public boolean isEmpty() {
        return mCollections.isEmpty();
    }

    public boolean copy(AndroidManifest<T> other) {
        if (other.mCollections.isEmpty())
            return false;
        return this.mCollections.addAll(other.mCollections);
    }

    protected void writeIntentFilter2File(INode nodeWriter, NodeIntentFilter intentFilter) throws Exception {
        if (!intentFilter.isEmpty()) {
            nodeWriter.startTag(QUALIFIED_NAME_INTENTFILTER, new HashSet<Attribute>());
            for (Attribute action : intentFilter.actions.all()) {
                nodeWriter.startTag(QUALIFIED_NAME_ACTION, action);
                nodeWriter.endTag(QUALIFIED_NAME_ACTION);
            }
            for (Attribute category : intentFilter.categories.all()) {
                nodeWriter.startTag(QUALIFIED_NAME_CATEGORY, category);
                nodeWriter.endTag(QUALIFIED_NAME_CATEGORY);
            }
            for (DataAttribute data : intentFilter.datas.all()) {
                nodeWriter.startTag(QUALIFIED_NAME_DATA, data.asSet());
                nodeWriter.endTag(QUALIFIED_NAME_DATA);
            }
            nodeWriter.endTag(QUALIFIED_NAME_INTENTFILTER);
        }
    }

    protected void writeMetaData2File(INode nodeWriter, NodeMetaData metaDatas) throws Exception {
        for (MetaData metaData : metaDatas.metaDatas) {
            nodeWriter.startTag(QUALIFIED_NAME_METADATA, metaData.asSet());
            nodeWriter.endTag(QUALIFIED_NAME_METADATA);
        }
    }


    public abstract void write2File(INode nodeWriter);

    public abstract void collect(String uri, String localName, String qName, Set<Attribute> attributes);

    public abstract String setQName();

    public static class ManifestCollection extends AndroidManifest<NodeManifest> {
        public static final String KEY_PACKAGE = "package";
        public static final String KEY_SHARED_USER_ID = "android:sharedUserId";
        public static final String KEY_SHARED_USER_LABEL = "android:sharedUserLabel";
        public static final String KEY_VERSION_CODE = "android:versionCode";
        public static final String KEY_VERSION_NAME = "android:versionName";
        public static final String KEY_INSTALL_LOCATION = "android:installLocation";

        NodeManifest mManifest = new NodeManifest();

        {
            mCollections.add(mManifest);
        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
//                nodeWriter.startDocument();
                nodeWriter.startTag(mQualifiedName, mManifest.attrs.all());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void collect(NodeManifest item) {
            mManifest.copy(item);
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (!qName.equals(mQualifiedName))
                return;
            if (attributes != null && !attributes.isEmpty()) {
                for (Attribute attr : attributes) {
                    if (KEY_PACKAGE.equals(attr.key)) {
                        sPackageName = attr.value;
                    }
                    mManifest.attrs.addAttr(attr);
                }
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
                writeMetaData2File(nodeWriter, mApp.metaDatas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void collect(NodeApp newItem) {
            mApp.copy(newItem);
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

    public static class UsesPermissionCollection extends AndroidManifest<NodeUsesPermission> {
        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeUsesPermission permission : mCollections) {
                    nodeWriter.startTag(mQualifiedName, permission.asSet());
                    nodeWriter.endTag(mQualifiedName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void collect(NodeUsesPermission item) {
            mCollections.add(item);
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (!qName.equals(mQualifiedName))
                return;
            collect(new NodeUsesPermission().addAttr(attributes));
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_USESPERMISSION;
        }
    }

    private abstract static class ComponentBasicCollection<T extends ComponentBasic> extends AndroidManifest<T> {

        protected static int lastAction = ACTION_NONE;
        protected String lastComponentName;
        protected boolean isLastElement = false;

        @Override
        public void collect(T item) {
            if (mCollections.contains(item)) {
                update(item);
                return;
            }
            mCollections.add(item);
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

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_ACTIVITY;
                String activityName = getNameFromSet(KEY_ATTR_NAME, attributes);
                activityName = Utils.getProperName(sPackageName, activityName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName = activityName).addAttr(attributes));
                }
                return;
            }
            if (!(lastAction == ACTION_ACTIVITY))
                return;
            if (QUALIFIED_NAME_ACTION.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addAction(attributes));
                }
            } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addCategory(attributes));
                }
            } else if (QUALIFIED_NAME_DATA.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    Utils.note("actiivity:data:attrs=" + Arrays.toString(attributes.toArray(new Attribute[attributes.size()])));
                    collect((NodeActivity) new NodeActivity(lastComponentName).addData(attributes));
                }
            }
        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeActivity activity : mCollections) {
                    nodeWriter.startTag(mQualifiedName, activity.attrs.all());
                    writeIntentFilter2File(nodeWriter, activity.intentFilter);
                    writeMetaData2File(nodeWriter, activity.metaDatas);
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
                    writeIntentFilter2File(nodeWriter, service.intentFilter);
                    writeMetaData2File(nodeWriter, service.metaDatas);
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
                lastAction = ACTION_SERVICE;
                String serviceName = getNameFromSet(KEY_ATTR_NAME, attributes);
                serviceName = Utils.getProperName(sPackageName, serviceName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName = serviceName).addAttr(attributes));
                }
                return;
            }
            if (!(lastAction == ACTION_SERVICE))
                return;

            else if (QUALIFIED_NAME_ACTION.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName).addAction(attributes));
                }
            } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName).addCategory(attributes));
                }
            } else if (qName.equals(QUALIFIED_NAME_DATA)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeService) new NodeService(lastComponentName).addData(attributes));
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
                    writeIntentFilter2File(nodeWriter, receiver.intentFilter);
                    writeMetaData2File(nodeWriter, receiver.metaDatas);
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
                String receiverName = getNameFromSet(KEY_ATTR_NAME, attributes);
                receiverName = Utils.getProperName(sPackageName, receiverName);
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName = receiverName).addAttr(attributes));
                }
                return;
            }
            if (!(lastAction == ACTION_RECEIVER))
                return;
            else if (QUALIFIED_NAME_ACTION.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addAction(attributes));
                }
            } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addCategory(attributes));
                }
            } else if (QUALIFIED_NAME_DATA.equals(qName)) {
                if (attributes != null && !attributes.isEmpty()) {
                    collect((NodeReceiver) new NodeActivity(lastComponentName).addData(attributes));
                }
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_RECEIVER;
        }
    }

}

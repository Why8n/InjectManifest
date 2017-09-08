package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.component.bean.DataAttribute;
import com.yn.component.bean.MetaData;
import com.yn.utils.Utils;
import com.yn.xmls.interfaces.INode;

import java.util.HashSet;
import java.util.Set;

import static com.yn.utils.Utils.getSameItemFromCollection;

/**
 * Created by Whyn on 2017/8/30.
 */

public abstract class AndroidManifest<T> {
    private static final String QUALIFIED_NAME_MANIFEST = "manifest";
    private static final String QUALIFIED_NAME_APPLICATION = "application";
    private static final String QUALIFIED_NAME_ACTIVITY = "activity";
    private static final String QUALIFIED_NAME_SERVICE = "service";
    private static final String QUALIFIED_NAME_PROVIDER = "provider";
    private static final String QUALIFIED_NAME_USESPERMISSION = "uses-permission";
    private static final String QUALIFIED_NAME_INTENTFILTER = "intent-filter";
    private static final String QUALIFIED_NAME_ACTION = "action";
    private static final String QUALIFIED_NAME_CATEGORY = "category";
    private static final String QUALIFIED_NAME_DATA = "data";
    private static final String QUALIFIED_NAME_METADATA = "meta-data";
    private static final String QUALIFIED_NAME_GRADT_URI_PERMISSION = "grant-uri-permission";
    private static final String QUALIFIED_NAME_PATH_PERMISSION = "path-permission";


    public static final String DOT = ".";

    public static final String KEY_ATTR_NAME = "android:name";
    public static final String KEY_ATTR_LABEL = "android:label";


    private static final int ACTION_NONE = 0x00;
    private static final int ACTION_APPLICATION = 0x01;
    private static final int ACTION_ACTIVITY = 0x02;
    private static final int ACTION_SERVICE = 0x03;
    private static final int ACTION_RECEIVER = 0x04;
    private static final int ACTION_PROVIDER = 0x04;
//    private static final int ACTION_INTENTFILTER = 0x05;
//    private static final int ACTION_ACTION = 0x06;
//    private static final int ACTION_CATEGORY = 0x07;

    static int lastAction = ACTION_NONE;

    static String sPackageName;

    String lastComponentName;
    boolean isLastElement = false;
    final String mQualifiedName;
    final Set<T> mCollections = new HashSet<>();

    {
        mQualifiedName = setQName();
    }

    public void collect(T item) {
        if (mCollections.contains(item))
            mCollections.remove(item);
        mCollections.add(item);
    }


    public <V extends AndroidManifest<T>> V isLastElement(boolean lastElement) {
        isLastElement = lastElement;
        return (V) this;
    }

    public boolean isEmpty() {
        return mCollections.isEmpty();
    }

    public boolean copy(AndroidManifest<T> other) {
        return !other.mCollections.isEmpty() && this.mCollections.addAll(other.mCollections);
    }

    void writeIntentFilter2File(INode nodeWriter, NodeIntentFilter intentFilter) throws Exception {
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

    void writeMetaData2File(INode nodeWriter, NodeMetaData metaDatas) throws Exception {
        for (MetaData metaData : metaDatas.metaDatas) {
            nodeWriter.startTag(QUALIFIED_NAME_METADATA, metaData.asSet());
            nodeWriter.endTag(QUALIFIED_NAME_METADATA);
        }
    }

    public abstract void collect(String uri, String localName, String qName, Set<Attribute> attributes);

    public abstract void write2File(INode nodeWriter);

    public abstract String setQName();

    public static class ManifestCollection extends AndroidManifest<NodeManifest> {
        static final String KEY_ATTR_XMLNS = "xmlns:android";
        static final String KEY_PACKAGE = "package";
        static final String KEY_SHARED_USER_ID = "android:sharedUserId";
        static final String KEY_SHARED_USER_LABEL = "android:sharedUserLabel";
        static final String KEY_VERSION_CODE = "android:versionCode";
        static final String KEY_VERSION_NAME = "android:versionName";
        static final String KEY_INSTALL_LOCATION = "android:installLocation";

        final NodeManifest mManifest = new NodeManifest();

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
        final NodeApp mApp = new NodeApp(null);

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
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_APPLICATION;
                if (attributes != null && !attributes.isEmpty()) {
                    mApp.addAllAttr(attributes);
                }
                return;
            }
            if (lastAction != ACTION_APPLICATION)
                return;
            if (QUALIFIED_NAME_METADATA.equals(qName)) {
                mApp.addMetaData(attributes);
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
        public void collect(NodeUsesPermission newItem) {
            NodeUsesPermission item = getSameItemFromCollection(mCollections, newItem);
            if (item != null) {
                item.maxSdkVersion = Utils.isEmpty(newItem.maxSdkVersion) ?
                        item.maxSdkVersion : newItem.maxSdkVersion;
                return;
            }
            mCollections.add(newItem);
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

    }

    public static class ActivityCollection extends ComponentBasicCollection<NodeActivity> {

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_ACTIVITY;
                lastComponentName = Utils.getProperName(sPackageName,
                        Utils.getValueFromCollection(KEY_ATTR_NAME, attributes));
                if (!attributes.isEmpty()) {
                    collect(new NodeActivity(lastComponentName).<NodeActivity>addAttr(attributes));
                }
                return;
            }
            if (lastAction != ACTION_ACTIVITY)
                return;
            if (!attributes.isEmpty()) {
                if (QUALIFIED_NAME_ACTION.equals(qName)) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addAction(attributes));
                } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addCategory(attributes));
                } else if (QUALIFIED_NAME_DATA.equals(qName)) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addData(attributes));
                } else if (QUALIFIED_NAME_METADATA.equals(qName)) {
                    collect((NodeActivity) new NodeActivity(lastComponentName).addMetaData(attributes));
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
                lastComponentName = Utils.getProperName(sPackageName,
                        Utils.getValueFromCollection(KEY_ATTR_NAME, attributes));
                if (attributes != null && !attributes.isEmpty()) {
                    collect(new NodeService(lastComponentName).<NodeService>addAttr(attributes));
                }
                return;
            }
            if (!(lastAction == ACTION_SERVICE))
                return;
            if (attributes != null && !attributes.isEmpty()) {
                if (QUALIFIED_NAME_ACTION.equals(qName)) {
                    collect((NodeService) new NodeService(lastComponentName).addAction(attributes));
                } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                    collect((NodeService) new NodeService(lastComponentName).addCategory(attributes));
                } else if (qName.equals(QUALIFIED_NAME_DATA)) {
                    collect((NodeService) new NodeService(lastComponentName).addData(attributes));
                } else if (QUALIFIED_NAME_METADATA.equals(qName)) {
                    collect((NodeService) new NodeService(lastComponentName).addMetaData(attributes));
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
                lastComponentName = Utils.getProperName(sPackageName,
                        Utils.getValueFromCollection(KEY_ATTR_NAME, attributes));
                if (attributes != null && !attributes.isEmpty()) {
                    collect(new NodeReceiver(lastComponentName).<NodeReceiver>addAttr(attributes));
                }
                return;
            }
            if (!(lastAction == ACTION_RECEIVER))
                return;
            if (attributes != null && !attributes.isEmpty()) {
                if (QUALIFIED_NAME_ACTION.equals(qName)) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addAction(attributes));
                } else if (QUALIFIED_NAME_CATEGORY.equals(qName)) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addCategory(attributes));
                } else if (QUALIFIED_NAME_DATA.equals(qName)) {
                    collect((NodeReceiver) new NodeActivity(lastComponentName).addData(attributes));
                } else if (QUALIFIED_NAME_METADATA.equals(qName)) {
                    collect((NodeReceiver) new NodeReceiver(lastComponentName).addMetaData(attributes));
                }
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_RECEIVER;
        }
    }

    public static class ProviderCollection extends AndroidManifest<NodeProvider> {

        @Override
        public void collect(NodeProvider item) {
            if (mCollections.contains(item)) {
                update(item);
                return;
            }
            mCollections.add(item);
        }

        private void update(final NodeProvider newItem) {
            NodeProvider item = Utils.getSameItemFromCollection(mCollections, newItem);
            if (item != null)
                item.copy(newItem);
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            if (qName.equals(mQualifiedName)) {
                lastAction = ACTION_PROVIDER;
                if (!attributes.isEmpty()) {
                    lastComponentName = Utils.getValueFromCollection(KEY_ATTR_NAME, attributes);
                    lastComponentName = Utils.getProperName(sPackageName, lastComponentName);
                    collect(new NodeProvider(lastComponentName).addAttr(attributes));
                }
                return;
            }
            if (lastAction != ACTION_PROVIDER)
                return;
            if (attributes.isEmpty())
                return;
            if (QUALIFIED_NAME_GRADT_URI_PERMISSION.equals(qName)) {
                collect(new NodeProvider(lastComponentName).addGrantUriPermission(attributes));
            } else if (QUALIFIED_NAME_PATH_PERMISSION.equals(qName)) {
                collect(new NodeProvider(lastComponentName).addPathPermissions(attributes));
            }

        }

        @Override
        public void write2File(INode nodeWriter) {
            try {
                for (NodeProvider provider : mCollections) {
                    nodeWriter.startTag(mQualifiedName, provider.attrs.all());
                    writeGrantUriPermission2File(nodeWriter, provider.grantUriPermissions);
                    writeMetaData2File(nodeWriter, provider.metaDatas);
                    writePathPermission2File(nodeWriter, provider.pathPermissions);
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

        private void writePathPermission2File(INode nodeWriter, Set<NodePathPermission> pathPermissions) throws Exception {
            for (NodePathPermission pathPermission : pathPermissions) {
                nodeWriter.startTag(QUALIFIED_NAME_PATH_PERMISSION, pathPermission.asSet());
                nodeWriter.endTag(QUALIFIED_NAME_PATH_PERMISSION);
            }
        }

        private void writeGrantUriPermission2File(INode nodeWriter, Set<NodeGrantUriPermission> grantUriPermissions) throws Exception {
            for (NodeGrantUriPermission grantUriPermission : grantUriPermissions) {
                nodeWriter.startTag(QUALIFIED_NAME_GRADT_URI_PERMISSION, grantUriPermission.asSet());
                nodeWriter.endTag(QUALIFIED_NAME_GRADT_URI_PERMISSION);
            }
        }

        @Override
        public String setQName() {
            return QUALIFIED_NAME_PROVIDER;
        }
    }

}

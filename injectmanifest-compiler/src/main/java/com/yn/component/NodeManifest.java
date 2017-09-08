package com.yn.component;

/**
 * Created by Whyn on 2017/9/6.
 */


public class NodeManifest {
    final Attrs attrs = new Attrs();

    public NodeManifest xmlns(String xmlns) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_ATTR_XMLNS, xmlns);
        return this;
    }

    public NodeManifest setPackage(String pkName) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_PACKAGE, pkName);
        return this;
    }

    public NodeManifest sharedUserId(String sharedUserId) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_SHARED_USER_ID, sharedUserId);
        return this;
    }

    public NodeManifest sharedUserLabel(String sharedUserLabel) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_SHARED_USER_LABEL, sharedUserLabel);
        return this;
    }

    public NodeManifest versionCode(String versionCode) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_VERSION_CODE, versionCode);
        return this;
    }

    public NodeManifest versionName(String versionName) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_VERSION_NAME, versionName);
        return this;
    }

    public NodeManifest installLocation(String installLocation) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_INSTALL_LOCATION, installLocation);
        return this;
    }

    public void copy(NodeManifest item) {
        attrs.addAllAttr(item.attrs.all());
    }
}

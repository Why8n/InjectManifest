package com.yn.component;

import com.yn.component.bean.Attribute;

/**
 * Created by Whyn on 2017/9/6.
 */


public class NodeManifest {
    Attrs attrs = new Attrs();

    public NodeManifest setPackage(String pkName) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_ATTR_NAME, pkName);
        return this;
    }

    public NodeManifest setSharedUserId(String sharedUserId) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_SHARED_USER_ID, sharedUserId);
        return this;
    }

    public NodeManifest setSharedUserLabel(String sharedUserLabel) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_SHARED_USER_LABEL, sharedUserLabel);
        return this;
    }

    public NodeManifest setVersionCode(String versionCode) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_VERSION_CODE, versionCode);
        return this;
    }

    public NodeManifest setVersionName(String versionName) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_VERSION_NAME, versionName);
        return this;
    }

    public NodeManifest setInstallLocation(String installLocation) {
        attrs.addAttr(AndroidManifest.ManifestCollection.KEY_INSTALL_LOCATION, installLocation);
        return this;
    }

    public void copy(NodeManifest item) {
        attrs.addAllAttr(item.attrs.all());
    }
}

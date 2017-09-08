package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.component.bean.MetaData;
import com.yn.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.yn.component.AndroidManifest.KEY_ATTR_NAME;

/**
 * Created by Whyn on 2017/9/7.
 */

public class NodeMetaData {


    final List<MetaData> metaDatas = new ArrayList<>();

    private boolean check(MetaData newMetaData) {
        if (metaDatas.contains(newMetaData)) {
            MetaData metaData = Utils.getSameItemFromCollection(metaDatas, newMetaData);
            String newData = newMetaData.getResource();
            if (!Utils.isEmpty(newData)) {
                metaData.resource(newData);
            }
            newData = newMetaData.getValue();
            if (!Utils.isEmpty(newData)) {
                metaData.value(newData);
            }
            return true;
        }
        return false;
    }

    public void addMetaData(MetaData newMetaData) {
        if (!check(newMetaData))
            metaDatas.add(newMetaData);
    }

//    public void addMetaData(Attribute metaDataAttr) {
//        MetaData metaData = new MetaData();
//        if (KEY_ATTR_NAME.equals(metaDataAttr.key)) {
//            metaData.name(metaDataAttr.value);
//        } else if (MetaData.KEY_ATTR_RESOURCE.equals(metaDataAttr.key)) {
//            metaData.resource(metaDataAttr.value);
//        } else if (MetaData.KEY_ATTR_VALUE.equals(metaDataAttr.key)) {
//            metaData.value(metaDataAttr.value);
//        }
//        addMetaData(metaData);
//    }

    public void addMetaData(Set<Attribute> metaDataAttrs) {
        MetaData metaData = new MetaData();
        for (Attribute attribute : metaDataAttrs) {
            if (KEY_ATTR_NAME.equals(attribute.key)) {
                metaData.name(attribute.value);
            } else if (MetaData.KEY_ATTR_RESOURCE.equals(attribute.key)) {
                metaData.resource(attribute.value);
            } else if (MetaData.KEY_ATTR_VALUE.equals(attribute.key)) {
                metaData.value(attribute.value);
            }
        }
        addMetaData(metaData);
    }


    public void addAll(NodeMetaData metaDatas) {
        for (MetaData metaData : metaDatas.metaDatas) {
            addMetaData(metaData);
        }
    }

    public boolean isEmpty() {
        return metaDatas.isEmpty();
    }

}

package com.yn.xmls.factory;

import com.yn.component.Collections;
import com.yn.xmls.SaxXml;
import com.yn.xmls.interfaces.IXml;

/**
 * Created by Whyn on 2017/8/30.
 */

public class XmlFactory {
    private XmlFactory() {
    }

    public static IXml createXmlDecoder(Collections androidManifest) {
        return new SaxXml(androidManifest);
    }
}

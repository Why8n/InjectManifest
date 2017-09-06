package com.yn.xmls.interfaces;

import java.io.Writer;

/**
 * Created by Whyn on 2017/8/30.
 */

public interface IXml {
    void parseXml(String path);

    void createXml(Writer writer);
}

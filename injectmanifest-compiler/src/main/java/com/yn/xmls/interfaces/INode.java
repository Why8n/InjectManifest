package com.yn.xmls.interfaces;

import com.yn.component.bean.Attribute;

import java.util.Set;

/**
 * Created by Whyn on 2017/8/31.
 */

public interface INode {
    void startDocument() throws Exception;

    void endDocument() throws Exception;

    void startTag(String tagName, Set<Attribute> attrs) throws Exception;

    void startTag(String tagName, Attribute attr) throws Exception;

    void endTag(String qName) throws Exception;

    void text(String content) throws Exception;
}

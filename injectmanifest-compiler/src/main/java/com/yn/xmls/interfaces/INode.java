package com.yn.xmls.interfaces;

import com.yn.component.bean.Attribute;

import java.util.Collection;

/**
 * Created by Whyn on 2017/8/31.
 */

public interface INode {
    void startDocument() throws Exception;

    void endDocument() throws Exception;

    void startTag(final String tagName, final Collection<? extends Attribute> attrs) throws Exception;

    void startTag(final String tagName, final Attribute attr) throws Exception;

    void endTag(final String qName) throws Exception;

    void text(final String content) throws Exception;
}

package com.yn.xmls.interfaces.impl;

import com.yn.component.bean.Attribute;
import com.yn.xmls.interfaces.INode;

import org.xml.sax.helpers.AttributesImpl;

import java.util.Collection;

import javax.xml.transform.sax.TransformerHandler;

/**
 * Created by Whyn on 2017/8/31.
 */

public class SaxINodeImpl implements INode {
    private final TransformerHandler mHanlder;

    public SaxINodeImpl(TransformerHandler handler) {
        mHanlder = handler;
    }

    @Override
    public void startDocument() throws Exception {
        mHanlder.startDocument();
    }

    @Override
    public void endDocument() throws Exception {
        mHanlder.endDocument();
    }

    //    startElement(String uri, String localName, String qName, Attributes atts)
    @Override
    public void startTag(final String tagName, final Collection<? extends Attribute> attrs) throws Exception {
        AttributesImpl attributes = new AttributesImpl();
        for (Attribute attr : attrs) {
            attributes.addAttribute("", "", attr.key, "", attr.value);
        }
        mHanlder.startElement("", "", tagName, attributes);
    }

    @Override
    public void startTag(final String tagName, final Attribute attr) throws Exception {
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", attr.key, "", attr.value);
        mHanlder.startElement("", "", tagName, attributes);
    }

    @Override
    public void endTag(final String qName) throws Exception {
        mHanlder.endElement("", "", qName);
    }

    @Override
    public void text(final String content) throws Exception {
        mHanlder.characters(content.toCharArray(), 0, content.length());
    }
}

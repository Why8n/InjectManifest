package com.yn.xmls;

import com.yn.component.Collections;
import com.yn.utils.Utils;
import com.yn.xmls.interfaces.IXml;
import com.yn.xmls.interfaces.impl.SaxINodeImpl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Whyn on 2017/8/30.
 */

public class SaxXml implements IXml {

    private final Collections mAndroidManifest;

    public SaxXml(Collections manifest) {
        Utils.checkNotNull(manifest, String.format(Locale.getDefault(),
                "the param %s passed to class %s must not be null",
                Collections.class.getSimpleName(), SaxXml.class.getSimpleName()));
        mAndroidManifest = manifest;
    }

    @Override
    public void parseXml(String path) {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try {
            SAXParser saxparser = saxfac.newSAXParser();
            InputStream is = new FileInputStream(path);
            saxparser.parse(is, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    mAndroidManifest.collect(uri, localName, qName, attributes);
                }
            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createXml(Writer writer) {

        try {
            SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();

            SaxINodeImpl saxNodeImpl = new SaxINodeImpl(handler);

            Transformer info = handler.getTransformer();
            info.setOutputProperty(OutputKeys.INDENT, "yes");
            info.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            info.setOutputProperty(OutputKeys.VERSION, "1.0");
            StreamResult result = new StreamResult(writer);
            handler.setResult(result);
            saxNodeImpl.startDocument();
            mAndroidManifest.write2Xml(saxNodeImpl);
            saxNodeImpl.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


package com.yn.component;

import com.yn.component.bean.Attribute;
import com.yn.xmls.interfaces.INode;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Whyn on 2017/8/30.
 */

public class Collections {
    private final List<AndroidManifest<?>> components = new ArrayList<>();
    private final Map<Object, AndroidManifest<?>> tags = new HashMap<>();
    private final AndroidManifest<Object> delegate = new AndroidManifest<Object>() {
        @Override
        public void write2File(INode nodeWriter) {
            for (AndroidManifest<?> compoent : components)
                compoent.write2File(nodeWriter);
        }

        @Override
        public void collect(String uri, String localName, String qName, Set<Attribute> attributes) {
            for (AndroidManifest<?> compoent : components)
                compoent.collect(uri, localName, qName, attributes);
        }

        @Override
        public String setQName() {
            return null;
        }

    };

    public <A, B extends AndroidManifest<A>> boolean plantComponent(B component) {
        return components.add(component);
    }

    public AndroidManifest<?> getTag(Object tag) {
        return tags.get(tag);
    }

    public <A, B extends AndroidManifest<A>> boolean plantComponent(B component, Object tag) {
        tags.put(tag, component);
        return components.add(component);
    }

    public void collect(String uri, String localName, String qName, Attributes attributes) {
        Set<Attribute> attributesSet = new HashSet<>();
        for (int i = 0, length = attributes.getLength(); i < length; ++i) {
            attributesSet.add(new Attribute(attributes.getQName(i), attributes.getValue(i)));
        }
        delegate.collect(uri, localName, qName, attributesSet);
    }

    public void write2Xml(INode nodeWriter) {
        delegate.write2File(nodeWriter);
    }
}

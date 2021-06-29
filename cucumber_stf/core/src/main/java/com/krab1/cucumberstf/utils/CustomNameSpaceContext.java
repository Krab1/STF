package com.krab1.cucumberstf.utils;

import lombok.AllArgsConstructor;

import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

@AllArgsConstructor
public class CustomNameSpaceContext implements NamespaceContext {
    private final String customPrefix;
    private final String customUrl;

    @Override
    public String getNamespaceURI(String prefix) {
        return customPrefix.equals(prefix) ? customUrl : null;
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return null;
    }

    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        return null;
    }
}

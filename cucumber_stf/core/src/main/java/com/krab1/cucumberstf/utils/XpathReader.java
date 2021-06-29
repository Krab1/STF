package com.krab1.cucumberstf.utils;

import net.sf.saxon.om.NamespaceConstant;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;

public class XpathReader {
    private final DocumentBuilder documentBuilder;
    private final XPathFactory factory;
    private final NamespaceContext namespaceContext;

    XpathReader(NamespaceContext namespaceContext){
        System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON, "net.sf.saxon.xpath.XPathFacotyImpl");
        try {
            this.factory = XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.namespaceContext = namespaceContext;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    Object read(String xml, String path, QName returnType){
        try{
            Document document = getDocument(xml);
            XPath xPath = factory.newXPath();
            xPath.setNamespaceContext(namespaceContext);
            XPathExpression expression = xPath.compile(path);
            return expression.evaluate(document, returnType);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    String read(String xml, String path){ return String.valueOf(read(xml,path, XPathConstants.STRING)); }

    String read(String xml, String path, int index){ return read(xml, path + "[" + index + "]"); }

    Boolean exist(String xml, String path){
        return (Boolean) read(xml, "exists(" + path + ")", XPathConstants.BOOLEAN);
    }

    private Document getDocument(String xml) throws IOException, SAXException {
        InputSource inputSource = new InputSource(new StringReader(xml));
        return documentBuilder.parse(inputSource);
    }
}

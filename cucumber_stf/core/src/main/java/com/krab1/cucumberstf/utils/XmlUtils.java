package com.krab1.cucumberstf.utils;

public class XmlUtils {

    private XmlUtils(){ }

    public static String xpathValue(String xml, String xpath){ return getXpathReader().read(xml, xpath); }

    public static String xpathValue(String xml, String xpath, int index ){ return getXpathReader().read(xml, xpath, index); }

    public static Boolean xpathNotExist(String xml, String xpath){ return !getXpathReader().exist(xml, xpath); }

    private static XpathReader getXpathReader() {
        return new XpathReader(new CustomNameSpaceContext("", ""));
    }
}

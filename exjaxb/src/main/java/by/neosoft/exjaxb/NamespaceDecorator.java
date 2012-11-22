/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.neosoft.exjaxb;

import java.util.HashMap;

import javax.xml.XMLConstants;

/**
 * NamespaceDecorator
 * 
 * Реализует шаблон Decorator для управления реализацией NamespacePrefixMapperImpl. Если не
 * проинициализирован, используются стандартные пространства имен.
 * 
 * @author oreshkevich
 */
public class NamespaceDecorator {

  HashMap<String, String> namespacePrefixMap = new HashMap<String, String>();

  public NamespaceDecorator() {

    // xmlns:xs="http://www.w3.org/2001/XMLSchema"
    namespacePrefixMap.put(XMLConstants.W3C_XML_SCHEMA_NS_URI, "xs");

    // xmlns:jxb="http://java.sun.com/xml/ns/jaxb"
    namespacePrefixMap.put("http://java.sun.com/xml/ns/jaxb", "jxb");
  }

  public HashMap<String, String> getNamespacePrefixMap() {
    return namespacePrefixMap;
  }
}

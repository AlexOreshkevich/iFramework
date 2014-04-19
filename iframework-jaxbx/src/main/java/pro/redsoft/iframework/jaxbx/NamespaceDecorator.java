/*
The MIT License (MIT)

Copyright (c) 2013 - 2014  REDSOFT.PRO

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package pro.redsoft.iframework.jaxbx;

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

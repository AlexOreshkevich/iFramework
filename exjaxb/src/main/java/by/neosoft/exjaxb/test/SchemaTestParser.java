/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.neosoft.exjaxb.test;

import java.util.HashMap;

import by.neosoft.exjaxb.parser.ExJAXBParser;
import by.neosoft.exjaxb.test.adhoc.Adhocroot;

/**
 * SchemaTestParser
 * 
 * @author oreshkevich
 */
public class SchemaTestParser implements ExJAXBParser<Adhocroot> {

  @Override
  public HashMap<String, String> getNamespacePrefixMap() {

    HashMap<String, String> map = new HashMap<String, String>();
    map.put("http://www.prognoz.ru/Adhoc", "xsi");

    return map;
  }

  @Override
  public String getNamespace() {
    return "http://www.prognoz.ru/Adhoc";
  }

  @Override
  public String getRootTagName() {
    return "Adhoc";
  }

  @Override
  public String getSchemaInstancePrefix() {
    return "xsi";
  }
}

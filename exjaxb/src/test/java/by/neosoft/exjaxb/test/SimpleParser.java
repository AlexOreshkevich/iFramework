/**
 * 
 */
package by.neosoft.exjaxb.test;

import java.util.HashMap;

import by.neosoft.exjaxb.AbstractJAXBParser;
import by.neosoft.exjaxb.test.config.Config;

class SimpleParser extends AbstractJAXBParser<Config> {

  @Override
  public String getNamespace() {
    return "http://www.example.com/example";
  }

  @Override
  public HashMap<String, String> getNamespacePrefixMap() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("http://www.example.com/example", "xsi");
    return map;
  }

  @Override
  public String getRootTagName() {
    return "config";
  }

  @Override
  public String getSchemaInstancePrefix() {
    return "xsi";
  }
}
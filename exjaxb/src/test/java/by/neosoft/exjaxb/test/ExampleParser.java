/**
 * 
 */
package by.neosoft.exjaxb.test;

import java.util.HashMap;

import by.neosoft.exjaxb.test.config.Config;
import by.neosoft.iframework.exjaxb.AbstractJAXBParser;

public class ExampleParser extends AbstractJAXBParser<Config> {

  public ExampleParser(Class<Config> classType) {
    super(classType);
  }

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
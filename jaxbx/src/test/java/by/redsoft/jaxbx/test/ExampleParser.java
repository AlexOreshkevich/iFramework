/**
 * 
 */
package by.redsoft.jaxbx.test;

import java.util.HashMap;

import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;

import by.redsoft.jaxbx.test.config.Config;

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
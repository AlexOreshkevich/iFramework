/**
 * 
 */
package by.neosoft.iframework.example.shared;

import java.util.HashMap;

import by.neosoft.iframework.example.shared.config.Config;
import by.neosoft.iframework.exjaxb.AbstractJAXBParser;

/**
 * ConfigParser
 * 
 * @author alex oreshkevich
 */
public class ConfigParser extends AbstractJAXBParser<Config> {

  /**
   * @param classType
   */
  public ConfigParser(Class<Config> classType) {
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

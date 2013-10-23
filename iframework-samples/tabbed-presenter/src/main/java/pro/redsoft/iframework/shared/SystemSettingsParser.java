/**
 * 
 */
package pro.redsoft.iframework.shared;

import java.util.HashMap;

import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;
import pro.redsoft.iframework.shared.config.Config;

/**
 * SystemSettingsParser
 * 
 * @author alex oreshkevich
 */
public class SystemSettingsParser extends AbstractJAXBParser<Config> {

  public SystemSettingsParser(Class<Config> classType) {
    super(classType);
  }

  @Override
  public String getNamespace() {
    return "http://www.intertrust.ru";
  }

  @Override
  public HashMap<String, String> getNamespacePrefixMap() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("http://www.intertrust.ru", "xsi");
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

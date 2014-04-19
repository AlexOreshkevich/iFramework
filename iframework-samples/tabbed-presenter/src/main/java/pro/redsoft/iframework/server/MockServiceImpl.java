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
package pro.redsoft.iframework.server;

import java.io.File;

import javax.servlet.ServletException;

import pro.redsoft.iframework.client.application.service.ConfigService;
import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;
import pro.redsoft.iframework.shared.SystemSettingsParser;
import pro.redsoft.iframework.shared.config.Config;
import pro.redsoft.iframework.shared.config.SystemSettings;
import pro.redsoft.iframework.shared.config.UserSettings;

/**
 * MockServiceImpl.
 * 
 * @since 4.3
 * @author Alex N. Oreshkevich
 */
public class MockServiceImpl extends AbstractConfigServlet<Config> implements ConfigService {

  private static final long                serialVersionUID = -1287811023799214221L;

  private final static String              cfgPath          = System.getProperty("user.dir")
                                                                + "/src/main/webapp/WEB-INF/";

  private final AbstractJAXBParser<Config> parser           = new SystemSettingsParser(Config.class);

  StringBuilder                            log              = new StringBuilder();

  @Override
  public void init() throws ServletException {
    log.append("123456");
  }

  @Override
  public StringBuilder getLogMessage() {
    return log;
  }

  @Override
  public String getConfigDirProperty() {
    return "ru.intertrust.cmj.configFileLocation.4.3";
  }

  @Override
  public String getExtConfigDirPath() {
    return "/Users/neo/java/jworkspace/iFramework/iframework-samples/tabbed-presenter/xml";
  }

  @Override
  public String getInitParameters() {
    return "Init parameter 1, Init parameter 2, " + getExtConfigDirPath();
  }

  @Override
  public File getSchemaRealPath() {
    return new File(cfgPath + "config.xsd");
  }

  @Override
  public File getInternalUserConfig() {
    return new File(cfgPath + "config-user.xml");
  }

  @Override
  public File getInternalSystemConfig() {
    return new File(cfgPath + "config-system.xml");
  }

  @Override
  protected AbstractJAXBParser<Config> getParser() {
    return parser;
  }

  @Override
  public Config getClientSettings() throws RuntimeException {
    return loadSettings();
  }

  @Override
  protected Config mergeResults(Object o1, Object o2, String log) {
    Config result = new Config();
    result.setSystem((SystemSettings) o1);
    result.setUser((UserSettings) o2);
    result.setLogMessage(log);
    return result;
  }
}

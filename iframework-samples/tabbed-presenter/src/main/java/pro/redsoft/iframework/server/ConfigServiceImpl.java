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
import java.util.Enumeration;

import pro.redsoft.iframework.client.application.service.ConfigService;
import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;
import pro.redsoft.iframework.shared.SystemSettingsParser;
import pro.redsoft.iframework.shared.config.Config;
import pro.redsoft.iframework.shared.config.SystemSettings;
import pro.redsoft.iframework.shared.config.UserSettings;

/**
 * Service for working with configuration sources: *.xml and *.properties files.
 * Implementation of {@link ConfigService}.
 * 
 * @author Alex N. Oreshkevich
 */
public class ConfigServiceImpl extends AbstractConfigServlet<Config> implements ConfigService {

  private static final long                serialVersionUID = 5525521520584334736L;

  private final AbstractJAXBParser<Config> parser           = new SystemSettingsParser(Config.class);

  @Override
  protected String getConfigDirProperty() { // never null
    return getServletContext().getInitParameter("configDirPropertyName");
  }

  @Override
  protected String getExtConfigDirPath() { // can be null
    return getServletContext().getInitParameter(getConfigDirProperty());
  }

  @Override
  protected String getInitParameters() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nLoad servlet context parameters...\n");
    Enumeration<?> s = getServletContext().getInitParameterNames();
    while (s.hasMoreElements()) {
      String next = s.nextElement().toString();
      sb.append("\n[");
      sb.append(next);
      sb.append("] = ");
      sb.append(getServletContext().getInitParameter(next));
    }
    sb.append("\n\n");
    return sb.toString();
  }

  @Override
  protected File getSchemaRealPath() throws Exception {
    return getFileAsLazyDeployed("/WEB-INF/config.xsd", appendFileSeparator(getExtConfigDirPath())
        + "config.xsd");
  }

  @Override
  protected File getInternalUserConfig() throws Exception {
    return getFileAsLazyDeployed("/WEB-INF/config-user.xml",
        appendFileSeparator(getExtConfigDirPath()) + "config-user.xml");
  }

  @Override
  protected File getInternalSystemConfig() throws Exception {
    return getFileAsLazyDeployed("/WEB-INF/config-system.xml",
        appendFileSeparator(getExtConfigDirPath()) + "config-system.xml");
  }

  private File getFileAsLazyDeployed(String pathSrc, String pathFS) {
    File file = null;
    try {
      file = new File(getServletContext().getRealPath(pathSrc));
      if (!file.exists()) {
        throw new UnsupportedOperationException("!exists");
      }
    }
    catch (Exception e) {
      file = new File(pathFS);
    }
    return file;
  }

  @Override
  protected AbstractJAXBParser<Config> getParser() {
    return parser;
  }

  @Override
  protected Config mergeResults(Object o1, Object o2, String log) {
    Config result = new Config();
    result.setSystem((SystemSettings) o1);
    result.setUser((UserSettings) o2);
    result.setLogMessage(log);
    return result;
  }

  @Override
  public Config getClientSettings() throws RuntimeException {
    return loadSettings();
  }
}

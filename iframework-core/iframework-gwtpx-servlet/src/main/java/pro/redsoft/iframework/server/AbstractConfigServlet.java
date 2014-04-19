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

import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;
import pro.redsoft.iframework.jaxbx.FileSystemUtils;
import pro.redsoft.iframework.shared.Setting;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * AbstractConfigServlet.
 * 
 * @author Alex N. Oreshkevich
 * @param <T>
 *          abstract setting
 */
public abstract class AbstractConfigServlet<T extends Setting> extends RemoteServiceServlet {

  public static String          CFG_PROP_NAME     = "config";

  protected static final String FSEP              = System.getProperty("file.separator");

  private static final long     serialVersionUID  = -7808411015210521860L;

  protected final StringBuilder contextParameters = new StringBuilder();

  protected final StringBuilder logMessage        = new StringBuilder();

  protected String appendFileSeparator(String srcPath) {
    String correctedPath = srcPath;
    if (srcPath != null && !srcPath.endsWith(FSEP)) {
      correctedPath += FSEP; // append file.separator ('/' or '\') if missing
    }
    return correctedPath;
  }

  protected abstract String getConfigDirProperty();

  public StringBuilder getContextParameters() {
    return contextParameters;
  }

  protected abstract String getExtConfigDirPath();

  protected abstract String getInitParameters();

  protected abstract File getInternalSystemConfig() throws Exception;

  protected abstract File getInternalUserConfig() throws Exception;

  protected abstract AbstractJAXBParser<T> getParser();

  protected abstract File getSchemaRealPath() throws Exception;

  @Override
  public void init() throws ServletException {

    // save to log message all servlet context parameters
    contextParameters.append(getInitParameters());

    // check if missing or illegal ru.intertrust.cmj.configFileLocation.X.X parameter.
    String extConfigPath = getExtConfigDirPath();
    if (extConfigPath == null || extConfigPath.isEmpty() || !new File(extConfigPath).isDirectory()) {
      logMessage.append("Null, empty or illegal value [");
      logMessage.append(extConfigPath);
      logMessage.append("] of ");
      logMessage.append(getConfigDirProperty());
      logMessage
          .append(" servlet context init parameter. Should be a directory that contains config source files.");
    }

    // save to log message external configuration path
    contextParameters.append(extConfigPath);

    getServletContext().setAttribute(CFG_PROP_NAME, loadSettings());
  }

  protected T loadSettings() {

    // result based on two configuration source
    Object systemSettings = null;
    Object userSettings = null;

    // if external config path is invalid
    if (logMessage.length() > 0) {

      try {
        systemSettings = readConfig(getInternalSystemConfig()).getSystem();
        userSettings = readConfig(getInternalUserConfig()).getUser();
      }
      catch (Exception e) {
        throw new RuntimeException("FATAL: Failed to load default config. ",
            e.getCause() != null ? e.getCause() : e);
      }
    }
    else { // using external configuration path
      try {

        // load external path
        String path = appendFileSeparator(getExtConfigDirPath());

        // load system settings
        systemSettings = readConfig(new File(path + "config-system.xml")).getSystem();

        // use internal user settings file if missing
        File userSettingsFile = new File(path + "config-user.xml");
        if (!userSettingsFile.exists()) {
          userSettingsFile = getInternalUserConfig();
        }

        userSettings = readConfig(userSettingsFile).getUser();
      }
      // common errors (for using external files)
      catch (Exception e) {
        Throwable caught = e.getCause() == null ? e : e.getCause();
        logMessage
            .append("\nError while working with configuration source: " + caught.getMessage());
        return loadSettings(); // logMessage.length > 0; so internal config should be used
      }
    }

    // get log message
    String log = logMessage.toString();
    if (log.isEmpty() || logMessage.length() == 0) {
      log = null;
    }

    // build result config (merge results)
    return mergeResults(systemSettings, userSettings, logMessage.toString());
  }

  protected abstract T mergeResults(Object o1, Object o2, String log);

  protected final T readConfig(File cfgSrc) throws Exception {
    return getParser().unmarshall(FileSystemUtils.readFile(cfgSrc),
        getParser().getSchema(getSchemaRealPath()), false);
  }

  public StringBuilder getLogMessage() {
    return logMessage;
  }
}

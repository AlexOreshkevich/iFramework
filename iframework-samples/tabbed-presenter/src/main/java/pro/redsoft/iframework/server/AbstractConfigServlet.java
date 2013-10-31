/**
 * Copyright 2013 REDSOFT.PRO
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.redsoft.iframework.server;

import java.io.File;

import javax.servlet.ServletException;

import pro.redsoft.iframework.client.application.service.ConfigService;
import pro.redsoft.iframework.jaxbx.AbstractJAXBParser;
import pro.redsoft.iframework.jaxbx.FileSystemUtils;
import pro.redsoft.iframework.shared.SystemSettingsParser;
import pro.redsoft.iframework.shared.config.Config;
import pro.redsoft.iframework.shared.config.SystemSettings;
import pro.redsoft.iframework.shared.config.UserSettings;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * AbstractConfigServlet.
 * 
 * @since 4.3
 * @author Alex N. Oreshkevich
 */
public abstract class AbstractConfigServlet extends RemoteServiceServlet implements ConfigService {

  private static final long     serialVersionUID = -7808411015210521860L;

  protected final static String FSEP             = System.getProperty("file.separator");

  protected abstract String getInitParameters();

  protected abstract String getConfigDirProperty();

  protected abstract String getExtConfigDirPath();

  protected abstract File getSchemaRealPath();

  protected abstract File getInternalUserConfig();

  protected abstract File getInternalSystemConfig();

  protected final StringBuilder logMessage        = new StringBuilder();

  protected final StringBuilder contextParameters = new StringBuilder();

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
  }

  protected final Config readConfig(File cfgSrc) throws Exception {

    // parser init
    AbstractJAXBParser<Config> parser = new SystemSettingsParser(Config.class);

    // start unmarshalling
    return parser.unmarshall(FileSystemUtils.readFile(cfgSrc),
        parser.getSchema(getSchemaRealPath()), false);
  }

  @Override
  public Config getClientSettings() throws RuntimeException {

    // result based on two configuration source
    SystemSettings systemSettings = null;
    UserSettings userSettings = null;

    // if external config path is invalid
    if (logMessage.length() > 0) {
      try {
        systemSettings = readConfig(getInternalSystemConfig()).getSystem();
        userSettings = readConfig(getInternalUserConfig()).getUser();
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    else { // using external configuration path
      try {

        // load external path
        String path = getExtConfigDirPath();
        if (!path.endsWith(FSEP)) {
          path += FSEP; // append file.separator ('/' or '\') if missing
        }

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
        logMessage.append("\nError with configuration files: " + caught.getMessage());
        return getClientSettings(); // logMessage.length > 0; so internal config should be used
      }
    }

    // build result config (merge results)
    Config result = new Config();
    result.setSystem(systemSettings);
    result.setUser(userSettings);
    result.setLogMessage(logMessage.toString());

    return result;
  }

  public StringBuilder getLogMessage() {
    return logMessage;
  }

  public StringBuilder getContextParameters() {
    return contextParameters;
  }
}

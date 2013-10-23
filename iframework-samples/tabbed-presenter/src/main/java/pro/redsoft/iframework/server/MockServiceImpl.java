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

/**
 * MockServiceImpl.
 * 
 * @since 4.3
 * @author Alex N. Oreshkevich
 */
public class MockServiceImpl extends AbstractConfigServlet {

  private static final long   serialVersionUID = -1287811023799214221L;

  private final static String cfgPath          = System.getProperty("user.dir")
                                                   + "/src/main/webapp/WEB-INF/";

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
}

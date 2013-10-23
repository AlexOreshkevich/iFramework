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
import java.util.Enumeration;

import pro.redsoft.iframework.client.application.service.ConfigService;

/**
 * Сервис для настройки серверных параметров приложения.
 * 
 * @since 4.1
 * @author Alex N. Oreshkevich
 */
public class ConfigServiceImpl extends AbstractConfigServlet implements ConfigService {

  private static final long serialVersionUID = 5525521520584334736L;

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
  protected File getSchemaRealPath() {
    return new File(getServletContext().getRealPath("/WEB-INF/config.xsd"));
  }

  @Override
  protected File getInternalUserConfig() {
    return new File(getServletContext().getRealPath("/WEB-INF/config-user.xml"));
  }

  @Override
  protected File getInternalSystemConfig() {
    return new File(getServletContext().getRealPath("/WEB-INF/config-system.xml"));
  }
}

package pro.redsoft.iframework.server;

import java.io.File;
import java.util.Enumeration;

/**
 * Сервис для настройки серверных параметров приложения.
 * 
 * @since 4.1
 * @author Alex N. Oreshkevich
 */
public class ConfigServiceImpl extends AbstractConfigServlet {

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

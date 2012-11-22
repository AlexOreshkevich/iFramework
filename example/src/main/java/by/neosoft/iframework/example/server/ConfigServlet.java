package by.neosoft.iframework.example.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {

  private static final long serialVersionUID = 8232303229476547470L;
  private static Logger     LOG              = Logger.getLogger(ConfigServlet.class);
  private static String     contextPath;
  private static String     realContextPath;
  private static String     fileSeparator;
  private static String     tempDir;

  /**
   * Сервлет для определения параметров серверной стороны.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ConfigServlet() {
    super();
  }

  @Override
  public void init(ServletConfig config) throws ServletException {

    LOG.debug("catalina.home=" + System.getProperty("catalina.home"));
    LOG.debug("catalina.base=" + System.getProperty("catalina.base"));
    // osLinux = System.getProperty("os.name").equalsIgnoreCase("linux");
    fileSeparator = System.getProperty("file.separator");
    LOG.debug("fileSeparator=" + fileSeparator);
    tempDir = System.getProperty("java.io.tmpdir") + fileSeparator;
    contextPath = config.getServletContext().getContextPath();
    LOG.debug("contextPath=" + contextPath);
    String web_inf = config.getServletContext().getRealPath("/WEB-INF/");
    LOG.debug("path /WEB-INF/ = " + web_inf);
    realContextPath = web_inf.substring(0, web_inf.length() - 8) + fileSeparator;
    LOG.debug("realContextPath = " + realContextPath);

    LOG.debug("getServletContextName = " + config.getServletContext().getServletContextName());
    LOG.debug("getMajorVersion = " + config.getServletContext().getMajorVersion());
    LOG.debug("getMinorVersion = " + config.getServletContext().getMinorVersion());
    LOG.debug("getServerInfo = " + config.getServletContext().getServerInfo());

    new ConfigServer(contextPath, realContextPath, tempDir);
    LOG.info("Приложение испешно развернуто на сервере. Путь: " + realContextPath);
  }
}
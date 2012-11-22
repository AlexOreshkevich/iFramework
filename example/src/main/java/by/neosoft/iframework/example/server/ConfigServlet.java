package by.neosoft.iframework.example.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * <h1>ConfigServlet</h1>
 * 
 * <p>
 * Do not call directly app/config. We use 'loadOnStartup' property in web.xml. <br/>
 * Use
 * </p>
 * 
 * @author vadim rylski, alex oreshkevich
 */
public class ConfigServlet extends HttpServlet {

  private static Logger       configLogger     = Logger.getLogger(ConfigServlet.class);
  /** Returns getServletContext().getContextPath() property */
  private static String       contextPath;

  /** Returns \ on Windows and / on Unix OS */
  private final static String fileSeparator    = System.getProperty("file.separator");

  /** Full path to war directory */
  private static String       realContextPath;

  private static final long   serialVersionUID = 8232303229476547470L;

  /** Full path to temp directory */
  private final static String tempDirPath      = System.getProperty("java.io.tmpdir") + fileSeparator;

  @Override
  public void init(ServletConfig config) throws ServletException {

    // save servlet contextPath
    contextPath = config.getServletContext().getContextPath();

    // calculate real contextPath to war directory
    String web_inf = config.getServletContext().getRealPath("/WEB-INF/");
    realContextPath = web_inf.substring(0, web_inf.length() - 8);
    if (!realContextPath.endsWith(fileSeparator)) {
      realContextPath += fileSeparator;
    }

    // debug info
    configLogger.debug("getServerInfo = " + config.getServletContext().getServerInfo());
    configLogger.info("Application was successfully deployed by path " + realContextPath);
  }

  public static String getFileseparator() {
    return fileSeparator;
  }

  public static String getRealContextPath() {
    return realContextPath;
  }

  public static String getTempDirPath() {
    return tempDirPath;
  }

  public static String getContextPath() {
    return contextPath;
  }

  public static void setContextPath(String contextPath) {
    ConfigServlet.contextPath = contextPath;
  }

  public static void setRealContextPath(String realContextPath) {
    ConfigServlet.realContextPath = realContextPath;
  }
}
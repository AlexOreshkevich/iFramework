package by.neosoft.iframework.example.server;

public class ConfigServer {

  private static String contextPath;
  private static String realContextPath;
  private static String tempDir;

  /**
   * Класс, содержащий настройки серверной стороны.
   * 
   * @param realContextPath
   *          - полный путь к контексту приложения;
   * @param contextPath
   *          - название контекста приложения;
   */
  public ConfigServer(String contextPath, String realContextPath, String tempDir) {
    ConfigServer.contextPath = contextPath;
    ConfigServer.realContextPath = realContextPath;
    ConfigServer.tempDir = tempDir;
  }

  /**
   * Контекст приложения.
   * 
   * @return
   */
  public static String getContextPath() {
    return contextPath;
  }

  /**
   * Полный путь к контексту приложения;
   * 
   * @return
   */
  public static String getRealContextPath() {
    return realContextPath;
  }

  /**
   * Папка к временным файлам
   * 
   * @return
   */
  public static String getTempDir() {
    return tempDir;
  }

}

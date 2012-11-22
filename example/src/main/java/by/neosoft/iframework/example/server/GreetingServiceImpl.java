package by.neosoft.iframework.example.server;

import java.io.File;

import org.apache.log4j.Logger;

import by.neosoft.exjaxb.FileSystemUtils;
import by.neosoft.iframework.example.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  private static Logger logger = Logger.getLogger(GreetingServiceImpl.class);

  @Override
  public String greetServer(String input) throws IllegalArgumentException {

    File file = new File(ConfigServlet.getRealContextPath() + "config.xml");
    logger.info("file.getAbsolutePath() = " + file.getAbsolutePath());

    String result = FileSystemUtils.readFile(file);
    logger.info("result = " + result);

    return escapeHtml(result);
  }

  /**
   * Escape an html string. Escaping data received from the client helps to prevent cross-site script
   * vulnerabilities.
   * 
   * @param html
   *          the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  }
}

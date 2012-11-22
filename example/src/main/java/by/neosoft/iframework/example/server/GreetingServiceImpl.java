package by.neosoft.iframework.example.server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import by.neosoft.exjaxb.FileSystemUtils;
import by.neosoft.iframework.example.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  @Override
  public String greetServer(String input) throws IllegalArgumentException {

    try {
      URL url = getServletContext().getResource("config.xml");
      return FileSystemUtils.readFile(new File(url.toURI()));
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return null;
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

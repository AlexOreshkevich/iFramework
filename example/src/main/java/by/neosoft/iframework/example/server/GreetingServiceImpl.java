package by.neosoft.iframework.example.server;

import java.io.File;

import javax.xml.validation.Schema;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.xml.sax.SAXException;

import by.neosoft.iframework.example.client.GreetingService;
import by.neosoft.iframework.example.shared.ConfigParser;
import by.neosoft.iframework.example.shared.config.Config;
import by.neosoft.iframework.exjaxb.AbstractJAXBParser;
import by.neosoft.iframework.exjaxb.FileSystemUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  private static Logger logger = Logger.getLogger(GreetingServiceImpl.class);

  @SuppressWarnings("deprecation")
  @Override
  public String greetServer(String input) throws IllegalArgumentException {

    // load config file
    File configFile = new File(ConfigServlet.getRealContextPath() + "config.xml");

    // loading test xml source
    String srcXml = FileSystemUtils.readFile(configFile);

    return escapeHtml(srcXml);
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

  @Override
  public Config loadConfig() throws RuntimeException {

    // parser init
    AbstractJAXBParser<Config> parser = new ConfigParser(Config.class);

    // load config file
    File configFile = new File(ConfigServlet.getRealContextPath() + "config.xml");

    // loading test xml source
    String srcXml = FileSystemUtils.readFile(configFile);

    // loading test xsd schema
    Schema srcXsd = null;
    try {
      srcXsd = parser.getSchema(new File(ConfigServlet.getRealContextPath() + "config.xsd"));
    }
    catch (SAXException e) {
      logger.log(Priority.ERROR, "", e);
    }

    // start unmarshalling
    Config result = parser.unmarshall(srcXml, srcXsd, false);

    return result;
  }
}

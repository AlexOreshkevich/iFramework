package by.neosoft.exjaxb.test;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import by.neosoft.exjaxb.ExJAXB;
import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.parser.ExJAXBParser;
import by.neosoft.exjaxb.test.config.Config;

public class HelloJAXBTest {

  static class SimpleParser implements ExJAXBParser<Config> {

    @Override
    public String getNamespace() {
      return "http://www.example.com/example";
    }

    @Override
    public HashMap<String, String> getNamespacePrefixMap() {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("http://www.example.com/example", "xsi");
      return map;
    }

    @Override
    public String getRootTagName() {
      return "config";
    }

    @Override
    public String getSchemaInstancePrefix() {
      return "xsi";
    }
  }

  private static DocumentBuilder        builder;

  private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public static Document getDocument() throws ParserConfigurationException {

    if (builder == null) {
      builder = factory.newDocumentBuilder();
    }

    return builder.newDocument();
  }

  Logger                     logger             = Logger.getLogger("HelloJAXBTest");

  private TransformerFactory transformerFactory = TransformerFactory.newInstance();

  private Schema getSchema(String name) throws NullPointerException, MalformedURLException, SAXException {
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema s = sf.newSchema(new File(System.getProperty("CONFIG_LOCATION") + "HelloJAXBTestSchema.xsd"));
    return s;
  }

  /**
   * Преобразование Node в строку
   */
  public String nodeToString(Node node) throws TransformerException {
    StringWriter sw = new StringWriter();

    // описание преобразователя
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(new DOMSource(node), new StreamResult(sw));

    return sw.toString();
  }

  @Before
  public void setUp() {
    System.setProperty("CONFIG_LOCATION", System.getProperty("user.dir") + "/src/test/resources/");
  }

  @Test
  public void test() throws Throwable {

    // загрузка тестового xml-документа
    File xmlFile = new File(System.getProperty("CONFIG_LOCATION") + "default.xml");
    String srcXml = FileSystemUtils.readFile(xmlFile);

    Assert.assertNotNull("srcXml is null", srcXml);

    // загрузка схемы XSD
    Schema srcXsd = getSchema("SimpleSchema.xsd");

    // объявление целевого класса
    Class<Config> target = Config.class;

    // параметры вызова
    boolean isValidationEnabled = false;
    boolean isSavingTempEnabled = false;

    // service init
    SimpleParser parser = new SimpleParser();
    ExJAXB<Config> service = new ExJAXB<Config>(parser);

    // тестирование демаршализации
    Config result = service.unmarshall(srcXml, srcXsd, target, isValidationEnabled, isSavingTempEnabled);

    Assert.assertEquals("this is entryPoint value", result.getEntryPoint());
    Assert.assertEquals("0.1-SNAPSHOT", result.getVersion());

    // тестирование маршализации
    Marshaller marshaller = service.getMarshallBuilder().createMarshaller(result.getClass());

    Document doc = getDocument();
    service.marshall(marshaller, result, parser.getRootTagName(), parser.getSchemaInstancePrefix(), doc);

    // FileSystemUtils.saveTempFile(nodeToString(doc), parser.getRootTagName());
  }
}

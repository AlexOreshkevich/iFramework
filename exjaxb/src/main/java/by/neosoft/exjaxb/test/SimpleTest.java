package by.neosoft.exjaxb.test;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;

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

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import by.neosoft.exjaxb.ExJAXB;
import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.parser.ExJAXBParser;
import by.neosoft.exjaxb.test.config.Cmjconfig;

/**
 * SimpleTest
 * 
 * @author alex oreshkevich
 */
public class SimpleTest {

  private static TransformerFactory transformerFactory = TransformerFactory.newInstance();

  private static Schema getSchema(String name) throws NullPointerException {

    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      URL schemaUrl = new URL(SimpleTest.class.getResource(""), name);
      Schema s = sf.newSchema(schemaUrl);
      return s;
    }
    catch (Throwable e) {
      System.err.println("No schema " + name);
    }
    return null;
  }

  private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  private static DocumentBuilder        builder;

  public static Document getDocument() throws ParserConfigurationException {

    if (builder == null) {
      builder = factory.newDocumentBuilder();
    }

    return builder.newDocument();
  }

  /**
   * Преобразование Node в строку
   */
  public static String nodeToString(Node node) throws TransformerException {
    StringWriter sw = new StringWriter();

    // описание преобразователя
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(new DOMSource(node), new StreamResult(sw));

    return sw.toString();
  }

  static class SimpleParser implements ExJAXBParser<Cmjconfig> {

    @Override
    public HashMap<String, String> getNamespacePrefixMap() {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("http://www.prognoz.ru/Adhoc", "xsi");
      return map;
    }

    @Override
    public String getNamespace() {
      return "http://www.prognoz.ru/Adhoc";
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

  public static void main(String[] args) throws Throwable {

    System.out.println("Running " + SimpleTest.class.getName());

    URL defaultUrl = new URL(SimpleTest.class.getResource(""), "default.xml");

    // загрузка тестового xml-документа
    File xmlFile = new File(defaultUrl.toURI());
    String srcXml = FileSystemUtils.readFile(xmlFile);

    if (srcXml == null) {
      throw new NullPointerException("srcXml is null");
    }

    // загрузка схемы XSD
    Schema srcXsd = getSchema("SimpleSchema.xsd");

    // объявление целевого класса
    Class<Cmjconfig> target = Cmjconfig.class;

    // параметры вызова
    boolean isValidationEnabled = false;
    boolean isSavingTempEnabled = false;

    // service init
    SimpleParser parser = new SimpleParser();
    ExJAXB<Cmjconfig> service = new ExJAXB<Cmjconfig>(parser);

    // тестирование демаршализации
    Cmjconfig result = service.unmarshall(srcXml, srcXsd, target, isValidationEnabled, isSavingTempEnabled);
    System.out.println("Cmjconfig!!!! " + result.getEntryPoint() + " " + result.getVersion());

    // тестирование маршализации
    Marshaller marshaller = service.getMarshallBuilder().createMarshaller(result.getClass());

    Document doc = getDocument();
    service.marshall(marshaller, result, parser.getRootTagName(), parser.getSchemaInstancePrefix(), doc);

    FileSystemUtils.saveTempFile(nodeToString(doc), parser.getRootTagName());
  }
}

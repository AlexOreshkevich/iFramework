package by.neosoft.exjaxb.test;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;

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
import by.neosoft.exjaxb.test.adhoc.Adhocroot;

/**
 * 
 * @author oreshkevich
 */
class MainTest {
  private static TransformerFactory transformerFactory = TransformerFactory.newInstance();

  private static Schema getSchema() throws NullPointerException {

    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      URL schemaUrl = new URL(SchemaTestParser.class.getResource(""), "SchemaTest");
      Schema s = sf.newSchema(schemaUrl);
      return s;
    }
    catch (Throwable e) {
      System.err.println("No schema..");
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

  public static void main(String[] args) throws Throwable {

    // загрузка тестового xml-документа
    File xmlFile = new File("c://adhoc//Temp//TestXML.xml");
    String srcXml = FileSystemUtils.readFile(xmlFile);

    if (srcXml == null) {
      throw new RuntimeException("pizdec");
    }

    // загрузка схемы XSD
    Schema srcXsd = getSchema();

    // объявление целевого класса
    Class<Adhocroot> target = Adhocroot.class;

    // параметры вызова
    boolean isValidationEnabled = true;
    boolean isSavingTempEnabled = false;

    // service init
    SchemaTestParser parser = new SchemaTestParser();
    ExJAXB<Adhocroot> service = new ExJAXB<Adhocroot>(parser);

    // тестирование демаршализации
    Adhocroot result = service.unmarshall(srcXml, srcXsd, target, isValidationEnabled, isSavingTempEnabled);

    // тестирование маршализации
    Marshaller marshaller = service.getMarshallBuilder().createMarshaller(result.getClass());

    Document doc = getDocument();
    service.marshall(marshaller, result, parser.getRootTagName(), parser.getSchemaInstancePrefix(), doc);

    FileSystemUtils.saveTempFile(nodeToString(doc), "test");
  }
}

/**
 * 
 */
package by.neosoft.exjaxb.test;

import java.io.File;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import by.neosoft.exjaxb.fs.FileSystemUtils;

/**
 * @author neo
 * 
 */
public class SecondSample {

  private static Schema getSchema(String name) throws NullPointerException {

    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      URL schemaUrl = new URL(SecondSample.class.getResource(""), name);
      Schema s = sf.newSchema(schemaUrl);
      return s;
    }
    catch (Throwable e) {
      System.err.println("No schema " + name);
    }
    return null;
  }

  public static void main(String[] args) throws Exception {

    System.out.println("Running " + SecondSample.class.getName());

    URL defaultUrl = new URL(SecondSample.class.getResource(""), "default.xml");

    // загрузка тестового xml-документа
    File xmlFile = new File(defaultUrl.toURI());
    String srcXml = FileSystemUtils.readFile(xmlFile);

    if (srcXml == null) {
      throw new NullPointerException("srcXml is null");
    }

    // загрузка схемы XSD
    Schema srcXsd = getSchema("SimpleSchema.xsd");

    // объявление целевого класса
    // Class<Cmjconfig> target = Cmjconfig.class;
    //
    // ExampleParser parser = new ExampleParser();
    //
    // Cmjconfig config = parser.deserialize(srcXml, srcXsd, target);
    //
    // System.out.println("RULLEZZZZ " + config.getVersion() + " " + config.getEntryPoint());

  }
}

package by.neosoft.exjaxb.test;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.test.config.Config;

public class HelloJAXBTest {

  Logger logger = Logger.getLogger("HelloJAXBTest");

  @Before
  public void setUp() {
    System.setProperty("CONFIG_LOCATION", System.getProperty("user.dir") + "/src/test/resources/");
  }

  @Test
  public void test() throws Throwable {

    // parser init
    SimpleParser parser = new SimpleParser();

    // loading test xml source
    File xmlFile = new File(System.getProperty("CONFIG_LOCATION") + "default.xml");
    String srcXml = FileSystemUtils.readFile(xmlFile);
    Assert.assertNotNull("srcXml is null", srcXml);

    // loading test xsd schema
    Schema srcXsd = parser.getSchema(new File(System.getProperty("CONFIG_LOCATION")
        + "HelloJAXBTestSchema.xsd"));

    // definition of target class
    Class<Config> target = Config.class;

    // calling arguments
    boolean isValidationEnabled = false;
    boolean isSavingTempEnabled = false;

    // start unmarshalling
    Config result = parser.unmarshall(srcXml, srcXsd, target, isValidationEnabled, isSavingTempEnabled);

    Assert.assertEquals("this is entryPoint value", result.getEntryPoint());
    Assert.assertEquals("0.1-SNAPSHOT", result.getVersion());

    // start marshalling
    Marshaller marshaller = parser.getMarshallBuilder().createMarshaller(result.getClass());

    Document doc = parser.getDocument();
    parser.marshall(marshaller, result, parser.getRootTagName(), parser.getSchemaInstancePrefix(), doc);

    // FileSystemUtils.saveTempFile(nodeToString(doc), parser.getRootTagName());
  }
}

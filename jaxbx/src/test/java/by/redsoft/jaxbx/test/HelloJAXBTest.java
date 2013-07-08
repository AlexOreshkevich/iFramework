package by.redsoft.jaxbx.test;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.validation.Schema;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import pro.redsoft.iframework.jaxbx.FileSystemUtils;

import by.redsoft.jaxbx.test.config.Config;

public class HelloJAXBTest {

  Logger logger = Logger.getLogger("HelloJAXBTest");

  @Before
  public void setUp() {
    System.setProperty("CONFIG_LOCATION", System.getProperty("user.dir") + "/src/test/resources/");
  }

  @Test
  public void test() throws Throwable {

    // parser init
    ExampleParser parser = new ExampleParser(Config.class);

    // loading test xml source
    String srcXml = FileSystemUtils.readFile(new File(System.getProperty("CONFIG_LOCATION") + "default.xml"));
    Assert.assertNotNull("srcXml is null", srcXml);

    // loading test xsd schema
    Schema srcXsd = parser.getSchema(new File(System.getProperty("CONFIG_LOCATION")
        + "HelloJAXBTestSchema.xsd"));

    // calling arguments
    boolean isSavingTempEnabled = false;

    // start unmarshalling
    Config result = parser.unmarshall(srcXml, srcXsd, isSavingTempEnabled);

    Assert.assertEquals("this is entryPoint value", result.getEntryPoint());
    Assert.assertEquals("0.1-SNAPSHOT", result.getVersion());

    // start marshalling
    Document doc = parser.marshall(result);

    // FileSystemUtils.saveTempFile(nodeToString(doc), parser.getRootTagName());
  }
}

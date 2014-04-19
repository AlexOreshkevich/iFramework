/*
The MIT License (MIT)

Copyright (c) 2013 - 2014  REDSOFT.PRO

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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

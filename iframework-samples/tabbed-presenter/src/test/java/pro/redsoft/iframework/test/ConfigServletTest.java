/**
 * Copyright 2013 REDSOFT.PRO
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.redsoft.iframework.test;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import pro.redsoft.iframework.server.AbstractConfigServlet;
import pro.redsoft.iframework.server.MockServiceImpl;
import pro.redsoft.iframework.shared.config.Config;

/**
 * ConfigServletTest.
 * 
 * @author Alex N. Oreshkevich
 */
@SuppressWarnings("serial")
public class ConfigServletTest {

  @Test
  public void testThatUserSettingsIsOptional() throws Exception {
    MockServiceImpl servlet = new MockServiceImpl() {
      @Override
      public String getExtConfigDirPath() {
        return System.getProperty("user.dir") + "/src/test/resources/test_missing_user";
      }
    };
    servlet.init();

    Config result = servlet.getClientSettings();
    Assert.assertEquals("test-missing-user", result.getSystem().getEntryPoint());
    Assert.assertEquals("INTERNAL_USER_CFG", result.getUser().getMargin());
  }

  @Test
  public void testThatOnValidationErrorClientHasMessages() throws Exception {
    MockServiceImpl servlet = new MockServiceImpl() {
      @Override
      public String getExtConfigDirPath() {
        return System.getProperty("user.dir") + "/src/test/resources/test_validation_failed_system";
      }
    };
    servlet.init();

    for (int i = 0; i < 3; i++) {
      doClientSettingsTest(servlet);
    }
  }

  private final void doClientSettingsTest(AbstractConfigServlet servlet) throws Exception {
    Config result = servlet.getClientSettings();
    Assert.assertNotNull(result.getLogMessage());
    Assert.assertEquals("INTERNAL_SYSTEM_CFG", result.getSystem().getEntryPoint());
    Assert.assertEquals("INTERNAL_USER_CFG", result.getUser().getMargin());
  }

  @Test
  public void testConfigDirProperty() throws ServletException {

    MockServiceImpl servlet = null;

    // configDirProperty cann't be null
    Assert.assertNotNull(new MockServiceImpl().getConfigDirProperty());

    // extConfigDirPath cann't be null
    servlet = new MockServiceImpl() {
      @Override
      public String getExtConfigDirPath() {
        return null;
      }
    };
    servlet.init();
    Assert.assertTrue(servlet.getLogMessage().length() > 0);

    // extConfigDirPath cann't be empty
    servlet = new MockServiceImpl() {
      @Override
      public String getExtConfigDirPath() {
        return "";
      }
    };
    servlet.init();
    Assert.assertTrue(servlet.getLogMessage().length() > 0);

    // extConfigDirPath cann't be directory
    servlet = new MockServiceImpl() {
      @Override
      public String getExtConfigDirPath() {
        return System.getProperty("user.dir") + FSEP + "pom.xml";
      }
    };
    servlet.init();
    Assert.assertTrue(servlet.getLogMessage().length() > 0);
  }
}

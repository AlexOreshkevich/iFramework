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
package by.redsoft.iframework.client;

import org.junit.Ignore;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * SandboxGwtTest.
 * 
 * @author Alex N. Oreshkevich
 */
@Ignore
public class SandboxGwtTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "pro.redsoft.iframework.Project";
  }

  public void testSandbox() {
    assertTrue(true);
  }
}
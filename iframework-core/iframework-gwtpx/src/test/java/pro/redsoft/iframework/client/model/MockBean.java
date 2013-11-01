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
package pro.redsoft.iframework.client.model;

/**
 * MockBean.
 * 
 * @author Alex N. Oreshkevich
 */
public class MockBean {

  private String name;
  private Object prop;
  private String telephone;

  public MockBean(String name, Object prop, String telephone) {
    this.name = name;
    this.prop = prop;
    this.telephone = telephone;
  }

  public MockBean() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getProp() {
    return prop;
  }

  public void setProp(Object prop) {
    this.prop = prop;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

}

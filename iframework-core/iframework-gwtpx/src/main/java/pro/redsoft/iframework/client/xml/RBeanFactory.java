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
package pro.redsoft.iframework.client.xml;

/**
 * RBeanFactory.
 * 
 * @author alex oreshkevich
 */
@StandardClassDescriptor(
    author = "Neo",
    date = "12/10/10",
    currentRevision = 3,
    reviewers = { "" },
    lastModified = "26/11/10",
    lastModifiedBy = "Neo")
public class RBeanFactory extends RClass {

  RBeanFactory() {
  }

  /**
   * Gets RBean Class By Name (String).
   * 
   * @param name
   * @return
   */
  public RBean getClassByName(String name) {
    RBean rb = EgrFactory.get().getEgrMap().getBeanByName(name.toUpperCase());
    rb.clearRFields();
    return rb;
  }

  /**
   * Register some bean.
   * 
   * @param bean
   */
  void registerBean(RBean bean) {
    EgrFactory.get().getEgrMap().put(null, bean, null, "");
  }
}

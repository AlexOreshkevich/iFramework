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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Top-level class in our Reflection on client side.
 * <p>
 * Defines class name as String value with access methods.
 * </p>
 * 
 * @author alex oreshkevich
 */
@StandardClassDescriptor(
    author = "Neo",
    date = "12/10/10",
    currentRevision = 1,
    reviewers = { "" },
    lastModified = "",
    lastModifiedBy = "")
public class RClass implements IsSerializable {

  /** String representation of class name. */
  String rclassName;

  /**
   * Returns the fully-qualified name for this class, for example: by.ipps.egr.client.core2.RClass.
   * <p>
   * Equals Class.getName() in Reflection
   * 
   * @return full name for this class
   */
  public String getName() {
    String sn = getClass().toString();
    return sn.substring(0, sn.indexOf(" ")).equals("class") ? sn.substring(sn.indexOf(" ") + 1) : null;
  }

  /**
   * Returns the simple name of the underlying class as given in the source code. Returns an empty string if the
   * underlying class is anonymous.
   * <p>
   * Difference from the Class.getSimpleName() is that our bean classes can't be an arrays, then we skip isArray() code
   * section.
   * </p>
   * 
   * @return the simple name of the underlying class
   */
  public String getSimpleName() {
    return getName().substring(getName().lastIndexOf(".") + 1);
  }

  /**
   * @param rclassName
   *          the rclassName to set
   */
  public void setRclassName(String rclassName) {
    this.rclassName = rclassName;
  }
}

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
package pro.redsoft.iframework.client.xml;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Top-level class in our Reflection on client side.
 * <p>
 * Defines class name as String value with access methods.
 * </p>
 *
 * @author alex oreshkevich
 * @version $Id: $Id
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
   * <p>Setter for the field <code>rclassName</code>.</p>
   *
   * @param rclassName
   *          the rclassName to set
   */
  public void setRclassName(String rclassName) {
    this.rclassName = rclassName;
  }
}

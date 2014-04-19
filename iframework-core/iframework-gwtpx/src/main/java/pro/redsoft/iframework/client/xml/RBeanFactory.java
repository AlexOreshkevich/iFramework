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

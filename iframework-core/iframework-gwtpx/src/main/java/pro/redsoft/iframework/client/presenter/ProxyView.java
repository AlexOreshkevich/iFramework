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
package pro.redsoft.iframework.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Tabbed view that based on proxy-tabs. Defines Proxy pattern implementation interface for tabbed view.
 * 
 * @author Alex N. Oreshkevich
 */
public interface ProxyView {

  /**
   * Checks whenever tab by specified index is proxy or not.
   * 
   * @param tabInd
   *          tab index
   * @return true if tab is proxy
   * @throws IndexOutOfBoundsException
   *           if the index is out of range
   */
  boolean isProxy(int tabInd) throws IndexOutOfBoundsException;

  /**
   * Replaces proxy specified by tabInd with some widget.
   * 
   * @param tabInd
   *          tab index
   * @param w
   *          real tab as widget
   * @throws IndexOutOfBoundsException
   *           if the index is out of range
   */
  void replaceProxy(int tabInd, IsWidget w) throws IndexOutOfBoundsException;
}

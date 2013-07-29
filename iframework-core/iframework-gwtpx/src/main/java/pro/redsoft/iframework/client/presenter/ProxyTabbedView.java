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
package pro.redsoft.iframework.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Tabbed view that based on proxy-tabs. Defines Proxy pattern implementation interface for tabbed view.
 * 
 * @author Alex N. Oreshkevich
 */
public interface ProxyTabbedView {

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

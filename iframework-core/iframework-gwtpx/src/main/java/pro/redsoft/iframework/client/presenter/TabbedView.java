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

import java.util.Map;

import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.AnimatedLayout;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.HandlerContainer;
import com.gwtplatform.mvp.client.View;

/**
 * TabbedView. <br/>
 * The tabbed presenter's view.
 * 
 * @author Alex N. Oreshkevich
 */
public interface TabbedView extends View, HandlerContainer, HasWidgets, ProvidesResize,
    IndexedPanel.ForIsWidget, AnimatedLayout, HasBeforeSelectionHandlers<Integer>,
    HasSelectionHandlers<Integer>, ProxyTabbedView {

  void add(Widget widget, String string);

  void init(int defaultSelectedInd, Map<Integer, String> tabNamesMap);

  /**
   * Inserts a widget into the panel. If the Widget is already attached, it will be moved to the requested index.
   * 
   * @param child
   *          the widget to be added
   * @param text
   *          the text to be shown on its tab
   * @param beforeIndex
   *          the index before which it will be inserted
   */
  void insert(Widget child, String text, int beforeIndex);

  void selectTab(int ind);

  void selectTab(int ind, boolean fireEvents);
}

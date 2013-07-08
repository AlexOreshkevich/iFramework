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
package pro.redsoft.iframework.client.application.render;

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
    HasSelectionHandlers<Integer> {

  void add(Widget widget, String string);

  void init(int defaultSelectedInd, Map<Integer, String> tabNamesMap);

  void insert(Widget child, String text, int beforeIndex);

  boolean isProxy(int tabInd);

  void replaceProxy(int tabInd, Widget w);

  void selectTab(int ind);

  void selectTab(int ind, boolean fireEvents);
}

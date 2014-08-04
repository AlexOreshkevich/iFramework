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
 * @version $Id: $Id
 */
public interface TabbedView extends View, HandlerContainer, HasWidgets, ProvidesResize,
    IndexedPanel.ForIsWidget, AnimatedLayout, HasBeforeSelectionHandlers<Integer>,
    HasSelectionHandlers<Integer>, ProxyView {

  /**
   * <p>add.</p>
   *
   * @param widget a {@link com.google.gwt.user.client.ui.Widget} object.
   * @param string a {@link java.lang.String} object.
   */
  void add(Widget widget, String string);

  /**
   * <p>init.</p>
   *
   * @param defaultSelectedInd a int.
   * @param tabNamesMap a {@link java.util.Map} object.
   */
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

  /**
   * <p>selectTab.</p>
   *
   * @param ind a int.
   */
  void selectTab(int ind);

  /**
   * <p>selectTab.</p>
   *
   * @param ind a int.
   * @param fireEvents a boolean.
   */
  void selectTab(int ind, boolean fireEvents);
}

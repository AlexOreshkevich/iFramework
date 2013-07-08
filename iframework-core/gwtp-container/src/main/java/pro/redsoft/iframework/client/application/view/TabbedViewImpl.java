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
package pro.redsoft.iframework.client.application.view;

import java.util.Iterator;
import java.util.Map;

import pro.redsoft.iframework.client.application.render.TabbedView;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.layout.client.Layout.AnimationCallback;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.HandlerContainerImpl;

/**
 * TabbedViewImpl.
 * 
 * @author Alex N. Oreshkevich
 */
public abstract class TabbedViewImpl extends HandlerContainerImpl implements TabbedView {

  protected TabLayoutPanel tabPanel;

  public TabbedViewImpl() {
    tabPanel = new TabLayoutPanel(25, Unit.PX);
    tabPanel.setAnimationVertical(true);
    tabPanel.setWidth("400px");
    tabPanel.setHeight("600px");
  }

  @Override
  public void add(Widget w) {
    tabPanel.add(w);
  }

  @Override
  public void add(Widget w, String text) {
    tabPanel.add(w, text);
  }

  @Override
  public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
    return tabPanel.addBeforeSelectionHandler(handler);
  }

  @Override
  public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
    return tabPanel.addSelectionHandler(handler);
  }

  @Override
  public void addToSlot(Object slot, IsWidget content) {
  }

  @Override
  public void animate(int duration) {
    tabPanel.animate(duration);
  }

  @Override
  public void animate(int duration, AnimationCallback callback) {
    tabPanel.animate(duration, callback);
  }

  @Override
  public Widget asWidget() {
    return tabPanel;
  }

  private Widget buildProxyContainer() {
    FlowPanel fp = new FlowPanel();
    return fp;
  }

  @Override
  public void clear() {
    tabPanel.clear();
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    tabPanel.fireEvent(event);
  }

  @Override
  public void forceLayout() {
    tabPanel.forceLayout();
  }

  @Override
  public Widget getWidget(int index) {
    return tabPanel.getWidget(index);
  }

  @Override
  public int getWidgetCount() {
    return tabPanel.getWidgetCount();
  }

  @Override
  public int getWidgetIndex(IsWidget child) {
    return tabPanel.getWidgetIndex(child);
  }

  @Override
  public int getWidgetIndex(Widget child) {
    return tabPanel.getWidgetIndex(child);
  }

  @Override
  public void init(int defaultSelectedInd, Map<Integer, String> tabNamesMap) {

    for (Integer ind : tabNamesMap.keySet()) {
      add(buildProxyContainer(), tabNamesMap.get(ind));
    }

    selectTab(defaultSelectedInd, false);
  }

  @Override
  public void insert(Widget child, String text, int beforeIndex) {
    tabPanel.insert(child, text, beforeIndex);
  }

  @Override
  public boolean isProxy(int tabInd) {
    return ((ComplexPanel) tabPanel.getWidget(tabInd)).getWidgetCount() == 0;
  }

  @Override
  public Iterator<Widget> iterator() {
    return tabPanel.iterator();
  }

  @Override
  public boolean remove(int index) {
    return tabPanel.remove(index);
  }

  @Override
  public boolean remove(Widget w) {
    return tabPanel.remove(w);
  }

  @Override
  public void removeFromSlot(Object slot, IsWidget content) {
  }

  @Override
  public void replaceProxy(int tabInd, Widget w) {
    Widget proxyContainer = tabPanel.getWidget(tabInd);
    assert proxyContainer != null;
    try {
      ComplexPanel proxyPanel = (ComplexPanel) proxyContainer;
      proxyPanel.add(w);
    }
    catch (ClassCastException e) {
      throw new RuntimeException("replaceProxy() cast error", e);
    }
  }

  @Override
  public void selectTab(int ind) {
    tabPanel.selectTab(ind);
  }

  @Override
  public void selectTab(int ind, boolean fireEvents) {
    tabPanel.selectTab(ind, fireEvents);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
  }
}

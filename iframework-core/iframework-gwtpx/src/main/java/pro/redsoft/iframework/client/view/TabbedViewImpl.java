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
package pro.redsoft.iframework.client.view;

import java.util.Iterator;
import java.util.Map;

import pro.redsoft.iframework.client.presenter.TabbedView;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.layout.client.Layout.AnimationCallback;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.HandlerContainerImpl;

/**
 * TabbedViewImpl.
 * 
 * @author Alex N. Oreshkevich
 */
public abstract class TabbedViewImpl extends HandlerContainerImpl implements TabbedView {

  private TabPanelEx tabPanel;

  public TabbedViewImpl() {
    buildTabPanel();
  }

  @Override
  public void add(Widget w) {
    getTabPanel().add(w);
  }

  @Override
  public void add(Widget w, String text) {
    tabPanel.add(w, text);
  }

  @Override
  public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
    return getTabPanel().addBeforeSelectionHandler(handler);
  }

  @Override
  public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
    return getTabPanel().addSelectionHandler(handler);
  }

  @Override
  public void addToSlot(Object slot, IsWidget content) {
  }

  @Override
  public void animate(int duration) {
    getTabPanel().animate(duration);
  }

  @Override
  public void animate(int duration, AnimationCallback callback) {
    getTabPanel().animate(duration, callback);
  }

  @Override
  public Widget asWidget() {
    return getTabPanel().asWidget();
  }

  private Widget buildProxyContainer() {
    FlowPanel fp = new FlowPanel();
    return fp;
  }

  protected void buildTabPanel() {
    tabPanel = new TabPanelEx(25, Unit.PX);
    tabPanel.setAnimationVertical(true);
    tabPanel.setWidth("400px");
    tabPanel.setHeight("600px");
  }

  @Override
  public void clear() {
    getTabPanel().clear();
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    getTabPanel().fireEvent(event);
  }

  @Override
  public void forceLayout() {
    getTabPanel().forceLayout();
  }

  protected TabPanel getTabPanel() {
    return tabPanel;
  }

  @Override
  public Widget getWidget(int index) {
    return getTabPanel().getWidget(index);
  }

  @Override
  public int getWidgetCount() {
    return getTabPanel().getWidgetCount();
  }

  @Override
  public int getWidgetIndex(IsWidget child) {
    return getTabPanel().getWidgetIndex(child);
  }

  @Override
  public int getWidgetIndex(Widget child) {
    return getTabPanel().getWidgetIndex(child);
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
  public final boolean isProxy(int tabInd) throws IndexOutOfBoundsException {
    return ((ComplexPanel) getTabPanel().getWidget(tabInd)).getWidgetCount() == 0;
  }

  @Override
  public Iterator<Widget> iterator() {
    return getTabPanel().iterator();
  }

  @Override
  public boolean remove(int index) {
    return getTabPanel().remove(index);
  }

  @Override
  public boolean remove(Widget w) {
    return getTabPanel().remove(w);
  }

  @Override
  public void removeFromSlot(Object slot, IsWidget content) {
  }

  @Override
  public void replaceProxy(int tabInd, IsWidget w) throws IndexOutOfBoundsException {
    ((ComplexPanel) getTabPanel().getWidget(tabInd)).add(w);
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

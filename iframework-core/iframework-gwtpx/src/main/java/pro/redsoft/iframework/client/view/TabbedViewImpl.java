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
 * @version $Id: $Id
 */
public abstract class TabbedViewImpl extends HandlerContainerImpl implements TabbedView {

  private TabPanelEx tabPanel;

  /**
   * <p>Constructor for TabbedViewImpl.</p>
   */
  public TabbedViewImpl() {
    buildTabPanel();
  }

  /** {@inheritDoc} */
  @Override
  public void add(Widget w) {
    getTabPanel().add(w);
  }

  /** {@inheritDoc} */
  @Override
  public void add(Widget w, String text) {
    tabPanel.add(w, text);
  }

  /** {@inheritDoc} */
  @Override
  public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
    return getTabPanel().addBeforeSelectionHandler(handler);
  }

  /** {@inheritDoc} */
  @Override
  public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
    return getTabPanel().addSelectionHandler(handler);
  }

  /** {@inheritDoc} */
  @Override
  public void addToSlot(Object slot, IsWidget content) {
  }

  /** {@inheritDoc} */
  @Override
  public void animate(int duration) {
    getTabPanel().animate(duration);
  }

  /** {@inheritDoc} */
  @Override
  public void animate(int duration, AnimationCallback callback) {
    getTabPanel().animate(duration, callback);
  }

  /** {@inheritDoc} */
  @Override
  public Widget asWidget() {
    return getTabPanel().asWidget();
  }

  private Widget buildProxyContainer() {
    FlowPanel fp = new FlowPanel();
    return fp;
  }

  /**
   * <p>buildTabPanel.</p>
   */
  protected void buildTabPanel() {
    tabPanel = new TabPanelEx(25, Unit.PX);
    tabPanel.setAnimationVertical(true);
    tabPanel.setWidth("400px");
    tabPanel.setHeight("200px");
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    getTabPanel().clear();
  }

  /** {@inheritDoc} */
  @Override
  public void fireEvent(GwtEvent<?> event) {
    getTabPanel().fireEvent(event);
  }

  /** {@inheritDoc} */
  @Override
  public void forceLayout() {
    getTabPanel().forceLayout();
  }

  /**
   * <p>Getter for the field <code>tabPanel</code>.</p>
   *
   * @return a {@link pro.redsoft.iframework.client.view.TabPanel} object.
   */
  protected TabPanel getTabPanel() {
    return tabPanel;
  }

  /** {@inheritDoc} */
  @Override
  public Widget getWidget(int index) {
    return getTabPanel().getWidget(index);
  }

  /** {@inheritDoc} */
  @Override
  public int getWidgetCount() {
    return getTabPanel().getWidgetCount();
  }

  /** {@inheritDoc} */
  @Override
  public int getWidgetIndex(IsWidget child) {
    return getTabPanel().getWidgetIndex(child);
  }

  /** {@inheritDoc} */
  @Override
  public int getWidgetIndex(Widget child) {
    return getTabPanel().getWidgetIndex(child);
  }

  /** {@inheritDoc} */
  @Override
  public void init(int defaultSelectedInd, Map<Integer, String> tabNamesMap) {

    for (Integer ind : tabNamesMap.keySet()) {
      add(buildProxyContainer(), tabNamesMap.get(ind));
    }

    selectTab(defaultSelectedInd, false);
  }

  /** {@inheritDoc} */
  @Override
  public void insert(Widget child, String text, int beforeIndex) {
    tabPanel.insert(child, text, beforeIndex);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isProxy(int tabInd) throws IndexOutOfBoundsException {
    return ((ComplexPanel) getTabPanel().getWidget(tabInd)).getWidgetCount() == 0;
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<Widget> iterator() {
    return getTabPanel().iterator();
  }

  /** {@inheritDoc} */
  @Override
  public boolean remove(int index) {
    return getTabPanel().remove(index);
  }

  /** {@inheritDoc} */
  @Override
  public boolean remove(Widget w) {
    return getTabPanel().remove(w);
  }

  /** {@inheritDoc} */
  @Override
  public void removeFromSlot(Object slot, IsWidget content) {
  }

  /** {@inheritDoc} */
  @Override
  public void replaceProxy(int tabInd, IsWidget w) throws IndexOutOfBoundsException {
    Widget child = getTabPanel().getWidget(tabInd);
    assert child instanceof ComplexPanel : "proxyContainer should be instance of ComplexPanel";
    ComplexPanel proxyContainer = (ComplexPanel) child;
    proxyContainer.clear();
    proxyContainer.add(w.asWidget());
  }

  /** {@inheritDoc} */
  @Override
  public void selectTab(int ind) {
    tabPanel.selectTab(ind, true);
  }

  /** {@inheritDoc} */
  @Override
  public void selectTab(int ind, boolean fireEvents) {
    tabPanel.selectTab(ind, fireEvents);
  }

  /** {@inheritDoc} */
  @Override
  public void setInSlot(Object slot, IsWidget content) {
  }
}

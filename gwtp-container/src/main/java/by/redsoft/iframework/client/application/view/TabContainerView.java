/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.view;

import by.redsoft.iframework.client.application.render.TabbedProxyView;

import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.user.client.ui.AnimatedLayout;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.View;

/**
 * TabContainerView
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public interface TabContainerView extends View, TabbedProxyView, HasWidgets, ProvidesResize,
    IndexedPanel.ForIsWidget, AnimatedLayout, HasBeforeSelectionHandlers<Integer>,
    HasSelectionHandlers<Integer> {

  void add(Widget widget, String string);

  void insert(Widget child, String text, int beforeIndex);

  void selectTab(int ind);

  void selectTab(int ind, boolean fireEvents);
}

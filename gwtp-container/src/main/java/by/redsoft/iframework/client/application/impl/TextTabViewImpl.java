/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.impl;

import by.redsoft.iframework.client.application.render.AbstractTabViewImpl;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TextTabViewImpl
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class TextTabViewImpl extends AbstractTabViewImpl implements TextTabView {

  private VerticalPanel widget;

  TextTabViewImpl() {
    widget = new VerticalPanel();
    for (int i = 0; i < 1000; i++) {
      widget.add(new HTML("<p><b>" + i + "</b>This is TextTabViewImpl</p>"));
    }
  }

  @Override
  public Widget asWidget() {
    return widget;
  }
}

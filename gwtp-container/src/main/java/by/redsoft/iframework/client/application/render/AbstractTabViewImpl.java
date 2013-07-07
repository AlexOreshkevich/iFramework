/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.mvp.client.HandlerContainerImpl;

/**
 * AbstractTabViewImpl.java
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public abstract class AbstractTabViewImpl extends HandlerContainerImpl implements AbstractTabView {

  protected AbstractTabViewImpl() {
    super(false); // No autobinding, the presenter will bind us.
    Window.alert("Create VIEW for " + getClass().getName());
  }

  @Override
  public void addToSlot(Object slot, IsWidget content) {
  }

  @Override
  public void removeFromSlot(Object slot, IsWidget content) {
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
  }

}

/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * AbstractTabPresenter
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public abstract class AbstractTabPresenter<V extends AbstractTabView> extends PresenterWidget<V> {

  protected AbstractTabPresenter(final EventBus eventBus, final V view) {
    super(false, eventBus, view);
  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().bind();
  }

  @Override
  protected void onUnbind() {
    super.onUnbind();
    getView().unbind();
  }
}

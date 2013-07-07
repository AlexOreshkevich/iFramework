/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * AbstractTabFactoryImpl
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public abstract class AbstractTabFactoryImpl<V extends AbstractTabView, P extends AbstractTabPresenter<V>, F extends AbstractTabViewFactory<V>>
    implements AbstractTabFactory<V, P> {

  protected final EventBus eventBus;
  protected final F        viewFactory;

  @Inject
  protected AbstractTabFactoryImpl(EventBus eventBus, F viewFactory) {
    this.eventBus = eventBus;
    this.viewFactory = viewFactory;
  }
}

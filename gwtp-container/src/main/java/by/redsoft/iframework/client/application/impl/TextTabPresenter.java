/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.impl;

import by.redsoft.iframework.client.application.render.AbstractTabFactoryImpl;
import by.redsoft.iframework.client.application.render.AbstractTabPresenter;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TextTabPresenter
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class TextTabPresenter extends AbstractTabPresenter<TextTabView> {

  public static class TextTabFactoryImpl extends
      AbstractTabFactoryImpl<TextTabView, TextTabPresenter, TextTabViewFactory> {

    @Inject
    protected TextTabFactoryImpl(EventBus eventBus, TextTabViewFactory viewFactory) {
      super(eventBus, viewFactory);
    }

    @Override
    public TextTabPresenter create() {
      return new TextTabPresenter(eventBus, viewFactory.create());
    }
  }

  private TextTabPresenter(EventBus eventBus, TextTabView view) {
    super(eventBus, view);
  }

}

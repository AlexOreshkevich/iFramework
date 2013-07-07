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
 * ImageTabPresenter
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class ImageTabPresenter extends AbstractTabPresenter<ImageTabView> {

  public static class ImageTabFactoryImpl extends
      AbstractTabFactoryImpl<ImageTabView, ImageTabPresenter, ImageTabViewFactory> {

    @Inject
    protected ImageTabFactoryImpl(EventBus eventBus, ImageTabViewFactory viewFactory) {
      super(eventBus, viewFactory);
    }

    @Override
    public ImageTabPresenter create() {
      return new ImageTabPresenter(eventBus, viewFactory.create());
    }
  }

  private ImageTabPresenter(EventBus eventBus, ImageTabView view) {
    super(eventBus, view);
  }

}

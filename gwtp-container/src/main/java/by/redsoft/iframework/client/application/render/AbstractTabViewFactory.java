/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

/**
 * This factory is used to create new views for the {@link AbstractTabPresenter}.
 */
public interface AbstractTabViewFactory<V extends AbstractTabView> {

  /**
   * Create a new view for a {@link AbstractTabPresenter}.
   * 
   * @return The newly created view for this {@link AbstractTabPresenter}.
   */
  V create();
}
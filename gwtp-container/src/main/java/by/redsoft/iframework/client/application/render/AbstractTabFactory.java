/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

/**
 * Use this factory to create {@link AbstractTabPresenter} objects.
 * 
 * @author Alex N. Oreshkevich
 */
public interface AbstractTabFactory<V extends AbstractTabView, P extends AbstractTabPresenter<V>> {

  /**
   * Create a new {@link AbstractTabPresenter}.
   * 
   * @return The newly created {@link AbstractTabPresenter}.
   */
  P create();
}
/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.impl;

import by.redsoft.iframework.client.application.render.AbstractTabViewFactory;

/**
 * TextTabViewFactory
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class TextTabViewFactory implements AbstractTabViewFactory<TextTabView> {

  @Override
  public TextTabView create() {
    return new TextTabViewImpl();
  }
}

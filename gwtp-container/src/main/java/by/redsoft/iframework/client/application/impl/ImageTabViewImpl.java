/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.impl;

import by.redsoft.iframework.client.application.render.AbstractTabViewImpl;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * ImageTabViewImpl.java
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class ImageTabViewImpl extends AbstractTabViewImpl implements ImageTabView {

  private Image image;

  ImageTabViewImpl() {
    image = new Image("http://www.tutorialspoint.com/images/gwt-mini.png");
  }

  @Override
  public Widget asWidget() {
    return image;
  }
}

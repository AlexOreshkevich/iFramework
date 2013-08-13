/**
 * Copyright 2013 REDSOFT.PRO
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.redsoft.iframework.client.application.impl;

import pro.redsoft.iframework.client.view.AbstractComponentView;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * ImageTabViewImpl.
 * 
 * @author Alex N. Oreshkevich
 */
public class ImageTabViewImpl extends AbstractComponentView implements ImageTabView {

  private Image image;

  ImageTabViewImpl() {
    image = new Image("http://www.tutorialspoint.com/images/gwt-mini.png");
  }

  @Override
  public Widget asWidget() {
    return image;
  }
}

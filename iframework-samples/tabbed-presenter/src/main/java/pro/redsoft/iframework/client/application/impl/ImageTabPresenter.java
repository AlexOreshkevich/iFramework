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

import pro.redsoft.iframework.client.factory.AbstractComponentPresenterFactory;
import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * ImageTabPresenter.
 * 
 * @author Alex N. Oreshkevich
 */
public class ImageTabPresenter extends ComponentPresenterWidget<ImageTabView> {

  /**
   * ImageTabFactoryImpl.
   */
  public static class ImageTabFactoryImpl extends
      AbstractComponentPresenterFactory<ImageTabView, ImageTabPresenter, ImageTabViewFactory> {

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

/**
 * Copyright 2012 ArcBees Inc.
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

package by.redsoft.iframework.client.application;

import by.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import by.redsoft.iframework.client.application.impl.ImageTabViewFactory;
import by.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;
import by.redsoft.iframework.client.application.impl.TextTabViewFactory;
import by.redsoft.iframework.client.application.render.AbstractTabFactoryLoader;
import by.redsoft.iframework.client.application.view.ViewModule;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {

    install(new ViewModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);

    // bindPresenterWidgetFactory
    // bind(AbstractTabFactory.class).to(AbstractTabFactoryImpl.class).in(Singleton.class);
    // bind(AbstractTabViewFactory.class).to(AbstractTabViewImpl.FactoryImpl.class).in(Singleton.class);
    bind(ImageTabFactoryImpl.class).in(Singleton.class);
    bind(ImageTabViewFactory.class).in(Singleton.class);

    bind(TextTabFactoryImpl.class).in(Singleton.class);
    bind(TextTabViewFactory.class).in(Singleton.class);

    bind(AbstractTabFactoryLoader.class);
  }
}

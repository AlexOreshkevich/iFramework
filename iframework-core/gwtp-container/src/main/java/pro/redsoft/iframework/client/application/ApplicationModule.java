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
package pro.redsoft.iframework.client.application;

import pro.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.ImageTabViewFactory;
import pro.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.TextTabViewFactory;
import pro.redsoft.iframework.client.application.view.ViewModule;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * ApplicationModule.
 * 
 * @author Alex N. Oreshkevich
 */
public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {

    install(new ViewModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);

    bind(FactoryLoader.class).in(Singleton.class);

    bind(ImageTabFactoryImpl.class).in(Singleton.class);
    bind(ImageTabViewFactory.class).in(Singleton.class);

    bind(TextTabFactoryImpl.class).in(Singleton.class);
    bind(TextTabViewFactory.class).in(Singleton.class);
  }
}

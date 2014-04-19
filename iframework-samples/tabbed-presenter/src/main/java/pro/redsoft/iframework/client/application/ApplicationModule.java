/*
The MIT License (MIT)

Copyright (c) 2013 - 2014  REDSOFT.PRO

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package pro.redsoft.iframework.client.application;

import pro.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.ImageTabViewFactory;
import pro.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.TextTabViewFactory;
import pro.redsoft.iframework.client.provider.ComponentLoader;

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

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
        ApplicationView.class, ApplicationPresenter.MyProxy.class);

    bind(ComponentLoader.class).to(FactoryLoaderImpl.class).in(Singleton.class);

    bind(ImageTabFactoryImpl.class).in(Singleton.class);
    bind(ImageTabViewFactory.class).in(Singleton.class);

    bind(TextTabFactoryImpl.class).in(Singleton.class);
    bind(TextTabViewFactory.class).in(Singleton.class);
  }
}

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
package pro.redsoft.iframework.client.application.impl;

import pro.redsoft.iframework.client.factory.AbstractComponentPresenterFactory;
import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TextTabPresenter.
 * 
 * @author Alex N. Oreshkevich
 */
public class TextTabPresenter extends ComponentPresenterWidget<TextTabView> {

  /**
   * TextTabFactoryImpl.
   */
  public static class TextTabFactoryImpl extends
      AbstractComponentPresenterFactory<TextTabView, TextTabPresenter, TextTabViewFactory> {

    @Inject
    protected TextTabFactoryImpl(EventBus eventBus, TextTabViewFactory viewFactory) {
      super(eventBus, viewFactory);
    }

    @Override
    public TextTabPresenter create() {
      return new TextTabPresenter(eventBus, viewFactory.create());
    }
  }

  private TextTabPresenter(EventBus eventBus, TextTabView view) {
    super(eventBus, view);
  }

}

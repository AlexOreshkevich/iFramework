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
package pro.redsoft.iframework.client.factory;

import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;
import pro.redsoft.iframework.client.view.ComponentView;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * Base class for all factories of {@link PresenterWidget}.
 * 
 * @author Alex N. Oreshkevich
 * @param <V> ComponentView
 * @param <P> ComponentPresenterWidget
 * @param <F> ComponentViewFactory
 */
public abstract class AbstractComponentPresenterFactory<V extends ComponentView, P extends ComponentPresenterWidget<V>, F extends ComponentViewFactory<V>>
    implements ComponentPresenterFactory<V, P> {

  protected final EventBus eventBus;
  protected final F        viewFactory;

  @Inject
  protected AbstractComponentPresenterFactory(EventBus eventBus, F viewFactory) {
    this.eventBus = eventBus;
    this.viewFactory = viewFactory;
  }
}

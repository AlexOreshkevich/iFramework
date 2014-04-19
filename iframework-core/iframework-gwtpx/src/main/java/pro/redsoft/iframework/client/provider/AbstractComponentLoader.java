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
package pro.redsoft.iframework.client.provider;

import java.util.HashMap;
import java.util.Map;

import pro.redsoft.iframework.client.factory.ComponentPresenterFactory;
import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;
import pro.redsoft.iframework.client.view.ComponentView;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * AbstractComponentLoader.
 * 
 * @author Alex N. Oreshkevich
 * @param <V>
 *          view
 * @param <P>
 *          presenter
 * @param <F>
 *          factory
 */
public abstract class AbstractComponentLoader<V extends ComponentView, P extends ComponentPresenterWidget<V>, F extends ComponentPresenterFactory<V, P>>
    implements ComponentLoader<V, P, F> {

  protected final Map<Object, AsyncProvider<F>> map;

  public AbstractComponentLoader() {
    map = new HashMap<Object, AsyncProvider<F>>();
  }

  @Override
  public AsyncProvider<F> register(Object type, AsyncProvider<F> provider) {
    return map.put(type, provider);
  }

  @Override
  public void get(Object type, final AsyncCallback<F> callback) {

    if (callback == null) {
      throw new IllegalArgumentException("Callback cann't be null.");
    }

    AsyncProvider<F> provider = map.get(type);
    if (null == provider) {
      callback.onFailure(new UnsupportedOperationException("Type " + type
          + " doesn't supported by ComponentLoader."));
    }
    else {
      provider
          .get(new AsyncCallback<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>() {

            @Override
            public void onFailure(Throwable caught) {
              callback.onFailure(caught);
            }

            @SuppressWarnings("unchecked")
            @Override
            public
                void
                onSuccess(
                    ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>> result) {
              callback.onSuccess((F) result);
            }
          });
    }
  }
}

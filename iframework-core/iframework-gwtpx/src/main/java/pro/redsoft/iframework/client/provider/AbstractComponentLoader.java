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
 */
public abstract class AbstractComponentLoader<V extends ComponentView, P extends ComponentPresenterWidget<V>, F extends ComponentPresenterFactory<V, P>>
    implements ComponentLoader<V, P, F> {

  protected final Map<Object, AsyncProvider<F>> map;

  public AbstractComponentLoader() {
    map = new HashMap<>();
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

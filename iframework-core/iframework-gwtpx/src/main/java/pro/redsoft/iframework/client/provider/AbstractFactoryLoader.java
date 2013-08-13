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
 * AbstractFactoryLoader.
 * 
 * @author Alex N. Oreshkevich
 */
public abstract class AbstractFactoryLoader implements FactoryLoader {

  protected final Map<Object, AsyncProvider<? extends ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>> map;

  public AbstractFactoryLoader() {
    map = new HashMap<Object, AsyncProvider<? extends ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>>();
  }

  @Override
  public
      void
      get(Object tabType,
          final AsyncCallback<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>> callback) {

    if (callback == null) {
      throw new IllegalArgumentException("Callback cann't be null.");
    }

    @SuppressWarnings("unchecked")
    AsyncProvider<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>> provider = (AsyncProvider<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>) map
        .get(tabType);

    if (provider != null) {
      provider
          .get(new AsyncCallback<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>() {

            @Override
            public void onFailure(Throwable caught) {
              callback.onFailure(caught);
            }

            @Override
            public
                void
                onSuccess(
                    ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>> result) {
              callback.onSuccess(result);
            }
          });
    }
    else {
      callback.onFailure(new UnsupportedOperationException("Tab type " + tabType
          + " doesn't supported by factory loader."));
    }
  }
}

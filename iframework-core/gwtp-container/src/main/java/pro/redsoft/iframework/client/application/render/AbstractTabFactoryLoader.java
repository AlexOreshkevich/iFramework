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
package pro.redsoft.iframework.client.application.render;

import java.util.HashMap;

import pro.redsoft.iframework.client.application.TabTypes;
import pro.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * AbstractTabFactoryLoader.
 * 
 * @author Alex N. Oreshkevich
 */
public class AbstractTabFactoryLoader {

  private final HashMap<TabTypes, AsyncProvider<? extends AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>> map;

  @Inject
  public AbstractTabFactoryLoader(AsyncProvider<ImageTabFactoryImpl> imageTabFactory,
      AsyncProvider<TextTabFactoryImpl> textTabFactory) {
    map = new HashMap<TabTypes, AsyncProvider<? extends AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>>();
    map.put(TabTypes.IMAGE, imageTabFactory);
    map.put(TabTypes.TEXT, textTabFactory);
  }

  public
      void
      get(TabTypes tabType,
          final AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>> callback) {

    @SuppressWarnings("unchecked")
    AsyncProvider<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>> provider = (AsyncProvider<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>) map
        .get(tabType);

    if (provider != null) {
      provider
          .get(new AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>() {

            @Override
            public void onFailure(Throwable caught) {
              callback.onFailure(caught);
            }

            @Override
            public void onSuccess(
                AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>> result) {
              callback.onSuccess(result);
            }
          });
    }
  }
}

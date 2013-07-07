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

package by.redsoft.iframework.client.application.view;

import java.util.HashMap;
import java.util.Map;

import by.redsoft.iframework.client.application.ApplicationPresenter;
import by.redsoft.iframework.client.application.TabType;
import by.redsoft.iframework.client.application.render.AbstractTabFactory;
import by.redsoft.iframework.client.application.render.AbstractTabFactoryLoader;
import by.redsoft.iframework.client.application.render.AbstractTabPresenter;
import by.redsoft.iframework.client.application.render.AbstractTabView;
import by.redsoft.iframework.client.place.NameTokens;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class ViewPagePresenter extends Presenter<ViewPagePresenter.MyView, ViewPagePresenter.MyProxy> {

  public interface MyView extends TabContainerView {
  }

  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<ViewPagePresenter> {
  }

  private final AbstractTabFactoryLoader tabFactoryLoader;

  private final Map<Integer, TabType>    initMap     = new HashMap<Integer, TabType>();
  private final Map<Integer, String>     tabNamesMap = new HashMap<Integer, String>();

  @Inject
  public ViewPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
      AbstractTabFactoryLoader tabFactoryLoader) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
    this.tabFactoryLoader = tabFactoryLoader;

    // this binding is set in config.xml
    for (int ind = 0; ind < TabType.values().length; ind++) {
      TabType type = TabType.values()[ind];
      initMap.put(ind, type);
      tabNamesMap.put(ind, type.toString());
    }
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    getView().init(0, tabNamesMap);
  }

  @Override
  protected void onBind() {
    super.onBind();
    registerHandler(getView().addSelectionHandler(new SelectionHandler<Integer>() {

      @Override
      public void onSelection(final SelectionEvent<Integer> event) {
        final int ind = event.getSelectedItem();
        tabFactoryLoader.get(
            initMap.get(ind),
            new AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>() {

              @Override
              public void onFailure(Throwable caught) {
                throw new RuntimeException(caught);
              }

              @Override
              public void onSuccess(
                  AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>> result) {

                if (getView().isProxy(ind)) {
                  getView().replaceProxy(ind, result.create().getView().asWidget());
                }
              }
            });
      }
    }));
  }
}

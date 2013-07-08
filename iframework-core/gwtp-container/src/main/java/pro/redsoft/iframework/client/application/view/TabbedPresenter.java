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
package pro.redsoft.iframework.client.application.view;

import java.util.HashMap;
import java.util.Map;

import pro.redsoft.iframework.client.application.TabTypes;
import pro.redsoft.iframework.client.application.render.AbstractTabFactory;
import pro.redsoft.iframework.client.application.render.AbstractTabFactoryLoader;
import pro.redsoft.iframework.client.application.render.AbstractTabPresenter;
import pro.redsoft.iframework.client.application.render.AbstractTabView;
import pro.redsoft.iframework.client.application.render.TabbedView;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * TabbedPresenter.
 * 
 * @author Alex N. Oreshkevich
 * @param <V>
 * @param <P>
 */
public abstract class TabbedPresenter<V extends TabbedView, P extends Proxy<?>> extends Presenter<V, P> {

  @Inject
  private AbstractTabFactoryLoader     tabFactoryLoader;

  private final Map<Integer, TabTypes> initMap     = new HashMap<Integer, TabTypes>();
  private final Map<Integer, String>   tabNamesMap = new HashMap<Integer, String>();

  public TabbedPresenter(EventBus eventBus, V view, P proxy, Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy, slot);
  }

  @Override
  protected void onBind() {
    super.onBind();

    // this binding can be set in configuration file
    for (int ind = 0; ind < TabTypes.values().length; ind++) {
      TabTypes type = TabTypes.values()[ind];
      initMap.put(ind, type);
      tabNamesMap.put(ind, type.toString());
    }

    // register tab selection handler
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

  @Override
  protected void onReveal() {
    super.onReveal();
    getView().init(0, tabNamesMap);
  }

}

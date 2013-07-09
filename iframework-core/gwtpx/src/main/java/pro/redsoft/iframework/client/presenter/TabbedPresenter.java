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
package pro.redsoft.iframework.client.presenter;

import java.util.HashMap;
import java.util.Map;

import pro.redsoft.iframework.client.factory.AbstractTabFactory;
import pro.redsoft.iframework.client.provider.AbstractTabFactoryLoader;
import pro.redsoft.iframework.client.provider.ITabType;
import pro.redsoft.iframework.client.view.AbstractTabView;

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

  protected final Map<Integer, ITabType> initMap     = new HashMap<Integer, ITabType>();
  protected final Map<Integer, String>   tabNamesMap = new HashMap<Integer, String>();

  @Inject
  private AbstractTabFactoryLoader       tabFactoryLoader;

  public TabbedPresenter(EventBus eventBus, V view, P proxy, Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy, slot);
  }

  protected abstract void initializeProxy();

  @Override
  protected void onBind() {
    super.onBind();

    initializeProxy();

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
              public
                  void
                  onSuccess(
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

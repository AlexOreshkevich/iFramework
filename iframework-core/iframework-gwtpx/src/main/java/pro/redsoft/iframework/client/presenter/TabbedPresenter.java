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
package pro.redsoft.iframework.client.presenter;

import java.util.HashMap;
import java.util.Map;

import pro.redsoft.iframework.client.factory.ComponentPresenterFactory;
import pro.redsoft.iframework.client.provider.ComponentLoader;
import pro.redsoft.iframework.client.view.ComponentView;

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
 * @version $Id: $Id
 */
public abstract class TabbedPresenter<V extends TabbedView, P extends Proxy<?>> extends Presenter<V, P> {

  protected final Map<Integer, Object> initMap     = new HashMap<Integer, Object>();
  protected final Map<Integer, String> tabNamesMap = new HashMap<Integer, String>();

  @Inject
  private ComponentLoader              tabFactoryLoader;

  /**
   * <p>Constructor for TabbedPresenter.</p>
   *
   * @param eventBus a {@link com.google.web.bindery.event.shared.EventBus} object.
   * @param view a V object.
   * @param proxy a P object.
   * @param slot a {@link com.google.gwt.event.shared.GwtEvent.Type} object.
   */
  public TabbedPresenter(EventBus eventBus, V view, P proxy, Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy, slot);
  }

  /**
   * <p>initializeProxy.</p>
   */
  protected abstract void initializeProxy();

  /** {@inheritDoc} */
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
            new AsyncCallback<ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>>>() {

              @Override
              public void onFailure(Throwable caught) {
                throw new RuntimeException(caught);
              }

              @Override
              public
                  void
                  onSuccess(
                      ComponentPresenterFactory<? extends ComponentView, ? extends ComponentPresenterWidget<?>> result) {

                if (getView().isProxy(ind)) {
                  getView().replaceProxy(ind, result.create().getView().asWidget());
                }
              }
            });
      }
    }));
  }

  /** {@inheritDoc} */
  @Override
  protected void onReveal() {
    super.onReveal();
    getView().init(0, tabNamesMap);
  }

}

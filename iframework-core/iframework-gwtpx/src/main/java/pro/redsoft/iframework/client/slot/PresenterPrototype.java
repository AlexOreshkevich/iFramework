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
package pro.redsoft.iframework.client.slot;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;

import java.util.Map;
import java.util.Set;

/**
 * General application-level presenter.
 *
 * @param <V> View subtype
 * @param <P> Proxy subtype
 * @author alex oreshkevich
 * @version 0.4.1
 */
public abstract class PresenterPrototype<V extends View, P extends Proxy<?>> extends Presenter<V, P> {

  /**
   * Defines parent-child relations via proxy level. See
   * {@link pro.redsoft.iframework.client.slot.SlotMappedProxy} for details.
   *
   * @param eventBus a {@link com.google.web.bindery.event.shared.EventBus} object.
   * @param view     a V object.
   * @param proxy    a P object.
   */
  public PresenterPrototype(EventBus eventBus, V view, P proxy) {
    super(eventBus, view, proxy);
  }

  /**
   * Use this function for common-purpose tokenProxy change handling.
   * For example, you can use this for VIEW mode request. In callback you
   * should receive ViewPresenter.
   *
   * @param requestedProxy some of {@link pro.redsoft.iframework.client.slot.TokenProxy} implementation
   * @param revealCallback presenter will be loaded dynamically, so instead of default return value we use callback
   */
  protected void changeTokenProxy(TokenProxy requestedProxy,
                                  AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback) {
    getEventBus().fireEvent(new ProxyPresenterChangeRequestEvent(requestedProxy, revealCallback));
  }

  /**
   * Load childs by tokenProxy.
   *
   * @param tokenProxy a {@link pro.redsoft.iframework.client.slot.TokenProxy} object.
   */
  public void loadChilds(final TokenProxy tokenProxy) {

    // do not load childs if proxy isn't mapped
    if (!(getProxy() instanceof SlotMappedProxy)) {
      return;
    }

    // do not load childs if childMap is empty
    SlotMappedProxy mappedProxy = (SlotMappedProxy) getProxy();
    final Map<String, Set<? extends TokenProxy>> childMap = mappedProxy.getChildMap();
    if (childMap.isEmpty()) {
      return;
    }

    // dynamic child presenter loading
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @SuppressWarnings("unchecked")
      @Override
      public void execute() {

        for (String slotKey : childMap.keySet()) {

          // do not load illegal tokens
          if (tokenProxy != null && !childMap.get(slotKey).contains(tokenProxy)) {
            continue;
          }

          getEventBus()
              .fireEvent(
                  new RevealProxyContentEvent(
                      slotKey,
                      tokenProxy,
                      (PresenterPrototype<? extends View, ? extends SlotMappedProxy>) PresenterPrototype.this));
        }
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onBind() {
    super.onBind();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onReveal() {
    // load childs by direct loadChilds() calls
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void revealInParent() {
    // discard default gwtp reveal strategy
  }
}

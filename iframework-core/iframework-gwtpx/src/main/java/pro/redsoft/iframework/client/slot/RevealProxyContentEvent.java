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
package pro.redsoft.iframework.client.slot;

import java.util.Map;
import java.util.Set;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * Dispatched to proxy-level by standard revealing strategy.
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class RevealProxyContentEvent extends GwtEvent<RevealProxyContentEvent.RevealProxyContentHandler> {

  /**
   * RevealProxyContentHandler.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface RevealProxyContentHandler extends EventHandler, SlotMappedProxy {
    void onRevealProxyContent(RevealProxyContentEvent event);
  }

  /**
   * RevealProxyContentHasHandlers.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface RevealProxyContentHasHandlers extends HasHandlers {
    HandlerRegistration addRevealProxyContentHandler(RevealProxyContentHandler handler);
  }

  public static Type<RevealProxyContentHandler> TYPE = new Type<RevealProxyContentHandler>();

  public static Type<RevealProxyContentHandler> getType() {
    return TYPE;
  }

  private final Object                                               requestedSlot;

  private final TokenProxy                                           tokenProxy;

  private final Presenter<? extends View, ? extends SlotMappedProxy> parentPresenter;

  private final AsyncCallback<PresenterPrototype<? extends View, ?>> childRevealCallback;

  public RevealProxyContentEvent(Object requestedSlot, TokenProxy tokenProxy,
      Presenter<? extends View, ? extends SlotMappedProxy> parentPresenter) {
    this(requestedSlot, tokenProxy, parentPresenter, null);
  }

  public RevealProxyContentEvent(Object requestedSlot, TokenProxy tokenProxy,
      Presenter<? extends View, ? extends SlotMappedProxy> parentPresenter,
      AsyncCallback<PresenterPrototype<? extends View, ?>> childRevealCallback) {
    this.requestedSlot = requestedSlot;
    this.tokenProxy = tokenProxy;
    this.parentPresenter = parentPresenter;
    this.childRevealCallback = childRevealCallback;
  }

  @Override
  protected final void dispatch(RevealProxyContentHandler handler) {

    // event can be handled only by non-source handlers that also is a Proxies
    if (parentPresenter.getProxy().equals(handler) || !(handler instanceof Proxy)) {
      return;
    }

    Object slot = null;
    Map<TokenProxy, String> parentMap = handler.getParentMap();

    // check that slot exists in parent mapping and matches requested slot
    if (null == parentMap || null == (slot = parentMap.get(tokenProxy)) || !slot.equals(requestedSlot)) {
      return;
    }

    Map<String, Set<? extends TokenProxy>> childMap = parentPresenter.getProxy().getChildMap();

    // checks that parentPresenter has child mapping for requested slot
    if (!childMap.get(requestedSlot).contains(tokenProxy)) {
      throw new IllegalArgumentException("Parent-child mapping error for "
          + parentPresenter.getClass().getName() + "\nproxy trace=" + tokenProxy + " " + childMap);
    }

    handler.onRevealProxyContent(this);
  }

  @Override
  public Type<RevealProxyContentHandler> getAssociatedType() {
    return TYPE;
  }

  public AsyncCallback<PresenterPrototype<? extends View, ?>> getChildRevealCallback() {
    return childRevealCallback;
  }

  public Presenter<? extends View, ? extends SlotMappedProxy> getParentPresenter() {
    return parentPresenter;
  }

  public Object getRequestedSlot() {
    return requestedSlot;
  }

  public TokenProxy getTokenProxy() {
    return tokenProxy;
  }

  public boolean hasRevealCallback() {
    return childRevealCallback != null;
  }
}

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

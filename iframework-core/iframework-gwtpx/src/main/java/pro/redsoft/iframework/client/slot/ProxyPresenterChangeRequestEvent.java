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

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.View;

/**
 * ProxyPresenterChangeRequestEvent.
 *
 * @author alex oreshkevich
 * @version 0.4.1
 */
public class ProxyPresenterChangeRequestEvent extends
    GwtEvent<ProxyPresenterChangeRequestEvent.ProxyPresenterChangeRequestHandler> {

  /**
   * ProxyPresenterChangeRequestHandler.
   *
   * @author Alex N. Oreshkevich
   */
  public interface ProxyPresenterChangeRequestHandler extends EventHandler {
    void onProxyPresenterChangeRequest(ProxyPresenterChangeRequestEvent event);
  }

  /**
   * ProxyPresenterChangeRequestHandler.
   *
   * @author Alex N. Oreshkevich
   */
  public interface ProxyPresenterChangeRequestHasHandlers extends HasHandlers {
    HandlerRegistration
    addProxyPresenterChangeRequestHandler(ProxyPresenterChangeRequestHandler handler);
  }

  /**
   * Constant <code>TYPE</code>.
   */
  public static Type<ProxyPresenterChangeRequestHandler> TYPE = new Type<ProxyPresenterChangeRequestHandler>();

  private final TokenProxy requestedProxy;
  private final AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback;

  /**
   * <p>fire.</p>
   *
   * @param source         a {@link com.google.gwt.event.shared.HasHandlers} object.
   * @param requestedProxy a {@link pro.redsoft.iframework.client.slot.TokenProxy} object.
   * @param revealCallback a {@link com.google.gwt.user.client.rpc.AsyncCallback} object.
   */
  public static void fire(HasHandlers source, TokenProxy requestedProxy,
                          AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback) {
    source.fireEvent(new ProxyPresenterChangeRequestEvent(requestedProxy, revealCallback));
  }

  /**
   * <p>getType.</p>
   *
   * @return a Type object.
   */
  public static Type<ProxyPresenterChangeRequestHandler> getType() {
    return TYPE;
  }

  /**
   * <p>Constructor for ProxyPresenterChangeRequestEvent.</p>
   *
   * @param requestedProxy a {@link pro.redsoft.iframework.client.slot.TokenProxy} object.
   * @param revealCallback a {@link com.google.gwt.user.client.rpc.AsyncCallback} object.
   */
  public ProxyPresenterChangeRequestEvent(TokenProxy requestedProxy,
                                          AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback) {
    this.requestedProxy = requestedProxy;
    this.revealCallback = revealCallback;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void dispatch(ProxyPresenterChangeRequestHandler handler) {
    handler.onProxyPresenterChangeRequest(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Type<ProxyPresenterChangeRequestHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * <p>Getter for the field <code>requestedProxy</code>.</p>
   *
   * @return a {@link pro.redsoft.iframework.client.slot.TokenProxy} object.
   */
  public TokenProxy getRequestedProxy() {
    return requestedProxy;
  }

  /**
   * <p>Getter for the field <code>revealCallback</code>.</p>
   *
   * @return a {@link com.google.gwt.user.client.rpc.AsyncCallback} object.
   */
  public AsyncCallback<PresenterPrototype<? extends View, ?>> getRevealCallback() {
    return revealCallback;
  }
}

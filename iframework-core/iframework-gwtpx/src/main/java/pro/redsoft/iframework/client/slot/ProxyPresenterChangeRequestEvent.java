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

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.View;

/**
 * ProxyPresenterChangeRequestEvent.
 * 
 * @since 4.1
 * @author alex oreshkevich
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

  public static Type<ProxyPresenterChangeRequestHandler> TYPE = new Type<ProxyPresenterChangeRequestHandler>();

  public static void fire(HasHandlers source, TokenProxy requestedProxy,
      AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback) {
    source.fireEvent(new ProxyPresenterChangeRequestEvent(requestedProxy, revealCallback));
  }

  public static Type<ProxyPresenterChangeRequestHandler> getType() {
    return TYPE;
  }

  private final TokenProxy                                           requestedProxy;

  private final AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback;

  public ProxyPresenterChangeRequestEvent(TokenProxy requestedProxy,
      AsyncCallback<PresenterPrototype<? extends View, ?>> revealCallback) {
    this.requestedProxy = requestedProxy;
    this.revealCallback = revealCallback;
  }

  @Override
  protected void dispatch(ProxyPresenterChangeRequestHandler handler) {
    handler.onProxyPresenterChangeRequest(this);
  }

  @Override
  public Type<ProxyPresenterChangeRequestHandler> getAssociatedType() {
    return TYPE;
  }

  public TokenProxy getRequestedProxy() {
    return requestedProxy;
  }

  public AsyncCallback<PresenterPrototype<? extends View, ?>> getRevealCallback() {
    return revealCallback;
  }
}

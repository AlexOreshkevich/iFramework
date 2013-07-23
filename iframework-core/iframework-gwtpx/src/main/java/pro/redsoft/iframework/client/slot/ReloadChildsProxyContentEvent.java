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
import com.gwtplatform.mvp.client.View;

/**
 * ReloadChildsProxyContentEvent.
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class ReloadChildsProxyContentEvent extends
    GwtEvent<ReloadChildsProxyContentEvent.ReloadChildsProxyContentHandler> {

  /**
   * ReloadChildsProxyContentHandler.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface ReloadChildsProxyContentHandler extends EventHandler {
    void onReloadChildsProxyContent(ReloadChildsProxyContentEvent event);
  }

  /**
   * ReloadChildsProxyContentHasHandlers.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface ReloadChildsProxyContentHasHandlers extends HasHandlers {
    HandlerRegistration addReloadChildsProxyContentHandler(ReloadChildsProxyContentHandler handler);
  }

  public static Type<ReloadChildsProxyContentHandler> TYPE = new Type<ReloadChildsProxyContentHandler>();

  public static Type<ReloadChildsProxyContentHandler> getType() {
    return TYPE;
  }

  private final PresenterPrototype<? extends View, ? extends SlotMappedProxy> sourcePresenter;

  private final TokenProxy                                                    tokenProxy;

  public ReloadChildsProxyContentEvent(
      PresenterPrototype<? extends View, ? extends SlotMappedProxy> sourcePresenter,
      TokenProxy tokenProxy) {
    this.sourcePresenter = sourcePresenter;
    this.tokenProxy = tokenProxy;
  }

  @Override
  protected void dispatch(ReloadChildsProxyContentHandler handler) {
    handler.onReloadChildsProxyContent(this);
  }

  @Override
  public Type<ReloadChildsProxyContentHandler> getAssociatedType() {
    return TYPE;
  }

  public PresenterPrototype<? extends View, ? extends SlotMappedProxy> getSourcePresenter() {
    return sourcePresenter;
  }

  public TokenProxy getTokenProxy() {
    return tokenProxy;
  }
}

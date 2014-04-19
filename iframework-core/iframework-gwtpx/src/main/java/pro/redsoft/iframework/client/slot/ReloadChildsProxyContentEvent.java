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
import com.gwtplatform.mvp.client.View;

/**
 * ReloadChildsProxyContentEvent.
 * 
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

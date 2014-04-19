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
package pro.redsoft.iframework.client.auth;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * CurrentUserChangedEvent.
 * 
 * @author Alex N. Oreshkevich
 */
public class CurrentUserChangedEvent extends GwtEvent<CurrentUserChangedEvent.CurrentUserChangedHandler> {

  /**
   * CurrentUserChangedHandler.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface CurrentUserChangedHandler extends EventHandler {
    void onCurrentUserChanged(CurrentUserChangedEvent event);
  }

  /**
   * CurrentUserChangedHasHandlers.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface CurrentUserChangedHasHandlers extends HasHandlers {
    HandlerRegistration addCurrentUserChangedHandler(CurrentUserChangedHandler handler);
  }

  public static Type<CurrentUserChangedHandler> TYPE = new Type<CurrentUserChangedHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new CurrentUserChangedEvent());
  }

  public static Type<CurrentUserChangedHandler> getType() {
    return TYPE;
  }

  public CurrentUserChangedEvent() {
  }

  @Override
  protected void dispatch(CurrentUserChangedHandler handler) {
    handler.onCurrentUserChanged(this);
  }

  @Override
  public Type<CurrentUserChangedHandler> getAssociatedType() {
    return TYPE;
  }
}

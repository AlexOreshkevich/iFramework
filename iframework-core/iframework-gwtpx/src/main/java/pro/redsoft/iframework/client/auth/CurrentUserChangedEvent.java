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
 * @version $Id: $Id
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

  /** Constant <code>TYPE</code>. */
  public static Type<CurrentUserChangedHandler> TYPE = new Type<CurrentUserChangedHandler>();

  /**
   * <p>fire.</p>
   *
   * @param source a {@link com.google.gwt.event.shared.HasHandlers} object.
   */
  public static void fire(HasHandlers source) {
    source.fireEvent(new CurrentUserChangedEvent());
  }

  /**
   * <p>getType.</p>
   *
   * @return a Type object.
   */
  public static Type<CurrentUserChangedHandler> getType() {
    return TYPE;
  }

  /**
   * <p>Constructor for CurrentUserChangedEvent.</p>
   */
  public CurrentUserChangedEvent() {
  }

  /** {@inheritDoc} */
  @Override
  protected void dispatch(CurrentUserChangedHandler handler) {
    handler.onCurrentUserChanged(this);
  }

  /** {@inheritDoc} */
  @Override
  public Type<CurrentUserChangedHandler> getAssociatedType() {
    return TYPE;
  }
}

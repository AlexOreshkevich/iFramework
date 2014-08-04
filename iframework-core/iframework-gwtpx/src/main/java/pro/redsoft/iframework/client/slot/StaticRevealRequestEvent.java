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

/**
 * StaticRevealRequestEvent.
 *
 * @since 4.1
 * @author alex oreshkevich
 * @version $Id: $Id
 */
public class StaticRevealRequestEvent extends
    GwtEvent<StaticRevealRequestEvent.StaticRevealRequestHandler> {

  /**
   * StaticRevealRequestHandler.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface StaticRevealRequestHandler extends EventHandler {
    void onStaticRevealRequest(StaticRevealRequestEvent event);
  }

  /**
   * StaticRevealRequestHasHandlers.
   * 
   * @author Alex N. Oreshkevich
   */
  public interface StaticRevealRequestHasHandlers extends HasHandlers {
    HandlerRegistration addStaticRevealRequestHandler(StaticRevealRequestHandler handler);
  }

  /** Constant <code>TYPE</code> */
  public static Type<StaticRevealRequestHandler> TYPE = new Type<StaticRevealRequestHandler>();

  /**
   * <p>fire.</p>
   *
   * @param source a {@link com.google.gwt.event.shared.HasHandlers} object.
   */
  public static void fire(HasHandlers source) {
    source.fireEvent(new StaticRevealRequestEvent());
  }

  /**
   * <p>getType.</p>
   *
   * @return a Type object.
   */
  public static Type<StaticRevealRequestHandler> getType() {
    return TYPE;
  }

  /**
   * <p>Constructor for StaticRevealRequestEvent.</p>
   */
  public StaticRevealRequestEvent() {
  }

  /** {@inheritDoc} */
  @Override
  protected void dispatch(StaticRevealRequestHandler handler) {
    handler.onStaticRevealRequest(this);
  }

  /** {@inheritDoc} */
  @Override
  public Type<StaticRevealRequestHandler> getAssociatedType() {
    return TYPE;
  }
}

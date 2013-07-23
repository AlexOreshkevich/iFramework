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

/**
 * StaticRevealRequestEvent.
 * 
 * @since 4.1
 * @author alex oreshkevich
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

  public static Type<StaticRevealRequestHandler> TYPE = new Type<StaticRevealRequestHandler>();

  public static void fire(HasHandlers source) {
    source.fireEvent(new StaticRevealRequestEvent());
  }

  public static Type<StaticRevealRequestHandler> getType() {
    return TYPE;
  }

  public StaticRevealRequestEvent() {
  }

  @Override
  protected void dispatch(StaticRevealRequestHandler handler) {
    handler.onStaticRevealRequest(this);
  }

  @Override
  public Type<StaticRevealRequestHandler> getAssociatedType() {
    return TYPE;
  }
}

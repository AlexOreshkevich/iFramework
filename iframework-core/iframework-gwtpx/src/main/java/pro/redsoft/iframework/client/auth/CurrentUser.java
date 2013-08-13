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
package pro.redsoft.iframework.client.auth;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * This is a basic class that holds the privileges of the user currently logged in.
 * 
 * @author alex oreshkevich
 */
public class CurrentUser implements HasHandlers {

  private final EventBus eventBus;
  private boolean        isAuthorized;

  @Inject
  public CurrentUser(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }

  public boolean isAuthorized() {
    return isAuthorized;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAuthorized = isAdmin;
    CurrentUserChangedEvent.fire(this);
  }
}

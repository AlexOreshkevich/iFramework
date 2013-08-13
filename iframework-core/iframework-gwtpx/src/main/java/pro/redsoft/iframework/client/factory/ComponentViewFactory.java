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
package pro.redsoft.iframework.client.factory;

import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;
import pro.redsoft.iframework.client.view.ComponentView;

/**
 * This factory is used to create new views for the {@link ComponentPresenterWidget}.
 * 
 * @author Alex N. Oreshkevich
 * @param <V>
 */
public interface ComponentViewFactory<V extends ComponentView> {

  /**
   * Create a new view for a {@link ComponentPresenterWidget}.
   * 
   * @return The newly created view for this {@link ComponentPresenterWidget}.
   */
  V create();
}

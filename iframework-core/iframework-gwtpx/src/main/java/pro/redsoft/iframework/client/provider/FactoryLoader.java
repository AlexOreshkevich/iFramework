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
package pro.redsoft.iframework.client.provider;

import pro.redsoft.iframework.client.factory.AbstractTabFactory;
import pro.redsoft.iframework.client.presenter.AbstractTabPresenter;
import pro.redsoft.iframework.client.view.AbstractTabView;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * FactoryLoader.
 * 
 * @author Alex N. Oreshkevich
 */
public interface FactoryLoader {

  /**
   * Load factory by tabType
   * 
   * @param tabType
   * @param asyncCallback
   * @throws IllegalArgumentException
   *           if asyncCallback is null
   * @throws UnsupportedOperationException
   *           if missing configuration mapping for tabType
   */
      void
      get(Object tabType,
          AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>> asyncCallback)
          throws IllegalArgumentException, UnsupportedOperationException;
}

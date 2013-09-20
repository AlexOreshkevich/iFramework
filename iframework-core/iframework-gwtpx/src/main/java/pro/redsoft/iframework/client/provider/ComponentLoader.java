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

import pro.redsoft.iframework.client.factory.ComponentPresenterFactory;
import pro.redsoft.iframework.client.presenter.ComponentPresenterWidget;
import pro.redsoft.iframework.client.view.ComponentView;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * ComponentLoader.
 * 
 * @author Alex N. Oreshkevich
 */
public interface ComponentLoader<V extends ComponentView, P extends ComponentPresenterWidget<V>, F extends ComponentPresenterFactory<V, P>> {

  /**
   * Loads component by type.
   * 
   * @param type
   * @param asyncCallback
   * @throws IllegalArgumentException
   *           if asyncCallback is null
   * @throws UnsupportedOperationException
   *           if missing configuration mapping for tabType
   */
  void get(Object type, AsyncCallback<F> asyncCallback) throws IllegalArgumentException,
      UnsupportedOperationException;

  /**
   * Register component by type.
   * 
   * @param type
   * @param provider
   * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for <tt>key</tt>.
   *         (A <tt>null</tt> return can also indicate that the map previously associated <tt>null</tt> with
   *         <tt>key</tt>, if the implementation supports <tt>null</tt> values.)
   */
  AsyncProvider<F> register(Object type, AsyncProvider<F> provider);
}

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

import pro.redsoft.iframework.client.presenter.AbstractTabPresenter;
import pro.redsoft.iframework.client.view.AbstractTabView;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * AbstractTabFactoryImpl.
 * 
 * @author Alex N. Oreshkevich
 * @param <V>
 * @param <P>
 * @param <F>
 */
public abstract class AbstractTabFactoryImpl<V extends AbstractTabView, P extends AbstractTabPresenter<V>, F extends AbstractTabViewFactory<V>>
    implements AbstractTabFactory<V, P> {

  protected final EventBus eventBus;
  protected final F        viewFactory;

  @Inject
  protected AbstractTabFactoryImpl(EventBus eventBus, F viewFactory) {
    this.eventBus = eventBus;
    this.viewFactory = viewFactory;
  }
}

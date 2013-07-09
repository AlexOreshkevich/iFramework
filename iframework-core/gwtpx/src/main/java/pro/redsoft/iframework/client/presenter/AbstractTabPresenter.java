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
package pro.redsoft.iframework.client.presenter;

import pro.redsoft.iframework.client.view.AbstractTabView;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * AbstractTabPresenter.
 * 
 * @author Alex N. Oreshkevich
 * @param <V>
 */
public abstract class AbstractTabPresenter<V extends AbstractTabView> extends PresenterWidget<V> {

  protected AbstractTabPresenter(final EventBus eventBus, final V view) {
    super(false, eventBus, view);
  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().bind();
  }

  @Override
  protected void onUnbind() {
    super.onUnbind();
    getView().unbind();
  }
}

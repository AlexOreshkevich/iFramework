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
package pro.redsoft.iframework.client.xml;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Panel;

/**
 * Form Presenter.
 * 
 * @author alex oreshkevich
 * @param <T>
 */
public abstract class FormPresenter<T extends RBean> extends RClass {

  private T              bean;
  private HandlerManager eventBus;

  public FormPresenter(T bean) {
    this.bean = bean;
  }

  /**
   * Start presenter with specified description.
   * 
   * @param container
   * @param tabDescription
   */
  public abstract void clear(Panel container);

  /**
   * @return the bean
   */
  public T getBean() {
    return bean;
  }

  /**
   * @return the eventBus
   */
  public HandlerManager getEventBus() {
    return eventBus;
  }

  public abstract int getFormCode();

  /**
   * Start presenter with specified description.
   * 
   * @param container
   * @param tabDescription
   */
  public abstract void go(Panel container, String tabDescription);

  public void setBean(T bean) {
    this.bean = bean;
  }

  /**
   * @param eventBus
   *          the eventBus to set
   */
  public void setEventBus(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

}

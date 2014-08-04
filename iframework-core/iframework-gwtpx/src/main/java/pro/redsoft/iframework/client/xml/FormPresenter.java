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
package pro.redsoft.iframework.client.xml;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Panel;

/**
 * Form Presenter.
 *
 * @author alex oreshkevich
 * @param <T> form type
 * @version 0.4.1
 */
public abstract class FormPresenter<T extends RBean> extends RClass {

  private T              bean;
  private HandlerManager eventBus;

  /**
   * <p>Constructor for FormPresenter.</p>
   *
   * @param bean a T object.
   */
  public FormPresenter(T bean) {
    this.bean = bean;
  }

  /**
   * Start presenter with specified description.
   *
   * @param container a {@link com.google.gwt.user.client.ui.Panel} object.
   */
  public abstract void clear(Panel container);

  /**
   * <p>Getter for the field <code>bean</code>.</p>
   *
   * @return the bean
   */
  public T getBean() {
    return bean;
  }

  /**
   * <p>Getter for the field <code>eventBus</code>.</p>
   *
   * @return the eventBus
   */
  public HandlerManager getEventBus() {
    return eventBus;
  }

  /**
   * <p>getFormCode.</p>
   *
   * @return a int.
   */
  public abstract int getFormCode();

  /**
   * Start presenter with specified description.
   *
   * @param container a {@link com.google.gwt.user.client.ui.Panel} object.
   * @param tabDescription a {@link java.lang.String} object.
   */
  public abstract void go(Panel container, String tabDescription);

  /**
   * <p>Setter for the field <code>bean</code>.</p>
   *
   * @param bean a T object.
   */
  public void setBean(T bean) {
    this.bean = bean;
  }

  /**
   * <p>Setter for the field <code>eventBus</code>.</p>
   *
   * @param eventBus
   *          the eventBus to set
   */
  public void setEventBus(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

}

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
package pro.redsoft.iframework.client.presenter;

import pro.redsoft.iframework.client.component.Component;
import pro.redsoft.iframework.client.component.Container;
import pro.redsoft.iframework.client.view.ComponentView;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * ComponentPresenterWidget.
 *
 * @author Alex N. Oreshkevich
 * @param <V>
 * @version $Id: $Id
 */
public abstract class ComponentPresenterWidget<V extends ComponentView> extends PresenterWidget<V>
    implements Component {

  protected transient Container parentContainer;

  /**
   * <p>Constructor for ComponentPresenterWidget.</p>
   *
   * @param eventBus a {@link com.google.web.bindery.event.shared.EventBus} object.
   * @param view a V object.
   */
  protected ComponentPresenterWidget(final EventBus eventBus, final V view) {
    super(false, eventBus, view);
  }

  /** {@inheritDoc} */
  @Override
  protected void onBind() {
    super.onBind();
    getView().bind();
  }

  /** {@inheritDoc} */
  @Override
  protected void onUnbind() {
    super.onUnbind();
    getView().unbind();
  }

  /** {@inheritDoc} */
  @Override
  public Container getParentContainer() {
    return parentContainer;
  }

  /**
   * <p>Setter for the field <code>parentContainer</code>.</p>
   *
   * @param parentContainer a {@link pro.redsoft.iframework.client.component.Container} object.
   */
  public void setParentContainer(Container parentContainer) {
    this.parentContainer = parentContainer;
  }

}

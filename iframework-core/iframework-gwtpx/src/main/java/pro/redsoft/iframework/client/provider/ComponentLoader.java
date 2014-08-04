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
 * @param <V>
 *          view
 * @param <P>
 *          presenter
 * @param <F>
 *          factory
 * @version $Id: $Id
 */
public interface ComponentLoader<V extends ComponentView, P extends ComponentPresenterWidget<V>, F extends ComponentPresenterFactory<V, P>> {

  /**
   * Loads component by type.
   *
   * @param type a {@link java.lang.Object} object.
   * @param asyncCallback a {@link com.google.gwt.user.client.rpc.AsyncCallback} object.
   * @throws java.lang.IllegalArgumentException
   *           if asyncCallback is null
   * @throws java.lang.UnsupportedOperationException
   *           if missing configuration mapping for tabType
   */
  void get(Object type, AsyncCallback<F> asyncCallback) throws IllegalArgumentException,
      UnsupportedOperationException;

  /**
   * Register component by type.
   *
   * @param type a {@link java.lang.Object} object.
   * @param provider a {@link com.google.gwt.inject.client.AsyncProvider} object.
   * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for <tt>key</tt>.
   *         (A <tt>null</tt> return can also indicate that the map previously associated <tt>null</tt> with
   *         <tt>key</tt>, if the implementation supports <tt>null</tt> values.)
   */
  AsyncProvider<F> register(Object type, AsyncProvider<F> provider);
}

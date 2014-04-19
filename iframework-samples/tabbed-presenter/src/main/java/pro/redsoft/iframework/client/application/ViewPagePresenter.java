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
package pro.redsoft.iframework.client.application;

import pro.redsoft.iframework.client.place.NameTokens;
import pro.redsoft.iframework.client.presenter.TabbedPresenter;
import pro.redsoft.iframework.client.presenter.TabbedView;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * ViewPagePresenter.
 * 
 * @author Alex N. Oreshkevich
 */
public class ViewPagePresenter extends
    TabbedPresenter<ViewPagePresenter.MyView, ViewPagePresenter.MyProxy> {

  /**
   * MyProxy.
   */
  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<ViewPagePresenter> {
  }

  /**
   * MyView.
   */
  public interface MyView extends View, TabbedView {
  }

  @Inject
  public ViewPagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
  }

  @Override
  protected void initializeProxy() {

    // this binding can be set in configuration file
    for (int ind = 0; ind < TabTypes.values().length; ind++) {
      TabTypes type = TabTypes.values()[ind];
      initMap.put(ind, type);
      tabNamesMap.put(ind, type.toString());
    }
  }
}

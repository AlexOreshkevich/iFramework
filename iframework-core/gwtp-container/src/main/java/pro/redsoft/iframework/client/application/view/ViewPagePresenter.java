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
package pro.redsoft.iframework.client.application.view;

import pro.redsoft.iframework.client.application.ApplicationPresenter;
import pro.redsoft.iframework.client.application.TabTypes;
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

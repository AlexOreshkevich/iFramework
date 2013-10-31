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
package pro.redsoft.iframework.client.application;

import pro.redsoft.iframework.client.application.service.ConfigService;
import pro.redsoft.iframework.client.application.service.ConfigServiceAsync;
import pro.redsoft.iframework.client.generate.Model;
import pro.redsoft.iframework.client.generate.NumberModel;
import pro.redsoft.iframework.shared.config.Config;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * ApplicationPresenter.
 * 
 * @author Alex N. Oreshkevich
 */
public class ApplicationPresenter extends
    Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

  /**
   * Proxy associated with ApplicationPresenter.
   */
  @ProxyStandard
  public interface MyProxy extends Proxy<ApplicationPresenter> {
  }

  /**
   * View associated with ApplicationPresenter.
   */
  public interface MyView extends View {
  }

  @ContentSlot
  public static final Type<RevealContentHandler<?>> SLOT_SetMainContent = new Type<RevealContentHandler<?>>();

  @Inject
  public ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, RevealType.Root);
  }

  private final ConfigServiceAsync configService = GWT.create(ConfigService.class);

  @Override
  protected void onReveal() {
    super.onReveal();

    Model numberModel = GWT.create(NumberModel.class);
    Window.alert(numberModel.getType().getName());

    configService.getClientSettings(new AsyncCallback<Config>() {

      @Override
      public void onSuccess(Config result) {

        if (result.getLogMessage() != null) {
          Window.alert("Ошибка при работе с внешним файлом конфигурации.\n\n"
              + result.getLogMessage());
        }

        getView().setInSlot("slot", new HTMLPanel("pre", result.getLogMessage() + "<br/>"));
        getView().setInSlot("slot",
            new HTMLPanel("p", "<br/>" + result.getSystem().getEntryPoint()));
      }

      @Override
      public void onFailure(Throwable caught) {
        getView().setInSlot("slot", new HTMLPanel("pre", caught.getMessage()));
      }
    });
  }
}

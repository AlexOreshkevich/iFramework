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

import pro.redsoft.iframework.client.application.service.ConfigService;
import pro.redsoft.iframework.client.application.service.ConfigServiceAsync;
import pro.redsoft.iframework.shared.config.Config;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
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

    configService.getClientSettings(new AsyncCallback<Config>() {

      @Override
      public void onSuccess(Config result) {

        // if (result.getLogMessage() != null) {
        // Window.alert("Ошибка при работе с внешним файлом конфигурации.\n\n"
        // + result.getLogMessage());
        // }

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

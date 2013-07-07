package by.redsoft.iframework.client.application.render;

import java.util.HashMap;

import by.redsoft.iframework.client.application.TabType;
import by.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import by.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * AbstractTabFactoryLoader.java
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public class AbstractTabFactoryLoader {

  private final HashMap<TabType, AsyncProvider<? extends AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>> map;

  @Inject
  public AbstractTabFactoryLoader(AsyncProvider<ImageTabFactoryImpl> imageTabFactory,
      AsyncProvider<TextTabFactoryImpl> textTabFactory) {
    map = new HashMap<TabType, AsyncProvider<? extends AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>>();
    map.put(TabType.IMAGE, imageTabFactory);
    map.put(TabType.TEXT, textTabFactory);
  }

  public
      void
      get(TabType tabType,
          final AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>> callback) {

    @SuppressWarnings("unchecked")
    AsyncProvider<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>> provider = (AsyncProvider<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>) map
        .get(tabType);

    if (provider != null) {
      provider
          .get(new AsyncCallback<AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>>>() {

            @Override
            public void onFailure(Throwable caught) {
              callback.onFailure(caught);
            }

            @Override
            public void onSuccess(
                AbstractTabFactory<? extends AbstractTabView, ? extends AbstractTabPresenter<?>> result) {
              callback.onSuccess(result);
            }
          });
    }
  }
}

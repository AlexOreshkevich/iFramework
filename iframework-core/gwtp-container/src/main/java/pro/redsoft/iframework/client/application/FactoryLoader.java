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

import pro.redsoft.iframework.client.application.impl.ImageTabPresenter.ImageTabFactoryImpl;
import pro.redsoft.iframework.client.application.impl.TextTabPresenter.TextTabFactoryImpl;
import pro.redsoft.iframework.client.provider.AbstractTabFactoryLoader;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * FactoryLoader.
 * 
 * @author Alex N. Oreshkevich
 */
@Singleton
public class FactoryLoader extends AbstractTabFactoryLoader {

  @Inject
  public FactoryLoader(AsyncProvider<ImageTabFactoryImpl> imageTabFactory,
      AsyncProvider<TextTabFactoryImpl> textTabFactory) {
    map.put(TabTypes.IMAGE, imageTabFactory);
    map.put(TabTypes.TEXT, textTabFactory);
  }
}

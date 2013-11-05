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

import javax.inject.Inject;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * ApplicationView.
 * 
 * @author Alex N. Oreshkevich
 */
public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

  FlowPanel main = new FlowPanel();

  @Inject
  public ApplicationView() {
    initWidget(main);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    main.add(content);
  }
}

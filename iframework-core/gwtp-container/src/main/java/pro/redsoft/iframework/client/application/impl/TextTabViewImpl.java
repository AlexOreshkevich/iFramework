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
package pro.redsoft.iframework.client.application.impl;

import pro.redsoft.iframework.client.view.AbstractTabViewImpl;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TextTabViewImpl.
 * 
 * @author Alex N. Oreshkevich
 */
public class TextTabViewImpl extends AbstractTabViewImpl implements TextTabView {

  private VerticalPanel widget;

  TextTabViewImpl() {
    widget = new VerticalPanel();
    for (int i = 0; i < 1000; i++) {
      widget.add(new HTML("<p><b>" + i + "</b>This is TextTabViewImpl</p>"));
    }
  }

  @Override
  public Widget asWidget() {
    return widget;
  }
}

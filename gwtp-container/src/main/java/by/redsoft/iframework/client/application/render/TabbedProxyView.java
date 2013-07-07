/**
 * Copyright 2000-2012 InterTrust LTD.
 * 
 * All rights reserved.
 *
 * Visit our web-site: www.intertrust.ru.
 */
package by.redsoft.iframework.client.application.render;

import java.util.Map;

import com.google.gwt.user.client.ui.Widget;

/**
 * TabbedProxyView
 * 
 * @since 4.1
 * @author alex oreshkevich
 */
public interface TabbedProxyView {

  void init(int defaultSelectedInd, Map<Integer, String> tabNamesMap);

  void replaceProxy(int tabInd, Widget w);

  boolean isProxy(int tabInd);
}

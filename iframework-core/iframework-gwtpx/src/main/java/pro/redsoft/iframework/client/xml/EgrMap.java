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
package pro.redsoft.iframework.client.xml;

import java.util.ArrayList;

/**
 * EgrMap.
 * 
 * @author SIgnatovich, Neo
 * @param <T>
 *          Kode Form
 * @param <K>
 *          RBean
 * @param <V>
 *          Presenter
 */
public class EgrMap<T extends Number, K extends RBean, V extends FormPresenter<K>> {

  private ArrayList<T>      formCodes;
  private ArrayList<K>      beans;
  private ArrayList<V>      presenters;
  private ArrayList<String> beanNames;
  private ArrayList<String> presenterNames;

  public EgrMap() {
    formCodes = new ArrayList<T>();
    beans = new ArrayList<K>();
    presenters = new ArrayList<V>();
    beanNames = new ArrayList<String>();
    presenterNames = new ArrayList<String>();
  }

  public K getBeanByKod(T key) {
    Integer ind = formCodes.indexOf(key);
    return beans.get(ind);
  }

  public K getBeanByName(String beanName) {
    Integer ind = beanNames.indexOf(beanName);
    return beans.get(ind);
  }

  public K getBeanByPresenter(V presenter) {
    Integer ind = presenters.indexOf(presenter);
    return beans.get(ind);
  }

  public T getKodByBean(K bean) {
    Integer ind = beans.indexOf(bean);
    return formCodes.get(ind);
  }

  public T getKodByPresenter(V presenter) {
    Integer ind = presenters.indexOf(presenter);
    return formCodes.get(ind);
  }

  public V getPresenterByBean(K bean) {
    Integer ind = beans.indexOf(bean);
    return presenters.get(ind);
  }

  public V getPresenterByKod(T key) {
    Integer ind = formCodes.indexOf(key);
    return -1 == ind ? null : presenters.get(ind);
  }

  public String getPresenterNameByKod(T key) {
    Integer ind = formCodes.indexOf(key);
    return presenterNames.get(ind);
  }

  private boolean hasBean(K bean) {
    return beans.contains(bean);
  }

  private boolean hasBean(String beanName) {
    return beanNames.contains(beanName);
  }

  private boolean hasPresenter(FormPresenter<RBean> presenter) {
    return presenters.contains(presenter);
  }

  @SuppressWarnings("unchecked")
  public void put(T key, K bean, V presenter, String namePresenter) {
    if (presenter != null && hasPresenter((FormPresenter<RBean>) presenter)) {
      return;
    }

    if (presenter == null && hasBean(bean.getSimpleName().toUpperCase())) {
      return;
    }

    if ((hasPresenter((FormPresenter<RBean>) presenter) || presenter == null) && hasBean(bean)) {
      return;
    }

    // if (key != null && presenter != null) {
    // System.out.println("Performing PUT operation in EgrMap: " + "key = " + key + " bean = "
    // + bean.getSimpleName() + " presenter = " + presenter.getSimpleName());
    // }
    // else {
    // System.out.println("Performing PUT operation in EgrMap: " + "bean name = " + bean.getSimpleName());
    // }

    formCodes.add(key);
    beans.add(bean);
    presenters.add(presenter);
    beanNames.add(bean.getSimpleName().toUpperCase());
    presenterNames.add(namePresenter);
  }

}

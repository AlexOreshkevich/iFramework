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

/**
 * EgrFactory.
 * 
 * @author alex oreshkevich
 */
@SuppressWarnings("unchecked")
public class EgrFactory {

  private static EgrFactory factory = new EgrFactory();

  private static boolean    hasInitialized;

  @SuppressWarnings("unused")
  private static <T extends RBean, V extends FormPresenter<T>> void easyPut(V presenter,
      String namePresenter) {
    EgrFactory
        .get()
        .getEgrMap()
        .put(presenter.getFormCode(), presenter.getBean(), (FormPresenter<RBean>) presenter,
            namePresenter);
  }

  public static EgrFactory get() {
    if (!hasInitialized && (hasInitialized = true)) {
      initialize();
    }
    return factory;
  }

  private static void initialize() {
    // easyPut(new AnPresenter(new AnBean()), "Согласование наименования");
    // easyPut(new JurRegChangesPresenter(new JurRegChangesBean()),
    // "Регистрация изменений в коммерческой организации");
    // easyPut(new JurRegPresenter(new JurRegBean()), "Регистрация ЮЛ");
    // easyPut(new IpRegPresenter(new IpRegBean()), "Регистрация ИП");
    // easyPut(new IpRegChangesPresenter(new IpRegChangesBean()), "Регистрация изменений ИП");
    // easyPut(new IpDestroyPresenter(new IpDestroyBean()), "Прекращение деятельности ИП");
    // easyPut(new RestoreIpPresenter(new RestoreIpBean()), "Восстановление права ИП");
    // easyPut(new NotifIpDestroyPresenter(new NotifIpDestroyBean()),
    // "Уведомление о завершении процесса прекращения деятельности ИП");
    // easyPut(new JurDestroyPresenter(new JurDestroyBean()), "Ликвидация ЮЛ");
    // easyPut(new JurAddressChangePresenter(new
    // JurChangeOfAddressBean()),"Уведомление об изменении местонахождения ЮЛ");
    // easyPut(new JurDirectorChangePresenter(new JurChangeOfDirectorBean()),
    // "Уведомление о назначении(замене) руководителя");
    // easyPut(new NJurRegPresenter(new NJurRegBean()),
    // "Заявление о государственной регистрации некоммерческой организации");
    // easyPut(new NJurRegChangesPresenter(new NJurRegChangesBean()),
    // "Регистрация изменений в некоммерческой организации");
    // new Founder();
    // new Successor();
    // new Predecessor();
    // new CertificateOfNames();
    // new BusinessType();
    // new BusinessTypeLic();
    // new Warrant();
    // new Names();
    // new Subdivision();
    // new UserInfo();
    // new UserInfoC();
    // new Payment();
  }

  private final EgrMap<Integer, RBean, FormPresenter<RBean>> egrMap;
  {
    egrMap = new EgrMap<Integer, RBean, FormPresenter<RBean>>();
  }

  private EgrFactory() {
  }

  /**
   * Creates any Presenter < T > type for null or existing bean < V > type
   * 
   * @param <T>
   * @param <V>
   * @param name
   * @param userInfo
   * @param bean
   * @param eventBus
   * @param rpcService
   * @return
   * @exception NullPointerException
   */
  // public <T extends FormPresenter, V extends RBean> T createPresenter(String name, final UserInfo userInfo,
  // V bean, CommonServiceAsync rpcService, HandlerManager eventBus) throws NullPointerException {
  //
  // T presenter = null;
  // if (name.equals("IpRegForm")) {
  // presenter = (T) new IpRegPresenter((IpRegBean) (bean != null ? bean : new IpRegBean()));
  // }
  // else if (name.equals("AnForm")) {
  // presenter = (T) new AnPresenter((AnBean) (bean != null ? bean : new AnBean()));
  // }
  // else if (name.equals("JurRegForm")) {
  // presenter = (T) new JurRegPresenter((JurRegBean) (bean != null ? bean : new JurRegBean()));
  // }
  // else if (name.equals("NJurRegForm")) {
  // presenter = (T) new NJurRegPresenter((NJurRegBean) (bean != null ? bean : new NJurRegBean()));
  // }
  // else if (name.equals("RestoreIpForm")) {
  // presenter = (T) new RestoreIpPresenter((RestoreIpBean) (bean != null ? bean : new RestoreIpBean()));
  // }
  // else if (name.equals("JurRegChangesForm")) {
  // presenter = (T) new JurRegChangesPresenter((JurRegChangesBean) (bean != null ? bean
  // : new JurRegChangesBean()));
  // }
  // else if (name.equals("NJurRegChangesForm")) {
  // presenter = (T) new NJurRegChangesPresenter((NJurRegChangesBean) (bean != null ? bean
  // : new NJurRegChangesBean()));
  // }
  // else if (name.equals("JurChangeOfAddressForm")) {
  // presenter = (T) new JurAddressChangePresenter((JurChangeOfAddressBean) (bean != null ? bean
  // : new JurChangeOfAddressBean()));
  // }
  // else if (name.equals("JurChangeOfDirectorForm")) {
  // presenter = (T) new JurDirectorChangePresenter((JurChangeOfDirectorBean) (bean != null ? bean
  // : new JurChangeOfDirectorBean()));
  // }
  // else if (name.equals("JurDestroyForm")) {
  // presenter = (T) new JurDestroyPresenter((JurDestroyBean) (bean != null ? bean : new JurDestroyBean()));
  // }
  // else if (name.equals("JurExcludeForm")) {
  // presenter = (T) new JurExcludePresenter((JurExcludeBean) (bean != null ? bean : new JurExcludeBean()));
  // }
  // else if (name.equals("IpRegChangesForm")) {
  // presenter = (T) new IpRegChangesPresenter((IpRegChangesBean) (bean != null ? bean
  // : new IpRegChangesBean()));
  // }
  // else if (name.equals("IpDestroyForm")) {
  // presenter = (T) new IpDestroyPresenter((IpDestroyBean) (bean != null ? bean : new IpDestroyBean()));
  // }
  // else if (name.equals("NotifIpDestroyForm")) {
  // presenter = (T) new NotifIpDestroyPresenter((NotifIpDestroyBean) (bean != null ? bean
  // : new NotifIpDestroyBean()));
  // }
  // else if (name.equals("SearchUlOrIpForm")) {
  // presenter = (T) new SearchUlOrIpPresenter((AnBean) (bean != null ? bean : new AnBean()));
  // }
  //
  // if (presenter != null) {
  // presenter.setUserInfo(userInfo);
  // presenter.setCommonService(rpcService);
  // presenter.setEventBus(eventBus);
  //
  // return presenter;
  // }
  //
  // return null;
  // }

  /**
   * @return the egrmap
   */
  public EgrMap<Integer, RBean, FormPresenter<RBean>> getEgrMap() {
    return egrMap;
  }

}

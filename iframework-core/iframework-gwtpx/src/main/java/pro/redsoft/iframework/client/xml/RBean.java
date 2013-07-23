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
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.ProcessingInstruction;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

/**
 * Standardized Abstract Bean Incapsulator for EGR Portal.
 * 
 * @author alex oreshkevich
 */
@StandardClassDescriptor(
    author = "Neo",
    date = "12/11/10",
    currentRevision = 2,
    reviewers = { "" },
    lastModified = "",
    lastModifiedBy = "")
public abstract class RBean extends RClass implements CreatableBean, IsSerializable {

  /** DB Key. */
  private Long                nwp07001 = -1L;

  /** RBeanFactory. */
  private static RBeanFactory factory  = new RBeanFactory();

  public static RBean createRBean(String xml) {

    RBean newBean = null;

    try {
      // creates new Doc from XML
      Document doc = XMLParser.parse(xml);
      // creates Rbean from RootElement
      Element root = doc.getDocumentElement();

      newBean = (RBean) RBean.getFactory().getClassByName(root.getAttribute(RField.JAVA_CLASS_ID))
          .newInstance();

      NodeList nodeList = root.getChildNodes();
      xmlToObj(newBean, nodeList);
    }
    catch (DOMParseException e) {
      e.printStackTrace();
      if (Window.confirm("<CORE> DOMParseException. Запустить код восстановления XML-документа?")) {
        try {
          // calculate new xml
          int ind = xml.indexOf(">");
          xml = xml.substring(ind + 1);
          // System.out.println("\n\n NewXML = \n\n" + xml); // creates new Doc
          // from XML
          Document doc = XMLParser.parse(xml);
          // creates Rbean from RootElement
          Element root = doc.getDocumentElement();
          newBean = RBean.getFactory().getClassByName(root.getAttribute(RField.JAVA_CLASS_ID));
          NodeList nodeList = root.getChildNodes();
          xmlToObj(newBean, nodeList);
        }
        catch (Exception e1) {
          Window.alert("<CORE> Irresolvable exception. " + e1.getMessage()
              + "\n Будет открыта пустая форма.");
          e1.printStackTrace();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      Window.alert("Ошибка чтения документа из Db");
    }
    return newBean;
  }

  public static RBeanFactory getFactory() {
    return factory;
  }

  public static void xmlToObj(RBean bean, NodeList nodeList) {
    for (int i = 0; i < nodeList.getLength(); i++) {
      bean.rfieldList.get(i).loadXml((Element) nodeList.item(i));
    }
  }

  public static void xmlToObj2(RBean bean, NodeList nodeList) {

    for (int j = 0; j < bean.rfieldList.size(); j++) {
      for (int i = 0; i < nodeList.getLength(); i++) {
        if (bean.rfieldList.get(j).name.equalsIgnoreCase(nodeList.item(i).getNodeName())) {
          Element child = (Element) nodeList.item(i);
          bean.rfieldList.get(j).loadXml(child);
        }
      }
    }
  }

  /**
   * Incapsulates array of simple fields in bean.
   */
  ArrayList<RField<?>> rfieldList = new ArrayList<RField<?>>();

  /** Registration for each bean. */
  public RBean() {
    factory.registerBean(this);
  }

  /**
   * Creates XML from Bean.
   * 
   * @return Document XML
   */
  public Document buildXML() {
    Document doc = XMLParser.createDocument();
    ProcessingInstruction procInst = doc.createProcessingInstruction("xml",
        "version=\"1.0\" encoding=\"UTF-8\"");
    ProcessingInstruction procInst1 = doc.createProcessingInstruction("xml-stylesheet",
        "type=\"text/xsl\" href=\"default.xsl\"");

    doc.appendChild(procInst);
    doc.appendChild(procInst1);

    Element root = doc.createElement(getSimpleName());
    root.setAttribute(RField.JAVA_CLASS_ID, getSimpleName());
    doc.appendChild(root);
    objToXml(doc, root);
    return doc;
  }

  /**
   * Clear all class members.
   */
  public void clearRFields() {
    for (RField<?> rfield : rfieldList) {
      rfield.clearRField();
    }
  }

  /**
   * Creates new field instance. <br/>
   * 
   * @param <T>
   * @param name
   * @param value
   * @return
   * @throws NullPointerException
   */
  public <T> RField<T> createField(String name, T value) throws NullPointerException {

    RField<T> newField = new RField<T>(name, value);
    rfieldList.add(newField);

    return newField;
  }

  /**
   * Creates new field instance. <br/>
   * 
   * @param <T>
   * @param name
   * @param value
   * @param dataTypes
   * @return
   * @throws NullPointerException
   */
  public <T> RField<T> createField(String name, T value, DataTypes dataTypes)
      throws NullPointerException {

    RField<T> newField = new RField<T>(name, value, dataTypes);
    rfieldList.add(newField);

    return newField;
  }

  /**
   * Creates new field instance. <br/>
   * 
   * @param <T>
   * @param name
   * @param value
   * @param dataTypes
   * @param b
   * @return
   * @throws NullPointerException
   */
  public <T> RField<T> createField(String name, T value, DataTypes dataTypes, byte b)
      throws NullPointerException {

    RField<T> newField = new RField<T>(name, value, dataTypes);
    rfieldList.add(newField);

    return newField;
  }

  /**
   * Get's an array of all simple fields (RField<?>) names.
   * 
   * @return ArrayList of all simple field names
   * @throws NullPointerException
   *           if fields doesn't exist
   */
  public ArrayList<String> getFieldsNames() throws NullPointerException {

    ArrayList<String> f = new ArrayList<String>();

    if (rfieldList.isEmpty()) {
      return null;
    }

    for (RField<?> field : rfieldList) {
      f.add(field.name);
    }

    return f;
  }

  /**
   * Get's an array of all simple fields (RField<?>) types.
   * 
   * @return ArrayList of all simple field types
   * @throws NullPointerException
   *           if fields doesn't exist
   */
  public ArrayList<String> getFieldsTypes() throws NullPointerException {

    ArrayList<String> f = new ArrayList<String>();

    if (rfieldList.isEmpty()) {
      return null;
    }
    else {
      for (RField<?> field : rfieldList) {
        f.add(field.value.getClass().getName().toString().substring(10));
      }
    }

    return f;
  }

  /**
   * Get's an array of all simple fields (RField<?>) values.
   * 
   * @return ArrayList of all simple field values
   * @throws NullPointerException
   *           if fields doesn't exist
   */
  public ArrayList<String> getFieldsValues() throws NullPointerException {

    ArrayList<String> f = new ArrayList<String>();

    if (rfieldList.isEmpty()) {
      return null;
    }

    for (RField<?> field : rfieldList) {
      f.add(field.value instanceof Date ? DateTimeFormat.getFormat("dd.MM.yyyy").format(
          (Date) field.value) : field.value.toString());
    }

    return f;
  }

  public Long getNwp07001() {
    return nwp07001;
  }

  /** Used by RBeanFactory. */
  @Override
  public abstract CreatableBean newInstance();

  public void objToXml(Document doc, Element root) {
    for (int i = 0; i < rfieldList.size(); i++) {
      rfieldList.get(i).toXml(doc, root);
    }
  }

  /**
   * Use this setter instead default setValue(T value) instruction.
   * 
   * @param <T>
   *          type of the field value
   * @param field
   *          field that contains T value
   * @param value
   *          value to set
   */
  public <T> void setField(RField<T> field, T value) {
    field.value = value;
  }

  public void setNwp07001(Long nwp07001) {
    this.nwp07001 = nwp07001;
  }
}

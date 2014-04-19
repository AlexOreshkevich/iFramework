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
package pro.redsoft.iframework.client.xml;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * Defines standardized set of operations with fields.
 * 
 * @author alex oreshkevich
 * @param <T>
 *          field type (any Class)
 */
@StandardClassDescriptor(
    author = "Neo",
    date = "11/11/10",
    currentRevision = 2,
    reviewers = { "" },
    lastModified = "",
    lastModifiedBy = "")
@SuppressWarnings("unchecked")
public class RField<T> extends RClass {

  static final String JAVA_CLASS_ID = "javaClassId";
  static final String TYPE_OBJECT   = "typeobject";

  public static String getDateToString(Date date) {
    String str;
    try {
      str = DateTimeFormat.getFormat("dd.MM.yyyy").format(date);
    }
    catch (Exception e) {
      str = "";
    }
    return str;
  }

  T         value;

  /** Это синоним. */
  String    name;

  String    javaClassId;

  DataTypes dateType = null;

  RField(String name, T value) {
    this.name = name;
    this.value = value;
    dateType = DataTypes.tField;
    javaClassId = value instanceof RClass ? ((RClass) value).getSimpleName() : value.getClass()
        .getName().substring(value.getClass().getName().lastIndexOf(".") + 1);
  }

  RField(String name, T value, DataTypes dataTypes) {
    this.name = name;
    this.value = value;
    this.dateType = dataTypes;
    javaClassId = value instanceof RClass ? ((RClass) value).getSimpleName() : value.getClass()
        .getName().substring(value.getClass().getName().lastIndexOf(".") + 1);
  }

  public RField(String name, T value, DataTypes dataTypes, RBean thisBean) {
    this.name = name;
    this.value = value;
    this.dateType = dataTypes;
    javaClassId = value instanceof RClass ? ((RClass) value).getSimpleName() : value.getClass()
        .getName().substring(value.getClass().getName().lastIndexOf(".") + 1);
    thisBean.rfieldList.add(this);
  }

  public void
      addNode(Document doc, Element parentNode, String nodeName, String value, String javaClassId) {
    Element newElem = doc.createElement(nodeName);
    newElem.setAttribute(JAVA_CLASS_ID, javaClassId);
    newElem.setAttribute(TYPE_OBJECT, dateType.toString());
    Node node = doc.createTextNode(value);
    newElem.appendChild(node);
    parentNode.appendChild(newElem);
  }

  public void addNode(Document doc, Element parentNode, String nodeName, String value,
      String javaClassId, DataTypes dataTypes) {
    Element newElem = doc.createElement(nodeName);
    newElem.setAttribute(JAVA_CLASS_ID, javaClassId);
    newElem.setAttribute(TYPE_OBJECT, dataTypes.toString());
    Node node = doc.createTextNode(value);
    newElem.appendChild(node);
    parentNode.appendChild(newElem);
  }

  public void clearRField() {
    switch (dateType) {

      case tField:
        if (value instanceof String) {
          setValue("");
        }
        else if (value instanceof Integer) {
          setValue(-1);
        }
        else if (value instanceof Long) {
          setValue(-1L);
        }
        else if (value instanceof Double) {
          setValue(-1D);
        }
        else if (value instanceof Float) {
          setValue(-1F);
        }
        break;

      case tObject:
        ((RBean) value).clearRFields();
        break;

      case tListField:
        ArrayList<?> arraySimpFld = (ArrayList<?>) value;
        arraySimpFld.clear();
        break;

      case tListObject:
        ArrayList<?> arrayComplFld = (ArrayList<?>) value;
        arrayComplFld.clear();
        break;

      default:
        break;
    }
  }

  // getName
  public String getCustomName() {
    return name;
  }

  /**
   * @return the dateType
   */
  public DataTypes getDateType() {
    return dateType;
  }

  private String getShortClassName(String name) {
    return name.substring(name.lastIndexOf(".") + 1);
  }

  private int getType(String type) {

    if (type.equalsIgnoreCase("Byte")) {
      return 0;
    }
    else if (type.equalsIgnoreCase("Short")) {
      return 1;
    }
    else if (type.equalsIgnoreCase("Integer")) {
      return 2;
    }
    else if (type.equalsIgnoreCase("Long")) {
      return 3;
    }
    else if (type.equalsIgnoreCase("Float")) {
      return 4;
    }
    else if (type.equalsIgnoreCase("Double")) {
      return 5;
    }
    else if (type.equalsIgnoreCase("Date")) {
      return 6;
    }
    else if (type.equalsIgnoreCase("String")) {
      return 7;
    }
    else if (type.equalsIgnoreCase("Boolean")) {
      return 8;
    }

    return -1;
  }

  // getValue
  public T getValue() {
    return value;
  }

  public void loadXml(Element elem) {
    String sJavaClass = elem.getAttribute(JAVA_CLASS_ID);
    String sDataTypes = elem.getAttribute(TYPE_OBJECT);
    DataTypes typeObj = DataTypes.valueOf(sDataTypes);
    switch (typeObj) {
      case tField:
        if (elem.hasChildNodes()) {
          setSingleFieldValue(elem.getFirstChild().getNodeValue().toString(), sJavaClass);
        }
        break;

      case tListField:
        if (elem.hasChildNodes()) {
          ArrayList<T> array = (ArrayList<T>) value;
          for (int j = 0; j < elem.getChildNodes().getLength(); j++) {
            String javaClass1 = ((Element) elem.getChildNodes().item(j)).getAttribute(JAVA_CLASS_ID);
            array.add(parseFromString(elem.getChildNodes().item(j).getFirstChild().getNodeValue(),
                javaClass1));
          }
        }
        break;

      case tObject:
        RBean.xmlToObj((RBean) this.value, elem.getChildNodes());
        break;

      case tListObject:
        if (elem.hasChildNodes()) {
          ArrayList<T> beanArray = (ArrayList<T>) value;
          beanArray.clear();
          for (int j = 0; j < elem.getChildNodes().getLength(); j++) {
            String sJavaClassInList = ((Element) elem.getChildNodes().item(j))
                .getAttribute(JAVA_CLASS_ID);
            RBean beanFromFactory = (RBean) RBean.getFactory().getClassByName(sJavaClassInList)
                .newInstance();

            try {
              RBean.xmlToObj(beanFromFactory, elem.getChildNodes().item(j).getChildNodes());
              beanArray.add((T) beanFromFactory);
            }
            catch (Exception e) {
              e.getMessage();
            }
          }
        }
        break;

      default:
        break;
    }
  }

  T parseFromString(String value, String type) {

    switch (getType(type)) {
      case -1:
        Window.alert("Failed to get Type from newValue | Error in function "
            + "<T> void setValueInField(RField<?> field, T newValue)");
        break;

      case 0:
        return (T) Byte.valueOf(value);

      case 1:
        return (T) Short.valueOf(value);

      case 2:
        return (T) Integer.valueOf(value);

      case 3:
        return (T) Long.valueOf(value);
      case 4:
        return (T) Float.valueOf(value);

      case 5:
        return (T) Double.valueOf(value);

      case 6:
        return (T) DateTimeFormat.getFormat("dd.MM.yyyy").parse(value);

      case 7:
        return (T) value.toString();

      case 8:
        return (T) Boolean.valueOf(value.toString());

      default:
        break;
    }
    return null;
  }

  // setName
  void setCustomName(String name) {
    this.name = name;
  }

  /**
   * @param dateType
   *          the dateType to set
   */
  public void setDateType(DataTypes dateType) {
    this.dateType = dateType;
  }

  void setSingleFieldValue(String value, String type) {
    try {
      switch (getType(type)) {

        case -1:
          Window.alert("Failed to get Type from newValue | Error in function "
              + "<T> void setValueInField(RField<?> field, T newValue)");
          break;

        case 0:
          setValue(Byte.valueOf(value));
          break;

        case 1:
          setValue(Short.valueOf(value));
          break;

        case 2:
          setValue(Integer.valueOf(value));
          break;

        case 3:
          setValue(Long.valueOf(value));
          break;

        case 4:
          setValue(Float.valueOf(value.replace(",", ".")));
          break;

        case 5:
          setValue(Double.valueOf(value));
          break;

        case 6:
          setValue(DateTimeFormat.getFormat("dd.MM.yyyy").parse(value));
          break;

        case 7:
          setValue(value.toString());
          break;

        case 8:
          setValue(Boolean.valueOf(value.toString()));
          break;

        default:
          break;
      }
    }
    catch (NumberFormatException e) {
      Window.alert("Failed to get Type from newValue | Error in function "
          + "<T> void setValueInField(RField<?> field, T newValue)\n" + e.getLocalizedMessage());
    }
  }

  public void setValue(Boolean valueOf) {
    value = (T) valueOf;
  }

  public void setValue(Byte b) {
    value = (T) b;
  }

  public void setValue(Date parse) {
    value = (T) parse;
  }

  public void setValue(Double valueOf) {
    value = (T) valueOf;
  }

  public void setValue(Float valueOf) {
    value = (T) valueOf;
  }

  public void setValue(Integer valueOf) {
    value = (T) valueOf;
  }

  public void setValue(Long valueOf) {
    value = (T) valueOf;
  }

  public void setValue(Short valueOf) {
    value = (T) valueOf;
  }

  public void setValue(String string) {
    value = (T) string;
  }

  // setValue
  void setValue(T newValue) {
    this.value = newValue;
  }

  public void toXml(Document doc, Element parentElement) {

    Element elem = doc.createElement(name);

    switch (dateType) {

      case tField:
        if (value instanceof Date) {
          addNode(doc, parentElement, name, getDateToString((Date) value), javaClassId);
        }
        else {
          try {
            addNode(doc, parentElement, name, value != null ? value.toString() : "", javaClassId);
          }
          catch (Exception e) {
            throw new RuntimeException("Ошибка    " + name);
          }
        }
        break;

      case tListField:
        elem.setAttribute(JAVA_CLASS_ID, getShortClassName(value.getClass().getName()));
        elem.setAttribute(TYPE_OBJECT, dateType.toString());
        parentElement.appendChild(elem);
        ArrayList<T> array = (ArrayList<T>) value;
        for (int i = 0; i < array.size(); i++) {
          addNode(doc, elem, getShortClassName(array.get(i).getClass().getName()), array.get(i)
              .toString(), getShortClassName(array.get(i).getClass().getName()), DataTypes.tField);
        }
        break;

      case tObject:
        elem.setAttribute(JAVA_CLASS_ID, ((RBean) value).getSimpleName());
        elem.setAttribute(TYPE_OBJECT, dateType.toString());
        parentElement.appendChild(elem);
        ((RBean) value).objToXml(doc, elem);
        break;

      case tListObject:

        ArrayList<T> beanArray = (ArrayList<T>) value;
        elem.setAttribute(JAVA_CLASS_ID, getShortClassName(beanArray.getClass().getName()));
        elem.setAttribute(TYPE_OBJECT, dateType.toString());
        parentElement.appendChild(elem);

        for (int i = 0; i < beanArray.size(); i++) {

          RField<T> newItem = new RField<T>(((RBean) beanArray.get(i)).rclassName, beanArray.get(i),
              DataTypes.tObject);

          Element elem1 = doc.createElement(newItem.name);
          elem1.setAttribute(JAVA_CLASS_ID, ((RBean) newItem.value).getSimpleName());
          elem1.setAttribute(TYPE_OBJECT, newItem.dateType.toString());
          elem.appendChild(elem1);

          ((RBean) newItem.value).objToXml(doc, elem1);
        }
        break;

      default:
        throw new RuntimeException();
    }
  }
}

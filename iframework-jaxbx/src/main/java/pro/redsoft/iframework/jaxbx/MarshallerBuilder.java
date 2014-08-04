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
package pro.redsoft.iframework.jaxbx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 * MarshallerBuilder Реализация шаблона Builder (GoF) для объектов Marshaller
 *
 * @author oreshkevich
 * @version $Id: $Id
 */
public class MarshallerBuilder {

  /**
   * Создает маршаллер для класса любого типа
   * http://jaxb.java.net/tutorial/section_4_5-Calling-marshal.html#Calling%20marshal
   *
   * @param <T>
   *          тип целевого класса
   * @param clazz
   *          сущность объекта, тип которого - целевой класс
   * @return Marshaller
   * @throws javax.xml.bind.JAXBException
   *           ошибки
   */
  public <T> Marshaller createMarshaller(Class<T> clazz) throws JAXBException {
    return createMarshaller(clazz, "", true, "", "", false);
  }

  /**
   *
   *
   * @param <T>
   *
   * @param clazz
   *
   * @param encoding
   *
   * @param formattedOutput
   *
   * @param schemaLocation
   *
   * @param noNamespaceSchemaLocation
   *
   * @return Marshaller
   * @throws JAXBException
   *
   */

  /**
   * Создает маршаллер для класса любого типа Включена поддержка всех возможных свойств согласно
   * http://download.oracle.com/javase/6/docs/api/javax/xml/bind/Marshaller.html#supportedProps
   *
   * @param clazz сущность объекта, тип которого - целевой класс
   * @param encoding кодировка (по умолчанию utf-8)
   * @param formattedOutput форматированный вывод (по умолчанию false)
   * @param schemaLocation установка атрибута xsi:schemaLocation
   * @param noNamespaceSchemaLocation установка атрибута xsi:noNamespaceSchemaLocation
   * @param fragment будут ли генерироваться document level events
   * @param <T> тип целевого класса
   * @return целевой класс
   * @throws javax.xml.bind.JAXBException ошибки
   */
  public <T> Marshaller createMarshaller(Class<T> clazz, String encoding, boolean formattedOutput,
      String schemaLocation, String noNamespaceSchemaLocation, boolean fragment) throws JAXBException {

    // инициализация контекста
    JAXBContext context = JAXBContext.newInstance(clazz);

    // создание маршаллера
    Marshaller marshaller = context.createMarshaller();

    // кодировка (по умолчанию utf-8)
    setProperty(marshaller, Marshaller.JAXB_ENCODING, encoding);

    // форматированный вывод (по умолчанию false)
    setProperty(marshaller, Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);

    // размещение схемы
    // this property allows the client application to specify an xsi:schemaLocation attribute in the generated
    // XML data.
    // <xsi:Adhoc xmlns:xsi="http://www.prognoz.ru/Adhoc" version="0.83">
    setProperty(marshaller, Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);

    // установка атрибута xsi:noNamespaceSchemaLocation
    setProperty(marshaller, Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, noNamespaceSchemaLocation);

    // будут ли генерироваться document level events
    setProperty(marshaller, Marshaller.JAXB_FRAGMENT, fragment);

    return marshaller;
  }

  /**
   * Устанавливает значение свойства, если оно не пустое
   * 
   * @param m marshaller
   * @param propertyName propertyName
   * @param propertyValue propertyValue
   * @throws PropertyException PropertyException
   */
  private static void setProperty(Marshaller m, String propertyName, Object propertyValue)
      throws PropertyException {
    if (!(propertyValue instanceof String) || propertyValue instanceof String
        && !((String) propertyValue).isEmpty()) {
      m.setProperty(propertyName, propertyValue);
    }
  }
}

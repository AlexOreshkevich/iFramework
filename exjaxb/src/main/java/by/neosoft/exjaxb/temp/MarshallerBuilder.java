/*
 * Copyright 2011-2012 InterTrust LTD. All rights reserved. Visit our web-site: www.intertrust.ru.
 */
package by.neosoft.exjaxb.temp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 * MarshallerBuilder <br/>
 * Реализация шаблона Builder (GoF) для объектов Marshaller
 * 
 * @author alex oreshkevich
 */
class MarshallerBuilder {

  /** Если поставщик расширений не JAXB-RI, то надо поменять эту пропертю */
  public final static String NAMESPACE_PREFIX_PROP     = "com.sun.xml.bind.namespacePrefixMapper";

  Object                     namespacePrefixMapperImpl = null;

  MarshallerBuilder() {
  }

  /**
   * Создание маршаллеров может быть расширено пространствами имен
   * 
   * @param service
   */
  MarshallerBuilder(AbstractJAXBParser<?> service) {
    namespacePrefixMapperImpl = service.getNamespacePrefixMapperImpl();
  }

  /**
   * Создает маршаллер для класса любого типа
   * http://jaxb.java.net/tutorial/section_4_5-Calling-marshal.html#Calling%20marshal
   * 
   * @param <T>
   *          тип целевого класса
   * @param clazz
   *          сущность объекта, тип которого - целевой класс
   * @return Marshaller
   * @throws JAXBException
   *           ошибки
   */
  public <T> Marshaller createMarshaller(Class<T> clazz) throws JAXBException {
    return createMarshaller(clazz, "", true, "", "", false);
  }

  /**
   * Создает маршаллер для класса любого типа <br/>
   * Включена поддержка всех возможных свойств согласно
   * http://download.oracle.com/javase/6/docs/api/javax/xml/bind/Marshaller.html#supportedProps
   * 
   * @param <T>
   *          тип целевого класса
   * @param clazz
   *          сущность объекта, тип которого - целевой класс
   * @param encoding
   *          кодировка (по умолчанию utf-8)
   * @param formattedOutput
   *          форматированный вывод (по умолчанию false)
   * @param schemaLocation
   *          установка атрибута xsi:schemaLocation
   * @param noNamespaceSchemaLocation
   *          установка атрибута xsi:noNamespaceSchemaLocation
   * @return Marshaller
   * @throws JAXBException
   *           ошибки
   */
  public <T> Marshaller createMarshaller(Class<T> clazz, String encoding, boolean formattedOutput,
      String schemaLocation, String noNamespaceSchemaLocation, boolean fragment) throws JAXBException {

    // инициализация контекста
    JAXBContext context = JAXBContext.newInstance(clazz);

    // создание маршаллера
    Marshaller marshaller = context.createMarshaller();

    // кодировка (по умолчанию utf-8)
    if (!encoding.isEmpty()) {
      marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
    }

    // форматированный вывод (по умолчанию false)
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);

    // размещение схемы
    // this property allows the client application to specify an xsi:schemaLocation attribute in the generated
    // XML data.
    // <xsi:Adhoc xmlns:xsi="http://www.prognoz.ru/Adhoc" version="0.83">
    if (!schemaLocation.isEmpty()) {
      marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
    }

    // установка атрибута xsi:noNamespaceSchemaLocation
    if (!noNamespaceSchemaLocation.isEmpty()) {
      marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, noNamespaceSchemaLocation);
    }

    // будут ли генерироваться document level events
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);

    // namespace
    try {
      marshaller.setProperty(NAMESPACE_PREFIX_PROP, namespacePrefixMapperImpl);
    }
    catch (NullPointerException e) {
    }
    catch (PropertyException e) {
    }

    return marshaller;
  }
}

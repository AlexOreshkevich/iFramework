package by.redsoft.iframework.jaxbx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 * MarshallerBuilder Реализация шаблона Builder (GoF) для объектов Marshaller
 * 
 * @author oreshkevich
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
   * @throws JAXBException
   *           ошибки
   */
  public <T> Marshaller createMarshaller(Class<T> clazz) throws JAXBException {
    return createMarshaller(clazz, "", true, "", "", false);
  }

  /**
   * Создает маршаллер для класса любого типа Включена поддержка всех возможных свойств согласно
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
   * @param m
   * @param propertyName
   * @param propertyValue
   * @throws PropertyException
   */
  private static void setProperty(Marshaller m, String propertyName, Object propertyValue)
      throws PropertyException {
    if (!(propertyValue instanceof String) || propertyValue instanceof String
        && !((String) propertyValue).isEmpty()) {
      m.setProperty(propertyName, propertyValue);
    }
  }
}

package by.neosoft.exjaxb;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;

import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import by.neosoft.exjaxb.builder.MarshallerBuilder;
import by.neosoft.exjaxb.builder.UnmarshallerBuilder;
import by.neosoft.exjaxb.exception.DetailedJAXBException;
import by.neosoft.exjaxb.exception.ErrorCodes;
import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.namespace.NamespaceDecorator;
import by.neosoft.exjaxb.parser.ExJAXBParser;

/**
 * ExJAXB
 * 
 * Предоставляет серию обобщенных методов для работы с JAXB-компонентами
 * 
 * @param T
 *          тип целевого класса
 * @since jaxb 2.2.4u2
 * 
 * @author oreshkevich
 */
public class ExJAXB<T> {

  // используется для параметризации пространств имен при маршализации
  private NamespaceDecorator        nsDecorator;
  // билдеры являются постоянными
  private final MarshallerBuilder   marshallBuilder;
  private final UnmarshallerBuilder unmarshallBuilder;
  // парсер, связанный с используемыми преобразованиями
  private final ExJAXBParser<T>     exParser;

  /**
   * Карта пространств имен дополняется сведениями о пользовательских простанствах имен
   * 
   * @param decorator
   */
  public ExJAXB(ExJAXBParser<T> exParser) {
    nsDecorator = new NamespaceDecorator();
    marshallBuilder = new MarshallerBuilder(this);
    unmarshallBuilder = new UnmarshallerBuilder(this);
    this.exParser = exParser;

    HashMap<String, String> additionalMap = exParser.getNamespacePrefixMap();

    if (additionalMap != null) {
      nsDecorator.getNamespacePrefixMap().putAll(additionalMap);
    }
  }

  /**
   * Демаршализация xml-документа в виде строки. <br/>
   * Расширенная обработка исключительных ситуаций. Обобщен тип получаемого результата.
   * 
   * @param srcXml
   *          исходный документ одной строкой
   * @param srcXsd
   *          xsd-схема для валидации (можно null)
   * @param <T>
   *          тип целевого класса
   * @param srcBean
   *          объект целевого класса (.class)
   * @param isValidationEnabled
   *          разрешить валидацию
   * @param isSavingTempEnabled
   *          разрешить сохранение xml на файловую систему
   * 
   * @return T instance заполненный бин из xml
   * 
   * @throws InvalidAdhocXmlException
   */
  @SuppressWarnings("unchecked")
  public T unmarshall(String srcXml, Schema srcXsd, Class<T> srcBean, boolean isValidationEnabled,
      boolean isSavingTempEnabled) throws DetailedJAXBException {

    JAXBContext jc = null;
    Unmarshaller unmarshaller = null;

    // сохраняю файлик себе при отладке
    if (isSavingTempEnabled) {
      try {
        FileSystemUtils.saveTempFile(srcXml, "temp");
      }
      catch (Throwable e1) {
        throw new DetailedJAXBException(ErrorCodes.ERROR_SAVING_TEMP_FILE, e1.getMessage());
      }
    }

    // создаем Unmarshaller
    try {
      jc = JAXBContext.newInstance(srcBean);
      unmarshaller = jc.createUnmarshaller();
    }
    catch (JAXBException e) {
      throw new DetailedJAXBException(ErrorCodes.INIT_UNMARSHALLER_ERROR, e.getMessage());
    }

    // схема должна существовать
    if (null == srcXsd) {
      try {
        throw new DetailedJAXBException(ErrorCodes.WRONG_SCHEMA_LOCATION, "");
      }
      catch (DetailedJAXBException e) {
        System.err.println(e.getDetailedMessage());
      }
    }

    // передаем ему схему для валидации
    ValidationEventCollector vec = null;
    if (isValidationEnabled && srcXsd != null) {
      unmarshaller.setSchema(srcXsd);

      // трассировка ошибок валидации
      vec = new ValidationEventCollector();
      try {
        unmarshaller.setEventHandler(vec);
      }
      catch (JAXBException e1) {
        throw new DetailedJAXBException(ErrorCodes.VALIDATION_EVENT_COLLECTOR_ERR, e1.getMessage());
      }
    }

    // вызов unmarshal(): получить root из sourceXml
    Object source = null;
    boolean wasWrongXmlStructure = false;
    String wringXmlStructureMessage = "";

    try {
      if (unmarshaller != null) {
        source = unmarshaller.unmarshal(new StringReader(srcXml));
        return source instanceof JAXBElement<?> ? ((JAXBElement<T>) source).getValue() : (T) source;
      }
    }
    catch (JAXBException e) {

      // ошибка формирования xml документа
      if (e.getLinkedException() instanceof SAXParseException) {
        wasWrongXmlStructure = true;
        wringXmlStructureMessage += e.getLinkedException().getMessage() + "\n";
      }
      else {
        throw new DetailedJAXBException(ErrorCodes.UNMARSHALLER_FATAL_ERROR, e.getMessage());
      }
    }
    catch (ClassCastException castExc) {
      throw new DetailedJAXBException(ErrorCodes.WRONG_RECIEVER_DESC, source.getClass().getName() + "\n"
          + castExc.getMessage());
    } // сбор детальной информации
    // см. http://jaxb.java.net/tutorial/section_3_3-Validation.html#Validation
    finally {

      if (isValidationEnabled && vec != null && vec.hasEvents()) {
        for (ValidationEvent ve : vec.getEvents()) {

          ValidationEventLocator vel = ve.getLocator();

          // определение положения ошибки в xml-документе
          int line = vel.getLineNumber();
          int column = vel.getColumnNumber();

          wringXmlStructureMessage += "Локация ошибки: " + " строка " + line + " столбец " + column;
        }
      }

      // генерация исключения с детализированным сообщением
      if (wasWrongXmlStructure) {
        throw new DetailedJAXBException(ErrorCodes.WRONG_XML_STRUCTURE, "\n" + wringXmlStructureMessage
            + "\n");
      }
    }

    return null;
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj Все параметры по умолчанию
   * 
   * @param <T>
   *          тип целевого класса
   * @param targetObj
   *          бин (сущность целевого класса)
   * 
   * @return org.w3c.dom.Document
   * @throws JAXBException
   *           ошибки
   */
  public <T> org.w3c.dom.Document marshall(T targetObj, Document doc) throws JAXBException {
    Marshaller m = marshallBuilder.createMarshaller(targetObj.getClass());
    return marshall(m, targetObj, doc);
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj
   * 
   * @param <T>
   *          тип целевого класса
   * @param m
   *          Marshaller
   * @param targetObj
   *          бин (сущность целевого класса)
   * 
   * @return org.w3c.dom.Document
   * @throws JAXBException
   *           ошибки
   */
  public <T> org.w3c.dom.Document marshall(Marshaller m, T targetObj, Document doc) throws JAXBException {
    return marshall(m, targetObj, targetObj.getClass().getName(), "xsi", doc);
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj Имя рутового тега rootName
   * 
   * @param <T>
   * @param m
   * @param targetObj
   * @param rootName
   * @param doc
   *          создание документа без создания фабрики для вывода результатов маршаллера
   * 
   * @return
   * @throws JAXBException
   */
  public <T> org.w3c.dom.Document marshall(Marshaller m, T targetObj, String rootName, String prefix,
      Document doc) throws JAXBException {

    // преобразовать бин targetObj в документ doc
    JAXBElement<T> jbx = wrap(rootName, prefix, targetObj);
    m.marshal(jbx, doc);

    return doc;
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj Вывод в консоль
   * 
   * @param <T>
   * @param m
   * @param targetObj
   * @throws JAXBException
   */
  public <T> void marshall(Marshaller m, T targetObj) throws JAXBException {
    m.marshal(targetObj, System.out);
  }

  /**
   * Универсальный метод для обертки бинов (разрешает не дописывать @XmlRootElement)
   * 
   * http://jaxb.java.net/tutorial/section_4_5-Calling-marshal.html#Calling%20marshal
   * 
   * @param <T>
   * @param namespace
   * @param tagName
   * @param object
   * @return
   */
  @SuppressWarnings("unchecked")
  <T> JAXBElement<T> wrap(String tagName, String prefix, T object) {
    QName qtag = new QName(exParser.getNamespace(), tagName, prefix);
    JAXBElement<T> jbe = new JAXBElement<T>(qtag, (Class<T>) object.getClass(), object);
    return jbe;
  }

  /**
   * Ассоциированный с данным сервисом декоратор
   * 
   * @return
   */
  public NamespaceDecorator getNsDecorator() {
    return nsDecorator;
  }

  public MarshallerBuilder getMarshallBuilder() {
    return marshallBuilder;
  }

  public UnmarshallerBuilder getUnmarshallerBuilder() {
    return unmarshallBuilder;
  }
}

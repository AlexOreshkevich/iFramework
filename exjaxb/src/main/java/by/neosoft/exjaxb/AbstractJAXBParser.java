/**
 * 
 */
package by.neosoft.exjaxb;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import by.neosoft.exjaxb.builder.MarshallerBuilder;
import by.neosoft.exjaxb.exception.DetailedJAXBException;
import by.neosoft.exjaxb.exception.ErrorCodes;
import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.namespace.NamespaceDecorator;
import by.neosoft.exjaxb.parser.ExJAXBParser;

/**
 * AbstractJAXBParser
 * 
 * @author alex oreshkevich
 * 
 * @param <T>
 */
public abstract class AbstractJAXBParser<T extends Object> implements ExJAXBParser<T> {

  // используется для параметризации пространств имен при маршализации
  private NamespaceDecorator           nsDecorator;

  // билдеры являются постоянными
  private final MarshallerBuilder      marshallBuilder;

  private TransformerFactory           transformerFactory = TransformerFactory.newInstance();

  private DocumentBuilder              builder;

  private final DocumentBuilderFactory factory            = DocumentBuilderFactory.newInstance();

  public AbstractJAXBParser() {
    nsDecorator = new NamespaceDecorator();
    marshallBuilder = new MarshallerBuilder(nsDecorator);

    nsDecorator.getNamespacePrefixMap().putAll(getNamespacePrefixMap());
  }

  public Document getDocument() throws ParserConfigurationException {
    if (builder == null) {
      builder = factory.newDocumentBuilder();
    }
    return builder.newDocument();
  }

  /**
   * Преобразование Node в строку
   */
  public String nodeToString(Node node) throws TransformerException {
    StringWriter sw = new StringWriter();

    // описание преобразователя
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(new DOMSource(node), new StreamResult(sw));

    return sw.toString();
  }

  public Schema getSchema(File file) throws SAXException {
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema s = sf.newSchema(file);
    return s;
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
      throw new DetailedJAXBException(ErrorCodes.WRONG_SCHEMA_LOCATION, "");
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
  public org.w3c.dom.Document marshall(T targetObj, Document doc) throws JAXBException {
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
  public org.w3c.dom.Document marshall(Marshaller m, T targetObj, Document doc) throws JAXBException {
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
  public org.w3c.dom.Document marshall(Marshaller m, T targetObj, String rootName, String prefix, Document doc)
      throws JAXBException {

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
  public void marshall(Marshaller m, T targetObj) throws JAXBException {
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
  JAXBElement<T> wrap(String tagName, String prefix, T object) {
    QName qtag = new QName(getNamespace(), tagName, prefix);
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
}

/**
 * 
 */
package by.neosoft.exjaxb;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

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

/**
 * AbstractJAXBParser
 * 
 * @author alex oreshkevich
 * 
 * @param <T>
 */
public abstract class AbstractJAXBParser<T extends Object> {

  private DocumentBuilder              builder;

  protected final Class<T>             classType;

  private final DocumentBuilderFactory factory            = DocumentBuilderFactory.newInstance();

  private final MarshallerBuilder      marshallBuilder;

  private NamespaceDecorator           nsDecorator;

  private TransformerFactory           transformerFactory = TransformerFactory.newInstance();

  public AbstractJAXBParser(Class<T> classType) {
    this.classType = classType;
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

  public MarshallerBuilder getMarshallBuilder() {
    return marshallBuilder;
  }

  /**
   * Для корректного определения префикса необходимо определить пространство имен
   * 
   * @return
   */
  public abstract String getNamespace();

  /**
   * ExJAXBParser должен предоставить сведения о пользовательских простанствах имен Если возвращается null, то
   * используется карта по умолчанию
   * 
   * @return сведения о пользовательских простанствах имен (HashMap)
   */
  public abstract HashMap<String, String> getNamespacePrefixMap();

  /**
   * Ассоциированный с данным сервисом декоратор
   * 
   * @return
   */
  public NamespaceDecorator getNsDecorator() {
    return nsDecorator;
  }

  /**
   * getRootTagName
   * 
   * @return
   */
  public abstract String getRootTagName();

  public Schema getSchema(File file) throws SAXException {
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema s = sf.newSchema(file);
    return s;
  }

  /**
   * getSchemaInstancePrefix
   * 
   * @return
   */
  public abstract String getSchemaInstancePrefix();

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
   * @throws ParserConfigurationException
   */
  public org.w3c.dom.Document marshall(T targetObj) throws JAXBException, ParserConfigurationException {

    Marshaller m = marshallBuilder.createMarshaller(classType);
    Document doc = getDocument();

    // преобразовать бин targetObj в документ doc
    JAXBElement<T> jbx = wrap(getRootTagName(), getSchemaInstancePrefix(), targetObj);
    m.marshal(jbx, doc);

    return doc;
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
  public T unmarshall(String srcXml, Schema srcXsd, boolean isSavingTempEnabled) throws DetailedJAXBException {

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
      jc = JAXBContext.newInstance(classType);
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
    if (srcXsd != null) {
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

      if (vec != null && vec.hasEvents()) {
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
}

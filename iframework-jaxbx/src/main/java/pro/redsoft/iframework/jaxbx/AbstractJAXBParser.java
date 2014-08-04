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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * AbstractJAXBParser.
 *
 * @param <T> target class type
 * @author alex oreshkevich
 * @version $Id: $Id
 */
public abstract class AbstractJAXBParser<T extends Object> {

  private DocumentBuilder builder;

  final protected Class<T> classType;

  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  private final MarshallerBuilder marshallBuilder;

  private TransformerFactory transformerFactory = TransformerFactory.newInstance();

  /**
   * <p>Constructor for AbstractJAXBParser.</p>
   *
   * @param classType a {@link java.lang.Class} object.
   */
  public AbstractJAXBParser(Class<T> classType) {
    this.classType = classType;
    marshallBuilder = new MarshallerBuilder();
  }

  /**
   * <p>getDocument.</p>
   *
   * @return a {@link org.w3c.dom.Document} object.
   * @throws javax.xml.parsers.ParserConfigurationException if any.
   */
  public Document getDocument() throws ParserConfigurationException {
    if (builder == null) {
      builder = factory.newDocumentBuilder();
    }
    return builder.newDocument();
  }

  /**
   * <p>Getter for the field <code>marshallBuilder</code>.</p>
   *
   * @return a {@link pro.redsoft.iframework.jaxbx.MarshallerBuilder} object.
   */
  public MarshallerBuilder getMarshallBuilder() {
    return marshallBuilder;
  }

  /**
   * Для корректного определения префикса необходимо определить пространство
   * имен.
   *
   * @return namespace
   */
  public abstract String getNamespace();

  /**
   * ExJAXBParser должен предоставить сведения о пользовательских простанствах имен Если возвращается null, то
   * используется карта по умолчанию.
   *
   * @return сведения о пользовательских простанствах имен (HashMap)
   */
  public abstract HashMap<String, String> getNamespacePrefixMap();

  /**
   * getRootTagName.
   *
   * @return rootTagName
   */
  public abstract String getRootTagName();

  /**
   * <p>getSchema.</p>
   *
   * @param file a {@link java.io.File} object.
   * @return a {@link javax.xml.validation.Schema} object.
   * @throws org.xml.sax.SAXException if any.
   */
  public Schema getSchema(File file) throws SAXException {
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema s = sf.newSchema(file);
    return s;
  }

  /**
   * getSchemaInstancePrefix.
   *
   * @return schemaInstancePrefix
   */
  public abstract String getSchemaInstancePrefix();

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj.
   *
   * @param targetObj serializable object
   * @return org.w3c.dom.Document
   * @throws javax.xml.bind.JAXBException                   javax.xml.bind.JAXBException
   * @throws javax.xml.parsers.ParserConfigurationException javax.xml.parsers.ParserConfigurationException
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
   * Преобразование Node в строку.
   *
   * @param node source node
   * @return String representation
   * @throws javax.xml.transform.TransformerException exception
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
   * Демаршализация xml-документа в виде строки. Расширенная обработка
   * исключительных ситуаций. Обобщен тип получаемого результата.
   *
   * @param srcXml              исходный документ одной строкой
   * @param srcXsd              xsd-схема для валидации (можно null)
   * @param isSavingTempEnabled разрешить сохранение xml на файловую систему
   * @return T instance заполненный бин из xml
   * @throws DetailedJAXBException exception
   */
  @SuppressWarnings("unchecked")
  public T unmarshall(String srcXml, Schema srcXsd, boolean isSavingTempEnabled) throws DetailedJAXBException {

    JAXBContext jc = null;
    Unmarshaller unmarshaller = null;

    // сохраняю файлик себе при отладке
    if (isSavingTempEnabled) {
      try {
        FileSystemUtils.saveTempFile(srcXml, "temp");
      } catch (Throwable e1) {
        throw new DetailedJAXBException(ErrorCodes.ERROR_SAVING_TEMP_FILE, e1.getMessage());
      }
    }

    // создаем Unmarshaller
    try {
      jc = JAXBContext.newInstance(classType.getPackage().getName());
      unmarshaller = jc.createUnmarshaller();
    } catch (JAXBException e) {
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
      } catch (JAXBException e1) {
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
    } catch (JAXBException e) {

      e.printStackTrace();

      // ошибка формирования xml документа
      if (e.getLinkedException() instanceof SAXParseException) {
        wasWrongXmlStructure = true;
        wringXmlStructureMessage += e.getLinkedException().getMessage() + "\n";
      } else {
        throw new DetailedJAXBException(ErrorCodes.UNMARSHALLER_FATAL_ERROR, e.getMessage());
      }
    } catch (ClassCastException castExc) {
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
   * Универсальный метод для обертки бинов (разрешает не дописывать
   *
   * @param tagName tagName
   * @param prefix  prefix
   * @param object  object
   * @return JAXBElement
   * @XmlRootElement).
   */
  // http://jaxb.java.net/tutorial/section_4_5-Calling-marshal.html#Calling%20marshal
  @SuppressWarnings("unchecked")
  JAXBElement<T> wrap(String tagName, String prefix, T object) {
    QName qtag = new QName(getNamespace(), tagName, prefix);
    JAXBElement<T> jbe = new JAXBElement<T>(qtag, (Class<T>) object.getClass(), object);
    return jbe;
  }
}

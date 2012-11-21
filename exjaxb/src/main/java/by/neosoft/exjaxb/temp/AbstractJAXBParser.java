/*
 * Copyright 2011-2012 InterTrust LTD. All rights reserved. Visit our web-site: www.intertrust.ru.
 */
package by.neosoft.exjaxb.temp;

import java.io.File;
import java.io.StringReader;

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
import javax.xml.validation.Schema;

import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import by.neosoft.exjaxb.exception.DetailedJAXBException;
import by.neosoft.exjaxb.exception.ErrorCodes;
import by.neosoft.exjaxb.fs.FileSystemUtils;
import by.neosoft.exjaxb.parser.ExJAXBProperties;

/**
 * ExJAXBParser
 * 
 * @author alex oreshkevich
 */
public abstract class AbstractJAXBParser<TBean extends Object> {

  protected final MarshallerBuilder marshallBuilder;

  public AbstractJAXBParser() {
    marshallBuilder = new MarshallerBuilder();
  }

  /**
   * Нормализация XML
   * 
   * @param src
   * @return
   */
  protected String normalizeXML(String src) {
    return src;
  }

  /**
   * Демаршализация xml-документа в виде строки. <br/>
   * Валидация отключена. Сохранение временного файла отключено.
   * 
   * @param <TBean>
   * @param srcXml
   *          исходный документ одной строкой
   * @param srcBean
   * @return
   * @throws DetailedJAXBException
   */
  public TBean deserialize(String srcXml, Class<? extends TBean> srcBean) throws DetailedJAXBException {
    return deserialize(srcXml, null, srcBean, false, false);
  }

  /**
   * Демаршализация xml-документа заданного файлом
   * 
   * @param <TBean>
   * @param srcFile
   * @param srcBean
   * @return
   * @throws DetailedJAXBException
   */
  public TBean deserialize(File srcFile, Class<? extends TBean> srcBean) throws DetailedJAXBException {
    return deserialize(FileSystemUtils.readFile(srcFile), null, srcBean, false, false);
  }

  /**
   * Демаршализация xml-документа заданного файлом
   * 
   * @param <TBean>
   * @param srcFile
   * @param srcXsd
   * @param srcBean
   * @return
   * @throws DetailedJAXBException
   */
  public TBean deserialize(File srcFile, Schema srcXsd, Class<? extends TBean> srcBean)
      throws DetailedJAXBException {
    return deserialize(FileSystemUtils.readFile(srcFile), srcXsd, srcBean, true, false);
  }

  /**
   * Демаршализация xml-документа в виде строки. <br/>
   * Валидация включена. Сохранение временного файла отключено.
   * 
   * @param <TBean>
   * @param srcXml
   *          исходный документ одной строкой
   * @param @param srcXsd xsd-схема для валидации (можно null)
   * @param srcBean
   * @return
   * @throws DetailedJAXBException
   */
  public TBean deserialize(String srcXml, Schema srcXsd, Class<? extends TBean> srcBean)
      throws DetailedJAXBException {
    return deserialize(srcXml, srcXsd, srcBean, true, false);
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
   * @return T instance заполненный бин из xml
   * @throws DetailedJAXBException
   */
  @SuppressWarnings("unchecked")
  public TBean deserialize(String srcXml, Schema srcXsd, Class<? extends TBean> srcBean,
      boolean isValidationEnabled, boolean isSavingTempEnabled) throws DetailedJAXBException {

    JAXBContext jc = null;
    Unmarshaller unmarshaller = null;

    // сохраняю файлик себе при отладке
    if (isSavingTempEnabled) {
      try {
        FileSystemUtils.saveTempFile(srcXml, "TBeanTempFile");
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
        return source instanceof JAXBElement<?> ? ((JAXBElement<TBean>) source).getValue() : (TBean) source;
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
    }
    // сбор детальной информации
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
   * NamespacePrefixMapperImpl instance
   * 
   * @return
   */
  protected Object getNamespacePrefixMapperImpl() {
    throw new UnsupportedOperationException("Not supported in standard JAXB");
  }

  private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  private static DocumentBuilder        builder;

  protected Document getResultInstance() throws ParserConfigurationException {

    if (builder == null) {
      builder = factory.newDocumentBuilder();
    }

    return builder.newDocument();
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj
   */
  public Document serialize(TBean targetObj) throws JAXBException {
    return serialize(marshallBuilder.createMarshaller(targetObj.getClass()), targetObj);
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj
   */
  public Document serialize(Marshaller m, TBean targetObj) throws JAXBException {
    return serialize(m, targetObj, false);
  }

  /**
   * Выполняет автоматическую сериализацию объекта, задаваемого через targetObj
   */
  public Document serialize(Marshaller m, TBean targetObj, boolean noWrap) throws JAXBException {

    Document doc;
    try {
      doc = getResultInstance();
    }
    catch (ParserConfigurationException e) {
      throw new JAXBException("ParserConfigurationException", e);
    }

    Object jbx = noWrap ? targetObj : wrap(targetObj);

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
  public void serializeToConsole(TBean targetObj) throws JAXBException {
    marshallBuilder.createMarshaller(targetObj.getClass()).marshal(targetObj, System.out);
  }

  /**
   * Универсальный метод для обертки бинов (разрешает не дописывать @XmlRootElement)
   * http://jaxb.java.net/tutorial/section_4_5-Calling-marshal.html#Calling%20marshal
   * 
   * @param <T>
   * @param namespace
   * @param tagName
   * @param object
   * @return
   */
  @SuppressWarnings("unchecked")
  JAXBElement<TBean> wrap(TBean object) {
    ExJAXBProperties properties = getClass().getAnnotation(ExJAXBProperties.class);
    QName qtag = new QName(properties.schemaNamespace(), properties.rootTagName(),
        properties.schemaNamespacePrefix());
    JAXBElement<TBean> jbe = new JAXBElement<TBean>(qtag, (Class<TBean>) object.getClass(), object);
    return jbe;
  }

  protected MarshallerBuilder getMarshallBuilder() {
    return marshallBuilder;
  }
}

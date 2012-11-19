package by.neosoft.exjaxb.exception;

/**
 * ErrorCodes
 * 
 * Перечисляет характерные ошибки при работе с JAXB
 * 
 * @author oreshkevich
 */
public enum ErrorCodes {

    UNKNOWN_ERROR ("Неизвестная ошибка"),
    ERROR_SAVING_TEMP_FILE ("Ошибка создания временного файла"),
    WRONG_SCHEMA_LOCATION ("Не найдена схема XSD"),
    MISSING_ROOT_CLASS ("Не найден класс, инкапсулирующий корневой элемент"),
    INIT_UNMARSHALLER_ERROR ("Ошибка инициализации демаршализатора"),
    UNMARSHALLER_FATAL_ERROR ("Ошибка демаршализации"),
    WRONG_RECIEVER_DESC ("Ошибка при формировании приемного класса для root"),
    WRONG_XML_STRUCTURE ("XML не соответствует спецификации XSD"),
    VALIDATION_EVENT_COLLECTOR_ERR ("Ошибка при подключении ValidationEventCollector");
  String desc = "";

  private ErrorCodes(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}

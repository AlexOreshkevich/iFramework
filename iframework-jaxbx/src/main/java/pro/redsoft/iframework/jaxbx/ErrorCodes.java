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

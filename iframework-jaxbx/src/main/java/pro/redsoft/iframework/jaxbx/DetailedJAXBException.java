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
 * Исключение JAXB, дополненное описанием.
 *
 * @author oreshkevich
 * @version $Id: $Id
 */
public class DetailedJAXBException extends RuntimeException {

  private static final long serialVersionUID = 3379423349112269775L;

  private ErrorCodes        errorCode;
  private String            detailedMessage  = "";

  /**
   * Создает новое исключение с неизвестным кодом ошибки
   */
  public DetailedJAXBException() {
    super();
    errorCode = ErrorCodes.UNKNOWN_ERROR;
    detailedMessage = errorCode.toString();
  }

  /**
   * Создает исключение от неизвестного источника
   *
   * @param e Throwable
   */
  public DetailedJAXBException(Throwable e) {
    super(e);

    if (e instanceof DetailedJAXBException) {
      errorCode = ((DetailedJAXBException) e).getErrorCode();
      detailedMessage = ((DetailedJAXBException) e).getDetailedMessage();
    }
  }

  /**
   * Создает исключение с определенным типом ошибки и сообщением
   *
   * @param errorCode errorCode
   * @param message message
   */
  public DetailedJAXBException(ErrorCodes errorCode, String message) {
    super("\n" + errorCode.getDesc() + "\n" + message);
    this.errorCode = errorCode;
  }

  /**
   * Создает исключение с определенным кодом ошибки и сообщением
   *
   * @param message message
   */
  public DetailedJAXBException(String message) {
    super("\n" + message);

    if (null == this.errorCode) {
      this.errorCode = ErrorCodes.UNKNOWN_ERROR;
    }
  }

  /**
   * Создает исключение с определенным кодом ошибки и любым количеством сообщений
   *
   * @param errorCode errorCode
   * @param info info
   */
  public DetailedJAXBException(ErrorCodes errorCode, String... info) {
    super("\n" + errorCode.getDesc() + "\n" + info[0]);

    if (info.length > 0) {
      for (int i = 1; i < info.length; i++) {
        detailedMessage += info[i] + "\n";
      }
    }

    this.errorCode = errorCode;
    detailedMessage = errorCode.getDesc();
  }

  /**
   * <p>Getter for the field <code>detailedMessage</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getDetailedMessage() {
    return detailedMessage;
  }

  /**
   * <p>Getter for the field <code>errorCode</code>.</p>
   *
   * @return a {@link pro.redsoft.iframework.jaxbx.ErrorCodes} object.
   */
  public ErrorCodes getErrorCode() {
    return errorCode;
  }
}

package by.neosoft.exjaxb;

/**
 * DetailedJAXBException
 * 
 * Исключение, дополненное описанием
 * 
 * @author oreshkevich
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
   * @param e
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
   * @param errorCode
   * @param message
   */
  public DetailedJAXBException(ErrorCodes errorCode, String message) {
    super("\n" + errorCode.getDesc() + "\n" + message);
    this.errorCode = errorCode;
  }

  /**
   * Создает исключение с определенным кодом ошибки и сообщением
   * 
   * @param errorCode
   * @param message
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
   * @param errorCode
   * @param detailed
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

  public String getDetailedMessage() {
    return detailedMessage;
  }

  public ErrorCodes getErrorCode() {
    return errorCode;
  }
}

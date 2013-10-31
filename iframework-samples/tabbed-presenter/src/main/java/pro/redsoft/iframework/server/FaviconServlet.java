package pro.redsoft.iframework.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.redsoft.iframework.shared.config.Config;

/**
 * Сервлет для подстановки иконки приложения из конфига.
 * 
 * @author Nick Gritsenko
 * @since 4.1
 */
public class FaviconServlet extends HttpServlet {

  /**
   * 
   */
  private static final long   serialVersionUID = 618674144221468248L;

  /**
   * The content-type of the images to return.
   */
  private static final String CONTENT_TYPE     = "image/x-icon";

  private static Logger       logger           = Logger.getLogger(FaviconServlet.class
                                                   .getName());

  private byte[]              icon;

  @Override
  public void init() throws ServletException {
    try {
      Config config = new ConfigServiceImpl().getClientSettings();
      if (config.getSystem().getFaviconPath() != null
          && config.getSystem().getFaviconPath().trim().length() > 0) {
        URL resource = getServletContext().getResource(config.getSystem().getFaviconPath());
        this.icon = getImage(resource);
        // если иконки нет пробуем получить стандартную иконку
        if (this.icon == null) {
          this.icon = getImage(getServletContext().getResource("/favicon.ico"));
        }
      }
    }
    catch (Exception e) {
      logger.log(Level.WARNING, "Cannot find any icon from url! " + e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // если есть иконка возвращаем в ответе, если нет обрабатываем стандартно
    if (icon != null) {
      writeBytesToStream(icon, response);
    }
    else {
      super.doGet(request, response);
    }
  }

  /**
   * Пишет массив байт в исходящий поток.
   * 
   * @param byte - массив байт для записи
   * @param response
   *          - ответ сервера куда пишем байты
   */
  private void writeBytesToStream(byte[] bytes, HttpServletResponse response) {
    response.setContentType(CONTENT_TYPE);

    // Send image
    OutputStream sos = null;
    try {
      sos = response.getOutputStream();
      sos.write(bytes);
    }
    catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot write to output stream for favicon! " + e);
    }
    finally {
      if (sos != null) {
        try {
          sos.close();
        }
        catch (IOException e) {
          logger.log(Level.SEVERE, "Cannot close output stream for favicon! " + e);
        }
      }
    }
  }

  /**
   * Получение ресурса (изображения) в виде массива байт.
   * 
   * @param resource
   *          - путь до ресурса
   * @return представление ресурса в массиве байт
   */
  private byte[] getImage(URL resource) {
    // Если ресурса нет сразу возвращаем значение null
    if (resource == null) {
      return null;
    }
    // Пытаемся получить иконку по ресурсу и переводим ее в массив байтов
    byte[] result = null;
    try {
      URLConnection urlConnection = resource.openConnection();
      urlConnection.setReadTimeout(1000);

      urlConnection.connect();
      InputStream di = urlConnection.getInputStream();
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

      int len;
      byte[] b = new byte[1024];
      while ((len = di.read(b)) != -1) {
        byteStream.write(b, 0, len);
      }
      di.close();
      byteStream.close();

      result = byteStream.toByteArray();
    }
    catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot write byte stream for favicon! " + e);
    }
    return result;
  }

}

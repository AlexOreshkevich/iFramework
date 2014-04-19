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

public class FaviconServlet extends HttpServlet {

  /**
   * The content-type of the images to return.
   */
  private static final String CONTENT_TYPE     = "image/x-icon";

  private static Logger       logger           = Logger.getLogger(FaviconServlet.class.getName());

  private static final long   serialVersionUID = 618674144221468248L;

  private byte[]              icon;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (icon == null) {
      init((Config) getServletContext().getAttribute(AbstractConfigServlet.CFG_PROP_NAME));
    }

    if (icon == null) {
      return;
    }

    writeBytesToStream(icon, response);
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

  private void init(Config config) {
    try {
      if (config.getSystem().getFaviconPath() != null
          && config.getSystem().getFaviconPath().trim().length() > 0) {
        URL resource = getServletContext().getResource(config.getSystem().getFaviconPath());
        this.icon = getImage(resource);
      }
    }
    catch (Exception e) {
      logger.log(Level.WARNING, "Cannot find any icon from url! " + e);
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

}

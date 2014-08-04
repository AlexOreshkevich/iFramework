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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * FileSystemUtils
 *
 * @author oreshkevich
 */
public class FileSystemUtils {

  /** Constant <code>PATH="System.getProperty(user.dir)"</code> */
  public static String PATH = System.getProperty("user.dir");

  /**
   * Чтение файла
   *
   * @param target target file
   * @return file content as String
   */
  public static String readFile(File target) {
    String adhocXml = null;
    try {
      FileInputStream fis = new FileInputStream(target);
      byte[] all = new byte[(int) target.length()];
      fis.read(all);
      fis.close();
      adhocXml = new String(all);
      return adhocXml;
    }
    catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * При отладке сохраняю на диск файлы во временную папку
   *
   * @param xmlSource xmlSource
   * @param fileName fileName
   * @throws java.lang.Throwable java.lang.Throwable
   */
  public static void saveTempFile(String xmlSource, String fileName) throws Throwable {

    if (fileName.isEmpty()) {
      fileName = "MissingId";
    }

    File adhoc = new File(PATH + fileName + ".xml");

    if (adhoc.exists()) {
      adhoc.delete();
    }

    adhoc.createNewFile();

    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(adhoc));
    bos.write(xmlSource.getBytes());
    bos.close();
  }
}

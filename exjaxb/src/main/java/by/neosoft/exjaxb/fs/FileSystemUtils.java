/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.neosoft.exjaxb.fs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * FileSystemUtils
 * 
 * Работа с файловой системой
 * 
 * @author oreshkevich
 */
public class FileSystemUtils {

  /**
   * Чтение файла
   * 
   * @param target
   * @return
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
      e.printStackTrace();
    }
    return adhocXml;
  }

  /**
   * При отладке сохраняю на диск файлы во временную папку
   * 
   * @param xmlSource
   * @param fileName
   * @throws Throwable
   */
  public static void saveTempFile(String xmlSource, String fileName) throws Throwable {

    File rootDir = new File(System.getProperty("user.dir"));
    if (!rootDir.exists()) {
      rootDir.mkdir();
    }

    if (fileName.isEmpty()) {
      fileName = "MissingId";
    }

    File adhoc = new File(rootDir.getPath() + "/" + fileName + ".xml");
    if (adhoc.exists()) {
      adhoc.delete();
    }

    adhoc.createNewFile();

    System.out.println("Created file " + adhoc.getAbsolutePath());

    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(adhoc));
    bos.write(xmlSource.getBytes());
    bos.close();
  }
}

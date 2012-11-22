package by.neosoft.exjaxb;

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

  public static String PATH = System.getProperty("user.dir");

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
      throw new RuntimeException(e);
    }
  }

  /**
   * При отладке сохраняю на диск файлы во временную папку
   * 
   * @param xmlSource
   * @param fileName
   * @throws Throwable
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

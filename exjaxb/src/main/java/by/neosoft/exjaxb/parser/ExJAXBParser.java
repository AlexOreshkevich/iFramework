/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.neosoft.exjaxb.parser;

import java.util.HashMap;

/**
 * Описывает базовый набор параметров для работы с библиотекой ExJAXB
 * 
 * @param T
 *          тип целевого класса
 * 
 * @author oreshkevich
 */
public interface ExJAXBParser<T> {

  /**
   * ExJAXBParser должен предоставить сведения о пользовательских простанствах имен Если возвращается null, то
   * используется карта по умолчанию
   * 
   * @return сведения о пользовательских простанствах имен (HashMap)
   */
  public HashMap<String, String> getNamespacePrefixMap();

  /**
   * Для корректного определения префикса необходимо определить пространство имен
   * 
   * @return
   */
  public String getNamespace();

  /**
   * getRootTagName
   * 
   * @return
   */
  public String getRootTagName();

  /**
   * getSchemaInstancePrefix
   * 
   * @return
   */
  public String getSchemaInstancePrefix();
}

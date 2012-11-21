package by.neosoft.exjaxb.parser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ExJAXBProperties
 * 
 * @author alex oreshkevich
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExJAXBProperties {

  String rootTagName();

  String schemaNamespace();

  String schemaNamespacePrefix();

  String schemaFileNameHeader();

  String currentVersion();
}

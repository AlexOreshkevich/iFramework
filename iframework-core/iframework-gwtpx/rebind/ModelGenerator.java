///**
// * Copyright 2013 REDSOFT.PRO
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy of
// * the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations under
// * the License.
// */
//package pro.redsoft.iframework.client.rebind;
//
//import java.io.PrintWriter;
//
//import pro.redsoft.iframework.client.model.Model;
//
//import com.google.gwt.core.ext.Generator;
//import com.google.gwt.core.ext.GeneratorContext;
//import com.google.gwt.core.ext.TreeLogger;
//import com.google.gwt.core.ext.UnableToCompleteException;
//import com.google.gwt.core.ext.typeinfo.JClassType;
//import com.google.gwt.core.ext.typeinfo.TypeOracle;
//import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
//import com.google.gwt.user.rebind.SourceWriter;
//
///**
// * ModelGenerator.
// * 
// * @author Alex N. Oreshkevich
// */
//public class ModelGenerator extends Generator {
//
//  @Override
//  public String generate(TreeLogger logger, GeneratorContext context, String typeName)
//      throws UnableToCompleteException {
//
//    TypeOracle typeOracle = context.getTypeOracle();
//    assert typeOracle != null;
//
//    JClassType classType = typeOracle.findType(typeName);
//    if (classType == null) {
//      logger.log(TreeLogger.ERROR, "Unable to find metadata for type '" + typeName + "'", null);
//      throw new UnableToCompleteException();
//    }
//
//    String packageName = classType.getPackage().getName();
//    String simpleName = classType.getSimpleSourceName() + "Impl";
//    String qualifiedClassName = packageName + "." + simpleName;
//
//    // load annotation source
//    Class<? extends Object> modelType = classType.getAnnotation(ModelType.class).value();
//
//    ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
//        packageName, simpleName);
//
//    // imports
//    composerFactory.addImport(Model.class.getCanonicalName());
//    composerFactory.addImport(modelType.getCanonicalName());
//
//    // interfaces
//    composerFactory.addImplementedInterface(Model.class.getSimpleName());
//
//    PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
//    if (printWriter == null) {
//      return qualifiedClassName;
//    }
//
//    SourceWriter sourceWriter = composerFactory.createSourceWriter(context, printWriter);
//    if (sourceWriter == null) {
//      return qualifiedClassName;
//    }
//
//    // write the method body of public Class<? extends Object> getType();
//    String modelSimpleName = modelType.getSimpleName();
//    sourceWriter.println();
//    sourceWriter.println("@Override");
//    sourceWriter.println("public Class<? extends Object> getType() {");
//    sourceWriter.println("    return " + modelSimpleName + ".class;");
//    sourceWriter.println("}");
//    // method body ends
//
//    sourceWriter.commit(logger);
//    return packageName + "." + simpleName;
//  }
// }

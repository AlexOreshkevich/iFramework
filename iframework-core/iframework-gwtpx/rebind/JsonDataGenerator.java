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
// * JsonDataGenerator.
// * 
// * @author Alex N. Oreshkevich
// */
//public class JsonDataGenerator extends Generator {
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
//    composerFactory.addImport(modelType.getCanonicalName());
//    composerFactory.addImport(JsonDataPrototype.class.getCanonicalName());
//    composerFactory.addImport(name.pehl.piriti.json.client.JsonReader.class.getCanonicalName());
//    composerFactory.addImport(name.pehl.piriti.json.client.JsonWriter.class.getCanonicalName());
//    composerFactory.addImport(com.google.inject.Inject.class.getCanonicalName());
//    composerFactory.addImport(com.google.gwt.core.shared.GWT.class.getCanonicalName());
//
//    // superclass
//    composerFactory.setSuperclass(JsonDataPrototype.class.getSimpleName());
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
//    // simple model name
//    String modelSimpleName = modelType.getSimpleName();
//
//    // public interface BeanReader extends JsonReader<ApprovalCycle> {
//    // }
//    sourceWriter.println("\npublic interface BeanReader extends JsonReader<" + modelSimpleName
//        + "> {");
//    sourceWriter.println("}\n");
//
//    // public interface BeanWriter extends JsonWriter<ApprovalCycle> {
//    // }
//    sourceWriter.println("public interface BeanWriter extends JsonWriter<" + modelSimpleName
//        + "> {");
//    sourceWriter.println("}\n");
//
//    // @Inject
//    // public ApprovalCycleInfo() {
//    // JSON_READER = GWT.create(BeanReader.class);
//    // JSON_WRITER = GWT.create(BeanWriter.class);
//    // }
//    sourceWriter.println("@Inject");
//    sourceWriter.println("public " + simpleName + "(){");
//    sourceWriter.indentln("jsonReader = GWT.create(BeanReader.class);");
//    sourceWriter.indentln("jsonWriter = GWT.create(BeanWriter.class);");
//    sourceWriter.println("}\n");
//
//    // write the method body of public Class<? extends Object> getType();
//    sourceWriter.println("@Override");
//    sourceWriter.println("public Class<? extends Object> getType() {");
//    sourceWriter.println("    return " + modelSimpleName + ".class;");
//    sourceWriter.println("}\n");
//    // method body ends
//
//    sourceWriter.commit(logger);
//    return packageName + "." + simpleName;
//  }
// }

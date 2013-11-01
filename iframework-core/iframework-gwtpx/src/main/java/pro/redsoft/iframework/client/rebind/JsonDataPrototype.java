/**
 * Copyright 2013 REDSOFT.PRO
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.redsoft.iframework.client.rebind;

import javax.annotation.Generated;

import pro.redsoft.iframework.client.model.JsonData;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

/**
 * JsonDataPrototype.
 * 
 * @author Alex N. Oreshkevich
 */
@Generated("JsonDataGenerator")
public abstract class JsonDataPrototype implements JsonData {

  protected JsonReader<?> jsonReader;
  protected JsonWriter<?> jsonWriter;

  @Override
  public final JsonReader<?> getReader() {
    return jsonReader;
  }

  @Override
  public final JsonWriter<?> getWriter() {
    return jsonWriter;
  }
}

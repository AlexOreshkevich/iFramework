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
package pro.redsoft.iframework.client.xml;

import java.lang.annotation.Documented;

/**
 * Standardized Class Descriptor.
 * 
 * @author Neo
 */
@Documented
public @interface StandardClassDescriptor {

  /** Class Advanced Software Architector */
  String author();

  /** Class Description */
  String classDescription() default "";

  /** Current Revision */
  int currentRevision();

  /** Class Deliver Date */
  String date();

  /** Last Revision Date */
  String lastModified();

  /** Last Revision Author */
  String lastModifiedBy();

  /** Class Reviewers */
  String[] reviewers();
}

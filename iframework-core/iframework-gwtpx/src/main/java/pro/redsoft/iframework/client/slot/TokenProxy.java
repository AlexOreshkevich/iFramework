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
package pro.redsoft.iframework.client.slot;

import com.google.gwt.user.client.History;

/**
 * Represents custom {@link pro.redsoft.iframework.client.slot.TokenProxy} that can be implemented as history token (no differencies from gwtp-platform
 * defaults, see {@link com.google.gwt.user.client.History} for details) or as any object.
 * <p>
 * Important: define correct {@link java.lang.Object#equals(Object)} and {@link java.lang.Object#hashCode()} implementation.
 * </p>
 *
 * @author alex oreshkevich
 * @version $Id: $Id
 */
public interface TokenProxy {
  // Marker
}

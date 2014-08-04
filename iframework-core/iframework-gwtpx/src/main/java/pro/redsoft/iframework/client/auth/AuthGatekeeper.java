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

package pro.redsoft.iframework.client.auth;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

/**
 * Blocks application for unauthorized users via revealing unauthorized place.
 *
 * @author alex oreshkevich
 * @version $Id: $Id
 */
public class AuthGatekeeper implements Gatekeeper {

  private final CurrentUser currentUser;

  /**
   * <p>Constructor for AuthGatekeeper.</p>
   *
   * @param currentUser a {@link pro.redsoft.iframework.client.auth.CurrentUser} object.
   */
  @Inject
  public AuthGatekeeper(CurrentUser currentUser) {
    this.currentUser = currentUser;
  }

  /** {@inheritDoc} */
  @Override
  public boolean canReveal() {
    return currentUser.isAuthorized();
  }
}

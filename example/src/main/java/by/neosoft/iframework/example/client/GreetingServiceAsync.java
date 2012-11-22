/**
 * 
 */
package by.neosoft.iframework.example.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author neo
 *
 */
public interface GreetingServiceAsync {

  /**
   * 
   * @see by.neosoft.iframework.example.client.GreetingService#greetServer(java.lang.String)
   */
  void greetServer(String name, AsyncCallback<String> callback);

}

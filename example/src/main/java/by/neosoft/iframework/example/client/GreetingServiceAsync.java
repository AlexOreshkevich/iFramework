/**
 * 
 */
package by.neosoft.iframework.example.client;

import by.neosoft.iframework.example.shared.config.Config;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author alex oreshkevich
 * 
 */
public interface GreetingServiceAsync {

  void greetServer(String name, AsyncCallback<String> callback);

  void loadConfig(AsyncCallback<Config> callback) throws RuntimeException;

}

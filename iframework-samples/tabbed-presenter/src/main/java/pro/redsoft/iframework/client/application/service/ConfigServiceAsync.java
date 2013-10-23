package pro.redsoft.iframework.client.application.service;

import pro.redsoft.iframework.shared.config.Config;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ConfigService</code>.
 */
public interface ConfigServiceAsync {

  void getClientSettings(AsyncCallback<Config> callback) throws RuntimeException;
}

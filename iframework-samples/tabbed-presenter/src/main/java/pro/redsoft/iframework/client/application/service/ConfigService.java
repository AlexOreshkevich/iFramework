package pro.redsoft.iframework.client.application.service;

import pro.redsoft.iframework.shared.config.Config;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC configService.
 */
@RemoteServiceRelativePath("configServiceImpl")
public interface ConfigService extends RemoteService {

  Config getClientSettings() throws RuntimeException;
}

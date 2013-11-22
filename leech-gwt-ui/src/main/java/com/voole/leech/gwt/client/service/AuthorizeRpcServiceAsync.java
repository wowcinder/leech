
package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.leech.shared.entity.authorize.Authorize;

public interface AuthorizeRpcServiceAsync {


    public void getAllocatenbeAuthorizes(AsyncCallback<List<Authorize>> callback);

}

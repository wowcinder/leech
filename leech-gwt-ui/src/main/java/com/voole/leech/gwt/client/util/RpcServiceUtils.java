
package com.voole.leech.gwt.client.util;

import com.google.gwt.core.client.GWT;
import com.voole.leech.gwt.client.service.AuthorizeRpcServiceAsync;
import com.voole.leech.gwt.client.service.CTypeLogModelMetaRpcServiceAsync;
import com.voole.leech.gwt.client.service.HbaseMetaRpcServiceAsync;
import com.voole.leech.gwt.client.service.HbaseQueryRpcServiceAsync;
import com.voole.leech.gwt.client.service.JsonLogModelMetaRpcServiceAsync;
import com.voole.leech.gwt.client.service.KafkaRpcServiceAsync;
import com.voole.leech.gwt.client.service.MenuRpcServiceAsync;
import com.voole.leech.gwt.client.service.OpenAuthorizeRpcServiceAsync;
import com.voole.leech.gwt.client.service.UserRpcServiceAsync;

public class RpcServiceUtils {

    public final static AuthorizeRpcServiceAsync AuthorizeRpcService = GWT.create(com.voole.leech.gwt.client.service.AuthorizeRpcService.class);
    public final static CTypeLogModelMetaRpcServiceAsync CTypeLogModelMetaRpcService = GWT.create(com.voole.leech.gwt.client.service.CTypeLogModelMetaRpcService.class);
    public final static HbaseMetaRpcServiceAsync HbaseMetaRpcService = GWT.create(com.voole.leech.gwt.client.service.HbaseMetaRpcService.class);
    public final static HbaseQueryRpcServiceAsync HbaseQueryRpcService = GWT.create(com.voole.leech.gwt.client.service.HbaseQueryRpcService.class);
    public final static JsonLogModelMetaRpcServiceAsync JsonLogModelMetaRpcService = GWT.create(com.voole.leech.gwt.client.service.JsonLogModelMetaRpcService.class);
    public final static KafkaRpcServiceAsync KafkaRpcService = GWT.create(com.voole.leech.gwt.client.service.KafkaRpcService.class);
    public final static MenuRpcServiceAsync MenuRpcService = GWT.create(com.voole.leech.gwt.client.service.MenuRpcService.class);
    public final static OpenAuthorizeRpcServiceAsync OpenAuthorizeRpcService = GWT.create(com.voole.leech.gwt.client.service.OpenAuthorizeRpcService.class);
    public final static UserRpcServiceAsync UserRpcService = GWT.create(com.voole.leech.gwt.client.service.UserRpcService.class);

}

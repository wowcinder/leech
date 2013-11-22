
package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

import org.hibernate.validator.engine.ValidationSupport;

public interface JsonLogModelMetaRpcServiceAsync {


    public void saveLogModel(JsonLogModel arg0, AsyncCallback<JsonLogModel> callback);

    public void updateLogModel(JsonLogModel arg0, AsyncCallback<JsonLogModel> callback);

    public void deleteLogModels(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModel(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<JsonLogModel>> callback);

    public void getLogModels(AsyncCallback<List<JsonLogModel>> callback);

    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveLogModelVersion(JsonLogModelVersion arg0, AsyncCallback<JsonLogModelVersion> callback);

    public void updateLogModelVersion(JsonLogModelVersion arg0, AsyncCallback<JsonLogModelVersion> callback);

    public void deleteLogModelVersions(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModelVersion(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<JsonLogModelVersion>> callback);

    public void getLogModelVersions(AsyncCallback<List<JsonLogModelVersion>> callback);

    public void getLogModelVersionRootNode(Integer arg0, AsyncCallback<JsonLogModelGroupColumn> callback);

    public void saveLogModelSimpleColumn(JsonLogModelSimpleColumn arg0, AsyncCallback<JsonLogModelSimpleColumn> callback);

    public void updateLogModelSimpleColumn(JsonLogModelSimpleColumn arg0, AsyncCallback<JsonLogModelSimpleColumn> callback);

    public void saveLogModelGroupColumn(JsonLogModelGroupColumn arg0, AsyncCallback<JsonLogModelGroupColumn> callback);

    public void updateLogModelGroupColumn(JsonLogModelGroupColumn arg0, AsyncCallback<JsonLogModelGroupColumn> callback);

    public void deleteLogModelColumn(Integer arg0, AsyncCallback<Void> callback);

}


package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

import org.hibernate.validator.engine.ValidationSupport;

public interface CTypeLogModelMetaRpcServiceAsync {


    public void saveLogModel(CTypeLogModel arg0, AsyncCallback<CTypeLogModel> callback);

    public void updateLogModel(CTypeLogModel arg0, AsyncCallback<CTypeLogModel> callback);

    public void deleteLogModels(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModel(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<CTypeLogModel>> callback);

    public void getLogModels(AsyncCallback<List<CTypeLogModel>> callback);

    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveLogModelVersion(CTypeLogModelVersion arg0, AsyncCallback<CTypeLogModelVersion> callback);

    public void updateLogModelVersion(CTypeLogModelVersion arg0, AsyncCallback<CTypeLogModelVersion> callback);

    public void deleteLogModelVersions(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModelVersion(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<CTypeLogModelVersion>> callback);

    public void getLogModelVersions(AsyncCallback<List<CTypeLogModelVersion>> callback);

    public void getLogModelVersionRootNode(Integer arg0, AsyncCallback<CTypeLogModelGroupColumn> callback);

    public void saveLogModelSimpleColumn(CTypeLogModelSimpleColumn arg0, AsyncCallback<CTypeLogModelSimpleColumn> callback);

    public void updateLogModelSimpleColumn(CTypeLogModelSimpleColumn arg0, AsyncCallback<CTypeLogModelSimpleColumn> callback);

    public void saveLogModelGroupColumn(CTypeLogModelGroupColumn arg0, AsyncCallback<CTypeLogModelGroupColumn> callback);

    public void updateLogModelGroupColumn(CTypeLogModelGroupColumn arg0, AsyncCallback<CTypeLogModelGroupColumn> callback);

    public void deleteLogModelColumn(Integer arg0, AsyncCallback<Void> callback);

    public void move(CTypeLogModelColumn arg0, CTypeLogModelColumn arg1, CTypeLogModelColumn arg2, AsyncCallback<CTypeLogModelColumn> callback);

}

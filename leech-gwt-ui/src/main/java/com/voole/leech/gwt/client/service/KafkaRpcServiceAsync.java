
package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

import org.hibernate.validator.engine.ValidationSupport;

public interface KafkaRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void getLogModelVersions(AsyncCallback<List<LogModelVersion<?>>> callback);

    public void getTopics(AsyncCallback<List<KafkaTopic>> callback);

    public void saveKafkaTopic(KafkaTopic arg0, AsyncCallback<KafkaTopic> callback);

    public void updateKafkaTopic(KafkaTopic arg0, AsyncCallback<KafkaTopic> callback);

    public void deleteKafkaTopics(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingKafkaTopic(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<KafkaTopic>> callback);

    public void saveKafkaWatchDog(KafkaWatchDog arg0, AsyncCallback<KafkaWatchDog> callback);

    public void updateKafkaWatchDog(KafkaWatchDog arg0, AsyncCallback<KafkaWatchDog> callback);

    public void deleteKafkaWatchDogs(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingKafkaWatchDog(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<KafkaWatchDog>> callback);

    public void saveKafkaWatchDogTopicSetting(KafkaWatchDogTopicSetting arg0, AsyncCallback<KafkaWatchDogTopicSetting> callback);

    public void updateKafkaWatchDogTopicSetting(KafkaWatchDogTopicSetting arg0, AsyncCallback<KafkaWatchDogTopicSetting> callback);

    public void deleteKafkaWatchDogTopicSettings(List<Integer> arg0, AsyncCallback<Void> callback);

    public void getKafkaWatchDogTopicSettings(Integer arg0, AsyncCallback<List<KafkaWatchDogTopicSetting>> callback);

    public void pagingKafkaWatchDogStatus(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<KafkaWatchDog>> callback);

    public void getRemainKafkaTopics(Integer arg0, AsyncCallback<List<KafkaTopic>> callback);

    public void saveWatchDogTopicSettings(Integer arg0, List<Integer> arg1, AsyncCallback<List<KafkaWatchDogTopicSetting>> callback);

    public void start(Integer arg0, AsyncCallback<Void> callback);

    public void stop(Integer arg0, AsyncCallback<Void> callback);

    public void restart(Integer arg0, AsyncCallback<Void> callback);

}

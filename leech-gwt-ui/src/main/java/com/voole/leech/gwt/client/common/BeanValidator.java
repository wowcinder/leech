
package com.voole.leech.gwt.client.common;

import javax.validation.Validator;
import com.google.gwt.validation.client.GwtValidation;

@GwtValidation(groups = {
    javax.validation.groups.Default.class,
    com.voole.leech.common.shared.groups.GWTChecks.class
}, value = {
    com.voole.leech.shared.entity.authorize.Authorize.class,
    com.voole.leech.shared.entity.authorize.AuthorizeGroup.class,
    com.voole.leech.shared.entity.menu.Menu.class,
    com.voole.leech.shared.entity.menu.MenuGroup.class,
    com.voole.leech.shared.entity.menu.MenuNode.class,
    com.voole.leech.shared.entity.user.User.class,
    com.voole.leech.shared.entity.user.UserGroup.class,
    com.voole.leech.hbasemeta.shared.entity.base.HbaseTable.class,
    com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn.class,
    com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion.class,
    com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel.class,
    com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion.class,
    com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel.class,
    com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn.class,
    com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion.class,
    com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.class,
    com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog.class,
    com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.class,
    com.voole.leech.logmodelmeta.shared.entity.LogModel.class,
    com.voole.leech.logmodelmeta.shared.entity.LogModelVersion.class
})
public interface BeanValidator
    extends Validator
{


}

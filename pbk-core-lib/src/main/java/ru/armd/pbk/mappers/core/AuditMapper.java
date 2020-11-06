package ru.armd.pbk.mappers.core;

import org.springframework.cache.annotation.Cacheable;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Audit;

import java.util.List;

/**
 * Маппер для работы с аудитом.
 */
@IsMapper
public interface AuditMapper
	extends IDomainMapper<Audit> {

   /**
	* Выбирает справочную информацию - перечень допустимых типов сообщений.
	*
	* @return List<SelectItem> список доступных для выбора типов сообщений
	*/
   @Cacheable(value = "systemCache", key = "#root.method.name")
   List<SelectItem> getAuditTypesSelectItems();

   /**
	* Выбирает справочную информацию - перечень допустимых уровней сообщений.
	*
	* @return List<SelectItem> список доступных для выбора уровней сообщений
	*/
   @Cacheable(value = "systemCache", key = "#root.method.name")
   List<SelectItem> getAuditLevelsSelectItems();

   /**
	* Выбирает справочную информацию - перечень допустимых операций.
	*
	* @return List<SelectItem> список доступных для выбора операций.
	*/
   @Cacheable(value = "systemCache", key = "#root.method.name")
   List<SelectItem> getAuditOperationsSelectItems();

   /**
	* Получает кол-во устаревших записей аудита.
	*
	* @return кол-во устаревших записей аудита
	*/
   long getCountOldValues();

   /**
	* Удаляет первые 100000 устаревших записей аудита.
	*/
   void removeOldValues();
}
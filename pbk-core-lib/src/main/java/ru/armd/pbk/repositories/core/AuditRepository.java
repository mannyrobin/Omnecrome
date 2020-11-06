package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.AuditMapper;

import java.util.List;

/**
 * Репозиторий аудита.
 */
@Repository
public class AuditRepository
	extends BaseDomainRepository<Audit> {

   public static final Logger LOGGER = Logger.getLogger(AuditRepository.class);

   private AuditMapper auditMapper;

   @Autowired
   AuditRepository(AuditMapper mapper) {
	  super(CoreAuditType.CORE_AUDIT, mapper);
	  this.auditMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Выбирает справочную информацию - перечень допустимых типов сообщений.
	*
	* @return List<SelectItem> список доступных для выбора типов сообщений
	*/
   public List<SelectItem> getAuditTypesSelectItems() {
	  List<SelectItem> sList = null;
	  try {
		 sList = auditMapper.getAuditTypesSelectItems();
	  } catch (Exception e) {
		 String message = "Не удалось получить список типов сообщений. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Выбирает справочную информацию - перечень допустимых уровней сообщений.
	*
	* @return List<SelectItem> список доступных для выбора уровней сообщений
	*/
   public List<SelectItem> getAuditLevelsSelectItems() {
	  List<SelectItem> sList = null;
	  try {
		 sList = auditMapper.getAuditLevelsSelectItems();
	  } catch (Exception e) {
		 String message = "Не удалось получить список уровней сообщений. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Выбирает справочную информацию - перечень допустимых операций.
	*
	* @return List<SelectItem> список доступных для выбора операций.
	*/
   public List<SelectItem> getAuditOperationsSelectItems() {
	  List<SelectItem> sList = null;
	  try {
		 sList = auditMapper.getAuditOperationsSelectItems();
	  } catch (Exception e) {
		 String message = "Не удалось получить список допустимых операций. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получает кол-во устаревших записей аудита.
	*
	* @return кол-во устаревших записей аудита
	*/
   public Long getCountOldValues() {
	  Long count = null;
	  try {
		 count = auditMapper.getCountOldValues();
	  } catch (Exception e) {
		 String message = "Не удалось получить количество устаревших записей аудита. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }

   /**
	* Удаляет первые 100000 устаревших записей аудита.
	*/
   public void removeOldValues() {
	  auditMapper.removeOldValues();
   }
}

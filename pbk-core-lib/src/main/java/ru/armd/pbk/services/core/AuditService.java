package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.domain.core.AuditLog;
import ru.armd.pbk.dto.core.AuditDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditLogState;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.matcher.core.IAuditMatcher;
import ru.armd.pbk.repositories.core.AuditLogRepository;
import ru.armd.pbk.repositories.core.AuditRepository;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Реализация сервиса аудита.
 */
@Service
public class AuditService
	extends BaseDomainService<Audit, AuditDTO> {

   private static final Logger LOGGER = Logger.getLogger(AuditService.class);

   private AuditRepository auditRepository;
   private AuditLogRepository auditLogRepository;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   AuditService(AuditRepository repository, AuditLogRepository auditLogRepository) {
	  super(repository);
	  this.auditRepository = repository;
	  this.auditLogRepository = auditLogRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Возвращает перечень типов сообщений, для отображения в выпадающем списке
	* на форме.
	*
	* @return List<SelectItem> SelectItem содержит название и id типа
	* сообщения.
	*/
   @Transactional
   public List<SelectItem> getAuditTypesSelectItems() {
	  try {
		 return auditRepository.getAuditTypesSelectItems();
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_AUDIT, AuditObjOperation.SELECT, null, "Не удалось получить список типов сообщений аудита. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось получить список типов сообщений аудита. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Возвращает перечень уровней сообщений, для отображения в выпадающем
	* списке на форме.
	*
	* @return List<SelectItem> SelectItem содержит название и id уровня
	* сообщения.
	*/
   @Transactional
   public List<SelectItem> getAuditLevelsSelectItems() {
	  try {
		 return auditRepository.getAuditLevelsSelectItems();
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_AUDIT, AuditObjOperation.SELECT, null, "Не удалось получить список уровней сообщений аудита. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось получить список уровней сообщений аудита. Ошибка:" + e.getMessage(), e);
	  }
   }

   @Transactional
   public List<SelectItem> getAuditOperationsSelectItems() {
	  try {
		 return auditRepository.getAuditOperationsSelectItems();
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_AUDIT, AuditObjOperation.SELECT, null, "Не удалось получить список операций аудита. Ошибка:" + e.getMessage(), e);
		 throw new PBKException("Не удалось получить список операций аудита. Ошибка:" + e.getMessage(), e);
	  }
   }

   /**
	* Метод логгирования успешной аутентификации.
	*
	* @param userLogin     userLogin.
	* @param userIPAddress userIPAddress.
	*/
   public void logLoginSuccess(String userLogin, String userIPAddress) {
	  String message = "Пользователь не определен";
	  if (userLogin != null) {
		 message = String.format("Пользователь:%s, IP адрес:%s успешно вошел в систему", userLogin, userIPAddress);
	  }
	  logAudit(AuditLevel.INFO, AuditType.LOG_IN, null, null, message, null);
   }

   /**
	* Метод логгирования не успешной аутентификации.
	*
	* @param userIPAddress userIPAddress.
	* @param t             t.
	*/
   public void logLoginFail(String userIPAddress, Throwable t) {
	  String message = "Пользователь не определен";
	  logAudit(AuditLevel.ERROR, AuditType.LOG_IN, null, null, message, t);
   }

   /**
	* Метод логгирования выхода пользователя из системы.
	*
	* @param userLogin userLogin.
	*/
   public void logLogout(String userLogin) {
	  String message = "Пользователь не определен";
	  if (userLogin != null) {
		 message = String.format("Пользователь %s успешно вышел из системы", userLogin);
	  }
	  logAudit(AuditLevel.INFO, AuditType.LOG_OUT, null, null, message, null);
   }

   @Override
   public AuditDTO toDTO(Audit domain) {
	  if (domain == null) {
		 return null;
	  }
	  AuditDTO dto = IAuditMatcher.INSTANCE.toDTO(domain);
	  return dto;
   }

   @Transactional
   public void removeOldValues() {
	  AuditLog auditLog = new AuditLog();
	  try {
		 int countDate = Integer.valueOf(settingsRepository.getByCode("CLEAN_TIME_LOGS").getValue());
		 if (countDate > 0) {
			auditLog.setStartDate(new Date());
			Long countAll = auditRepository.getCountOldValues();
			while (auditRepository.getCountOldValues() > 0L) {
			   auditRepository.removeOldValues();
			}
			auditLog.setEndDate(new Date());
			auditLog.setLogStateId(AuditLogState.SUCCESS.getId());
			auditLog.setMessage("Количество очищенных записей: " + countAll);
			auditLog.setToDate(DateUtils.addDays(auditLog.getStartDate(), -countDate));
			auditLogRepository.save(auditLog);
		 }
	  } catch (Exception ex) {
		 logAudit(AuditLevel.ERROR, CoreAuditType.CORE_AUDIT, AuditObjOperation.DELETE, null, "Не удалось очистить записи аудита. Ошибка:" + ex.getMessage(), ex);
		 auditLog.setEndDate(new Date());
		 auditLog.setLogStateId(AuditLogState.NOT_SUCCESS.getId());
		 auditLog.setMessage("Ошибка: " + ex.getMessage());
		 auditLogRepository.save(auditLog);
	  }
   }
}

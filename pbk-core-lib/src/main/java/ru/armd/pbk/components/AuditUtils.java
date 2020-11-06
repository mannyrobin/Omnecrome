package ru.armd.pbk.components;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.authtoken.SecurityAdapterUser;
import ru.armd.pbk.core.IAuditable;
import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.AuditMapper;
import ru.armd.pbk.utils.StringUtils;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * Класс с методами логирования аудита в БД. Пишет аудит в нескольких потоках.
 */
@Component
public class AuditUtils
	implements IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(AuditUtils.class);

   public static final Long DEFAULT_CREATE_USER_ID = 0L;
   public static final String DEFAULT_CREATE_USER_INFO = "SYSTEM";
   public static final AuditLevel DEFAULT_AUDIT_LEVEL = AuditLevel.INFO;
   public static final AuditType DEFAULT_AUDIT_TYPE = AuditType.NOT_DEFINED;
   public static final AuditObjOperation DEFAULT_AUDIT_OBJ_OPERATION = AuditObjOperation.OTHER;

   public static final String AUDIT_EXECUTOR = "auditExecutor";

   private static final int MAX_AUDIT_QUEUE_SIZE = 1 * 1_000;
   private static final int MAX_AUDIT_QUEUE_CAPACITY = MAX_AUDIT_QUEUE_SIZE + 10;

   @Autowired
   protected AuditMapper auditMapper;

   @Autowired
   protected SettingUtils settingUtils;


   // private BlockingQueue<Audit> auditQueue = new
   // ArrayBlockingQueue<Audit>(MAX_AUDIT_QUEUE_CAPACITY);
   private Queue<Audit> auditQueue = new ArrayDeque<>(MAX_AUDIT_QUEUE_SIZE);

   private final boolean isQueue = false;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Производит проверку объекта на null и возвращает его информацию.
	*
	* @param object object.
	* @return
	*/
   protected String makeObjectInfo(Object object) {
	  if (object == null) {
		 return null;
	  }
	  if (object instanceof IAuditable) {
		 return ((IAuditable) object).toAuditString();
	  }
	  if (object instanceof IHasId) {
		 IHasId hasId = (IHasId) object;
		 if (hasId == null || hasId.getId() == null) {
			return null;
		 }
		 return hasId.getId().toString();
	  }
	  if (object instanceof List) {
		 List list = (List) object;
		 return StringUtils.mkString(list);
	  }

	  return object.toString();
   }

   /**
	* Метод формирует запись аудита, сохраняет в БД в отдельной транзакции,
	* возвращает сформированную и вставленную запись.
	*
	* @param auditLevel    Уровень события.
	* @param auditType     Тип события.
	* @param objOperation  Операция над объектом в событии.
	* @param object        объект, для которого событие возникло.
	* @param message       Сообщение.
	* @param t             Исключение.
	* @param sau           Данные о пользователе.
	* @param userIPAddress Данные по ip адресу.
	* @param threadName    Имя основного пока выполнения.
	* @param settingValue  Настройка логирования.
	* @return Объект аудита.
	*/
   @Async(AUDIT_EXECUTOR)
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public Audit logAudit(AuditLevel auditLevel, AuditType auditType, AuditObjOperation objOperation, Object object,
						 String message, Throwable t, SecurityAdapterUser sau, String userIPAddress, String threadName,
						 String settingValue) {
	  if (settingValue == null) {
		 settingValue = settingUtils.getSettingValue(Settings.AUDIT_LEVEL, "INFO");
	  }
	  boolean isLogEnable = !StringUtils.isBlank(settingValue);

	  Audit audit = new Audit();
	  audit.setCreateDate(new Date());
	  if (auditLevel != null) {
		 audit.setAuditLevelId(auditLevel.getId());
		 isLogEnable = isLogEnable && auditLevel.isEnable(settingValue);
	  } else {
		 audit.setAuditLevelId(DEFAULT_AUDIT_LEVEL.getId());
		 isLogEnable = isLogEnable && DEFAULT_AUDIT_LEVEL.isEnable(settingValue);
	  }
	  audit.setAuditTypeId(auditType != null ? auditType.getId() : DEFAULT_AUDIT_TYPE.getId());
	  audit.setObjOperationId(objOperation != null ? objOperation.getId() : DEFAULT_AUDIT_OBJ_OPERATION.getId());
	  audit.setObjInfo(makeObjectInfo(object));
	  audit.setMessage(message);
	  audit.setUserIPAddress(userIPAddress);

	  if (sau != null) {
		 StringBuffer sb = new StringBuffer();
		 sb.append(sau.getUsername());
		 if (sau.getRoleNames() != null && !sau.getRoleNames().isEmpty()) {
			sb.append("(");
			for (String roleCode : sau.getRoleNames()) {
			   sb.append(roleCode).append(", ");
			}
			sb.delete(sb.length() - 2, sb.length() - 1);
			sb.append(")");
		 }

		 audit.setCreateUserId(sau.getUserId());
		 audit.setCreateUserInfo(sb.toString());
	  } else {
		 audit.setCreateUserId(DEFAULT_CREATE_USER_ID);
		 audit.setCreateUserInfo(DEFAULT_CREATE_USER_INFO);
	  }

	  audit.setThreadInfo("[" + Thread.currentThread().getName() + "]: " + threadName);

	  if (t != null) {
		 audit.setStackTrace(t.getMessage() + ":\n" + ExceptionUtils.getStackTrace(t));
	  }

	  try {
		 if (isLogEnable) {
			getLogger().trace("isLogEnable = true");
			if (isQueue) {
			   getLogger().trace("isQueue = true");
			   auditQueue.add(audit);
			   if (auditQueue.size() >= MAX_AUDIT_QUEUE_SIZE) {
				  flushAuditQueue();
			   }
			} else {
			   getLogger().trace("isQueue = false");
			   save(audit);
			}
		 } else {
			getLogger().trace("isLogEnable = false");
		 }
	  } catch (PBKException e) {
		 throw e;
	  } catch (Exception e) {
		 throw new PBKException(
			 "Ошибка сохранения записи аудита:\n" + audit.toAuditString() + "\nОшибка: " + e.getMessage(), e);
	  }
	  return audit;
   }

   // @Scheduled(initialDelay = 1 * 10 * 1_000, fixedDelay = 1 * 10 * 1_000)
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   void flushAuditQueue() {
	  getLogger().trace("start flushAuditQueue");
	  Audit audit = auditQueue.poll();
	  while (audit != null) {
		 try {
			save(audit);
		 } catch (PBKException e) {
			getLogger().error(e.getMessage(), e);
		 }
		 audit = auditQueue.poll();
	  }
	  getLogger().trace("finish flushAuditQueue");
   }

   @Transactional(propagation = Propagation.REQUIRED)
   private Audit save(Audit audit) {
	  try {
		 int insertCount = auditMapper.insert(audit);
		 getLogger().trace("Insert into audit " + insertCount + " item(s)");
	  } catch (Exception e) {
		 throw new PBKException(
			 "Ошибка сохранения записи аудита:\n" + audit.toAuditString() + "\nОшибка: " + e.getMessage(), e);
	  }
	  return audit;
   }
}

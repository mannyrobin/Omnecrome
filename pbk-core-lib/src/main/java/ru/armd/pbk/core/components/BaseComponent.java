package ru.armd.pbk.core.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.authtoken.SecurityAdapterUser;
import ru.armd.pbk.components.AuditUtils;
import ru.armd.pbk.core.IHasCreater;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.core.IHasUpdater;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.AuditMapper;
import ru.armd.pbk.mappers.core.SettingsMapper;

import java.util.Date;

/**
 * Базовый класс всех компонентов. Содержит общие методы для всех уровней
 * приложения.
 */
public abstract class BaseComponent
	implements IComponent, IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(BaseComponent.class);

   @Autowired
   protected AuditMapper auditMapper;

   @Autowired
   protected AuditUtils auditUtils;

   @Autowired
   protected SettingsMapper settingsMapper;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Устанавливает дату создания записи и id пользователя, создавшего запись.
	* Если поля были уже иницализированы, то перезапись не произойдет.
	*
	* @param object
	* @return
	*/
   protected IHasCreater initCreaterInfo(IHasCreater object) {
	  if (object == null) {
		 return object;
	  }
	  if (object.getCreateDate() == null) {
		 object.setCreateDate(new Date());
	  }
	  if (object.getCreateUserId() == null) {
		 SecurityAdapterUser sau = AuthenticationManager.getUserInfo();
		 if (sau == null) {
			throw new PBKException("Невозможно получить информацию о текущем пользователе");
		 }
		 object.setCreateUserId(sau.getUserId());
	  }
	  return object;
   }

   /**
	* Устанавливает дату обновления записи и id пользователя, обновивего
	* запись.
	*
	* @param object
	* @return
	*/
   protected IHasUpdater initUpdaterInfo(IHasUpdater object) {
	  if (object == null) {
		 return object;
	  }
	  object.setUpdateDate(new Date());
	  SecurityAdapterUser sau = AuthenticationManager.getUserInfo();
	  if (sau == null) {
		 throw new PBKException("Невозможно получить информацию о текущем пользователе");
	  }
	  object.setUpdateUserId(sau.getUserId());

	  return object;
   }

   /**
	* Метод формирует запись аудита, сохраняет в БД в отдельной транзакции,
	* возвращает сформированную и вставленную запись.
	*
	* @param auditLevel   Уровень события.
	* @param auditType    Тип события.
	* @param objOperation Операция над объектом в событии.
	* @param object       объект, для которого событие возникло.
	* @param message      Сообщение.
	* @param t            Исключение.
	* @return
	*/
   @Deprecated
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public Audit logAuditOld(AuditLevel auditLevel, AuditType auditType, AuditObjOperation objOperation, Object object,
							String message, Throwable t) {
	  Audit audit = new Audit();
	  // ХЗ почему, но если удалить это метод, то приложение не стартует??!!!
	  return audit;
   }

   /**
	* Метод формирует запись аудита, сохраняет в БД в отдельной транзакции,
	* возвращает сформированную и вставленную запись.
	*
	* @param auditLevel   Уровень события.
	* @param auditType    Тип события.
	* @param objOperation Операция над объектом в событии.
	* @param object       объект, для которого событие возникло.
	* @param message      Сообщение.
	* @param t            Исключение.
	* @return
	*/
   public Audit logAudit(AuditLevel auditLevel, AuditType auditType, AuditObjOperation objOperation, Object object,
						 String message, Throwable t) {
	  Audit audit = null;
	  logLogger(auditLevel, message, t);
	  try {
		 audit = auditUtils.logAudit(auditLevel, auditType, objOperation, object, message, t,
			 AuthenticationManager.getUserInfo(), AuthenticationManager.getUserIPAddress(),
			 Thread.currentThread().getName(), null);
	  } catch (Exception e) {
		 LOGGER.error(e.getMessage(), e);
	  }
	  return audit;
   }

   protected void logLogger(AuditLevel auditLevel, String message, Throwable t) {
	  AuditLevel lAuditLevel = auditLevel != null ? auditLevel : AuditLevel.INFO;
	  if (AuditLevel.TRACE.equals(lAuditLevel)) {
		 getLogger().trace(message, t);
	  } else if (AuditLevel.DEBUG.equals(lAuditLevel)) {
		 getLogger().debug(message, t);
	  } else if (AuditLevel.INFO.equals(lAuditLevel)) {
		 getLogger().info(message, t);
	  } else if (AuditLevel.WARNING.equals(lAuditLevel)) {
		 getLogger().warn(message, t);
	  } else if (AuditLevel.ERROR.equals(lAuditLevel)) {
		 getLogger().error(message, t);
	  } else {
		 getLogger().info(message, t);
	  }
   }

   protected Setting getSetting(Settings settings)
	   throws PBKException {
	  try {
		 return settingsMapper.getById(settings.getId());
	  } catch (Exception e) {
		 LOGGER.error("Ошибка получения настройки: " + e.getMessage(), e);
		 throw new PBKException("Ошибка получения настройки: " + e.getMessage(), e);
	  }
   }

   protected String getSettingValue(Settings settings, String defValue) {
	  try {
		 Setting setting = getSetting(settings);
		 if (setting == null) {
			return defValue;
		 }
		 return setting.getValue();
	  } catch (PBKException e) {
		 return defValue;
	  }
   }

   protected String getSettingStringValue(Settings settings) {
	  return getSettingValue(settings, null);
   }

   protected boolean getSettingBooleanValue(Settings settings) {
	  String value = getSettingValue(settings, "false");
	  boolean result = (value != null) && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1"));
	  return result;
   }

}

package ru.armd.pbk.components.viss.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.repositories.viss.VisExchangeRepository;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Класс с методами взаимодействия с ВИС АСДУ-НГПТ высокого уровня. Каждая
 * задача запускается в отдельном процессе.
 */

/**
 * Базовый класс процессора взаимодействия с ВИС. Содержит общие методы всех
 * процессоров ВИС.
 */
public abstract class BaseExchangeProcessor
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(BaseExchangeProcessor.class);

   protected final String AUTH_USER_NAME = "ADMIN";
   protected final String AUTH_USER_PASS = "ADMIN";

   @Autowired
   protected AuthenticationManager authenticationManager;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   @Autowired
   private VisExchangeRepository visExchangeRepository;

   private AuditType visAsduType;

   /**
	* Конструктор.
	*
	* @param visAsduType тип аудита.
	*/
   public BaseExchangeProcessor(AuditType visAsduType) {
	  this.visAsduType = visAsduType;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС АСДУ.
	*
	* @param visExchangeObject Тип обмена с ВИС.
	*/
   protected void doProcess(VisExchangeObjects visExchangeObject, VisExchangeOperations visExchangeOperation,
							String parameter, Date start, Date end, boolean force) {
	  try {
		 logAudit(AuditLevel.DEBUG, visAsduType, AuditObjOperation.EXCHANGE, null, Thread.currentThread().getName()
			 + " Отправка запроса в ВИС: visExchangeObject = " + visExchangeObject.getId(), null);

		 // Проверяю, разрешен ли обмен
		 if (!getSettingBooleanValue(Settings.VIS_EXCHANGE_ALLOW)) {
			logAudit(AuditLevel.INFO, VisAuditType.VIS_ASDU, AuditObjOperation.EXCHANGE, null,
				Thread.currentThread().getName() + "Выполнение задачи по информационному обмену отключено",
				null);
			return;
		 }

		 // Получаю конфигурации для обмена с ВИС
		 if (visExchangeOperation == VisExchangeOperations.IMPORT) {
			 List<VisExchangeConfig> visExchangeConfigs = visExchangeConfigRepository
				.getActiveImportConfigs(visExchangeObject);
			 for (VisExchangeConfig visExchangeConfig : visExchangeConfigs) {
				 doProcessExchangeConfig(visExchangeConfig, parameter, start, end, force);
			 }
		 } else {
			List<VisExchangeConfig> visExchangeConfigs = visExchangeConfigRepository
				.getActiveExportConfigs(visExchangeObject);
			for (VisExchangeConfig visExchangeConfig : visExchangeConfigs) {
			   doProcessExchangeConfig(visExchangeConfig, parameter, start, end, force);
			}
		 }
	  } catch (Throwable t) {
		 logAudit(AuditLevel.ERROR, VisAuditType.VIS_ASDU, AuditObjOperation.EXCHANGE, null,
			 Thread.currentThread().getName() + "exchange error: visExchangeObject = "
				 + visExchangeObject.getId(),
			 t);
	  }
   }

	/**
	 * Метод производит взаимодействие с ВИС согласно своей конфигурации.
	 *
	 * @param visExchangeConfig Конфигурация.
	 */
	protected void doProcessExchangeConfig(VisExchangeConfig visExchangeConfig, String parameter,
										   Date start, Date end, boolean force) {
		logAudit(AuditLevel.DEBUG, VisAuditType.VIS_ASDU,
				AuditObjOperation.EXCHANGE, null, Thread.currentThread().getName()
						+ "doProcessExchangeConfig: visExchangeObject = "
						+ visExchangeConfig.getExchangeObjectId(), null);

		List<Date> datesToSend = null;
		// на сколько глубоко смотреть вынесено в конфиг обмена
		int daysOfDeep = -visExchangeConfig.getReRequestDepth();
		if (start == null && end == null) {
			end = new Date();
			start = DateUtils.addDays(end, daysOfDeep);
		} else if (end == null) {
			end = start;
			start = DateUtils.addDays(end, daysOfDeep);
		} else if (start == null) {
			start = DateUtils.addDays(end, daysOfDeep);
		}

		// Смотрим, что не отправляли ранее или что обломилось.
		datesToSend = visExchangeRepository.getWorkDatesToSend(visExchangeConfig.getId(), start, end, force);

		// Производим отправку на дату
		for (Date dateToSend : datesToSend) {
			doExchange(visExchangeConfig, parameter, dateToSend, force);
		}
	}

   /**
	* Метод производит выбор провайдера по типу взаимодействия и вызывает само
	* взаимодействие.
	*
	* @param visExchangeConfig Конфигурация.
	* @param workDate          Дата, на которую нужно проихвести взаимодействие.
	*/
   protected abstract void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate,
									  boolean force);
}

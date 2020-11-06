package ru.armd.pbk.components.viss.asdu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.asdu.driver.AsduDriverExchangeProvider;
import ru.armd.pbk.components.viss.asdu.equipment.AsduEquipmentExchangeProvider;
import ru.armd.pbk.components.viss.asdu.plantrip.AsduPlanTripExchangeProvider;
import ru.armd.pbk.components.viss.asdu.stop.AsduStopExchangeProvider;
import ru.armd.pbk.components.viss.asdu.telematic.AsduTelematicExchangeProvider;
import ru.armd.pbk.components.viss.asdu.trip.AsduTripExchangeProvider;
import ru.armd.pbk.components.viss.asdu.vehicle.AsduVenicleExchangeProvider;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Класс с методами взаимодействия с ВИС АСДУ-НГПТ высокого уровня. Каждая
 * задача запускается в отдельном процессе.
 */
@Component
public class AsduExchangeProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(AsduExchangeProcessor.class);

   @Autowired
   private AsduDriverExchangeProvider asduDriverExchangeProvider;

   @Autowired
   private AsduVenicleExchangeProvider asduVenicleExchangeProvider;

   @Autowired
   private AsduEquipmentExchangeProvider asduEquipmentExchangeProvider;

   @Autowired
   private AsduStopExchangeProvider asduStopExchangeProvider;

   @Autowired
   private AsduTelematicExchangeProvider asduTelematicExchangeProvider;

   @Autowired
   private AsduTripExchangeProvider asduTripExchangeProvider;

   @Autowired
   private AsduPlanTripExchangeProvider asduPlanTripExchangeProvider;

   AsduExchangeProcessor() {
	  super(VisAuditType.VIS_ASDU);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС АСДУ.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.ASDU_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС АСДУ.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.ASDU_EXECUTOR)
   public void doExport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  logAudit(AuditLevel.DEBUG, VisAuditType.VIS_ASDU, AuditObjOperation.EXPORT, null,
		  Thread.currentThread().getName() + "doExport: visExchangeObject = " + visExchangeObject.getId(), null);

	  doProcess(visExchangeObject, VisExchangeOperations.EXPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.ASDU_GTFS_DRIVER.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduDriverExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_VENICLE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduVenicleExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_EQUIPMENT.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduEquipmentExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_STOP.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduStopExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_TELEMATIC.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduTelematicExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_TRIP.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduTripExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASDU_GTFS_PLANTRIP.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asduPlanTripExchangeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }
}

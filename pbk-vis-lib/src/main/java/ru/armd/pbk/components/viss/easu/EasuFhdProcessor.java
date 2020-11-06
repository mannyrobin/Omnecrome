package ru.armd.pbk.components.viss.easu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.components.viss.easu.bsk.EasuFhdBskProvider;
import ru.armd.pbk.components.viss.easu.department.EasuFhdDepartmentProvider;
import ru.armd.pbk.components.viss.easu.employee.EasuFhdEmployeeProvider;
import ru.armd.pbk.components.viss.easu.skk.EasuFhdSkkProvider;
import ru.armd.pbk.components.viss.easu.triporder.EasuTripOrderProvider;
import ru.armd.pbk.components.viss.easu.tripschedule.EasuTripScheduleProvider;
import ru.armd.pbk.components.viss.easu.venicle.EasuFhdVenicleProvider;
import ru.armd.pbk.components.viss.easu.workmode.EasuFhdWorkModeProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Класс с бизнес-логикой обращения к ВИС и анализу результата.
 */
@Component
public class EasuFhdProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(EasuFhdProcessor.class);

   @Autowired
   private EasuFhdDepartmentProvider easuFhdDepartmentProvider;

   @Autowired
   private EasuFhdEmployeeProvider easuFhdEmployeeProvider;

   @Autowired
   private EasuTripOrderProvider easuTripOrderProvider;

   @Autowired
   private EasuTripScheduleProvider easuTripScheduleProvider;

   @Autowired
   private EasuFhdWorkModeProvider easuFhdWorkModeProvider;

   @Autowired
   private EasuFhdVenicleProvider easuFhdVenicleProvide;

   @Autowired
   private EasuFhdBskProvider easuFhdBskProvider;

   @Autowired
   private EasuFhdSkkProvider easuFhdSkkProvider;

   /**
	* Конструктор по умолчанию.
	*/
   public EasuFhdProcessor() {
	  super(VisAuditType.VIS_EASUFHD);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС ЕАСУ ФХД.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.EASUFHD_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.EASU_FHD_DEPARTMENT.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdDepartmentProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_ORDER.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuTripOrderProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_TRIP_SCHEDULE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuTripScheduleProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_EMPLOYEE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdEmployeeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_WORK_MODE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdWorkModeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_VENICLE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdVenicleProvide.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_SKK.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdSkkProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.EASU_FHD_BSK.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 easuFhdBskProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }
}

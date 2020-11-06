package ru.armd.pbk.components.viss.gkuop;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.components.viss.gkuop.employee.GkuopExchangeEmployeeProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Процессор для обмена с ВИС ГКУ ОП.
 */
@Component
public class GkuopExchangeProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(GkuopExchangeProcessor.class);

   @Autowired
   private GkuopExchangeEmployeeProvider employeeProvider;

   /**
	* Конструктор по умолчанию.
	*/
   public GkuopExchangeProcessor() {
	  super(VisAuditType.VIS_GKUOP);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод, запускающий процесс информационного обмена с ГКУ ОП.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.GKUOP_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.GKUOP_EMPLOYEE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 employeeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }

}

package ru.armd.pbk.components.viss.asmpp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.asmpp.providers.AsmppStopProvider;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

@Component
public class AsmppExchangeProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(AsmppExchangeProcessor.class);

   @Autowired
   private ObjectFactory<AsmppStopProvider> asmppStopProviderFactory;

   /**
	* Конструктор по умолчанию.
	*/
   public AsmppExchangeProcessor() {
	  super(VisAuditType.VIS_ASMPP);
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС ДОЗОР ПРО.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.ASMPP_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.ASMPP_STOPS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 asmppStopProviderFactory.getObject().doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }
}

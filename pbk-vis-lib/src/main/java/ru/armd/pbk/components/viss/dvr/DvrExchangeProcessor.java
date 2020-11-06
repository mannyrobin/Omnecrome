package ru.armd.pbk.components.viss.dvr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.components.viss.dvr.dvr.DvrProvider;
import ru.armd.pbk.components.viss.dvr.records.DvrRecordExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Класс с бизнес-логикой обращения к ВИС ДОЗОР ПРО и анализу результата.
 */
@Component
public class DvrExchangeProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(DvrExchangeProcessor.class);

   @Autowired
   private ObjectFactory<DvrRecordExchangeProvider> dvrRecordExchangeProviderFactory;

   @Autowired
   private ObjectFactory<DvrProvider> dvrProviderFactory;

   /**
	* Конструктор по умолчанию.
	*/
   public DvrExchangeProcessor() {
	  super(VisAuditType.VIS_DVR);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
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
   @Async(IVisExecutor.DVR_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.DVR_DVR.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 dvrProviderFactory.getObject().doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.DVR_RECORD_MOK1.getId().equals(visExchangeConfig.getExchangeObjectId())
		  || VisExchangeObjects.DVR_RECORD_MOK2.getId().equals(visExchangeConfig.getExchangeObjectId())
		  || VisExchangeObjects.DVR_RECORD_MOK3.getId().equals(visExchangeConfig.getExchangeObjectId())
		  || VisExchangeObjects.DVR_RECORD_MOK4.getId().equals(visExchangeConfig.getExchangeObjectId())
		  || VisExchangeObjects.DVR_RECORD_MOK5.getId().equals(visExchangeConfig.getExchangeObjectId())) {

		 dvrRecordExchangeProviderFactory.getObject().doExchange(visExchangeConfig, parameter, workDate, force);

	  }
   }

}

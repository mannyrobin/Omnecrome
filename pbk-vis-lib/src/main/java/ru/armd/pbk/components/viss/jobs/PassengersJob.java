package ru.armd.pbk.components.viss.jobs;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.domain.viss.intervals.Passengers;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.intervals.PassengersMapper;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Джоб расчета пассажиропотока.
 */
@Component
public class PassengersJob
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(PassengersJob.class);

   @Autowired
   private PassengersMapper mapper;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   private AuditType auditType = VisAuditType.VIS_ASDU_STOPINTERVALS;
   private AuditObjOperation auditObjOperation = AuditObjOperation.IMPORT;

   /**
	* В ручную запустить можно так (end необязательный параметр)
	* http://127.0.0.1:82/api/pbk/job?name=passengers&start=20160920&end=20160921.
	*
	* @param date date.
	*/
   @Async(IVisExecutor.PASSENGERS_EXECUTOR)
   public void calculate(Date start, Date end) {
	  if (end == null) {
		 end = start;
	  }
	  while (!start.after(end)) {
		 calculate(start);
		 start = DateUtils.addDays(start, 1);
	  }
   }

   private void calculate(Date date) {
	  int all = 0;
	  int processed = 0;
	  VisExchangeConfig visExchangeConfig = null;
	  try {
		 date = DateUtils.truncate(date, Calendar.DATE);

		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Начинаю подсчет пассажиропотока на дату " + date, null);

		 try {
			visExchangeConfig = visExchangeConfigRepository.getActiveImportConfig(VisExchangeObjects.ASMPP_PASSENGERS);
			if (visExchangeConfig == null) {
			   throw new PBKException("visExchangeConfig is null");
			}
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Невозможно получить активную конфигурацию ВИС АСМПП", e);
			return;
		 }

		 List<Passengers> passengers = mapper.getPassengers(date);
		 Date start = date;
		 Date end = DateUtils.addDays(date, 1);
		 for (Passengers item : passengers) {
			processRow(visExchangeConfig, date, item, start, end);
			++processed;
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Неизвестная ошибка", e);
	  } finally {
		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Пассажиропоток на дату " + date
				 + " расчитан для " + processed + " маршрутов из " + all, null);
	  }
   }

   protected void processRow(VisExchangeConfig visExchangeConfig, Date date, Passengers passengers, Date start, Date end) {
	  BaseHttpClientParameters parameters = AsmppTools.getMarshPsgAvgValueParameters(
		  visExchangeConfig, date, passengers.getRouteCode(), start, end);
	  try {
		 mapper.updateAsmppCounts(passengers.getId(), AsmppTools.getMarshPsgAvgValue(parameters));
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Ошибка запроса к АСМПП: " + parameters.getServiceAddress(), e);
	  }
   }
}

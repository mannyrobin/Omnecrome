package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.mappers.viss.intervals.StopIntervalsMapper;
import ru.armd.pbk.repositories.So16Repository;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;

import java.util.List;

/**
 * Сервис стандартного отчёта "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно".
 */
@Service
public class So16Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So16Service.class);

   @Autowired
   StopIntervalsMapper stopIntervalsMapper;

   @Autowired
   private VisExchangeConfigRepository visExchangeConfigRepository;

   @Autowired
   So16Service(So16Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Скачать данные из ASMPP.
	*
	* @param ids идс.
	*/
   public void downloadAsmppData(List<Long> ids) {
		/*
		Сейчас это не будет работать, тк схема изменена, в новых данных нет ermId
		VisExchangeConfig visExchangeConfig = null;
		AuditType auditType = VisAuditType.VIS_ASDU_STOPINTERVALS;
		AuditObjOperation auditObjOperation = AuditObjOperation.IMPORT;

		try {
			visExchangeConfig = visExchangeConfigRepository.getActiveImportConfig(VisExchangeObjects.ASMPP_PASSENGERS);
		} catch (Exception e) {
			logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
					Thread.currentThread().getName() + " Невозможно получить активную конфигурацию ВИС АСМПП", e);
			return;
		}
		
		List<StopInterval> stopIntervals = stopIntervalsMapper.getStopIntervals(ids);
		for (StopInterval interval : stopIntervals) {
			AsmppPsgAvg asmpp = null;
			BaseHttpClientParameters parameters = AsmppTools.getStopPsgAvgValueParameters(visExchangeConfig,
					interval.getDate(),
					interval.getErmStopId(),
					new Date(interval.getStopArrivalTime().getTime() - 60000),
					new Date(interval.getStopArrivalTime().getTime() + 20000));
			try {
				asmpp = AsmppTools.getStopPsgAvgValue(interval.getRouteCode(), parameters);
			} catch (Exception e) {
				logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
						Thread.currentThread().getName() + " Ошибка запроса к АСМПП: " + parameters.getServiceAddress(), e);
			}
			
			if (asmpp != null) {
				interval.setAsmppInCount(asmpp.getIn());
				interval.setAsmppOutCount(asmpp.getOut());
				stopIntervalsMapper.updateAsmppCounts(interval);
			}
		}*/
   }
}

package ru.armd.pbk.components.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;
import ru.armd.pbk.mappers.viss.gismgt.GmGisMapper;

import java.util.Date;
import java.util.List;

/**
 * Класс с бизнес-логикой обращения к ВИС и анализу результата.
 */
@Component
public class GisMgtProcessor
	extends BaseExchangeProcessor {

   public static final Logger LOGGER = Logger.getLogger(GisMgtProcessor.class);

   @Autowired
   private GisMgtProvider gisMgtProvider;

   @Autowired
   private GmGisMapper gmGisMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GisMgtProcessor() {
	  super(VisAuditType.VIS_GISMGT_UPDATE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС ГИС МГТ.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.GISMGT_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
       doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
       LOGGER.info("Обработка файлов выгрузки завершена");
       updateRouteStops();
       LOGGER.info("Обновление остановок завершено");
   }

    @Transactional
    void updateRouteStops() {
        List<Long> routes = gmGisMapper.getRouteIdsList();
        if (routes != null) {
            for (Long route : routes) {
                gmGisMapper.insertActiveRouteStops(route);
                LOGGER.debug("Обновлены остановки маршрута c ID " + route);
            }
            gmGisMapper.updateInactiveRouteStops();
            gmGisMapper.deleteInactiveRouteStops();
        }
    }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.GIS_MGT_OBJECTS.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 gisMgtProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }
}

package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So1Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So1EmployeeSchedulesTableView;

/**
 * Сервис стандартного отчёта
 * "График работы контролеров по территориальному подразделению".
 */
@Service
public class So1Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So1Service.class);

   private So1Repository repository;

   @Autowired
   So1Service(So1Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  So1EmployeeSchedulesTableView ticketTypePassCountsViews = repository.getEmployeesSchedules(params);
	  return createJsonGridData(ticketTypePassCountsViews.asSingleList(), params.getPage(), params.getCount());
   }

   /**
	* Получить данные для отчета
	* "График работы контролеров по территориальному подразделению".
	*
	* @param params параметры.
	* @return
	*/
   public JsonGridData getPreviewGridViews(BaseGridViewParams params) {
	  So1EmployeeSchedulesTableView ticketTypePassCountsViews = repository.getEmployeesSchedules(params);
	  return createJsonGridData(ticketTypePassCountsViews, params.getPage(), params.getCount());
   }
}

package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.matchers.So2Matcher;
import ru.armd.pbk.repositories.So2Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So2EmployeeView;

import java.util.List;

/**
 * Сервис стандартного отчёта "Табель фактически отработанного времени".
 */
@Service
public class So2Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So2Service.class);

   private So2Repository repository;

   @Autowired
   So2Service(So2Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So2EmployeeView> employeeShiftHours = So2Matcher.INSTANCE.getSo2Views(repository.getEmployeesShiftHours(params));
	  return createJsonGridData(employeeShiftHours, params.getPage(), params.getCount());
   }
}

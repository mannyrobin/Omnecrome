package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So23Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So23EmplSummariesView;

import java.util.List;

/**
 * Сервис стандартного отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее значение
 * численности за период".
 */
@Service
public class So23Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So23Service.class);

   private So23Repository repository;

   @Autowired
   So23Service(So23Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So23EmplSummariesView> EmployeeSummaries = repository.getEmployeeSummaries(params);
	  return createJsonGridData(EmployeeSummaries, params.getPage(), params.getCount());
   }
}

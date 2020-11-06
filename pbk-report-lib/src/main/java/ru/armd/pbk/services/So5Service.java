package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So5Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So5BranchEmplSummariesView;

import java.util.List;

/**
 * Сервис стандартного отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее значение
 * численности за период".
 */
@Service
public class So5Service
	extends SoService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So5Service.class);

   private So5Repository repository;

   @Autowired
   So5Service(So5Repository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<So5BranchEmplSummariesView> branchEmployeeSummaries = repository.getBranchEmployeeSummaries(params);
	  return createJsonGridData(branchEmployeeSummaries, params.getPage(), params.getCount());
   }
}

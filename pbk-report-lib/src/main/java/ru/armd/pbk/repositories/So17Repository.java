package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So17Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий стандартного отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)".
 */
@Repository
public class So17Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So17Repository.class);

   @Autowired
   So17Repository(So17Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO17, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  if (!params.getFilter().containsKey("routes")) {
		 return new LinkedList<Views>();
	  }
	  return super.getGridViews(params);
   }
}

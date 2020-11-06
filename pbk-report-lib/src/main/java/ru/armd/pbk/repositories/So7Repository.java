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
import ru.armd.pbk.mappers.report.standard.So7Mapper;

import java.util.List;

/**
 * Репозиторий стандартного отчёта "Количество перевезенных пассажиров по маршрутам".
 */
@Repository
public class So7Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So7Repository.class);

   @Autowired
   So7Repository(So7Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO7, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }
}

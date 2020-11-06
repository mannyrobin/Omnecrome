package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So4Mapper;

import java.util.List;

/**
 * Репозиторий стандартного отчёта "Ежедневная посменная численность контролёров ГУП "Мосгортранс"
 * по территориальному подразделению".
 */
@Repository
public class So4Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So4Repository.class);

   @Autowired
   So4Repository(So4Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO4, mapper);
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

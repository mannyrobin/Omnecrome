package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So14Mapper;

/**
 * Репозиторий стандартного отчёта "Результаты ПБК за период".
 */
@Repository
public class So14Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So14Repository.class);

   @Autowired
   So14Repository(So14Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO14, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

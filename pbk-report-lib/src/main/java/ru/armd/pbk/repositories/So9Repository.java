package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So9Mapper;

/**
 * Репозиторий стандартного отчёта "Сводные данные по работе контролеров по подразделениям".
 */
@Repository
public class So9Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So9Repository.class);

   @Autowired
   So9Repository(So9Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO9, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

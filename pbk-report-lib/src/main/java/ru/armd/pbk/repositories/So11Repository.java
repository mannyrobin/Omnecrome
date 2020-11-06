package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So11Mapper;

/**
 * Репозиторий стандартного отчёта "Работа контролеров".
 */
@Repository
public class So11Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So11Repository.class);

   @Autowired
   So11Repository(So11Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO11, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

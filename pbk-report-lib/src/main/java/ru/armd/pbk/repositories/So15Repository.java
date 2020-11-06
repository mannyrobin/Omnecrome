package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So15Mapper;

/**
 * Репозиторий стандартного отчёта "Итоги ПБК по контролеру по данным АСУ ПБК".
 */
@Repository
public class So15Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So15Repository.class);

   @Autowired
   So15Repository(So15Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO15, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

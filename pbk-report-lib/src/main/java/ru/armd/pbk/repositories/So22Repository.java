package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So22Mapper;

/**
 * Репозиторий стандартного отчёта "Сверка с ГКУ ОП".
 */
@Repository
public class So22Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So22Repository.class);

   @Autowired
   So22Repository(So22Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO22, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

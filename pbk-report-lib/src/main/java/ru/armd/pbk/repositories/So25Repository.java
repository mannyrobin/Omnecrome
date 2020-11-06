package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So24Mapper;
import ru.armd.pbk.mappers.report.standard.So25Mapper;

/**
 * Репозиторий стандартного отчёта "Cписок маршрутов АСМ-ПП".
 */
@Repository
public class So25Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So25Repository.class);

   @Autowired
   So25Repository(So25Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO25, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}

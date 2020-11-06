package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So24Mapper;

/**
 * Репозиторий стандартного отчёта "Сводные данные по наряд заданиям".
 */
@Repository
public class So24Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So24Repository.class);

   @Autowired
   So24Repository(So24Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO24, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}

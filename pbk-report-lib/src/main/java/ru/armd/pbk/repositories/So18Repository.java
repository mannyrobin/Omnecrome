package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So18Mapper;

/**
 * Репозиторий стандартного отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП".
 */
@Repository
public class So18Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So18Repository.class);

   @Autowired
   So18Repository(So18Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO18, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.So20Mapper;
import ru.armd.pbk.views.report.So20CheckPlanView;

import java.util.List;

/**
 * Created by Yakov Volkov.
 * Репозиторий стандартного отчёта "Статистика проверок маршрута".
 */
@Repository
public class So20Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So20Repository.class);

   @Autowired
   So20Repository(So20Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO20, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public List<So20CheckPlanView> getCheckPlanView() {

	  List<So20CheckPlanView> views;
	  try {
		 views = ((So20Mapper) getDomainMapper()).getCheckPlanView();
	  } catch (Exception e) {
		 String message = "Не удалось получить список checkPlanView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }

	  return views;
   }
}

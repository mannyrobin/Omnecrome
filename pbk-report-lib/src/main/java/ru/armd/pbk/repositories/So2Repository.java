package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.So2DomainView;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.So2Mapper;

/**
 * Репозиторий стандартного отчёта "Табель фактически отработанного времени".
 */
@Repository
public class So2Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So2Repository.class);

   @Autowired
   private So2Mapper so2Mapper;

   @Autowired
   So2Repository(So2Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO2, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить отработанное время контролёров за все дни.
	*
	* @param params параметры фильтрации
	* @return отработанное время контролёров за все дни
	*/
   @DepartmentAuthorization
   public So2DomainView getEmployeesShiftHours(BaseGridViewParams params) {
	  So2DomainView so2Domain = new So2DomainView();
	  so2Domain.setStartDate(so2Mapper.getDateStart(params));
	  so2Domain.setEndDate(so2Mapper.getDateEnd(params));
	  try {
		 so2Domain.setEmployees(so2Mapper.getEmployees(params));
		 if (so2Domain.getEmployees() != null && !so2Domain.getEmployees().isEmpty()) {
			so2Domain.setShifts(
				so2Mapper.getShiftHoursForEmployee(params, so2Domain.getEmployees()));
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения стандартного отчета №2. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_SCHEDULE, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return so2Domain;
   }
}

package ru.armd.pbk.repositories.plans.timesheets;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.timesheets.PlanTimesheetView;
import ru.armd.pbk.domain.plans.timesheets.Timesheet;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.plans.timesheets.PlanTimesheetMapper;

/**
 * Репозиторий табелей отдела.
 */
@Repository
public class PlanTimesheetRepository
	extends BaseDomainRepository<Timesheet> {

   public static final Logger LOGGER = Logger.getLogger(PlanTimesheetRepository.class);

   private PlanTimesheetMapper planTimesheetMapper;

   @Autowired
   PlanTimesheetRepository(PlanTimesheetMapper mapper) {
	  super(PlanAuditType.PLAN_SCHEDULE, mapper);
	  this.planTimesheetMapper = mapper;
   }

   /**
	* Получает домен табелей отдела по параметрам.
	*
	* @param params параметры
	* @return домен табелей отдела
	*/
   public PlanTimesheetView getPlanTimesheets(BaseGridViewParams params) {
	  PlanTimesheetView planTimesheet = new PlanTimesheetView();
	  try {
		 planTimesheet.setEmployees(planTimesheetMapper.getEmployeeForPlanTimesheetViews(params));
		 if (planTimesheet.getEmployees() != null && !planTimesheet.getEmployees().isEmpty()) {
			planTimesheet.setTimesheets(
				planTimesheetMapper.getPlanTimesheetsForEmployees(params, planTimesheet.getEmployees()));
			planTimesheet.setReserves(planTimesheetMapper.getReserveList(params));
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список табелей. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_TIMESHEET, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return planTimesheet;
   }
}

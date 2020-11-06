package ru.armd.pbk.services.plans.timesheet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.timesheets.Timesheet;
import ru.armd.pbk.dto.plans.timesheets.TimesheetDTO;
import ru.armd.pbk.matcher.plans.PlanTimesheetMatcher;
import ru.armd.pbk.repositories.plans.timesheets.PlanTimesheetRepository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.plans.timesheets.EmployeePlanTimesheetsView;

import java.util.List;

/**
 * Сервис табелей отдела.
 */
@Service
public class PlanTimesheetService
	extends BaseDomainService<Timesheet, TimesheetDTO> {

   private static final Logger LOGGER = Logger.getLogger(PlanTimesheetService.class);

   private PlanTimesheetRepository repository;

   @Autowired
   PlanTimesheetService(PlanTimesheetRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Timesheet toDomain(TimesheetDTO dto) {
	  return PlanTimesheetMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TimesheetDTO toDTO(Timesheet domain) {
	  return PlanTimesheetMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<EmployeePlanTimesheetsView> planTimesheetView = PlanTimesheetMatcher.INSTANCE
		  .getPlanTimesheetView(repository.getPlanTimesheets(params));
	  return createJsonGridData(planTimesheetView, params.getPage(), params.getCount());
   }
}
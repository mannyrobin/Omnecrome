package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.report.standard.So12Mapper;
import ru.armd.pbk.views.report.So12EmployeeCountView;
import ru.armd.pbk.views.report.So12VenueShiftEmployeeCountsView;
import ru.armd.pbk.views.report.So12VenueShiftView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий стандартного отчёта "Совместный график по местам встреч".
 */
@Repository
public class So12Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So12Repository.class);

   @Autowired
   private So12Mapper so12Mapper;

   @Autowired
   So12Repository(So12Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO12, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить список мест встреч и часов работы с количествами контролёров по датам.
	*
	* @param params параметры фильтрации
	* @return список мест встреч с количествами контролёров по дням
	*/
   @DepartmentAuthorization
   public List<So12VenueShiftEmployeeCountsView> getVenuesEmployeeCounts(BaseGridViewParams params) {
	  List<So12VenueShiftEmployeeCountsView> venueShiftsEmployeeCounts = new ArrayList<>();
	  try {
		 List<So12VenueShiftView> venuesShifts = so12Mapper.getVenueShifts(params);
		 Date dateStart = so12Mapper.getDateStart(params);
		 Date dateEnd = so12Mapper.getDateEnd(params);
		 for (So12VenueShiftView venueShift : venuesShifts) {
			List<So12EmployeeCountView> employeeCounts =
				so12Mapper.getEmployeeCountsForVenueShift(params, venueShift.getVenueId(), venueShift.getShiftId());
			So12VenueShiftEmployeeCountsView venueShiftEmployeeCounts =
				new So12VenueShiftEmployeeCountsView(venueShift, employeeCounts, dateStart, dateEnd);
			venueShiftsEmployeeCounts.add(venueShiftEmployeeCounts);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить мест встреч с количествами контролёров. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO12, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKReportException(message, e);
	  }
	  return venueShiftsEmployeeCounts;
   }

   @Override
   @DepartmentAuthorization
   public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
	  So12VenueShiftEmployeeCountsView result = null;
	  List<So12EmployeeCountView> view = null;
	  try {
		 view = so12Mapper.getGridViewTotal(params);
		 result = new So12VenueShiftEmployeeCountsView(null, view, null, null);
	  } catch (Exception e) {
		 String message = "Не удалось получить значение Итого. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return (View) result;
   }
}

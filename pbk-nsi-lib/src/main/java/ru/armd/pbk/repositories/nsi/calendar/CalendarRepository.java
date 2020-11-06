package ru.armd.pbk.repositories.nsi.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.enums.nsi.WorkDayType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.calendar.CalendarMapper;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий календаря.
 */
@Repository
public class CalendarRepository
	extends BaseComponent {

   @Autowired
   private CalendarMapper calendarMapper;

   /**
	* Изменить календарь на дату.
	*
	* @param day день.
	*/
   public Calendar updateDay(Calendar day) {
	  initUpdaterInfo(day);
	  day.setUpdateDate(new Date());
	  try {
		 calendarMapper.updateDay(day);
		 return day;
	  } catch (Exception e) {
		 String message = "Не удалось обновить каледнарь на день: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CALENDAR, AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Заполнить календарь на дату.
	*
	* @param day день.
	*/
   public Calendar createDay(Calendar day) {
	  initCreaterInfo(day);
	  initUpdaterInfo(day);
	  day.setWorkDate(DateUtils.shiftToDayStart(day.getWorkDate()));
	  try {
		 calendarMapper.createDay(day);
		 return day;
	  } catch (Exception e) {
		 String message = "Не удалось заполнить каледнарь на день: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CALENDAR, AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Календарь на период.
	*
	* @param from с
	* @param to   по
	* @return
	*/
   public List<Calendar> getCalendarForPeriod(Date from, Date to) {
	  List<Calendar> calendar = null;
	  try {
		 calendar = calendarMapper.getCalendarForPeriod(from, to);
	  } catch (Exception e) {
		 String message = "Не удалось получить список дней календаря на период. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CALENDAR, AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return calendar;
   }

   /**
	* Календарь на дату.
	*
	* @param day дата
	* @return
	*/
   public Calendar getCalendarByDay(Date day) {
	  Calendar calendar = null;
	  try {
		 calendar = calendarMapper.getCalendarByDay(day);
	  } catch (Exception e) {
		 String message = "Не удалось получить день календаря на дату. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CALENDAR, AuditObjOperation.SELECT, day, message, e);
		 throw new PBKException(message, e);
	  }
	  return calendar;
   }

   /**
	* Получить количество рабочих дней в указанном периоде
	*
	* @param from с
	* @param to   по
	* @return количество рабочих дней
	*/
   public Integer getCountWorkDays(Date from, Date to) {
	  List<Calendar> period = getCalendarForPeriod(from, to);
	  int countWorkDays = 0;
	  for (Calendar day : period) {
		 if (day.getWorkDayTypeId().equals(WorkDayType.WORK_DAY.getId())) {
			countWorkDays++;
		 }
	  }
	  return countWorkDays;
   }
}

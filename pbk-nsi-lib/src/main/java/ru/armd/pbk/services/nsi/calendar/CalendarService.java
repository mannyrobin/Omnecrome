package ru.armd.pbk.services.nsi.calendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.dto.nsi.CalendarDTO;
import ru.armd.pbk.matcher.nsi.ICalendarMatcher;
import ru.armd.pbk.repositories.nsi.calendar.CalendarRepository;
import ru.armd.pbk.utils.date.DateRange;
import ru.armd.pbk.utils.date.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Сервис календаря.
 */
@Service
public class CalendarService {

   @SuppressWarnings("unused")
   private static final Logger LOG = Logger.getLogger(CalendarService.class);

   private static final LocalDate HOLIDAYS[] = {new LocalDate(1970, 1, 1), new LocalDate(1970, 1, 2),
	   new LocalDate(1970, 1, 3), new LocalDate(1970, 1, 4), new LocalDate(1970, 1, 5), new LocalDate(1970, 1, 6),
	   new LocalDate(1970, 1, 7), new LocalDate(1970, 1, 8), new LocalDate(1970, 2, 23), new LocalDate(1970, 3, 8),
	   new LocalDate(1970, 5, 1), new LocalDate(1970, 5, 9), new LocalDate(1970, 6, 12),
	   new LocalDate(1970, 11, 4)};

   @Autowired
   private CalendarRepository calendarRepository;

   /**
	* Календарь на период.
	*
	* @param from с
	* @param to   по
	* @return
	*/
   public List<CalendarDTO> getCalendarForPeriod(Date from, Date to) {
	  return ICalendarMatcher.INSTANCE.domainListToDtoList(calendarRepository.getCalendarForPeriod(from, to));
   }

   /**
	* Календарь на дату.
	*
	* @param day дата
	* @return
	*/
   public CalendarDTO getCalendarByDay(Date day) {
	  return ICalendarMatcher.INSTANCE.toDTO(calendarRepository.getCalendarByDay(day));
   }

   /**
	* Изменить календарь на дату.
	*
	* @param day день
	*/
   @Transactional
   public CalendarDTO updateDay(CalendarDTO day) {
	  return ICalendarMatcher.INSTANCE
		  .toDTO(calendarRepository.updateDay(ICalendarMatcher.INSTANCE.toDomain(day)));
   }

   /**
	* Заполнить календарь на дату.
	*
	* @param day день
	*/
   @Transactional
   public CalendarDTO createDay(CalendarDTO day) {
	  return ICalendarMatcher.INSTANCE
		  .toDTO(calendarRepository.createDay(ICalendarMatcher.INSTANCE.toDomain(day)));
   }

   /**
	* Заполнение календаря.
	*
	* @param from дата начала.
	* @param to   дата окончания.
	*/
   @Transactional
   public void fillCalendar(Date from, Date to) {
	  // Сдвиг на час для правильного определения даты.
	  from.setTime(from.getTime() + 3600000);
	  to.setTime(to.getTime() + 3600000);
	  DateFormat df = new SimpleDateFormat("dd MMMM YYYY");
	  for (LocalDate date : new DateRange(new LocalDate(from), new LocalDate(to))) {
		 Date day = date.toDateTimeAtStartOfDay().toDate();
		 day = DateUtils.shiftToDayStart(day);

		 Calendar calendar = new Calendar();
		 calendar.setCode(df.format(day));
		 calendar.setName(df.format(day));
		 calendar.setWorkDate(day);
		 calendar.setWorkDayTypeId(1);

		 if (date.dayOfWeek().get() == DateTimeConstants.SATURDAY
			 || date.dayOfWeek().get() == DateTimeConstants.SUNDAY) {
			calendar.setWorkDayTypeId(2);
		 }
		 for (LocalDate holiday : HOLIDAYS) {
			if (holiday.dayOfMonth().equals(date.dayOfMonth()) && holiday.getMonthOfYear() == date.getMonthOfYear()) {
			   calendar.setWorkDayTypeId(3);
			}
		 }
		 Calendar oldCalendar = calendarRepository.getCalendarByDay(day);
		 if (oldCalendar == null) {
			calendarRepository.createDay(calendar);
		 } else {
			calendar.setId(oldCalendar.getId());
			calendarRepository.updateDay(calendar);
		 }
	  }
   }
}
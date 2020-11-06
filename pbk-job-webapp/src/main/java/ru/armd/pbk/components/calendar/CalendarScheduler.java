package ru.armd.pbk.components.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.services.nsi.calendar.CalendarService;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Calendar;

/**
 * Класс отвечающий за запуск запланированных задач, которые
 * отвечают за работу с календарями.
 */
@Component
public class CalendarScheduler {

   @Autowired
   private CalendarService calendarService;

   /**
	* Джоб заполнения календаря. Запускается раз в месяц и заполняет на три месяца вперед.
	*/
   @Scheduled(cron = "0 0 0 1 * ?")
   public void fillCalendar() {
	  Calendar from = Calendar.getInstance();
	  Calendar to = Calendar.getInstance();
	  to.add(Calendar.MONTH, 3);
	  calendarService.fillCalendar(DateUtils.shiftToDayStart(from.getTime()), DateUtils.shiftToDayStart(to.getTime()));
   }
}

package ru.armd.pbk.mappers.nsi.calendar;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.nsi.Calendar;

import java.util.Date;
import java.util.List;

/**
 * Маппер для работы с календарем.
 */
@IsMapper
public interface CalendarMapper {
   /**
	* Календарь на период.
	*
	* @param from с
	* @param to   по
	* @return
	*/
   List<Calendar> getCalendarForPeriod(@Param("from") Date from, @Param("to") Date to);

   /**
	* Календарь на дату.
	*
	* @param day дата
	* @return
	*/
   Calendar getCalendarByDay(Date day);

   /**
	* Поиск аналогичного дня по типу.
	*
	* @param isPrevious   искать в прошлом
	* @param workDate     стартовая дата
	* @param workDayTypes типы дня календаря
	* @return
	*/
   Calendar getSameDayByType(@Param("isPrevious") boolean isPrevious, @Param("workDate") Date workDate,
							 @Param("workDayTypes") int[] workDayTypes);

   /**
	* Заполнить календарь на дату.
	*
	* @param day день
	*/
   void createDay(Calendar day);

   /**
	* Изменить календарь на дату.
	*
	* @param day день
	*/
   void updateDay(Calendar day);
}

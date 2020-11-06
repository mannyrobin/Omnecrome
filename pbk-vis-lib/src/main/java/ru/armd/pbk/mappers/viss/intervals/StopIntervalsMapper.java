package ru.armd.pbk.mappers.viss.intervals;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.viss.intervals.StopInterval;
import ru.armd.pbk.domain.viss.intervals.TasksAndStops;
import ru.armd.pbk.domain.viss.intervals.Telematic;

import java.util.Date;
import java.util.List;

/**
 * Маппер остановочных интервалов.
 */
@IsMapper
public interface StopIntervalsMapper {

   /**
	* Получить список плановых маршрутов на дату.
	*
	* @param date дата.
	* @return
	*/
   List<String> getPlanRoutes(@Param("workDate") Date date);

   /**
	* Получить список заданий и связанных остановок для маршрутов
	* из списка {@code routes} на дату {@code date}.
	*
	* @param date
	* @param routes
	* @return
	*/
   List<TasksAndStops> getTasksAndStops(@Param("workDate") Date date, @Param("list") List<String> routes);

   /**
	* Получить телематику для парка в временом промежутке.
	*
	* @param depotNumber номер парка.
	* @param dateFrom    начало.
	* @param dateTo      конец.
	* @return
	*/
   List<Telematic> getTelematics(@Param("depotNumber") String depotNumber, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

   /**
	* Получить всю телематику на дату.
	*
	* @param date дата.
	* @return
	*/
   List<Telematic> getAllTelematics(@Param("workDate") Date date);

   /**
	* Множестванная вставка.
	*
	* @param list список интервалов.
	*/
   void insertBulk(List<StopInterval> list);

   /**
	* Обновить названия остановок.
	*
	* @param date дата.
	*/
   void updateStopNames(@Param("workDate") Date date);

   /**
	* Обновить название парков.
	*
	* @param date дата.
	*/
   void updateDepots(@Param("workDate") Date date);

   /**
	* Получить список интервалов по идентификаторам.
	*
	* @param ids список идентификаторов.
	* @return
	*/
   List<StopInterval> getStopIntervals(@Param("list") List<Long> ids);

   /**
	* Обновить количество из АСМПП.
	*
	* @param interval интервал.
	*/
   void updateAsmppCounts(StopInterval interval);

   /**
	* Вставка начальных интервалов.
	* Вставляет для каждого маршрута и выхода интервал 'date 00:00'
	*
	* @param date дата
	* @return
	*/
   int insertStartIntervals(@Param("date") Date date);

   /**
	* Вставка конечных интервалов.
	* Вставляет для каждого маршрута и выхода интервал 'date 23:59'
	*
	* @param date дата
	* @return
	*/
   int insertEndIntervals(@Param("date") Date date);

   /**
	* Обновление конечеых интервалов.
	* Обновляет каждый конечный интервал данными из предыдущего
	*
	* @param date дата
	* @return
	*/
   int updateEndIntervals(@Param("date") Date date);

   /**
	* Удаление интервалов на дату.
	*
	* @param date дата
	* @return
	*/
   int deleteIntervals(@Param("date") Date date);
}

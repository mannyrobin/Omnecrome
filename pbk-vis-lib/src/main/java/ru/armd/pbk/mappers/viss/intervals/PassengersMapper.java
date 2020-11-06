package ru.armd.pbk.mappers.viss.intervals;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.viss.intervals.Passengers;

import java.util.Date;
import java.util.List;

/**
 * Маппер остановочных интервалов.
 */
@IsMapper
public interface PassengersMapper {

   /**
	* Обновить количество АСМПП.
	*
	* @param id            ид
	* @param asmppAvgCount среднее количество АСМПП
	*/
   void updateAsmppCounts(@Param("id") Long id, @Param("asmppAvgCount") Integer asmppAvgCount);

   /**
	* Вставить данные из проверок билетов на дату {@code date}.
	*
	* @param date дата
	*/
   void insertFromTicketChecks(@Param("workDate") Date date);

   /**
	* Очистить расчет пассажиропотока для даты.
	*
	* @param workDate дата расчета.
	* @return
	*/
   int deleteByDate(@Param("date") Date date);

   /**
	* Получить пассажиров на дату.
	*
	* @param date дата
	* @return
	*/
   List<Passengers> getPassengers(@Param("date") Date date);
}

package ru.armd.pbk.mappers.nsi.askp;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.askp.AskpRouteMoveTrip;

import java.util.Date;

/**
 * Маппер для работы с агрегированными данными АСКП
 * по выходам маршрутов рейса.
 */
@IsMapper
public interface AskpRouteMoveTripMapper
	extends IDomainMapper<AskpRouteMoveTrip> {

   /**
	* Вставка агрегированых данных по дате.
	*
	* @param workDate дата расчета.
	* @return
	*/
   int insertByWorkDate(@Param("workDate") Date workDate);

   /**
	* Очистить агрегированные данные по дате.
	*
	* @param workDate дата расчета.
	* @return
	*/
   int deleteByWorkDate(@Param("workDate") Date workDate);

}

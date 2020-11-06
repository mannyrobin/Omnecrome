package ru.armd.pbk.mappers.nsi.asdu;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.asdu.AsduRouteMoveTrip;

import java.util.Date;

/**
 * Маппер для выходов АСДУ маршрутов.
 */
@IsMapper
public interface AsduRouteMoveTripMapper
	extends IDomainMapper<AsduRouteMoveTrip> {

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

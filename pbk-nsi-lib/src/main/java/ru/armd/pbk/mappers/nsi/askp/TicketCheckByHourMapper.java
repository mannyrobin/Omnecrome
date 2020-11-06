package ru.armd.pbk.mappers.nsi.askp;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;

import java.util.Date;

/**
 * Маппер проверки билетов по времени.
 */
@IsMapper
public interface TicketCheckByHourMapper {

   /**
	* Вставка агрегированых данных в интервале.
	*
	* @param from начало.
	* @param to   конец.
	* @return
	*/
   int insertByWorkDate(@Param("from") Date from, @Param("to") Date to);

   /**
	* Очистить агрегированные данные в интервале.
	*
	* @param from начало.
	* @param to   конец.
	* @return
	*/
   int deleteByWorkDate(@Param("from") Date from, @Param("to") Date to);

   /**
	* Привязка маршрутов после вставки данных.
	*
	* @param from начало.
	* @param to   конец.
	* @return
	*/
   int updateRoutesId(@Param("from") Date from, @Param("to") Date to);

}

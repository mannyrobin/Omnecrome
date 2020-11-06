package ru.armd.pbk.mappers.nsi.askp;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

import java.util.Date;

/**
 * Базовый маппер для данных из АСКП.
 *
 * @param <Domain> домен
 */
public interface ITicketCheckMapper<Domain extends BaseDomain>
	extends IDomainMapper<Domain> {

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

   /**
	* Привязка маршрутов после вставки данных.
	*
	* @param workDate дата расчета.
	* @return
	*/
   int updateRoutesId(@Param("workDate") Date workDate);

}

package ru.armd.pbk.mappers.nsi.askppuskcheck;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;

import java.util.Date;
import java.util.List;

/**
 * Маппер данных от АСКП.
 */
@IsMapper
public interface AskpPuskCheckMapper
	extends IDomainMapper<AskpPuskCheck> {

   /**
	* Привязать проверку ПУсК к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   int bind(@Param("taskId") Long taskId);

   /**
	* Получить количество уникальных маршрутов для задания.
	*
	* @param taskId ИД задания
	* @return
	*/
   int getUniqueRouteCount(@Param("taskId") Long taskId);

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации для маршрутов АСКП.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViewsByRoutes(Params params);

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации для маршрутов АСКП.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViewsByRoutesDetails(Params params);

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации для маршрутов АСКП.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViewsByHours(Params params);

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации для маршрутов АСКП.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViewsByHoursDetails(Params params);

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации для маршрутов АСКП.
	* Для страницы привязанных / не привязанных АСКП.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViewsByBind(Params params);

   /**
	* Привязать проверку ПУсК к заданиям по дате.
	*
	* @param workDate дата
	* @return
	*/
   int bindByWorkDate(@Param("workDate") Date workDate);

}

package ru.armd.pbk.mappers.plan;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IReportMapper;
import ru.armd.pbk.views.plan.BrigadeGraphPlanVenuesView;
import ru.armd.pbk.views.plan.BrigadeGraphVenuesView;

import java.util.Date;
import java.util.List;

/**
 * Маппер для работы с печатными формами планирования.
 */
@IsMapper
public interface PlansReportMapper
	extends IReportMapper {
   /**
	* Получить список мест встречи графика бригад по id подразделения (null -
	* для всех подразделений) в диапазоне дат.
	*
	* @param deptId   id подразделения
	* @param dateFrom начало периода
	* @param dateTo   окончание периода
	* @return данные графика для вывода на печатную форму
	*/
   List<BrigadeGraphVenuesView> getBrigadeGraphVenues(@Param("deptId") Long deptId, @Param("dateFrom") Date dateFrom,
													  @Param("dateTo") Date dateTo);

   /**
	* Получить список бригад графика бригад по id подразделения (null - для
	* всех подразделений) в диапазоне дат.
	*
	* @param deptId   id подразделения
	* @param dateFrom начало периода
	* @param dateTo   окончание периода
	* @return данные графика для вывода на печатную форму
	*/
   List<BrigadeGraphPlanVenuesView> getBrigadeGraphPlanVenues(@Param("deptId") Long deptId,
															  @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

   /**
	* Получает количество заданий, назначенных на бригаду.
	*
	* @param brigadeId id бригады
	* @return количество заданий
	*/
   int getBrigadeTaskCount(@Param("brigadeId") Long brigadeId);

   /**
	* Получить разницу между кол-вом контролеров по расписанию и кол-вом
	* контролеров по бригадам.
	*
	* @param deptId   id подразделения
	* @param workDate дата
	* @param shiftId  id смены
	* @return разницу между кол-вом контролеров по расписанию и кол-вом
	* контролеров по бригадам
	*/
   int getDifferenceForBrigadeState(@Param("deptId") Long deptId, @Param("workDate") Date workDate,
									@Param("shiftId") Long shiftId);


   List<Long> getPlanDeptId(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
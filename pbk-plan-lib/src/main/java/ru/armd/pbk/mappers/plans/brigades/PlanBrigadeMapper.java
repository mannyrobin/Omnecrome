package ru.armd.pbk.mappers.plans.brigades;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.domain.plans.brigades.ReserveCount;
import ru.armd.pbk.views.plans.brigades.BrigadeVenueView;

import java.util.Date;
import java.util.List;

/**
 * Маппер бригад плана отдела.
 */
@IsMapper
public interface PlanBrigadeMapper
	extends IDomainMapper<Brigade> {

   /**
	* Получает список бригад планов отдела по параметрам.
	*
	* @param param параметры
	* @return список бригад планов отдела
	*/
   List<BrigadeVenueView> getBrigadeVenueViews(BaseGridViewParams param);

   /**
	* Получает список смен для переданного плана отдела и списка бригад - мест
	* встреч.
	*
	* @param param  параметры грида.
	* @param venues список бригад - мест встреч
	* @return список смен
	*/
   List<Brigade> getBrigadeShiftsForVenues(@Param("params") BaseGridViewParams param,
										   @Param("venues") List<BrigadeVenueView> venues);

   /**
	* Получает список количеств контроллеров В резерве.
	*
	* @param param параметры грида.
	* @return список
	*/
   List<ReserveCount> getBrigadeReserveCounts(@Param("params") BaseGridViewParams param);

   /**
	* Получает список бригад на определенную дату.
	*
	* @param workDate рабочая дата
	* @param deptId   подразделение
	* @param shiftId  смена
	* @return список бригад
	*/
   List<Brigade> getBrigades(@Param("workDate") Date workDate, @Param("deptId") Long deptId, @Param("shiftId") Long shiftId);

   /**
	* Удаление всех бригад для подразделения на дату.
	*
	* @param deptId   подразделение
	* @param workDate рабочая дата
	* @return
	*/
   int removeBrigadesByWorkDate(@Param("deptId") Long deptId, @Param("workDate") Date workDate, @Param("resetManualData") boolean resetManualData);

   /**
	* Возвращает вручную установленные бригады.
	*
	* @param deptId   подразделение
	* @param workDate рабочая дата
	* @return
	*/
   List<Brigade> getManualBrigades(@Param("deptId") Long deptId, @Param("workDate") Date workDate);

   /**
	* Согласовние бригад.
	*
	* @param deptId    подразделение
	* @param startDate начало периода согласования
	* @param endDate   окончание периода
	* @param userId    ИД пользователя
	* @param bApprove  флаг согласования
	* @return
	*/
   int approveBrigade(@Param("deptId") Long deptId, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
					  @Param("bApprove") boolean bApprove, @Param("userId") Long userId);

   /**
	* Получить бригаду по параметрам.
	*
	* @param deptId      id подразделения
	* @param cityVenueId id места встречи
	* @param shiftId     id смены
	* @param workDate    дата
	* @return бригаду
	*/
   Brigade getBrigadeByParams(@Param("deptId") Long deptId, @Param("cityVenueId") Long cityVenueId,
							  @Param("shiftId") Long shiftId, @Param("workDate") Date workDate);

   /**
	* Поиск импортированной бригады по названиям.
	*
	* @param deptName   поздразделение
	* @param venueName  место встречи
	* @param shiftHours смена.
	* @return
	*/
   List<SelectItem> findBrigadeImportByNames(@Param("deptName") String deptName, @Param("venueName") String venueName,
											 @Param("shiftHours") String shiftHours);

   List<Brigade> checkBrigadeShiftsForVenues(@Param("deptId") Long deptId, @Param("shiftId") Long shiftId,
											 @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

   /**
	* Проверяет, существует ли бригады для подразделения с индефикатором {@code deptId}
	* в период с {@code dateFrom} по {@code dateTo} задания со статусом 'IN_PROGRESS', 'DONE', 'CLOSED'.
	*
	* @param deptId   индефикатор подразделения. Если null то учитываются все подразделения.
	* @param dateFrom начало
	* @param dateTo   конец
	* @return
	*/
   List<Brigade> checkBrigadeForTask(@Param("deptId") Long deptId, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

}
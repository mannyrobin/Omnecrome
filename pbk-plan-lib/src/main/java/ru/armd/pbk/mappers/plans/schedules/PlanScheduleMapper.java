package ru.armd.pbk.mappers.plans.schedules;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.plans.schedules.PlanSchedule;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.domain.plans.schedules.ScheduleWorkMode;
import ru.armd.pbk.domain.tasks.CheckRoute;
import ru.armd.pbk.views.plans.schedules.EmployeePlanScheduleView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Маппер расписания плана отдела.
 */
@IsMapper
public interface PlanScheduleMapper
	extends IDomainMapper<ScheduleShift> {

   /**
	* Получает список сотрудников по параметрам.
	*
	* @param param параметры
	* @return список сотрудников
	*/
   List<EmployeePlanScheduleView> getEmployeeForPlanViews(BaseGridViewParams param);

   /**
	* Получает список смен для переданного плана отдела и списка сотрудников.
	*
	* @param params    параметры
	* @param employees список сотрудников
	* @return список смен
	*/
   List<ScheduleShift> getPlanSchedulesForEmployees(@Param("params") BaseGridViewParams params,
													@Param("employees") List<EmployeePlanScheduleView> employees);

   /**
	* Получение предыдущих расписаний.
	*
	* @param dateFrom     граничная дата
	* @param departmentId подразделение
	* @param employeeId   сотрудник
	* @return
	*/
   List<ScheduleShift> getPreviousPlanSchedulesForDepts(@Param("dateFrom") Date dateFrom,
														@Param("departmentId") Long departmentId, @Param("employeeId") Long employeeId);

   /**
	* Получение списка контроллеров.
	*
	* @param dateFrom     стартовая дата
	* @param dateTo       конечная дата
	* @param departmentId подразделение
	* @return
	*/
   List<SelectItem> getDeptsEmployees(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo,
									  @Param("departmentId") Long departmentId);

   /**
	* Поиск свободных контроллеров.
	*
	* @param deptId   подразделение
	* @param workDate рабочая дата
	* @param shiftId  смена
	* @return
	*/
   List<PlanSchedule> findFreeController(@Param("deptId") Long deptId, @Param("workDate") Date workDate,
										 @Param("shiftId") Long shiftId);

   /**
	* Получение списка маршрутов для проверки.
	*
	* @param workDate    рабочая дата
	* @param planVenueId место встречи
	* @return
	*/
   List<CheckRoute> getAvailableForCheckRoutes(@Param("workDate") Date workDate,
											   @Param("planVenueId") Long planVenueId);

   /**
	* Получить смену расписания по параметрам.
	*
	* @param params параметры
	* @return смену расписания по параметрам
	*/
   ScheduleShift getSchedule(Map<String, Object> params);

   /**
	* Получить смену расписания по id задания.
	*
	* @param taskId id задания
	* @return смену расписания по параметрам
	*/
   ScheduleShift getScheduleByTaskId(Long taskId);

   /**
	* Получить смену расписания на замену по id задания.
	*
	* @param taskId id задания
	* @return смену расписания на замену по параметрам
	*/
   ScheduleShift getChangeScheduleByTaskId(Long taskId);

   /**
	* Получить смену причины замены по id расписания.
	*
	* @param scheduleId id расписания
	* @return смену причины замены
	*/
   Long getCauseShiftId(Long scheduleId);

   /**
	* Получить список смен расписания, находящихся в резерве по параметрам.
	*
	* @param params параметры
	* @return список смен расписания, находящихся в резерве
	*/
   List<Long> getReserveList(BaseGridViewParams params);

   /**
	* Получить смену расписания по параметрам.
	*
	* @param deptId     id подразделения
	* @param employeeId id сотрудника
	* @param workDate   дата
	* @return смену расписания
	*/
   ScheduleShift getScheduleByParams(@Param("deptId") Long deptId, @Param("employeeId") Long employeeId,
									 @Param("workDate") Date workDate);

   /**
	* Найти расписание контроллера.
	*
	* @param employeeId контроллер
	* @param workDate   дата
	* @return
	*/
   ScheduleShift getPlanScheduleCell(@Param("employeeId") Long employeeId, @Param("workDate") Date workDate);

	/**
	 * Найти расписание контроллера для перемещения сотрудника.
	 *
	 * @param employeeId контроллер
	 * @param workDate   дата
	 * @return
	 */
	ScheduleShift getPlanScheduleCellForMove(@Param("employeeId") Long employeeId,
											 @Param("workDate") Date workDate, @Param("deptSearchDate") Date deptSearchDate);

   /**
	* Найти расписание контроллера за периуд.
	*
	* @param employeeId контроллер
	* @param dateFrom   стартовая дата
	* @return
	*/
   List<ScheduleShift> getPlanScheduleCells(@Param("employeeId") Long employeeId, @Param("dateFrom") Date dateFrom);

   /**
	* Проверка участия контроллера в планировании.
	*
	* @param employeeId контроллер
	* @param date       дата
	* @return
	*/
   SelectItem isForPlanUse(@Param("employeeId") Long employeeId, @Param("date") Date date);

   /**
	* Получение списка плановых расписаний.
	*
	* @param date
	* @return
	*/
   List<ScheduleWorkMode> getEmployeeWorkModes(@Param("employeeId") Long employeeId, @Param("dateFrom") Date dateFrom);

   /**
	* Получение списка расписания для отображения в комбобоксах при создании
	* заданий.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   List<SelectItem> getSelectItemsForCreatingTask(Map<String, Object> params);

   /**
	* Получение списка сотрудников для которых не существует расписания
	* в заданном временом промежутке.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   List<SelectItem> getEmployeesWithoutSchedule(Map<String, Object> params);

   /**
	* Получение планового расписания по ИД.
	*
	* @param id ИД планового расписания.
	* @return
	*/
   PlanSchedule getPlanScheduleById(@Param("id") Long id);

   List<ScheduleShift> getScheduleForCheckingMode();


}

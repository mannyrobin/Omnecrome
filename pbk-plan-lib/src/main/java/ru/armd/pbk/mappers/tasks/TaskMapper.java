package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.PuskTask;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.domain.tasks.TaskRoute;
import ru.armd.pbk.domain.tasks.TaskState;
import ru.armd.pbk.views.tasks.TaskAdditionalInfo;
import ru.armd.pbk.views.tasks.TaskView;
import ru.armd.pbk.views.tasks.TaskViewDomain;

import java.util.*;

/**
 * Маппер заданий.
 */
@IsMapper
public interface TaskMapper
	extends IDomainMapper<Task> {
   /**
	* Получение списка заданий для подразделения на дату.
	*
	* @param deptId   подразделение
	* @param workDate дата
	* @return
	*/
   List<Task> getTasksByWorkDate(@Param("deptId") Long deptId, @Param("workDate") Date workDate);

   /**
	* Удаление всех заданий для подразделения на дату.
	*
	* @param deptId   подразделение
	* @param workDate дата
	* @return
	*/
   int removeTasksByWorkDate(@Param("deptId") Long deptId, @Param("workDate") Date workDate);

   /**
	* Получение заданий для замены контроллеров по причине отпуска и т.п.
	*
	* @param planVenueId бригада
	* @return
	*/
   List<Task> getIncorrectControllerTasks(@Param("planVenueId") Long planVenueId);

   /**
	* Сменить контроллера в задании.
	*
	* @param task задание
	* @return
	*/
   int changeController(Task task);

   /**
	* Установить статус задания.
	*
	* @param stateId      статус
	* @param taskIds      ИД задания
	* @param updateDate   дата изменения
	* @param updateUserId ИД пользователя
	*/
   void changeTasksStatus(@Param("stateId") Long stateId, @Param("taskIds") List<Long> taskIds,
						  @Param("updateDate") Date updateDate, @Param("updateUserId") Long updateUserId);

   /**
	* Получить статус задания по ид задания.
	*
	* @param taskId ид задания
	* @return статус задания
	*/
   TaskState getTaskStateByTaskId(@Param("taskId") Long taskId);

   /**
	* Получить view задания по id.
	*
	* @param taskId id задания
	* @return view задания
	*/
   TaskViewDomain getGridViewByTaskId(Long taskId);

   /**
	* Получить дату выполнения задания.
	*
	* @param taskId ИД.
	* @return
	*/
   Date getTaskDate(@Param("taskId") Long taskId);

   /**
	* Получения постраничного списка заданий по заданным параметрам фильтрации.
	*
	* @param params Параметры фильтрации.
	* @return Список заданий.
	*/
   List<TaskView> getPlanTaskViews(BaseGridViewParams params);

   /**
	* Получение маршрутов для заданий.
	*
	* @param tasks плановые задания
	* @return список маршрутов
	*/
   List<TaskRoute> getPlanRoutsForTask(@Param("tasks") List<TaskView> tasks);

   /**
	* Получение задания по id расписания.
	*
	* @param scheduleId id расписания
	* @return задание
	*/
   Task getTaskByScheduleId(@Param("scheduleId") Long scheduleId);

   /**
	* Найти задание контроллера.
	*
	* @param employeeId - ИД сотрудника.
	* @param workDate   - дата выполнения.
	* @return
	*/
   Task getEmployeeTask(@Param("employeeId") Long employeeId, @Param("workDate") Date workDate);

   /**
	* Перенос маршрутов от одного задания к другому.
	*
	* @param oldTaskId    - задание от которого переносить.
	* @param newTaskId    - задание к которому переносить.
	* @param updateUserId - обновиший пользователь.
	*/
   void moveRoutes(@Param("oldTaskId") Long oldTaskId, @Param("newTaskId") Long newTaskId,
				   @Param("updateUserId") Long updateUserId);

   /**
	* Проверят можно ли автоматически закрыть задачу.
	*
	* @param taskId ИД - задачи
	* @return
	*/
   Boolean canAutoCloseTask(@Param("taskId") Long taskId);

   /**
	* Создать задания.
	*
	* @param tasks задания.
	* @return
	*/
   int createTasks(@Param("tasks") Collection<Task> tasks);

   /**
	* Получение дополнительной информации по id задания (место встречи,
	* подразделние).
	*
	* @param taskId id задания
	* @return дополнительная информация по заданию
	*/
   TaskAdditionalInfo getTaskAdditionalInfoById(@Param("taskId") Long taskId);

   /**
	* Получить список заданий на дату.
	*
	* @param workDate дата
	* @return список заданий
	*/
   List<Task> getTasksByDate(@Param("workDate") Date workDate);

   /**
	* Обновить районы заданий, для которых подбираются маршруты.
	*
	* @param tasks связка задание - район.
	* @return
	*/
   int updateTaskDistrict(@Param("tasks") Map<Long, Long> tasks);

   /**
	* Получение списка районов задания.
	*
	* @param taskId     ИД задания.
	* @param districtId ИД текущего района
	* @return
	*/
   List<SelectItem> getTaskDistricts(@Param("taskId") Long taskId, @Param("districtId") Long districtId);

	List<Long> getTasksOfFiredEmployees();

	List<Long> getTasksOfMovedDepEmployees();

	List<Long> getTasksOfChangedPlanEmployees();

	void removeTasksByWorkDateP(HashMap<String, Object> map);

	List<PuskTask> getTasksForPusk();

}
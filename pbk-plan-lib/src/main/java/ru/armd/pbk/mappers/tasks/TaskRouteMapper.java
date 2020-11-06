package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskRoute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Маппер для маршрутов простых заданий.
 */
@IsMapper
public interface TaskRouteMapper
	extends IDomainMapper<TaskRoute> {

   /**
	* Удаляет маршруты заданий по id задания и маршрутов.
	*
	* @param taskId   id задания
	* @param routeIds список id маршрутов
	* @return
	*/
   int deleteTaskRoutes(@Param("taskId") Long taskId, @Param("routeIds") List<Long> routeIds);

   /**
	* Получает все id маршрутов для задания.
	*
	* @param taskId id задания
	* @return список id маршрутов
	*/
   List<Long> getRouteIdsByTaskId(@Param("taskId") Long taskId);

   /**
	* Привязать маршруты к заданию.
	*
	* @param taskId       задание.
	* @param routes       маршруты.
	* @param createUserId создавший пользователь.
	* @param createDate   дата создания.
	* @return
	*/
   int insertRoutes(@Param("taskId") Long taskId, @Param("routes") Map<Long, Long> routes,
					@Param("createUserId") Long createUserId, @Param("createDate") Date createDate);

   int insertRoute(@Param("taskId") Long taskId, @Param("routeId") Long routeId, @Param("districtId") Long districtId,
					 @Param("createUserId") Long createUserId, @Param("createDate") Date createDate);

   /**
	* Получение списка маршрутов для задания.
	*
	* @param typeId тип маршрута (сопутствующий от, до)
	* @param taskId ИД задания
	* @return
	*/
   List<SelectItem> getTaskRoutesForType(@Param("typeId") Long typeId, @Param("taskId") Long taskId);

   List<SelectItem> getBskRoutes(@Param("taskId") Long taskId);

   /**
	* Получение списка всех маршрутов для задания.
	* (сопутствующие от, до)
	*
	* @param taskId ИД задания
	* @return
	*/
   List<SelectItem> getAllRoutesForTask(@Param("taskId") Long taskId);

   /**
	* Удалить маршруты с нулевыми выходами у заданий.
	*
	* @param date дата
	* @return
	*/
   int deleteZeroMoveRoutesByDate(@Param("date") Date date);

   /**
	* Удаление проверяемых маршрутов из всех заданий для подразделения на дату.
	*
	* @param deptId   подразделение
	* @param workDate дата
	* @return
	*/
   int removeTasksRoutesByWorkDate(@Param("deptId") Long deptId, @Param("workDate") Date workDate);

	void removeTasksRoutesByWorkDateP(HashMap<String, Object> map);
}

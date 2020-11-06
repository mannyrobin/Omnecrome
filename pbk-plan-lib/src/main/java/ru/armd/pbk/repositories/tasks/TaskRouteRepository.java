package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskRoute;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.enums.core.TaskRouteType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.tasks.TaskRouteMapper;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.views.nsi.route.RouteMapView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий маршрутов обычных заданий.
 */
@Repository
public class TaskRouteRepository
	extends BaseDomainRepository<TaskRoute> {

   public static final Logger LOGGER = Logger.getLogger(TaskRouteRepository.class);

   private TaskRouteMapper taskRouteMapper;

   @Autowired
   private RouteRepository routeRepository;

   @Autowired
   TaskRouteRepository(TaskRouteMapper mapper) {
	  super(TaskAuditType.TASK_ROUTE, mapper);
	  this.taskRouteMapper = mapper;
   }

   /**
	* Удаляет маршруты плановых заданий по id задания и маршрутов.
	*
	* @param taskId   id планового задания
	* @param routeIds список id маршрутов
	* @return
	*/
   public int deleteTaskRoutes(Long taskId, List<Long> routeIds) {
	  int count = 0;
	  if (routeIds == null) {
		 return count;
	  }
	  try {
		 count = taskRouteMapper.deleteTaskRoutes(taskId, routeIds);
		 logAudit(AuditLevel.TRACE, getDomainAuditType(), AuditObjOperation.DELETE, routeIds,
			 "Успешно удалено " + count + " из " + routeIds.size() + " записи/записей.", null);
	  } catch (Exception e) {
		 String message = "Не удалось удалить записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, routeIds, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }

   /**
	* Получает все id маршрутов для планового задания.
	*
	* @param taskId id планового задания
	* @return список id маршрутов
	*/
   public List<Long> getRouteIdsByTaskId(Long taskId) {
	  List<Long> routeIds = null;
	  try {
		 routeIds = taskRouteMapper.getRouteIdsByTaskId(taskId);
		 ;
	  } catch (Exception e) {
		 String message = "Не удалось получить список id маршрутов задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return routeIds;
   }

   /**
	* Получить маршруты по типу задания.
	*
	* @param typeId id типа задания
	* @param taskId id задания
	* @return список маршрутов
	*/
   public List<RouteMapView> getTaskRoutesForType(Long typeId, Long taskId) {
	  List<RouteMapView> result = null;
	  try {
		 if (TaskRouteType.TASK.getId().equals(typeId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskId", taskId);
			List<SelectItem> routes = taskRouteMapper.getSelectItems(params);
			result = routeRepository.fillRouteTrajectoryAndStops(routes, true);
		 } else
		 	if (TaskRouteType.BSK.getId().equals(typeId)) {
				result = routeRepository
						.fillRouteTrajectoryAndStops(taskRouteMapper.getBskRoutes(taskId), true);
			} else {
			result = routeRepository
				.fillRouteTrajectoryAndStops(taskRouteMapper.getTaskRoutesForType(typeId, taskId), true);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получение списка всех маршрутов для задания.
	* (сопутствующие от, до)
	*
	* @param taskId - ИД задания
	* @return
	*/
   public List<SelectItem> getAllRoutesForTask(Long taskId) {
	  List<SelectItem> result = null;
	  try {
		 result = taskRouteMapper.getAllRoutesForTask(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список всех маршрутов задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получение маршрутов задания с ИД {code id}.
	*
	* @param id ИД задания.
	* @return
	*/
   public List<TaskRoute> getTaskRoutesByTaskId(Long id) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("taskId", id);
	  return getDomains(params);
   }

   /**
	* Удаление проверяемых маршрутов из всех заданий со статусом "Создано"
	* для подразделения на дату.
	*
	* @param deptId   подразделение
	* @param workDate дата
	*/
   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   public void removeTasksRoutesByWorkDate(Long deptId, Date workDate) {
	   //taskRouteMapper.removeTasksRoutesByWorkDate(deptId, workDate);
	   HashMap<String, Object> params = new HashMap<String, Object>();
	   params.put("deptId", deptId);
	   params.put("workDate", workDate);
	   taskRouteMapper.removeTasksRoutesByWorkDateP(params);

   }

   /**
	* Привязать маршруты к заданию.
	*
	* @param taskId задание.
	* @param routes маршруты.
	* @return
	*/
   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   public void insertRoutes(Long taskId, Map<Long, Long> routes) {
	   //taskRouteMapper.insertRoutes(taskId, routes, AuthenticationManager.getUserInfo().getUserId(), new Date());

	   for (Map.Entry<Long, Long> route : routes.entrySet()) {
		   //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		   taskRouteMapper.insertRoute(taskId, route.getKey(), route.getValue(), AuthenticationManager.getUserInfo().getUserId(), new Date());
	   }

   }

   /**
	* Удалить маршруты с нулевыми выходами у заданий.
	*
	* @param workDate дата
	* @return
	*/
   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   public void deleteZeroMoveRoutesByDate(Date workDate) {
	  taskRouteMapper.deleteZeroMoveRoutesByDate(workDate);
   }
}

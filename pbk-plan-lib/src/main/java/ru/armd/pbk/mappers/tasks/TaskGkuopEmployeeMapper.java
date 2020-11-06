package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.tasks.TaskGkuopEmployee;

import java.util.List;

/**
 * Маппер таблицы связки задание - сотрудник ГКУ ОП.
 */
@IsMapper
public interface TaskGkuopEmployeeMapper
	extends IDomainMapper<TaskGkuopEmployee> {

   /**
	* Удаляет сотрудников ГКУ ОП заданий по id задания и сотрудников.
	*
	* @param taskId   id задания
	* @param routeIds список id сотрудников
	* @return
	*/
   int deleteTaskEmployees(@Param("taskId") Long taskId, @Param("employeeIds") List<Long> employeeIds);

   /**
	* Получает все id сотрудников ГКУ ОП для задания.
	*
	* @param taskId id задания
	* @return список id сотрудников
	*/
   List<Long> getGkuopEmployeeIdsByTaskId(@Param("taskId") Long taskId);
}
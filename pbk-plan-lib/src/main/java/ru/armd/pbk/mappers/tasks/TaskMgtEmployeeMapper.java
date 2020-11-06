package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.tasks.TaskMgtEmployee;

import java.util.List;

/**
 * Маппер таблицы связки задание - сотрудник МГТ.
 */
@IsMapper
public interface TaskMgtEmployeeMapper
	extends IDomainMapper<TaskMgtEmployee> {

   /**
	* Удаляет сотрудников МГТ заданий по id задания и сотрудников.
	*
	* @param taskId      id задания
	* @param employeeIds список id сотрудников
	* @return
	*/
   int deleteTaskEmployees(@Param("taskId") Long taskId, @Param("employeeIds") List<Long> employeeIds);

   /**
	* Получает все id сотрудников МГТ для задания.
	*
	* @param taskId    id задания
	* @param skipEmpId id пропускаимых сотрудников.
	* @return список id сотрудников
	*/
   List<Long> getMgtEmployeeIdsByTaskId(@Param("taskId") Long taskId, @Param("skipEmpId") Long skipEmpId);
}
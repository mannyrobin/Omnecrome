package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.tasks.TaskReport;

/**
 * Маппер отчётов по заданиям.
 */
@IsMapper
public interface TaskReportMapper
	extends IDomainMapper<TaskReport> {

   /**
	* Создать краткие отчеты для заданий.
	*
	* @param createUserId id - пользователя, который создает задания.
	* @return
	*/
   int insertReports(@Param("createUserId") Long createUserId);

}
package ru.armd.pbk.mappers.tasks;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IReportMapper;
import ru.armd.pbk.views.tasks.TaskReportReportView;

/**
 * Маппер для работы с печатной формой отчёта по заданию.
 */
@IsMapper
public interface TaskReportReportMapper
	extends IReportMapper {

   /**
	* Получить данные отчёта по заданию (для вывода на печатную форму) по id задания.
	*
	* @param taskId id задания
	* @return данные отчёта по заданию для вывода на печатную форму
	*/
   TaskReportReportView getByTaskId(@Param("taskId") Long taskId);

}

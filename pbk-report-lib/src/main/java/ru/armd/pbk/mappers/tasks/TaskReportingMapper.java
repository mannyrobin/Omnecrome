package ru.armd.pbk.mappers.tasks;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IReportMapper;
import ru.armd.pbk.views.tasks.TaskReportView;

/**
 * Маппер для работы с печатной формой задания.
 * Назван не TaskReportMapper, т.к. такой уже есть (маппер отчёта по заданию)
 */
@IsMapper
public interface TaskReportingMapper
	extends IReportMapper {

   /**
	* Получить данные задания (для вывода на печатную форму) по id задания.
	*
	* @param id id задания
	* @return данные задания для вывода на печатную форму
	*/
   TaskReportView getById(Long id);

}

package ru.armd.pbk.matcher.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.tasks.TaskReport;
import ru.armd.pbk.dto.tasks.TaskReportDTO;

/**
 * Маппер для сопоставления различных типов сущности "отчёт по заданию".
 */
@Mapper
public interface ITaskReportMatcher
	extends IDomainMatcher<TaskReport, TaskReportDTO> {

   ITaskReportMatcher INSTANCE = Mappers.getMapper(ITaskReportMatcher.class);
}

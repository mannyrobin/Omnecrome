package ru.armd.pbk.repositories.tasks;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.tasks.TaskReport;
import ru.armd.pbk.enums.core.TaskReportAuditType;
import ru.armd.pbk.mappers.tasks.TaskReportMapper;

/**
 * Репозиторий отчётов по заданиям.
 */
@Repository
public class TaskReportRepository
	extends BaseDomainRepository<TaskReport> {

   public static final Logger LOGGER = Logger.getLogger(TaskReportRepository.class);

   private TaskReportMapper mapper;

   @Autowired
   TaskReportRepository(TaskReportMapper mapper) {
	  super(TaskReportAuditType.TASK_TASK_REPORT, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Создать пустой отчёт по заданию.
	*
	* @param taskId id задания
	*/
   public void createEmptyReport(Long taskId) {
	  TaskReport taskReport = new TaskReport();
	  taskReport.setTaskId(taskId);
	  save(taskReport);
   }

   /**
	* Создать краткие отчеты для заданий.
	*
	* @param createUserId id - пользователя, который создает задания.
	* @return
	*/
   public int insertReports(@Param("createUserId") Long createUserId) {
	  return mapper.insertReports(createUserId);
   }
}

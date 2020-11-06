package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.TaskReportField;
import ru.armd.pbk.dto.nsi.TaskReportFieldDTO;
import ru.armd.pbk.repositories.nsi.TaskReportFieldRepository;

/**
 * Сервис для поля отчета сотрудника.
 */
@Service
public class TaskReportFieldService
	extends BaseDomainService<TaskReportField, TaskReportFieldDTO> {

   private static final Logger LOGGER = Logger.getLogger(TaskReportFieldService.class);

   @Autowired
   public TaskReportFieldService(TaskReportFieldRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TaskReportFieldDTO toDTO(TaskReportField domain) {
	  TaskReportFieldDTO dto = new TaskReportFieldDTO();
	  dto.setName(domain.getName());
	  dto.setDescription(domain.getDescription());
	  return dto;
   }
}

package ru.armd.pbk.services.aspose.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.tasks.TaskReportBeanData;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.tasks.TaskReportReportMapper;
import ru.armd.pbk.mappers.tasks.TaskReportingMapper;
import ru.armd.pbk.mappers.tasks.TaskRouteReportMapper;
import ru.armd.pbk.services.reports.AsposeReportsService;
import ru.armd.pbk.views.tasks.TaskReportReportView;
import ru.armd.pbk.views.tasks.TaskReportView;
import ru.armd.pbk.views.tasks.TaskRoutesReportView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис генерации печатных форм заданий.
 */
@Service("taskReportingService")
public class TaskReportService
	extends AsposeReportsService {

   private static final Logger LOGGER = Logger.getLogger(TaskReportService.class);

   private TaskReportingMapper taskReportMapper;
   private TaskReportReportMapper taskReportReportMapper;

   private TaskRouteReportMapper taskRouteRepo;

   /**
	* Конструктор по мапперам задания и краткого отчёта по заданию.
	*
	* @param mapper                 маппер задания
	* @param taskReportReportMapper маппер краткого отчёта по заданию
	* @param taskRouteRepo          маппер маршрутов
	*/
   @Autowired
   public TaskReportService(TaskReportingMapper mapper, TaskReportReportMapper taskReportReportMapper, TaskRouteReportMapper taskRouteRepo) {
	  this.taskReportMapper = mapper;
	  this.taskReportReportMapper = taskReportReportMapper;
	  this.taskRouteRepo = taskRouteRepo;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Генерирует печатную форму задания.
	*
	* @param id   id задания
	* @param type свойства выходной печатной формы
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generate(IReportType type, Long id)
	   throws PBKAsposeReportException {
	  TaskReportView taskView = getTaskView(id);
	  TaskReportReportView taskReportView = getTaskReportView(id);
	  TaskRoutesReportView taskRoutesReportView = getTaskRoutesReportView(id);
	  return generateReport(type, new ReportData(new TaskReportBeanData(taskView, taskReportView, taskRoutesReportView)));
   }

   /**
	* Генерирует список печатных форм заданий.
	*
	* @param ids  список id заданий
	* @param type свойства выходной печатной формы
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public List<Map<ReportFormat, File>> generateMultiple(IReportType type, List<Long> ids)
	   throws PBKAsposeReportException {
	  List<ReportData> taskReportDataList = new ArrayList<>();
	  for (Long id : ids) {
		 TaskReportView taskView = getTaskView(id);
		 TaskReportReportView taskReportView = getTaskReportView(id);
		 TaskRoutesReportView taskRoutesReportView = getTaskRoutesReportView(id);
		 taskReportDataList.add(new ReportData(new TaskReportBeanData(taskView, taskReportView, taskRoutesReportView)));
	  }
	  return generateReports(type, taskReportDataList);
   }

   /**
	* Генерирует печатную форму, сформированную из отдельных печатных форм заданий с расположением их рядом на форме.
	*
	* @param ids  список id заданий
	* @param type свойства выходной печатной формы
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generateMerged(IReportType type, List<Long> ids)
	   throws PBKAsposeReportException {
	  List<ReportData> taskReportDataList = new ArrayList<>();
	  for (Long id : ids) {
		 TaskReportView taskView = getTaskView(id);
		 TaskReportReportView taskReportView = getTaskReportView(id);
		 TaskRoutesReportView taskRoutesReportView = getTaskRoutesReportView(id);
		 taskReportDataList.add(new ReportData(new TaskReportBeanData(taskView, taskReportView, taskRoutesReportView)));
	  }
	  return generateMergedReport(type, taskReportDataList);
   }

   private TaskReportView getTaskView(Long id) {
	  TaskReportView taskView = taskReportMapper.getById(id);
	  if (taskView == null) {
		 throw new PBKReportException("Задание не найдено в БД");
	  }
	  return taskView;
   }

   private TaskReportReportView getTaskReportView(Long id) {
	  TaskReportReportView taskReportView = taskReportReportMapper.getByTaskId(id);
	  if (taskReportView == null) {
		 throw new PBKReportException("Отчёт по заданию не найден в БД");
	  }
	  return taskReportView;
   }

   private TaskRoutesReportView getTaskRoutesReportView(Long id) {
	  List<String> routeNumbers = taskRouteRepo.getRouteNumbersByTaskId(id);
	  TaskRoutesReportView taskRoutes = new TaskRoutesReportView(routeNumbers);
	  return taskRoutes;
   }

}

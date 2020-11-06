package ru.armd.pbk.aspose.tasks;

import ru.armd.pbk.aspose.core.IReportDataBean;
import ru.armd.pbk.views.tasks.TaskReportReportView;
import ru.armd.pbk.views.tasks.TaskReportView;
import ru.armd.pbk.views.tasks.TaskRoutesReportView;

/**
 * Данные для размещения на печатной форме задания (само задание и отчёт).
 */
public class TaskReportBeanData
	implements IReportDataBean {

   private TaskReportView task;
   private TaskReportReportView taskReport;
   private TaskRoutesReportView taskRoutes;

   /**
	* Конструктор по данным для задания и данным для отчёта по заданию.
	*
	* @param task       данные для задания
	* @param taskReport данные для отчёта по заданию
	* @param taskRoutes маршруты задания
	*/
   public TaskReportBeanData(TaskReportView task, TaskReportReportView taskReport, TaskRoutesReportView taskRoutes) {
	  this.task = task;
	  this.taskReport = taskReport;
	  this.taskRoutes = taskRoutes;
   }

   public TaskReportView getTask() {
	  return task;
   }

   public void setTask(TaskReportView task) {
	  this.task = task;
   }

   public TaskReportReportView getTaskReport() {
	  return taskReport;
   }

   public void setTaskReport(TaskReportReportView taskReport) {
	  this.taskReport = taskReport;
   }

   public TaskRoutesReportView getTaskRoutes() {
	  return taskRoutes;
   }

   public void setTaskRoutes(TaskRoutesReportView taskRoutes) {
	  this.taskRoutes = taskRoutes;
   }
}

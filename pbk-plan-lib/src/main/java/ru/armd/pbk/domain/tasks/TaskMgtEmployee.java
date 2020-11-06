package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен связки задание - сотрудник МГТ.
 */
public class TaskMgtEmployee
	extends BaseDomain {

   private Long taskId;
   private Long employeeId;

   /**
	* Конструктор по умолчанию.
	*/
   public TaskMgtEmployee() {

   }

   /**
	* Конструктор.
	*
	* @param taskId     ИД задания
	* @param employeeId ИД сотрудника
	*/
   public TaskMgtEmployee(Long taskId, Long employeeId) {
	  this.taskId = taskId;
	  this.employeeId = employeeId;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }
}

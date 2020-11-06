package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен связки задание - сотрудник ГКУ ОП.
 */
public class TaskGkuopEmployee
	extends BaseDomain {

   private Long taskId;
   private Long employeeId;

   /**
	* Конструктор по умолчанию.
	*/
   public TaskGkuopEmployee() {

   }

   /**
	* Конструктор.
	*
	* @param taskId     ИД задания
	* @param employeeId ИД сотрудника
	*/
   public TaskGkuopEmployee(Long taskId, Long employeeId) {
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

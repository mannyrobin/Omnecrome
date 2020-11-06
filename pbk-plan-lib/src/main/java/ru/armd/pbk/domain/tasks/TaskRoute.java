package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен маршрута простого задания.
 */
public class TaskRoute
	extends BaseDomain {
   private Long taskId;
   private Long routeId;
   private String routeNumber;
   private Long districtId;

   /**
	* Базовый конструктор.
	*/
   public TaskRoute() {

   }

   /**
	* Конструктор.
	*
	* @param taskId  ИД задания.
	* @param routeId ИД маршрута.
	*/
   public TaskRoute(Long taskId, Long routeId) {
	  super();
	  this.taskId = taskId;
	  this.routeId = routeId;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}

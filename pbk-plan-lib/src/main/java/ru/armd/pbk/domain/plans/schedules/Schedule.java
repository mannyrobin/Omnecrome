package ru.armd.pbk.domain.plans.schedules;

/**
 * Домен для получения информации о сменах, на которые можно заменить в
 * расписании сотрудника.
 */
public class Schedule {
   private Integer workModeId;
   private Double workHours;

   public Integer getWorkModeId() {
	  return workModeId;
   }

   public void setWorkModeId(Integer workModeId) {
	  this.workModeId = workModeId;
   }

   public Double getWorkHours() {
	  return workHours;
   }

   public void setWorkHours(Double workHours) {
	  this.workHours = workHours;
   }
}

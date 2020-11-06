package ru.armd.pbk.views.tasks;

/**
 * Дополнительная информация по заданию.
 */
public class TaskAdditionalInfo {

   private Long id;
   private Long deptId;
   private Long venueId;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }
}

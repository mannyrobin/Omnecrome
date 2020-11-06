package ru.armd.pbk.views.nsi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedTimeSerializer;

import java.util.Date;

/**
 * View смены.
 */
public class ShiftView
	extends BaseGridView {

   private Long id;
   private String code;
   private String name;
   private String description;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date workStartTime;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date workEndTime;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date breakStartTime;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date breakEndTime;

   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Date getWorkStartTime() {
	  return workStartTime;
   }

   public void setWorkStartTime(Date workStartTime) {
	  this.workStartTime = workStartTime;
   }

   public Date getWorkEndTime() {
	  return workEndTime;
   }

   public void setWorkEndTime(Date workEndTime) {
	  this.workEndTime = workEndTime;
   }

   public Date getBreakStartTime() {
	  return breakStartTime;
   }

   public void setBreakStartTime(Date breakStartTime) {
	  this.breakStartTime = breakStartTime;
   }

   public Date getBreakEndTime() {
	  return breakEndTime;
   }

   public void setBreakEndTime(Date breakEndTime) {
	  this.breakEndTime = breakEndTime;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}

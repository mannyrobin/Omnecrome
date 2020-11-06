package ru.armd.pbk.views.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSeparatedDateTimeDetailedSerializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View результа очистки логов аудита.
 */
public class AuditLogView
	extends BaseGridView {

   private Long id;
   @JsonSerialize(using = DotSeparatedDateTimeDetailedSerializer.class)
   private Date startDate;
   @JsonSerialize(using = DotSeparatedDateTimeDetailedSerializer.class)
   private Date endDate;
   private String state;
   private String message;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date toDate;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public String getState() {
	  return state;
   }

   public void setState(String state) {
	  this.state = state;
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }

   public Date getToDate() {
	  return toDate;
   }

   public void setToDate(Date toDate) {
	  this.toDate = toDate;
   }

}

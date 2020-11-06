package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View для смены контролёра за дату.
 */
public class So2ShiftHoursView
	extends SoView {

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date date;
   private Long absenceShiftId;
   private Double shiftHours;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public Long getAbsenceShiftId() {
	  return absenceShiftId;
   }

   public void setAbsenceShiftId(Long absenceShiftId) {
	  this.absenceShiftId = absenceShiftId;
   }

   public Double getShiftHours() {
	  return shiftHours;
   }

   public void setShiftHours(Double shiftHours) {
	  this.shiftHours = shiftHours;
   }
}

package ru.armd.pbk.views.nsi.askp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * View для данных из АСКП.
 */
public class AskpChecksByHoursView
	extends BaseGridView {
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date workDate;
   private Integer sum;
   private List<Integer> hours;
   private Map<Integer, Integer> extcols;
   private Boolean holiday;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getSum() {
	  return sum;
   }

   public void setSum(Integer sum) {
	  this.sum = sum;
   }

   public List<Integer> getHours() {
	  return hours;
   }

   public void setHours(List<Integer> hours) {
	  this.hours = hours;
   }

   public Map<Integer, Integer> getExtcols() {
	  return extcols;
   }

   public void setExtcols(Map<Integer, Integer> extcols) {
	  this.extcols = extcols;
   }

   public Boolean getHoliday() {
	  return holiday;
   }

   public void setHoliday(Boolean holiday) {
	  this.holiday = holiday;
   }
}

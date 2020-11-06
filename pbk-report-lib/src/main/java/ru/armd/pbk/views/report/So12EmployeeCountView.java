package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View для связки даты и количества контролёров на дату (для использования в отчёте
 * "Совместный график по местам встреч").
 */
public class So12EmployeeCountView
	extends SoView {

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date date;


   private Integer employeeCount;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public Integer getEmployeeCount() {
	  return employeeCount;
   }

   public void setEmployeeCount(Integer employeeCount) {
	  this.employeeCount = employeeCount;
   }
}

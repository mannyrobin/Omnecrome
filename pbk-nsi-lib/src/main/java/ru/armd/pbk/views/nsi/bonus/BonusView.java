package ru.armd.pbk.views.nsi.bonus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View для отображения премирования.
 */
public class BonusView
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date periodStartDate;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date periodEndDate;

   private String name;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getPeriodStartDate() {
	  return periodStartDate;
   }

   public void setPeriodStartDate(Date periodStartDate) {
	  this.periodStartDate = periodStartDate;
   }

   public Date getPeriodEndDate() {
	  return periodEndDate;
   }

   public void setPeriodEndDate(Date periodEndDate) {
	  this.periodEndDate = periodEndDate;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }
}

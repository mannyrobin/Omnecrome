package ru.armd.pbk.domain.nsi.bonus;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен для сущности "Премирование".
 */
public class Bonus
	extends BaseDomain {

   private Long count;
   private Date periodStartDate;
   private Date periodEndDate;
   private String name;

   public Long getCount() {
	  return count;
   }

   public void setCount(Long count) {
	  this.count = count;
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

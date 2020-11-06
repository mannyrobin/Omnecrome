package ru.armd.pbk.domain.nsi.bonus;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен коэфициента премирования.
 */
public class BonusPeriod
	extends BaseDomain {

   private Long bonusId;
   private int countFrom;
   private Integer countTo;
   private int percent;

   public int getCountFrom() {
	  return countFrom;
   }

   public void setCountFrom(int countFrom) {
	  this.countFrom = countFrom;
   }

   public Integer getCountTo() {
	  return countTo;
   }

   public void setCountTo(Integer countTo) {
	  this.countTo = countTo;
   }

   public int getPercent() {
	  return percent;
   }

   public void setPercent(int percent) {
	  this.percent = percent;
   }

   public Long getBonusId() {
	  return bonusId;
   }

   public void setBonusId(Long bonusId) {
	  this.bonusId = bonusId;
   }
}
